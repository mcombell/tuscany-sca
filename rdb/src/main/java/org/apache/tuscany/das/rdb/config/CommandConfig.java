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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Command Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.CommandConfig#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.CommandConfig#getSQL <em>SQL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.CommandConfig#getKind <em>Kind</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.CommandConfig#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.CommandConfig#getParameterConfig <em>Parameter Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.CommandConfig#getResultDescriptor <em>Result Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig()
 * @model
 * @generated
 */
public interface CommandConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SQL</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SQL</em>' attribute.
	 * @see #setSQL(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig_SQL()
	 * @model
	 * @generated
	 */
	String getSQL();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getSQL <em>SQL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SQL</em>' attribute.
	 * @see #getSQL()
	 * @generated
	 */
	void setSQL(String value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

	/**
	 * Returns the value of the '<em><b>Config</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Config#getCommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config</em>' container reference.
	 * @see #setConfig(Config)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig_Config()
	 * @see org.apache.tuscany.das.rdb.config.Config#getCommandConfig
	 * @model opposite="CommandConfig"
	 * @generated
	 */
	Config getConfig();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getConfig <em>Config</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config</em>' container reference.
	 * @see #getConfig()
	 * @generated
	 */
	void setConfig(Config value);

	/**
	 * Returns the value of the '<em><b>Parameter Config</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.ParameterConfig}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.ParameterConfig#getCommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Config</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Config</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig_ParameterConfig()
	 * @see org.apache.tuscany.das.rdb.config.ParameterConfig#getCommandConfig
	 * @model type="org.apache.tuscany.das.rdb.config.ParameterConfig" opposite="CommandConfig" containment="true"
	 * @generated
	 */
	EList getParameterConfig();

	/**
	 * Returns the value of the '<em><b>Result Descriptor</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.ResultDescriptor}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Descriptor</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Descriptor</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommandConfig_ResultDescriptor()
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommandConfig
	 * @model type="org.apache.tuscany.das.rdb.config.ResultDescriptor" opposite="CommandConfig" containment="true"
	 * @generated
	 */
	EList getResultDescriptor();

} // CommandConfig
