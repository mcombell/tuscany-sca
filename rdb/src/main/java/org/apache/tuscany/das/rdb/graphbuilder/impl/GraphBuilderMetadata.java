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
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.graphbuilder.schema.ESchemaMaker;
import org.apache.tuscany.das.rdb.impl.ResultSetShape;

import commonj.sdo.Type;


/**
 */
public class GraphBuilderMetadata {

	private MappingWrapper configWrapper;
	private final Collection resultSets = new ArrayList();
	private String typeURI;


	public GraphBuilderMetadata(Collection results, Config model, ResultSetShape shape) throws SQLException {
		this.configWrapper = new MappingWrapper(model);
		if (model != null) {
			this.typeURI = model.getDataObjectModel();		
		}
		
		Iterator i = results.iterator();
		while (i.hasNext()) {
			ResultSet rs = (ResultSet) i.next();
			ResultMetadata resultMetadata = new ResultMetadata(rs, configWrapper, shape);
			resultSets.add(resultMetadata);
		}

	}
	

	public Collection getResultMetadata() {
		return this.resultSets;
	}

	/**
	 * @return
	 */
	
	public Collection getRelationships() {
		return configWrapper.getConfig().getRelationship();
	}


	/**
	 * @return
	 */
	public Type getSchema() {
		ESchemaMaker schemaMaker = new ESchemaMaker(this);
		if ( this.typeURI == null ) {		
			return schemaMaker.createTypes();
		} else {
			return schemaMaker.createTypes(this.typeURI);
		}
	}

	public MappingWrapper getConfigWrapper() {
		return this.configWrapper;
	}
}
