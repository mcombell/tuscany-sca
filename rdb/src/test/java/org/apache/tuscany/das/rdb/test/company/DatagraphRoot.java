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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Datagraph Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getCompanies <em>Companies</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getDepartments <em>Departments</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getEmployees <em>Employees</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface DatagraphRoot
{
  /**
   * Returns the value of the '<em><b>Companies</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.test.company.CompanyType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Companies</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Companies</em>' containment reference list.
   * @generated
   */
  List getCompanies();

  /**
   * Returns the value of the '<em><b>Departments</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.test.company.DepartmentType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Departments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Departments</em>' containment reference list.
   * @generated
   */
  List getDepartments();

  /**
   * Returns the value of the '<em><b>Employees</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.test.company.EmployeeType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Employees</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Employees</em>' containment reference list.
   * @generated
   */
  List getEmployees();

} // DatagraphRoot
