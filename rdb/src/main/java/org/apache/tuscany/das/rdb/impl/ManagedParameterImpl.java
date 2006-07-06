package org.apache.tuscany.das.rdb.impl;

public class ManagedParameterImpl extends ParameterImpl {
	
	public void setValue(Object oldValue) {
		this.value = updateValue(oldValue);
	}
	
	private Object updateValue(Object oldValue) {
		if ( oldValue instanceof Integer) 
			return new Integer( ((Integer)oldValue).intValue() + 1);
		else 
			throw new RuntimeException("Unsupported type for managed column: " + oldValue.getClass().getName());		
	}
	

}
