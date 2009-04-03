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

package org.apache.tuscany.sca.binding.erlang.testing;

import org.osoa.sca.annotations.Reference;

/**
 * @version $Rev$ $Date$
 */
public class ReferenceTestComponentImpl implements ReferenceTestComponent {

	private MboxInterface mboxReference;
	private MboxInterface timeoutMboxReference;
	private MboxInterface cookieMboxReference;
	private ServiceInterface moduleReference;
	private ServiceInterface cookieModuleReference;
	private ServiceInterface invalidCookieModuleReference;
	private ServiceInterface timeoutModuleReference;
	private ServiceInterface clonedModuleReference;

	@Reference
	public void setMboxReference(MboxInterface mboxReference) {
		this.mboxReference = mboxReference;
	}
	
	@Reference
	public void setTimeoutMboxReference(MboxInterface timeoutMboxReference) {
		this.timeoutMboxReference = timeoutMboxReference;
	}
	
	@Reference
	public void setCookieMboxReference(MboxInterface cookieMboxReference) {
		this.cookieMboxReference = cookieMboxReference;
	}

	@Reference
	public void setModuleReference(ServiceInterface timeoutModuleReference) {
		this.moduleReference = timeoutModuleReference;
	}
	
	@Reference
	public void setCookieModuleReference(ServiceInterface cookieModuleReference) {
		this.cookieModuleReference = cookieModuleReference;
	}
	
	@Reference
	public void setInvalidCookieModuleReference(ServiceInterface invalidCookieModuleReference) {
		this.invalidCookieModuleReference = invalidCookieModuleReference;
	}
	
	@Reference
	public void setTimeoutModuleReference(ServiceInterface timeoutModuleReference) {
		this.timeoutModuleReference = timeoutModuleReference;
	}

	@Reference
	public void setClonedModuleReference(ServiceInterface clonedModuleReference) {
		this.clonedModuleReference = clonedModuleReference;
	}

	public MboxInterface getMboxReference() {
		return mboxReference;
	}

	public MboxInterface getTimeoutMboxReference() {
		return timeoutMboxReference;
	}
	
	public MboxInterface getCookieMboxReference() {
		return cookieMboxReference;
	}
	
	public ServiceInterface getModuleReference() {
		return moduleReference;
	}

	public ServiceInterface getCookieModuleReference() {
		return cookieModuleReference;
	}

	public ServiceInterface getInvalidCookieModuleReference() {
		return invalidCookieModuleReference;
	}
	
	public ServiceInterface getTimeoutModuleReference() {
		return timeoutModuleReference;
	}

	public ServiceInterface getClonedModuleReference() {
		return clonedModuleReference;
	}

}
