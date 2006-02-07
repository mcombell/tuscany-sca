/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.das.rdb.config.Command;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.Table;

import org.apache.tuscany.sdo.impl.DataObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getCommand <em>Command</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getConnectionProperties <em>Connection Properties</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigImpl extends DataObjectImpl implements Config
{
  /**
   * The cached value of the '{@link #getCommand() <em>Command</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommand()
   * @generated
   * @ordered
   */
  protected EList command = null;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConfigImpl()
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
    return ConfigPackageImpl.Literals.CONFIG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getCommand()
  {
    if (command == null)
    {
      command = new EObjectContainmentEList(Command.class, this, ConfigPackageImpl.CONFIG__COMMAND);
    }
    return command;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConnectionProperties getConnectionProperties()
  {
    return connectionProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConnectionProperties(ConnectionProperties newConnectionProperties, NotificationChain msgs)
  {
    ConnectionProperties oldConnectionProperties = connectionProperties;
    connectionProperties = newConnectionProperties;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES, oldConnectionProperties, newConnectionProperties);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConnectionProperties(ConnectionProperties newConnectionProperties)
  {
    if (newConnectionProperties != connectionProperties)
    {
      NotificationChain msgs = null;
      if (connectionProperties != null)
        msgs = ((InternalEObject)connectionProperties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES, null, msgs);
      if (newConnectionProperties != null)
        msgs = ((InternalEObject)newConnectionProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES, null, msgs);
      msgs = basicSetConnectionProperties(newConnectionProperties, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES, newConnectionProperties, newConnectionProperties));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getTable()
  {
    if (table == null)
    {
      table = new EObjectContainmentEList(Table.class, this, ConfigPackageImpl.CONFIG__TABLE);
    }
    return table;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getRelationship()
  {
    if (relationship == null)
    {
      relationship = new EObjectContainmentEList(Relationship.class, this, ConfigPackageImpl.CONFIG__RELATIONSHIP);
    }
    return relationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUri()
  {
    return uri;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUri(String newUri)
  {
    String oldUri = uri;
    uri = newUri;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackageImpl.CONFIG__URI, oldUri, uri));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ConfigPackageImpl.CONFIG__COMMAND:
        return ((InternalEList)getCommand()).basicRemove(otherEnd, msgs);
      case ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES:
        return basicSetConnectionProperties(null, msgs);
      case ConfigPackageImpl.CONFIG__TABLE:
        return ((InternalEList)getTable()).basicRemove(otherEnd, msgs);
      case ConfigPackageImpl.CONFIG__RELATIONSHIP:
        return ((InternalEList)getRelationship()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case ConfigPackageImpl.CONFIG__COMMAND:
        return getCommand();
      case ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES:
        return getConnectionProperties();
      case ConfigPackageImpl.CONFIG__TABLE:
        return getTable();
      case ConfigPackageImpl.CONFIG__RELATIONSHIP:
        return getRelationship();
      case ConfigPackageImpl.CONFIG__URI:
        return getUri();
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
      case ConfigPackageImpl.CONFIG__COMMAND:
        getCommand().clear();
        getCommand().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES:
        setConnectionProperties((ConnectionProperties)newValue);
        return;
      case ConfigPackageImpl.CONFIG__TABLE:
        getTable().clear();
        getTable().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.CONFIG__RELATIONSHIP:
        getRelationship().clear();
        getRelationship().addAll((Collection)newValue);
        return;
      case ConfigPackageImpl.CONFIG__URI:
        setUri((String)newValue);
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
      case ConfigPackageImpl.CONFIG__COMMAND:
        getCommand().clear();
        return;
      case ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES:
        setConnectionProperties((ConnectionProperties)null);
        return;
      case ConfigPackageImpl.CONFIG__TABLE:
        getTable().clear();
        return;
      case ConfigPackageImpl.CONFIG__RELATIONSHIP:
        getRelationship().clear();
        return;
      case ConfigPackageImpl.CONFIG__URI:
        setUri(URI_EDEFAULT);
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
      case ConfigPackageImpl.CONFIG__COMMAND:
        return command != null && !command.isEmpty();
      case ConfigPackageImpl.CONFIG__CONNECTION_PROPERTIES:
        return connectionProperties != null;
      case ConfigPackageImpl.CONFIG__TABLE:
        return table != null && !table.isEmpty();
      case ConfigPackageImpl.CONFIG__RELATIONSHIP:
        return relationship != null && !relationship.isEmpty();
      case ConfigPackageImpl.CONFIG__URI:
        return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
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
    result.append(" (uri: ");
    result.append(uri);
    result.append(')');
    return result.toString();
  }

} //ConfigImpl
