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
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverClassName <em>Driver Class Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverURL <em>Driver URL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverPassword <em>Driver Password</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getDriverUserName <em>Driver User Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl#getConfig <em>Config</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionPropertiesImpl extends EObjectImpl implements ConnectionProperties {
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
	protected ConnectionPropertiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getConnectionProperties();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataSource(String newDataSource) {
		String oldDataSource = dataSource;
		dataSource = newDataSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONNECTION_PROPERTIES__DATA_SOURCE, oldDataSource, dataSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriverClassName(String newDriverClassName) {
		String oldDriverClassName = driverClassName;
		driverClassName = newDriverClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME, oldDriverClassName, driverClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDriverURL() {
		return driverURL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriverURL(String newDriverURL) {
		String oldDriverURL = driverURL;
		driverURL = newDriverURL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONNECTION_PROPERTIES__DRIVER_URL, oldDriverURL, driverURL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDriverPassword() {
		return driverPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriverPassword(String newDriverPassword) {
		String oldDriverPassword = driverPassword;
		driverPassword = newDriverPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONNECTION_PROPERTIES__DRIVER_PASSWORD, oldDriverPassword, driverPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDriverUserName() {
		return driverUserName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriverUserName(String newDriverUserName) {
		String oldDriverUserName = driverUserName;
		driverUserName = newDriverUserName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONNECTION_PROPERTIES__DRIVER_USER_NAME, oldDriverUserName, driverUserName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Config getConfig() {
		if (eContainerFeatureID != ConfigPackage.CONNECTION_PROPERTIES__CONFIG) return null;
		return (Config)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfig(Config newConfig) {
		if (newConfig != eContainer || (eContainerFeatureID != ConfigPackage.CONNECTION_PROPERTIES__CONFIG && newConfig != null)) {
			if (EcoreUtil.isAncestor(this, newConfig))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConfig != null)
				msgs = ((InternalEObject)newConfig).eInverseAdd(this, ConfigPackage.CONFIG__CONNECTION_PROPERTIES, Config.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newConfig, ConfigPackage.CONNECTION_PROPERTIES__CONFIG, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONNECTION_PROPERTIES__CONFIG, newConfig, newConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ConfigPackage.CONNECTION_PROPERTIES__CONFIG, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
					return eBasicSetContainer(null, ConfigPackage.CONNECTION_PROPERTIES__CONFIG, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
					return eContainer.eInverseRemove(this, ConfigPackage.CONFIG__CONNECTION_PROPERTIES, Config.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ConfigPackage.CONNECTION_PROPERTIES__DATA_SOURCE:
				return getDataSource();
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
				return getDriverClassName();
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_URL:
				return getDriverURL();
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
				return getDriverPassword();
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
				return getDriverUserName();
			case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
				return getConfig();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ConfigPackage.CONNECTION_PROPERTIES__DATA_SOURCE:
				setDataSource((String)newValue);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
				setDriverClassName((String)newValue);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_URL:
				setDriverURL((String)newValue);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
				setDriverPassword((String)newValue);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
				setDriverUserName((String)newValue);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
				setConfig((Config)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ConfigPackage.CONNECTION_PROPERTIES__DATA_SOURCE:
				setDataSource(DATA_SOURCE_EDEFAULT);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
				setDriverClassName(DRIVER_CLASS_NAME_EDEFAULT);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_URL:
				setDriverURL(DRIVER_URL_EDEFAULT);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
				setDriverPassword(DRIVER_PASSWORD_EDEFAULT);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
				setDriverUserName(DRIVER_USER_NAME_EDEFAULT);
				return;
			case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
				setConfig((Config)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ConfigPackage.CONNECTION_PROPERTIES__DATA_SOURCE:
				return DATA_SOURCE_EDEFAULT == null ? dataSource != null : !DATA_SOURCE_EDEFAULT.equals(dataSource);
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_CLASS_NAME:
				return DRIVER_CLASS_NAME_EDEFAULT == null ? driverClassName != null : !DRIVER_CLASS_NAME_EDEFAULT.equals(driverClassName);
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_URL:
				return DRIVER_URL_EDEFAULT == null ? driverURL != null : !DRIVER_URL_EDEFAULT.equals(driverURL);
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_PASSWORD:
				return DRIVER_PASSWORD_EDEFAULT == null ? driverPassword != null : !DRIVER_PASSWORD_EDEFAULT.equals(driverPassword);
			case ConfigPackage.CONNECTION_PROPERTIES__DRIVER_USER_NAME:
				return DRIVER_USER_NAME_EDEFAULT == null ? driverUserName != null : !DRIVER_USER_NAME_EDEFAULT.equals(driverUserName);
			case ConfigPackage.CONNECTION_PROPERTIES__CONFIG:
				return getConfig() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dataSource: ");
		result.append(dataSource);
		result.append(", driverClassName: ");
		result.append(driverClassName);
		result.append(", driverURL: ");
		result.append(driverURL);
		result.append(", driverPassword: ");
		result.append(driverPassword);
		result.append(", driverUserName: ");
		result.append(driverUserName);
		result.append(')');
		return result.toString();
	}

} //ConnectionPropertiesImpl
