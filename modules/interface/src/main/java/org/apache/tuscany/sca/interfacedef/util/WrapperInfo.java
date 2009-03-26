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

package org.apache.tuscany.sca.interfacedef.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.sca.interfacedef.DataType;
import org.apache.tuscany.sca.interfacedef.impl.DataTypeImpl;

/**
 * The "Wrapper Style" WSDL operation is defined by The Java API for XML-Based
 * Web Services (JAX-WS) 2.0 specification, section 2.3.1.2 Wrapper Style. <p/>
 * A WSDL operation qualifies for wrapper style mapping only if the following
 * criteria are met:
 * <ul>
 * <li>(i) The operation is input and output messages (if present) each contain
 * only a single part
 * <li>(ii) The input message part refers to a global element declaration whose
 * localname is equal to the operation name
 * <li>(iii) The output message part refers to a global element declaration
 * <li>(iv) The elements referred to by the input and output message parts
 * (henceforth referred to as wrapper elements) are both complex types defined
 * using the xsd:sequence compositor
 * <li>(v) The wrapper elements only contain child elements, they must not
 * contain other structures such as wildcards (element or attribute),
 * xsd:choice, substitution groups (element references are not permitted) or
 * attributes; furthermore, they must not be nillable.
 * </ul>
 * 
 * @version $Rev$ $Date$
 */
public class WrapperInfo {
    private String dataBinding;
    private ElementInfo wrapperElement;
    private List<ElementInfo> childElements;
    private DataType<XMLType> wrapperType;

    // A cache for the derived data type of the unwrapped 
    // input child elements 
    private DataType<List<DataType>> unwrappedInputType;

    // A cache for the derived data type of the unwrapped 
    // output child element (we only support one child)
    private DataType<XMLType> unwrappedOutputType;

    public WrapperInfo(String dataBinding,
                       ElementInfo wrapperElement,
                       List<ElementInfo> childElements) {
        super();
        this.dataBinding = dataBinding;
        this.wrapperElement = wrapperElement;
        this.childElements = childElements;
    }

    public List<ElementInfo> getChildElements() {
        return childElements;
    }

    public ElementInfo getWrapperElement() {
        return wrapperElement;
    }

    public String getDataBinding() {
        return dataBinding;
    }

    public void setDataBinding(String dataBinding) {
        this.dataBinding = dataBinding;
    }

    public DataType<XMLType> getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(DataType<XMLType> wrapperType) {
        this.wrapperType = wrapperType;
    }
    
    public Class<?> getWrapperClass() {
        return wrapperType == null ? null : wrapperType.getPhysical();
    }    


    public DataType<List<DataType>> getUnwrappedInputType() {
        if (unwrappedInputType == null) {
            List<DataType> childTypes = new ArrayList<DataType>();
            for (ElementInfo element : getChildElements()) {
                DataType type = getDataType(element);
                childTypes.add(type);
            }
            unwrappedInputType = new DataTypeImpl<List<DataType>>("idl:unwrapped.input", Object[].class, childTypes);
        }
        return unwrappedInputType;
    }

    public DataType getUnwrappedOutputType() {
        if (unwrappedOutputType == null) {
            List<ElementInfo> elements = getChildElements();
            if (elements != null && elements.size() > 0) {
                if (elements.size() > 1) {
                    // We don't support output with multiple parts
                    // throw new IllegalArgumentException("Multi-part output is not supported");
                }
                ElementInfo element = elements.get(0);

                unwrappedOutputType = getDataType(element);
            }
        }
        return unwrappedOutputType;
    }
    
    private DataType getDataType(ElementInfo element) {
        DataType type = null;
        if (element.isMany()) {
            DataType logical = new DataTypeImpl<XMLType>(dataBinding, Object.class, new XMLType(element));
            type = new DataTypeImpl<DataType>("java:array", Object[].class, logical);
        } else {
            type = new DataTypeImpl<XMLType>(dataBinding, Object.class, new XMLType(element));
        }
        return type;
    }    
}
