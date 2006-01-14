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
package org.apache.tuscany.das.rdb.graphbuilder.schema;

import java.sql.Types;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import commonj.sdo.Type;

/**
 *         would provide some facility to do this.
 */
public class ResultSetTypeMap {

	public static ResultSetTypeMap instance = new ResultSetTypeMap();

	/**
	 * Constructor for ResultSetTypeMap.
	 */
	protected ResultSetTypeMap() {
		// Empty Constructor
	}

	/**
	 * These mappings taken primarily from "JDBC API and Tutorial and Reference" by
	 * Fisher, Ellis and Bruce.
	 * 
	 * @param type
	 * @param isNullable
	 * @return
	 */
	public EDataType getEDataType(int type, boolean isNullable) {

		switch (type) {

		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGVARCHAR:
			return getPackage().getEString();

		case Types.NUMERIC:
		case Types.DECIMAL:
			return getPackage().getEBigDecimal();

		case Types.BIT:
		case Types.BOOLEAN:
			if (isNullable)
				return getPackage().getEBooleanObject();
			else
				return getPackage().getEBoolean();

		case Types.TINYINT:
		case Types.SMALLINT:
		case Types.INTEGER:
			if (isNullable)
				return getPackage().getEIntegerObject();
			else
				return getPackage().getEInt();

		case Types.BIGINT:
			if (isNullable)
				return getPackage().getELongObject();
			else
				return getPackage().getELong();

		case Types.REAL:
			if (isNullable)
				return getPackage().getEFloatObject();
			else
				return getPackage().getEFloat();

		case Types.FLOAT:
		case Types.DOUBLE:
			if (isNullable)
				return getPackage().getEDoubleObject();
			else
				return getPackage().getEDouble();

		case Types.BINARY:
		case Types.VARBINARY:
		case Types.LONGVARBINARY:
			return getPackage().getEByteArray();

		case Types.DATE:
		case Types.TIME:
		case Types.TIMESTAMP:
			return getPackage().getEDate();

		case Types.CLOB:
			return getPackage().getEString();

		case Types.BLOB:
			return getPackage().getEByteArray();

		case Types.ARRAY:
			return getPackage().getEByteArray();

		case Types.DISTINCT:
		case Types.STRUCT:
		case Types.REF:
		case Types.DATALINK:
		case Types.JAVA_OBJECT:
			return getPackage().getEJavaObject();

		default:
			return getPackage().getEJavaObject();
		}

	}

	public EcorePackage getPackage() {
		return EcorePackage.eINSTANCE;
	}

	public Type getType(int columnType, boolean b) {
		return SDOUtil.adaptType(getEDataType(columnType, b));
	}

}
