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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.Converter;
import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.ResultSetShape;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.Type;

public class ResultMetadata {

    private HashMap tableToColumnMap = new HashMap();

    private ArrayList typeNames = new ArrayList();

    private ArrayList propertyNames = new ArrayList();

    private final ResultSet resultSet;

    private final ResultSetShape resultSetShape;

    private final MappingWrapper mappingWrapper;

    private Converter[] converters;

    private static boolean debug = false;

    public ResultMetadata(ResultSet rs, Config model, ResultSetShape shape)
            throws SQLException {
        debug("Creating new ResultMetadata with mapping model " + model);
        this.resultSet = rs;
        this.mappingWrapper = new MappingWrapper(model);

        if (shape == null)
            this.resultSetShape = new ResultSetShape(rs.getMetaData());
        else
            this.resultSetShape = shape;

        this.converters = new Converter[resultSetShape.getColumnCount()];

        for (int i = 1; i <= resultSetShape.getColumnCount(); i++) {
            String tableName = resultSetShape.getTableName(i);

            String typeName = mappingWrapper
                    .getTableTypeName(tableName);
            String propertyName = mappingWrapper.getColumnPropertyName(
                    tableName, resultSetShape.getColumnName(i));
            String converterName = mappingWrapper.getConverter(tableName,
                    resultSetShape.getColumnName(i));
            
            converters[i-1] = loadConverter(converterName);
            
            DebugUtil.debugln(getClass(), debug, "Adding table/column: "
                    + typeName + "/" + propertyName);
            typeNames.add(typeName);
            propertyNames.add(propertyName);

            Collection columns = (Collection) tableToColumnMap
                    .get(typeName);
            if (columns == null)
                columns = new ArrayList();
            columns.add(propertyName);
            tableToColumnMap.put(typeName, columns);
        }

        // Add any tables defined in the model but not included in the ResultSet
        // to the list of propertyNames
        if (model != null) {
            Iterator tablesFromModel = model.getTable().iterator();
            while (tablesFromModel.hasNext()) {
                TableWrapper t = new TableWrapper((Table) tablesFromModel
                        .next());
                if (tableToColumnMap.get(t.getTypeName()) == null)
                    tableToColumnMap.put(t.getTypeName(),
                            Collections.EMPTY_LIST);
            }
        }

        if (debug) {
            DebugUtil.debugln(getClass(), debug, toString());
            DebugUtil
                    .debugln(getClass(), debug, this.resultSetShape.toString());
        }

    }

	private Converter loadConverter(String converterName) {
		if (converterName != null) {
		    
		    try {
		        
		        Class converterClazz=  Class.forName(converterName, true, Thread.currentThread().getContextClassLoader());
		        if(null != converterClazz)
		            return (Converter) converterClazz.newInstance();		         
		    } catch( Exception ex){
		    	throw new RuntimeException(ex);
		    }		
		    
	        try{   			        			        
		        Class converterClazz = Class.forName(converterName);
		        if ( converterClazz != null)
		        	return (Converter)converterClazz.newInstance();		      
		    } catch (Exception ex) {
		        throw new RuntimeException(ex);
		    }
		}
		return new DefaultConverter();
	}

    private void debug(Object string) {
        if (debug)
            DebugUtil.debugln(getClass(), debug, string);
    }   

    public String getColumnPropertyName(int i) {
        return (String) propertyNames.get(i - 1);
    }

    public String getDatabaseColumnName(int i) {
        return resultSetShape.getColumnName(i);
    }

    public String getTableName(String columnName) {
        return (String) typeNames.get(propertyNames
                .indexOf(columnName));
    }

    public int getTableSize(String tableName) {
        return ((Collection) tableToColumnMap.get(tableName)).size();
    }

    public Type getDataType(String columnName) {
        return resultSetShape.getColumnType(propertyNames
                .indexOf(columnName));
    }

    public String getTablePropertyName(int i) {
        return (String) typeNames.get(i - 1);
    }

    public Collection getAllTablePropertyNames() {
        return tableToColumnMap.keySet();
    }

    public String toString() {

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (Table Names: ");
        Iterator i = typeNames.iterator();
        while (i.hasNext()) {
            String tableName = (String) i.next();
            result.append(' ');
            result.append(tableName);
            result.append(',');
        }

        result.append(" columnNames: ");

        i = propertyNames.iterator();
        while (i.hasNext()) {
            String columnName = (String) i.next();
            result.append(' ');
            result.append(columnName);
            result.append(',');
        }

        result.append(" mappingModel: ");
        result.append(this.mappingWrapper.getConfig());

        result.append(" resultSet: ");
        result.append(resultSet);

        result.append(" resultSetSize: ");
        result.append(resultSetShape.getColumnCount());
        result.append(')');
        return result.toString();

    }

    /**
     * @return
     */
    public int getNumberOfTables() {
        return tableToColumnMap.keySet().size();
    }

    /**
     * Return whether the column at the given position is part of a primary key.
     * If we don't have this information, we assume every column is a primary
     * key. This results in uniqueness checks using all columns in a table.
     * 
     * @param i
     * @return
     */
    public boolean isPKColumn(int i) {
        if (debug) {
            DebugUtil.debugln(getClass(), debug, "Checking to see if "
                    + getColumnPropertyName(i) + " is a PK column in "
                    + getTablePropertyName(i));
        }
        if (!hasMappingModel()) {
            if (debug)
                DebugUtil
                        .debugln(getClass(), debug,
                                "No mapping model exists, all columns will be considered PK columns");
            return true;
        } else {
            Table t = mappingWrapper.getTableByTypeName(getTablePropertyName(i));
            if (t == null)
                return true;
            Column c = mappingWrapper.getColumn(t, getDatabaseColumnName(i));

            if (c == null)
                return false;

            if (c.isPrimaryKey())
                return true;
        }
        return false;
    }

    public boolean hasMappingModel() {
        return mappingWrapper.getConfig() == null ? false : true;
    }

    /**
     * @param i
     * @return
     */
    public Type getDataType(int i) {
        return resultSetShape.getColumnType(i);
    }

    /**
     * @param tableName
     * @return
     */
    public Collection getColumnNames(String tableName) {
        return (Collection) tableToColumnMap.get(tableName);
    }

    public ResultSet getResultSet() {
        return this.resultSet;
    }

    public int getResultSetSize() {
        return resultSetShape.getColumnCount();
    }

    public boolean isRecursive() {
        return mappingWrapper.hasRecursiveRelationships();
    }

    public Converter getConverter(int i) {
        return converters[i - 1];
    }

}
