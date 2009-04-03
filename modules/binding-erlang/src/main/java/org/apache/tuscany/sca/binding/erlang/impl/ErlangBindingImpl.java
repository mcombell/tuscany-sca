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

package org.apache.tuscany.sca.binding.erlang.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.sca.binding.erlang.ErlangBinding;
import org.apache.tuscany.sca.policy.Intent;
import org.apache.tuscany.sca.policy.IntentAttachPointType;
import org.apache.tuscany.sca.policy.PolicySet;
import org.apache.tuscany.sca.policy.PolicySetAttachPoint;

/**
 * @version $Rev: $ $Date: $
 */
public class ErlangBindingImpl implements ErlangBinding, PolicySetAttachPoint {

	private String node;
	private String module;
	private boolean mbox;
	private String cookie;
	private int serviceThreadPool = DEFAULT_THREAD_POOL;
	
	private boolean defaultTimeout = true;
	private boolean defaultThreads = true;

	private List<Intent> requiredIntents = new ArrayList<Intent>();
	private List<PolicySet> policySets = new ArrayList<PolicySet>();
	private IntentAttachPointType intentAttachPointType;
	private List<PolicySet> applicablePolicySets = new ArrayList<PolicySet>();
	private long timeout = NO_TIMEOUT;

	public String getNode() {
		return node;
	}

	public void setNode(String nodeName) {
		this.node = nodeName;
	}

	public String getName() {
		return null;
	}

	public String getURI() {
		return null;
	}

	public void setName(String arg0) {
	}

	public void setURI(String arg0) {
	}

	public boolean isUnresolved() {
		return false;
	}

	public void setUnresolved(boolean arg0) {
	}

	public List<PolicySet> getApplicablePolicySets() {
		return applicablePolicySets;
	}

	public List<PolicySet> getPolicySets() {
		return policySets;
	}

	public List<Intent> getRequiredIntents() {
		return requiredIntents;
	}

	public IntentAttachPointType getType() {
		return intentAttachPointType;
	}

	public void setType(IntentAttachPointType intentAttachPointType) {
		this.intentAttachPointType = intentAttachPointType;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getModule() {
		return module;
	}

	public boolean isMbox() {
		return mbox;
	}

	public void setMbox(boolean mbox) {
		this.mbox = mbox;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		// NOTE: not setting timeout or setting it to 0 will cause no timeout
		this.timeout = timeout;
		if (timeout != 0) {
			defaultTimeout = false;
		}
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public boolean hasTimeout() {
		return timeout != ErlangBinding.NO_TIMEOUT;
	}

	public boolean hasCookie() {
		return cookie != null && cookie.length() > 0;
	}

	public int getServiceThreadPool() {
		return serviceThreadPool;
	}

	public void setServiceThreadPool(int threads) {
		this.serviceThreadPool = threads;
		this.defaultThreads = false;
	}

	public boolean isDefaultServiceThreadPool() {
		return defaultThreads;
	}

	public boolean isDefaultTimeout() {
		return defaultTimeout;
	}

}
