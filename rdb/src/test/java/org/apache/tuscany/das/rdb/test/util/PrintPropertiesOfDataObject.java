/**
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.tuscany.das.rdb.test.util;

import java.util.List;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

/**
 * Demonstrates iterating through, and inspecting properites of a DataObject. This
 * sample shows listing properties of a DataObject, determining if the properties are
 * set and whether they are simple properties or represent DataObjects <br>
 * <br>
 * This sample is from the <a href="http://incubator.apache.org/tuscany"
 * target="_blank"> Apache Tuscany</a> project. It was written to help users
 * understand and experiment with SDO. It is based upon code snippets contained
 * within, and is meant for use with, and reference to the <a
 * href=http://osoa.org/download/attachments/36/Java-SDO-Spec-v2.1.0-FINAL.pdf?version=1"
 * target="_bank">SDO Specification</a>. This specific examples was based upon code
 * snippets in the section titled 'Property Indexes'. <br>
 * <b>Usage:</b> <br>
 * This sample can easily be run from within Eclipse as a Java Application if Tuscany or 
 * the sample-sdo project is imported into Eclipse as an existing project.
 * <br><br>
 * If executing as a standalone application please do the following: 
 * <br>
 * <UL>
 * <LI>Include the following jar files on your classpath :
 * <UL>
 * <LI>SDO API and Tuscany Implementation
 * <UL>
 * <LI>sdo-api-{version}.jar - SDO API
 * <LI>tuscany-sdo-impl-{version}.jar - Tuscany SDO implementation
 * </UL>
 * </LI>
 * <LI>EMF dependencies. 
 * <UL>
 * <LI>emf-common-{version}.jar - some common framework utility and base classes
 * <LI>emf-ecore-{version}.jar - the EMF core runtime implementation classes (the Ecore metamodel)
 * <LI>emf-ecore-change-{version}.jar - the EMF change recorder and framework
 * <LI>emf-ecore-xmi-{version}.jar - EMF's default XML (and XMI) serializer and loader
 * <LI>xsd-{version}.jar - the XML Schema model
 * </UL>
 * </LI>
 * </UL>
 * 
 * These jar files can be obtained by downloading and unpacking a <a href="http://cwiki.apache.org/TUSCANY/sdo-downloads.html" target="_blank">Tuscany binary distribution</a>  </LI>
 * <LI>Execute: <br>
 * java org.apache.tuscany.samples.sdo.specCodeSnippets.PrintPropertiesOfDataObject</LI>
 * </UL>
 */

public class PrintPropertiesOfDataObject {

    /**
     * Calls recursive method to inspect and print properties of a DataObject.
     * 
     * @param DataObject.
     *            The DataObject to print the properties of
     */
    public static void printDataObjectProperties(DataObject myDo) throws Exception {
        System.out.println("Recursivly inspecting and printing properties of DataObject");
        printAndInspectDataObject("   ", myDo);
    }

    /**
     * Recursivly inspects and prints DataObject. This method will list all set
     * properties
     * 
     * @param buffer.
     *            Blank string to help with formating
     * @param myDo.
     *            DataObject to recursivly inspect and print the properties of.
     * @throws Exception
     */
    private static void printAndInspectDataObject(String buffer, DataObject dataObject) throws Exception {
        for (int i = 0; i < dataObject.getInstanceProperties().size(); i++) {
            Property p = (Property) dataObject.getInstanceProperties().get(i);
            // just print out the name, type and string value of the property
            Type type = p.getType();
            System.out.print(buffer + "  " + p.getName() + ",type=" + type.getName());
            if (dataObject.isSet(p)) {
                // see if this represents a simple property or if the type is also a
                // datatype
                if (type.isDataType()) {
                    System.out.println(", simple type :  " + dataObject.get(i));
                } else {

                    // check to see if the property has many values
                    if (p.isMany()) {
                        System.out.println(buffer + ", many valued with list of DataObjects : ");
                        List dataObjects = dataObject.getList(p);
                        for (int x = 0; x < dataObjects.size(); x++) {
                            if (p.isContainment()) {
                                printAndInspectDataObject(buffer + "    ", (DataObject) dataObjects.get(x));
                                System.out.println();
                            }
                        }

                    } else {

                        System.out.println(buffer + ", data object : ");
                        DataObject newDataObject = dataObject.getDataObject(p);
                        printAndInspectDataObject(buffer + "    ", newDataObject);
                        System.out.println("");
                    }
                }
            } else {
                System.out.println(buffer + ", is not set");
            }
        }

    }
}
