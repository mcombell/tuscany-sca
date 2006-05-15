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

import org.apache.tuscany.das.rdb.test.customer.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomerFactoryImpl extends EFactoryImpl implements CustomerFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final CustomerFactoryImpl eINSTANCE = init();

  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CustomerFactoryImpl init()
  {
    try
    {
      CustomerFactoryImpl theCustomerFactory = (CustomerFactoryImpl)EPackage.Registry.INSTANCE.getEFactory("http:///org.apache.tuscany.das.rdb.test/customer.xsd"); 
      if (theCustomerFactory != null)
      {
        return theCustomerFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CustomerFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case CustomerPackageImpl.AN_ORDER: return (EObject)createAnOrder();
      case CustomerPackageImpl.CUSTOMER: return (EObject)createCustomer();
      case CustomerPackageImpl.DATA_GRAPH_ROOT: return (EObject)createDataGraphRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnOrder createAnOrder()
  {
    AnOrderImpl anOrder = new AnOrderImpl();
    return anOrder;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Customer createCustomer()
  {
    CustomerImpl customer = new CustomerImpl();
    return customer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataGraphRoot createDataGraphRoot()
  {
    DataGraphRootImpl dataGraphRoot = new DataGraphRootImpl();
    return dataGraphRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerPackageImpl getCustomerPackageImpl()
  {
    return (CustomerPackageImpl)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static CustomerPackageImpl getPackage()
  {
    return CustomerPackageImpl.eINSTANCE;
  }

} //CustomerFactoryImpl
