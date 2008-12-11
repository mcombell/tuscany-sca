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

import org.osoa.sca.annotations.Callback;
import org.osoa.sca.annotations.Scope;
import org.osoa.sca.annotations.Service;

/**
 * 
 *
 */
@Service(MyService.class)
public class MyServiceImpl implements MyService{

//	@Context
//    protected RequestContext requestContext;	
	
//	@Callback MyServiceCallBack callback ; //we can't use this !
	@Callback protected MyServiceCallBack callback ; //this callback is ok
	
	public void dosomething() {
		
		try {
			System.out.println("Recved request from client.");
//			Thread.sleep(3000) ;
//			if (requestContext != null) {
//				System.out.println("requestContext is OK!");
//			}
//			 = requestContext.getCallback() ;
			
					
			if (callback != null) {
				System.out.println("callback is OK.");
				callback.receiveResult("Message from Service side") ;
			} else {
				System.out.println("callback is NULL!");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		
		
	}
	
	
}
