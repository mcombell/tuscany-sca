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
package org.apache.tuscany.das.rdb.util;

/**
 * Log4J layout helper for Tuscany DAS
 * @author lresende
 */
public final class LoggerLayout {

    private LoggerLayout() {

    }

    public static String layout() {

        /*
         *  Substitute symbols
         *  %c Logger, %c{2 } last 2 partial names
         *  %C Class name (full agony), %C{2 } last 2 partial names
         *  %d{dd MMM yyyy HH:MM:ss } Date, format see java.text.SimpleDateFormat
         *  %F File name
         *  %l Location (caution: compiler-option-dependently)
         *  %L Line number
         *  %m user-defined message
         *  %M Method name
         *  %p Level
         *  %r Milliseconds since program start
         *  %t Threadname
         *  %x, %X see Doku
         *  %% individual percentage sign
         *  
         *  Caution: %C, %F, %l, %L, %M slow down program run!
         *  Note, %n is newline
         */

        return "[DAS RDB] - %c{1}.%M (%L) : %m %n";
    }

}
