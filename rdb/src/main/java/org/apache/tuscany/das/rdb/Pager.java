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
package org.apache.tuscany.das.rdb;

import commonj.sdo.DataObject;

/**
 * An iterator-like interface to conveniently move through chunks of data. The 
 * idea is that a Pager works with a read Command. The read command
 * returns a large amount of data and the client wants to work with chunks 
 * of it at a time. So the Pager is created on the command and each call to
 * next returns the next chunk of data. This is done completely disconnected. 
 * No cursor is maintained between calls to #next.
 * 
 * TODO - This is very preliminary. We need to look at this interface 
 * in the context of disonnected scenarios such as a web app. The Pager instance
 * will probably be saved in session so it must be very light and cannot 
 * reference a connection. Also, we probably need to define a factory or add a
 * method to set page size.
 * 
 * 
 */
public interface Pager {

    /**
     * Get the next page of data
     * 
     * @return the next page of data
     */
    DataObject next();

    /**
     * Get the page prior to the last page returned
     * 
     * @return the previous page
     */
    DataObject previous();

    /**
     * Return a specific identified page.
     * 
     * @param page
     *            The number of the page to return
     * @return the indicated page
     */
    DataObject getPage(int page);

}
