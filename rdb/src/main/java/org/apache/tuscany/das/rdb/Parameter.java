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
package org.apache.tuscany.das.rdb;

import commonj.sdo.Type;

/**
 * Describes a single parameter for a parameterized SQL statement.
 * 
 * 
 */
public interface Parameter {

	/**
	 * Value for "Direction" that indicates that a parameter is soley for input.
	 */
	final static int IN = 1;

	/**
	 * Value for "Direction" that indicates that a parameter is soley for
	 * output. Out parameters only apply to Stored Procedures
	 */
	final static int OUT = 2;

	/**
	 * Value for "Direction" that indicates that a parameter is for both input
	 * and output. In-out parameters only apply to stored procedures
	 */
	final static int IN_OUT = 3;

	/**
	 * A parameter has a type in terms of SDO2 DataTypes. Legal values are
	 * provided in {@link SDODataTypes}
	 * <p>
	 * Explicit definition of type is required only for OUT parameters of stored
	 * procedures commands
	 * 
	 * @param type
	 */
	public void setType(Type type);

	/**
	 * Set the index of the parameter. For example, if a SQL statement requires
	 * two parameters, the first one has an index of one.
	 * 
	 * @param index
	 *            The index of the parameter
	 */
	public void setIndex(int index);

	/**
	 * Sets the name of the parameter
	 * 
	 * @param name
	 *            the parameter name
	 */
	public void setName(String name);

	/**
	 * Sets the value of the parameter. All IN and IN_OUT parameters must be set
	 * beore a command can be executed.
	 * 
	 * @param value
	 *            The value for the parameter
	 */
	public void setValue(Object value);

	/**
	 * Parameters are typically IN and this is the default. A parameter's
	 * direction must be set when the parameter is OUT or IN_OUT.
	 * 
	 * @param direction
	 *            the parameters direction
	 */
	public void setDirection(int direction);

	/**
	 * TODO  Not sure this method is needed.
	 * @param converter
	 */
	public void setConverter(Converter converter);

	/**
	 * @return the type of the paramater in terms of {@link SDODataTypes}
	 */
	public Type getType();

	/**
	 * TODO Not sure this is is needed.
	 * @return
	 */
	public Converter getConverter();

	/**
	 * @return the index of the parameter
	 */
	public int getIndex();

	/**
	 * @return the name of the parameter
	 */
	public String getName();

	/**
	 * @return the value of the parameter
	 */
	public Object getValue();

	/**
	 * @return the direction of the parameter
	 */
	public int getDirection();

}
