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
package client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import services.ejb.CatalogEJB;
import services.ejb.Vegetable;

public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "ejbd://localhost:4201");
            InitialContext context = new InitialContext(properties);
            
            CatalogEJB catalog = (CatalogEJB)context.lookup("java:VegetablesCatalogEJB");
        
            Vegetable items[] = catalog.get();
            System.out.println(items[0].getName());
        
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

    }

}
