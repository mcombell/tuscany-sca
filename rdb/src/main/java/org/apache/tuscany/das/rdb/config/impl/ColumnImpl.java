/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.Column;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#isCollision <em>Collision</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#getColumnType <em>Column Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#getConverterClassName <em>Converter Class Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#isGenerated <em>Generated</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#isPrimaryKey <em>Primary Key</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnImpl extends DataObjectImpl implements Column
{
  /**
   * The default value of the '{@link #isCollision() <em>Collision</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCollision()
   * @generated
   * @ordered
   */
  protected static final boolean COLLISION_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCollision() <em>Collision</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCollision()
   * @generated
   * @ordered
   */
  protected boolean collision = COLLISION_EDEFAULT;

  /**
   * This is true if the Collision attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean collisionESet = false;

  /**
   * The default value of the '{@link #getColumnType() <em>Column Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnType()
   * @generated
   * @ordered
   */
  protected static final String COLUMN_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumnType() <em>Column Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnType()
   * @generated
   * @ordered
   */
  protected String columnType = COLUMN_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getConverterClassName() <em>Converter Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConverterClassName()
   * @generated
   * @ordered
   */
  protected static final String CONVERTER_CLASS_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConverterClassName() <em>Converter Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConverterClassName()
   * @generated
   * @ordered
   */
  protected String converterClassName = CONVERTER_CLASS_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #isGenerated() <em>Generated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerated()
   * @generated
   * @ordered
   */
  protected static final boolean GENERATED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isGenerated() <em>Generated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerated()
   * @generated
   * @ordered
   */
  protected boolean generated = GENERATED_EDEFAULT;

  /**
   * This is true if the Generated attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean generatedESet = false;

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
   * The default value of the '{@link #isPrimaryKey() <em>Primary Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPrimaryKey()
   * @generated
   * @ordered
   */
  protected static final boolean PRIMARY_KEY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPrimaryKey() <em>Primary Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPrimaryKey()
   * @generated
   * @ordered
   */
  protected boolean primaryKey = PRIMARY_KEY_EDEFAULT;

  /**
   * This is true if the Primary Key attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean primaryKeyESet = false;

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
   * The default value of the '{@link #getTable() <em>Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTable()
   * @generated
   * @ordered
   */
  protected static final String TABLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTable() <em>Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTable()
   * @generated
   * @ordered
   */
  protected String table = TABLE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnImpl()
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
    return ConfigPackageImpl.Literals.COLUMN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCollision()
  {
    return collision;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCollision(boolean newCollision)
  {
    boolean oldCollision = collision;
    collision = newCollision;
    boolean oldCollisionESet = collisionESet;
    collisionESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__COLLISION, oldCollision, collision, !oldCollisionESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCollision()
  {
    boolean oldCollision = collision;
    boolean oldCollisionESet = collisionESet;
    collision = COLLISION_EDEFAULT;
    collisionESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackageImpl.COLUMN__COLLISION, oldCollision, COLLISION_EDEFAULT, oldCollisionESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetCollision()
  {
    return collisionESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getColumnType()
  {
    return columnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumnType(String newColumnType)
  {
    String oldColumnType = columnType;
    columnType = newColumnType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__COLUMN_TYPE, oldColumnType, columnType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConverterClassName()
  {
    return converterClassName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConverterClassName(String newConverterClassName)
  {
    String oldConverterClassName = converterClassName;
    converterClassName = newConverterClassName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__CONVERTER_CLASS_NAME, oldConverterClassName, converterClassName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isGenerated()
  {
    return generated;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenerated(boolean newGenerated)
  {
    boolean oldGenerated = generated;
    generated = newGenerated;
    boolean oldGeneratedESet = generatedESet;
    generatedESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__GENERATED, oldGenerated, generated, !oldGeneratedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetGenerated()
  {
    boolean oldGenerated = generated;
    boolean oldGeneratedESet = generatedESet;
    generated = GENERATED_EDEFAULT;
    generatedESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackageImpl.COLUMN__GENERATED, oldGenerated, GENERATED_EDEFAULT, oldGeneratedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetGenerated()
  {
    return generatedESet;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPrimaryKey()
  {
    return primaryKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrimaryKey(boolean newPrimaryKey)
  {
    boolean oldPrimaryKey = primaryKey;
    primaryKey = newPrimaryKey;
    boolean oldPrimaryKeyESet = primaryKeyESet;
    primaryKeyESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__PRIMARY_KEY, oldPrimaryKey, primaryKey, !oldPrimaryKeyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetPrimaryKey()
  {
    boolean oldPrimaryKey = primaryKey;
    boolean oldPrimaryKeyESet = primaryKeyESet;
    primaryKey = PRIMARY_KEY_EDEFAULT;
    primaryKeyESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackageImpl.COLUMN__PRIMARY_KEY, oldPrimaryKey, PRIMARY_KEY_EDEFAULT, oldPrimaryKeyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetPrimaryKey()
  {
    return primaryKeyESet;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__PROPERTY_NAME, oldPropertyName, propertyName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTable()
  {
    return table;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTable(String newTable)
  {
    String oldTable = table;
    table = newTable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.COLUMN__TABLE, oldTable, table));
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
      case ConfigPackageImpl.COLUMN__COLLISION:
        return isCollision() ? Boolean.TRUE : Boolean.FALSE;
      case ConfigPackageImpl.COLUMN__COLUMN_TYPE:
        return getColumnType();
      case ConfigPackageImpl.COLUMN__CONVERTER_CLASS_NAME:
        return getConverterClassName();
      case ConfigPackageImpl.COLUMN__GENERATED:
        return isGenerated() ? Boolean.TRUE : Boolean.FALSE;
      case ConfigPackageImpl.COLUMN__NAME:
        return getName();
      case ConfigPackageImpl.COLUMN__PRIMARY_KEY:
        return isPrimaryKey() ? Boolean.TRUE : Boolean.FALSE;
      case ConfigPackageImpl.COLUMN__PROPERTY_NAME:
        return getPropertyName();
      case ConfigPackageImpl.COLUMN__TABLE:
        return getTable();
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
      case ConfigPackageImpl.COLUMN__COLLISION:
        setCollision(((Boolean)newValue).booleanValue());
        return;
      case ConfigPackageImpl.COLUMN__COLUMN_TYPE:
        setColumnType((String)newValue);
        return;
      case ConfigPackageImpl.COLUMN__CONVERTER_CLASS_NAME:
        setConverterClassName((String)newValue);
        return;
      case ConfigPackageImpl.COLUMN__GENERATED:
        setGenerated(((Boolean)newValue).booleanValue());
        return;
      case ConfigPackageImpl.COLUMN__NAME:
        setName((String)newValue);
        return;
      case ConfigPackageImpl.COLUMN__PRIMARY_KEY:
        setPrimaryKey(((Boolean)newValue).booleanValue());
        return;
      case ConfigPackageImpl.COLUMN__PROPERTY_NAME:
        setPropertyName((String)newValue);
        return;
      case ConfigPackageImpl.COLUMN__TABLE:
        setTable((String)newValue);
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
      case ConfigPackageImpl.COLUMN__COLLISION:
        unsetCollision();
        return;
      case ConfigPackageImpl.COLUMN__COLUMN_TYPE:
        setColumnType(COLUMN_TYPE_EDEFAULT);
        return;
      case ConfigPackageImpl.COLUMN__CONVERTER_CLASS_NAME:
        setConverterClassName(CONVERTER_CLASS_NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.COLUMN__GENERATED:
        unsetGenerated();
        return;
      case ConfigPackageImpl.COLUMN__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.COLUMN__PRIMARY_KEY:
        unsetPrimaryKey();
        return;
      case ConfigPackageImpl.COLUMN__PROPERTY_NAME:
        setPropertyName(PROPERTY_NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.COLUMN__TABLE:
        setTable(TABLE_EDEFAULT);
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
      case ConfigPackageImpl.COLUMN__COLLISION:
        return isSetCollision();
      case ConfigPackageImpl.COLUMN__COLUMN_TYPE:
        return COLUMN_TYPE_EDEFAULT == null ? columnType != null : !COLUMN_TYPE_EDEFAULT.equals(columnType);
      case ConfigPackageImpl.COLUMN__CONVERTER_CLASS_NAME:
        return CONVERTER_CLASS_NAME_EDEFAULT == null ? converterClassName != null : !CONVERTER_CLASS_NAME_EDEFAULT.equals(converterClassName);
      case ConfigPackageImpl.COLUMN__GENERATED:
        return isSetGenerated();
      case ConfigPackageImpl.COLUMN__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ConfigPackageImpl.COLUMN__PRIMARY_KEY:
        return isSetPrimaryKey();
      case ConfigPackageImpl.COLUMN__PROPERTY_NAME:
        return PROPERTY_NAME_EDEFAULT == null ? propertyName != null : !PROPERTY_NAME_EDEFAULT.equals(propertyName);
      case ConfigPackageImpl.COLUMN__TABLE:
        return TABLE_EDEFAULT == null ? table != null : !TABLE_EDEFAULT.equals(table);
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
    result.append(" (collision: ");
    if (collisionESet) result.append(collision); else result.append("<unset>");
    result.append(", columnType: ");
    result.append(columnType);
    result.append(", converterClassName: ");
    result.append(converterClassName);
    result.append(", generated: ");
    if (generatedESet) result.append(generated); else result.append("<unset>");
    result.append(", name: ");
    result.append(name);
    result.append(", primaryKey: ");
    if (primaryKeyESet) result.append(primaryKey); else result.append("<unset>");
    result.append(", propertyName: ");
    result.append(propertyName);
    result.append(", table: ");
    result.append(table);
    result.append(')');
    return result.toString();
  }

} //ColumnImpl
