/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.customer;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface CustomerFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CustomerFactory INSTANCE = org.apache.tuscany.das.rdb.test.customer.impl.CustomerFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>An Order</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>An Order</em>'.
   * @generated
   */
  AnOrder createAnOrder();

  /**
   * Returns a new object of class '<em>Customer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Customer</em>'.
   * @generated
   */
  Customer createCustomer();

  /**
   * Returns a new object of class '<em>Data Graph Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Graph Root</em>'.
   * @generated
   */
  DataGraphRoot createDataGraphRoot();

} //CustomerFactory
