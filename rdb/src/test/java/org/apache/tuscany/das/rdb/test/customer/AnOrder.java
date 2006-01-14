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
 * @see org.apache.tuscany.das.rdb.test.customer.CustomerPackage#getAnOrder()
 * @model extendedMetaData="name='AnOrder' kind='elementOnly'"
 * @generated
 */
public interface AnOrder {
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
	 * @see org.apache.tuscany.das.rdb.test.customer.CustomerPackage#getAnOrder_ID()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='ID'"
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
	 * @see org.apache.tuscany.das.rdb.test.customer.CustomerPackage#getAnOrder_Product()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='Product'"
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
	 * @see org.apache.tuscany.das.rdb.test.customer.CustomerPackage#getAnOrder_Quantity()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='Quantity'"
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
	 * @see org.apache.tuscany.das.rdb.test.customer.CustomerPackage#getAnOrder_CustomerID()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='Customer_ID'"
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
