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

package org.apache.tuscany.sca.databinding.sdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.databinding.TransformationContext;
import org.apache.tuscany.sca.databinding.WrapperHandler;
import org.apache.tuscany.sca.interfacedef.DataType;
import org.apache.tuscany.sca.interfacedef.impl.DataTypeImpl;
import org.apache.tuscany.sca.interfacedef.util.ElementInfo;
import org.apache.tuscany.sca.interfacedef.util.TypeInfo;
import org.apache.tuscany.sca.interfacedef.util.XMLType;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Sequence;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.HelperContext;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XSDHelper;

/**
 * SDO Wrapper Handler
 */
public class SDOWrapperHandler implements WrapperHandler<Object> {

    /**
     * @see org.apache.tuscany.sca.databinding.WrapperHandler#create(ElementInfo, TransformationContext)
     */
    public Object create(ElementInfo element, TransformationContext context) {
        DataObject wrapper = null;
        HelperContext helperContext = SDOContextHelper.getHelperContext(context);
        Type sdoType = getSDOType(helperContext, element);
        if (sdoType != null) {
            DataFactory dataFactory = helperContext.getDataFactory();
            return dataFactory.create(sdoType);
        }
        return null;
    }

    /**
     * @see org.apache.tuscany.sca.databinding.WrapperHandler#setChild(java.lang.Object, int, ElementInfo,
     *      java.lang.Object)
     */
    public void setChild(Object wrapper, int i, ElementInfo childElement, Object value) {
        DataObject wrapperDO =
            (wrapper instanceof XMLDocument) ? ((XMLDocument)wrapper).getRootObject() : (DataObject)wrapper;
        wrapperDO.set(i, value);
    }

    @SuppressWarnings("unchecked")
    public List getChildren(Object wrapper, List<ElementInfo> childElements, TransformationContext context) {
        DataObject wrapperDO =
            (wrapper instanceof XMLDocument) ? ((XMLDocument)wrapper).getRootObject() : (DataObject)wrapper;
        List<Property> properties = wrapperDO.getInstanceProperties();
        List<Object> elements = new ArrayList<Object>();
        Type type = wrapperDO.getType();
        if (type.isSequenced()) {
            // Add values in the sequence
            Sequence sequence = wrapperDO.getSequence();
            for (int i = 0; i < sequence.size(); i++) {
                // Skip mixed text
                if (sequence.getProperty(i) != null) {
                    elements.add(sequence.getValue(i));
                }
            }
        } else {
            for (Property p : properties) {
                Object child = wrapperDO.get(p);
                if (p.isMany()) {
                    for (Object c : (Collection<?>)child) {
                        elements.add(c);
                    }
                } else {
                    elements.add(child);
                }
            }
        }
        return elements;
    }

    /**
     * @see org.apache.tuscany.sca.databinding.WrapperHandler#getWrapperType(org.apache.tuscany.sca.interfacedef.util.ElementInfo, List, org.apache.tuscany.sca.databinding.TransformationContext)
     */
    public DataType getWrapperType(ElementInfo element, List<ElementInfo> childElements, TransformationContext context) {
        // FIXME: [rfeng] Temporarily disable the wrapping support for SDO to work around a few issues
        // in the WSDL-less story: https://issues.apache.org/jira/browse/TUSCANY-1713
//        if (true) {
//            return null;
//        }
        HelperContext helperContext = SDOContextHelper.getHelperContext(context);
        Type sdoType = getSDOType(helperContext, element);
        if (sdoType != null) {
            // Check if child elements matches
            for (ElementInfo child : childElements) {
                if (sdoType.getProperty(child.getQName().getLocalPart()) == null) {
                    return null;
                }
            }
            Class physical = sdoType.getInstanceClass();
            DataType<XMLType> wrapperType =
                new DataTypeImpl<XMLType>(SDODataBinding.NAME, physical, new XMLType(element));
            return wrapperType;
        } else {
            return null;
        }
    }

    /**
     * @param helperContext
     * @param element
     * @return
     */
    private Type getSDOType(HelperContext helperContext, ElementInfo element) {
        XSDHelper xsdHelper = helperContext.getXSDHelper();
        Type sdoType = null;
        Property prop =
            xsdHelper.getGlobalProperty(element.getQName().getNamespaceURI(), element.getQName().getLocalPart(), true);
        if (prop != null) {
            sdoType = prop.getType();
        } else {
            TypeInfo type = element.getType();
            QName typeName = type != null ? type.getQName() : null;
            if (typeName != null) {
                sdoType = helperContext.getTypeHelper().getType(typeName.getNamespaceURI(), typeName.getLocalPart());
            }
        }
        return sdoType;
    }

    /**
     * @see org.apache.tuscany.sca.databinding.WrapperHandler#isInstance(java.lang.Object, org.apache.tuscany.sca.interfacedef.util.ElementInfo, java.util.List, org.apache.tuscany.sca.databinding.TransformationContext)
     */
    public boolean isInstance(Object wrapper,
                              ElementInfo element,
                              List<ElementInfo> childElements,
                              TransformationContext context) {
        HelperContext helperContext = SDOContextHelper.getHelperContext(context);
        Type sdoType = getSDOType(helperContext, element);
        if (sdoType != null) {
            return sdoType.isInstance(wrapper);
        }
        return false;
    }
}