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
import org.apache.tuscany.das.rdb.test.customer.CustomerPackage;
import org.apache.tuscany.das.rdb.test.customer.DataGraphRoot;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

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
public class DataGraphRootImpl extends EDataObjectImpl implements DataGraphRoot {
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
	protected DataGraphRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CustomerPackage.eINSTANCE.getDataGraphRoot();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getCustomers() {
		if (customers == null) {
			customers = new EObjectContainmentEList(Customer.class, this, CustomerPackage.DATA_GRAPH_ROOT__CUSTOMERS);
		}
		return customers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getOrders() {
		if (orders == null) {
			orders = new EObjectContainmentEList(AnOrder.class, this, CustomerPackage.DATA_GRAPH_ROOT__ORDERS);
		}
		return orders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CustomerPackage.DATA_GRAPH_ROOT__CUSTOMERS:
					return ((InternalEList)getCustomers()).basicRemove(otherEnd, msgs);
				case CustomerPackage.DATA_GRAPH_ROOT__ORDERS:
					return ((InternalEList)getOrders()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CustomerPackage.DATA_GRAPH_ROOT__CUSTOMERS:
				return getCustomers();
			case CustomerPackage.DATA_GRAPH_ROOT__ORDERS:
				return getOrders();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CustomerPackage.DATA_GRAPH_ROOT__CUSTOMERS:
				getCustomers().clear();
				getCustomers().addAll((Collection)newValue);
				return;
			case CustomerPackage.DATA_GRAPH_ROOT__ORDERS:
				getOrders().clear();
				getOrders().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CustomerPackage.DATA_GRAPH_ROOT__CUSTOMERS:
				getCustomers().clear();
				return;
			case CustomerPackage.DATA_GRAPH_ROOT__ORDERS:
				getOrders().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CustomerPackage.DATA_GRAPH_ROOT__CUSTOMERS:
				return customers != null && !customers.isEmpty();
			case CustomerPackage.DATA_GRAPH_ROOT__ORDERS:
				return orders != null && !orders.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //DataGraphRootImpl
