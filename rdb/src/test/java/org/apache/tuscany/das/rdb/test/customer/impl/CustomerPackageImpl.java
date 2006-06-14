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
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

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
 * @generated
 */
public class CustomerPackageImpl extends EPackageImpl
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "customer";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "http:///org.apache.tuscany.das.rdb.test/customer.xsd";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "customer";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final CustomerPackageImpl eINSTANCE = org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl.init();

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl <em>An Order</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl
   * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getAnOrder()
   * @generated
   */
  public static final int AN_ORDER = 0;

  /**
   * The feature id for the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int AN_ORDER__ID = 0;

  /**
   * The feature id for the '<em><b>Product</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int AN_ORDER__PRODUCT = 1;

  /**
   * The feature id for the '<em><b>Quantity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int AN_ORDER__QUANTITY = 2;

  /**
   * The feature id for the '<em><b>Customer ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int AN_ORDER__CUSTOMER_ID = 3;

  /**
   * The number of structural features of the '<em>An Order</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int AN_ORDER_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl <em>Customer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl
   * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getCustomer()
   * @generated
   */
  public static final int CUSTOMER = 1;

  /**
   * The feature id for the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CUSTOMER__ID = 0;

  /**
   * The feature id for the '<em><b>Last Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CUSTOMER__LAST_NAME = 1;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CUSTOMER__ADDRESS = 2;

  /**
   * The feature id for the '<em><b>Orders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CUSTOMER__ORDERS = 3;

  /**
   * The number of structural features of the '<em>Customer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CUSTOMER_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl <em>Data Graph Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl
   * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getDataGraphRoot()
   * @generated
   */
  public static final int DATA_GRAPH_ROOT = 2;

  /**
   * The feature id for the '<em><b>Customers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATA_GRAPH_ROOT__CUSTOMERS = 0;

  /**
   * The feature id for the '<em><b>Orders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATA_GRAPH_ROOT__ORDERS = 1;

  /**
   * The number of structural features of the '<em>Data Graph Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATA_GRAPH_ROOT_FEATURE_COUNT = 2;

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
   * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private CustomerPackageImpl()
  {
    super(eNS_URI, ((EFactory)CustomerFactory.INSTANCE));
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
  public static CustomerPackageImpl init()
  {
    if (isInited) return (CustomerPackageImpl)EPackage.Registry.INSTANCE.getEPackage(CustomerPackageImpl.eNS_URI);

    // Obtain or create and register package
    CustomerPackageImpl theCustomerPackageImpl = (CustomerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof CustomerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CustomerPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theCustomerPackageImpl.createPackageContents();

    // Initialize created meta-data
    theCustomerPackageImpl.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theCustomerPackageImpl.freeze();

    return theCustomerPackageImpl;
  }


  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder <em>An Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>An Order</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.AnOrder
   * @generated
   */
  public EClass getAnOrder()
  {
    return anOrderEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getID <em>ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ID</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getID()
   * @see #getAnOrder()
   * @generated
   */
  public EAttribute getAnOrder_ID()
  {
    return (EAttribute)anOrderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getProduct <em>Product</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Product</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getProduct()
   * @see #getAnOrder()
   * @generated
   */
  public EAttribute getAnOrder_Product()
  {
    return (EAttribute)anOrderEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity <em>Quantity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Quantity</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getQuantity()
   * @see #getAnOrder()
   * @generated
   */
  public EAttribute getAnOrder_Quantity()
  {
    return (EAttribute)anOrderEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID <em>Customer ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Customer ID</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.AnOrder#getCustomerID()
   * @see #getAnOrder()
   * @generated
   */
  public EAttribute getAnOrder_CustomerID()
  {
    return (EAttribute)anOrderEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.customer.Customer <em>Customer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Customer</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.Customer
   * @generated
   */
  public EClass getCustomer()
  {
    return customerEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getID <em>ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ID</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.Customer#getID()
   * @see #getCustomer()
   * @generated
   */
  public EAttribute getCustomer_ID()
  {
    return (EAttribute)customerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getLastName <em>Last Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Name</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.Customer#getLastName()
   * @see #getCustomer()
   * @generated
   */
  public EAttribute getCustomer_LastName()
  {
    return (EAttribute)customerEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getAddress <em>Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Address</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.Customer#getAddress()
   * @see #getCustomer()
   * @generated
   */
  public EAttribute getCustomer_Address()
  {
    return (EAttribute)customerEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.customer.Customer#getOrders <em>Orders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Orders</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.Customer#getOrders()
   * @see #getCustomer()
   * @generated
   */
  public EReference getCustomer_Orders()
  {
    return (EReference)customerEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot <em>Data Graph Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Graph Root</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.DataGraphRoot
   * @generated
   */
  public EClass getDataGraphRoot()
  {
    return dataGraphRootEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getCustomers <em>Customers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Customers</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getCustomers()
   * @see #getDataGraphRoot()
   * @generated
   */
  public EReference getDataGraphRoot_Customers()
  {
    return (EReference)dataGraphRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getOrders <em>Orders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Orders</em>'.
   * @see org.apache.tuscany.das.rdb.test.customer.DataGraphRoot#getOrders()
   * @see #getDataGraphRoot()
   * @generated
   */
  public EReference getDataGraphRoot_Orders()
  {
    return (EReference)dataGraphRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  public CustomerFactory getCustomerFactory()
  {
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
  public void createPackageContents()
  {
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
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

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
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (anOrderEClass, 
       source, 
       new String[] 
       {
       "name", "AnOrder",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getAnOrder_ID(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "ID"
       });		
    addAnnotation
      (getAnOrder_Product(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Product"
       });		
    addAnnotation
      (getAnOrder_Quantity(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Quantity"
       });		
    addAnnotation
      (getAnOrder_CustomerID(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Customer_ID"
       });		
    addAnnotation
      (customerEClass, 
       source, 
       new String[] 
       {
       "name", "Customer",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCustomer_ID(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "ID"
       });		
    addAnnotation
      (getCustomer_LastName(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "lastName"
       });		
    addAnnotation
      (getCustomer_Address(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "address"
       });		
    addAnnotation
      (getCustomer_Orders(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "orders"
       });		
    addAnnotation
      (dataGraphRootEClass, 
       source, 
       new String[] 
       {
       "name", "DataGraphRoot",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getDataGraphRoot_Customers(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "customers"
       });		
    addAnnotation
      (getDataGraphRoot_Orders(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "orders"
       });
  }

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  public interface Literals
  {
    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl <em>An Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.customer.impl.AnOrderImpl
     * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getAnOrder()
     * @generated
     */
    public static final EClass AN_ORDER = eINSTANCE.getAnOrder();

    /**
     * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute AN_ORDER__ID = eINSTANCE.getAnOrder_ID();

    /**
     * The meta object literal for the '<em><b>Product</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute AN_ORDER__PRODUCT = eINSTANCE.getAnOrder_Product();

    /**
     * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute AN_ORDER__QUANTITY = eINSTANCE.getAnOrder_Quantity();

    /**
     * The meta object literal for the '<em><b>Customer ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute AN_ORDER__CUSTOMER_ID = eINSTANCE.getAnOrder_CustomerID();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl <em>Customer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerImpl
     * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getCustomer()
     * @generated
     */
    public static final EClass CUSTOMER = eINSTANCE.getCustomer();

    /**
     * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CUSTOMER__ID = eINSTANCE.getCustomer_ID();

    /**
     * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CUSTOMER__LAST_NAME = eINSTANCE.getCustomer_LastName();

    /**
     * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CUSTOMER__ADDRESS = eINSTANCE.getCustomer_Address();

    /**
     * The meta object literal for the '<em><b>Orders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CUSTOMER__ORDERS = eINSTANCE.getCustomer_Orders();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl <em>Data Graph Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.customer.impl.DataGraphRootImpl
     * @see org.apache.tuscany.das.rdb.test.customer.impl.CustomerPackageImpl#getDataGraphRoot()
     * @generated
     */
    public static final EClass DATA_GRAPH_ROOT = eINSTANCE.getDataGraphRoot();

    /**
     * The meta object literal for the '<em><b>Customers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DATA_GRAPH_ROOT__CUSTOMERS = eINSTANCE.getDataGraphRoot_Customers();

    /**
     * The meta object literal for the '<em><b>Orders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DATA_GRAPH_ROOT__ORDERS = eINSTANCE.getDataGraphRoot_Orders();

  }

} //CustomerPackageImpl
