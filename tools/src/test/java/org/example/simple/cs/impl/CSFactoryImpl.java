/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.example.simple.cs.impl;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

import org.apache.tuscany.sdo.SDOFactory;

import org.apache.tuscany.sdo.impl.FactoryBase;

import org.apache.tuscany.sdo.model.ModelFactory;

import org.apache.tuscany.sdo.model.impl.ModelFactoryImpl;

import org.apache.tuscany.sdo.util.SDOUtil;

import org.example.simple.cs.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CSFactoryImpl extends FactoryBase implements CSFactory
{

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String NAMESPACE_URI = "http://www.example.com/simpleCS";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String NAMESPACE_PREFIX = "cs";	
  public static final int QUOTE = 1;	
  public static final int QUOTE_BASE = 2;
  
  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CSFactoryImpl()
  {
    super(NAMESPACE_URI, NAMESPACE_PREFIX);
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataObject create(int typeNumber)
  {
    switch (typeNumber)
    {
      case QUOTE: return (DataObject)createQuote();
      case QUOTE_BASE: return (DataObject)createQuoteBase();
      default:
        return super.create(typeNumber);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Quote createQuote()
  {
    QuoteImpl quote = new QuoteImpl();
    return quote;
  }
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QuoteBase createQuoteBase()
  {
    QuoteBaseImpl quoteBase = new QuoteBaseImpl();
    return quoteBase;
  }
  
  // Following creates and initializes SDO metadata for the supported types.			
  protected Type quoteType = null;

  public Type getQuote()
  {
    return quoteType;
  }
    
  protected Type quoteBaseType = null;

  public Type getQuoteBase()
  {
    return quoteBaseType;
  }
  

  private static boolean isInited = false;

  public static CSFactoryImpl init()
  {
    if (isInited) return (CSFactoryImpl)FactoryBase.getStaticFactory(CSFactoryImpl.NAMESPACE_URI);
    CSFactoryImpl theCSFactoryImpl = new CSFactoryImpl();
    isInited = true;

    // Initialize simple dependencies
    SDOUtil.registerStaticTypes(SDOFactory.class);
    SDOUtil.registerStaticTypes(ModelFactory.class);

    // Create package meta-data objects
    theCSFactoryImpl.createMetaData();

    // Initialize created meta-data
    theCSFactoryImpl.initializeMetaData();

    // Mark meta-data to indicate it can't be changed
    //theCSFactoryImpl.freeze(); //FB do we need to freeze / should we freeze ????

    return theCSFactoryImpl;
  }
  
  private boolean isCreated = false;

  public void createMetaData()
  {
    if (isCreated) return;
    isCreated = true;	


    quoteType = createType(false, QUOTE);
    createProperty(true, quoteType, QuoteImpl.SYMBOL);
    createProperty(true, quoteType, QuoteImpl.COMPANY_NAME);
    createProperty(true, quoteType, QuoteImpl.PRICE);
    createProperty(true, quoteType, QuoteImpl.OPEN1);
    createProperty(true, quoteType, QuoteImpl.HIGH);
    createProperty(true, quoteType, QuoteImpl.LOW);
    createProperty(true, quoteType, QuoteImpl.VOLUME);
    createProperty(true, quoteType, QuoteImpl.CHANGE1);
    createProperty(false, quoteType, QuoteImpl.QUOTES);

    quoteBaseType = createType(false, QUOTE_BASE);
    createProperty(false, quoteBaseType, QuoteBaseImpl.CHANGES);
  }
  
  private boolean isInitialized = false;

  public void initializeMetaData()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Obtain other dependent packages
    ModelFactoryImpl theModelPackageImpl = (ModelFactoryImpl)FactoryBase.getStaticFactory(ModelFactoryImpl.NAMESPACE_URI);
    Property property = null;

    // Add supertypes to classes
    addSuperType(quoteBaseType, quoteType);

    // Initialize classes and features; add operations and parameters
    initializeType(quoteType, Quote.class, "Quote");

    property = (Property)quoteType.getProperties().get(QuoteImpl.SYMBOL);
    initializeProperty(property, theModelPackageImpl.getString(), "symbol", null, 1, 1, Quote.class, false, false, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.COMPANY_NAME);
    initializeProperty(property, theModelPackageImpl.getString(), "companyName", null, 1, 1, Quote.class, false, false, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.PRICE);
    initializeProperty(property, theModelPackageImpl.getDecimal(), "price", null, 1, 1, Quote.class, false, false, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.OPEN1);
    initializeProperty(property, theModelPackageImpl.getDecimal(), "open1", null, 1, 1, Quote.class, false, false, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.HIGH);
    initializeProperty(property, theModelPackageImpl.getDecimal(), "high", null, 1, 1, Quote.class, false, false, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.LOW);
    initializeProperty(property, theModelPackageImpl.getDecimal(), "low", null, 1, 1, Quote.class, false, false, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.VOLUME);
    initializeProperty(property, theModelPackageImpl.getDouble(), "volume", null, 1, 1, Quote.class, false, true, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.CHANGE1);
    initializeProperty(property, theModelPackageImpl.getDouble(), "change1", null, 1, 1, Quote.class, false, true, false);

    property = (Property)quoteType.getProperties().get(QuoteImpl.QUOTES);
    initializeProperty(property, this.getQuote(), "quotes", null, 0, -1, Quote.class, false, false, false, true , null);

    initializeType(quoteBaseType, QuoteBase.class, "QuoteBase");

    property = (Property)quoteBaseType.getProperties().get(QuoteBaseImpl.CHANGES);
    initializeProperty(property, theModelPackageImpl.getChangeSummaryType(), "changes", null, 1, 1, QuoteBase.class, false, false, false, false , null);

    createXSDMetaData(theModelPackageImpl);
  }
    
  protected void createXSDMetaData(ModelFactoryImpl theModelPackageImpl)
  {
//  TODO T-153 regenerate this code when issue with isProxy is fixed createXSDMetaData()
//    super.createXSDMetaData();
    
    Property property = null;
    
    property = createGlobalProperty
      ("stockQuote",
      this.getQuoteBase(),
       new String[]
       {
       "kind", "element",
       "name", "stockQuote",
       "namespace", "##targetNamespace"
       });
                
    addXSDMapping
      (quoteType,
       new String[] 
       {
       "name", "Quote",
       "kind", "elementOnly"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.SYMBOL),
       new String[]
       {
       "kind", "element",
       "name", "symbol"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.COMPANY_NAME),
       new String[]
       {
       "kind", "element",
       "name", "companyName"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.PRICE),
       new String[]
       {
       "kind", "element",
       "name", "price"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.OPEN1),
       new String[]
       {
       "kind", "element",
       "name", "open1"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.HIGH),
       new String[]
       {
       "kind", "element",
       "name", "high"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.LOW),
       new String[]
       {
       "kind", "element",
       "name", "low"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.VOLUME),
       new String[]
       {
       "kind", "element",
       "name", "volume"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.CHANGE1),
       new String[]
       {
       "kind", "element",
       "name", "change1"
       });

    addXSDMapping
      ((Property)quoteType.getProperties().get(QuoteImpl.QUOTES),
       new String[]
       {
       "kind", "element",
       "name", "quotes"
       });

    addXSDMapping
      (quoteBaseType,
       new String[] 
       {
       "name", "QuoteBase",
       "kind", "elementOnly"
       });

    addXSDMapping
      ((Property)quoteBaseType.getProperties().get(QuoteBaseImpl.CHANGES),
       new String[]
       {
       "kind", "element",
       "name", "changes"
       });

  }
  
} //CSFactoryImpl
