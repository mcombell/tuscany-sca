/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Table;

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
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getCreate <em>Create</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getDelete <em>Delete</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.TableImpl#getUpdate <em>Update</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableImpl extends DataObjectImpl implements Table
{
  /**
   * The cached value of the '{@link #getColumn() <em>Column</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumn()
   * @generated
   * @ordered
   */
  protected EList column = null;

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
   * The default value of the '{@link #getCreate() <em>Create</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreate()
   * @generated
   * @ordered
   */
  protected static final String CREATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCreate() <em>Create</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreate()
   * @generated
   * @ordered
   */
  protected String create = CREATE_EDEFAULT;

  /**
   * The default value of the '{@link #getDelete() <em>Delete</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDelete()
   * @generated
   * @ordered
   */
  protected static final String DELETE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDelete() <em>Delete</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDelete()
   * @generated
   * @ordered
   */
  protected String delete = DELETE_EDEFAULT;

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
   * The default value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyName()
   * @generated
   * @ordered
   */
  protected static final String PROPERTY_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyName()
   * @generated
   * @ordered
   */
  protected String propertyName = PROPERTY_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getUpdate() <em>Update</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpdate()
   * @generated
   * @ordered
   */
  protected static final String UPDATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUpdate() <em>Update</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpdate()
   * @generated
   * @ordered
   */
  protected String update = UPDATE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TableImpl()
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
    return ConfigPackageImpl.Literals.TABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getColumn()
  {
    if (column == null)
    {
      column = new EObjectContainmentEList(Column.class, this, ConfigPackageImpl.TABLE__COLUMN);
    }
    return column;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.TABLE__CONFIG, oldConfig, config));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCreate()
  {
    return create;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreate(String newCreate)
  {
    String oldCreate = create;
    create = newCreate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.TABLE__CREATE, oldCreate, create));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDelete()
  {
    return delete;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDelete(String newDelete)
  {
    String oldDelete = delete;
    delete = newDelete;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.TABLE__DELETE, oldDelete, delete));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.TABLE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPropertyName()
  {
    return propertyName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPropertyName(String newPropertyName)
  {
    String oldPropertyName = propertyName;
    propertyName = newPropertyName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.TABLE__PROPERTY_NAME, oldPropertyName, propertyName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUpdate()
  {
    return update;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpdate(String newUpdate)
  {
    String oldUpdate = update;
    update = newUpdate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.TABLE__UPDATE, oldUpdate, update));
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
      case ConfigPackageImpl.TABLE__COLUMN:
        return ((InternalEList)getColumn()).basicRemove(otherEnd, msgs);
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
      case ConfigPackageImpl.TABLE__COLUMN:
        return getColumn();
      case ConfigPackageImpl.TABLE__CONFIG:
        return getConfig();
      case ConfigPackageImpl.TABLE__CREATE:
        return getCreate();
      case ConfigPackageImpl.TABLE__DELETE:
        return getDelete();
      case ConfigPackageImpl.TABLE__NAME:
        return getName();
      case ConfigPackageImpl.TABLE__PROPERTY_NAME:
        return getPropertyName();
      case ConfigPackageImpl.TABLE__UPDATE:
        return getUpdate();
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
      case ConfigPackageImpl.TABLE__COLUMN:
        getColumn().clear();
        getColumn().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.TABLE__CONFIG:
        setConfig((String)newValue);
        return;
      case ConfigPackageImpl.TABLE__CREATE:
        setCreate((String)newValue);
        return;
      case ConfigPackageImpl.TABLE__DELETE:
        setDelete((String)newValue);
        return;
      case ConfigPackageImpl.TABLE__NAME:
        setName((String)newValue);
        return;
      case ConfigPackageImpl.TABLE__PROPERTY_NAME:
        setPropertyName((String)newValue);
        return;
      case ConfigPackageImpl.TABLE__UPDATE:
        setUpdate((String)newValue);
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
      case ConfigPackageImpl.TABLE__COLUMN:
        getColumn().clear();
        return;
      case ConfigPackageImpl.TABLE__CONFIG:
        setConfig(CONFIG_EDEFAULT);
        return;
      case ConfigPackageImpl.TABLE__CREATE:
        setCreate(CREATE_EDEFAULT);
        return;
      case ConfigPackageImpl.TABLE__DELETE:
        setDelete(DELETE_EDEFAULT);
        return;
      case ConfigPackageImpl.TABLE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.TABLE__PROPERTY_NAME:
        setPropertyName(PROPERTY_NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.TABLE__UPDATE:
        setUpdate(UPDATE_EDEFAULT);
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
      case ConfigPackageImpl.TABLE__COLUMN:
        return column != null && !column.isEmpty();
      case ConfigPackageImpl.TABLE__CONFIG:
        return CONFIG_EDEFAULT == null ? config != null : !CONFIG_EDEFAULT.equals(config);
      case ConfigPackageImpl.TABLE__CREATE:
        return CREATE_EDEFAULT == null ? create != null : !CREATE_EDEFAULT.equals(create);
      case ConfigPackageImpl.TABLE__DELETE:
        return DELETE_EDEFAULT == null ? delete != null : !DELETE_EDEFAULT.equals(delete);
      case ConfigPackageImpl.TABLE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ConfigPackageImpl.TABLE__PROPERTY_NAME:
        return PROPERTY_NAME_EDEFAULT == null ? propertyName != null : !PROPERTY_NAME_EDEFAULT.equals(propertyName);
      case ConfigPackageImpl.TABLE__UPDATE:
        return UPDATE_EDEFAULT == null ? update != null : !UPDATE_EDEFAULT.equals(update);
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
    result.append(", create: ");
    result.append(create);
    result.append(", delete: ");
    result.append(delete);
    result.append(", name: ");
    result.append(name);
    result.append(", propertyName: ");
    result.append(propertyName);
    result.append(", update: ");
    result.append(update);
    result.append(')');
    return result.toString();
  }

} //TableImpl
