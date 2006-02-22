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
package org.apache.tuscany.das.rdb.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.graphbuilder.impl.GraphBuilderMetadata;
import org.apache.tuscany.das.rdb.graphbuilder.impl.ResultSetProcessor;
import org.apache.tuscany.sdo.util.DataObjectUtil;
import org.apache.tuscany.sdo.util.SDOUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.XMLHelper;

public class ReadCommandImpl extends CommandImpl {

	private Type schema;

	private int startRow = 1;

	private int endRow = Integer.MAX_VALUE;

	public ReadCommandImpl(String sqlString) {
		super(sqlString);
	}

	public ReadCommandImpl(String sqlString, InputStream modelStream) {
		this(sqlString);
		if (modelStream != null)
			setMappingModel(modelStream);
	}

	public ReadCommandImpl(String sqlString, Config mapping) {
		this(sqlString);
		if (mapping != null)
			setMappingModel(mapping);
	}

	public void execute() {
		throw new UnsupportedOperationException();
	}

	public DataObject executeQuery() {

		if (statement.getConnection() == null)
			throw new RuntimeException(
					"A DASConnection object must be specified before executing the query.");

		boolean success = false;
		try {
			ResultSet rs = statement.executeQuery(parameters);
			success = true;
			return buildGraph(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (success)
				statement.getConnection().cleanUp();
			else
				statement.getConnection().errorCleanUp();
		}
	}

	protected DataObject buildGraph(ResultSet result) throws SQLException {
		
		List results = new ArrayList();
		results.add(result);

		// Before we use the mappingModel, do some checking/updating. If
		// inferrable information
		// isn't specified, add it in.


		GraphBuilderMetadata gbmd = new GraphBuilderMetadata(results,
				getSchema(), mappingModel.getConfig(), resultSetShape);

		DataGraph g = createEDataGraph(gbmd.getSchema());
		ChangeSummary summary = g.getChangeSummary();

		ResultSetProcessor rsp = new ResultSetProcessor(g.getRootObject(), gbmd);
		rsp.processResults(getStartRow(), getEndRow());

		summary.beginLogging();
	
		return g.getRootObject();
	}

	private Type getSchema() {
		return (Type) schema;
	}

	protected int getStartRow() {
		return startRow;
	}

	protected int getEndRow() {
		return endRow;
	}

	protected void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	protected void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	private DataGraph createEDataGraph(Type type) {

		DataGraph g = SDOUtil.createDataGraph();

		// Create a ResourceSet to contain the DataGraph
		ResourceSet resourceSet = DataObjectUtil.createResourceSet();

		// Create a Resource to hold the schema
        
		Resource r = resourceSet.createResource(URI.createURI(type.getURI()));
		r.getContents().add(g);

		// Create the root object
		g.createRootObject(type);

		return g;
	}

	private void setMappingModel(Config mapping) {
		mappingModel = new MappingWrapper(mapping);
	}

	public void setMappingModel(InputStream stream) {
		
		 XMLHelper helper = XMLHelper.INSTANCE;
		  
//		 ExtendedMetaData metadata = ExtendedMetaData.INSTANCE;
//		 metadata.putPackage(null, ConfigPackageImpl.eINSTANCE);

		 try {
			 Config config = (Config) helper.load(stream).getRootObject();     
			 mappingModel = new MappingWrapper(config);
	     } catch (IOException e) {
	    	 throw new RuntimeException(e);
	     }
        
	
	     if (mappingModel.getConfig().getConnectionProperties() != null)
			setConnection(mappingModel.getConfig().getConnectionProperties());

	}

	public void setConnection(ConnectionProperties c) {
		try {
			Connection connection = null;
			Class.forName(c.getDriverClassName());
			if (c.getDriverUserName() == null)
				connection = DriverManager.getConnection(c.getDriverURL());
			else
				connection = DriverManager.getConnection(c.getDriverURL(), c
						.getDriverUserName(), c.getDriverPassword());
			connection.setAutoCommit(false);
			setConnection(connection);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void setDataObjectModel(Type schema) {
		this.schema = schema;
	}

	protected void enablePaging() {
		statement.enablePaging();
	}

}
