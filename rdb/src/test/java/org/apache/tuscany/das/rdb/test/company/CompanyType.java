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
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getDepartments <em>Departments</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth <em>Employee Of The Month</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface CompanyType
{
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
   * Returns the value of the '<em><b>Employee Of The Month</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Employee Of The Month</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Employee Of The Month</em>' attribute.
   * @see #setEmployeeOfTheMonth(String)
   * @generated
   */
  String getEmployeeOfTheMonth();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth <em>Employee Of The Month</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Employee Of The Month</em>' attribute.
   * @see #getEmployeeOfTheMonth()
   * @generated
   */
  void setEmployeeOfTheMonth(String value);

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
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // CompanyType
