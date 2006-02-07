/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.ConnectionProperties;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverClassName <em>Driver Class Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverPassword <em>Driver Password</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverURL <em>Driver URL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverUserName <em>Driver User Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionPropertiesImpl extends DataObjectImpl implements ConnectionProperties
{
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
   * The default value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSource()
   * @generated
   * @ordered
   */
  protected static final String DATA_SOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSource()
   * @generated
   * @ordered
   */
  protected String dataSource = DATA_SOURCE_EDEFAULT;

  /**
   * The default value of the '{@link #getDriverClassName() <em>Driver Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverClassName()
   * @generated
   * @ordered
   */
  protected static final String DRIVER_CLASS_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDriverClassName() <em>Driver Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverClassName()
   * @generated
   * @ordered
   */
  protected String driverClassName = DRIVER_CLASS_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getDriverPassword() <em>Driver Password</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverPassword()
   * @generated
   * @ordered
   */
  protected static final String DRIVER_PASSWORD_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDriverPassword() <em>Driver Password</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverPassword()
   * @generated
   * @ordered
   */
  protected String driverPassword = DRIVER_PASSWORD_EDEFAULT;

  /**
   * The default value of the '{@link #getDriverURL() <em>Driver URL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverURL()
   * @generated
   * @ordered
   */
  protected static final String DRIVER_URL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDriverURL() <em>Driver URL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverURL()
   * @generated
   * @ordered
   */
  protected String driverURL = DRIVER_URL_EDEFAULT;

  /**
   * The default value of the '{@link #getDriverUserName() <em>Driver User Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverUserName()
   * @generated
   * @ordered
   */
  protected static final String DRIVER_USER_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDriverUserName() <em>Driver User Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDriverUserName()
   * @generated
   * @ordered
   */
  protected String driverUserName = DRIVER_USER_NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConnectionPropertiesImpl()
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
    return ConfigPackageImpl.Literals.CONNECTION_PROPERTIES;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONNECTION_PROPERTIES__CONFIG, oldConfig, config));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSource()
  {
    return dataSource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSource(String newDataSource)
  {
    String oldDataSource = dataSource;
    dataSource = newDataSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONNECTION_PROPERTIES__DATA_SOURCE, oldDataSource, dataSource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDriverClassName()
  {
    return driverClassName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDriverClassName(String newDriverClassName)
  {
    String oldDriverClassName = driverClassName;
    driverClassName = newDriverClassName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME, oldDriverClassName, driverClassName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDriverPassword()
  {
    return driverPassword;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDriverPassword(String newDriverPassword)
  {
    String oldDriverPassword = driverPassword;
    driverPassword = newDriverPassword;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_PASSWORD, oldDriverPassword, driverPassword));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDriverURL()
  {
    return driverURL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDriverURL(String newDriverURL)
  {
    String oldDriverURL = driverURL;
    driverURL = newDriverURL;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_URL, oldDriverURL, driverURL));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDriverUserName()
  {
    return driverUserName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDriverUserName(String newDriverUserName)
  {
    String oldDriverUserName = driverUserName;
    driverUserName = newDriverUserName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_USER_NAME, oldDriverUserName, driverUserName));
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
      case ConfigPackageImpl.CONNECTION_PROPERTIES__CONFIG:
        return getConfig();
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DATA_SOURCE:
        return getDataSource();
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
        return getDriverClassName();
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
        return getDriverPassword();
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_URL:
        return getDriverURL();
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
        return getDriverUserName();
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
      case ConfigPackageImpl.CONNECTION_PROPERTIES__CONFIG:
        setConfig((String)newValue);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DATA_SOURCE:
        setDataSource((String)newValue);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
        setDriverClassName((String)newValue);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
        setDriverPassword((String)newValue);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_URL:
        setDriverURL((String)newValue);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
        setDriverUserName((String)newValue);
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
      case ConfigPackageImpl.CONNECTION_PROPERTIES__CONFIG:
        setConfig(CONFIG_EDEFAULT);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DATA_SOURCE:
        setDataSource(DATA_SOURCE_EDEFAULT);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
        setDriverClassName(DRIVER_CLASS_NAME_EDEFAULT);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
        setDriverPassword(DRIVER_PASSWORD_EDEFAULT);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_URL:
        setDriverURL(DRIVER_URL_EDEFAULT);
        return;
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
        setDriverUserName(DRIVER_USER_NAME_EDEFAULT);
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
      case ConfigPackageImpl.CONNECTION_PROPERTIES__CONFIG:
        return CONFIG_EDEFAULT == null ? config != null : !CONFIG_EDEFAULT.equals(config);
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DATA_SOURCE:
        return DATA_SOURCE_EDEFAULT == null ? dataSource != null : !DATA_SOURCE_EDEFAULT.equals(dataSource);
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
        return DRIVER_CLASS_NAME_EDEFAULT == null ? driverClassName != null : !DRIVER_CLASS_NAME_EDEFAULT.equals(driverClassName);
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
        return DRIVER_PASSWORD_EDEFAULT == null ? driverPassword != null : !DRIVER_PASSWORD_EDEFAULT.equals(driverPassword);
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_URL:
        return DRIVER_URL_EDEFAULT == null ? driverURL != null : !DRIVER_URL_EDEFAULT.equals(driverURL);
      case ConfigPackageImpl.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
        return DRIVER_USER_NAME_EDEFAULT == null ? driverUserName != null : !DRIVER_USER_NAME_EDEFAULT.equals(driverUserName);
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
    result.append(", dataSource: ");
    result.append(dataSource);
    result.append(", driverClassName: ");
    result.append(driverClassName);
    result.append(", driverPassword: ");
    result.append(driverPassword);
    result.append(", driverURL: ");
    result.append(driverURL);
    result.append(", driverUserName: ");
    result.append(driverUserName);
    result.append(')');
    return result.toString();
  }

} //ConnectionPropertiesImpl
