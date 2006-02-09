/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.customer;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>An Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getID <em>ID</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getProduct <em>Product</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID <em>Customer ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface AnOrder
{
  /**
   * Returns the value of the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ID</em>' attribute.
   * @see #isSetID()
   * @see #unsetID()
   * @see #setID(int)
   * @generated
   */
  int getID();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getID <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ID</em>' attribute.
   * @see #isSetID()
   * @see #unsetID()
   * @see #getID()
   * @generated
   */
  void setID(int value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getID <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetID()
   * @see #getID()
   * @see #setID(int)
   * @generated
   */
  void unsetID();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getID <em>ID</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>ID</em>' attribute is set.
   * @see #unsetID()
   * @see #getID()
   * @see #setID(int)
   * @generated
   */
  boolean isSetID();

  /**
   * Returns the value of the '<em><b>Product</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Product</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Product</em>' attribute.
   * @see #setProduct(String)
   * @generated
   */
  String getProduct();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getProduct <em>Product</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Product</em>' attribute.
   * @see #getProduct()
   * @generated
   */
  void setProduct(String value);

  /**
   * Returns the value of the '<em><b>Quantity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quantity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quantity</em>' attribute.
   * @see #isSetQuantity()
   * @see #unsetQuantity()
   * @see #setQuantity(int)
   * @generated
   */
  int getQuantity();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity <em>Quantity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Quantity</em>' attribute.
   * @see #isSetQuantity()
   * @see #unsetQuantity()
   * @see #getQuantity()
   * @generated
   */
  void setQuantity(int value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity <em>Quantity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetQuantity()
   * @see #getQuantity()
   * @see #setQuantity(int)
   * @generated
   */
  void unsetQuantity();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity <em>Quantity</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Quantity</em>' attribute is set.
   * @see #unsetQuantity()
   * @see #getQuantity()
   * @see #setQuantity(int)
   * @generated
   */
  boolean isSetQuantity();

  /**
   * Returns the value of the '<em><b>Customer ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Customer ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Customer ID</em>' attribute.
   * @see #isSetCustomerID()
   * @see #unsetCustomerID()
   * @see #setCustomerID(int)
   * @generated
   */
  int getCustomerID();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Customer ID</em>' attribute.
   * @see #isSetCustomerID()
   * @see #unsetCustomerID()
   * @see #getCustomerID()
   * @generated
   */
  void setCustomerID(int value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetCustomerID()
   * @see #getCustomerID()
   * @see #setCustomerID(int)
   * @generated
   */
  void unsetCustomerID();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID <em>Customer ID</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Customer ID</em>' attribute is set.
   * @see #unsetCustomerID()
   * @see #getCustomerID()
   * @see #setCustomerID(int)
   * @generated
   */
  boolean isSetCustomerID();

} // AnOrder
