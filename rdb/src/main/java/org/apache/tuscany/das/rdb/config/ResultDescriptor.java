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
package org.apache.tuscany.das.rdb.config;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName <em>Table Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getType <em>Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommandConfig <em>Command Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor()
 * @model
 * @generated
 */
public interface ResultDescriptor extends EObject{
	/**
	 * Returns the value of the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Name</em>' attribute.
	 * @see #setColumnName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_ColumnName()
	 * @model
	 * @generated
	 */
	String getColumnName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Name</em>' attribute.
	 * @see #getColumnName()
	 * @generated
	 */
	void setColumnName(String value);

	/**
	 * Returns the value of the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Name</em>' attribute.
	 * @see #setTableName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_TableName()
	 * @model
	 * @generated
	 */
	String getTableName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Name</em>' attribute.
	 * @see #getTableName()
	 * @generated
	 */
	void setTableName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Converter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Converter</em>' attribute.
	 * @see #setConverter(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_Converter()
	 * @model
	 * @generated
	 */
	String getConverter();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter <em>Converter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converter</em>' attribute.
	 * @see #getConverter()
	 * @generated
	 */
	void setConverter(String value);

	/**
	 * Returns the value of the '<em><b>Command Config</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getResultDescriptor <em>Result Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command Config</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Config</em>' container reference.
	 * @see #setCommandConfig(CommandConfig)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_CommandConfig()
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getResultDescriptor
	 * @model opposite="ResultDescriptor"
	 * @generated
	 */
	CommandConfig getCommandConfig();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommandConfig <em>Command Config</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command Config</em>' container reference.
	 * @see #getCommandConfig()
	 * @generated
	 */
	void setCommandConfig(CommandConfig value);

} // ResultDescriptor
