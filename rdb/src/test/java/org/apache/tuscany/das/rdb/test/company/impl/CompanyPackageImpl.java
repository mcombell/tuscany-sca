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
package org.apache.tuscany.das.rdb.test.company.impl;

import org.apache.tuscany.das.rdb.test.company.CompanyFactory;
import org.apache.tuscany.das.rdb.test.company.CompanyType;
import org.apache.tuscany.das.rdb.test.company.DatagraphRoot;
import org.apache.tuscany.das.rdb.test.company.DepartmentType;
import org.apache.tuscany.das.rdb.test.company.EmployeeType;
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
 * @see org.apache.tuscany.das.rdb.test.company.CompanyFactory
 * @generated
 */
public class CompanyPackageImpl extends EPackageImpl
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "company";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "org.apache.tuscany.das.rdb.test/company.xsd";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "company";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final CompanyPackageImpl eINSTANCE = org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl.init();

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl <em>Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getCompanyType()
   * @generated
   */
  public static final int COMPANY_TYPE = 0;

  /**
   * The feature id for the '<em><b>Departments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPANY_TYPE__DEPARTMENTS = 0;

  /**
   * The feature id for the '<em><b>Employee Of The Month</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPANY_TYPE__NAME = 2;

  /**
   * The number of structural features of the '<em>Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPANY_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl <em>Datagraph Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDatagraphRoot()
   * @generated
   */
  public static final int DATAGRAPH_ROOT = 1;

  /**
   * The feature id for the '<em><b>Companies</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATAGRAPH_ROOT__COMPANIES = 0;

  /**
   * The feature id for the '<em><b>Departments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATAGRAPH_ROOT__DEPARTMENTS = 1;

  /**
   * The feature id for the '<em><b>Employees</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATAGRAPH_ROOT__EMPLOYEES = 2;

  /**
   * The number of structural features of the '<em>Datagraph Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DATAGRAPH_ROOT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl <em>Department Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDepartmentType()
   * @generated
   */
  public static final int DEPARTMENT_TYPE = 2;

  /**
   * The feature id for the '<em><b>Employees</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DEPARTMENT_TYPE__EMPLOYEES = 0;

  /**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DEPARTMENT_TYPE__LOCATION = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DEPARTMENT_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DEPARTMENT_TYPE__NUMBER = 3;

  /**
   * The number of structural features of the '<em>Department Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DEPARTMENT_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.company.impl.DocumentRootImpl
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDocumentRoot()
   * @generated
   */
  public static final int DOCUMENT_ROOT = 3;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Company</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__COMPANY = 3;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl <em>Employee Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getEmployeeType()
   * @generated
   */
  public static final int EMPLOYEE_TYPE = 4;

  /**
   * The feature id for the '<em><b>Manager</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EMPLOYEE_TYPE__MANAGER = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EMPLOYEE_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>SN</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EMPLOYEE_TYPE__SN = 2;

  /**
   * The number of structural features of the '<em>Employee Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EMPLOYEE_TYPE_FEATURE_COUNT = 3;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass companyTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass datagraphRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass departmentTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass documentRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass employeeTypeEClass = null;

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
   * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private CompanyPackageImpl()
  {
    super(eNS_URI, ((EFactory)CompanyFactory.INSTANCE));
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
  public static CompanyPackageImpl init()
  {
    if (isInited) return (CompanyPackageImpl)EPackage.Registry.INSTANCE.getEPackage(CompanyPackageImpl.eNS_URI);

    // Obtain or create and register package
    CompanyPackageImpl theCompanyPackageImpl = (CompanyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof CompanyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CompanyPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theCompanyPackageImpl.createPackageContents();

    // Initialize created meta-data
    theCompanyPackageImpl.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theCompanyPackageImpl.freeze();

    return theCompanyPackageImpl;
  }


  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.CompanyType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.CompanyType
   * @generated
   */
  public EClass getCompanyType()
  {
    return companyTypeEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getDepartments <em>Departments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Departments</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.CompanyType#getDepartments()
   * @see #getCompanyType()
   * @generated
   */
  public EReference getCompanyType_Departments()
  {
    return (EReference)companyTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth <em>Employee Of The Month</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Employee Of The Month</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth()
   * @see #getCompanyType()
   * @generated
   */
  public EAttribute getCompanyType_EmployeeOfTheMonth()
  {
    return (EAttribute)companyTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.CompanyType#getName()
   * @see #getCompanyType()
   * @generated
   */
  public EAttribute getCompanyType_Name()
  {
    return (EAttribute)companyTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot <em>Datagraph Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Datagraph Root</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DatagraphRoot
   * @generated
   */
  public EClass getDatagraphRoot()
  {
    return datagraphRootEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getCompanies <em>Companies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Companies</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getCompanies()
   * @see #getDatagraphRoot()
   * @generated
   */
  public EReference getDatagraphRoot_Companies()
  {
    return (EReference)datagraphRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getDepartments <em>Departments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Departments</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getDepartments()
   * @see #getDatagraphRoot()
   * @generated
   */
  public EReference getDatagraphRoot_Departments()
  {
    return (EReference)datagraphRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getEmployees <em>Employees</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Employees</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DatagraphRoot#getEmployees()
   * @see #getDatagraphRoot()
   * @generated
   */
  public EReference getDatagraphRoot_Employees()
  {
    return (EReference)datagraphRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType <em>Department Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Department Type</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DepartmentType
   * @generated
   */
  public EClass getDepartmentType()
  {
    return departmentTypeEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getEmployees <em>Employees</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Employees</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getEmployees()
   * @see #getDepartmentType()
   * @generated
   */
  public EReference getDepartmentType_Employees()
  {
    return (EReference)departmentTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getLocation()
   * @see #getDepartmentType()
   * @generated
   */
  public EAttribute getDepartmentType_Location()
  {
    return (EAttribute)departmentTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getName()
   * @see #getDepartmentType()
   * @generated
   */
  public EAttribute getDepartmentType_Name()
  {
    return (EAttribute)departmentTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getNumber <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getNumber()
   * @see #getDepartmentType()
   * @generated
   */
  public EAttribute getDepartmentType_Number()
  {
    return (EAttribute)departmentTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.EObject <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.ecore.EObject
   * @generated
   */
  public EClass getDocumentRoot()
  {
    return documentRootEClass;
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.EObject#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.ecore.EObject#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Mixed()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.ecore.EObject#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.ecore.EObject#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.ecore.EObject#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.ecore.EObject#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getCompany <em>Company</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Company</em>'.
   * @see org.eclipse.emf.ecore.EObject#getCompany()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_Company()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType <em>Employee Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Employee Type</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.EmployeeType
   * @generated
   */
  public EClass getEmployeeType()
  {
    return employeeTypeEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager <em>Manager</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Manager</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager()
   * @see #getEmployeeType()
   * @generated
   */
  public EAttribute getEmployeeType_Manager()
  {
    return (EAttribute)employeeTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.EmployeeType#getName()
   * @see #getEmployeeType()
   * @generated
   */
  public EAttribute getEmployeeType_Name()
  {
    return (EAttribute)employeeTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getSN <em>SN</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>SN</em>'.
   * @see org.apache.tuscany.das.rdb.test.company.EmployeeType#getSN()
   * @see #getEmployeeType()
   * @generated
   */
  public EAttribute getEmployeeType_SN()
  {
    return (EAttribute)employeeTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  public CompanyFactory getCompanyFactory()
  {
    return (CompanyFactory)getEFactoryInstance();
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
    companyTypeEClass = createEClass(COMPANY_TYPE);
    createEReference(companyTypeEClass, COMPANY_TYPE__DEPARTMENTS);
    createEAttribute(companyTypeEClass, COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH);
    createEAttribute(companyTypeEClass, COMPANY_TYPE__NAME);

    datagraphRootEClass = createEClass(DATAGRAPH_ROOT);
    createEReference(datagraphRootEClass, DATAGRAPH_ROOT__COMPANIES);
    createEReference(datagraphRootEClass, DATAGRAPH_ROOT__DEPARTMENTS);
    createEReference(datagraphRootEClass, DATAGRAPH_ROOT__EMPLOYEES);

    departmentTypeEClass = createEClass(DEPARTMENT_TYPE);
    createEReference(departmentTypeEClass, DEPARTMENT_TYPE__EMPLOYEES);
    createEAttribute(departmentTypeEClass, DEPARTMENT_TYPE__LOCATION);
    createEAttribute(departmentTypeEClass, DEPARTMENT_TYPE__NAME);
    createEAttribute(departmentTypeEClass, DEPARTMENT_TYPE__NUMBER);

    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEReference(documentRootEClass, DOCUMENT_ROOT__COMPANY);

    employeeTypeEClass = createEClass(EMPLOYEE_TYPE);
    createEAttribute(employeeTypeEClass, EMPLOYEE_TYPE__MANAGER);
    createEAttribute(employeeTypeEClass, EMPLOYEE_TYPE__NAME);
    createEAttribute(employeeTypeEClass, EMPLOYEE_TYPE__SN);
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
    initEClass(companyTypeEClass, CompanyType.class, "CompanyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompanyType_Departments(), this.getDepartmentType(), null, "departments", null, 1, -1, CompanyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCompanyType_EmployeeOfTheMonth(), theXMLTypePackage.getIDREF(), "employeeOfTheMonth", null, 0, 1, CompanyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCompanyType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, CompanyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(datagraphRootEClass, DatagraphRoot.class, "DatagraphRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDatagraphRoot_Companies(), this.getCompanyType(), null, "companies", null, 0, -1, DatagraphRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatagraphRoot_Departments(), this.getDepartmentType(), null, "departments", null, 0, -1, DatagraphRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatagraphRoot_Employees(), this.getEmployeeType(), null, "employees", null, 0, -1, DatagraphRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(departmentTypeEClass, DepartmentType.class, "DepartmentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDepartmentType_Employees(), this.getEmployeeType(), null, "employees", null, 1, -1, DepartmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDepartmentType_Location(), theXMLTypePackage.getString(), "location", null, 0, 1, DepartmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDepartmentType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, DepartmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDepartmentType_Number(), theXMLTypePackage.getInt(), "number", null, 0, 1, DepartmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(documentRootEClass, null, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Company(), this.getCompanyType(), null, "company", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(employeeTypeEClass, EmployeeType.class, "EmployeeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEmployeeType_Manager(), theXMLTypePackage.getBoolean(), "manager", null, 0, 1, EmployeeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEmployeeType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, EmployeeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEmployeeType_SN(), theXMLTypePackage.getID(), "sN", null, 0, 1, EmployeeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
      (companyTypeEClass, 
       source, 
       new String[] 
       {
       "name", "CompanyType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCompanyType_Departments(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "departments"
       });		
    addAnnotation
      (getCompanyType_EmployeeOfTheMonth(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "employeeOfTheMonth"
       });		
    addAnnotation
      (getCompanyType_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (datagraphRootEClass, 
       source, 
       new String[] 
       {
       "name", "DatagraphRoot",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getDatagraphRoot_Companies(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "companies"
       });		
    addAnnotation
      (getDatagraphRoot_Departments(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "departments"
       });		
    addAnnotation
      (getDatagraphRoot_Employees(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "employees"
       });		
    addAnnotation
      (departmentTypeEClass, 
       source, 
       new String[] 
       {
       "name", "DepartmentType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getDepartmentType_Employees(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "employees"
       });		
    addAnnotation
      (getDepartmentType_Location(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "location"
       });		
    addAnnotation
      (getDepartmentType_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (getDepartmentType_Number(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "number"
       });		
    addAnnotation
      (documentRootEClass, 
       source, 
       new String[] 
       {
       "name", "",
       "kind", "mixed"
       });		
    addAnnotation
      (getDocumentRoot_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getDocumentRoot_XMLNSPrefixMap(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xmlns:prefix"
       });		
    addAnnotation
      (getDocumentRoot_XSISchemaLocation(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xsi:schemaLocation"
       });		
    addAnnotation
      (getDocumentRoot_Company(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "company",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (employeeTypeEClass, 
       source, 
       new String[] 
       {
       "name", "EmployeeType",
       "kind", "empty"
       });		
    addAnnotation
      (getEmployeeType_Manager(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "manager"
       });		
    addAnnotation
      (getEmployeeType_Name(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "name"
       });		
    addAnnotation
      (getEmployeeType_SN(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "SN"
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
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl
     * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getCompanyType()
     * @generated
     */
    public static final EClass COMPANY_TYPE = eINSTANCE.getCompanyType();

    /**
     * The meta object literal for the '<em><b>Departments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference COMPANY_TYPE__DEPARTMENTS = eINSTANCE.getCompanyType_Departments();

    /**
     * The meta object literal for the '<em><b>Employee Of The Month</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH = eINSTANCE.getCompanyType_EmployeeOfTheMonth();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute COMPANY_TYPE__NAME = eINSTANCE.getCompanyType_Name();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl <em>Datagraph Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.company.impl.DatagraphRootImpl
     * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDatagraphRoot()
     * @generated
     */
    public static final EClass DATAGRAPH_ROOT = eINSTANCE.getDatagraphRoot();

    /**
     * The meta object literal for the '<em><b>Companies</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DATAGRAPH_ROOT__COMPANIES = eINSTANCE.getDatagraphRoot_Companies();

    /**
     * The meta object literal for the '<em><b>Departments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DATAGRAPH_ROOT__DEPARTMENTS = eINSTANCE.getDatagraphRoot_Departments();

    /**
     * The meta object literal for the '<em><b>Employees</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DATAGRAPH_ROOT__EMPLOYEES = eINSTANCE.getDatagraphRoot_Employees();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl <em>Department Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl
     * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDepartmentType()
     * @generated
     */
    public static final EClass DEPARTMENT_TYPE = eINSTANCE.getDepartmentType();

    /**
     * The meta object literal for the '<em><b>Employees</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DEPARTMENT_TYPE__EMPLOYEES = eINSTANCE.getDepartmentType_Employees();

    /**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DEPARTMENT_TYPE__LOCATION = eINSTANCE.getDepartmentType_Location();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DEPARTMENT_TYPE__NAME = eINSTANCE.getDepartmentType_Name();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DEPARTMENT_TYPE__NUMBER = eINSTANCE.getDepartmentType_Number();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.company.impl.DocumentRootImpl
     * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDocumentRoot()
     * @generated
     */
    public static final EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Company</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__COMPANY = eINSTANCE.getDocumentRoot_Company();

    /**
     * The meta object literal for the '{@link org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl <em>Employee Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl
     * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getEmployeeType()
     * @generated
     */
    public static final EClass EMPLOYEE_TYPE = eINSTANCE.getEmployeeType();

    /**
     * The meta object literal for the '<em><b>Manager</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute EMPLOYEE_TYPE__MANAGER = eINSTANCE.getEmployeeType_Manager();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute EMPLOYEE_TYPE__NAME = eINSTANCE.getEmployeeType_Name();

    /**
     * The meta object literal for the '<em><b>SN</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute EMPLOYEE_TYPE__SN = eINSTANCE.getEmployeeType_SN();

  }

} //CompanyPackageImpl
