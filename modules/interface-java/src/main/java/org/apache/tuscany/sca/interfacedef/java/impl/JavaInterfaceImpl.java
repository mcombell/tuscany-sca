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
package org.apache.tuscany.sca.interfacedef.java.impl;

import javax.xml.namespace.QName;
import org.apache.tuscany.sca.interfacedef.impl.InterfaceImpl;
import org.apache.tuscany.sca.interfacedef.java.JavaInterface;

/**
 * Represents a Java interface.
 * 
 * @version $Rev$ $Date$
 */
public class JavaInterfaceImpl extends InterfaceImpl implements JavaInterface {

    private String className;
    private Class<?> javaClass;
    private Class<?> callbackClass;
    private QName qname;
    
    protected JavaInterfaceImpl() {
    }

    public String getName() {
        if (isUnresolved()) {
            return className;
        } else if (javaClass != null) {
            return javaClass.getName();
        } else {
            return null;
        }
    }

    public void setName(String className) {
        if (!isUnresolved()) {
            throw new IllegalStateException();
        }
        this.className = className;
    }

    public QName getQName() {
        return qname;
    }

    public void setQName(QName interfacename) {
        qname = interfacename;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class<?> javaClass) {
        this.javaClass = javaClass;
    }
    
    public Class<?> getCallbackClass() {
        return callbackClass;
    }
    
    public void setCallbackClass(Class<?> callbackClass) {
        this.callbackClass = callbackClass;
    }
    
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return String.valueOf(getName()).hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof JavaInterface) {
            if (getName() != null) {
                return getName().equals(((JavaInterface)obj).getName());
            } else {
                return ((JavaInterface)obj).getName() == null;
            }
        } else {
            return false;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
