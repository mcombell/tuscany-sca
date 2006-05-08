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
package org.apache.tuscany.container.java.context;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.common.ObjectCreationException;
import org.apache.tuscany.common.ObjectFactory;
import org.apache.tuscany.core.context.PojoAtomicContext;
import org.apache.tuscany.core.context.PojoInstanceContext;
import org.apache.tuscany.core.injection.EventInvoker;
import org.apache.tuscany.core.injection.FieldInjector;
import org.apache.tuscany.core.injection.Injector;
import org.apache.tuscany.core.injection.InvalidAccessorException;
import org.apache.tuscany.core.injection.MethodInjector;
import org.apache.tuscany.core.injection.NoAccessorException;
import org.apache.tuscany.core.injection.ProxyObjectFactory;
import org.apache.tuscany.core.injection.ArrayMultiplicityObjectFactory;
import org.apache.tuscany.core.injection.ListMultiplicityObjectFactory;
import org.apache.tuscany.spi.QualifiedName;
import org.apache.tuscany.spi.context.InstanceContext;
import org.apache.tuscany.spi.context.TargetException;
import org.apache.tuscany.spi.wire.SourceWireFactory;
import org.apache.tuscany.spi.wire.TargetWireFactory;

/**
 * Manages Java component implementation instances
 *
 * @version $Rev$ $Date$
 */
public class JavaAtomicContext extends PojoAtomicContext {

    private Map<String, TargetWireFactory> targetWireFactories = new HashMap<String, TargetWireFactory>();
    private List<SourceWireFactory> sourceWireFactories = new ArrayList<SourceWireFactory>();
    private List<Injector> injectors;
    private Map<String, Member> members;

    public JavaAtomicContext(String name, ObjectFactory<?> objectFactory, boolean eagerInit, EventInvoker<Object> initInvoker,
                             EventInvoker<Object> destroyInvoker, List<Injector> injectors, Map<String, Member> members) {
        super(name, objectFactory, eagerInit, initInvoker, destroyInvoker);
        this.objectFactory = objectFactory;
        this.injectors = injectors;
        this.members = members;
    }

    public InstanceContext createInstance() throws ObjectCreationException {
        InstanceContext ctx = new PojoInstanceContext(this, objectFactory.getInstance());
        ctx.start();
        return ctx;
    }

    public void prepare() {
    }

    public Object getInstance(QualifiedName qName) throws TargetException {
        return getTargetInstance();
    }

    public void addTargetWireFactory(String serviceName, TargetWireFactory factory) {
        targetWireFactories.put(serviceName, factory);
    }

    public TargetWireFactory getTargetWireFactory(String serviceName) {
        return targetWireFactories.get(serviceName);
    }

    public Map<String, TargetWireFactory> getTargetWireFactories() {
        return targetWireFactories;
    }

    public void addSourceWireFactory(String referenceName, SourceWireFactory factory) {
        Member member = members.get(referenceName);
        if(member == null){
            throw new NoAccessorException(referenceName);
        }
        injectors.add(createInjector(member, factory));
        sourceWireFactories.add(factory);
    }

    public void addSourceWireFactories(String referenceName, Class<?> multiplicityClass,  List<SourceWireFactory> factories) {
        Member member = members.get(referenceName);
        if(member == null){
            throw new NoAccessorException(referenceName);
        }
        injectors.add(createMultiplicityInjector(member, multiplicityClass, factories));
        sourceWireFactories.addAll(factories);
    }

    public List<SourceWireFactory> getSourceWireFactories() {
        return sourceWireFactories;
    }


    private Injector createInjector(Member member, SourceWireFactory wireFactory) {
        ObjectFactory<?> factory = new ProxyObjectFactory(wireFactory);
        if (member instanceof Field) {
            return new FieldInjector(((Field) member), factory);
        } else if (member instanceof Method) {
            return new MethodInjector(((Method) member), factory);
        } else {
            InvalidAccessorException e = new InvalidAccessorException("Member must be a field or method");
            e.setIdentifier(member.getName());
            throw e;
        }
    }

    private Injector createMultiplicityInjector(Member member, Class<?> interfaceType, List<SourceWireFactory> wireFactories) {
        List<ObjectFactory<?>> factories = new ArrayList<ObjectFactory<?>>();
        for (SourceWireFactory wireFactory : wireFactories) {
            factories.add(new ProxyObjectFactory(wireFactory));
        }
        if (member instanceof Field) {
            Field field = (Field) member;
            if (field.getType().isArray()) {
                return new FieldInjector(field, new ArrayMultiplicityObjectFactory(interfaceType, factories));
            } else {
                return new FieldInjector(field, new ListMultiplicityObjectFactory(factories));
            }
        } else if (member instanceof Method) {
            Method method = (Method) member;
            if (method.getParameterTypes()[0].isArray()) {
                return new MethodInjector(method, new ArrayMultiplicityObjectFactory(interfaceType, factories));
            } else {
                return new MethodInjector(method, new ListMultiplicityObjectFactory(factories));
            }
        } else {
            InvalidAccessorException e = new InvalidAccessorException("Member must be a field or method");
            e.setIdentifier(member.getName());
            throw e;
        }
    }
    /**
     * Creates an <code>Injector</code> for a set of object factories associated with a reference.
     */
//    private Injector createInjector(String refName, Class refClass, boolean multiplicity, List<ObjectFactory> objectFactories,
//                                    Set<Field> fields, Set<Method> methods) throws NoAccessorException, BuilderConfigException {
//        Field field;
//        Method method = null;
//        if (multiplicity) {
//            // since this is a multiplicity, we cannot match on business interface type, so scan through the fields,
//            // matching on name and List or Array
//            field = JavaIntrospectionHelper.findMultiplicityFieldByName(refName, fields);
//            if (field == null) {
//                // No fields found. Again, since this is a multiplicity, we cannot match on business interface type, so
//                // scan through the fields, matching on name and List or Array
//                method = JavaIntrospectionHelper.findMultiplicityMethodByName(refName, methods);
//                if (method == null) {
//                    throw new NoAccessorException(refName);
//                }
//            }
//            Injector injector;
//            // for multiplicities, we need to inject the reference proxy or proxies using an object factory
//            // which first delegates to create the proxies and then returns them in the appropriate List or array type
//            if (field != null) {
//                if (field.getType().isArray()) {
//                    injector = new FieldInjector(field, new ArrayMultiplicityObjectFactory(refClass, objectFactories));
//                } else {
//                    injector = new FieldInjector(field, new ListMultiplicityObjectFactory(objectFactories));
//                }
//            } else {
//                if (method.getParameterTypes()[0].isArray()) {
//                    injector = new MethodInjector(method, new ArrayMultiplicityObjectFactory(refClass, objectFactories));
//                } else {
//                    injector = new MethodInjector(method, new ListMultiplicityObjectFactory(objectFactories));
//                }
//            }
//            return injector;
//        } else {
//            field = JavaIntrospectionHelper.findClosestMatchingField(refName, refClass, fields);
//            if (field == null) {
//                method = JavaIntrospectionHelper.findClosestMatchingMethod(refName, new Class[]{refClass}, methods);
//                if (method == null) {
//                    throw new NoAccessorException(refName);
//                }
//            }
//            Injector injector;
//            if (field != null) {
//                injector = new FieldInjector(field, objectFactories.get(0));
//            } else {
//                injector = new MethodInjector(method, objectFactories.get(0));
//            }
//            return injector;
//        }
//    }


}
