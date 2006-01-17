/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Parameter#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Parameter#getType <em>Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Parameter#getCommand <em>Command</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends EObject {
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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getParameter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Parameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getParameter_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Parameter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Command</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Command#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command</em>' container reference.
	 * @see #setCommand(Command)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getParameter_Command()
	 * @see org.apache.tuscany.das.rdb.config.Command#getParameter
	 * @model opposite="Parameter"
	 * @generated
	 */
	Command getCommand();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Parameter#getCommand <em>Command</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command</em>' container reference.
	 * @see #getCommand()
	 * @generated
	 */
	void setCommand(Command value);

} // Parameter
