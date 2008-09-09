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
package org.apache.tuscany.sca.implementation.java.injection;


/**
 * Root unchecked exception for the injection package
 *
 * @version $Rev: 537424 $ $Date: 2007-05-12 06:47:18 -0700 (Sat, 12 May 2007) $
 */
public abstract class InjectionRuntimeException extends RuntimeException {

    public InjectionRuntimeException() {
        super();
    }

    public InjectionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectionRuntimeException(String message) {
        super(message);
    }

    public InjectionRuntimeException(Throwable cause) {
        super(cause);
    }

}