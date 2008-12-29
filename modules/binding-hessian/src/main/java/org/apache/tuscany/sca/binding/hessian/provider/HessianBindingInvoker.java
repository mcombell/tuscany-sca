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
package org.apache.tuscany.sca.binding.hessian.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.tuscany.sca.binding.hessian.impl.HessianBinding;
import org.apache.tuscany.sca.interfacedef.Interface;
import org.apache.tuscany.sca.interfacedef.java.JavaOperation;
import org.apache.tuscany.sca.invocation.Invoker;
import org.apache.tuscany.sca.invocation.Message;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessianBindingInvoker implements Invoker {

    private String serviceURL;
    private Interface homeImpl;

    HessianBindingInvoker(Interface homeImpl, HessianBinding binding) {
        this.serviceURL = binding.getURI();
        this.homeImpl = homeImpl;
    }

    public Message invoke(Message msg) {
        try {
            Method method = ((JavaOperation)msg.getOperation()).getJavaMethod();
            Object[] args = msg.getBody();

            HessianProxyFactory proxyFactory = new HessianProxyFactory();
            Object service = proxyFactory.create(loadClass(homeImpl.toString()), serviceURL);
            Object result = null;
            result = method.invoke(service, args);
            msg.setBody(result);
        } catch (Throwable e) {
            e.printStackTrace();
            if (e instanceof InvocationTargetException)
                e = ((InvocationTargetException)e).getTargetException();
            msg.setFaultBody(e);
        }
        return msg;
    }

    private Class loadClass(String className) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            if (loader != null)
                return Class.forName(className, false, loader);
            else
                return Class.forName(className);
        } catch (Exception e) {

        }
        return null;
    }
}
