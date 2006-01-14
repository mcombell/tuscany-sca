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
package org.apache.tuscany.das.rdb.test.company;

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
 * @see org.apache.tuscany.das.rdb.test.company.CompanyFactory
 * @model kind="package"
 * @generated
 */
public interface CompanyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "company";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.apache.tuscany.das.rdb.test/company.xsd";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "company";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CompanyPackage eINSTANCE = org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyTypeImpl
	 * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getCompanyType()
	 * @generated
	 */
	int COMPANY_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Departments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY_TYPE__DEPARTMENTS = 0;

	/**
	 * The feature id for the '<em><b>Employee Of The Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY_TYPE__EMPLOYEE_OF_THE_MONTH = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY_TYPE__NAME = 2;

	/**
	 * The number of structural features of the the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl <em>Department Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.company.impl.DepartmentTypeImpl
	 * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDepartmentType()
	 * @generated
	 */
	int DEPARTMENT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Employees</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPARTMENT_TYPE__EMPLOYEES = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPARTMENT_TYPE__LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPARTMENT_TYPE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPARTMENT_TYPE__NUMBER = 3;

	/**
	 * The number of structural features of the the '<em>Department Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPARTMENT_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.company.impl.DocumentRootImpl
	 * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 2;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Company</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPANY = 3;

	/**
	 * The number of structural features of the the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl <em>Employee Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.tuscany.das.rdb.test.company.impl.EmployeeTypeImpl
	 * @see org.apache.tuscany.das.rdb.test.company.impl.CompanyPackageImpl#getEmployeeType()
	 * @generated
	 */
	int EMPLOYEE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_TYPE__MANAGER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_TYPE__NAME = 1;

	/**
	 * The feature id for the '<em><b>SN</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_TYPE__SN = 2;

	/**
	 * The number of structural features of the the '<em>Employee Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_TYPE_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.CompanyType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyType
	 * @generated
	 */
	EClass getCompanyType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getDepartments <em>Departments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Departments</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyType#getDepartments()
	 * @see #getCompanyType()
	 * @generated
	 */
	EReference getCompanyType_Departments();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth <em>Employee Of The Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Employee Of The Month</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth()
	 * @see #getCompanyType()
	 * @generated
	 */
	EAttribute getCompanyType_EmployeeOfTheMonth();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyType#getName()
	 * @see #getCompanyType()
	 * @generated
	 */
	EAttribute getCompanyType_Name();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType <em>Department Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Department Type</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DepartmentType
	 * @generated
	 */
	EClass getDepartmentType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Employees</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getEmployees()
	 * @see #getDepartmentType()
	 * @generated
	 */
	EReference getDepartmentType_Employees();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getLocation()
	 * @see #getDepartmentType()
	 * @generated
	 */
	EAttribute getDepartmentType_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getName()
	 * @see #getDepartmentType()
	 * @generated
	 */
	EAttribute getDepartmentType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.DepartmentType#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DepartmentType#getNumber()
	 * @see #getDepartmentType()
	 * @generated
	 */
	EAttribute getDepartmentType_Number();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.apache.tuscany.das.rdb.test.company.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.apache.tuscany.das.rdb.test.company.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.apache.tuscany.das.rdb.test.company.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.apache.tuscany.das.rdb.test.company.DocumentRoot#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Company</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.DocumentRoot#getCompany()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Company();

	/**
	 * Returns the meta object for class '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType <em>Employee Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Employee Type</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.EmployeeType
	 * @generated
	 */
	EClass getEmployeeType();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager <em>Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manager</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.EmployeeType#isManager()
	 * @see #getEmployeeType()
	 * @generated
	 */
	EAttribute getEmployeeType_Manager();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.EmployeeType#getName()
	 * @see #getEmployeeType()
	 * @generated
	 */
	EAttribute getEmployeeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.apache.tuscany.das.rdb.test.company.EmployeeType#getSN <em>SN</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SN</em>'.
	 * @see org.apache.tuscany.das.rdb.test.company.EmployeeType#getSN()
	 * @see #getEmployeeType()
	 * @generated
	 */
	EAttribute getEmployeeType_SN();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CompanyFactory getCompanyFactory();

} //CompanyPackage
