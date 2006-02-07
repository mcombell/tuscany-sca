/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;

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
 * An implementation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getKeyPair <em>Key Pair</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getForeignKeyTable <em>Foreign Key Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#isMany <em>Many</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getPrimaryKeyTable <em>Primary Key Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationshipImpl extends DataObjectImpl implements Relationship
{
  /**
   * The cached value of the '{@link #getKeyPair() <em>Key Pair</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyPair()
   * @generated
   * @ordered
   */
  protected EList keyPair = null;

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
   * The default value of the '{@link #getForeignKeyTable() <em>Foreign Key Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForeignKeyTable()
   * @generated
   * @ordered
   */
  protected static final String FOREIGN_KEY_TABLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getForeignKeyTable() <em>Foreign Key Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForeignKeyTable()
   * @generated
   * @ordered
   */
  protected String foreignKeyTable = FOREIGN_KEY_TABLE_EDEFAULT;

  /**
   * The default value of the '{@link #isMany() <em>Many</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMany()
   * @generated
   * @ordered
   */
  protected static final boolean MANY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isMany() <em>Many</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMany()
   * @generated
   * @ordered
   */
  protected boolean many = MANY_EDEFAULT;

  /**
   * This is true if the Many attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean manyESet = false;

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
   * The default value of the '{@link #getPrimaryKeyTable() <em>Primary Key Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimaryKeyTable()
   * @generated
   * @ordered
   */
  protected static final String PRIMARY_KEY_TABLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPrimaryKeyTable() <em>Primary Key Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimaryKeyTable()
   * @generated
   * @ordered
   */
  protected String primaryKeyTable = PRIMARY_KEY_TABLE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RelationshipImpl()
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
    return ConfigPackageImpl.Literals.RELATIONSHIP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getKeyPair()
  {
    if (keyPair == null)
    {
      keyPair = new EObjectContainmentEList(KeyPair.class, this, ConfigPackageImpl.RELATIONSHIP__KEY_PAIR);
    }
    return keyPair;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RELATIONSHIP__CONFIG, oldConfig, config));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getForeignKeyTable()
  {
    return foreignKeyTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForeignKeyTable(String newForeignKeyTable)
  {
    String oldForeignKeyTable = foreignKeyTable;
    foreignKeyTable = newForeignKeyTable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RELATIONSHIP__FOREIGN_KEY_TABLE, oldForeignKeyTable, foreignKeyTable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isMany()
  {
    return many;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMany(boolean newMany)
  {
    boolean oldMany = many;
    many = newMany;
    boolean oldManyESet = manyESet;
    manyESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RELATIONSHIP__MANY, oldMany, many, !oldManyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetMany()
  {
    boolean oldMany = many;
    boolean oldManyESet = manyESet;
    many = MANY_EDEFAULT;
    manyESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackageImpl.RELATIONSHIP__MANY, oldMany, MANY_EDEFAULT, oldManyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetMany()
  {
    return manyESet;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RELATIONSHIP__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPrimaryKeyTable()
  {
    return primaryKeyTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrimaryKeyTable(String newPrimaryKeyTable)
  {
    String oldPrimaryKeyTable = primaryKeyTable;
    primaryKeyTable = newPrimaryKeyTable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RELATIONSHIP__PRIMARY_KEY_TABLE, oldPrimaryKeyTable, primaryKeyTable));
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
      case ConfigPackageImpl.RELATIONSHIP__KEY_PAIR:
        return ((InternalEList)getKeyPair()).basicRemove(otherEnd, msgs);
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
      case ConfigPackageImpl.RELATIONSHIP__KEY_PAIR:
        return getKeyPair();
      case ConfigPackageImpl.RELATIONSHIP__CONFIG:
        return getConfig();
      case ConfigPackageImpl.RELATIONSHIP__FOREIGN_KEY_TABLE:
        return getForeignKeyTable();
      case ConfigPackageImpl.RELATIONSHIP__MANY:
        return isMany() ? Boolean.TRUE : Boolean.FALSE;
      case ConfigPackageImpl.RELATIONSHIP__NAME:
        return getName();
      case ConfigPackageImpl.RELATIONSHIP__PRIMARY_KEY_TABLE:
        return getPrimaryKeyTable();
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
      case ConfigPackageImpl.RELATIONSHIP__KEY_PAIR:
        getKeyPair().clear();
        getKeyPair().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.RELATIONSHIP__CONFIG:
        setConfig((String)newValue);
        return;
      case ConfigPackageImpl.RELATIONSHIP__FOREIGN_KEY_TABLE:
        setForeignKeyTable((String)newValue);
        return;
      case ConfigPackageImpl.RELATIONSHIP__MANY:
        setMany(((Boolean)newValue).booleanValue());
        return;
      case ConfigPackageImpl.RELATIONSHIP__NAME:
        setName((String)newValue);
        return;
      case ConfigPackageImpl.RELATIONSHIP__PRIMARY_KEY_TABLE:
        setPrimaryKeyTable((String)newValue);
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
      case ConfigPackageImpl.RELATIONSHIP__KEY_PAIR:
        getKeyPair().clear();
        return;
      case ConfigPackageImpl.RELATIONSHIP__CONFIG:
        setConfig(CONFIG_EDEFAULT);
        return;
      case ConfigPackageImpl.RELATIONSHIP__FOREIGN_KEY_TABLE:
        setForeignKeyTable(FOREIGN_KEY_TABLE_EDEFAULT);
        return;
      case ConfigPackageImpl.RELATIONSHIP__MANY:
        unsetMany();
        return;
      case ConfigPackageImpl.RELATIONSHIP__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.RELATIONSHIP__PRIMARY_KEY_TABLE:
        setPrimaryKeyTable(PRIMARY_KEY_TABLE_EDEFAULT);
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
      case ConfigPackageImpl.RELATIONSHIP__KEY_PAIR:
        return keyPair != null && !keyPair.isEmpty();
      case ConfigPackageImpl.RELATIONSHIP__CONFIG:
        return CONFIG_EDEFAULT == null ? config != null : !CONFIG_EDEFAULT.equals(config);
      case ConfigPackageImpl.RELATIONSHIP__FOREIGN_KEY_TABLE:
        return FOREIGN_KEY_TABLE_EDEFAULT == null ? foreignKeyTable != null : !FOREIGN_KEY_TABLE_EDEFAULT.equals(foreignKeyTable);
      case ConfigPackageImpl.RELATIONSHIP__MANY:
        return isSetMany();
      case ConfigPackageImpl.RELATIONSHIP__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ConfigPackageImpl.RELATIONSHIP__PRIMARY_KEY_TABLE:
        return PRIMARY_KEY_TABLE_EDEFAULT == null ? primaryKeyTable != null : !PRIMARY_KEY_TABLE_EDEFAULT.equals(primaryKeyTable);
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
    result.append(", foreignKeyTable: ");
    result.append(foreignKeyTable);
    result.append(", many: ");
    if (manyESet) result.append(many); else result.append("<unset>");
    result.append(", name: ");
    result.append(name);
    result.append(", primaryKeyTable: ");
    result.append(primaryKeyTable);
    result.append(')');
    return result.toString();
  }

} //RelationshipImpl
