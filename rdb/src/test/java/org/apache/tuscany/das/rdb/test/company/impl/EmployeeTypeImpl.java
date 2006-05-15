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

import org.apache.tuscany.das.rdb.test.company.EmployeeType;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Employee Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl#isManager <em>Manager</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl#getSN <em>SN</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EmployeeTypeImpl extends DataObjectImpl implements EmployeeType
{
  /**
   * The default value of the '{@link #isManager() <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isManager()
   * @generated
   * @ordered
   */
  protected static final boolean MANAGER_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isManager() <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isManager()
   * @generated
   * @ordered
   */
  protected boolean manager = MANAGER_EDEFAULT;

  /**
   * This is true if the Manager attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean managerESet = false;

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
   * The default value of the '{@link #getSN() <em>SN</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSN()
   * @generated
   * @ordered
   */
  protected static final String SN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSN() <em>SN</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSN()
   * @generated
   * @ordered
   */
  protected String sN = SN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EmployeeTypeImpl()
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
    return CompanyPackageImpl.Literals.EMPLOYEE_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isManager()
  {
    return manager;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setManager(boolean newManager)
  {
    boolean oldManager = manager;
    manager = newManager;
    boolean oldManagerESet = managerESet;
    managerESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.EMPLOYEE_TYPE__MANAGER, oldManager, manager, !oldManagerESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetManager()
  {
    boolean oldManager = manager;
    boolean oldManagerESet = managerESet;
    manager = MANAGER_EDEFAULT;
    managerESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, CompanyPackageImpl.EMPLOYEE_TYPE__MANAGER, oldManager, MANAGER_EDEFAULT, oldManagerESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetManager()
  {
    return managerESet;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.EMPLOYEE_TYPE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSN()
  {
    return sN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSN(String newSN)
  {
    String oldSN = sN;
    sN = newSN;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.EMPLOYEE_TYPE__SN, oldSN, sN));
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
      case CompanyPackageImpl.EMPLOYEE_TYPE__MANAGER:
        return isManager() ? Boolean.TRUE : Boolean.FALSE;
      case CompanyPackageImpl.EMPLOYEE_TYPE__NAME:
        return getName();
      case CompanyPackageImpl.EMPLOYEE_TYPE__SN:
        return getSN();
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
      case CompanyPackageImpl.EMPLOYEE_TYPE__MANAGER:
        setManager(((Boolean)newValue).booleanValue());
        return;
      case CompanyPackageImpl.EMPLOYEE_TYPE__NAME:
        setName((String)newValue);
        return;
      case CompanyPackageImpl.EMPLOYEE_TYPE__SN:
        setSN((String)newValue);
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
      case CompanyPackageImpl.EMPLOYEE_TYPE__MANAGER:
        unsetManager();
        return;
      case CompanyPackageImpl.EMPLOYEE_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CompanyPackageImpl.EMPLOYEE_TYPE__SN:
        setSN(SN_EDEFAULT);
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
      case CompanyPackageImpl.EMPLOYEE_TYPE__MANAGER:
        return isSetManager();
      case CompanyPackageImpl.EMPLOYEE_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CompanyPackageImpl.EMPLOYEE_TYPE__SN:
        return SN_EDEFAULT == null ? sN != null : !SN_EDEFAULT.equals(sN);
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
    result.append(" (manager: ");
    if (managerESet) result.append(manager); else result.append("<unset>");
    result.append(", name: ");
    result.append(name);
    result.append(", sN: ");
    result.append(sN);
    result.append(')');
    return result.toString();
  }

} //EmployeeTypeImpl
