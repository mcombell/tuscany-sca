/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.KeyPair;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Key Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl#getForeignKeyColumn <em>Foreign Key Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl#getPrimaryKeyColumn <em>Primary Key Column</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl#getRelationship <em>Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KeyPairImpl extends DataObjectImpl implements KeyPair
{
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
   * The default value of the '{@link #getRelationship() <em>Relationship</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelationship()
   * @generated
   * @ordered
   */
  protected static final String RELATIONSHIP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRelationship() <em>Relationship</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelationship()
   * @generated
   * @ordered
   */
  protected String relationship = RELATIONSHIP_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected KeyPairImpl()
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
    return ConfigPackageImpl.Literals.KEY_PAIR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getForeignKeyColumn()
  {
    return foreignKeyColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForeignKeyColumn(String newForeignKeyColumn)
  {
    String oldForeignKeyColumn = foreignKeyColumn;
    foreignKeyColumn = newForeignKeyColumn;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.KEY_PAIR__FOREIGN_KEY_COLUMN, oldForeignKeyColumn, foreignKeyColumn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPrimaryKeyColumn()
  {
    return primaryKeyColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrimaryKeyColumn(String newPrimaryKeyColumn)
  {
    String oldPrimaryKeyColumn = primaryKeyColumn;
    primaryKeyColumn = newPrimaryKeyColumn;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.KEY_PAIR__PRIMARY_KEY_COLUMN, oldPrimaryKeyColumn, primaryKeyColumn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRelationship()
  {
    return relationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRelationship(String newRelationship)
  {
    String oldRelationship = relationship;
    relationship = newRelationship;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.KEY_PAIR__RELATIONSHIP, oldRelationship, relationship));
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
      case ConfigPackageImpl.KEY_PAIR__FOREIGN_KEY_COLUMN:
        return getForeignKeyColumn();
      case ConfigPackageImpl.KEY_PAIR__PRIMARY_KEY_COLUMN:
        return getPrimaryKeyColumn();
      case ConfigPackageImpl.KEY_PAIR__RELATIONSHIP:
        return getRelationship();
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
      case ConfigPackageImpl.KEY_PAIR__FOREIGN_KEY_COLUMN:
        setForeignKeyColumn((String)newValue);
        return;
      case ConfigPackageImpl.KEY_PAIR__PRIMARY_KEY_COLUMN:
        setPrimaryKeyColumn((String)newValue);
        return;
      case ConfigPackageImpl.KEY_PAIR__RELATIONSHIP:
        setRelationship((String)newValue);
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
      case ConfigPackageImpl.KEY_PAIR__FOREIGN_KEY_COLUMN:
        setForeignKeyColumn(FOREIGN_KEY_COLUMN_EDEFAULT);
        return;
      case ConfigPackageImpl.KEY_PAIR__PRIMARY_KEY_COLUMN:
        setPrimaryKeyColumn(PRIMARY_KEY_COLUMN_EDEFAULT);
        return;
      case ConfigPackageImpl.KEY_PAIR__RELATIONSHIP:
        setRelationship(RELATIONSHIP_EDEFAULT);
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
      case ConfigPackageImpl.KEY_PAIR__FOREIGN_KEY_COLUMN:
        return FOREIGN_KEY_COLUMN_EDEFAULT == null ? foreignKeyColumn != null : !FOREIGN_KEY_COLUMN_EDEFAULT.equals(foreignKeyColumn);
      case ConfigPackageImpl.KEY_PAIR__PRIMARY_KEY_COLUMN:
        return PRIMARY_KEY_COLUMN_EDEFAULT == null ? primaryKeyColumn != null : !PRIMARY_KEY_COLUMN_EDEFAULT.equals(primaryKeyColumn);
      case ConfigPackageImpl.KEY_PAIR__RELATIONSHIP:
        return RELATIONSHIP_EDEFAULT == null ? relationship != null : !RELATIONSHIP_EDEFAULT.equals(relationship);
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
    result.append(" (foreignKeyColumn: ");
    result.append(foreignKeyColumn);
    result.append(", primaryKeyColumn: ");
    result.append(primaryKeyColumn);
    result.append(", relationship: ");
    result.append(relationship);
    result.append(')');
    return result.toString();
  }

} //KeyPairImpl
