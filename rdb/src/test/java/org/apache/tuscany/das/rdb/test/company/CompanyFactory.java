/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.company;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface CompanyFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CompanyFactory INSTANCE = org.apache.tuscany.das.rdb.test.company.impl.CompanyFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type</em>'.
   * @generated
   */
  CompanyType createCompanyType();

  /**
   * Returns a new object of class '<em>Datagraph Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Datagraph Root</em>'.
   * @generated
   */
  DatagraphRoot createDatagraphRoot();

  /**
   * Returns a new object of class '<em>Department Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Department Type</em>'.
   * @generated
   */
  DepartmentType createDepartmentType();

  /**
   * Returns a new object of class '<em>Employee Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Employee Type</em>'.
   * @generated
   */
  EmployeeType createEmployeeType();

} //CompanyFactory
