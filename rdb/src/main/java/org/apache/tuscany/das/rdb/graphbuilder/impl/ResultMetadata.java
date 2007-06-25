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
import java.util.List;
import java.util.Map;

import org.apache.tuscany.das.rdb.Converter;
import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;
import org.apache.tuscany.das.rdb.impl.ResultSetShape;

import commonj.sdo.Type;

public class ResultMetadata {

    private Map tableToPropertyMap = new HashMap();

    private List typeNames = new ArrayList();

    private List propertyNames = new ArrayList();

    private final ResultSet resultSet;

    private final ResultSetShape resultSetShape;

    private final MappingWrapper configWrapper;

    private Converter[] converters;

    //JIRA-952
    public ResultMetadata(ResultSet rs, MappingWrapper cfgWrapper, ResultSetShape shape) throws SQLException {

        this.resultSet = rs;
        this.configWrapper = cfgWrapper;

        if (shape == null) {
            this.resultSetShape = new ResultSetShape(rs.getMetaData(), configWrapper.getConfig());
        } else {
            this.resultSetShape = shape;
        }

        this.converters = new Converter[resultSetShape.getColumnCount()];

        Map impliedRelationships = new HashMap();
        String schemaName = "";
        for (int i = 1; i <= resultSetShape.getColumnCount(); i++) {
            String tableName = resultSetShape.getTableName(i);
             schemaName = resultSetShape.getSchemaName(i);
            if (( tableName == null ) || ( tableName.equals(""))) {
                throw new RuntimeException("Unable to obtain table information from JDBC. DAS configuration must specify ResultDescriptors");
            }
            String typeName = null;
            
            if(this.configWrapper.getConfig().isDatabaseSchemaNameSupported()){
            	typeName = configWrapper.getTableTypeName(schemaName+"."+tableName);
            }
            else{
            	typeName = configWrapper.getTableTypeName(tableName);	
            }
            String columnName = resultSetShape.getColumnName(i);

            if (columnName.contains("_ID")) {
            	String colName = "";
            	if(this.configWrapper.getConfig().isDatabaseSchemaNameSupported()){
            		colName = schemaName+"."+columnName;
            		impliedRelationships.put(colName, schemaName+"."+tableName);
            	}
            	else{
            		colName = columnName;
            		impliedRelationships.put(colName, tableName);	
            	}
            } else if (columnName.equalsIgnoreCase("ID")) {
                configWrapper.addImpliedPrimaryKey(schemaName, tableName, columnName);
            }

            String propertyName = null;
            
            if(this.configWrapper.getConfig().isDatabaseSchemaNameSupported()){
            	propertyName = configWrapper.getColumnPropertyName(schemaName+"."+tableName, columnName);	
            }
            else{
            	propertyName = configWrapper.getColumnPropertyName(tableName, columnName);
            }
            String converterName = null;
            
            if(this.configWrapper.getConfig().isDatabaseSchemaNameSupported()){
            	converterName = configWrapper.getConverter(schemaName+"."+tableName, resultSetShape.getColumnName(i));
            }
            else{
            	converterName = configWrapper.getConverter(tableName, resultSetShape.getColumnName(i));	
            }

            converters[i - 1] = loadConverter(converterName);

            typeNames.add(typeName);
            propertyNames.add(propertyName);

            Collection properties = (Collection) tableToPropertyMap.get(typeName);
            if (properties == null) {
                properties = new ArrayList();
            }
            properties.add(propertyName);
            tableToPropertyMap.put(typeName, properties);
        }

        Iterator i = impliedRelationships.keySet().iterator();
        while (i.hasNext()) {
            String columnName = (String) i.next();
            String pkTableName = columnName.substring(0, columnName.indexOf("_ID"));
            String fkTableName = (String) impliedRelationships.get(columnName);
            List pkTableProperties = (List) tableToPropertyMap.get(pkTableName);
            if ((pkTableProperties != null) && (pkTableProperties.contains("ID"))) {
                configWrapper.addImpliedRelationship(pkTableName, fkTableName, columnName);
            }
        }
        // Add any tables defined in the model but not included in the ResultSet
        // to the list of propertyNames
        Config model = configWrapper.getConfig();
        if (model != null) {
            Iterator tablesFromModel = model.getTable().iterator();
            while (tablesFromModel.hasNext()) {
                TableWrapper t = new TableWrapper((Table) tablesFromModel.next());
                if (tableToPropertyMap.get(t.getTypeName()) == null) {
                    tableToPropertyMap.put(t.getTypeName(), Collections.EMPTY_LIST);
                }
            }
        }

    }

    private Converter loadConverter(String converterName) {
        if (converterName != null) {

            try {
                Class converterClazz = Class.forName(converterName, true, 
                        Thread.currentThread().getContextClassLoader());
                if (null != converterClazz) {
                    return (Converter) converterClazz.newInstance();
                }

                converterClazz = Class.forName(converterName);
                if (converterClazz != null) {
                    return (Converter) converterClazz.newInstance();
                }
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            }
        }
        return new DefaultConverter();
    }

    public String getColumnPropertyName(int i) {
        return (String) propertyNames.get(i - 1);
    }

    public String getDatabaseColumnName(int i) {
        return resultSetShape.getColumnName(i);
    }

    public String getTableName(String columnName) {
        return (String) typeNames.get(propertyNames.indexOf(columnName));
    }

    public int getTableSize(String tableName) {
        return ((Collection) tableToPropertyMap.get(tableName)).size();
    }

    public Type getDataType(String columnName) {
        return resultSetShape.getColumnType(propertyNames.indexOf(columnName));
    }

    public String getTablePropertyName(int i) {
        return (String) typeNames.get(i - 1);
    }

    public Collection getAllTablePropertyNames() {
        return tableToPropertyMap.keySet();
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
        result.append(this.configWrapper.getConfig());

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
        return tableToPropertyMap.keySet().size();
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

        Table t = configWrapper.getTableByTypeName(getTablePropertyName(i));
        if (t == null) {
            return true;
        }

        // If no Columns have been defined, consider every column to be part of
        // the PK
        if (t.getColumn().isEmpty()) {
            return true;
        }

        Column c = configWrapper.getColumn(t, getDatabaseColumnName(i));

        if (c == null) {
            return false;
        }

        if (c.isPrimaryKey()) {
            return true;
        }

        return false;
    }

    /**
     * @param i
     * @return Type
     */
    public Type getDataType(int i) {
        return resultSetShape.getColumnType(i);
    }

    /**
     * @param tableName
     * @return Collection
     */
    public Collection getPropertyNames(String tableName) {
        return (Collection) tableToPropertyMap.get(tableName);
    }

    public ResultSet getResultSet() {
        return this.resultSet;
    }

    public int getResultSetSize() {
        return resultSetShape.getColumnCount();
    }

    public boolean isRecursive() {
        return configWrapper.hasRecursiveRelationships();
    }

    public Converter getConverter(int i) {
        return converters[i - 1];
    }

}
