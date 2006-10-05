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
package org.apache.tuscany.das.rdb.impl;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.Pager;

import commonj.sdo.DataObject;

public class PagerImpl implements Pager {

    private final ReadCommandImpl command;

    private final int size;

    private int idx = 1;

    public PagerImpl(Command command, int size) {
        this.command = (ReadCommandImpl) command;
        this.command.enablePaging();
        this.size = size;
    }

    public DataObject next() {
        int start = idx;
        int end = idx + size;
        idx += size;
        command.setStartRow(start);
        command.setEndRow(end);
        return command.executeQuery();
    }

    public DataObject getPage(int page) {
        int end = (page * size) + 1;
        int start = end - size;
        idx = end;
        command.setStartRow(start);
        command.setEndRow(end);
        return command.executeQuery();
    }

    public DataObject previous() {
        int start = idx - (2 * size);
        if (start < 1) {
            start = 1;
        }
        int end = start + size;
        idx = end;
        command.setStartRow(start);
        command.setEndRow(end);
        return command.executeQuery();
    }
}
