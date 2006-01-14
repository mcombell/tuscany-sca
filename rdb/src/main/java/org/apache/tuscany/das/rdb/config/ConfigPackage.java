/**
*
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package org.apache.tuscany.das.rdb.config;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.apache.tuscany.das.rdb.config.ConfigFactory
 * @model kind="package"
 * @generated
 */
public interface ConfigPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "config";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org.apache.tuscany.das.rdb/config.xsd";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.apache.tuscany.das.rdb.config";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConfigPackage eINSTANCE = org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl <em>Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getConfig()
	 * @generated
	 */
	int CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__URI = 0;

	/**
	 * The feature id for the '<em><b>Command Config</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__COMMAND_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Connection Properties</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__CONNECTION_PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__TABLE = 3;

	/**
	 * The feature id for the '<em><b>Relationship</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__RELATIONSHIP = 4;

	/**
	 * The number of structural features of the the '<em>Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl <em>Command Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.CommandConfigImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getCommandConfig()
	 * @generated
	 */
	int COMMAND_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG__NAME = 0;

	/**
	 * The feature id for the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG__SQL = 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG__KIND = 2;

	/**
	 * The feature id for the '<em><b>Config</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG__CONFIG = 3;

	/**
	 * The feature id for the '<em><b>Parameter Config</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG__PARAMETER_CONFIG = 4;

	/**
	 * The feature id for the '<em><b>Result Descriptor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG__RESULT_DESCRIPTOR = 5;

	/**
	 * The number of structural features of the the '<em>Command Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_CONFIG_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl <em>Connection Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getConnectionProperties()
	 * @generated
	 */
	int CONNECTION_PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES__DATA_SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Driver Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES__DRIVER_CLASS_NAME = 1;

	/**
	 * The feature id for the '<em><b>Driver URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES__DRIVER_URL = 2;

	/**
	 * The feature id for the '<em><b>Driver Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES__DRIVER_PASSWORD = 3;

	/**
	 * The feature id for the '<em><b>Driver User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES__DRIVER_USER_NAME = 4;

	/**
	 * The feature id for the '<em><b>Config</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES__CONFIG = 5;

	/**
	 * The number of structural features of the the '<em>Connection Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROPERTIES_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ParameterConfigImpl <em>Parameter Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.ParameterConfigImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getParameterConfig()
	 * @generated
	 */
	int PARAMETER_CONFIG = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CONFIG__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CONFIG__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Command Config</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CONFIG__COMMAND_CONFIG = 2;

	/**
	 * The number of structural features of the the '<em>Parameter Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl <em>Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.RelationshipImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getRelationship()
	 * @generated
	 */
	int RELATIONSHIP = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Primary Key Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__PRIMARY_KEY_TABLE = 1;

	/**
	 * The feature id for the '<em><b>Foreign Key Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__FOREIGN_KEY_TABLE = 2;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__MANY = 3;

	/**
	 * The feature id for the '<em><b>Config</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__CONFIG = 4;

	/**
	 * The feature id for the '<em><b>Key Pair</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__KEY_PAIR = 5;

	/**
	 * The number of structural features of the the '<em>Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.TableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.TableImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getTable()
	 * @generated
	 */
	int TABLE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__PROPERTY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Create</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__CREATE = 2;

	/**
	 * The feature id for the '<em><b>Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__UPDATE = 3;

	/**
	 * The feature id for the '<em><b>Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__DELETE = 4;

	/**
	 * The feature id for the '<em><b>Config</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__CONFIG = 5;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__COLUMN = 6;

	/**
	 * The number of structural features of the the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl <em>Key Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.KeyPairImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getKeyPair()
	 * @generated
	 */
	int KEY_PAIR = 6;

	/**
	 * The feature id for the '<em><b>Primary Key Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__PRIMARY_KEY_COLUMN = 0;

	/**
	 * The feature id for the '<em><b>Foreign Key Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__FOREIGN_KEY_COLUMN = 1;

	/**
	 * The feature id for the '<em><b>Relationship</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__RELATIONSHIP = 2;

	/**
	 * The number of structural features of the the '<em>Key Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.ColumnImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getColumn()
	 * @generated
	 */
	int COLUMN = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NAME = 0;

	/**
	 * The feature id for the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__PROPERTY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Converter Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__CONVERTER_CLASS_NAME = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Primary Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__PRIMARY_KEY = 4;

	/**
	 * The feature id for the '<em><b>Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__GENERATED = 5;

	/**
	 * The feature id for the '<em><b>Collision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__COLLISION = 6;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__TABLE = 7;

	/**
	 * The number of structural features of the the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl <em>Result Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl
	 * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getResultDescriptor()
	 * @generated
	 */
	int RESULT_DESCRIPTOR = 8;

	/**
	 * The feature id for the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_DESCRIPTOR__COLUMN_NAME = 0;

	/**
	 * The feature id for the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_DESCRIPTOR__TABLE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_DESCRIPTOR__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Converter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_DESCRIPTOR__CONVERTER = 3;

	/**
	 * The feature id for the '<em><b>Command Config</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_DESCRIPTOR__COMMAND_CONFIG = 4;

	/**
	 * The number of structural features of the the '<em>Result Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_DESCRIPTOR_FEATURE_COUNT = 5;


	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Config <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Config
	 * @generated
	 */
	EClass getConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Config#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Config#getUri()
	 * @see #getConfig()
	 * @generated
	 */
	EAttribute getConfig_Uri();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Config#getCommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Command Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Config#getCommandConfig()
	 * @see #getConfig()
	 * @generated
	 */
	EReference getConfig_CommandConfig();

	/**
	 * Returns the meta object for the containment reference '{@link org.apache.tuscany.das.rdb.config.Config#getConnectionProperties <em>Connection Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connection Properties</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Config#getConnectionProperties()
	 * @see #getConfig()
	 * @generated
	 */
	EReference getConfig_ConnectionProperties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Config#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Table</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Config#getTable()
	 * @see #getConfig()
	 * @generated
	 */
	EReference getConfig_Table();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Config#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relationship</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Config#getRelationship()
	 * @see #getConfig()
	 * @generated
	 */
	EReference getConfig_Relationship();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.CommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig
	 * @generated
	 */
	EClass getCommandConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getName()
	 * @see #getCommandConfig()
	 * @generated
	 */
	EAttribute getCommandConfig_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getSQL <em>SQL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SQL</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getSQL()
	 * @see #getCommandConfig()
	 * @generated
	 */
	EAttribute getCommandConfig_SQL();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getKind()
	 * @see #getCommandConfig()
	 * @generated
	 */
	EAttribute getCommandConfig_Kind();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getConfig()
	 * @see #getCommandConfig()
	 * @generated
	 */
	EReference getCommandConfig_Config();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getParameterConfig <em>Parameter Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getParameterConfig()
	 * @see #getCommandConfig()
	 * @generated
	 */
	EReference getCommandConfig_ParameterConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.CommandConfig#getResultDescriptor <em>Result Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Result Descriptor</em>'.
	 * @see org.apache.tuscany.das.rdb.config.CommandConfig#getResultDescriptor()
	 * @see #getCommandConfig()
	 * @generated
	 */
	EReference getCommandConfig_ResultDescriptor();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties <em>Connection Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Properties</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties
	 * @generated
	 */
	EClass getConnectionProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Source</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDataSource()
	 * @see #getConnectionProperties()
	 * @generated
	 */
	EAttribute getConnectionProperties_DataSource();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverClassName <em>Driver Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Driver Class Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverClassName()
	 * @see #getConnectionProperties()
	 * @generated
	 */
	EAttribute getConnectionProperties_DriverClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverURL <em>Driver URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Driver URL</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverURL()
	 * @see #getConnectionProperties()
	 * @generated
	 */
	EAttribute getConnectionProperties_DriverURL();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverPassword <em>Driver Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Driver Password</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverPassword()
	 * @see #getConnectionProperties()
	 * @generated
	 */
	EAttribute getConnectionProperties_DriverPassword();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverUserName <em>Driver User Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Driver User Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverUserName()
	 * @see #getConnectionProperties()
	 * @generated
	 */
	EAttribute getConnectionProperties_DriverUserName();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig()
	 * @see #getConnectionProperties()
	 * @generated
	 */
	EReference getConnectionProperties_Config();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.ParameterConfig <em>Parameter Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ParameterConfig
	 * @generated
	 */
	EClass getParameterConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ParameterConfig#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ParameterConfig#getName()
	 * @see #getParameterConfig()
	 * @generated
	 */
	EAttribute getParameterConfig_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ParameterConfig#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ParameterConfig#getType()
	 * @see #getParameterConfig()
	 * @generated
	 */
	EAttribute getParameterConfig_Type();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.ParameterConfig#getCommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Command Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ParameterConfig#getCommandConfig()
	 * @see #getParameterConfig()
	 * @generated
	 */
	EReference getParameterConfig_CommandConfig();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Relationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship
	 * @generated
	 */
	EClass getRelationship();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getName()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getPrimaryKeyTable <em>Primary Key Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Key Table</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getPrimaryKeyTable()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_PrimaryKeyTable();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getForeignKeyTable <em>Foreign Key Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Foreign Key Table</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getForeignKeyTable()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_ForeignKeyTable();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#isMany <em>Many</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Many</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship#isMany()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_Many();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.Relationship#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getConfig()
	 * @see #getRelationship()
	 * @generated
	 */
	EReference getRelationship_Config();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Relationship#getKeyPair <em>Key Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Key Pair</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Relationship#getKeyPair()
	 * @see #getRelationship()
	 * @generated
	 */
	EReference getRelationship_KeyPair();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table
	 * @generated
	 */
	EClass getTable();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getName()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getPropertyName()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_PropertyName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getCreate <em>Create</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getCreate()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Create();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getUpdate <em>Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Update</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getUpdate()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Update();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getDelete <em>Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delete</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getDelete()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Delete();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.Table#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getConfig()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Config();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Table#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Column</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Table#getColumn()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Column();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.KeyPair <em>Key Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Pair</em>'.
	 * @see org.apache.tuscany.das.rdb.config.KeyPair
	 * @generated
	 */
	EClass getKeyPair();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.KeyPair#getPrimaryKeyColumn <em>Primary Key Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Key Column</em>'.
	 * @see org.apache.tuscany.das.rdb.config.KeyPair#getPrimaryKeyColumn()
	 * @see #getKeyPair()
	 * @generated
	 */
	EAttribute getKeyPair_PrimaryKeyColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.KeyPair#getForeignKeyColumn <em>Foreign Key Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Foreign Key Column</em>'.
	 * @see org.apache.tuscany.das.rdb.config.KeyPair#getForeignKeyColumn()
	 * @see #getKeyPair()
	 * @generated
	 */
	EAttribute getKeyPair_ForeignKeyColumn();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.KeyPair#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Relationship</em>'.
	 * @see org.apache.tuscany.das.rdb.config.KeyPair#getRelationship()
	 * @see #getKeyPair()
	 * @generated
	 */
	EReference getKeyPair_Relationship();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column
	 * @generated
	 */
	EClass getColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#getName()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#getPropertyName()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_PropertyName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getConverterClassName <em>Converter Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Converter Class Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#getConverterClassName()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_ConverterClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#getType()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#isPrimaryKey <em>Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Key</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#isPrimaryKey()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_PrimaryKey();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#isGenerated <em>Generated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generated</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#isGenerated()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Generated();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#isCollision <em>Collision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collision</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#isCollision()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Collision();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.Column#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.apache.tuscany.das.rdb.config.Column#getTable()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_Table();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor <em>Result Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result Descriptor</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor
	 * @generated
	 */
	EClass getResultDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName <em>Column Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName()
	 * @see #getResultDescriptor()
	 * @generated
	 */
	EAttribute getResultDescriptor_ColumnName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName <em>Table Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Name</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName()
	 * @see #getResultDescriptor()
	 * @generated
	 */
	EAttribute getResultDescriptor_TableName();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getType()
	 * @see #getResultDescriptor()
	 * @generated
	 */
	EAttribute getResultDescriptor_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter <em>Converter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Converter</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter()
	 * @see #getResultDescriptor()
	 * @generated
	 */
	EAttribute getResultDescriptor_Converter();

	/**
	 * Returns the meta object for the container reference '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommandConfig <em>Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Command Config</em>'.
	 * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommandConfig()
	 * @see #getResultDescriptor()
	 * @generated
	 */
	EReference getResultDescriptor_CommandConfig();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConfigFactory getConfigFactory();

} 
