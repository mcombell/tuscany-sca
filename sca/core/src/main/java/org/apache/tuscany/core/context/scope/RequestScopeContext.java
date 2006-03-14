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

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.tuscany.core.builder.ContextFactory;
import org.apache.tuscany.core.context.InstanceContext;
import org.apache.tuscany.core.context.Context;
import org.apache.tuscany.core.context.LifecycleEventListener;
import org.apache.tuscany.core.context.CoreRuntimeException;
import org.apache.tuscany.core.context.EventContext;
import org.apache.tuscany.core.context.RuntimeEventListener;
import org.apache.tuscany.core.context.SimpleComponentContext;

/**
 * An implementation of a request-scoped component container.
 * 
 * @version $Rev$ $Date$
 */
public class RequestScopeContext extends AbstractScopeContext implements RuntimeEventListener, LifecycleEventListener {

    // ----------------------------------
    // Fields
    // ----------------------------------

    // A collection of service component contexts keyed by thread. Note this could have been implemented with a ThreadLocal but
    // using a Map allows finer-grained concurrency.
    private Map<Object, Map<String, InstanceContext>> contextMap;

    // stores ordered lists of contexts to shutdown for each thread.
    private Map<Object, Queue> destroyComponents;

    // ----------------------------------
    // Constructor
    // ----------------------------------

    public RequestScopeContext(EventContext eventContext) {
        super(eventContext);
        setName("Request Scope");
    }

    // ----------------------------------
    // Listener methods
    // ----------------------------------

    public void onEvent(int type, Object key) {
        checkInit();
        /* clean up current context for pooled threads */
        if (type == EventContext.REQUEST_END) {
            getEventContext().clearIdentifier(EventContext.HTTP_SESSION);
            notifyInstanceShutdown(Thread.currentThread());
            destroyContext();
        }
    }

    // ----------------------------------
    // Lifecycle methods
    // ----------------------------------

    public synchronized void start() {
        if (lifecycleState != UNINITIALIZED) {
            throw new IllegalStateException("Scope must be in UNINITIALIZED state [" + lifecycleState + "]");
        }
        super.start();
        contextMap = new ConcurrentHashMap();
        destroyComponents = new ConcurrentHashMap();
        lifecycleState = RUNNING;

    }

    public synchronized void stop() {
        if (lifecycleState != RUNNING) {
            throw new IllegalStateException("Scope in wrong state [" + lifecycleState + "]");
        }
        super.stop();
        contextMap = null;
        destroyComponents = null;
        lifecycleState = STOPPED;
    }

    // ----------------------------------
    // Methods
    // ----------------------------------

    public boolean isCacheable() {
        return true;
    }

    public void registerFactory(ContextFactory<InstanceContext> configuration) {
        contextFactorys.put(configuration.getName(), configuration);
    }
    
    public InstanceContext getContext(String ctxName) {
        checkInit();
        Map<String, InstanceContext> contexts = getComponentContexts();
        InstanceContext ctx = contexts.get(ctxName);
        if (ctx == null){
            // check to see if the configuration was added after the request was started
            ContextFactory<InstanceContext> configuration = contextFactorys.get(ctxName);
            if (configuration != null) {
                ctx = configuration.createContext();
                ctx.addContextListener(this);
                ctx.start();
                contexts.put(ctx.getName(), ctx);
            }
        }
        return ctx;
    }

    public InstanceContext getContextByKey(String ctxName, Object key) {
        checkInit();
        if (key == null) {
            return null;
        }
        Map<String, InstanceContext> components = (Map) contextMap.get(key);
        if (components == null) {
            return null;
        }
        return components.get(ctxName);
    }

    public void removeContext(String ctxName) {
        checkInit();
        removeContextByKey(ctxName, Thread.currentThread());
    }

    public void removeContextByKey(String ctxName, Object key) {
        checkInit();
        if (key == null || ctxName == null) {
            return;
        }
        Map components = (Map) contextMap.get(key);
        if (components == null) {
            return;
        }
        components.remove(ctxName);
        Map<String, InstanceContext> contexts = (Map) contextMap.get(key);
        // no synchronization for the following two operations since the request
        // context will not be shutdown before the second call is processed
        InstanceContext context = contexts.get(ctxName);
        destroyComponents.get(key).remove(context);
    }

    public void onInstanceCreate(Context context) {
        checkInit();
        if (context instanceof SimpleComponentContext) {
            // Queue the context to have its implementation instance released if destroyable
            if (((SimpleComponentContext) context).isDestroyable()) {
                Queue collection = destroyComponents.get(Thread.currentThread());
                collection.add(context);
            }
        }
    }

    /**
     * Returns an array of {@link SimpleComponentContext}s representing components that need to be notified of scope shutdown.
     */
    protected InstanceContext[] getShutdownContexts(Object key) {
        checkInit();
        Queue queue = destroyComponents.get(Thread.currentThread());
        if (queue != null) {
            // create 0-length array since Queue.size() has O(n) traversal
            return (InstanceContext[]) queue.toArray(new InstanceContext[0]);
        } else {
            return null;
        }
    }

    // ----------------------------------
    // Private methods
    // ----------------------------------

    private void destroyContext() {
        // TODO uninitialize all request-scoped components
        contextMap.remove(Thread.currentThread());
        destroyComponents.remove(Thread.currentThread());
    }

    /**
     * Initializes ServiceComponentContexts for the current request.
     * <p>
     * TODO This eagerly creates all component contexts, even if the component is never accessed during the request. This method
     * should be profiled to determine if lazy initialization is more performant
     * <p>
     * TODO Eager initialization is not performed for request-scoped components
     */

    private Map<String, InstanceContext> getComponentContexts() throws CoreRuntimeException {
        Map contexts = (Map) contextMap.get(Thread.currentThread());
        if (contexts == null) {
            contexts = new ConcurrentHashMap();
            Queue shutdownQueue = new ConcurrentLinkedQueue();
            for (ContextFactory<InstanceContext> config : contextFactorys.values()) {
                InstanceContext context = null;
                context = config.createContext();
                context.addContextListener(this);
                context.start();
                contexts.put(context.getName(), context);
            }
            contextMap.put(Thread.currentThread(), contexts);
            destroyComponents.put(Thread.currentThread(), shutdownQueue);
        }
        return contexts;
    }

}