/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.config.Command;
import org.apache.tuscany.das.rdb.config.Parameter;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;

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
 * An implementation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getResultDescriptor <em>Result Descriptor</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getSQL <em>SQL</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommandImpl extends DataObjectImpl implements Command
{
  /**
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected EList parameter = null;

  /**
   * The cached value of the '{@link #getResultDescriptor() <em>Result Descriptor</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResultDescriptor()
   * @generated
   * @ordered
   */
  protected EList resultDescriptor = null;

  /**
   * The default value of the '{@link #getConfig() <em>Config</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfig()
   * @generated
   * @ordered
   */
  protected static final String CONFIG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConfig() <em>Config</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfig()
   * @generated
   * @ordered
   */
  protected String config = CONFIG_EDEFAULT;

  /**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected static final String KIND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected String kind = KIND_EDEFAULT;

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
   * The default value of the '{@link #getSQL() <em>SQL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSQL()
   * @generated
   * @ordered
   */
  protected static final String SQL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSQL() <em>SQL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSQL()
   * @generated
   * @ordered
   */
  protected String sQL = SQL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CommandImpl()
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
    return ConfigPackageImpl.Literals.COMMAND;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getParameter()
  {
    if (parameter == null)
    {
      parameter = new EObjectContainmentEList(Parameter.class, this, ConfigPackageImpl.COMMAND__PARAMETER);
    }
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getResultDescriptor()
  {
    if (resultDescriptor == null)
    {
      resultDescriptor = new EObjectContainmentEList(ResultDescriptor.class, this, ConfigPackageImpl.COMMAND__RESULT_DESCRIPTOR);
    }
    return resultDescriptor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConfig()
  {
    return config;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConfig(String newConfig)
  {
    String oldConfig = config;
    config = newConfig;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COMMAND__CONFIG, oldConfig, config));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getKind()
  {
    return kind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKind(String newKind)
  {
    String oldKind = kind;
    kind = newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COMMAND__KIND, oldKind, kind));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COMMAND__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSQL()
  {
    return sQL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSQL(String newSQL)
  {
    String oldSQL = sQL;
    sQL = newSQL;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COMMAND__SQL, oldSQL, sQL));
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
      case ConfigPackageImpl.COMMAND__PARAMETER:
        return ((InternalEList)getParameter()).basicRemove(otherEnd, msgs);
      case ConfigPackageImpl.COMMAND__RESULT_DESCRIPTOR:
        return ((InternalEList)getResultDescriptor()).basicRemove(otherEnd, msgs);
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
      case ConfigPackageImpl.COMMAND__PARAMETER:
        return getParameter();
      case ConfigPackageImpl.COMMAND__RESULT_DESCRIPTOR:
        return getResultDescriptor();
      case ConfigPackageImpl.COMMAND__CONFIG:
        return getConfig();
      case ConfigPackageImpl.COMMAND__KIND:
        return getKind();
      case ConfigPackageImpl.COMMAND__NAME:
        return getName();
      case ConfigPackageImpl.COMMAND__SQL:
        return getSQL();
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
      case ConfigPackageImpl.COMMAND__PARAMETER:
        getParameter().clear();
        getParameter().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.COMMAND__RESULT_DESCRIPTOR:
        getResultDescriptor().clear();
        getResultDescriptor().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.COMMAND__CONFIG:
        setConfig((String)newValue);
        return;
      case ConfigPackageImpl.COMMAND__KIND:
        setKind((String)newValue);
        return;
      case ConfigPackageImpl.COMMAND__NAME:
        setName((String)newValue);
        return;
      case ConfigPackageImpl.COMMAND__SQL:
        setSQL((String)newValue);
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
      case ConfigPackageImpl.COMMAND__PARAMETER:
        getParameter().clear();
        return;
      case ConfigPackageImpl.COMMAND__RESULT_DESCRIPTOR:
        getResultDescriptor().clear();
        return;
      case ConfigPackageImpl.COMMAND__CONFIG:
        setConfig(CONFIG_EDEFAULT);
        return;
      case ConfigPackageImpl.COMMAND__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case ConfigPackageImpl.COMMAND__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.COMMAND__SQL:
        setSQL(SQL_EDEFAULT);
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
      case ConfigPackageImpl.COMMAND__PARAMETER:
        return parameter != null && !parameter.isEmpty();
      case ConfigPackageImpl.COMMAND__RESULT_DESCRIPTOR:
        return resultDescriptor != null && !resultDescriptor.isEmpty();
      case ConfigPackageImpl.COMMAND__CONFIG:
        return CONFIG_EDEFAULT == null ? config != null : !CONFIG_EDEFAULT.equals(config);
      case ConfigPackageImpl.COMMAND__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case ConfigPackageImpl.COMMAND__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ConfigPackageImpl.COMMAND__SQL:
        return SQL_EDEFAULT == null ? sQL != null : !SQL_EDEFAULT.equals(sQL);
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
    result.append(" (config: ");
    result.append(config);
    result.append(", kind: ");
    result.append(kind);
    result.append(", name: ");
    result.append(name);
    result.append(", sQL: ");
    result.append(sQL);
    result.append(')');
    return result.toString();
  }

} //CommandImpl
