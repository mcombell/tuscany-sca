/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.das.rdb.graphbuilder.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.graphbuilder.schema.ESchemaMaker;
import org.apache.tuscany.das.rdb.impl.ResultSetShape;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.Type;


/**
 */
public class GraphBuilderMetadata {

	private Config mappingModel;
	private final Collection resultSets = new ArrayList();
	private boolean debug = false;
	private String typeURI;


	public GraphBuilderMetadata(Collection results, Config model, ResultSetShape shape) throws SQLException {
		this.mappingModel = model;
		if (model != null) {
			this.typeURI = model.getDataObjectModel();		
		}
		
		Iterator i = results.iterator();
		while (i.hasNext()) {
			ResultSet rs = (ResultSet) i.next();
			ResultMetadata resultMetadata = new ResultMetadata(rs, mappingModel, shape);
			resultSets.add(resultMetadata);
		}

		DebugUtil.debugln(getClass(), debug, "Mapping model: " + mappingModel);
	}
	

	public Collection getResultMetadata() {
		return this.resultSets;
	}
	

	public boolean hasMappingModel() {
		return mappingModel == null ? false : true;
	}


	/**
	 * @return
	 */
	
	public Collection getRelationships() {
		if (!hasMappingModel())  {
			DebugUtil.debugln(getClass(), debug, "No relationships to return");
			return Collections.EMPTY_LIST;
		}
		
		return mappingModel.getRelationship();
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

	public Config getMapping() {
		return this.mappingModel;
	}
}
