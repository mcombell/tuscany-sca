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
import java.util.HashMap;
import java.util.List;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.graphbuilder.impl.GraphBuilderMetadata;
import org.apache.tuscany.das.rdb.graphbuilder.impl.ResultSetProcessor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.EType;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.impl.EDataGraphImpl;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import commonj.sdo.DataObject;
import commonj.sdo.Type;

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

		EDataGraphImpl g = createEDataGraph(gbmd.getSchema());

		ResultSetProcessor rsp = new ResultSetProcessor(g.getRootObject(), gbmd);
		rsp.processResults(getStartRow(), getEndRow());

		g.getChangeSummary().beginLogging();
		return g.getRootObject();
	}

	private EType getSchema() {
		return (EType) schema;
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

	private EDataGraphImpl createEDataGraph(EType type) {

		EClassifier rootClass = type.getEClassifier();
		EDataGraphImpl g = (EDataGraphImpl) SDOFactory.eINSTANCE
				.createEDataGraph();

		// Create a ResourceSet to contain the DataGraph
		ResourceSet resourceSet = SDOUtil.createResourceSet();

		// Create a Resource to hold the schema
		Resource r = resourceSet.createResource(URI.createURI(rootClass
				.getEPackage().getNsURI()));
		InternalEList list = (InternalEList) r.getContents();
		list.addUnique(rootClass.getEPackage());

		// Set the DataGraph's ResourceSet
		g.setResourceSet(resourceSet);

		// Create the root object
		g.createRootObject(SDOUtil.adaptType(rootClass));

		return g;
	}

	private void setMappingModel(Config mapping) {
		mappingModel = new MappingWrapper(mapping);
	}

	public void setMappingModel(InputStream stream) {
		XMLResource resource = new XMLResourceImpl();

		HashMap map = new HashMap();
		ExtendedMetaData metadata = ExtendedMetaData.INSTANCE;
		metadata.putPackage(null, ConfigPackage.eINSTANCE);

		map.put(XMLResource.NO_NAMESPACE_SCHEMA_LOCATION,
				ConfigPackage.eNS_URI);
		map.put(XMLResource.OPTION_EXTENDED_META_DATA, metadata);

		try {
			resource.load(stream, map);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		mappingModel = new MappingWrapper((Config) resource.getContents().get(
				0));
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
