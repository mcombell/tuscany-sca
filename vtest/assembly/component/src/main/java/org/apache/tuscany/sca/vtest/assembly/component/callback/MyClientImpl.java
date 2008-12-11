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
package org.apache.tuscany.sca.vtest.assembly.component.callback;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.osoa.sca.annotations.Service;

/**
 * 
 *
 */
@Service(MyClient.class)
public class MyClientImpl implements MyClient, MyServiceCallBack{

	@Reference
	private MyService myService ;
	
	String returnMessage ;
	
	private static Object monitor = new Object();
	
	
	public void callService() {
		
		if (myService == null) {
			System.out.println("myService is null.");
//			throw new Exception() ;
		} else {
			System.out.println("myService is not null.");
			myService.dosomething() ;
		}
		
		
		int count = 0 ;
		synchronized (monitor) {
            while (returnMessage == null && count++ < 5) {
                try {
                    monitor.wait(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
	
	public void receiveResult(String result) {
		this.returnMessage = result ;
		System.out.println("returnMessage = " + returnMessage);
	}
	
	
}
