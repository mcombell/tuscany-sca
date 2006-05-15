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
package org.apache.tuscany.das.rdb.test.company;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Employee Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager <em>Manager</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getSN <em>SN</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface EmployeeType
{
  /**
   * Returns the value of the '<em><b>Manager</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Manager</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Manager</em>' attribute.
   * @see #isSetManager()
   * @see #unsetManager()
   * @see #setManager(boolean)
   * @generated
   */
  boolean isManager();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Manager</em>' attribute.
   * @see #isSetManager()
   * @see #unsetManager()
   * @see #isManager()
   * @generated
   */
  void setManager(boolean value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetManager()
   * @see #isManager()
   * @see #setManager(boolean)
   * @generated
   */
  void unsetManager();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager <em>Manager</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Manager</em>' attribute is set.
   * @see #unsetManager()
   * @see #isManager()
   * @see #setManager(boolean)
   * @generated
   */
  boolean isSetManager();

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
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>SN</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>SN</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>SN</em>' attribute.
   * @see #setSN(String)
   * @generated
   */
  String getSN();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getSN <em>SN</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>SN</em>' attribute.
   * @see #getSN()
   * @generated
   */
  void setSN(String value);

} // EmployeeType
