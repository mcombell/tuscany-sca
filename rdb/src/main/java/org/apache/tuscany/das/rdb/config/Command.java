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
 * A representation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getResultDescriptor <em>Result Descriptor</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getKind <em>Kind</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getSQL <em>SQL</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface Command
{
  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Parameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference list.
   * @generated
   */
  List getParameter();

  /**
   * Returns the value of the '<em><b>Result Descriptor</b></em>' containment reference list.
   * The list contents are of type {@link org.apache.tuscany.das.rdb.config.ResultDescriptor}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result Descriptor</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Result Descriptor</em>' containment reference list.
   * @generated
   */
  List getResultDescriptor();

  /**
   * Returns the value of the '<em><b>Config</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Config</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Config</em>' attribute.
   * @see #setConfig(String)
   * @generated
   */
  String getConfig();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Command#getConfig <em>Config</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Config</em>' attribute.
   * @see #getConfig()
   * @generated
   */
  void setConfig(String value);

  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see #setKind(String)
   * @generated
   */
  String getKind();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Command#getKind <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see #getKind()
   * @generated
   */
  void setKind(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Command#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>SQL</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>SQL</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>SQL</em>' attribute.
   * @see #setSQL(String)
   * @generated
   */
  String getSQL();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Command#getSQL <em>SQL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>SQL</em>' attribute.
   * @see #getSQL()
   * @generated
   */
  void setSQL(String value);

} // Command
