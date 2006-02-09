/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.customer;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Graph Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getCustomers <em>Customers</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getOrders <em>Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface DataGraphRoot
{
  /**
   * Returns the value of the '<em><b>Customers</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.test.customer.Customer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Customers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Customers</em>' containment reference list.
   * @generated
   */
  List getCustomers();

  /**
   * Returns the value of the '<em><b>Orders</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.test.customer.AnOrder}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Orders</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Orders</em>' containment reference list.
   * @generated
   */
  List getOrders();

} // DataGraphRoot
