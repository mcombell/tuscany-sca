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

import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;
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
 * An implementation of the model object '<em><b>Key Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl#getPrimaryKeyColumn <em>Primary Key Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl#getForeignKeyColumn <em>Foreign Key Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl#getRelationship <em>Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KeyPairImpl extends EObjectImpl implements KeyPair {
	/**
	 * The default value of the '{@link #getPrimaryKeyColumn() <em>Primary Key Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryKeyColumn()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIMARY_KEY_COLUMN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrimaryKeyColumn() <em>Primary Key Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryKeyColumn()
	 * @generated
	 * @ordered
	 */
	protected String primaryKeyColumn = PRIMARY_KEY_COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #getForeignKeyColumn() <em>Foreign Key Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignKeyColumn()
	 * @generated
	 * @ordered
	 */
	protected static final String FOREIGN_KEY_COLUMN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getForeignKeyColumn() <em>Foreign Key Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignKeyColumn()
	 * @generated
	 * @ordered
	 */
	protected String foreignKeyColumn = FOREIGN_KEY_COLUMN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KeyPairImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getKeyPair();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryKeyColumn(String newPrimaryKeyColumn) {
		String oldPrimaryKeyColumn = primaryKeyColumn;
		primaryKeyColumn = newPrimaryKeyColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.KEY_PAIR__PRIMARY_KEY_COLUMN, oldPrimaryKeyColumn, primaryKeyColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getForeignKeyColumn() {
		return foreignKeyColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForeignKeyColumn(String newForeignKeyColumn) {
		String oldForeignKeyColumn = foreignKeyColumn;
		foreignKeyColumn = newForeignKeyColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.KEY_PAIR__FOREIGN_KEY_COLUMN, oldForeignKeyColumn, foreignKeyColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relationship getRelationship() {
		if (eContainerFeatureID != ConfigPackage.KEY_PAIR__RELATIONSHIP) return null;
		return (Relationship)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelationship(Relationship newRelationship) {
		if (newRelationship != eContainer || (eContainerFeatureID != ConfigPackage.KEY_PAIR__RELATIONSHIP && newRelationship != null)) {
			if (EcoreUtil.isAncestor(this, newRelationship))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRelationship != null)
				msgs = ((InternalEObject)newRelationship).eInverseAdd(this, ConfigPackage.RELATIONSHIP__KEY_PAIR, Relationship.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newRelationship, ConfigPackage.KEY_PAIR__RELATIONSHIP, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.KEY_PAIR__RELATIONSHIP, newRelationship, newRelationship));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.KEY_PAIR__RELATIONSHIP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ConfigPackage.KEY_PAIR__RELATIONSHIP, msgs);
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
				case ConfigPackage.KEY_PAIR__RELATIONSHIP:
					return eBasicSetContainer(null, ConfigPackage.KEY_PAIR__RELATIONSHIP, msgs);
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
				case ConfigPackage.KEY_PAIR__RELATIONSHIP:
					return eContainer.eInverseRemove(this, ConfigPackage.RELATIONSHIP__KEY_PAIR, Relationship.class, msgs);
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
			case ConfigPackage.KEY_PAIR__PRIMARY_KEY_COLUMN:
				return getPrimaryKeyColumn();
			case ConfigPackage.KEY_PAIR__FOREIGN_KEY_COLUMN:
				return getForeignKeyColumn();
			case ConfigPackage.KEY_PAIR__RELATIONSHIP:
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
			case ConfigPackage.KEY_PAIR__PRIMARY_KEY_COLUMN:
				setPrimaryKeyColumn((String)newValue);
				return;
			case ConfigPackage.KEY_PAIR__FOREIGN_KEY_COLUMN:
				setForeignKeyColumn((String)newValue);
				return;
			case ConfigPackage.KEY_PAIR__RELATIONSHIP:
				setRelationship((Relationship)newValue);
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
			case ConfigPackage.KEY_PAIR__PRIMARY_KEY_COLUMN:
				setPrimaryKeyColumn(PRIMARY_KEY_COLUMN_EDEFAULT);
				return;
			case ConfigPackage.KEY_PAIR__FOREIGN_KEY_COLUMN:
				setForeignKeyColumn(FOREIGN_KEY_COLUMN_EDEFAULT);
				return;
			case ConfigPackage.KEY_PAIR__RELATIONSHIP:
				setRelationship((Relationship)null);
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
			case ConfigPackage.KEY_PAIR__PRIMARY_KEY_COLUMN:
				return PRIMARY_KEY_COLUMN_EDEFAULT == null ? primaryKeyColumn != null : !PRIMARY_KEY_COLUMN_EDEFAULT.equals(primaryKeyColumn);
			case ConfigPackage.KEY_PAIR__FOREIGN_KEY_COLUMN:
				return FOREIGN_KEY_COLUMN_EDEFAULT == null ? foreignKeyColumn != null : !FOREIGN_KEY_COLUMN_EDEFAULT.equals(foreignKeyColumn);
			case ConfigPackage.KEY_PAIR__RELATIONSHIP:
				return getRelationship() != null;
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
		result.append(" (primaryKeyColumn: ");
		result.append(primaryKeyColumn);
		result.append(", foreignKeyColumn: ");
		result.append(foreignKeyColumn);
		result.append(')');
		return result.toString();
	}

} 
