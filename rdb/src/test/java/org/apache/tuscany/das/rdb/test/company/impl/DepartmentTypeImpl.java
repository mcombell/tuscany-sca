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

import org.apache.tuscany.das.rdb.test.company.DepartmentType;
import org.apache.tuscany.das.rdb.test.company.EmployeeType;

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
 * An implementation of the model object '<em><b>Department Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl#getNumber <em>Number</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DepartmentTypeImpl extends DataObjectImpl implements DepartmentType
{
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
   * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocation()
   * @generated
   * @ordered
   */
  protected static final String LOCATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocation()
   * @generated
   * @ordered
   */
  protected String location = LOCATION_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected static final int NUMBER_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected int number = NUMBER_EDEFAULT;

  /**
   * This is true if the Number attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean numberESet = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DepartmentTypeImpl()
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
    return CompanyPackageImpl.Literals.DEPARTMENT_TYPE;
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
      employees = new EObjectContainmentEList(EmployeeType.class, this, CompanyPackageImpl.DEPARTMENT_TYPE__EMPLOYEES);
    }
    return employees;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLocation()
  {
    return location;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLocation(String newLocation)
  {
    String oldLocation = location;
    location = newLocation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.DEPARTMENT_TYPE__LOCATION, oldLocation, location));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.DEPARTMENT_TYPE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getNumber()
  {
    return number;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNumber(int newNumber)
  {
    int oldNumber = number;
    number = newNumber;
    boolean oldNumberESet = numberESet;
    numberESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.DEPARTMENT_TYPE__NUMBER, oldNumber, number, !oldNumberESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetNumber()
  {
    int oldNumber = number;
    boolean oldNumberESet = numberESet;
    number = NUMBER_EDEFAULT;
    numberESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, CompanyPackageImpl.DEPARTMENT_TYPE__NUMBER, oldNumber, NUMBER_EDEFAULT, oldNumberESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetNumber()
  {
    return numberESet;
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
      case CompanyPackageImpl.DEPARTMENT_TYPE__EMPLOYEES:
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
      case CompanyPackageImpl.DEPARTMENT_TYPE__EMPLOYEES:
        return getEmployees();
      case CompanyPackageImpl.DEPARTMENT_TYPE__LOCATION:
        return getLocation();
      case CompanyPackageImpl.DEPARTMENT_TYPE__NAME:
        return getName();
      case CompanyPackageImpl.DEPARTMENT_TYPE__NUMBER:
        return new Integer(getNumber());
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
      case CompanyPackageImpl.DEPARTMENT_TYPE__EMPLOYEES:
        getEmployees().clear();
        getEmployees().addAll((Collection)newValue);
        return;
      case CompanyPackageImpl.DEPARTMENT_TYPE__LOCATION:
        setLocation((String)newValue);
        return;
      case CompanyPackageImpl.DEPARTMENT_TYPE__NAME:
        setName((String)newValue);
        return;
      case CompanyPackageImpl.DEPARTMENT_TYPE__NUMBER:
        setNumber(((Integer)newValue).intValue());
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
      case CompanyPackageImpl.DEPARTMENT_TYPE__EMPLOYEES:
        getEmployees().clear();
        return;
      case CompanyPackageImpl.DEPARTMENT_TYPE__LOCATION:
        setLocation(LOCATION_EDEFAULT);
        return;
      case CompanyPackageImpl.DEPARTMENT_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CompanyPackageImpl.DEPARTMENT_TYPE__NUMBER:
        unsetNumber();
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
      case CompanyPackageImpl.DEPARTMENT_TYPE__EMPLOYEES:
        return employees != null && !employees.isEmpty();
      case CompanyPackageImpl.DEPARTMENT_TYPE__LOCATION:
        return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
      case CompanyPackageImpl.DEPARTMENT_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CompanyPackageImpl.DEPARTMENT_TYPE__NUMBER:
        return isSetNumber();
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
    result.append(" (location: ");
    result.append(location);
    result.append(", name: ");
    result.append(name);
    result.append(", number: ");
    if (numberESet) result.append(number); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //DepartmentTypeImpl
