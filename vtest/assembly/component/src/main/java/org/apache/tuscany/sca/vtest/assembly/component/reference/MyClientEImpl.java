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
@Service(MyClientE.class)
public class MyClientEImpl implements MyClientE {
	

	@Reference
	protected MyService[] myservices ; 
	
	@Reference
	protected MyService myservice ; 
	
	
	public String callOtherServices() {
		
		StringBuffer buf = new StringBuffer() ;
		String str ;
//		for (MyService s: bService) {
		for (int i = 0 ; i < myservices.length ; i ++) {
			
			buf.append(myservices[i].doMyService()) ;
			buf.append("_") ;
//			System.out.println(s.doMyService());
		}
		buf.append(myservice.doMyService()) ;
		buf.append("_") ;
		
		str = buf.toString() ;
		if (str.length() > 0) {
			str = str.substring(0, str.length() -1) ;
		}
		
		System.out.println(str);
		
		return str ;
//		return bService.doMyService() ;
		
	}
	


}
