package org.apache.tuscany.das.rdb.generator.impl;

import org.apache.tuscany.das.rdb.Converter;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.wrapper.TableWrapper;

public class BaseGenerator {

	protected Converter getConverter(Table t, String name) {
		TableWrapper tw = new TableWrapper(t);
		String converter = tw.getConverter(name);
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
