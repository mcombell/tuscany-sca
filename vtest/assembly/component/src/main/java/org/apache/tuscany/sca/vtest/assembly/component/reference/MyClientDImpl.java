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
package org.apache.tuscany.sca.vtest.assembly.component.reference;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;

/**
 * 
 *
 */
@Service(MyClientD.class)
public class MyClientDImpl implements MyClientD {
	

	@Reference
	protected MyService service1 ; 
	
	@Reference
	protected MyService service2 ; 
	
	@Reference
	protected MyService service3 ; 
	
	@Reference
	protected MyService service4 ;
	
	/**
	 * In this case , there is no target service defined for service1 and service2,
	 * so service1 and service2 should be null.
	 */
	public String callOtherService_1() {
		
		String str1 = null ;
		String str2 = null ;
		if (service1 == null) {
			str1 = "MyService" ;
		}
		if (service2 == null) {
			str2 = "MyService" ;
		}
//		String str1 = service1.doMyService() ;
//		String str2 = service2.doMyService() ;
		
		return str1 + "_" + str2 ;
	}
	
	public String callOtherService_2() {
				
		String str1 = service3.doMyService() ;
		String str2 = service4.doMyService() ;
		
		return str1 + "_" + str2 ;
	}


}
