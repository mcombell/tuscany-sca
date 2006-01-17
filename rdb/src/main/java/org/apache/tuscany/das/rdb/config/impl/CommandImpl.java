/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import java.util.Collection;

import org.apache.tuscany.das.rdb.config.Command;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.Parameter;
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
 * An implementation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getSQL <em>SQL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl#getResultDescriptor <em>Result Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommandImpl extends EObjectImpl implements Command {
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
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected EList parameter = null;

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
	protected CommandImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getCommand();
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND__SQL, oldSQL, sql));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Config getConfig() {
		if (eContainerFeatureID != ConfigPackage.COMMAND__CONFIG) return null;
		return (Config)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfig(Config newConfig) {
		if (newConfig != eContainer || (eContainerFeatureID != ConfigPackage.COMMAND__CONFIG && newConfig != null)) {
			if (EcoreUtil.isAncestor(this, newConfig))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConfig != null)
				msgs = ((InternalEObject)newConfig).eInverseAdd(this, ConfigPackage.CONFIG__COMMAND, Config.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newConfig, ConfigPackage.COMMAND__CONFIG, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMMAND__CONFIG, newConfig, newConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameter() {
		if (parameter == null) {
			parameter = new EObjectContainmentWithInverseEList(Parameter.class, this, ConfigPackage.COMMAND__PARAMETER, ConfigPackage.PARAMETER__COMMAND);
		}
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResultDescriptor() {
		if (resultDescriptor == null) {
			resultDescriptor = new EObjectContainmentWithInverseEList(ResultDescriptor.class, this, ConfigPackage.COMMAND__RESULT_DESCRIPTOR, ConfigPackage.RESULT_DESCRIPTOR__COMMAND);
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
				case ConfigPackage.COMMAND__CONFIG:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ConfigPackage.COMMAND__CONFIG, msgs);
				case ConfigPackage.COMMAND__PARAMETER:
					return ((InternalEList)getParameter()).basicAdd(otherEnd, msgs);
				case ConfigPackage.COMMAND__RESULT_DESCRIPTOR:
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
				case ConfigPackage.COMMAND__CONFIG:
					return eBasicSetContainer(null, ConfigPackage.COMMAND__CONFIG, msgs);
				case ConfigPackage.COMMAND__PARAMETER:
					return ((InternalEList)getParameter()).basicRemove(otherEnd, msgs);
				case ConfigPackage.COMMAND__RESULT_DESCRIPTOR:
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
				case ConfigPackage.COMMAND__CONFIG:
					return eContainer.eInverseRemove(this, ConfigPackage.CONFIG__COMMAND, Config.class, msgs);
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
			case ConfigPackage.COMMAND__NAME:
				return getName();
			case ConfigPackage.COMMAND__SQL:
				return getSQL();
			case ConfigPackage.COMMAND__KIND:
				return getKind();
			case ConfigPackage.COMMAND__CONFIG:
				return getConfig();
			case ConfigPackage.COMMAND__PARAMETER:
				return getParameter();
			case ConfigPackage.COMMAND__RESULT_DESCRIPTOR:
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
			case ConfigPackage.COMMAND__NAME:
				setName((String)newValue);
				return;
			case ConfigPackage.COMMAND__SQL:
				setSQL((String)newValue);
				return;
			case ConfigPackage.COMMAND__KIND:
				setKind((String)newValue);
				return;
			case ConfigPackage.COMMAND__CONFIG:
				setConfig((Config)newValue);
				return;
			case ConfigPackage.COMMAND__PARAMETER:
				getParameter().clear();
				getParameter().addAll((Collection)newValue);
				return;
			case ConfigPackage.COMMAND__RESULT_DESCRIPTOR:
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
			case ConfigPackage.COMMAND__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ConfigPackage.COMMAND__SQL:
				setSQL(SQL_EDEFAULT);
				return;
			case ConfigPackage.COMMAND__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case ConfigPackage.COMMAND__CONFIG:
				setConfig((Config)null);
				return;
			case ConfigPackage.COMMAND__PARAMETER:
				getParameter().clear();
				return;
			case ConfigPackage.COMMAND__RESULT_DESCRIPTOR:
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
			case ConfigPackage.COMMAND__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ConfigPackage.COMMAND__SQL:
				return SQL_EDEFAULT == null ? sql != null : !SQL_EDEFAULT.equals(sql);
			case ConfigPackage.COMMAND__KIND:
				return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
			case ConfigPackage.COMMAND__CONFIG:
				return getConfig() != null;
			case ConfigPackage.COMMAND__PARAMETER:
				return parameter != null && !parameter.isEmpty();
			case ConfigPackage.COMMAND__RESULT_DESCRIPTOR:
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

} //CommandImpl
