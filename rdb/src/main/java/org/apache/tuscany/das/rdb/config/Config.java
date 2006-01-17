/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getUri <em>Uri</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getCommand <em>Command</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getConnectionProperties <em>Connection Properties</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getTable <em>Table</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Config#getRelationship <em>Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConfig()
 * @model
 * @generated
 */
public interface Config extends EObject {
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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConfig_Uri()
	 * @model
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

	/**
	 * Returns the value of the '<em><b>Command</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Command}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Command#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConfig_Command()
	 * @see org.apache.tuscany.das.rdb.config.Command#getConfig
	 * @model type="org.apache.tuscany.das.rdb.config.Command" opposite="Config" containment="true"
	 * @generated
	 */
	EList getCommand();

	/**
	 * Returns the value of the '<em><b>Connection Properties</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Properties</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Properties</em>' containment reference.
	 * @see #setConnectionProperties(ConnectionProperties)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConfig_ConnectionProperties()
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig
	 * @model opposite="Config" containment="true"
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
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Table#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConfig_Table()
	 * @see org.apache.tuscany.das.rdb.config.Table#getConfig
	 * @model type="org.apache.tuscany.das.rdb.config.Table" opposite="Config" containment="true"
	 * @generated
	 */
	EList getTable();

	/**
	 * Returns the value of the '<em><b>Relationship</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Relationship}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Relationship#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getConfig_Relationship()
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getConfig
	 * @model type="org.apache.tuscany.das.rdb.config.Relationship" opposite="Config" containment="true"
	 * @generated
	 */
	EList getRelationship();

} // Config
