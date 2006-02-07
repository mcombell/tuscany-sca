/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getCommand <em>Command</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getConnectionProperties <em>Connection Properties</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getTable <em>Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface Config
{
  /**
   * Returns the value of the '<em><b>Command</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Command}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Command</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Command</em>' containment reference list.
   * @generated
   */
  List getCommand();

  /**
   * Returns the value of the '<em><b>Connection Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Connection Properties</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Connection Properties</em>' containment reference.
   * @see #setConnectionProperties(ConnectionProperties)
   * @generated
   */
  ConnectionProperties getConnectionProperties();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Config#getConnectionProperties <em>Connection Properties</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Connection Properties</em>' containment reference.
   * @see #getConnectionProperties()
   * @generated
   */
  void setConnectionProperties(ConnectionProperties value);

  /**
   * Returns the value of the '<em><b>Table</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Table}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Table</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Table</em>' containment reference list.
   * @generated
   */
  List getTable();

  /**
   * Returns the value of the '<em><b>Relationship</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Relationship}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relationship</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relationship</em>' containment reference list.
   * @generated
   */
  List getRelationship();

  /**
   * Returns the value of the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uri</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uri</em>' attribute.
   * @see #setUri(String)
   * @generated
   */
  String getUri();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Config#getUri <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uri</em>' attribute.
   * @see #getUri()
   * @generated
   */
  void setUri(String value);

} // Config
