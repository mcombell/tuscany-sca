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

/* Import necessary log4j API classes */
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Helper class for logging using external log utility : log4j
 * This will use log4j and to configure it, please add/update log4j.properties
 * 
 * @author lresende
 */

public final class LoggerFactory {
    public static final LoggerFactory INSTANCE = new LoggerFactory();

    private static Layout defaultLayout;

    private static Appender defaultAppender;

    static {
        synchronized (LoggerFactory.class) {
            defaultLayout = new PatternLayout(LoggerLayout.layout());
            defaultAppender = new ConsoleAppender(defaultLayout);
        }
    }

    private LoggerFactory() {

    }

    public Logger getLogger(Class loggingClass) {
        Logger logger = Logger.getLogger(loggingClass);
        logger.setLevel(Level.OFF);
        logger.addAppender(defaultAppender);

        return logger;
    }

    public Logger getLogger(Class loggingClass, Level logLevel) {
        Logger logger = Logger.getLogger(loggingClass);
        logger.setLevel(logLevel);
        logger.addAppender(defaultAppender);

        return logger;
    }

    public static void main(String[] args) {

        Logger log = LoggerFactory.INSTANCE.getLogger(LoggerFactory.class);
        if (log.isDebugEnabled()) {
            log.debug("something");
        }

    }
}
