/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.Command;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;

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
 * An implementation of the model object '<em><b>Result Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getTableName <em>Table Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl#getCommand <em>Command</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultDescriptorImpl extends EObjectImpl implements ResultDescriptor {
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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResultDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.eINSTANCE.getResultDescriptor();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnName(String newColumnName) {
		String oldColumnName = columnName;
		columnName = newColumnName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RESULT_DESCRIPTOR__COLUMN_NAME, oldColumnName, columnName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableName(String newTableName) {
		String oldTableName = tableName;
		tableName = newTableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RESULT_DESCRIPTOR__TABLE_NAME, oldTableName, tableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RESULT_DESCRIPTOR__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConverter() {
		return converter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConverter(String newConverter) {
		String oldConverter = converter;
		converter = newConverter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RESULT_DESCRIPTOR__CONVERTER, oldConverter, converter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Command getCommand() {
		if (eContainerFeatureID != ConfigPackage.RESULT_DESCRIPTOR__COMMAND) return null;
		return (Command)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCommand(Command newCommand) {
		if (newCommand != eContainer || (eContainerFeatureID != ConfigPackage.RESULT_DESCRIPTOR__COMMAND && newCommand != null)) {
			if (EcoreUtil.isAncestor(this, newCommand))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCommand != null)
				msgs = ((InternalEObject)newCommand).eInverseAdd(this, ConfigPackage.COMMAND__RESULT_DESCRIPTOR, Command.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newCommand, ConfigPackage.RESULT_DESCRIPTOR__COMMAND, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RESULT_DESCRIPTOR__COMMAND, newCommand, newCommand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, ConfigPackage.RESULT_DESCRIPTOR__COMMAND, msgs);
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
				case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
					return eBasicSetContainer(null, ConfigPackage.RESULT_DESCRIPTOR__COMMAND, msgs);
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
				case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
					return eContainer.eInverseRemove(this, ConfigPackage.COMMAND__RESULT_DESCRIPTOR, Command.class, msgs);
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
			case ConfigPackage.RESULT_DESCRIPTOR__COLUMN_NAME:
				return getColumnName();
			case ConfigPackage.RESULT_DESCRIPTOR__TABLE_NAME:
				return getTableName();
			case ConfigPackage.RESULT_DESCRIPTOR__TYPE:
				return getType();
			case ConfigPackage.RESULT_DESCRIPTOR__CONVERTER:
				return getConverter();
			case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
				return getCommand();
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
			case ConfigPackage.RESULT_DESCRIPTOR__COLUMN_NAME:
				setColumnName((String)newValue);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__TABLE_NAME:
				setTableName((String)newValue);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__TYPE:
				setType((String)newValue);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__CONVERTER:
				setConverter((String)newValue);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
				setCommand((Command)newValue);
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
			case ConfigPackage.RESULT_DESCRIPTOR__COLUMN_NAME:
				setColumnName(COLUMN_NAME_EDEFAULT);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__TABLE_NAME:
				setTableName(TABLE_NAME_EDEFAULT);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__CONVERTER:
				setConverter(CONVERTER_EDEFAULT);
				return;
			case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
				setCommand((Command)null);
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
			case ConfigPackage.RESULT_DESCRIPTOR__COLUMN_NAME:
				return COLUMN_NAME_EDEFAULT == null ? columnName != null : !COLUMN_NAME_EDEFAULT.equals(columnName);
			case ConfigPackage.RESULT_DESCRIPTOR__TABLE_NAME:
				return TABLE_NAME_EDEFAULT == null ? tableName != null : !TABLE_NAME_EDEFAULT.equals(tableName);
			case ConfigPackage.RESULT_DESCRIPTOR__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ConfigPackage.RESULT_DESCRIPTOR__CONVERTER:
				return CONVERTER_EDEFAULT == null ? converter != null : !CONVERTER_EDEFAULT.equals(converter);
			case ConfigPackage.RESULT_DESCRIPTOR__COMMAND:
				return getCommand() != null;
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
		result.append(" (columnName: ");
		result.append(columnName);
		result.append(", tableName: ");
		result.append(tableName);
		result.append(", type: ");
		result.append(type);
		result.append(", converter: ");
		result.append(converter);
		result.append(')');
		return result.toString();
	}

} //ResultDescriptorImpl
