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
 * A representation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getSQL <em>SQL</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getKind <em>Kind</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getConfig <em>Config</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Command#getResultDescriptor <em>Result Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand()
 * @model
 * @generated
 */
public interface Command extends EObject {
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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand_Name()
	 * @model
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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand_SQL()
	 * @model
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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand_Kind()
	 * @model
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
	 * Returns the value of the '<em><b>Config</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Config#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config</em>' container reference.
	 * @see #setConfig(Config)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand_Config()
	 * @see org.apache.tuscany.das.rdb.config.Config#getCommand
	 * @model opposite="Command"
	 * @generated
	 */
	Config getConfig();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Command#getConfig <em>Config</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config</em>' container reference.
	 * @see #getConfig()
	 * @generated
	 */
	void setConfig(Config value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.Parameter}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Parameter#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand_Parameter()
	 * @see org.apache.tuscany.das.rdb.config.Parameter#getCommand
	 * @model type="org.apache.tuscany.das.rdb.config.Parameter" opposite="Command" containment="true"
	 * @generated
	 */
	EList getParameter();

	/**
	 * Returns the value of the '<em><b>Result Descriptor</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.config.ResultDescriptor}.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Descriptor</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Descriptor</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getCommand_ResultDescriptor()
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommand
	 * @model type="org.apache.tuscany.das.rdb.config.ResultDescriptor" opposite="Command" containment="true"
	 * @generated
	 */
	EList getResultDescriptor();

} // Command
