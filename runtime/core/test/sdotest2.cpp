/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/* $Rev$ $Date$ */

#include <stdio.h>

#pragma warning(disable:4786)

#include <iostream>
#include <fstream>
using namespace std;



#include "sdotest.h"



using namespace commonj::sdo;

DataObjectPtr sdotest::scopetest2()
{

    DataFactoryPtr mdg  = DataFactory::getDataFactory();

    mdg->addType("myspace","Root");

    mdg->addType("myspace","Company");

    mdg->addPropertyToType("myspace","Company","name",
                       "commonj.sdo","String", false, false, false);

    mdg->addPropertyToType("myspace","Company","id",
                       "commonj.sdo","String", false, false, false);

    mdg->addPropertyToType("myspace","Root","company",
                       "myspace","Company", false, false, true);

    const Type& tcc = mdg->getType("myspace","Root");

    DataObjectPtr dop = mdg->create((Type&)tcc);

    DataObjectPtr co = dop->createDataObject("company");

    return dop;
}

int sdotest::scopetest()
{
    DataObjectPtr dob;
    dob = scopetest2();

    // fails on close?
    return 1;
}

int sdotest::xsdtosdo()
{
const char* value;

value = SDOUtils::XSDToSDO("string");
if (!silent)cout << "XSDToSDO(string)=" << value << endl;
if (strcmp(value,"String")) return 0;

value = SDOUtils::XSDToSDO("int");
if (!silent)cout << "XSDToSDO(int)="<< value << endl;
if (strcmp(value,"Integer")) return 0;

value = SDOUtils::XSDToSDO("notinlist");
if (!silent)cout << "XSDToSDO(notinlist)="<< value << endl;
if (strcmp(value,"String")) return 0;

value = SDOUtils::XSDToSDO("");
if (!silent)cout << "XSDToSDO(\"\")="<< value << endl;
if (strcmp(value,"String")) return 0;

value = SDOUtils::XSDToSDO(0);
if (!silent)cout << "XSDToSDO(0)="<< value << endl;
if (strcmp(value,"String")) return 0;

value = SDOUtils::SDOToXSD("String");
if (!silent)cout << "SDOToXSD(String)="<< value << endl;
if (strcmp(value,"string")) return 0;

value = SDOUtils::SDOToXSD("Long");
if (!silent)cout << "SDOToXSD(Long)="<< value << endl;
if (strcmp(value,"unsignedLong")) return 0;

value = SDOUtils::SDOToXSD("notinlist");
if (!silent)cout << "SDOToXSD(notinlist)="<< value << endl;
if (strcmp(value,"string")) return 0;

value = SDOUtils::SDOToXSD("");
if (!silent)cout << "SDOToXSD(\"\")="<< value << endl;
if (strcmp(value,"string")) return 0;

value = SDOUtils::SDOToXSD(0);
if (!silent)cout << "SDOToXSD(0)="<< value << endl;
if (strcmp(value,"string")) return 0;

return 1;

}

// The compatibleFactory() test creates a series of factories that are either
// identical or very similar so we need a function that populates a data
// factory with the common part.

void sdotest::populateFactory(DataFactoryPtr dfp)
{
   
   dfp->addType("Namespace", "Project");
   dfp->addType("Namespace", "WorkPackage");
   dfp->addType("Namespace", "LineItem");
   dfp->addType("Namespace", "StringHolder");
 
   dfp->addType("Namespace", "Root");
   dfp->addPropertyToType("Namespace","Root","project",
                         "Namespace","Project", false, false, true);
 dfp->addPropertyToType("Namespace","Project","wp",
                         "Namespace","WorkPackage", false, false, false);

   dfp->addPropertyToType("Namespace","StringHolder","value",
                         "commonj.sdo","String", false, false, false);
   
   dfp->addPropertyToType("Namespace","StringHolder","proj",
                         "Namespace","Project", false, false, false);

   dfp->addPropertyToType("Namespace","Project","id",
                         "commonj.sdo","String", false, false, false);
    
   dfp->addPropertyToType("Namespace","Project","string",
                         "Namespace","StringHolder", false, false, true);
 
   dfp->addPropertyToType("Namespace","WorkPackage","name",
                         "commonj.sdo","String", false, false, false);
    

   dfp->addPropertyToType("Namespace","WorkPackage","string",
                         "Namespace","StringHolder", false, false, true);

   dfp->addPropertyToType("Namespace","LineItem","itemname",
                         "commonj.sdo","String", false, false, false);

   dfp->addPropertyToType("Namespace","LineItem","string",
                         "Namespace","StringHolder", false, false, true);

   dfp->addPropertyToType("Namespace","Project","packages",
                         "Namespace","WorkPackage", true, false, true);

   dfp->addPropertyToType("Namespace","WorkPackage","lineitems",
                         "Namespace","LineItem", true, false, true);
   
}

int sdotest::compatiblefactory()
{
    DataFactoryPtr f1 = DataFactory::getDataFactory();
    populateFactory(f1);

    // project
    //    id (string)
    //    string (StringHolder)
    //    workpackages (WorkPackage)
    
    // workpackage
    //     name (string)
    //     string (StringHolder)
    //     lineitems (LineItem)

    // lineitem
    //     itemname (string)
    

    // factories 1 and 2 are compatible

    DataFactoryPtr f2 = DataFactory::getDataFactory();
    populateFactory(f2);    

    // factory 3 has no project type

    DataFactoryPtr f3 = DataFactory::getDataFactory();

    f3->addType("Namespace","WorkPackage");
    f3->addType("Namespace","LineItem");
    f3->addType("Namespace","StringHolder");
 
    f3->addType("Namespace","Root");
    f3->addPropertyToType("Namespace","Root","project",
                           "Namespace","WorkPackage", false, false, true);

 
    f3->addPropertyToType("Namespace","StringHolder","value",
                           "commonj.sdo","String", false, false, false);
   
    f3->addPropertyToType("Namespace","WorkPackage","name",
                           "commonj.sdo","String", false, false, false);
    

    f3->addPropertyToType("Namespace","WorkPackage","string",
                           "Namespace","StringHolder", false, false, true);

    f3->addPropertyToType("Namespace","LineItem","itemname",
                           "commonj.sdo","String", false, false, false);

    f3->addPropertyToType("Namespace","LineItem","string",
                           "Namespace","StringHolder", false, false, true);


    f3->addPropertyToType("Namespace","WorkPackage","lineitems",
                           "Namespace","LineItem", true, false, true);


    // factory 4 looks compatible - but stringholder is missing

    DataFactoryPtr f4 = DataFactory::getDataFactory();
    
    f4->addType("Namespace","Project");
    f4->addType("Namespace","WorkPackage");
    f4->addType("Namespace","LineItem");
 
    f4->addType("Namespace","Root");
    f4->addPropertyToType("Namespace","Root","project",
                           "Namespace","Project", false, false, true);

 
    f4->addPropertyToType("Namespace","Project","id",
                           "commonj.sdo","String", false, false, false);
    
 
    f4->addPropertyToType("Namespace","WorkPackage","name",
                           "commonj.sdo","String", false, false, false);
    

    f4->addPropertyToType("Namespace","LineItem","itemname",
                           "commonj.sdo","String", false, false, false);

    f4->addPropertyToType("Namespace","Project","packages",
                           "Namespace","WorkPackage", true, false, true);

    f4->addPropertyToType("Namespace","WorkPackage","lineitems",
                           "Namespace","LineItem", true, false, true);


    // factory 5 looks compatible, but the lists are not lists

    DataFactoryPtr f5 = DataFactory::getDataFactory();
    
    f5->addType("Namespace","Project");
    f5->addType("Namespace","WorkPackage");
    f5->addType("Namespace","LineItem");
    f5->addType("Namespace","StringHolder");
 
    f5->addType("Namespace","Root");
    f5->addPropertyToType("Namespace","Root","project",
                           "Namespace","Project", false, false, true);

 
    f5->addPropertyToType("Namespace","StringHolder","value",
                           "commonj.sdo","String", false, false, false);
   
    f5->addPropertyToType("Namespace","Project","id",
                           "commonj.sdo","String", false, false, false);
    
    f5->addPropertyToType("Namespace","Project","string",
                           "Namespace","StringHolder", false, false, true);
 
    f5->addPropertyToType("Namespace","WorkPackage","name",
                           "commonj.sdo","String", false, false, false);
    

    f5->addPropertyToType("Namespace","WorkPackage","string",
                           "Namespace","StringHolder", false, false, true);

    f5->addPropertyToType("Namespace","LineItem","itemname",
                           "commonj.sdo","String", false, false, false);

    f5->addPropertyToType("Namespace","LineItem","string",
                           "Namespace","StringHolder", false, false, true);

    f5->addPropertyToType("Namespace","Project","packages",
                           "Namespace","WorkPackage", false, false, true);

    f5->addPropertyToType("Namespace","WorkPackage","lineitems",
                           "Namespace","LineItem", false, false, true);

    // factory 6 looks OK, but the lineitems have no itemname field

    DataFactoryPtr f6 = DataFactory::getDataFactory();
    
    f6->addType("Namespace","Project");
    f6->addType("Namespace","WorkPackage");
    f6->addType("Namespace","LineItem");
    f6->addType("Namespace","StringHolder");
 
    f6->addType("Namespace","Root");
    f6->addPropertyToType("Namespace","Root","project",
                           "Namespace","Project", false, false, true);

 
    f6->addPropertyToType("Namespace","StringHolder","value",
                           "commonj.sdo","String", false, false, false);
   
    f6->addPropertyToType("Namespace","Project","id",
                           "commonj.sdo","String", false, false, false);
    
    f6->addPropertyToType("Namespace","Project","string",
                           "Namespace","StringHolder", false, false, true);
 
    f6->addPropertyToType("Namespace","WorkPackage","name",
                           "commonj.sdo","String", false, false, false);
    

    f6->addPropertyToType("Namespace","WorkPackage","string",
                           "Namespace","StringHolder", false, false, true);

    f6->addPropertyToType("Namespace","LineItem","string",
                           "Namespace","StringHolder", false, false, true);

    f6->addPropertyToType("Namespace","Project","packages",
                           "Namespace","WorkPackage", true, false, true);

    f6->addPropertyToType("Namespace","WorkPackage","lineitems",
                           "Namespace","LineItem", true, false, true);


    // factory 7 looks OK, but the identifiers are ints
    
    
    DataFactoryPtr f7 = DataFactory::getDataFactory();

    f7->addType("Namespace","Project");
    f7->addType("Namespace","WorkPackage");
    f7->addType("Namespace","LineItem");
    f7->addType("Namespace","StringHolder");
 
    f7->addType("Namespace","Root");
    f7->addPropertyToType("Namespace","Root","project",
                           "Namespace","Project", false, false, true);

    f7->addPropertyToType("Namespace","StringHolder","value",
                           "commonj.sdo","String", false, false, true);
   
    f7->addPropertyToType("Namespace","Project","id",
                           "commonj.sdo","Integer", false, false, false);
    
    f7->addPropertyToType("Namespace","Project","string",
                           "Namespace","StringHolder", false, false, true);
 
    f7->addPropertyToType("Namespace","WorkPackage","name",
                           "commonj.sdo","String", false, false, false);
    

    f7->addPropertyToType("Namespace","WorkPackage","string",
                           "Namespace","StringHolder", false, false, true);

    f7->addPropertyToType("Namespace","LineItem","itemname",
                           "commonj.sdo","Integer", false, false, false);

    f7->addPropertyToType("Namespace","LineItem","string",
                           "Namespace","StringHolder", false, false, true);

    f7->addPropertyToType("Namespace","Project","packages",
                           "Namespace","WorkPackage", true, false, true);

    f7->addPropertyToType("Namespace","WorkPackage","lineitems",
                           "Namespace","LineItem", true, false, true);


    // create a tree from Root in factory 1

    DataObjectPtr root = f1->create("Namespace","Root");
    DataObjectPtr project = root->createDataObject("project");
    project->setCString("id","The TTP Project");
    DataObjectPtr str = project->createDataObject("string");
    str->setCString("value","The Recursive Acronym Project");
    DataObjectPtr wp1 = project->createDataObject("packages");
    DataObjectPtr wp2 = project->createDataObject("packages");
    wp1->setCString("name","Work Package 1");
    wp2->setCString("name","Work Package 2");
    DataObjectPtr li1 = wp1->createDataObject("lineitems");
    DataObjectPtr li2 = wp1->createDataObject("lineitems");
    DataObjectPtr li3 = wp2->createDataObject("lineitems");
    DataObjectPtr li4 = wp2->createDataObject("lineitems");
    li1->setCString("itemname","LineItem 1");
    li2->setCString("itemname","LineItem 2");
    li3->setCString("itemname","LineItem 3");
    li4->setCString("itemname","LineItem 4");
    DataObjectPtr str1 = li1->createDataObject("string");
    DataObjectPtr str2 = li2->createDataObject("string");
    DataObjectPtr str3 = li3->createDataObject("string");
    DataObjectPtr str4 = li4->createDataObject("string");
    str1->setCString("value","String1");
    str2->setCString("value","String2");
    str3->setCString("value","String3");
    str4->setCString("value","String4");


//    cout << "DATA OBJECTS FROM FACTORY 1" << endl;
//    printDataObject(root);

    if (!transferto(root,f2, false)) return 0;
    if (!transferto(root,f3, true)) return 0;
    // Following 4 tests do not now cause an error
    if (!transferto(root,f4, false)) return 0; 
    if (!transferto(root,f5, false)) return 0;
    if (!transferto(root,f6, false)) return 0;
    if (!transferto(root,f7, false)) return 0;

    // finally, lets move one which has a parent and cant be moved.

    try {
        DataObjectPtr project = root->getDataObject("project");

        DataObjectPtr root2 = f2->create("Namespace","Root");
    
        root2->setDataObject("project", project);
        return 0;
    }
    catch (SDORuntimeException e)
    {
        //cout << "Compatibility test correctly returned an error :" ;
        //cout << e.getMessageText() << endl;
        return 1;
    }
  
}

int sdotest::transferto(DataObjectPtr root, DataFactoryPtr f2, bool expecterror)
{
    try {

        // transfer the tree to factory 2...
        DataObjectPtr rproject = root->getDataObject("project");

        DataObjectPtr project = CopyHelper::copy(rproject);

        DataObjectPtr root2 = f2->create("Namespace","Root");
    
        root2->setDataObject("project", project);

        //cout << "DATA OBJECTS TRANSFERRED" << endl;

        //printDataObject(root2);
        
        if (expecterror)
        {
            return 0;
        }
        return 1;
    }
    catch (SDORuntimeException e)
    {
        if (expecterror){
            //cout << "Compatibility test correctly returned an error :" ;
            //cout << e.getMessageText() << endl;
            return 1;
        }
        else
        {
            if (!silent)cout << "Compatibility test failed whilst transferring " << e << endl;
            return 0;
        }
    }
}


int sdotest::b48602()
{


    int i,j;

    try {


        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
    
        xsh->defineFile("b48602.xsd");

        if ((i = xsh->getErrorCount()) > 0)
        {
            if (!silent)
            {
                cout << "PROBLEM: b48602 XSD reported some errors:" << endl;
                for (j=0;j<i;j++)
                {
                    const char *m = xsh->getErrorMessage(j);
                    if (m != 0) cout << m;
                    cout << endl;
                }
            }
            return 0;
        }
        return 0;
    }
    catch (SDOTypeNotFoundException e)
    {
        return 1;
    }
    catch (SDORuntimeException e)
    {
        return 1;
    }

}

int sdotest::testany(const char* xsd, 
                     const char* acceptedxsderror,
                     const char* xml,
                     const char* acceptedxmlerror)
{


    unsigned int i,j;
    int rc;

    try {

        char *c;
        

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
    
        if (xsd)
        {
            char * name1 = new char[strlen(xsd)+5];
            char * name3 = new char[strlen(xsd)+5];
            strcpy(name1,xsd);
            while ((c = strchr(name1,'.')) != 0)*c = '_';
            strcpy(name3,name1);
            strcat(name1,".dat");
            strcat(name3,".txt");


            FILE *f1 = fopen(name1,"w+");
            if (f1 == 0)
            {
                if (!silent)cout << "Unable to open " << name1 << endl;
                delete[] name1;
                delete[] name3;
                return 0;
            }

            xsh->defineFile(xsd);

            if ((i = xsh->getErrorCount()) > 0)
            {
                if (acceptedxsderror != 0)
                {
                    for (j=0;j<i;j++)
                    {
                        if (!strcmp(acceptedxsderror,xsh->getErrorMessage(j)))
                        {
                            fclose(f1);
                            delete[] name1;
                            delete[] name3;
                            return 1;
                        }
                    }
                }
                if (!silent)
                {
                    cout << "PROBLEM: Testany XSD reported some errors:" << endl;
                    for (j=0;j<i;j++)
                    {
                        const char *m = xsh->getErrorMessage(j);
                        if (m != 0) cout << m;
                        cout << endl;
                    }
                }
                fclose(f1);
                delete[] name1;
                delete[] name3;
                return 0;
            }


            TypeList tl = mdg->getTypes();

            fprintf(f1, "***** TYPES BEFORE RESOLVE **********************************\n");

            for (i=0;i<tl.size();i++)
            {
                fprintf(f1,"Type:%s#%s\n",tl[i].getURI(),tl[i].getName());
                PropertyList pl = tl[i].getProperties();
                for (unsigned int j=0;j<pl.size();j++)
                {
                    fprintf(f1,"Property:%s ",pl[j].getName());
                    if (pl[j].isMany())
                         fprintf(f1, "(many) ");
                    fprintf(f1,  " of type %s\n",pl[j].getType().getName());
                }
            }

            fprintf(f1, "*******************************END TYPES******************\n");

            fclose(f1);
        

            rc = comparefiles(name1,name3);
            
            delete[] name1;
            delete[] name3;
            
            if (rc == 0)return rc;
    
        }

        if (xml == 0 || strlen(xml) == 0) return 1;

        char * name2 = new char[strlen(xml)+5];
        char * name4 = new char[strlen(xml)+5];
        strcpy(name2,xml);
        while ((c = strchr(name2,'.')) != 0)*c = '_';
        strcpy(name4,name2);
      
        strcat(name2,".dat");
        strcat(name4,".txt");

        FILE *f2 = fopen(name2,"w+");
        if (f2 == 0)
        {
            if (!silent)cout << "Unable to open " << name2 << endl;
            delete[] name2;
            delete[] name4;
            return 0;
        }

        XMLHelperPtr xmh = HelperProvider::getXMLHelper(mdg);
 
        XMLDocumentPtr doc = xmh->loadFile(xml);

        if ((i = xmh->getErrorCount()) > 0)
        {
            if (acceptedxmlerror != 0)
            {
                for (j=0;j<i;j++)
                {
                    if (!strcmp(acceptedxmlerror,xmh->getErrorMessage(j)))
                    {
                        fclose(f2);
                        delete[] name2;
                        delete[] name4;
                        return 1;
                    }
                }
            }
            if (!silent)
            {
                cout << "TestAny XML found errors:" << endl;
                for (j=0;j<i;j++)
                {
                    const char *m = xmh->getErrorMessage(j);
                    if (m != 0) cout << m;
                    cout << endl;
                }
            }
            fclose(f2);
            delete[] name2;
            delete[] name4;
            return 0;
        }

        else {
            TypeList tl = mdg->getTypes();

            fprintf(f2, "***** TYPES AFTER RESOLVE*********************************\n");

            for (i=0;i<tl.size();i++)
            {
                fprintf(f2,"Type:%s#%s\n",tl[i].getURI(),tl[i].getName());
                PropertyList pl = tl[i].getProperties();
                for (unsigned int j=0;j<pl.size();j++)
                {
                    fprintf(f2, "Property:%s ",pl[j].getName());
                    if (pl[j].isMany())
                        fprintf(f2, "(many) ");
                    fprintf(f2,  " of type %s\n",pl[j].getType().getName());
                }
            }

            fprintf(f2, "*******************************END TYPES******************\n");
    

            DataObjectPtr dob = doc->getRootDataObject();
            printDataObject(f2, dob);

            fclose(f2);

            rc = comparefiles(name2,name4);

            delete[] name2;
            delete[] name4;

            return rc;
        }

    }
    catch (SDORuntimeException e)
    {
        if (!silent)cout << "Exception in TestAny" << e << endl;
        return 0;
    }
}



int sdotest::testanytwo(const char* xsd, const char* xsd2,
                        const char* xml)
{
   unsigned int i, j;

   try
   {
      char * name1 = new char[strlen(xsd)+5];
      char * name2 = new char[strlen(xml)+5];
      char * name3 = new char[strlen(xsd)+5];
      char * name4 = new char[strlen(xml)+5];

      strcpy(name1,xsd);
      strcpy(name2,xml);

      char *c;

      while ((c = strchr(name1,'.')) != 0)*c = '_';
      while ((c = strchr(name2,'.')) != 0)*c = '_';

      strcpy(name3,name1);
      strcpy(name4,name2);
    
      strcat(name1,".dat");
      strcat(name2,".dat");
      strcat(name3,".txt");
      strcat(name4,".txt");

      DataFactoryPtr mdg  = DataFactory::getDataFactory();

      XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
    
      if (xsd)
      {
         xsh->defineFile(xsd);

         if ((i = xsh->getErrorCount()) > 0)
         {
            if (!silent)
            {
               cout << "PROBLEM: Testany XSD reported some errors:" << endl;
               for (j=0;j<i;j++)
               {
                  const char *m = xsh->getErrorMessage(j);
                  if (m != 0) cout << m;
                  cout << endl;
               }
            }
            delete[] name1;
            delete[] name2;
            delete[] name3;
            delete[] name4;
            
            return 0;
         }

      }

      if (xsd2)
      {
         xsh->defineFile(xsd2);

         if ((i = xsh->getErrorCount()) > 0)
         {
            if (!silent)
            {
               cout << "PROBLEM: Testany XSD2 reported some errors:" << endl;
               for (j=0;j<i;j++)
               {
                  const char *m = xsh->getErrorMessage(j);
                  if (m != 0) cout << m;
                  cout << endl;
               }
            }

            delete[] name1;
            delete[] name2;
            delete[] name3;
            delete[] name4;

            return 0;
         }
      }

      if (xsd)
      {


         FILE *f1 = fopen(name1,"w+");
         if (f1 == 0)
         {
            if (!silent)cout << "Unable to open " << name1 << endl;

            delete[] name1;
            delete[] name2;
            delete[] name3;
            delete[] name4;

            return 0;
         }

         TypeList tl = mdg->getTypes();

         fprintf(f1,"***** TESTANY ******************************************\n");

         for (i=0;i<tl.size();i++)
         {
            fprintf(f1,"Type:%s#%s\n",tl[i].getURI(),tl[i].getName());
            PropertyList pl = tl[i].getProperties();
            for (unsigned int j=0;j<pl.size();j++)
            {
               fprintf(f1,"Property:%s ",pl[j].getName());
               if (pl[j].isMany())
                  fprintf(f1, "(many) ");
               fprintf(f1,  " of type %s\n",pl[j].getType().getName());
            }
         }

         fprintf(f1,"*******************************END TYPES******************\n");
    
         fclose(f1);

         if (!comparefiles(name1,name3))
         {
            delete[] name1;
            delete[] name2;
            delete[] name3;
            delete[] name4;

            return 0;
         }
      }
    

      if (xml == 0 || strlen(xml) == 0)
      {
         delete[] name1;
         delete[] name2;
         delete[] name3;
         delete[] name4;

         return 1;
      }

      FILE *f2 = fopen(name2,"w+");
      if (f2 == 0)
      {
         if (!silent)cout << "Unable to open " << name2 << endl;
         delete[] name1;
         delete[] name2;
         delete[] name3;
         delete[] name4;

         return 0;
      }

      XMLHelperPtr xmh = HelperProvider::getXMLHelper(mdg);
 
      XMLDocumentPtr doc = xmh->loadFile(xml);

      if ((i = xmh->getErrorCount()) > 0)
      {
         if (!silent)
         {
            cout << "TestAny XML found errors:" << endl;
            for (j=0;j<i;j++)
            {
               const char *m = xmh->getErrorMessage(j);
               if (m != 0) cout << m;
               cout << endl;
            }
         }
         delete[] name1;
         delete[] name2;
         delete[] name3;
         delete[] name4;

         return 0;
      }
      else 
      {
         DataObjectPtr dob = doc->getRootDataObject();
         printDataObject(f2, dob);
      }

      fclose(f2);
      int result = comparefiles(name2,name4);

      delete[] name1;
      delete[] name2;
      delete[] name3;
      delete[] name4;

      return result;
    

   }
   catch (SDORuntimeException e)
   {
      if (!silent)cout << "Exception in TestAnyTwo" << e << endl;

      return 0;
   }
}

int sdotest::openseq()

{

    DataFactoryPtr mdg  = DataFactory::getDataFactory();

    FILE *f = fopen("openseq.dat","w+");
    if (f == 0)
    {
        if (!silent)cout << "Unable to open openseq.dat" << endl;
        return 0;
    }


    mdg->addType("companyNS","CompanyType");
    // employee will be an open sequenced type...
    mdg->addType("companyNS","EmployeeType", /*seq*/true, 
                                             /*open*/true, 
                                             /*abs */ false, 
                                             /*data*/ false);


    /* Now add the properties to the types...*/

    
    const Type& tstring  = mdg->getType("commonj.sdo","String");
    const Type& tcomp    = mdg->getType("companyNS","CompanyType");
    const Type& temp     = mdg->getType("companyNS","EmployeeType");

    
    mdg->addPropertyToType(tcomp,"name",tstring);

    mdg->addPropertyToType(tcomp,"employees",temp,true);
    
    mdg->addPropertyToType(temp, "name",tstring);
 
    DataObjectPtr comp = mdg->create((Type&)tcomp);
    comp->setCString("name","ACME");

    DataObjectPtr emp = mdg->create(temp);

    emp->setCString("name","Albert");
 
    emp->setCString("openstring","Value Of Open String");

    /* now do the same with the employee seq...*/

    SequencePtr seq = emp->getSequence();

    seq->addCString("opensequencedstring","Value of sequenced string");

    const char* c = emp->getCString("openstring");

    fprintf(f, "Open Type string value: %s\n",c);

    c = emp->getCString("opensequencedstring");

    fprintf(f, "Open Sequenced string value: %s\n",c);

    c = seq->getCStringValue(0);

    fprintf(f, "Open Sequenced string value from seq: %s\n",c);

    //cout << "END TEST: OpenSeq ======================================" << endl;
    fclose(f);
    return comparefiles("openseq.dat","openseq.txt");
}

int sdotest::b48601()
{
    return testany("48601.xsd",
        "Schema contains a union which is not yet implemented",0,0);
}

int sdotest::b48686()
{
    return testany("48686.xsd",0,"48686.xml",0);
}

int sdotest::b48736()
{
    return testany("48736.xsd",0,"48736.xml",0);
}


int sdotest::testgenerate(const char* xsd, const char* output)
{

    int i,j;

    try {

        char * name1 = new char[strlen(output) + 5];
        char * name2 = new char[strlen(output) + 5];
        char * c;
        strcpy(name1,output);
        while ((c = strchr(name1,'.')) != 0)*c='_';
        strcpy(name2,name1); 
        strcat(name1,".dat");
        strcat(name2,".txt");


        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
    
        if (xsd)
        {
            xsh->defineFile(xsd);

            if ((i = xsh->getErrorCount()) > 0)
            {

                if (!silent)
                {
                    cout << "PROBLEM: generation XSD reported some errors:" << endl;
                    for (j=0;j<i;j++)
                    {
                        const char *m = xsh->getErrorMessage(j);
                        if (m != 0) cout << m;
                        cout << endl;
                    }
                }
                return 0;
            }
            else
            {
                mdg->generateInterface(name1,"Test");
                return comparefiles(name1,name2);
            }
        }
        return 0;
    }
    catch (SDORuntimeException e)
    {
        if (!silent)cout << "Exception in TestGenerate" << e << endl;
        return 0;
    }
}


int sdotest::emptycs()
{
try {
    DataFactoryPtr mdg  = DataFactory::getDataFactory();

    XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
    xsh->defineFile("company.xsd");

   
    const Type& tstring  = mdg->getType("commonj.sdo","String");
    const Type& tbool    = mdg->getType("commonj.sdo","Boolean");
    const Type& tcs      = mdg->getType("commonj.sdo","ChangeSummary");
    const Type& tcomp    = mdg->getType("companyNS","CompanyType");
    const Type& tdept    = mdg->getType("companyNS","DepartmentType");
    const Type& temp     = mdg->getType("companyNS","EmployeeType");

    
    // create a graph, then save it

    DataObjectPtr comp = mdg->create((Type&)tcomp);
    comp->setCString("name","ACME");

    DataObjectPtr dept = mdg->create((Type&)tdept);
    DataObjectList& dol = comp->getList("departments");
    dol.append(dept);

    dept->setCString("name","Advanced Technologies");
    dept->setCString("location","NY");
    dept->setCString("number","123");

    DataObjectPtr emp1 = mdg->create(temp);
    DataObjectPtr emp2 = mdg->create(temp);
    DataObjectPtr emp3 = mdg->create(temp);

    emp1->setCString("name","John Jones");
    emp1->setCString("SN","E0001");

    emp2->setCString("name","Mary Smith");
    emp2->setCString("SN","E0002");
    emp2->setBoolean("manager",true);

    emp3->setCString("name","Jane Doe");
    emp3->setCString("SN","E0003");

    DataObjectList& dol2 = dept->getList("employees");
    dol2.append(emp1);
    dol2.append(emp2);
    dol2.append(emp3);

    comp->setDataObject("employeeOfTheMonth",emp2);

    // right now, there is no change summary, and logging is
    // off - we expect an empty change summary element

    XMLHelperPtr xmh = HelperProvider::getXMLHelper(mdg);
    XMLDocumentPtr doc = xmh->createDocument(comp,"companyNS","company");
    
    xmh->save(doc,"emptycs1.xml");

    if (!comparefiles("emptycs1.xml","emptycs1.txt"))return 0;

    ChangeSummaryPtr cs = comp->getChangeSummary();

    cs->beginLogging();

    xmh->save(doc,"emptycs2.xml");

    if (!comparefiles("emptycs2.xml","emptycs2.txt"))return 0;

    // now we expect and empty change summary with logging true

    DataObjectPtr emp4 = mdg->create(temp);
    emp4->setCString("name","Al Smith");
    emp4->setCString("SN","E0004");
    emp4->setBoolean("manager",true);

    // first change - create employee 4
    dol2.append(emp4);

    cs->endLogging();

    //serializeChangeSummary(cs);

    xmh->save(doc,"emptycs3.xml");

    if (!comparefiles("emptycs3.xml","emptycs3.txt"))return 0;

    return 1;

    }
    catch (SDORuntimeException e)
    {
        if (!silent)cout << "empty change summary save failed" << e << endl;
        return 0;
    }
}


int sdotest::definetest()
{
    try {

        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
        
        TypeDefinitions* ts = new TypeDefinitions();
        if (ts) delete ts;
        ts = new TypeDefinitions();


        TypeDefinition* td = new TypeDefinition();

        td->setName("MySmallObject");
        td->setUri("MyNameSpace");
        td->setIsDataType(false);

        TypeDefinition* td2 = new TypeDefinition();
        td2->setName("MyOtherObject");
        td2->setUri("MyNameSpace");
        td2->setIsDataType(false);


        PropertyDefinition* pd = new PropertyDefinition();
        pd->setName("MyIntegerProperty");
        pd->setType("commonj.sdo","Integer");

        PropertyDefinition* pd2 = new PropertyDefinition();
        pd2->setName("MyObjectProperty");
        pd2->setType("MyNameSpace","MyOtherObject");
        pd2->setIsMany(true);

        td->addPropertyDefinition(*pd);

        td->addPropertyDefinition(*pd2); 

        ts->addTypeDefinition(*td);
        ts->addTypeDefinition(*td2);

        // should have an object of type MySmallObject, containing
        // a single integer called MyIntegerProperty, and a list of
        // objects called MyObjectProperty - of type MyOtherObject

        xsh->defineTypes(*ts);

        delete td;
        delete td2;
        delete ts;
        delete pd;
        delete pd2;

        DataObjectPtr dob = mdg->create("MyNameSpace","MySmallObject");
        dob->setInteger("MyIntegerProperty",43);

        dob->createDataObject("MyObjectProperty");
        dob->createDataObject("MyObjectProperty");
        DataObjectList& dl = dob->getList("MyObjectProperty");

        if (dl.size() != 2)
        {
            if (!silent) cout << "Define test list size is wrong" << endl;
            return 0;
        }
        int value = dob->getInteger("MyIntegerProperty");
        if (value != 43)
        {
            if (!silent) cout << "Define test integer value is wrong" << endl;
            return 0;
        }

        return 1;
    }
    catch (SDORuntimeException e)
    {
        if (!silent)cout << "define test failed" << endl << e << endl;
        return 0;
    }

}


int sdotest::stocktest()
{
    return sdotest::testany("stock.wsdl",0,"stock.xml",0);
}


/******************************************************
int sdotest::stocktest()
{
    int i,j;

    try {

        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
    
        xsh->defineFile("stock.wsdl");

        if ((i = xsh->getErrorCount()) > 0)
        {
            cout << "PROBLEM: Testany XSD reported some errors:" << endl;
            for (j=0;j<i;j++)
            {
                const char *m = xsh->getErrorMessage(j);
                if (m != 0) cout << m;
                cout << endl;
            }
        }
 
        TypeList tl = mdg->getTypes();

        printf("***** TYPES BEFORE RESOLVE **********************************\n");

        for (i=0;i<tl.size();i++)
        {
            printf("Type:%s#%s\n",tl[i].getURI(),tl[i].getName());
            PropertyList pl = tl[i].getProperties();
            for (int j=0;j<pl.size();j++)
            {
                printf("Property:%s ",pl[j].getName());
                if (pl[j].isMany())
                     printf("(many) ");
                printf(" of type %s\n",pl[j].getType().getName());
            }
        }

        printf("*******************************END TYPES******************\n");

 
        
        XMLHelperPtr xmh = HelperProvider::getXMLHelper(mdg);
 
        XMLDocumentPtr doc = xmh->loadFile("stock.xml");

        if ((i = xmh->getErrorCount()) > 0)
        {
            cout << "TestAny XML found errors:" << endl;
            for (j=0;j<i;j++)
            {
                const char *m = xmh->getErrorMessage(j);
                if (m != 0) cout << m;
                cout << endl;
            }
        }
   

        DataObjectPtr dob = doc->getRootDataObject();
        printDataObject(stdout, dob);

        return 1;

    }
    catch (SDORuntimeException e)
    {
        if (!silent)cout << "Exception in TestAny" << e << endl;
        return 0;
    }
}
**************************************************/


int sdotest::pete()
{


    unsigned int i,j;

    try {

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
 
        xsh->defineFile("pete.xsd");

        if ((i = xsh->getErrorCount()) > 0)
        {
            for (j=0;j<i;j++)
            {
                const char *m = xsh->getErrorMessage(j);
                if (m != 0) cout << m;
                cout << endl;
            }
            return 0;
        }



        XMLHelperPtr xmh = HelperProvider::getXMLHelper(mdg);
 
        XMLDocumentPtr doc = xmh->loadFile("pete.xml");

        if ((i = xmh->getErrorCount()) > 0)
        {
            for (j=0;j<i;j++)
            {
                const char *m = xmh->getErrorMessage(j);
                if (m != 0) cout << m;
                cout << endl;
            }
            return 0;
        }

        TypeList tl = mdg->getTypes();

        //printf("***** TYPES **********************************************\n");

        for (i=0;i<tl.size();i++)
        {
            //printf("Type:%s#%s\n",tl[i].getURI(),tl[i].getName());
            PropertyList pl = tl[i].getProperties();
            for (unsigned int j=0;j<pl.size();j++)
            {
                //printf("Property:%s ",pl[j].getName());
                //if (pl[j].isMany())
                    //printf( "(many) ");
                //printf( " of type %s\n",pl[j].getType().getName());
            }
        }

        //printf( "*******************************END TYPES******************\n");
    

        DataObjectPtr dob = doc->getRootDataObject();
        float f = dob->getFloat("Stock[1]/Last");
        //printf("Float is %2.3f \r\n",f);
        return 1;

    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in Pete" << e << endl;
        return 0;
    }
}

int sdotest::xhtml1()
{


    int i,j;

    try {

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);

        xsh->defineFile("Atom1.0.xsd");
        // SDOUtils::printTypes(cout, xsh->getDataFactory());

        if ((i = xsh->getErrorCount()) > 0)
        {
            for (j=0;j<i;j++)
            {
                const char *m = xsh->getErrorMessage(j);
                if (m != 0) cout << m;
                cout << endl;
            }
            return 0;
        }

        XMLHelperPtr xmh = HelperProvider::getXMLHelper(mdg);
 
        XMLDocumentPtr doc = xmh->loadFile("xhtml_in.xml");

        if ((i = xmh->getErrorCount()) > 0)
        {
           for (j=0;j<i;j++)
            {
                const char *m = xmh->getErrorMessage(j);
                if (m != 0) cout << m;
                cout << endl;
            }
            return 0;
        }

 
        //DataObjectPtr dob = doc->getRootDataObject();

        //ofstream myout("myfile");

        //if (dob)SDOUtils::printDataObject(myout , dob);

        xmh->save(doc,"xhtml_out.xml");

        return comparefiles("xhtml_out.xml" ,"xhtml_out.txt");


    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in xhtml1" << e << endl;
        return 0;
    }
}

int sdotest::testXPath()
{


    try {
        DataFactoryPtr mdg  = DataFactory::getDataFactory();
        
        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
        xsh->defineFile("company.xsd");
        XMLHelperPtr myXMLHelper = HelperProvider::getXMLHelper(mdg);
        XMLDocumentPtr myXMLDocument = myXMLHelper->loadFile("b46617b.xml", "companyNS");
        DataObjectPtr newdob = myXMLDocument->getRootDataObject();

        DataObjectPtr dop = newdob->getDataObject("departments[name='Shoe']/employees[name='Sarah Jones']");
        string x = "departments[name='Shoe']/employees[name='Sarah Jones']";
        dop = newdob->getDataObject(x);

        return 1;
        
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in testXPath" << e << endl;
        return 0;
    }
}

int sdotest::jira945()
{
    try {
        DataFactoryPtr mdg  = DataFactory::getDataFactory(); 

        mdg->addType("myspace","Company");
        mdg->addType("myspace","Employee");
        mdg->addPropertyToType("myspace","Employee","name",
                           "commonj.sdo","String", false, false, false);

        mdg->addPropertyToType("myspace","Company","name",
                           "commonj.sdo","String", false, false, false);    

        mdg->addPropertyToType("myspace","Company","employees",
                           "myspace","Employee", true, false, true);

        const Type& tc = mdg->getType("myspace","Company");

        DataObjectPtr com = mdg->create((Type&)tc);
        com->setCString("name","acme");

        const Type& te = mdg->getType("myspace","Employee");
        DataObjectPtr emp = mdg->create(te);
        emp->setCString("name", "Mr Expendible");

        const int propIndex = tc.getPropertyIndex("employees");

        // This fails with Jira945
        DataObjectList& emps = com->getList(propIndex);

        emps.append(emp);

        return 1;
        
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in test jira945" << e << endl;
        return 0;
    }
}
int sdotest::tuscany963()
{


    try {
        DataFactoryPtr mdg  = DataFactory::getDataFactory();
        
        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
        xsh->defineFile("tuscany963.xsd");
        XMLHelperPtr myXMLHelper = HelperProvider::getXMLHelper(mdg);
        XMLDocumentPtr myXMLDocument = myXMLHelper->loadFile("tuscany963.xml");
        myXMLHelper->save(myXMLDocument, "tuscany963.out.xml");
        

        return comparefiles("tuscany963.out.xml" ,"tuscany963.out.xml.txt");

        
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in tuscany963" << e << endl;
        return 0;
    }
}

int sdotest::tuscany562()
{
    DataFactoryPtr mdg  = DataFactory::getDataFactory();
    mdg->addType("myspace", "Base");
    mdg->addType("myspace", "Extended");
    const Type &base = mdg->getType("myspace", "Base"),
               &extended = mdg->getType("myspace", "Extended");
    mdg->setBaseType(extended, base);
    try
    {
        mdg->setBaseType(extended, extended);
        cout << "Exception should have been thrown in tuscany562 (1)" << endl;
        return 0;
    }
    catch (SDOIllegalArgumentException e)
    {}
    try
    {
        mdg->setBaseType(base, extended);
        cout << "Exception should have been thrown in tuscany562 (2)" << endl;
        return 0;
    }
    catch (SDOIllegalArgumentException e)
    {}
    return 1;
}

int sdotest::upandatom()
{


    try {
        DataFactoryPtr mdg  = DataFactory::getDataFactory();
        DataFactoryPtr df  = DataFactory::getDataFactory();
        
        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
        xsh->defineFile("Atom/Atom1.0.xsd");

        XSDHelperPtr xh = HelperProvider::getXSDHelper(df); 
        xh->defineFile("Atom/Atom1.0.xsd");

        DataObjectPtr block = mdg->create("http://www.w3.org/1999/xhtml", "Block");
        DataObjectPtr div = df->create("http://www.w3.org/1999/xhtml", "div");

        block->setDataObject("div", div);
        return 1;
        
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in upandatom" << e << endl;
        return 0;
    }
}

int sdotest::jira980()
{

   // Load both schema files into a single data factory and create content
   // within an open type root element.
   try
   {
      // Data factory to load both schema files. 
      DataFactoryPtr df_both = DataFactory::getDataFactory();
      XSDHelperPtr xsh_both = HelperProvider::getXSDHelper(df_both);

      // Load a root element definition, then the three animal types.
      xsh_both->defineFile("jira980_jungle.xsd");
      xsh_both->defineFile("jira980_animaltypes.xsd");

      // Create three animals based on the preceding types.
      DataObjectPtr baloo = df_both->create("", "bearType");
      baloo->setCString("name", "Mummy bear");
      baloo->setInteger("weight", 700);

      DataObjectPtr bagheera = df_both->create("", "pantherType");
      bagheera->setCString("name", "Bagheera");
      bagheera->setCString("colour", "inky black");

      DataObjectPtr kaa = df_both->create("", "snakeType");
      kaa->setCString("name", "Kaa");
      kaa->setInteger("length", 25);

      // Create an output document
      XMLHelperPtr xmh_both = HelperProvider::getXMLHelper(df_both);
      XMLDocumentPtr document_both = xmh_both->createDocument();

      DataObjectPtr jungle = document_both->getRootDataObject();

      // Add the three animals as children of the document root. In this test
      // that root will be a "jungle" element.
      jungle->setDataObject("bear", baloo);
      jungle->setDataObject("panther", bagheera);
      jungle->setDataObject("snake", kaa);

      xmh_both->save(document_both, "jira980_jungle_out.xml");
      if (!comparefiles("jira980_jungle_out.txt" ,"jira980_jungle_out.xml"))
      {
         return 0;
      }
   }
   catch (SDORuntimeException e)
   {
      cout << "Exception in jira980" << e << endl;
      return 0;
   }


   // Load the schema files into two different data factories and then create
   // content within an open type root element.
   try
   {
      // Load the schema files into two different data factories.
      DataFactoryPtr df_1 = DataFactory::getDataFactory();
      DataFactoryPtr df_2 = DataFactory::getDataFactory();

      XSDHelperPtr xsh_1 = HelperProvider::getXSDHelper(df_1);
      XSDHelperPtr xsh_2 = HelperProvider::getXSDHelper(df_2);

      xsh_1->defineFile("jira980_jungle.xsd");
      xsh_2->defineFile("jira980_animaltypes.xsd");

      // Create three animals based on the preceding types.
      DataObjectPtr baloo = df_2->create("", "bearType");
      baloo->setCString("name", "Mummy bear");
      baloo->setInteger("weight", 700);

      DataObjectPtr bagheera = df_2->create("", "pantherType");
      bagheera->setCString("name", "Bagheera");
      bagheera->setCString("colour", "inky black");

      DataObjectPtr kaa = df_2->create("", "snakeType");
      kaa->setCString("name", "Kaa");
      kaa->setInteger("length", 25);

      // Create an output document
      XMLHelperPtr xmh_1 = HelperProvider::getXMLHelper(df_1);
      XMLDocumentPtr document_1 = xmh_1->createDocument();

      DataObjectPtr jungle = document_1->getRootDataObject();

      // Add the three animals as children of the document root. In this test
      // that root will be a "jungle" element.
      jungle->setDataObject("bear", baloo);
      jungle->setDataObject("panther", bagheera);
      jungle->setDataObject("snake", kaa);

      xmh_1->save(document_1, "jira980_splitJungle_out.xml");
      if (!comparefiles("jira980_splitJungle_out.txt" ,"jira980_splitJungle_out.xml"))
      {
         return 0;
      }
   }
   catch (SDORuntimeException e)
   {
      cout << "Exception in jira980" << e << endl;
      return 0;
   }

   // Load both schema files into a single data factory and create content
   // within an open type root element with mixed ie sequenced) content.
   try
   {
      // Data factory to load both schema files. 
      DataFactoryPtr df_both = DataFactory::getDataFactory();
      XSDHelperPtr xsh_both = HelperProvider::getXSDHelper(df_both);

      // Load a root element definition, then the three animal types.
      xsh_both->defineFile("jira980_mixedJungle.xsd");
      xsh_both->defineFile("jira980_animaltypes.xsd");

      // Create three animals based on the preceding types.
      DataObjectPtr baloo = df_both->create("", "bearType");
      baloo->setCString("name", "Mummy bear");
      baloo->setInteger("weight", 700);

      DataObjectPtr bagheera = df_both->create("", "pantherType");
      bagheera->setCString("name", "Bagheera");
      bagheera->setCString("colour", "inky black");

      DataObjectPtr kaa = df_both->create("", "snakeType");
      kaa->setCString("name", "Kaa");
      kaa->setInteger("length", 25);

      // Create an output document
      XMLHelperPtr xmh_both = HelperProvider::getXMLHelper(df_both);
      XMLDocumentPtr document_both = xmh_both->createDocument();

      DataObjectPtr mixedJungle = document_both->getRootDataObject();

      // Add the three animals as children of the document root. In this test
      // that root will be a "mixedJungle" element.
      mixedJungle->setDataObject("bear", baloo);
      mixedJungle->setDataObject("panther", bagheera);
      mixedJungle->setDataObject("snake", kaa);

      xmh_both->save(document_both, "jira980_mixedJungle_out.xml");
      return comparefiles("jira980_mixedJungle_out.txt" ,"jira980_mixedJungle_out.xml");

   }

   catch (SDORuntimeException e)
   {
      cout << "Exception in jira980" << e << endl;
      return 0;
   }

}

int sdotest::typedefinitionstest()
{

    // We re-use the  parsed types from the company schema


    try {

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
 
        xsh->defineFile("company.xsd");

        XSDHelperPtr clonedHelper = HelperProvider::getXSDHelper();
        clonedHelper->defineTypes(xsh->getDefinedTypes());

        xsh->generateFile(mdg->getTypes(),"typedefs.xsd", "companyNS", 0);
        xsh->generateFile(clonedHelper->getDataFactory()->getTypes(),"typedefs_cloned.xsd", "companyNS", 0);

        return comparefiles("typedefs_cloned.xsd" ,"typedefs.xsd");


    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in typedefinitionstest" << e << endl;
        return 0;
    }
}


int sdotest::eBayTest()
{

    try {

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);

        cout << "parsing" <<endl;
        xsh->defineFile("eBaySvc.wsdl");
        cout << "parsed" <<endl;

        cout<< "number of types = " << xsh->getDefinedTypes().size() <<endl;
        XSDHelperPtr clonedHelper = HelperProvider::getXSDHelper();
        cout << "cloning" <<endl;
        clonedHelper->defineTypes(xsh->getDefinedTypes());
        cout << "cloned" <<endl;
        cout<< "number of types = " << clonedHelper->getDefinedTypes().size() <<endl;


    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in eBayTest" << e << endl;
        return 0;
    }
    return 1;
}

int sdotest::jira1174()
{

    try {

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
 
        xsh->defineFile("company.xsd");


        DataObjectPtr comp = mdg->create("companyNS", "CompanyType");
        DataObjectPtr dept = comp->createDataObject("departments");
        DataObjectPtr ron = dept->createDataObject("employees");
        ron->setCString("name", "Ron");
        comp->setDataObject("employeeOfTheMonth", ron);
        comp = NULL;
        return 1;
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in jira1174" << e << endl;
        return 0;
    }
}

int sdotest::jira1238()
{

    try {

 
        DataFactoryPtr mdg  = DataFactory::getDataFactory();

        XSDHelperPtr xsh = HelperProvider::getXSDHelper(mdg);
 
        xsh->defineFile("overlappingtypes.xsd");

        mdg->getType("http://www.example.org/AnnonTypes", "Overlapping");
        mdg->getType("http://www.example.org/AnnonTypes", "Overlapping1");
        return 1;
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in jira1238" << e << endl;
        return 0;
    }
}

int sdotest::loadWithoutSchema()
{

    try {
        XMLHelperPtr xmh = HelperProvider::getXMLHelper();

        XMLDocumentPtr doc = xmh->loadFile("stock.wsdl");

        return 1;
    }
    catch (SDORuntimeException e)
    {
        cout << "Exception in loadWithoutSchema" << e << endl;
        return 0;
    }
}