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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getDepartments <em>Departments</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth <em>Employee Of The Month</em>}</li>
 *   <li>{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.tuscany.das.rdb.test.company.CompanyPackage#getCompanyType()
 * @model extendedMetaData="name='CompanyType' kind='elementOnly'"
 * @generated
 */
public interface CompanyType {
	/**
	 * Returns the value of the '<em><b>Departments</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.tuscany.das.rdb.test.company.DepartmentType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Departments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Departments</em>' containment reference list.
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyPackage#getCompanyType_Departments()
	 * @model type="org.apache.tuscany.das.rdb.test.company.DepartmentType" containment="true" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='departments'"
	 * @generated
	 */
	List getDepartments();

	/**
	 * Returns the value of the '<em><b>Employee Of The Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Employee Of The Month</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Employee Of The Month</em>' attribute.
	 * @see #setEmployeeOfTheMonth(String)
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyPackage#getCompanyType_EmployeeOfTheMonth()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='attribute' name='employeeOfTheMonth'"
	 * @generated
	 */
	String getEmployeeOfTheMonth();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getEmployeeOfTheMonth <em>Employee Of The Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Employee Of The Month</em>' attribute.
	 * @see #getEmployeeOfTheMonth()
	 * @generated
	 */
	void setEmployeeOfTheMonth(String value);

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
	 * @see org.apache.tuscany.das.rdb.test.company.CompanyPackage#getCompanyType_Name()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.apache.tuscany.das.rdb.test.company.CompanyType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // CompanyType
