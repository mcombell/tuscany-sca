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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.apache.tuscany.das.rdb.test.customer.CustomerFactory
 * @model kind="package"
 * @generated
 */
public interface CustomerPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "customer";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org.apache.tuscany.das.rdb.test/customer.xsd";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "customer";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CustomerPackage eINSTANCE = org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl <em>An Order</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl
	 * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getAnOrder()
	 * @generated
	 */
	int AN_ORDER = 0;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AN_ORDER__ID = 0;

	/**
	 * The feature id for the '<em><b>Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AN_ORDER__PRODUCT = 1;

	/**
	 * The feature id for the '<em><b>Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AN_ORDER__QUANTITY = 2;

	/**
	 * The feature id for the '<em><b>Customer ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AN_ORDER__CUSTOMER_ID = 3;

	/**
	 * The number of structural features of the the '<em>An Order</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AN_ORDER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl <em>Customer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl
	 * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getCustomer()
	 * @generated
	 */
	int CUSTOMER = 1;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__ID = 0;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__LAST_NAME = 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__ADDRESS = 2;

	/**
	 * The feature id for the '<em><b>Orders</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__ORDERS = 3;

	/**
	 * The number of structural features of the the '<em>Customer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl <em>Data Graph Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl
	 * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getDataGraphRoot()
	 * @generated
	 */
	int DATA_GRAPH_ROOT = 2;

	/**
	 * The feature id for the '<em><b>Customers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_GRAPH_ROOT__CUSTOMERS = 0;

	/**
	 * The feature id for the '<em><b>Orders</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_GRAPH_ROOT__ORDERS = 1;

	/**
	 * The number of structural features of the the '<em>Data Graph Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_GRAPH_ROOT_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder <em>An Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>An Order</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.AnOrder
	 * @generated
	 */
	EClass getAnOrder();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getID()
	 * @see #getAnOrder()
	 * @generated
	 */
	EAttribute getAnOrder_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getProduct()
	 * @see #getAnOrder()
	 * @generated
	 */
	EAttribute getAnOrder_Product();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity <em>Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantity</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity()
	 * @see #getAnOrder()
	 * @generated
	 */
	EAttribute getAnOrder_Quantity();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID <em>Customer ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Customer ID</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID()
	 * @see #getAnOrder()
	 * @generated
	 */
	EAttribute getAnOrder_CustomerID();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.customer.Customer <em>Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.Customer
	 * @generated
	 */
	EClass getCustomer();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.Customer#getID()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getLastName <em>Last Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Name</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.Customer#getLastName()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_LastName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.Customer#getAddress()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_Address();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getOrders <em>Orders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Orders</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.Customer#getOrders()
	 * @see #getCustomer()
	 * @generated
	 */
	EReference getCustomer_Orders();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot <em>Data Graph Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Graph Root</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.DataGraphRoot
	 * @generated
	 */
	EClass getDataGraphRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getCustomers <em>Customers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Customers</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getCustomers()
	 * @see #getDataGraphRoot()
	 * @generated
	 */
	EReference getDataGraphRoot_Customers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getOrders <em>Orders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Orders</em>'.
	 * @see org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getOrders()
	 * @see #getDataGraphRoot()
	 * @generated
	 */
	EReference getDataGraphRoot_Orders();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CustomerFactory getCustomerFactory();

} //CustomerPackage
