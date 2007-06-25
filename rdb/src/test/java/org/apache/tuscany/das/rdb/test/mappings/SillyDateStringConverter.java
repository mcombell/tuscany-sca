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
package org.apache.tuscany.das.rdb.test.mappings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tuscany.das.rdb.Converter;

public class SillyDateStringConverter implements Converter {

    private static DateFormat myformat = new SimpleDateFormat("yyyy.MM.dd");

    private static Date kbday;

    private static Date tbday;

    public SillyDateStringConverter() {
        super();
    }

    static {
        try {
            kbday = myformat.parse("1957.09.27");
            tbday = myformat.parse("1966.12.20");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getPropertyValue(Object columnData) {

        if (columnData.equals("Williams")) {
            return kbday;
        }

        if (columnData.equals("Pavick")) {
            return tbday;
        }

        throw new IllegalArgumentException();

    }

    public Object getColumnValue(Object propertyData) {

        if (propertyData.equals(kbday)) {
            return "Williams";
        }

        if (propertyData.equals(tbday)) {
            return "Pavick";
        }

        throw new IllegalArgumentException();

    }

}
