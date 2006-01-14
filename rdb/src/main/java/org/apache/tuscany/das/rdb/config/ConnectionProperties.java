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
 * A representation of the model object '<em><b>Connection Properties</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverClassName <em>Driver Class Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverURL <em>Driver URL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverPassword <em>Driver Password</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverUserName <em>Driver User Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig <em>Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties()
 * @model
 * @generated
 */
public interface ConnectionProperties extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source</em>' attribute.
	 * @see #setDataSource(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties_DataSource()
	 * @model
	 * @generated
	 */
	String getDataSource();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDataSource <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source</em>' attribute.
	 * @see #getDataSource()
	 * @generated
	 */
	void setDataSource(String value);

	/**
	 * Returns the value of the '<em><b>Driver Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver Class Name</em>' attribute.
	 * @see #setDriverClassName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties_DriverClassName()
	 * @model
	 * @generated
	 */
	String getDriverClassName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverClassName <em>Driver Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver Class Name</em>' attribute.
	 * @see #getDriverClassName()
	 * @generated
	 */
	void setDriverClassName(String value);

	/**
	 * Returns the value of the '<em><b>Driver URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver URL</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver URL</em>' attribute.
	 * @see #setDriverURL(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties_DriverURL()
	 * @model
	 * @generated
	 */
	String getDriverURL();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverURL <em>Driver URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver URL</em>' attribute.
	 * @see #getDriverURL()
	 * @generated
	 */
	void setDriverURL(String value);

	/**
	 * Returns the value of the '<em><b>Driver Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver Password</em>' attribute.
	 * @see #setDriverPassword(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties_DriverPassword()
	 * @model
	 * @generated
	 */
	String getDriverPassword();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverPassword <em>Driver Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver Password</em>' attribute.
	 * @see #getDriverPassword()
	 * @generated
	 */
	void setDriverPassword(String value);

	/**
	 * Returns the value of the '<em><b>Driver User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver User Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver User Name</em>' attribute.
	 * @see #setDriverUserName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties_DriverUserName()
	 * @model
	 * @generated
	 */
	String getDriverUserName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverUserName <em>Driver User Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver User Name</em>' attribute.
	 * @see #getDriverUserName()
	 * @generated
	 */
	void setDriverUserName(String value);

	/**
	 * Returns the value of the '<em><b>Config</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Config#getConnectionProperties <em>Connection Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config</em>' container reference.
	 * @see #setConfig(Config)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConnectionProperties_Config()
	 * @see org.apache.tuscany.das.rdb.config.Config#getConnectionProperties
	 * @model opposite="ConnectionProperties"
	 * @generated
	 */
	Config getConfig();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig <em>Config</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config</em>' container reference.
	 * @see #getConfig()
	 * @generated
	 */
	void setConfig(Config value);

} // ConnectionProperties
