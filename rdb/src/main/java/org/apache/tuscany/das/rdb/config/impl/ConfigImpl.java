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

import java.util.Collection;

import org.apache.tuscany.das.rdb.config.CommandConfig;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getCommandConfig <em>Command Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getConnectionProperties <em>Connection Properties</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getRelationship <em>Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigImpl extends EObjectImpl implements Config {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCommandConfig() <em>Command Config</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandConfig()
	 * @generated
	 * @ordered
	 */
	protected EList commandConfig = null;

	/**
	 * The cached value of the '{@link #getConnectionProperties() <em>Connection Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionProperties()
	 * @generated
	 * @ordered
	 */
	protected ConnectionProperties connectionProperties = null;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected EList table = null;

	/**
	 * The cached value of the '{@link #getRelationship() <em>Relationship</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationship()
	 * @generated
	 * @ordered
	 */
	protected EList relationship = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getConfig();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCommandConfig() {
		if (commandConfig == null) {
			commandConfig = new EObjectContainmentWithInverseEList(CommandConfig.class, this, ConfigPackage.CONFIG__COMMAND_CONFIG, ConfigPackage.COMMAND_CONFIG__CONFIG);
		}
		return commandConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnectionProperties(ConnectionProperties newConnectionProperties, NotificationChain msgs) {
		ConnectionProperties oldConnectionProperties = connectionProperties;
		connectionProperties = newConnectionProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG__CONNECTION_PROPERTIES, oldConnectionProperties, newConnectionProperties);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionProperties(ConnectionProperties newConnectionProperties) {
		if (newConnectionProperties != connectionProperties) {
			NotificationChain msgs = null;
			if (connectionProperties != null)
				msgs = ((InternalEObject)connectionProperties).eInverseRemove(this, ConfigPackage.CONNECTION_PROPERTIES__CONFIG, ConnectionProperties.class, msgs);
			if (newConnectionProperties != null)
				msgs = ((InternalEObject)newConnectionProperties).eInverseAdd(this, ConfigPackage.CONNECTION_PROPERTIES__CONFIG, ConnectionProperties.class, msgs);
			msgs = basicSetConnectionProperties(newConnectionProperties, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG__CONNECTION_PROPERTIES, newConnectionProperties, newConnectionProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTable() {
		if (table == null) {
			table = new EObjectContainmentWithInverseEList(Table.class, this, ConfigPackage.CONFIG__TABLE, ConfigPackage.TABLE__CONFIG);
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRelationship() {
		if (relationship == null) {
			relationship = new EObjectContainmentWithInverseEList(Relationship.class, this, ConfigPackage.CONFIG__RELATIONSHIP, ConfigPackage.RELATIONSHIP__CONFIG);
		}
		return relationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.CONFIG__COMMAND_CONFIG:
					return ((InternalEList)getCommandConfig()).basicAdd(otherEnd, msgs);
				case ConfigPackage.CONFIG__CONNECTION_PROPERTIES:
					if (connectionProperties != null)
						msgs = ((InternalEObject)connectionProperties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.CONFIG__CONNECTION_PROPERTIES, null, msgs);
					return basicSetConnectionProperties((ConnectionProperties)otherEnd, msgs);
				case ConfigPackage.CONFIG__TABLE:
					return ((InternalEList)getTable()).basicAdd(otherEnd, msgs);
				case ConfigPackage.CONFIG__RELATIONSHIP:
					return ((InternalEList)getRelationship()).basicAdd(otherEnd, msgs);
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
				case ConfigPackage.CONFIG__COMMAND_CONFIG:
					return ((InternalEList)getCommandConfig()).basicRemove(otherEnd, msgs);
				case ConfigPackage.CONFIG__CONNECTION_PROPERTIES:
					return basicSetConnectionProperties(null, msgs);
				case ConfigPackage.CONFIG__TABLE:
					return ((InternalEList)getTable()).basicRemove(otherEnd, msgs);
				case ConfigPackage.CONFIG__RELATIONSHIP:
					return ((InternalEList)getRelationship()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ConfigPackage.CONFIG__URI:
				return getUri();
			case ConfigPackage.CONFIG__COMMAND_CONFIG:
				return getCommandConfig();
			case ConfigPackage.CONFIG__CONNECTION_PROPERTIES:
				return getConnectionProperties();
			case ConfigPackage.CONFIG__TABLE:
				return getTable();
			case ConfigPackage.CONFIG__RELATIONSHIP:
				return getRelationship();
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
			case ConfigPackage.CONFIG__URI:
				setUri((String)newValue);
				return;
			case ConfigPackage.CONFIG__COMMAND_CONFIG:
				getCommandConfig().clear();
				getCommandConfig().addAll((Collection)newValue);
				return;
			case ConfigPackage.CONFIG__CONNECTION_PROPERTIES:
				setConnectionProperties((ConnectionProperties)newValue);
				return;
			case ConfigPackage.CONFIG__TABLE:
				getTable().clear();
				getTable().addAll((Collection)newValue);
				return;
			case ConfigPackage.CONFIG__RELATIONSHIP:
				getRelationship().clear();
				getRelationship().addAll((Collection)newValue);
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
			case ConfigPackage.CONFIG__URI:
				setUri(URI_EDEFAULT);
				return;
			case ConfigPackage.CONFIG__COMMAND_CONFIG:
				getCommandConfig().clear();
				return;
			case ConfigPackage.CONFIG__CONNECTION_PROPERTIES:
				setConnectionProperties((ConnectionProperties)null);
				return;
			case ConfigPackage.CONFIG__TABLE:
				getTable().clear();
				return;
			case ConfigPackage.CONFIG__RELATIONSHIP:
				getRelationship().clear();
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
			case ConfigPackage.CONFIG__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ConfigPackage.CONFIG__COMMAND_CONFIG:
				return commandConfig != null && !commandConfig.isEmpty();
			case ConfigPackage.CONFIG__CONNECTION_PROPERTIES:
				return connectionProperties != null;
			case ConfigPackage.CONFIG__TABLE:
				return table != null && !table.isEmpty();
			case ConfigPackage.CONFIG__RELATIONSHIP:
				return relationship != null && !relationship.isEmpty();
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
		result.append(" (uri: ");
		result.append(uri);
		result.append(')');
		return result.toString();
	}

} 
