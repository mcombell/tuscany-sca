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
 * A representation of the model object '<em><b>Key Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.KeyPair#getPrimaryKeyColumn <em>Primary Key Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.KeyPair#getForeignKeyColumn <em>Foreign Key Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.KeyPair#getRelationship <em>Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getKeyPair()
 * @model
 * @generated
 */
public interface KeyPair extends EObject {
	/**
	 * Returns the value of the '<em><b>Primary Key Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Key Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Key Column</em>' attribute.
	 * @see #setPrimaryKeyColumn(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getKeyPair_PrimaryKeyColumn()
	 * @model
	 * @generated
	 */
	String getPrimaryKeyColumn();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.KeyPair#getPrimaryKeyColumn <em>Primary Key Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Key Column</em>' attribute.
	 * @see #getPrimaryKeyColumn()
	 * @generated
	 */
	void setPrimaryKeyColumn(String value);

	/**
	 * Returns the value of the '<em><b>Foreign Key Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreign Key Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Key Column</em>' attribute.
	 * @see #setForeignKeyColumn(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getKeyPair_ForeignKeyColumn()
	 * @model
	 * @generated
	 */
	String getForeignKeyColumn();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.KeyPair#getForeignKeyColumn <em>Foreign Key Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreign Key Column</em>' attribute.
	 * @see #getForeignKeyColumn()
	 * @generated
	 */
	void setForeignKeyColumn(String value);

	/**
	 * Returns the value of the '<em><b>Relationship</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Relationship#getKeyPair <em>Key Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship</em>' container reference.
	 * @see #setRelationship(Relationship)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getKeyPair_Relationship()
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getKeyPair
	 * @model opposite="KeyPair"
	 * @generated
	 */
	Relationship getRelationship();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.KeyPair#getRelationship <em>Relationship</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relationship</em>' container reference.
	 * @see #getRelationship()
	 * @generated
	 */
	void setRelationship(Relationship value);

}
