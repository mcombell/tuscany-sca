/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.company.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.test.company.CompanyType;
import org.apache.tuscany.das.rdb.test.company.DepartmentType;

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
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl#getDepartments <em>Departments</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl#getEmployeeOfTheMonth <em>Employee Of The Month</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompanyTypeImpl extends DataObjectImpl implements CompanyType
{
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
   * The default value of the '{@link #getEmployeeOfTheMonth() <em>Employee Of The Month</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmployeeOfTheMonth()
   * @generated
   * @ordered
   */
  protected static final String EMPLOYEE_OF_THE_MONTH_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEmployeeOfTheMonth() <em>Employee Of The Month</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmployeeOfTheMonth()
   * @generated
   * @ordered
   */
  protected String employeeOfTheMonth = EMPLOYEE_OF_THE_MONTH_EDEFAULT;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CompanyTypeImpl()
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
    return CompanyPackageImpl.Literals.COMPANY_TYPE;
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
      departments = new EObjectContainmentEList(DepartmentType.class, this, CompanyPackageImpl.COMPANY_TYPE__DEPARTMENTS);
    }
    return departments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEmployeeOfTheMonth()
  {
    return employeeOfTheMonth;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEmployeeOfTheMonth(String newEmployeeOfTheMonth)
  {
    String oldEmployeeOfTheMonth = employeeOfTheMonth;
    employeeOfTheMonth = newEmployeeOfTheMonth;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH, oldEmployeeOfTheMonth, employeeOfTheMonth));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackageImpl.COMPANY_TYPE__NAME, oldName, name));
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
      case CompanyPackageImpl.COMPANY_TYPE__DEPARTMENTS:
        return ((InternalEList)getDepartments()).basicRemove(otherEnd, msgs);
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
      case CompanyPackageImpl.COMPANY_TYPE__DEPARTMENTS:
        return getDepartments();
      case CompanyPackageImpl.COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH:
        return getEmployeeOfTheMonth();
      case CompanyPackageImpl.COMPANY_TYPE__NAME:
        return getName();
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
      case CompanyPackageImpl.COMPANY_TYPE__DEPARTMENTS:
        getDepartments().clear();
        getDepartments().addAll((Collection)newValue);
        return;
      case CompanyPackageImpl.COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH:
        setEmployeeOfTheMonth((String)newValue);
        return;
      case CompanyPackageImpl.COMPANY_TYPE__NAME:
        setName((String)newValue);
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
      case CompanyPackageImpl.COMPANY_TYPE__DEPARTMENTS:
        getDepartments().clear();
        return;
      case CompanyPackageImpl.COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH:
        setEmployeeOfTheMonth(EMPLOYEE_OF_THE_MONTH_EDEFAULT);
        return;
      case CompanyPackageImpl.COMPANY_TYPE__NAME:
        setName(NAME_EDEFAULT);
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
      case CompanyPackageImpl.COMPANY_TYPE__DEPARTMENTS:
        return departments != null && !departments.isEmpty();
      case CompanyPackageImpl.COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH:
        return EMPLOYEE_OF_THE_MONTH_EDEFAULT == null ? employeeOfTheMonth != null : !EMPLOYEE_OF_THE_MONTH_EDEFAULT.equals(employeeOfTheMonth);
      case CompanyPackageImpl.COMPANY_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
    result.append(" (employeeOfTheMonth: ");
    result.append(employeeOfTheMonth);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //CompanyTypeImpl
