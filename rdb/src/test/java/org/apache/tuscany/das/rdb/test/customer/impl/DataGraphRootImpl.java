/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.customer.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.test.customer.AnOrder;
import org.apache.tuscany.das.rdb.test.customer.Customer;
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Graph Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl#getCustomers <em>Customers</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl#getOrders <em>Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataGraphRootImpl extends DataObjectImpl implements DataGraphRoot
{
  /**
   * The cached value of the '{@link #getCustomers() <em>Customers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCustomers()
   * @generated
   * @ordered
   */
  protected EList customers = null;

  /**
   * The cached value of the '{@link #getOrders() <em>Orders</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrders()
   * @generated
   * @ordered
   */
  protected EList orders = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DataGraphRootImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return CustomerPackageImpl.Literals.DATA_GRAPH_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getCustomers()
  {
    if (customers == null)
    {
      customers = new EObjectContainmentEList(Customer.class, this, CustomerPackageImpl.DATA_GRAPH_ROOT__CUSTOMERS);
    }
    return customers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getOrders()
  {
    if (orders == null)
    {
      orders = new EObjectContainmentEList(AnOrder.class, this, CustomerPackageImpl.DATA_GRAPH_ROOT__ORDERS);
    }
    return orders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case CustomerPackageImpl.DATA_GRAPH_ROOT__CUSTOMERS:
        return ((InternalEList)getCustomers()).basicRemove(otherEnd, msgs);
      case CustomerPackageImpl.DATA_GRAPH_ROOT__ORDERS:
        return ((InternalEList)getOrders()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case CustomerPackageImpl.DATA_GRAPH_ROOT__CUSTOMERS:
        return getCustomers();
      case CustomerPackageImpl.DATA_GRAPH_ROOT__ORDERS:
        return getOrders();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CustomerPackageImpl.DATA_GRAPH_ROOT__CUSTOMERS:
        getCustomers().clear();
        getCustomers().addAll((Collection)newValue);
        return;
      case CustomerPackageImpl.DATA_GRAPH_ROOT__ORDERS:
        getOrders().clear();
        getOrders().addAll((Collection)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case CustomerPackageImpl.DATA_GRAPH_ROOT__CUSTOMERS:
        getCustomers().clear();
        return;
      case CustomerPackageImpl.DATA_GRAPH_ROOT__ORDERS:
        getOrders().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case CustomerPackageImpl.DATA_GRAPH_ROOT__CUSTOMERS:
        return customers != null && !customers.isEmpty();
      case CustomerPackageImpl.DATA_GRAPH_ROOT__ORDERS:
        return orders != null && !orders.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //DataGraphRootImpl
