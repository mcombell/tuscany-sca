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

import org.apache.tuscany.das.rdb.test.customer.AnOrder;
import org.apache.tuscany.sdo.impl.DataObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>An Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl#getCustomerID <em>Customer ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnOrderImpl extends DataObjectImpl implements AnOrder
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
   * The default value of the '{@link #getProduct() <em>Product</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProduct()
   * @generated
   * @ordered
   */
  protected static final String PRODUCT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProduct() <em>Product</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProduct()
   * @generated
   * @ordered
   */
  protected String product = PRODUCT_EDEFAULT;

  /**
   * The default value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuantity()
   * @generated
   * @ordered
   */
  protected static final int QUANTITY_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuantity()
   * @generated
   * @ordered
   */
  protected int quantity = QUANTITY_EDEFAULT;

  /**
   * This is true if the Quantity attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean quantityESet = false;

  /**
   * The default value of the '{@link #getCustomerID() <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCustomerID()
   * @generated
   * @ordered
   */
  protected static final int CUSTOMER_ID_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getCustomerID() <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCustomerID()
   * @generated
   * @ordered
   */
  protected int customerID = CUSTOMER_ID_EDEFAULT;

  /**
   * This is true if the Customer ID attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean customerIDESet = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AnOrderImpl()
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
    return CustomerPackageImpl.Literals.AN_ORDER;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.AN_ORDER__ID, oldID, iD, !oldIDESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, CustomerPackageImpl.AN_ORDER__ID, oldID, ID_EDEFAULT, oldIDESet));
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
  public String getProduct()
  {
    return product;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProduct(String newProduct)
  {
    String oldProduct = product;
    product = newProduct;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.AN_ORDER__PRODUCT, oldProduct, product));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getQuantity()
  {
    return quantity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQuantity(int newQuantity)
  {
    int oldQuantity = quantity;
    quantity = newQuantity;
    boolean oldQuantityESet = quantityESet;
    quantityESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.AN_ORDER__QUANTITY, oldQuantity, quantity, !oldQuantityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetQuantity()
  {
    int oldQuantity = quantity;
    boolean oldQuantityESet = quantityESet;
    quantity = QUANTITY_EDEFAULT;
    quantityESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, CustomerPackageImpl.AN_ORDER__QUANTITY, oldQuantity, QUANTITY_EDEFAULT, oldQuantityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetQuantity()
  {
    return quantityESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getCustomerID()
  {
    return customerID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCustomerID(int newCustomerID)
  {
    int oldCustomerID = customerID;
    customerID = newCustomerID;
    boolean oldCustomerIDESet = customerIDESet;
    customerIDESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackageImpl.AN_ORDER__CUSTOMER_ID, oldCustomerID, customerID, !oldCustomerIDESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCustomerID()
  {
    int oldCustomerID = customerID;
    boolean oldCustomerIDESet = customerIDESet;
    customerID = CUSTOMER_ID_EDEFAULT;
    customerIDESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, CustomerPackageImpl.AN_ORDER__CUSTOMER_ID, oldCustomerID, CUSTOMER_ID_EDEFAULT, oldCustomerIDESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetCustomerID()
  {
    return customerIDESet;
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
      case CustomerPackageImpl.AN_ORDER__ID:
        return new Integer(getID());
      case CustomerPackageImpl.AN_ORDER__PRODUCT:
        return getProduct();
      case CustomerPackageImpl.AN_ORDER__QUANTITY:
        return new Integer(getQuantity());
      case CustomerPackageImpl.AN_ORDER__CUSTOMER_ID:
        return new Integer(getCustomerID());
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
      case CustomerPackageImpl.AN_ORDER__ID:
        setID(((Integer)newValue).intValue());
        return;
      case CustomerPackageImpl.AN_ORDER__PRODUCT:
        setProduct((String)newValue);
        return;
      case CustomerPackageImpl.AN_ORDER__QUANTITY:
        setQuantity(((Integer)newValue).intValue());
        return;
      case CustomerPackageImpl.AN_ORDER__CUSTOMER_ID:
        setCustomerID(((Integer)newValue).intValue());
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
      case CustomerPackageImpl.AN_ORDER__ID:
        unsetID();
        return;
      case CustomerPackageImpl.AN_ORDER__PRODUCT:
        setProduct(PRODUCT_EDEFAULT);
        return;
      case CustomerPackageImpl.AN_ORDER__QUANTITY:
        unsetQuantity();
        return;
      case CustomerPackageImpl.AN_ORDER__CUSTOMER_ID:
        unsetCustomerID();
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
      case CustomerPackageImpl.AN_ORDER__ID:
        return isSetID();
      case CustomerPackageImpl.AN_ORDER__PRODUCT:
        return PRODUCT_EDEFAULT == null ? product != null : !PRODUCT_EDEFAULT.equals(product);
      case CustomerPackageImpl.AN_ORDER__QUANTITY:
        return isSetQuantity();
      case CustomerPackageImpl.AN_ORDER__CUSTOMER_ID:
        return isSetCustomerID();
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
    result.append(", product: ");
    result.append(product);
    result.append(", quantity: ");
    if (quantityESet) result.append(quantity); else result.append("<unset>");
    result.append(", customerID: ");
    if (customerIDESet) result.append(customerID); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //AnOrderImpl
