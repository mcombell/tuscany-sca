/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package org.apache.tuscany.sca.implementation.java.introspect.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.implementation.java.IntrospectionException;
import org.apache.tuscany.sca.implementation.java.JavaImplementation;
import org.apache.tuscany.sca.implementation.java.introspect.BaseJavaClassVisitor;
import org.oasisopen.sca.annotation.Destroy;

/**
 * Processes the {@link @Destroy} annotation on a component implementation and
 * updates the component type with the decorated destructor method
 * 
 * @version $Rev$ $Date$
 */
public class DestroyProcessor extends BaseJavaClassVisitor {
    
    public DestroyProcessor(AssemblyFactory factory) {
        super(factory);
    }

    @Override
    public void visitMethod(Method method, JavaImplementation type) throws IntrospectionException {
        Destroy annotation = method.getAnnotation(Destroy.class);
        if (annotation == null) {
            return;
        }
        if (method.getParameterTypes().length != 0) {
            throw new IllegalDestructorException("Destructor must not have argments", method);
        }
        if(!method.getReturnType().equals(void.class)) {
            throw new IllegalDestructorException("Destructor must return void.", method);
        }
        if (type.getDestroyMethod() != null) {
            throw new DuplicateDestructorException("More than one destructor found on implementation");
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            throw new IllegalDestructorException("Destructor must be a public method. Invalid annotation @Destroy found on "+method);
        }
        type.setDestroyMethod(method);
    }
}
