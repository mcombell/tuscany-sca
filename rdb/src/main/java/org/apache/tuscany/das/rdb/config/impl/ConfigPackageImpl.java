/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.config.impl;

import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Command;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.ConnectionProperties;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Parameter;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.Table;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

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
 * @generated
 */
public class ConfigPackageImpl extends EPackageImpl
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "config";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "http:///org.apache.tuscany.das.rdb/config.xsd";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "org.apache.tuscany.das.rdb.config";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final ConfigPackageImpl eINSTANCE = org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl.init();

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl <em>Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.ColumnImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getColumn()
   * @generated
   */
  public static final int COLUMN = 0;

  /**
   * The feature id for the '<em><b>Collision</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__COLLISION = 0;

  /**
   * The feature id for the '<em><b>Column Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__COLUMN_TYPE = 1;

  /**
   * The feature id for the '<em><b>Converter Class Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__CONVERTER_CLASS_NAME = 2;

  /**
   * The feature id for the '<em><b>Generated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__GENERATED = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__NAME = 4;

  /**
   * The feature id for the '<em><b>Primary Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__PRIMARY_KEY = 5;

  /**
   * The feature id for the '<em><b>Property Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__PROPERTY_NAME = 6;

  /**
   * The feature id for the '<em><b>Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN__TABLE = 7;

  /**
   * The number of structural features of the '<em>Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COLUMN_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl <em>Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.CommandImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getCommand()
   * @generated
   */
  public static final int COMMAND = 1;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND__PARAMETER = 0;

  /**
   * The feature id for the '<em><b>Result Descriptor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND__RESULT_DESCRIPTOR = 1;

  /**
   * The feature id for the '<em><b>Config</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND__CONFIG = 2;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND__KIND = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND__NAME = 4;

  /**
   * The feature id for the '<em><b>SQL</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND__SQL = 5;

  /**
   * The number of structural features of the '<em>Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMMAND_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl <em>Config</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getConfig()
   * @generated
   */
  public static final int CONFIG = 2;

  /**
   * The feature id for the '<em><b>Command</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONFIG__COMMAND = 0;

  /**
   * The feature id for the '<em><b>Connection Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONFIG__CONNECTION_PROPERTIES = 1;

  /**
   * The feature id for the '<em><b>Table</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONFIG__TABLE = 2;

  /**
   * The feature id for the '<em><b>Relationship</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONFIG__RELATIONSHIP = 3;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONFIG__URI = 4;

  /**
   * The number of structural features of the '<em>Config</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONFIG_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl <em>Connection Properties</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getConnectionProperties()
   * @generated
   */
  public static final int CONNECTION_PROPERTIES = 3;

  /**
   * The feature id for the '<em><b>Config</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES__CONFIG = 0;

  /**
   * The feature id for the '<em><b>Data Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES__DATA_SOURCE = 1;

  /**
   * The feature id for the '<em><b>Driver Class Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES__DRIVER_CLASS_NAME = 2;

  /**
   * The feature id for the '<em><b>Driver Password</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES__DRIVER_PASSWORD = 3;

  /**
   * The feature id for the '<em><b>Driver URL</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES__DRIVER_URL = 4;

  /**
   * The feature id for the '<em><b>Driver User Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES__DRIVER_USER_NAME = 5;

  /**
   * The number of structural features of the '<em>Connection Properties</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONNECTION_PROPERTIES_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl <em>Key Pair</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.KeyPairImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getKeyPair()
   * @generated
   */
  public static final int KEY_PAIR = 4;

  /**
   * The feature id for the '<em><b>Foreign Key Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int KEY_PAIR__FOREIGN_KEY_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Primary Key Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int KEY_PAIR__PRIMARY_KEY_COLUMN = 1;

  /**
   * The feature id for the '<em><b>Relationship</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int KEY_PAIR__RELATIONSHIP = 2;

  /**
   * The number of structural features of the '<em>Key Pair</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int KEY_PAIR_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.ParameterImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getParameter()
   * @generated
   */
  public static final int PARAMETER = 5;

  /**
   * The feature id for the '<em><b>Column Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__COLUMN_TYPE = 0;

  /**
   * The feature id for the '<em><b>Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__COMMAND = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__NAME = 2;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl <em>Relationship</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.RelationshipImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getRelationship()
   * @generated
   */
  public static final int RELATIONSHIP = 6;

  /**
   * The feature id for the '<em><b>Key Pair</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP__KEY_PAIR = 0;

  /**
   * The feature id for the '<em><b>Config</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP__CONFIG = 1;

  /**
   * The feature id for the '<em><b>Foreign Key Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP__FOREIGN_KEY_TABLE = 2;

  /**
   * The feature id for the '<em><b>Many</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP__MANY = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP__NAME = 4;

  /**
   * The feature id for the '<em><b>Primary Key Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP__PRIMARY_KEY_TABLE = 5;

  /**
   * The number of structural features of the '<em>Relationship</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RELATIONSHIP_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl <em>Result Descriptor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getResultDescriptor()
   * @generated
   */
  public static final int RESULT_DESCRIPTOR = 7;

  /**
   * The feature id for the '<em><b>Column Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RESULT_DESCRIPTOR__COLUMN_NAME = 0;

  /**
   * The feature id for the '<em><b>Column Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RESULT_DESCRIPTOR__COLUMN_TYPE = 1;

  /**
   * The feature id for the '<em><b>Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RESULT_DESCRIPTOR__COMMAND = 2;

  /**
   * The feature id for the '<em><b>Converter</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RESULT_DESCRIPTOR__CONVERTER = 3;

  /**
   * The feature id for the '<em><b>Table Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RESULT_DESCRIPTOR__TABLE_NAME = 4;

  /**
   * The number of structural features of the '<em>Result Descriptor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int RESULT_DESCRIPTOR_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.config.impl.TableImpl <em>Table</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.config.impl.TableImpl
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getTable()
   * @generated
   */
  public static final int TABLE = 8;

  /**
   * The feature id for the '<em><b>Column</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__COLUMN = 0;

  /**
   * The feature id for the '<em><b>Config</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__CONFIG = 1;

  /**
   * The feature id for the '<em><b>Create</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__CREATE = 2;

  /**
   * The feature id for the '<em><b>Delete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__DELETE = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__NAME = 4;

  /**
   * The feature id for the '<em><b>Property Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__PROPERTY_NAME = 5;

  /**
   * The feature id for the '<em><b>Update</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE__UPDATE = 6;

  /**
   * The number of structural features of the '<em>Table</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TABLE_FEATURE_COUNT = 7;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass commandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass connectionPropertiesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass keyPairEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass relationshipEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass resultDescriptorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tableEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private ConfigPackageImpl()
  {
    super(eNS_URI, ((EFactory)ConfigFactory.INSTANCE));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ConfigPackageImpl init()
  {
    if (isInited) return (ConfigPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ConfigPackageImpl.eNS_URI);

    // Obtain or create and register package
    ConfigPackageImpl theConfigPackageImpl = (ConfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ConfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ConfigPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theConfigPackageImpl.createPackageContents();

    // Initialize created meta-data
    theConfigPackageImpl.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theConfigPackageImpl.freeze();

    return theConfigPackageImpl;
  }


  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Column <em>Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column
   * @generated
   */
  public EClass getColumn()
  {
    return columnEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#isCollision <em>Collision</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Collision</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#isCollision()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_Collision()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getColumnType <em>Column Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Type</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#getColumnType()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_ColumnType()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getConverterClassName <em>Converter Class Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Converter Class Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#getConverterClassName()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_ConverterClassName()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#isGenerated <em>Generated</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Generated</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#isGenerated()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_Generated()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#getName()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_Name()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#isPrimaryKey <em>Primary Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Primary Key</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#isPrimaryKey()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_PrimaryKey()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getPropertyName <em>Property Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#getPropertyName()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_PropertyName()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(6);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Column#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Table</em>'.
   * @see org.apache.tuscany.das.rdb.config.Column#getTable()
   * @see #getColumn()
   * @generated
   */
  public EAttribute getColumn_Table()
  {
    return (EAttribute)columnEClass.getEStructuralFeatures().get(7);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Command <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Command</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command
   * @generated
   */
  public EClass getCommand()
  {
    return commandEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Command#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command#getParameter()
   * @see #getCommand()
   * @generated
   */
  public EReference getCommand_Parameter()
  {
    return (EReference)commandEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Command#getResultDescriptor <em>Result Descriptor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Result Descriptor</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command#getResultDescriptor()
   * @see #getCommand()
   * @generated
   */
  public EReference getCommand_ResultDescriptor()
  {
    return (EReference)commandEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Command#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Config</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command#getConfig()
   * @see #getCommand()
   * @generated
   */
  public EAttribute getCommand_Config()
  {
    return (EAttribute)commandEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Command#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command#getKind()
   * @see #getCommand()
   * @generated
   */
  public EAttribute getCommand_Kind()
  {
    return (EAttribute)commandEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Command#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command#getName()
   * @see #getCommand()
   * @generated
   */
  public EAttribute getCommand_Name()
  {
    return (EAttribute)commandEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Command#getSQL <em>SQL</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>SQL</em>'.
   * @see org.apache.tuscany.das.rdb.config.Command#getSQL()
   * @see #getCommand()
   * @generated
   */
  public EAttribute getCommand_SQL()
  {
    return (EAttribute)commandEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Config <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Config</em>'.
   * @see org.apache.tuscany.das.rdb.config.Config
   * @generated
   */
  public EClass getConfig()
  {
    return configEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Config#getCommand <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Command</em>'.
   * @see org.apache.tuscany.das.rdb.config.Config#getCommand()
   * @see #getConfig()
   * @generated
   */
  public EReference getConfig_Command()
  {
    return (EReference)configEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.apache.tuscany.das.rdb.config.Config#getConnectionProperties <em>Connection Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Connection Properties</em>'.
   * @see org.apache.tuscany.das.rdb.config.Config#getConnectionProperties()
   * @see #getConfig()
   * @generated
   */
  public EReference getConfig_ConnectionProperties()
  {
    return (EReference)configEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Config#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Table</em>'.
   * @see org.apache.tuscany.das.rdb.config.Config#getTable()
   * @see #getConfig()
   * @generated
   */
  public EReference getConfig_Table()
  {
    return (EReference)configEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Config#getRelationship <em>Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Relationship</em>'.
   * @see org.apache.tuscany.das.rdb.config.Config#getRelationship()
   * @see #getConfig()
   * @generated
   */
  public EReference getConfig_Relationship()
  {
    return (EReference)configEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Config#getUri <em>Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see org.apache.tuscany.das.rdb.config.Config#getUri()
   * @see #getConfig()
   * @generated
   */
  public EAttribute getConfig_Uri()
  {
    return (EAttribute)configEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties <em>Connection Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Connection Properties</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties
   * @generated
   */
  public EClass getConnectionProperties()
  {
    return connectionPropertiesEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Config</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getConfig()
   * @see #getConnectionProperties()
   * @generated
   */
  public EAttribute getConnectionProperties_Config()
  {
    return (EAttribute)connectionPropertiesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDataSource <em>Data Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Source</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDataSource()
   * @see #getConnectionProperties()
   * @generated
   */
  public EAttribute getConnectionProperties_DataSource()
  {
    return (EAttribute)connectionPropertiesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverClassName <em>Driver Class Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Driver Class Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverClassName()
   * @see #getConnectionProperties()
   * @generated
   */
  public EAttribute getConnectionProperties_DriverClassName()
  {
    return (EAttribute)connectionPropertiesEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverPassword <em>Driver Password</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Driver Password</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverPassword()
   * @see #getConnectionProperties()
   * @generated
   */
  public EAttribute getConnectionProperties_DriverPassword()
  {
    return (EAttribute)connectionPropertiesEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverURL <em>Driver URL</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Driver URL</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverURL()
   * @see #getConnectionProperties()
   * @generated
   */
  public EAttribute getConnectionProperties_DriverURL()
  {
    return (EAttribute)connectionPropertiesEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverUserName <em>Driver User Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Driver User Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.ConnectionProperties#getDriverUserName()
   * @see #getConnectionProperties()
   * @generated
   */
  public EAttribute getConnectionProperties_DriverUserName()
  {
    return (EAttribute)connectionPropertiesEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.KeyPair <em>Key Pair</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Key Pair</em>'.
   * @see org.apache.tuscany.das.rdb.config.KeyPair
   * @generated
   */
  public EClass getKeyPair()
  {
    return keyPairEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.KeyPair#getForeignKeyColumn <em>Foreign Key Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Foreign Key Column</em>'.
   * @see org.apache.tuscany.das.rdb.config.KeyPair#getForeignKeyColumn()
   * @see #getKeyPair()
   * @generated
   */
  public EAttribute getKeyPair_ForeignKeyColumn()
  {
    return (EAttribute)keyPairEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.KeyPair#getPrimaryKeyColumn <em>Primary Key Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Primary Key Column</em>'.
   * @see org.apache.tuscany.das.rdb.config.KeyPair#getPrimaryKeyColumn()
   * @see #getKeyPair()
   * @generated
   */
  public EAttribute getKeyPair_PrimaryKeyColumn()
  {
    return (EAttribute)keyPairEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.KeyPair#getRelationship <em>Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Relationship</em>'.
   * @see org.apache.tuscany.das.rdb.config.KeyPair#getRelationship()
   * @see #getKeyPair()
   * @generated
   */
  public EAttribute getKeyPair_Relationship()
  {
    return (EAttribute)keyPairEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.apache.tuscany.das.rdb.config.Parameter
   * @generated
   */
  public EClass getParameter()
  {
    return parameterEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Parameter#getColumnType <em>Column Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Type</em>'.
   * @see org.apache.tuscany.das.rdb.config.Parameter#getColumnType()
   * @see #getParameter()
   * @generated
   */
  public EAttribute getParameter_ColumnType()
  {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Parameter#getCommand <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Command</em>'.
   * @see org.apache.tuscany.das.rdb.config.Parameter#getCommand()
   * @see #getParameter()
   * @generated
   */
  public EAttribute getParameter_Command()
  {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Parameter#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Parameter#getName()
   * @see #getParameter()
   * @generated
   */
  public EAttribute getParameter_Name()
  {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Relationship <em>Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Relationship</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship
   * @generated
   */
  public EClass getRelationship()
  {
    return relationshipEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Relationship#getKeyPair <em>Key Pair</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Key Pair</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship#getKeyPair()
   * @see #getRelationship()
   * @generated
   */
  public EReference getRelationship_KeyPair()
  {
    return (EReference)relationshipEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Config</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship#getConfig()
   * @see #getRelationship()
   * @generated
   */
  public EAttribute getRelationship_Config()
  {
    return (EAttribute)relationshipEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getForeignKeyTable <em>Foreign Key Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Foreign Key Table</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship#getForeignKeyTable()
   * @see #getRelationship()
   * @generated
   */
  public EAttribute getRelationship_ForeignKeyTable()
  {
    return (EAttribute)relationshipEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#isMany <em>Many</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Many</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship#isMany()
   * @see #getRelationship()
   * @generated
   */
  public EAttribute getRelationship_Many()
  {
    return (EAttribute)relationshipEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship#getName()
   * @see #getRelationship()
   * @generated
   */
  public EAttribute getRelationship_Name()
  {
    return (EAttribute)relationshipEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Relationship#getPrimaryKeyTable <em>Primary Key Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Primary Key Table</em>'.
   * @see org.apache.tuscany.das.rdb.config.Relationship#getPrimaryKeyTable()
   * @see #getRelationship()
   * @generated
   */
  public EAttribute getRelationship_PrimaryKeyTable()
  {
    return (EAttribute)relationshipEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor <em>Result Descriptor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Result Descriptor</em>'.
   * @see org.apache.tuscany.das.rdb.config.ResultDescriptor
   * @generated
   */
  public EClass getResultDescriptor()
  {
    return resultDescriptorEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName <em>Column Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnName()
   * @see #getResultDescriptor()
   * @generated
   */
  public EAttribute getResultDescriptor_ColumnName()
  {
    return (EAttribute)resultDescriptorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnType <em>Column Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Type</em>'.
   * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getColumnType()
   * @see #getResultDescriptor()
   * @generated
   */
  public EAttribute getResultDescriptor_ColumnType()
  {
    return (EAttribute)resultDescriptorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommand <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Command</em>'.
   * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getCommand()
   * @see #getResultDescriptor()
   * @generated
   */
  public EAttribute getResultDescriptor_Command()
  {
    return (EAttribute)resultDescriptorEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter <em>Converter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Converter</em>'.
   * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getConverter()
   * @see #getResultDescriptor()
   * @generated
   */
  public EAttribute getResultDescriptor_Converter()
  {
    return (EAttribute)resultDescriptorEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName <em>Table Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Table Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.ResultDescriptor#getTableName()
   * @see #getResultDescriptor()
   * @generated
   */
  public EAttribute getResultDescriptor_TableName()
  {
    return (EAttribute)resultDescriptorEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.config.Table <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Table</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table
   * @generated
   */
  public EClass getTable()
  {
    return tableEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.config.Table#getColumn <em>Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Column</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getColumn()
   * @see #getTable()
   * @generated
   */
  public EReference getTable_Column()
  {
    return (EReference)tableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getConfig <em>Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Config</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getConfig()
   * @see #getTable()
   * @generated
   */
  public EAttribute getTable_Config()
  {
    return (EAttribute)tableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getCreate <em>Create</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Create</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getCreate()
   * @see #getTable()
   * @generated
   */
  public EAttribute getTable_Create()
  {
    return (EAttribute)tableEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getDelete <em>Delete</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Delete</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getDelete()
   * @see #getTable()
   * @generated
   */
  public EAttribute getTable_Delete()
  {
    return (EAttribute)tableEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getName()
   * @see #getTable()
   * @generated
   */
  public EAttribute getTable_Name()
  {
    return (EAttribute)tableEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getPropertyName <em>Property Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property Name</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getPropertyName()
   * @see #getTable()
   * @generated
   */
  public EAttribute getTable_PropertyName()
  {
    return (EAttribute)tableEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.config.Table#getUpdate <em>Update</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Update</em>'.
   * @see org.apache.tuscany.das.rdb.config.Table#getUpdate()
   * @see #getTable()
   * @generated
   */
  public EAttribute getTable_Update()
  {
    return (EAttribute)tableEClass.getEStructuralFeatures().get(6);
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  public ConfigFactory getConfigFactory()
  {
    return (ConfigFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    columnEClass = createEClass(COLUMN);
    createEAttribute(columnEClass, COLUMN__COLLISION);
    createEAttribute(columnEClass, COLUMN__COLUMN_TYPE);
    createEAttribute(columnEClass, COLUMN__CONVERTER_CLASS_NAME);
    createEAttribute(columnEClass, COLUMN__GENERATED);
    createEAttribute(columnEClass, COLUMN__NAME);
    createEAttribute(columnEClass, COLUMN__PRIMARY_KEY);
    createEAttribute(columnEClass, COLUMN__PROPERTY_NAME);
    createEAttribute(columnEClass, COLUMN__TABLE);

    commandEClass = createEClass(COMMAND);
    createEReference(commandEClass, COMMAND__PARAMETER);
    createEReference(commandEClass, COMMAND__RESULT_DESCRIPTOR);
    createEAttribute(commandEClass, COMMAND__CONFIG);
    createEAttribute(commandEClass, COMMAND__KIND);
    createEAttribute(commandEClass, COMMAND__NAME);
    createEAttribute(commandEClass, COMMAND__SQL);

    configEClass = createEClass(CONFIG);
    createEReference(configEClass, CONFIG__COMMAND);
    createEReference(configEClass, CONFIG__CONNECTION_PROPERTIES);
    createEReference(configEClass, CONFIG__TABLE);
    createEReference(configEClass, CONFIG__RELATIONSHIP);
    createEAttribute(configEClass, CONFIG__URI);

    connectionPropertiesEClass = createEClass(CONNECTION_PROPERTIES);
    createEAttribute(connectionPropertiesEClass, CONNECTION_PROPERTIES__CONFIG);
    createEAttribute(connectionPropertiesEClass, CONNECTION_PROPERTIES__DATA_SOURCE);
    createEAttribute(connectionPropertiesEClass, CONNECTION_PROPERTIES__DRIVER_CLASS_NAME);
    createEAttribute(connectionPropertiesEClass, CONNECTION_PROPERTIES__DRIVER_PASSWORD);
    createEAttribute(connectionPropertiesEClass, CONNECTION_PROPERTIES__DRIVER_URL);
    createEAttribute(connectionPropertiesEClass, CONNECTION_PROPERTIES__DRIVER_USER_NAME);

    keyPairEClass = createEClass(KEY_PAIR);
    createEAttribute(keyPairEClass, KEY_PAIR__FOREIGN_KEY_COLUMN);
    createEAttribute(keyPairEClass, KEY_PAIR__PRIMARY_KEY_COLUMN);
    createEAttribute(keyPairEClass, KEY_PAIR__RELATIONSHIP);

    parameterEClass = createEClass(PARAMETER);
    createEAttribute(parameterEClass, PARAMETER__COLUMN_TYPE);
    createEAttribute(parameterEClass, PARAMETER__COMMAND);
    createEAttribute(parameterEClass, PARAMETER__NAME);

    relationshipEClass = createEClass(RELATIONSHIP);
    createEReference(relationshipEClass, RELATIONSHIP__KEY_PAIR);
    createEAttribute(relationshipEClass, RELATIONSHIP__CONFIG);
    createEAttribute(relationshipEClass, RELATIONSHIP__FOREIGN_KEY_TABLE);
    createEAttribute(relationshipEClass, RELATIONSHIP__MANY);
    createEAttribute(relationshipEClass, RELATIONSHIP__NAME);
    createEAttribute(relationshipEClass, RELATIONSHIP__PRIMARY_KEY_TABLE);

    resultDescriptorEClass = createEClass(RESULT_DESCRIPTOR);
    createEAttribute(resultDescriptorEClass, RESULT_DESCRIPTOR__COLUMN_NAME);
    createEAttribute(resultDescriptorEClass, RESULT_DESCRIPTOR__COLUMN_TYPE);
    createEAttribute(resultDescriptorEClass, RESULT_DESCRIPTOR__COMMAND);
    createEAttribute(resultDescriptorEClass, RESULT_DESCRIPTOR__CONVERTER);
    createEAttribute(resultDescriptorEClass, RESULT_DESCRIPTOR__TABLE_NAME);

    tableEClass = createEClass(TABLE);
    createEReference(tableEClass, TABLE__COLUMN);
    createEAttribute(tableEClass, TABLE__CONFIG);
    createEAttribute(tableEClass, TABLE__CREATE);
    createEAttribute(tableEClass, TABLE__DELETE);
    createEAttribute(tableEClass, TABLE__NAME);
    createEAttribute(tableEClass, TABLE__PROPERTY_NAME);
    createEAttribute(tableEClass, TABLE__UPDATE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(columnEClass, Column.class, "Column", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getColumn_Collision(), theXMLTypePackage.getBoolean(), "collision", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_ColumnType(), theXMLTypePackage.getString(), "columnType", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_ConverterClassName(), theXMLTypePackage.getString(), "converterClassName", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_Generated(), theXMLTypePackage.getBoolean(), "generated", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_PrimaryKey(), theXMLTypePackage.getBoolean(), "primaryKey", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_PropertyName(), theXMLTypePackage.getString(), "propertyName", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumn_Table(), theXMLTypePackage.getAnyURI(), "table", null, 0, 1, Column.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(commandEClass, Command.class, "Command", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCommand_Parameter(), this.getParameter(), null, "parameter", null, 0, -1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCommand_ResultDescriptor(), this.getResultDescriptor(), null, "resultDescriptor", null, 0, -1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCommand_Config(), theXMLTypePackage.getAnyURI(), "config", null, 0, 1, Command.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCommand_Kind(), theXMLTypePackage.getString(), "kind", null, 0, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCommand_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCommand_SQL(), theXMLTypePackage.getString(), "sQL", null, 0, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(configEClass, Config.class, "Config", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConfig_Command(), this.getCommand(), null, "command", null, 0, -1, Config.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConfig_ConnectionProperties(), this.getConnectionProperties(), null, "connectionProperties", null, 0, 1, Config.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConfig_Table(), this.getTable(), null, "table", null, 0, -1, Config.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConfig_Relationship(), this.getRelationship(), null, "relationship", null, 0, -1, Config.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConfig_Uri(), theXMLTypePackage.getString(), "uri", null, 0, 1, Config.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(connectionPropertiesEClass, ConnectionProperties.class, "ConnectionProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getConnectionProperties_Config(), theXMLTypePackage.getAnyURI(), "config", null, 0, 1, ConnectionProperties.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConnectionProperties_DataSource(), theXMLTypePackage.getString(), "dataSource", null, 0, 1, ConnectionProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConnectionProperties_DriverClassName(), theXMLTypePackage.getString(), "driverClassName", null, 0, 1, ConnectionProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConnectionProperties_DriverPassword(), theXMLTypePackage.getString(), "driverPassword", null, 0, 1, ConnectionProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConnectionProperties_DriverURL(), theXMLTypePackage.getString(), "driverURL", null, 0, 1, ConnectionProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConnectionProperties_DriverUserName(), theXMLTypePackage.getString(), "driverUserName", null, 0, 1, ConnectionProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(keyPairEClass, KeyPair.class, "KeyPair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getKeyPair_ForeignKeyColumn(), theXMLTypePackage.getString(), "foreignKeyColumn", null, 0, 1, KeyPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getKeyPair_PrimaryKeyColumn(), theXMLTypePackage.getString(), "primaryKeyColumn", null, 0, 1, KeyPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getKeyPair_Relationship(), theXMLTypePackage.getAnyURI(), "relationship", null, 0, 1, KeyPair.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getParameter_ColumnType(), theXMLTypePackage.getString(), "columnType", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getParameter_Command(), theXMLTypePackage.getAnyURI(), "command", null, 0, 1, Parameter.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getParameter_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(relationshipEClass, Relationship.class, "Relationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRelationship_KeyPair(), this.getKeyPair(), null, "keyPair", null, 0, -1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRelationship_Config(), theXMLTypePackage.getAnyURI(), "config", null, 0, 1, Relationship.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRelationship_ForeignKeyTable(), theXMLTypePackage.getString(), "foreignKeyTable", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRelationship_Many(), theXMLTypePackage.getBoolean(), "many", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRelationship_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRelationship_PrimaryKeyTable(), theXMLTypePackage.getString(), "primaryKeyTable", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(resultDescriptorEClass, ResultDescriptor.class, "ResultDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getResultDescriptor_ColumnName(), theXMLTypePackage.getString(), "columnName", null, 0, 1, ResultDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResultDescriptor_ColumnType(), theXMLTypePackage.getString(), "columnType", null, 0, 1, ResultDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResultDescriptor_Command(), theXMLTypePackage.getAnyURI(), "command", null, 0, 1, ResultDescriptor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResultDescriptor_Converter(), theXMLTypePackage.getString(), "converter", null, 0, 1, ResultDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResultDescriptor_TableName(), theXMLTypePackage.getString(), "tableName", null, 0, 1, ResultDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tableEClass, Table.class, "Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTable_Column(), this.getColumn(), null, "column", null, 0, -1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTable_Config(), theXMLTypePackage.getAnyURI(), "config", null, 0, 1, Table.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTable_Create(), theXMLTypePackage.getString(), "create", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTable_Delete(), theXMLTypePackage.getString(), "delete", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTable_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTable_PropertyName(), theXMLTypePackage.getString(), "propertyName", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTable_Update(), theXMLTypePackage.getString(), "update", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (columnEClass, 
       source, 
       new String[] 
       {
       "name", "Column",
       "kind", "empty"
       });		
    addAnnotation
      (getColumn_Collision(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "collision"
       });		
    addAnnotation
      (getColumn_ColumnType(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "columnType"
       });		
    addAnnotation
      (getColumn_ConverterClassName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "converterClassName"
       });		
    addAnnotation
      (getColumn_Generated(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "generated"
       });		
    addAnnotation
      (getColumn_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (getColumn_PrimaryKey(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "primaryKey"
       });		
    addAnnotation
      (getColumn_PropertyName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "propertyName"
       });		
    addAnnotation
      (getColumn_Table(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Table"
       });		
    addAnnotation
      (commandEClass, 
       source, 
       new String[] 
       {
       "name", "Command",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCommand_Parameter(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Parameter"
       });		
    addAnnotation
      (getCommand_ResultDescriptor(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "ResultDescriptor"
       });		
    addAnnotation
      (getCommand_Config(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Config"
       });		
    addAnnotation
      (getCommand_Kind(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "kind"
       });		
    addAnnotation
      (getCommand_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (getCommand_SQL(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "SQL"
       });		
    addAnnotation
      (configEClass, 
       source, 
       new String[] 
       {
       "name", "Config",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getConfig_Command(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Command"
       });		
    addAnnotation
      (getConfig_ConnectionProperties(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "ConnectionProperties"
       });		
    addAnnotation
      (getConfig_Table(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Table"
       });		
    addAnnotation
      (getConfig_Relationship(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Relationship"
       });		
    addAnnotation
      (getConfig_Uri(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "uri"
       });		
    addAnnotation
      (connectionPropertiesEClass, 
       source, 
       new String[] 
       {
       "name", "ConnectionProperties",
       "kind", "empty"
       });		
    addAnnotation
      (getConnectionProperties_Config(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Config"
       });		
    addAnnotation
      (getConnectionProperties_DataSource(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "dataSource"
       });		
    addAnnotation
      (getConnectionProperties_DriverClassName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "driverClassName"
       });		
    addAnnotation
      (getConnectionProperties_DriverPassword(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "driverPassword"
       });		
    addAnnotation
      (getConnectionProperties_DriverURL(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "driverURL"
       });		
    addAnnotation
      (getConnectionProperties_DriverUserName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "driverUserName"
       });		
    addAnnotation
      (keyPairEClass, 
       source, 
       new String[] 
       {
       "name", "KeyPair",
       "kind", "empty"
       });		
    addAnnotation
      (getKeyPair_ForeignKeyColumn(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "foreignKeyColumn"
       });		
    addAnnotation
      (getKeyPair_PrimaryKeyColumn(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "primaryKeyColumn"
       });		
    addAnnotation
      (getKeyPair_Relationship(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Relationship"
       });		
    addAnnotation
      (parameterEClass, 
       source, 
       new String[] 
       {
       "name", "Parameter",
       "kind", "empty"
       });		
    addAnnotation
      (getParameter_ColumnType(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "columnType"
       });		
    addAnnotation
      (getParameter_Command(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Command"
       });		
    addAnnotation
      (getParameter_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (relationshipEClass, 
       source, 
       new String[] 
       {
       "name", "Relationship",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getRelationship_KeyPair(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "KeyPair"
       });		
    addAnnotation
      (getRelationship_Config(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Config"
       });		
    addAnnotation
      (getRelationship_ForeignKeyTable(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "foreignKeyTable"
       });		
    addAnnotation
      (getRelationship_Many(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "many"
       });		
    addAnnotation
      (getRelationship_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (getRelationship_PrimaryKeyTable(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "primaryKeyTable"
       });		
    addAnnotation
      (resultDescriptorEClass, 
       source, 
       new String[] 
       {
       "name", "ResultDescriptor",
       "kind", "empty"
       });		
    addAnnotation
      (getResultDescriptor_ColumnName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "columnName"
       });		
    addAnnotation
      (getResultDescriptor_ColumnType(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "columnType"
       });		
    addAnnotation
      (getResultDescriptor_Command(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Command"
       });		
    addAnnotation
      (getResultDescriptor_Converter(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "converter"
       });		
    addAnnotation
      (getResultDescriptor_TableName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "tableName"
       });		
    addAnnotation
      (tableEClass, 
       source, 
       new String[] 
       {
       "name", "Table",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getTable_Column(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "Column"
       });		
    addAnnotation
      (getTable_Config(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "Config"
       });		
    addAnnotation
      (getTable_Create(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "create"
       });		
    addAnnotation
      (getTable_Delete(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "delete"
       });		
    addAnnotation
      (getTable_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (getTable_PropertyName(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "propertyName"
       });		
    addAnnotation
      (getTable_Update(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "update"
       });
  }

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  public interface Literals
  {
    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.ColumnImpl <em>Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.ColumnImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getColumn()
     * @generated
     */
    public static final EClass COLUMN = eINSTANCE.getColumn();

    /**
     * The meta object literal for the '<em><b>Collision</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__COLLISION = eINSTANCE.getColumn_Collision();

    /**
     * The meta object literal for the '<em><b>Column Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__COLUMN_TYPE = eINSTANCE.getColumn_ColumnType();

    /**
     * The meta object literal for the '<em><b>Converter Class Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__CONVERTER_CLASS_NAME = eINSTANCE.getColumn_ConverterClassName();

    /**
     * The meta object literal for the '<em><b>Generated</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__GENERATED = eINSTANCE.getColumn_Generated();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__NAME = eINSTANCE.getColumn_Name();

    /**
     * The meta object literal for the '<em><b>Primary Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__PRIMARY_KEY = eINSTANCE.getColumn_PrimaryKey();

    /**
     * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__PROPERTY_NAME = eINSTANCE.getColumn_PropertyName();

    /**
     * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COLUMN__TABLE = eINSTANCE.getColumn_Table();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.CommandImpl <em>Command</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.CommandImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getCommand()
     * @generated
     */
    public static final EClass COMMAND = eINSTANCE.getCommand();

    /**
     * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference COMMAND__PARAMETER = eINSTANCE.getCommand_Parameter();

    /**
     * The meta object literal for the '<em><b>Result Descriptor</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference COMMAND__RESULT_DESCRIPTOR = eINSTANCE.getCommand_ResultDescriptor();

    /**
     * The meta object literal for the '<em><b>Config</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COMMAND__CONFIG = eINSTANCE.getCommand_Config();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COMMAND__KIND = eINSTANCE.getCommand_Kind();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COMMAND__NAME = eINSTANCE.getCommand_Name();

    /**
     * The meta object literal for the '<em><b>SQL</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COMMAND__SQL = eINSTANCE.getCommand_SQL();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.ConfigImpl <em>Config</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getConfig()
     * @generated
     */
    public static final EClass CONFIG = eINSTANCE.getConfig();

    /**
     * The meta object literal for the '<em><b>Command</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONFIG__COMMAND = eINSTANCE.getConfig_Command();

    /**
     * The meta object literal for the '<em><b>Connection Properties</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONFIG__CONNECTION_PROPERTIES = eINSTANCE.getConfig_ConnectionProperties();

    /**
     * The meta object literal for the '<em><b>Table</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONFIG__TABLE = eINSTANCE.getConfig_Table();

    /**
     * The meta object literal for the '<em><b>Relationship</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONFIG__RELATIONSHIP = eINSTANCE.getConfig_Relationship();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONFIG__URI = eINSTANCE.getConfig_Uri();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl <em>Connection Properties</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.ConnectionPropertiesImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getConnectionProperties()
     * @generated
     */
    public static final EClass CONNECTION_PROPERTIES = eINSTANCE.getConnectionProperties();

    /**
     * The meta object literal for the '<em><b>Config</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONNECTION_PROPERTIES__CONFIG = eINSTANCE.getConnectionProperties_Config();

    /**
     * The meta object literal for the '<em><b>Data Source</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONNECTION_PROPERTIES__DATA_SOURCE = eINSTANCE.getConnectionProperties_DataSource();

    /**
     * The meta object literal for the '<em><b>Driver Class Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONNECTION_PROPERTIES__DRIVER_CLASS_NAME = eINSTANCE.getConnectionProperties_DriverClassName();

    /**
     * The meta object literal for the '<em><b>Driver Password</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONNECTION_PROPERTIES__DRIVER_PASSWORD = eINSTANCE.getConnectionProperties_DriverPassword();

    /**
     * The meta object literal for the '<em><b>Driver URL</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONNECTION_PROPERTIES__DRIVER_URL = eINSTANCE.getConnectionProperties_DriverURL();

    /**
     * The meta object literal for the '<em><b>Driver User Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONNECTION_PROPERTIES__DRIVER_USER_NAME = eINSTANCE.getConnectionProperties_DriverUserName();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.KeyPairImpl <em>Key Pair</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.KeyPairImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getKeyPair()
     * @generated
     */
    public static final EClass KEY_PAIR = eINSTANCE.getKeyPair();

    /**
     * The meta object literal for the '<em><b>Foreign Key Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute KEY_PAIR__FOREIGN_KEY_COLUMN = eINSTANCE.getKeyPair_ForeignKeyColumn();

    /**
     * The meta object literal for the '<em><b>Primary Key Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute KEY_PAIR__PRIMARY_KEY_COLUMN = eINSTANCE.getKeyPair_PrimaryKeyColumn();

    /**
     * The meta object literal for the '<em><b>Relationship</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute KEY_PAIR__RELATIONSHIP = eINSTANCE.getKeyPair_Relationship();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.ParameterImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getParameter()
     * @generated
     */
    public static final EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Column Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PARAMETER__COLUMN_TYPE = eINSTANCE.getParameter_ColumnType();

    /**
     * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PARAMETER__COMMAND = eINSTANCE.getParameter_Command();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.RelationshipImpl <em>Relationship</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.RelationshipImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getRelationship()
     * @generated
     */
    public static final EClass RELATIONSHIP = eINSTANCE.getRelationship();

    /**
     * The meta object literal for the '<em><b>Key Pair</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference RELATIONSHIP__KEY_PAIR = eINSTANCE.getRelationship_KeyPair();

    /**
     * The meta object literal for the '<em><b>Config</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RELATIONSHIP__CONFIG = eINSTANCE.getRelationship_Config();

    /**
     * The meta object literal for the '<em><b>Foreign Key Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RELATIONSHIP__FOREIGN_KEY_TABLE = eINSTANCE.getRelationship_ForeignKeyTable();

    /**
     * The meta object literal for the '<em><b>Many</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RELATIONSHIP__MANY = eINSTANCE.getRelationship_Many();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RELATIONSHIP__NAME = eINSTANCE.getRelationship_Name();

    /**
     * The meta object literal for the '<em><b>Primary Key Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RELATIONSHIP__PRIMARY_KEY_TABLE = eINSTANCE.getRelationship_PrimaryKeyTable();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl <em>Result Descriptor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getResultDescriptor()
     * @generated
     */
    public static final EClass RESULT_DESCRIPTOR = eINSTANCE.getResultDescriptor();

    /**
     * The meta object literal for the '<em><b>Column Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RESULT_DESCRIPTOR__COLUMN_NAME = eINSTANCE.getResultDescriptor_ColumnName();

    /**
     * The meta object literal for the '<em><b>Column Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RESULT_DESCRIPTOR__COLUMN_TYPE = eINSTANCE.getResultDescriptor_ColumnType();

    /**
     * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RESULT_DESCRIPTOR__COMMAND = eINSTANCE.getResultDescriptor_Command();

    /**
     * The meta object literal for the '<em><b>Converter</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RESULT_DESCRIPTOR__CONVERTER = eINSTANCE.getResultDescriptor_Converter();

    /**
     * The meta object literal for the '<em><b>Table Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute RESULT_DESCRIPTOR__TABLE_NAME = eINSTANCE.getResultDescriptor_TableName();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.config.impl.TableImpl <em>Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.config.impl.TableImpl
     * @see org.apache.tuscany.das.rdb.config.impl.ConfigPackageImpl#getTable()
     * @generated
     */
    public static final EClass TABLE = eINSTANCE.getTable();

    /**
     * The meta object literal for the '<em><b>Column</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TABLE__COLUMN = eINSTANCE.getTable_Column();

    /**
     * The meta object literal for the '<em><b>Config</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute TABLE__CONFIG = eINSTANCE.getTable_Config();

    /**
     * The meta object literal for the '<em><b>Create</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute TABLE__CREATE = eINSTANCE.getTable_Create();

    /**
     * The meta object literal for the '<em><b>Delete</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute TABLE__DELETE = eINSTANCE.getTable_Delete();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute TABLE__NAME = eINSTANCE.getTable_Name();

    /**
     * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute TABLE__PROPERTY_NAME = eINSTANCE.getTable_PropertyName();

    /**
     * The meta object literal for the '<em><b>Update</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute TABLE__UPDATE = eINSTANCE.getTable_Update();

  }

} //ConfigPackageImpl
