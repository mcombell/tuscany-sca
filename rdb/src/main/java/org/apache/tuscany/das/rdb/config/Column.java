/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#isCollision <em>Collision</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#getColumnType <em>Column Type</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#getConverterClassName <em>Converter Class Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#isGenerated <em>Generated</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#isPrimaryKey <em>Primary Key</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.config.Column#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface Column
{
  /**
   * Returns the value of the '<em><b>Collision</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Collision</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Collision</em>' attribute.
   * @see #isSetCollision()
   * @see #unsetCollision()
   * @see #setCollision(boolean)
   * @generated
   */
  boolean isCollision();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isCollision <em>Collision</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Collision</em>' attribute.
   * @see #isSetCollision()
   * @see #unsetCollision()
   * @see #isCollision()
   * @generated
   */
  void setCollision(boolean value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isCollision <em>Collision</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetCollision()
   * @see #isCollision()
   * @see #setCollision(boolean)
   * @generated
   */
  void unsetCollision();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isCollision <em>Collision</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Collision</em>' attribute is set.
   * @see #unsetCollision()
   * @see #isCollision()
   * @see #setCollision(boolean)
   * @generated
   */
  boolean isSetCollision();

  /**
   * Returns the value of the '<em><b>Column Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Column Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Column Type</em>' attribute.
   * @see #setColumnType(String)
   * @generated
   */
  String getColumnType();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#getColumnType <em>Column Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column Type</em>' attribute.
   * @see #getColumnType()
   * @generated
   */
  void setColumnType(String value);

  /**
   * Returns the value of the '<em><b>Converter Class Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Converter Class Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Converter Class Name</em>' attribute.
   * @see #setConverterClassName(String)
   * @generated
   */
  String getConverterClassName();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#getConverterClassName <em>Converter Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Converter Class Name</em>' attribute.
   * @see #getConverterClassName()
   * @generated
   */
  void setConverterClassName(String value);

  /**
   * Returns the value of the '<em><b>Generated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Generated</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generated</em>' attribute.
   * @see #isSetGenerated()
   * @see #unsetGenerated()
   * @see #setGenerated(boolean)
   * @generated
   */
  boolean isGenerated();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isGenerated <em>Generated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Generated</em>' attribute.
   * @see #isSetGenerated()
   * @see #unsetGenerated()
   * @see #isGenerated()
   * @generated
   */
  void setGenerated(boolean value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isGenerated <em>Generated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetGenerated()
   * @see #isGenerated()
   * @see #setGenerated(boolean)
   * @generated
   */
  void unsetGenerated();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isGenerated <em>Generated</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Generated</em>' attribute is set.
   * @see #unsetGenerated()
   * @see #isGenerated()
   * @see #setGenerated(boolean)
   * @generated
   */
  boolean isSetGenerated();

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
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Primary Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Primary Key</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primary Key</em>' attribute.
   * @see #isSetPrimaryKey()
   * @see #unsetPrimaryKey()
   * @see #setPrimaryKey(boolean)
   * @generated
   */
  boolean isPrimaryKey();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isPrimaryKey <em>Primary Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Primary Key</em>' attribute.
   * @see #isSetPrimaryKey()
   * @see #unsetPrimaryKey()
   * @see #isPrimaryKey()
   * @generated
   */
  void setPrimaryKey(boolean value);

  /**
   * Unsets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isPrimaryKey <em>Primary Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetPrimaryKey()
   * @see #isPrimaryKey()
   * @see #setPrimaryKey(boolean)
   * @generated
   */
  void unsetPrimaryKey();

  /**
   * Returns whether the value of the '{@link org.apache.tuscany.das.rdb.config.Column#isPrimaryKey <em>Primary Key</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Primary Key</em>' attribute is set.
   * @see #unsetPrimaryKey()
   * @see #isPrimaryKey()
   * @see #setPrimaryKey(boolean)
   * @generated
   */
  boolean isSetPrimaryKey();

  /**
   * Returns the value of the '<em><b>Property Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Name</em>' attribute.
   * @see #setPropertyName(String)
   * @generated
   */
  String getPropertyName();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#getPropertyName <em>Property Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Name</em>' attribute.
   * @see #getPropertyName()
   * @generated
   */
  void setPropertyName(String value);

  /**
   * Returns the value of the '<em><b>Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Table</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Table</em>' attribute.
   * @see #setTable(String)
   * @generated
   */
  String getTable();

  /**
   * Sets the value of the '{@link org.apache.tuscany.das.rdb.config.Column#getTable <em>Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Table</em>' attribute.
   * @see #getTable()
   * @generated
   */
  void setTable(String value);

} // Column
