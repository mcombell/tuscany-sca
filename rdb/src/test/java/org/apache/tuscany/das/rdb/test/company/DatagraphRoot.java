/**
 * <copyright>
 * </copyright>
 *
 * $Id$
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
