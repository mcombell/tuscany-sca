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

import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;
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
 * An implementation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getPrimaryKeyTable <em>Primary Key Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getForeignKeyTable <em>Foreign Key Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#isMany <em>Many</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl#getKeyPair <em>Key Pair</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationshipImpl extends EObjectImpl implements Relationship {
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
	 * The cached value of the '{@link #getKeyPair() <em>Key Pair</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyPair()
	 * @generated
	 * @ordered
	 */
	protected EList keyPair = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getRelationship();
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RELATIONSHIP__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrimaryKeyTable() {
		return primaryKeyTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryKeyTable(String newPrimaryKeyTable) {
		String oldPrimaryKeyTable = primaryKeyTable;
		primaryKeyTable = newPrimaryKeyTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RELATIONSHIP__PRIMARY_KEY_TABLE, oldPrimaryKeyTable, primaryKeyTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getForeignKeyTable() {
		return foreignKeyTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForeignKeyTable(String newForeignKeyTable) {
		String oldForeignKeyTable = foreignKeyTable;
		foreignKeyTable = newForeignKeyTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RELATIONSHIP__FOREIGN_KEY_TABLE, oldForeignKeyTable, foreignKeyTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMany() {
		return many;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMany(boolean newMany) {
		boolean oldMany = many;
		many = newMany;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RELATIONSHIP__MANY, oldMany, many));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Config getConfig() {
		if (eContainerFeatureID != ConfigPackage.RELATIONSHIP__CONFIG) return null;
		return (Config)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfig(Config newConfig) {
		if (newConfig != eContainer || (eContainerFeatureID != ConfigPackage.RELATIONSHIP__CONFIG && newConfig != null)) {
			if (EcoreUtil.isAncestor(this, newConfig))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConfig != null)
				msgs = ((InternalEObject)newConfig).eInverseAdd(this, ConfigPackage.CONFIG__RELATIONSHIP, Config.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newConfig, ConfigPackage.RELATIONSHIP__CONFIG, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RELATIONSHIP__CONFIG, newConfig, newConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getKeyPair() {
		if (keyPair == null) {
			keyPair = new EObjectContainmentWithInverseEList(KeyPair.class, this, ConfigPackage.RELATIONSHIP__KEY_PAIR, ConfigPackage.KEY_PAIR__RELATIONSHIP);
		}
		return keyPair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.RELATIONSHIP__CONFIG:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ConfigPackage.RELATIONSHIP__CONFIG, msgs);
				case ConfigPackage.RELATIONSHIP__KEY_PAIR:
					return ((InternalEList)getKeyPair()).basicAdd(otherEnd, msgs);
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
				case ConfigPackage.RELATIONSHIP__CONFIG:
					return eBasicSetContainer(null, ConfigPackage.RELATIONSHIP__CONFIG, msgs);
				case ConfigPackage.RELATIONSHIP__KEY_PAIR:
					return ((InternalEList)getKeyPair()).basicRemove(otherEnd, msgs);
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
				case ConfigPackage.RELATIONSHIP__CONFIG:
					return eContainer.eInverseRemove(this, ConfigPackage.CONFIG__RELATIONSHIP, Config.class, msgs);
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
			case ConfigPackage.RELATIONSHIP__NAME:
				return getName();
			case ConfigPackage.RELATIONSHIP__PRIMARY_KEY_TABLE:
				return getPrimaryKeyTable();
			case ConfigPackage.RELATIONSHIP__FOREIGN_KEY_TABLE:
				return getForeignKeyTable();
			case ConfigPackage.RELATIONSHIP__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case ConfigPackage.RELATIONSHIP__CONFIG:
				return getConfig();
			case ConfigPackage.RELATIONSHIP__KEY_PAIR:
				return getKeyPair();
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
			case ConfigPackage.RELATIONSHIP__NAME:
				setName((String)newValue);
				return;
			case ConfigPackage.RELATIONSHIP__PRIMARY_KEY_TABLE:
				setPrimaryKeyTable((String)newValue);
				return;
			case ConfigPackage.RELATIONSHIP__FOREIGN_KEY_TABLE:
				setForeignKeyTable((String)newValue);
				return;
			case ConfigPackage.RELATIONSHIP__MANY:
				setMany(((Boolean)newValue).booleanValue());
				return;
			case ConfigPackage.RELATIONSHIP__CONFIG:
				setConfig((Config)newValue);
				return;
			case ConfigPackage.RELATIONSHIP__KEY_PAIR:
				getKeyPair().clear();
				getKeyPair().addAll((Collection)newValue);
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
			case ConfigPackage.RELATIONSHIP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ConfigPackage.RELATIONSHIP__PRIMARY_KEY_TABLE:
				setPrimaryKeyTable(PRIMARY_KEY_TABLE_EDEFAULT);
				return;
			case ConfigPackage.RELATIONSHIP__FOREIGN_KEY_TABLE:
				setForeignKeyTable(FOREIGN_KEY_TABLE_EDEFAULT);
				return;
			case ConfigPackage.RELATIONSHIP__MANY:
				setMany(MANY_EDEFAULT);
				return;
			case ConfigPackage.RELATIONSHIP__CONFIG:
				setConfig((Config)null);
				return;
			case ConfigPackage.RELATIONSHIP__KEY_PAIR:
				getKeyPair().clear();
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
			case ConfigPackage.RELATIONSHIP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ConfigPackage.RELATIONSHIP__PRIMARY_KEY_TABLE:
				return PRIMARY_KEY_TABLE_EDEFAULT == null ? primaryKeyTable != null : !PRIMARY_KEY_TABLE_EDEFAULT.equals(primaryKeyTable);
			case ConfigPackage.RELATIONSHIP__FOREIGN_KEY_TABLE:
				return FOREIGN_KEY_TABLE_EDEFAULT == null ? foreignKeyTable != null : !FOREIGN_KEY_TABLE_EDEFAULT.equals(foreignKeyTable);
			case ConfigPackage.RELATIONSHIP__MANY:
				return many != MANY_EDEFAULT;
			case ConfigPackage.RELATIONSHIP__CONFIG:
				return getConfig() != null;
			case ConfigPackage.RELATIONSHIP__KEY_PAIR:
				return keyPair != null && !keyPair.isEmpty();
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
		result.append(", primaryKeyTable: ");
		result.append(primaryKeyTable);
		result.append(", foreignKeyTable: ");
		result.append(foreignKeyTable);
		result.append(", many: ");
		result.append(many);
		result.append(')');
		return result.toString();
	}

} 
