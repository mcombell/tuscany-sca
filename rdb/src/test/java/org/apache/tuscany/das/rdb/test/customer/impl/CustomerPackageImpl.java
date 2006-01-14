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
import org.apache.tuscany.das.rdb.test.customer.Customer;
import org.apache.tuscany.das.rdb.test.customer.CustomerFactory;
import org.apache.tuscany.das.rdb.test.customer.CustomerPackage;
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomerPackageImpl extends EPackageImpl implements CustomerPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anOrderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataGraphRootEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.apache.tuscany.das.rdb.test.customer.CustomerPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CustomerPackageImpl() {
		super(eNS_URI, CustomerFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CustomerPackage init() {
		if (isInited) return (CustomerPackage)EPackage.Registry.INSTANCE.getEPackage(CustomerPackage.eNS_URI);

		// Obtain or create and register package
		CustomerPackageImpl theCustomerPackage = (CustomerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof CustomerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CustomerPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackageImpl.init();

		// Create package meta-data objects
		theCustomerPackage.createPackageContents();

		// Initialize created meta-data
		theCustomerPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCustomerPackage.freeze();

		return theCustomerPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnOrder() {
		return anOrderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnOrder_ID() {
		return (EAttribute)anOrderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnOrder_Product() {
		return (EAttribute)anOrderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnOrder_Quantity() {
		return (EAttribute)anOrderEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnOrder_CustomerID() {
		return (EAttribute)anOrderEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomer() {
		return customerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomer_ID() {
		return (EAttribute)customerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomer_LastName() {
		return (EAttribute)customerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomer_Address() {
		return (EAttribute)customerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomer_Orders() {
		return (EReference)customerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataGraphRoot() {
		return dataGraphRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataGraphRoot_Customers() {
		return (EReference)dataGraphRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataGraphRoot_Orders() {
		return (EReference)dataGraphRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomerFactory getCustomerFactory() {
		return (CustomerFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		anOrderEClass = createEClass(AN_ORDER);
		createEAttribute(anOrderEClass, AN_ORDER__ID);
		createEAttribute(anOrderEClass, AN_ORDER__PRODUCT);
		createEAttribute(anOrderEClass, AN_ORDER__QUANTITY);
		createEAttribute(anOrderEClass, AN_ORDER__CUSTOMER_ID);

		customerEClass = createEClass(CUSTOMER);
		createEAttribute(customerEClass, CUSTOMER__ID);
		createEAttribute(customerEClass, CUSTOMER__LAST_NAME);
		createEAttribute(customerEClass, CUSTOMER__ADDRESS);
		createEReference(customerEClass, CUSTOMER__ORDERS);

		dataGraphRootEClass = createEClass(DATA_GRAPH_ROOT);
		createEReference(dataGraphRootEClass, DATA_GRAPH_ROOT__CUSTOMERS);
		createEReference(dataGraphRootEClass, DATA_GRAPH_ROOT__ORDERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackageImpl theXMLTypePackage = (XMLTypePackageImpl)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(anOrderEClass, AnOrder.class, "AnOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnOrder_ID(), theXMLTypePackage.getInt(), "iD", null, 1, 1, AnOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnOrder_Product(), theXMLTypePackage.getString(), "product", null, 1, 1, AnOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnOrder_Quantity(), theXMLTypePackage.getInt(), "quantity", null, 1, 1, AnOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnOrder_CustomerID(), theXMLTypePackage.getInt(), "customerID", null, 1, 1, AnOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customerEClass, Customer.class, "Customer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomer_ID(), theXMLTypePackage.getInt(), "iD", null, 1, 1, Customer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomer_LastName(), theXMLTypePackage.getString(), "lastName", null, 1, 1, Customer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomer_Address(), theXMLTypePackage.getString(), "address", null, 1, 1, Customer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomer_Orders(), this.getAnOrder(), null, "orders", null, 1, -1, Customer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataGraphRootEClass, DataGraphRoot.class, "DataGraphRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataGraphRoot_Customers(), this.getCustomer(), null, "customers", null, 0, -1, DataGraphRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataGraphRoot_Orders(), this.getAnOrder(), null, "orders", null, 0, -1, DataGraphRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (anOrderEClass, 
		   source, 
		   new String[] {
			 "name", "AnOrder",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getAnOrder_ID(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ID"
		   });		
		addAnnotation
		  (getAnOrder_Product(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Product"
		   });		
		addAnnotation
		  (getAnOrder_Quantity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Quantity"
		   });		
		addAnnotation
		  (getAnOrder_CustomerID(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Customer_ID"
		   });		
		addAnnotation
		  (customerEClass, 
		   source, 
		   new String[] {
			 "name", "Customer",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getCustomer_ID(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ID"
		   });		
		addAnnotation
		  (getCustomer_LastName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "lastName"
		   });		
		addAnnotation
		  (getCustomer_Address(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "address"
		   });		
		addAnnotation
		  (getCustomer_Orders(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "orders"
		   });		
		addAnnotation
		  (dataGraphRootEClass, 
		   source, 
		   new String[] {
			 "name", "DataGraphRoot",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDataGraphRoot_Customers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "customers"
		   });		
		addAnnotation
		  (getDataGraphRoot_Orders(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "orders"
		   });
	}

} //CustomerPackageImpl
