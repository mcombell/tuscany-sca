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
package org.apache.tuscany.sca.store;

/**
 * Thrown when an error occurs writing to persistent storage
 *
 * @version $Rev: 537206 $ $Date: 2007-05-11 08:14:19 -0700 (Fri, 11 May 2007) $
 */
public class StoreWriteException extends StoreException {
    private static final long serialVersionUID = 5539070473942048555L;

    public StoreWriteException() {
        super();
    }

    public StoreWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreWriteException(String message) {
        super(message);
    }

    public StoreWriteException(Throwable cause) {
        super(cause);
    }

}