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

import java.io.UnsupportedEncodingException;

import org.apache.tuscany.das.rdb.Converter;

public class StringObfuscationConverter implements Converter {

    public StringObfuscationConverter() {
        super();
    }

    public Object getPropertyValue(Object columnData) {
        return toRot13((String) columnData);
    }

    public Object getColumnValue(Object propertyData) {
        return toRot13((String) propertyData);
    }

    // Utilities

    // A simple, reversible, obfuscation algorithm using a ROT13 implementation
    private String toRot13(String original) {

        int abyte = 0;
        byte[] buffer = {};
        try {
            buffer = original.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }

        for (int i = 0; i < buffer.length; i++) {
            abyte = buffer[i];
            int cap = abyte & 32;
            abyte &= ~cap;
            abyte = ((abyte >= 'A') && (abyte <= 'Z') ? ((abyte - 'A' + 13) % 26 + 'A') : abyte) | cap;
            buffer[i] = (byte) abyte;
        }
        try {
            return new String(buffer, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }
}
