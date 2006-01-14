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
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.CommandConfig;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.ParameterConfig;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.Table;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigFactoryImpl extends EFactoryImpl implements ConfigFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ConfigPackage.CONFIG: return createConfig();
			case ConfigPackage.COMMAND_CONFIG: return createCommandConfig();
			case ConfigPackage.CONNECTION_PROPERTIES: return createConnectionProperties();
			case ConfigPackage.PARAMETER_CONFIG: return createParameterConfig();
			case ConfigPackage.RELATIONSHIP: return createRelationship();
			case ConfigPackage.TABLE: return createTable();
			case ConfigPackage.KEY_PAIR: return createKeyPair();
			case ConfigPackage.COLUMN: return createColumn();
			case ConfigPackage.RESULT_DESCRIPTOR: return createResultDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Config createConfig() {
		ConfigImpl config = new ConfigImpl();
		return config;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommandConfig createCommandConfig() {
		CommandConfigImpl commandConfig = new CommandConfigImpl();
		return commandConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionProperties createConnectionProperties() {
		ConnectionPropertiesImpl connectionProperties = new ConnectionPropertiesImpl();
		return connectionProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterConfig createParameterConfig() {
		ParameterConfigImpl parameterConfig = new ParameterConfigImpl();
		return parameterConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relationship createRelationship() {
		RelationshipImpl relationship = new RelationshipImpl();
		return relationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table createTable() {
		TableImpl table = new TableImpl();
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyPair createKeyPair() {
		KeyPairImpl keyPair = new KeyPairImpl();
		return keyPair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column createColumn() {
		ColumnImpl column = new ColumnImpl();
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResultDescriptor createResultDescriptor() {
		ResultDescriptorImpl resultDescriptor = new ResultDescriptorImpl();
		return resultDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigPackage getConfigPackage() {
		return (ConfigPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static ConfigPackage getPackage() {
		return ConfigPackage.eINSTANCE;
	}

} 
