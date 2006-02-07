/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.ResultDescriptor;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getColumnType <em>Column Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getCommand <em>Command</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getTableName <em>Table Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultDescriptorImpl extends DataObjectImpl implements ResultDescriptor
{
  /**
   * The default value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnName()
   * @generated
   * @ordered
   */
  protected static final String COLUMN_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnName()
   * @generated
   * @ordered
   */
  protected String columnName = COLUMN_NAME_EDEFAULT;

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
   * The default value of the '{@link #getCommand() <em>Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommand()
   * @generated
   * @ordered
   */
  protected static final String COMMAND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCommand() <em>Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommand()
   * @generated
   * @ordered
   */
  protected String command = COMMAND_EDEFAULT;

  /**
   * The default value of the '{@link #getConverter() <em>Converter</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConverter()
   * @generated
   * @ordered
   */
  protected static final String CONVERTER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConverter() <em>Converter</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConverter()
   * @generated
   * @ordered
   */
  protected String converter = CONVERTER_EDEFAULT;

  /**
   * The default value of the '{@link #getTableName() <em>Table Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTableName()
   * @generated
   * @ordered
   */
  protected static final String TABLE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTableName() <em>Table Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTableName()
   * @generated
   * @ordered
   */
  protected String tableName = TABLE_NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResultDescriptorImpl()
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
    return ConfigPackageImpl.Literals.RESULT_DESCRIPTOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getColumnName()
  {
    return columnName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumnName(String newColumnName)
  {
    String oldColumnName = columnName;
    columnName = newColumnName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_NAME, oldColumnName, columnName));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_TYPE, oldColumnType, columnType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCommand()
  {
    return command;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCommand(String newCommand)
  {
    String oldCommand = command;
    command = newCommand;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RESULT_DESCRIPTOR__COMMAND, oldCommand, command));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConverter()
  {
    return converter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConverter(String newConverter)
  {
    String oldConverter = converter;
    converter = newConverter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RESULT_DESCRIPTOR__CONVERTER, oldConverter, converter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTableName()
  {
    return tableName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTableName(String newTableName)
  {
    String oldTableName = tableName;
    tableName = newTableName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.RESULT_DESCRIPTOR__TABLE_NAME, oldTableName, tableName));
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
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_NAME:
        return getColumnName();
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_TYPE:
        return getColumnType();
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COMMAND:
        return getCommand();
      case ConfigPackageImpl.RESULT_DESCRIPTOR__CONVERTER:
        return getConverter();
      case ConfigPackageImpl.RESULT_DESCRIPTOR__TABLE_NAME:
        return getTableName();
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
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_NAME:
        setColumnName((String)newValue);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_TYPE:
        setColumnType((String)newValue);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COMMAND:
        setCommand((String)newValue);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__CONVERTER:
        setConverter((String)newValue);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__TABLE_NAME:
        setTableName((String)newValue);
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
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_NAME:
        setColumnName(COLUMN_NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_TYPE:
        setColumnType(COLUMN_TYPE_EDEFAULT);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COMMAND:
        setCommand(COMMAND_EDEFAULT);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__CONVERTER:
        setConverter(CONVERTER_EDEFAULT);
        return;
      case ConfigPackageImpl.RESULT_DESCRIPTOR__TABLE_NAME:
        setTableName(TABLE_NAME_EDEFAULT);
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
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_NAME:
        return COLUMN_NAME_EDEFAULT == null ? columnName != null : !COLUMN_NAME_EDEFAULT.equals(columnName);
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COLUMN_TYPE:
        return COLUMN_TYPE_EDEFAULT == null ? columnType != null : !COLUMN_TYPE_EDEFAULT.equals(columnType);
      case ConfigPackageImpl.RESULT_DESCRIPTOR__COMMAND:
        return COMMAND_EDEFAULT == null ? command != null : !COMMAND_EDEFAULT.equals(command);
      case ConfigPackageImpl.RESULT_DESCRIPTOR__CONVERTER:
        return CONVERTER_EDEFAULT == null ? converter != null : !CONVERTER_EDEFAULT.equals(converter);
      case ConfigPackageImpl.RESULT_DESCRIPTOR__TABLE_NAME:
        return TABLE_NAME_EDEFAULT == null ? tableName != null : !TABLE_NAME_EDEFAULT.equals(tableName);
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
    result.append(" (columnName: ");
    result.append(columnName);
    result.append(", columnType: ");
    result.append(columnType);
    result.append(", command: ");
    result.append(command);
    result.append(", converter: ");
    result.append(converter);
    result.append(", tableName: ");
    result.append(tableName);
    result.append(')');
    return result.toString();
  }

} //ResultDescriptorImpl
