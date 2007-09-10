package org.apache.tuscany.das.rdb.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.config.Parameters;
import org.apache.tuscany.das.rdb.config.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.config.impl.ParametersImpl;

import commonj.sdo.Type;

public class ParametersExtendedImpl extends ParametersImpl{
	private final Logger logger = Logger.getLogger(ParametersExtendedImpl.class);
    private List inParams = new ArrayList();

    private List outParams = new ArrayList();
    
    public ParametersExtendedImpl(){
    	
    }
    
    public ParametersExtendedImpl(List paramsList) {
    	if(this.parameter == null){
    		this.parameter = new ArrayList();
    	}
    	if(paramsList != null){
	    	for(int i=0; i<paramsList.size(); i++){
				ParameterImpl paramImpl = (ParameterImpl)paramsList.get(i);
				ParameterExtendedImpl paramExtImpl = new ParameterExtendedImpl();
				paramExtImpl.setColumnType(paramImpl.getColumnType());
				paramExtImpl.setIndex(paramImpl.getIndex());
				paramExtImpl.setName(paramImpl.getName());
				paramExtImpl.setDirection(paramImpl.getDirection());
				this.parameter.add(paramExtImpl);
			}
    	}
    }
    
    public ParametersExtendedImpl(Parameters params) {
    	this(params.getParameter());
    }
    
    public List getOutParameters() {
        return outParams;
    }

    public List getInParameters() {
        return inParams;
    }
    
    public ParameterExtendedImpl getParameter(int index) {
    	if(this.getParameter() == null || this.getParameter().isEmpty())
    		return null;
    	
		Iterator itr = this.getParameter().iterator();
		while(itr.hasNext()){
			ParameterExtendedImpl curParam = (ParameterExtendedImpl)itr.next();
			if(curParam.getIndex() == index){
				return curParam;
			}
		}
		return null;
    }
    
    public ParameterExtendedImpl getParameter(int index, String direction) {
    	if(direction.equals(ParameterExtendedImpl.IN)) {
    		Iterator itr = this.inParams.iterator();
    		while(itr.hasNext()){
    			ParameterExtendedImpl curParam = (ParameterExtendedImpl)itr.next();
    			if(curParam.getIndex() == index){
    				return curParam;
    			}
    		}
    	}    		
    	else {
    		Iterator itr = this.outParams.iterator();
    		while(itr.hasNext()){
    			ParameterExtendedImpl curParam = (ParameterExtendedImpl)itr.next();
    			if(curParam.getIndex() == index){
    				return curParam;
    			}
    		}    		
    	}
    	return null;
    }
    
    private ParameterExtendedImpl getNamedParameter(List params, String name){
    	if(params == null)
    		return null;
    	for(int i=0; i<params.size(); i++){
    		if( ((ParameterExtendedImpl)params.get(i)).getName().equals(name)){
    			return (ParameterExtendedImpl)params.get(i);
    		}
    	}
    	return null;    	
    }
    
    public ParameterExtendedImpl getParameter(String name) {
    	return getNamedParameter(this.getParameter(), name);
    }
    
    public ParameterExtendedImpl getParameter(String name, String direction) {
    	if(direction.equals(ParameterExtendedImpl.IN))
    		return getNamedParameter(this.inParams, name);
    	else
    		return getNamedParameter(this.outParams, name);
    }
    
    public ParameterExtendedImpl findOrCreateParameterWithIndex(int index, String direction, Type sdoType) {
    	if(this.parameter == null){
    		this.parameter = new ArrayList();
    	}
    	Iterator i = this.parameter.iterator();
        while (i.hasNext()) {
            ParameterExtendedImpl param = (ParameterExtendedImpl) i.next();

            if (param.getIndex() == index) {
                return param;
            }
        }
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Creating new parameter with index " + index);
        }

        ParameterExtendedImpl newParam = new ParameterExtendedImpl();
        newParam.setIndex(index);
        newParam.setDirection(direction);
        newParam.setType(sdoType);
        newParam.setColumnType(SDODataTypeHelper.columnTypeForSDOType(sdoType));
        this.getParameter().add(newParam);
        if(!direction.equals(ParameterExtendedImpl.IN)){
        	this.getOutParameters().add(newParam);
        }
        else{
        	this.getInParameters().add(newParam);
        }
        return newParam;    	
    }

    /**maintain compatibility with parameters="name1 name2 " from config
     * 
     * @param parameters
     */
    public static ArrayList getParameters(String parameters) {
        StringTokenizer tokenizer = new StringTokenizer(parameters);
        ArrayList paramExtList = new ArrayList();
        for (int idx = 1; tokenizer.hasMoreTokens(); idx++) {
            ParameterExtendedImpl p = new ParameterExtendedImpl();
            p.setName(tokenizer.nextToken());
            p.setIndex(idx);
            p.setDirection(ParameterExtendedImpl.IN);
            paramExtList.add(p);            
        }
        return paramExtList;
    }
    
    public void addParameters(List paramExtList){
    	for(int i=0; i<paramExtList.size(); i++){
    		ParameterExtendedImpl curParam = (ParameterExtendedImpl)paramExtList.get(i);
            this.getParameter().add(curParam);
            if(curParam.getDirection().equals(ParameterExtendedImpl.IN))
            	this.getInParameters().add(paramExtList.get(i));
            else
            	this.getOutParameters().add(paramExtList.get(i));
    	}
    }

}
