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
package org.apache.tuscany.das.rdb.test.customer.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.test.customer.AnOrder;
import org.apache.tuscany.das.rdb.test.customer.Customer;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Customer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl#getLastName <em>Last Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl#getOrders <em>Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomerImpl extends DataObjectImpl implements Customer
{
  /**
   * The default value of the '{@link #getID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getID()
   * @generated
   * @ordered
   */
  protected static final int ID_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getID()
   * @generated
   * @ordered
   */
  protected int iD = ID_EDEFAULT;

  /**
   * This is true if the ID attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean iDESet = false;

  /**
   * The default value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastName()
   * @generated
   * @ordered
   */
  protected static final String LAST_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastName()
   * @generated
   * @ordered
   */
  protected String lastName = LAST_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddress()
   * @generated
   * @ordered
   */
  protected static final String ADDRESS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddress()
   * @generated
   * @ordered
   */
  protected String address = ADDRESS_EDEFAULT;

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
  protected CustomerImpl()
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
    return CustomerPackageImpl.Literals.CUSTOMER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getID()
  {
    return iD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setID(int newID)
  {
    int oldID = iD;
    iD = newID;
    boolean oldIDESet = iDESet;
    iDESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.CUSTOMER__ID, oldID, iD, !oldIDESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetID()
  {
    int oldID = iD;
    boolean oldIDESet = iDESet;
    iD = ID_EDEFAULT;
    iDESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, CustomerPackageImpl.CUSTOMER__ID, oldID, ID_EDEFAULT, oldIDESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetID()
  {
    return iDESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastName(String newLastName)
  {
    String oldLastName = lastName;
    lastName = newLastName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.CUSTOMER__LAST_NAME, oldLastName, lastName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAddress(String newAddress)
  {
    String oldAddress = address;
    address = newAddress;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.CUSTOMER__ADDRESS, oldAddress, address));
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
      orders = new EObjectContainmentEList(AnOrder.class, this, CustomerPackageImpl.CUSTOMER__ORDERS);
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
      case CustomerPackageImpl.CUSTOMER__ORDERS:
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
      case CustomerPackageImpl.CUSTOMER__ID:
        return new Integer(getID());
      case CustomerPackageImpl.CUSTOMER__LAST_NAME:
        return getLastName();
      case CustomerPackageImpl.CUSTOMER__ADDRESS:
        return getAddress();
      case CustomerPackageImpl.CUSTOMER__ORDERS:
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
      case CustomerPackageImpl.CUSTOMER__ID:
        setID(((Integer)newValue).intValue());
        return;
      case CustomerPackageImpl.CUSTOMER__LAST_NAME:
        setLastName((String)newValue);
        return;
      case CustomerPackageImpl.CUSTOMER__ADDRESS:
        setAddress((String)newValue);
        return;
      case CustomerPackageImpl.CUSTOMER__ORDERS:
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
      case CustomerPackageImpl.CUSTOMER__ID:
        unsetID();
        return;
      case CustomerPackageImpl.CUSTOMER__LAST_NAME:
        setLastName(LAST_NAME_EDEFAULT);
        return;
      case CustomerPackageImpl.CUSTOMER__ADDRESS:
        setAddress(ADDRESS_EDEFAULT);
        return;
      case CustomerPackageImpl.CUSTOMER__ORDERS:
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
      case CustomerPackageImpl.CUSTOMER__ID:
        return isSetID();
      case CustomerPackageImpl.CUSTOMER__LAST_NAME:
        return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
      case CustomerPackageImpl.CUSTOMER__ADDRESS:
        return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
      case CustomerPackageImpl.CUSTOMER__ORDERS:
        return orders != null && !orders.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (iD: ");
    if (iDESet) result.append(iD); else result.append("<unset>");
    result.append(", lastName: ");
    result.append(lastName);
    result.append(", address: ");
    result.append(address);
    result.append(')');
    return result.toString();
  }

} //CustomerImpl
