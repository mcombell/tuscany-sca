/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.tuscany.das.rdb.test.company.impl;

import org.apache.tuscany.das.rdb.test.company.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CompanyFactoryImpl extends EFactoryImpl implements CompanyFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final CompanyFactoryImpl eINSTANCE = init();

  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CompanyFactoryImpl init()
  {
    try
    {
      CompanyFactoryImpl theCompanyFactory = (CompanyFactoryImpl)EPackage.Registry.INSTANCE.getEFactory("org.apache.tuscany.das.rdb.test/company.xsd"); 
      if (theCompanyFactory != null)
      {
        return theCompanyFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CompanyFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompanyFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case CompanyPackageImpl.COMPANY_TYPE: return (EObject)createCompanyType();
      case CompanyPackageImpl.DATAGRAPH_ROOT: return (EObject)createDatagraphRoot();
      case CompanyPackageImpl.DEPARTMENT_TYPE: return (EObject)createDepartmentType();
      case CompanyPackageImpl.DOCUMENT_ROOT: return (EObject)createDocumentRoot();
      case CompanyPackageImpl.EMPLOYEE_TYPE: return (EObject)createEmployeeType();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompanyType createCompanyType()
  {
    CompanyTypeImpl companyType = new CompanyTypeImpl();
    return companyType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatagraphRoot createDatagraphRoot()
  {
    DatagraphRootImpl datagraphRoot = new DatagraphRootImpl();
    return datagraphRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DepartmentType createDepartmentType()
  {
    DepartmentTypeImpl departmentType = new DepartmentTypeImpl();
    return departmentType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject createDocumentRoot()
  {
    EObject documentRoot = super.create(CompanyPackageImpl.Literals.DOCUMENT_ROOT);
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EmployeeType createEmployeeType()
  {
    EmployeeTypeImpl employeeType = new EmployeeTypeImpl();
    return employeeType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompanyPackageImpl getCompanyPackageImpl()
  {
    return (CompanyPackageImpl)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static CompanyPackageImpl getPackage()
  {
    return CompanyPackageImpl.eINSTANCE;
  }

} //CompanyFactoryImpl
