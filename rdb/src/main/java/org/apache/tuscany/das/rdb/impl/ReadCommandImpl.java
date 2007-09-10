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
package org.apache.tuscany.das.rdb.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.graphbuilder.impl.GraphBuilderMetadata;
import org.apache.tuscany.das.rdb.graphbuilder.impl.ResultSetProcessor;
import org.apache.tuscany.sdo.api.SDOUtil;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;

public class ReadCommandImpl extends CommandImpl {

    private int startRow = 1;

    private int endRow = Integer.MAX_VALUE;   

    private List resultDescriptors = null;
    
    public ReadCommandImpl(org.apache.tuscany.das.rdb.config.Command command, MappingWrapper mapping, List resultDescriptor) {
        super(command);
        this.configWrapper = mapping;
        
        if (resultDescriptor != null && !resultDescriptor.isEmpty()) {
            this.resultSetShape = new ResultSetShape(resultDescriptor, configWrapper.getConfig());//JIRA-952
        }
    }
    
    public ReadCommandImpl(String sqlString, MappingWrapper mapping, List resultDescriptor) {
        super(sqlString);
        this.configWrapper = mapping;
        
        if (resultDescriptor != null && !resultDescriptor.isEmpty()) {
            this.resultSetShape = new ResultSetShape(resultDescriptor, configWrapper.getConfig());//JIRA-952
        }
    }

    private void refreshResultSetShape(){
		//sort descriptor and use in ResultSetShape
        sortResultDescriptors();    		
		this.resultSetShape = new ResultSetShape(this.resultDescriptors, configWrapper.getConfig());	
    }
    
	private void sortResultDescriptors(){
		if(this.resultDescriptors == null) {
			return;
		}
		
		if( this.resultDescriptors.size()==0) {
			return;
		}
		
		//when any index is found not set, do not sort	
		for(Iterator it =  this.resultDescriptors.iterator() ; it.hasNext();){
			ResultDescriptor resultDescriptor = (ResultDescriptor) it.next();
			if(resultDescriptor.getColumnIndex() <= -1){
				return;
			}
		}

		//now is time to sort
		Object[] resultDescAry = this.resultDescriptors.toArray();
		for(int i=0; i<resultDescAry.length; i++){
			for(int j=i+1; j<resultDescAry.length; j++){
				if( ((ResultDescriptor)resultDescAry[j]).getColumnIndex()
						< ((ResultDescriptor)resultDescAry[i]).getColumnIndex()){
					ResultDescriptor tmpResDesc = (ResultDescriptor)resultDescAry[i];
					resultDescAry[i] = resultDescAry[j];
					resultDescAry[j] = tmpResDesc;
				}
				
				if( ((ResultDescriptor)resultDescAry[j]).getColumnIndex()
						== ((ResultDescriptor)resultDescAry[i]).getColumnIndex()){
					throw new RuntimeException("Two columns in Result Descriptor can not have same index");				
				}
			}
		}
		
		this.resultDescriptors.clear();
		for(int i=0; i<resultDescAry.length; i++){
			this.resultDescriptors.add(resultDescAry[i]);
		}
		
		return;
	}
	
	private ResultDescriptor deepCopyResultDescriptor(ResultDescriptor inObj){
		ResultDescriptorImpl outObj = new ResultDescriptorImpl();
		outObj.setColumnIndex(inObj.getColumnIndex());
		outObj.setColumnName(inObj.getColumnName());
		outObj.setColumnType(inObj.getColumnType());
		outObj.setTableName(inObj.getTableName());
		outObj.setSchemaName(inObj.getSchemaName());	
		return outObj;
	}
	
	private List deepCopyResultDescriptors(List resultDescriptors){
		if(resultDescriptors == null || resultDescriptors.size() == 0)
			return null;
		
		ArrayList copyList = new ArrayList();
		
		for(Iterator it =  resultDescriptors.iterator() ; it.hasNext();){
			copyList.add( deepCopyResultDescriptor( (ResultDescriptorImpl) it.next()));
		}
		return copyList;
	}
	
    /**
     * When any columnIndex == -ve, sorting will not happen in ResultShapeSorter (old way)
     * When null is passed, set this.resultSetShape to null, this will later trigger, dbms metadata
     * based shaping of result
     */
    public void setResultDescriptors(List resultDescriptors){    	
    	this.resultDescriptors = deepCopyResultDescriptors(resultDescriptors);
    	if(this.resultDescriptors == null || this.resultDescriptors.size()==0){
    		this.resultSetShape = null;
    	}
    	else{
    		//below will go away with List<> JDK5
    		for(Iterator it =  this.resultDescriptors.iterator() ; it.hasNext();){

    			if(!(it.next() instanceof ResultDescriptor)){
    				throw new RuntimeException("Elements in List not of type ResultDescriptor!");
    			}

    		}
    		refreshResultSetShape();
    	}
    }
      
    public List getResultDescriptors(){
    	return this.resultDescriptors;
    }
    
    public void addResultDescriptor(ResultDescriptor resultDescriptor){
    	//if >= 0 columnIndex, add/replace for given index 
    	//if < 0 columnIndex, add at end of current list
    	if(resultDescriptor == null) {
    		return;
    	}
    	
		if(this.resultDescriptors == null){
			this.resultDescriptors = new ArrayList();
		}
		
    	if(resultDescriptor.getColumnIndex() <0){
    		this.resultDescriptors.add(deepCopyResultDescriptor(resultDescriptor));//dont care about columnIndex,add at end,  old way
    	}
    	else{
    		ResultDescriptor existing = getResultDescriptor(resultDescriptor.getColumnIndex());
    		if(existing != null){
    			removeResultDescriptor(resultDescriptor.getColumnIndex());
    		}
    		this.resultDescriptors.add(deepCopyResultDescriptor(resultDescriptor));//add at end, sorting will happen below
    	}
    	
    	refreshResultSetShape();
    }
    

    public ResultDescriptor removeResultDescriptor(int columnIndex){
    	//if < 0 index return null
    	//if >=0 index and available at given index, remove and return same
    	//if >=0 index and not available at given index, return null
    	ResultDescriptor existing = null;
    	if(columnIndex >=0 && ((existing = getResultDescriptor(columnIndex)) != null) ){
    		this.resultDescriptors.remove(existing);    	
    		refreshResultSetShape();			
    		return existing;
    	}    	
    	return null;
    }
    
    public ResultDescriptor removeResultDescriptor(ResultDescriptor resultDescriptor){
    	//remove and return only if matched for index, name, type, table name, schema name
    	//else return null
    	if(resultDescriptor != null){
    		ResultDescriptor existing = getResultDescriptor(resultDescriptor.getColumnIndex());
    		if(existing != null &&
    		   existing.getColumnName().equals(resultDescriptor.getColumnName()) &&
    		   existing.getColumnType().equals(resultDescriptor.getColumnType()) &&
    		   existing.getTableName().equals(resultDescriptor.getTableName()) ) {
    		   if(this.configWrapper.getConfig().isDatabaseSchemaNameSupported()){//multi schema support
    			   if(resultDescriptor.getSchemaName() != null && existing.getSchemaName() != null
    				&& resultDescriptor.getSchemaName().equals(existing.getSchemaName())){
    				   this.resultDescriptors.remove(existing);    				   
    				   refreshResultSetShape();    					
    				   return existing;
    			   }
    			   return null;
    		   }
    		   else{
    			   this.resultDescriptors.remove(existing);    			   
    			   refreshResultSetShape();    				
				   return existing;
    		   }    			
    		}    				
    	}
    	return null;
    }
    
    public ResultDescriptor getResultDescriptor(int columnIndex){
    	//if <0 index return null
    	//if >=0 index and available at given index,  return same
    	//if >=0 index and not available at given index, return null
    	if(columnIndex >=0 && this.resultDescriptors != null){
			
			for(Iterator it =  this.resultDescriptors.iterator() ; it.hasNext();){
				ResultDescriptor rs = (ResultDescriptor) it.next();

				if( rs.getColumnIndex() == columnIndex){
					return rs;
				}

			}    		
    	}

    	return null;
    }
    
    //Utility method
    public void printResultDescriptors(OutputStream ostrm) throws IOException{
    	if(this.resultDescriptors != null && this.resultDescriptors.size() != 0){

		for(Iterator it =  this.resultDescriptors.iterator() ; it.hasNext();){
    			ostrm.write( it.next().toString().getBytes() );
    			ostrm.write('\n');

    		}
    		ostrm.flush();

    	}
    }
    
    public void execute() {
        throw new UnsupportedOperationException();
    }

    public DataObject executeQuery() {

        if (statement.getConnection() == null) {
            throw new RuntimeException("A DASConnection object must be specified before executing the query.");
        }

        boolean success = false;
        try {
            List results = statement.executeQuery(parameters);
            success = true;
            return buildGraph(results);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (success) {
                statement.getConnection().cleanUp();
            } else {
                statement.getConnection().errorCleanUp();
            }
        }
    }

    protected DataObject buildGraph(List results) throws SQLException {      

        // Before we use the mappingModel, do some checking/updating. If
        // inferrable information
        // isn't specified, add it in.

        GraphBuilderMetadata gbmd = new GraphBuilderMetadata(results, configWrapper.getConfig(),
                resultSetShape);

        // Create the DataGraph
        DataGraph g = SDOUtil.createDataGraph();

        // Create the root object       
        g.createRootObject(gbmd.getRootType());

        SDOUtil.registerDataGraphTypes(g, gbmd.getDefinedTypes());
        
        ChangeSummary summary = g.getChangeSummary();

        ResultSetProcessor rsp = new ResultSetProcessor(g.getRootObject(), gbmd);
        rsp.processResults(getStartRow(), getEndRow());

        summary.beginLogging();

        return g.getRootObject();
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


    protected void enablePaging() {
        statement.enablePaging();
    }

}
