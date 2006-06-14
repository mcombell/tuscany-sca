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
package org.apache.tuscany.das.rdb.test.company.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.test.company.CompanyType;
import org.apache.tuscany.das.rdb.test.company.DatagraphRoot;
import org.apache.tuscany.das.rdb.test.company.DepartmentType;
import org.apache.tuscany.das.rdb.test.company.EmployeeType;
import org.apache.tuscany.sdo.impl.DataObjectImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Datagraph Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl#getCompanies <em>Companies</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl#getDepartments <em>Departments</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl#getEmployees <em>Employees</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatagraphRootImpl extends DataObjectImpl implements DatagraphRoot
{
  /**
   * The cached value of the '{@link #getCompanies() <em>Companies</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompanies()
   * @generated
   * @ordered
   */
  protected EList companies = null;

  /**
   * The cached value of the '{@link #getDepartments() <em>Departments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDepartments()
   * @generated
   * @ordered
   */
  protected EList departments = null;

  /**
   * The cached value of the '{@link #getEmployees() <em>Employees</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmployees()
   * @generated
   * @ordered
   */
  protected EList employees = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DatagraphRootImpl()
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
    return CompanyPackageImpl.Literals.DATAGRAPH_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getCompanies()
  {
    if (companies == null)
    {
      companies = new EObjectContainmentEList(CompanyType.class, this, CompanyPackageImpl.DATAGRAPH_ROOT__COMPANIES);
    }
    return companies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getDepartments()
  {
    if (departments == null)
    {
      departments = new EObjectContainmentEList(DepartmentType.class, this, CompanyPackageImpl.DATAGRAPH_ROOT__DEPARTMENTS);
    }
    return departments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getEmployees()
  {
    if (employees == null)
    {
      employees = new EObjectContainmentEList(EmployeeType.class, this, CompanyPackageImpl.DATAGRAPH_ROOT__EMPLOYEES);
    }
    return employees;
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
      case CompanyPackageImpl.DATAGRAPH_ROOT__COMPANIES:
        return ((InternalEList)getCompanies()).basicRemove(otherEnd, msgs);
      case CompanyPackageImpl.DATAGRAPH_ROOT__DEPARTMENTS:
        return ((InternalEList)getDepartments()).basicRemove(otherEnd, msgs);
      case CompanyPackageImpl.DATAGRAPH_ROOT__EMPLOYEES:
        return ((InternalEList)getEmployees()).basicRemove(otherEnd, msgs);
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
      case CompanyPackageImpl.DATAGRAPH_ROOT__COMPANIES:
        return getCompanies();
      case CompanyPackageImpl.DATAGRAPH_ROOT__DEPARTMENTS:
        return getDepartments();
      case CompanyPackageImpl.DATAGRAPH_ROOT__EMPLOYEES:
        return getEmployees();
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
      case CompanyPackageImpl.DATAGRAPH_ROOT__COMPANIES:
        getCompanies().clear();
        getCompanies().addAll((Collection)newValue);
        return;
      case CompanyPackageImpl.DATAGRAPH_ROOT__DEPARTMENTS:
        getDepartments().clear();
        getDepartments().addAll((Collection)newValue);
        return;
      case CompanyPackageImpl.DATAGRAPH_ROOT__EMPLOYEES:
        getEmployees().clear();
        getEmployees().addAll((Collection)newValue);
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
      case CompanyPackageImpl.DATAGRAPH_ROOT__COMPANIES:
        getCompanies().clear();
        return;
      case CompanyPackageImpl.DATAGRAPH_ROOT__DEPARTMENTS:
        getDepartments().clear();
        return;
      case CompanyPackageImpl.DATAGRAPH_ROOT__EMPLOYEES:
        getEmployees().clear();
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
      case CompanyPackageImpl.DATAGRAPH_ROOT__COMPANIES:
        return companies != null && !companies.isEmpty();
      case CompanyPackageImpl.DATAGRAPH_ROOT__DEPARTMENTS:
        return departments != null && !departments.isEmpty();
      case CompanyPackageImpl.DATAGRAPH_ROOT__EMPLOYEES:
        return employees != null && !employees.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //DatagraphRootImpl
