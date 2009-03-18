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
package org.apache.tuscany.sca.contribution.jee;

/**
 * @version $Rev$ $Date$
 */
public class EjbReferenceInfo {
    public enum EjbType {STATELESS, STATEFUL, SESSION_UNKNOWN, UNKNOWN};
    public enum RefType {LOCAL, REMOTE, UNKNOWN};
    public String referenceName;
    public EjbType ejbType;
    public RefType referenceType;
    public Class<?> businessInterface;
    public String ejbLink;
    public String mappedName;
}
