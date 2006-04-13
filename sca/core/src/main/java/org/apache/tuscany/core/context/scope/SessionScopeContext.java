/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.core.context.scope;

import org.apache.tuscany.core.builder.ContextFactory;
import org.apache.tuscany.core.context.AtomicContext;
import org.apache.tuscany.core.context.Context;
import org.apache.tuscany.core.context.CoreRuntimeException;
import org.apache.tuscany.core.context.EventContext;
import org.apache.tuscany.core.context.RuntimeEventListener;
import org.apache.tuscany.core.context.ScopeRuntimeException;
import org.apache.tuscany.core.context.event.ContextCreatedEvent;
import org.apache.tuscany.core.context.event.Event;
import org.apache.tuscany.core.context.event.HttpSessionEvent;
import org.apache.tuscany.core.context.event.SessionEndEvent;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * An implementation of an session-scoped component container
 * TODO this implementation needs to be made generic so that it supports a range of session types, i.e. not tied to HTTP
 * session scope 
 * 
 * @version $Rev$ $Date$
 */
public class SessionScopeContext extends AbstractScopeContext implements RuntimeEventListener {

    // The collection of service component contexts keyed by session
    private Map<Object, Map<String, Context>> contexts;

    // Stores ordered lists of contexts to shutdown keyed by session
    private Map<Object, Queue<AtomicContext>> destroyableContexts;

    public SessionScopeContext(EventContext eventContext) {
        super(eventContext);
        setName("Session Scope");
    }

    public synchronized void start() {
        if (lifecycleState != UNINITIALIZED) {
            throw new IllegalStateException("Scope container must be in UNINITIALIZED state");
        }
        contexts = new ConcurrentHashMap<Object, Map<String, Context>>();
        destroyableContexts = new ConcurrentHashMap<Object, Queue<AtomicContext>>();
        lifecycleState = RUNNING;
    }

    public synchronized void stop() {
        if (lifecycleState != RUNNING) {
            throw new IllegalStateException("Scope container in wrong state");
        }
        contexts = null;
        contexts = null;
        destroyableContexts = null;
        lifecycleState = STOPPED;
    }

    public void onEvent(Event event) {
        if (event instanceof SessionEndEvent){
            checkInit();
            Object key = ((SessionEndEvent)event).getId();
            notifyInstanceShutdown(key);
            destroyComponentContext(key);
        }else if(event instanceof ContextCreatedEvent){
            checkInit();
            if (event.getSource() instanceof AtomicContext) {
                 AtomicContext simpleCtx = (AtomicContext)event.getSource();
                 // if destroyable, queue the context to have its component implementation instance released
                 if (simpleCtx.isDestroyable()) {
                     Object sessionKey = getEventContext().getIdentifier(HttpSessionEvent.HTTP_IDENTIFIER);
                     Queue<AtomicContext> comps = destroyableContexts.get(sessionKey);
                     if (comps == null) {
                         ScopeRuntimeException e = new ScopeRuntimeException("Shutdown queue not found for key");
                         e.setIdentifier(sessionKey.toString());
                         throw e;
                     }
                     comps.add(simpleCtx);
                 }
            }
        }
    }

    public boolean isCacheable() {
        return true;
    }

    public void registerFactory(ContextFactory<Context> configuration) {
        contextFactorys.put(configuration.getName(), configuration);
    }

    public Context getContext(String ctxName) {
        checkInit();
        if (ctxName == null) {
            return null;
        }
        // try{
        Map<String, Context> ctxs = getSessionContext();
        if (ctxs == null) {
            return null;
        }
        Context ctx = ctxs.get(ctxName);
        if (ctx == null) {
            // the configuration was added after the session had started, so create a context now and start it
            ContextFactory<Context> configuration = contextFactorys.get(ctxName);
            if (configuration != null) {
                ctx = configuration.createContext();
                ctx.addListener(this);
                ctx.start();
                ctxs.put(ctx.getName(), ctx);
            }
        }
        return ctx;
    }

    public Context getContextByKey(String ctxName, Object key) {
        checkInit();
        if (key == null && ctxName == null) {
            return null;
        }
        Map components = contexts.get(key);
        if (components == null) {
            return null;
        }
        return (Context) components.get(ctxName);
    }

    public void removeContext(String ctxName) {
        checkInit();
        Object key = getEventContext().getIdentifier(HttpSessionEvent.HTTP_IDENTIFIER);
        removeContextByKey(ctxName, key);
    }

    public void removeContextByKey(String ctxName, Object key) {
        checkInit();
        if (key == null || ctxName == null) {
            return;
        }
        Map components = contexts.get(key);
        if (components == null) {
            return;
        }
        components.remove(ctxName);
        Map<String, Context> definitions = contexts.get(key);
        Context ctx = definitions.get(ctxName);
        if (ctx != null){
            destroyableContexts.get(key).remove(ctx);
        }
        definitions.remove(ctxName);
    }


    /**
     * Returns an array of {@link AtomicContext}s representing components that need to be notified of scope shutdown or
     * null if none found.
     */
    protected Context[] getShutdownContexts(Object key) {
        /*
         * This method will be called from the Listener which is associated with a different thread than the request. So, just
         * grab the key directly
         */
        Queue<AtomicContext> queue = destroyableContexts.get(key);
        if (queue != null) {
            // create 0-length array since Queue.size() has O(n) traversal
            return queue.toArray(new AtomicContext[0]);
        }
        return null;
    }

    /**
     * Returns and, if necessary, creates a context for the current sesion
     */
    private Map<String, Context> getSessionContext() throws CoreRuntimeException {
        Object key = getEventContext().getIdentifier(HttpSessionEvent.HTTP_IDENTIFIER);
        if (key == null) {
            throw new ScopeRuntimeException("Session key not set in request context");
        }
        Map<String, Context> m = contexts.get(key);
        if (m != null) {
            return m; // already created, return
        }
        Map<String, Context> sessionContext = new ConcurrentHashMap<String, Context>(contextFactorys.size());
        for (ContextFactory<Context> config : contextFactorys.values()) {
            Context context = config.createContext();
            context.addListener(this);
            context.start();
            sessionContext.put(context.getName(), context);
        }

        Queue<AtomicContext> shutdownQueue = new ConcurrentLinkedQueue<AtomicContext>();
        contexts.put(key, sessionContext);
        destroyableContexts.put(key, shutdownQueue);
        // initialize eager components. Note this cannot be done when we initially create each context since a component may
        // contain a forward reference to a component which has not been instantiated
        for (Context context : sessionContext.values()) {
            if (context instanceof AtomicContext) {
                AtomicContext atomic = (AtomicContext) context;
                if (atomic.isEagerInit()) {
                    context.notify();  // Notify the instance
                    //if (atomic.isDestroyable()) {
                    shutdownQueue.add(atomic);
                    //}
                }
            }
        }
        return sessionContext;
    }

    /**
     * Removes the components associated with an expiring context
     */
    private void destroyComponentContext(Object key) {
        contexts.remove(key);
        destroyableContexts.remove(key);
    }

}