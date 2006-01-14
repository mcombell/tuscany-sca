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
 * A representation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Relationship#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Relationship#getPrimaryKeyTable <em>Primary Key Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Relationship#getForeignKeyTable <em>Foreign Key Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Relationship#isMany <em>Many</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Relationship#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Relationship#getKeyPair <em>Key Pair</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship()
 * @model
 * @generated
 */
public interface Relationship extends EObject {
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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Relationship#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Primary Key Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Key Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Key Table</em>' attribute.
	 * @see #setPrimaryKeyTable(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship_PrimaryKeyTable()
	 * @model
	 * @generated
	 */
	String getPrimaryKeyTable();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Relationship#getPrimaryKeyTable <em>Primary Key Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Key Table</em>' attribute.
	 * @see #getPrimaryKeyTable()
	 * @generated
	 */
	void setPrimaryKeyTable(String value);

	/**
	 * Returns the value of the '<em><b>Foreign Key Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreign Key Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Key Table</em>' attribute.
	 * @see #setForeignKeyTable(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship_ForeignKeyTable()
	 * @model
	 * @generated
	 */
	String getForeignKeyTable();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Relationship#getForeignKeyTable <em>Foreign Key Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreign Key Table</em>' attribute.
	 * @see #getForeignKeyTable()
	 * @generated
	 */
	void setForeignKeyTable(String value);

	/**
	 * Returns the value of the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Many</em>' attribute.
	 * @see #setMany(boolean)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship_Many()
	 * @model
	 * @generated
	 */
	boolean isMany();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Relationship#isMany <em>Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Many</em>' attribute.
	 * @see #isMany()
	 * @generated
	 */
	void setMany(boolean value);

	/**
	 * Returns the value of the '<em><b>Config</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Config#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config</em>' container reference.
	 * @see #setConfig(Config)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship_Config()
	 * @see org.apache.tuscany.das.rdb.config.Config#getRelationship
	 * @model opposite="Relationship"
	 * @generated
	 */
	Config getConfig();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Relationship#getConfig <em>Config</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config</em>' container reference.
	 * @see #getConfig()
	 * @generated
	 */
	void setConfig(Config value);

	/**
	 * Returns the value of the '<em><b>Key Pair</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.KeyPair}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.KeyPair#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Pair</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Pair</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getRelationship_KeyPair()
	 * @see org.apache.tuscany.das.rdb.config.KeyPair#getRelationship
	 * @model type="org.apache.tuscany.das.rdb.config.KeyPair" opposite="Relationship" containment="true"
	 * @generated
	 */
	EList getKeyPair();

} 
