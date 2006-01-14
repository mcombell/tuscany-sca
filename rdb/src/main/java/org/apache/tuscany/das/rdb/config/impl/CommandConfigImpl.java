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
import org.apache.tuscany.das.rdb.config.ParameterConfig;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Command Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl#getSQL <em>SQL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl#getParameterConfig <em>Parameter Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl#getResultDescriptor <em>Result Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommandConfigImpl extends EObjectImpl implements CommandConfig {
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
	protected String sql = SQL_EDEFAULT;

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
	 * The cached value of the '{@link #getParameterConfig() <em>Parameter Config</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterConfig()
	 * @generated
	 * @ordered
	 */
	protected EList parameterConfig = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommandConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getCommandConfig();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND_CONFIG__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSQL() {
		return sql;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSQL(String newSQL) {
		String oldSQL = sql;
		sql = newSQL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND_CONFIG__SQL, oldSQL, sql));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(String newKind) {
		String oldKind = kind;
		kind = newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND_CONFIG__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Config getConfig() {
		if (eContainerFeatureID != ConfigPackage.COMMAND_CONFIG__CONFIG) return null;
		return (Config)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfig(Config newConfig) {
		if (newConfig != eContainer || (eContainerFeatureID != ConfigPackage.COMMAND_CONFIG__CONFIG && newConfig != null)) {
			if (EcoreUtil.isAncestor(this, newConfig))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConfig != null)
				msgs = ((InternalEObject)newConfig).eInverseAdd(this, ConfigPackage.CONFIG__COMMAND_CONFIG, Config.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newConfig, ConfigPackage.COMMAND_CONFIG__CONFIG, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND_CONFIG__CONFIG, newConfig, newConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameterConfig() {
		if (parameterConfig == null) {
			parameterConfig = new EObjectContainmentWithInverseEList(ParameterConfig.class, this, ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG, ConfigPackage.PARAMETER_CONFIG__COMMAND_CONFIG);
		}
		return parameterConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResultDescriptor() {
		if (resultDescriptor == null) {
			resultDescriptor = new EObjectContainmentWithInverseEList(ResultDescriptor.class, this, ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR, ConfigPackage.RESULT_DESCRIPTOR__COMMAND_CONFIG);
		}
		return resultDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.COMMAND_CONFIG__CONFIG:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ConfigPackage.COMMAND_CONFIG__CONFIG, msgs);
				case ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG:
					return ((InternalEList)getParameterConfig()).basicAdd(otherEnd, msgs);
				case ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR:
					return ((InternalEList)getResultDescriptor()).basicAdd(otherEnd, msgs);
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
				case ConfigPackage.COMMAND_CONFIG__CONFIG:
					return eBasicSetContainer(null, ConfigPackage.COMMAND_CONFIG__CONFIG, msgs);
				case ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG:
					return ((InternalEList)getParameterConfig()).basicRemove(otherEnd, msgs);
				case ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR:
					return ((InternalEList)getResultDescriptor()).basicRemove(otherEnd, msgs);
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
				case ConfigPackage.COMMAND_CONFIG__CONFIG:
					return eContainer.eInverseRemove(this, ConfigPackage.CONFIG__COMMAND_CONFIG, Config.class, msgs);
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
			case ConfigPackage.COMMAND_CONFIG__NAME:
				return getName();
			case ConfigPackage.COMMAND_CONFIG__SQL:
				return getSQL();
			case ConfigPackage.COMMAND_CONFIG__KIND:
				return getKind();
			case ConfigPackage.COMMAND_CONFIG__CONFIG:
				return getConfig();
			case ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG:
				return getParameterConfig();
			case ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR:
				return getResultDescriptor();
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
			case ConfigPackage.COMMAND_CONFIG__NAME:
				setName((String)newValue);
				return;
			case ConfigPackage.COMMAND_CONFIG__SQL:
				setSQL((String)newValue);
				return;
			case ConfigPackage.COMMAND_CONFIG__KIND:
				setKind((String)newValue);
				return;
			case ConfigPackage.COMMAND_CONFIG__CONFIG:
				setConfig((Config)newValue);
				return;
			case ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG:
				getParameterConfig().clear();
				getParameterConfig().addAll((Collection)newValue);
				return;
			case ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR:
				getResultDescriptor().clear();
				getResultDescriptor().addAll((Collection)newValue);
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
			case ConfigPackage.COMMAND_CONFIG__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ConfigPackage.COMMAND_CONFIG__SQL:
				setSQL(SQL_EDEFAULT);
				return;
			case ConfigPackage.COMMAND_CONFIG__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case ConfigPackage.COMMAND_CONFIG__CONFIG:
				setConfig((Config)null);
				return;
			case ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG:
				getParameterConfig().clear();
				return;
			case ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR:
				getResultDescriptor().clear();
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
			case ConfigPackage.COMMAND_CONFIG__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ConfigPackage.COMMAND_CONFIG__SQL:
				return SQL_EDEFAULT == null ? sql != null : !SQL_EDEFAULT.equals(sql);
			case ConfigPackage.COMMAND_CONFIG__KIND:
				return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
			case ConfigPackage.COMMAND_CONFIG__CONFIG:
				return getConfig() != null;
			case ConfigPackage.COMMAND_CONFIG__PARAMETER_CONFIG:
				return parameterConfig != null && !parameterConfig.isEmpty();
			case ConfigPackage.COMMAND_CONFIG__RESULT_DESCRIPTOR:
				return resultDescriptor != null && !resultDescriptor.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", SQL: ");
		result.append(sql);
		result.append(", kind: ");
		result.append(kind);
		result.append(')');
		return result.toString();
	}

} //CommandConfigImpl
