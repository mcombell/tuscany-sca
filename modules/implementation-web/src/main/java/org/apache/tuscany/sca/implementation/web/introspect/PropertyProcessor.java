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
package org.apache.tuscany.sca.implementation.web.introspect;

import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.sca.assembly.AssemblyFactory;
import org.apache.tuscany.sca.assembly.Property;
import org.apache.tuscany.sca.implementation.java.IntrospectionException;
import org.apache.tuscany.sca.implementation.java.JavaImplementation;
import org.apache.tuscany.sca.implementation.java.impl.JavaElementImpl;
import org.apache.tuscany.sca.implementation.java.impl.JavaParameterImpl;
import org.apache.tuscany.sca.implementation.java.introspect.impl.BaseJavaClassVisitor;
import org.apache.tuscany.sca.implementation.java.introspect.impl.DuplicatePropertyException;
import org.apache.tuscany.sca.implementation.java.introspect.impl.IllegalPropertyException;
import org.apache.tuscany.sca.implementation.java.introspect.impl.JavaIntrospectionHelper;
import org.apache.tuscany.sca.interfacedef.util.JavaXMLMapper;

/**
 * Class to handle annotations that add Properties.
 * 
 * @version $Rev$ $Date$
 */
public class PropertyProcessor extends BaseJavaClassVisitor {
    public PropertyProcessor(AssemblyFactory assemblyFactory) {
        super(assemblyFactory);
    }
    
    private boolean removeProperty(JavaElementImpl prop, JavaImplementation type) {
        if(prop==null) {
            return false;
        }
        List<Property> props = type.getProperties();
        for(int i=0;i<props.size();i++) {
            if(props.get(i).getName().equals(prop.getName())) {
                props.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void visitMethod(Method method, JavaImplementation type) throws IntrospectionException {
        org.osoa.sca.annotations.Property annotation = method.getAnnotation(org.osoa.sca.annotations.Property.class);
        if (annotation == null) {
            return;
        }

        if (!JavaIntrospectionHelper.isSetter(method)) {
            throw new IllegalPropertyException("Annotated method is not a setter: " + method, method);
        }

        String name = annotation.name();
        if (name == null || "".equals(name)) {
            name = method.getName();
            if (name.startsWith("set")) {
                name = JavaIntrospectionHelper.toPropertyName(method.getName());
            }
            // When the name is not specified, prefix the computed name with the class name
            name = method.getDeclaringClass().getName()+"_"+name;
        }

        Map<String, JavaElementImpl> properties = type.getPropertyMembers();
        JavaElementImpl prop = properties.get(name);
        // Setter override field
        if (prop != null && prop.getElementType() != ElementType.FIELD) {
            throw new DuplicatePropertyException(name);
        }

        removeProperty(prop, type);
        
        JavaElementImpl element = new JavaElementImpl(method, 0);
        Property property = createProperty(name, element);

        property.setMustSupply(annotation.required());
        type.getProperties().add(property);
        properties.put(name, element);
    }

    @Override
    public void visitField(Field field, JavaImplementation type) throws IntrospectionException {

        org.osoa.sca.annotations.Property annotation = field.getAnnotation(org.osoa.sca.annotations.Property.class);
        if (annotation == null) {
            return;
        }

        String name = annotation.name();
        if (name == null) {
            name = "";
        }
        if ("".equals(name) || name.equals(field.getType().getName())) {
            name = field.getName();
            // When the name is not specified, prefix the computed name with the class name
            name = field.getDeclaringClass().getName()+"_"+name;
        }

        Map<String, JavaElementImpl> properties = type.getPropertyMembers();
        JavaElementImpl prop = properties.get(name);
        // Setter override field
        if (prop != null && prop.getElementType() == ElementType.FIELD) {
            throw new DuplicatePropertyException(name);
        }

        if (prop == null) {
            JavaElementImpl element = new JavaElementImpl(field);
            Property property = createProperty(name, element);
            property.setMustSupply(annotation.required());
            type.getProperties().add(property);
            properties.put(name, element);
        }
    }

    @Override
    public void visitConstructorParameter(JavaParameterImpl parameter, JavaImplementation type)
        throws IntrospectionException {
    }

    protected Property createProperty(String name, JavaElementImpl element) throws IntrospectionException {

        Property property = assemblyFactory.createProperty();
        property.setName(name);
        Class<?> baseType = JavaIntrospectionHelper.getBaseType(element.getType(), element.getGenericType());
        property.setXSDType(JavaXMLMapper.getXMLType(baseType));

        Class<?> javaType = element.getType();
        if (javaType.isArray() || Collection.class.isAssignableFrom(javaType)) {
            property.setMany(true);
        }
        return property;
    }
}
