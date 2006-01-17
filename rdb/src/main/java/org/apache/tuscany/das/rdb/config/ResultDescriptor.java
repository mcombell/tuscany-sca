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
 * A representation of the model object '<em><b>Result Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName <em>Table Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getType <em>Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommand <em>Command</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor()
 * @model
 * @generated
 */
public interface ResultDescriptor extends EObject {
	/**
	 * Returns the value of the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Name</em>' attribute.
	 * @see #setColumnName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_ColumnName()
	 * @model
	 * @generated
	 */
	String getColumnName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Name</em>' attribute.
	 * @see #getColumnName()
	 * @generated
	 */
	void setColumnName(String value);

	/**
	 * Returns the value of the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Name</em>' attribute.
	 * @see #setTableName(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_TableName()
	 * @model
	 * @generated
	 */
	String getTableName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Name</em>' attribute.
	 * @see #getTableName()
	 * @generated
	 */
	void setTableName(String value);

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
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Converter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Converter</em>' attribute.
	 * @see #setConverter(String)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_Converter()
	 * @model
	 * @generated
	 */
	String getConverter();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter <em>Converter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converter</em>' attribute.
	 * @see #getConverter()
	 * @generated
	 */
	void setConverter(String value);

	/**
	 * Returns the value of the '<em><b>Command</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.apache.tuscany.das.rdb.config.Command#getResultDescriptor <em>Result Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command</em>' container reference.
	 * @see #setCommand(Command)
	 * @see org.apache.tuscany.das.rdb.config.ConfigPackage#getResultDescriptor_Command()
	 * @see org.apache.tuscany.das.rdb.config.Command#getResultDescriptor
	 * @model opposite="ResultDescriptor"
	 * @generated
	 */
	Command getCommand();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommand <em>Command</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command</em>' container reference.
	 * @see #getCommand()
	 * @generated
	 */
	void setCommand(Command value);

} // ResultDescriptor
