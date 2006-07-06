package org.apache.tuscany.das.rdb.generator.impl;

import org.apache.tuscany.das.rdb.Converter;

public class BaseGenerator {

	protected Converter getConverter(String converter) {			
		if ( converter != null ) {
			try {
				return (Converter) Thread.currentThread().getContextClassLoader().loadClass(converter).newInstance();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}

}
