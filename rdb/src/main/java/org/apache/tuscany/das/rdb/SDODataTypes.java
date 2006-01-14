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
package org.apache.tuscany.das.rdb;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import commonj.sdo.Type;

/**
 * Defines SDO data types. This is used primalirly to type stored procedure OUT parameters.
 * 
 * TODO - This maybe be obviated by the move to SDO2.  We also need to settle the question
 * of whether config information is in terms of database or data object types
 * 
 * 
 */public class SDODataTypes {

	final public static Type BOOLEAN = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEBoolean());
	final public static Type BYTE = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEByte());
	final public static Type BYTES = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEByteArray());
	final public static Type CHARACTER = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEChar());
	final public static Type DATE = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type DATETIME = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type DAY = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type DECIMAL = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEFloat());
	final public static Type DOUBLE =SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDouble());
	final public static Type DURATION = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type FLOAT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEFloat());
	final public static Type INT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEInt());
	final public static Type INTEGER = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEInt());
	final public static Type LONG = SDOUtil.adaptType(EcorePackage.eINSTANCE.getELong());
	final public static Type MONTH = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type MONTHDAY = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type OBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEObject());
	final public static Type SHORT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEShort());
	final public static Type STRING = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEString());
	final public static Type STRINGS = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEString());
	final public static Type TIME = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type URI = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEString());
	final public static Type YEAR = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type YEARMONTH = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type YEARMONTHDAY = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDate());
	final public static Type BOOLEANOBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEBooleanObject());
	final public static Type BYTEOBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEByteObject());
	final public static Type CHARACTEROBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getECharacterObject());
	final public static Type DOUBLEOBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEDoubleObject());
	final public static Type FLOATOBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEFloatObject());
	final public static Type INTEGEROBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEIntegerObject());
	final public static Type LONGOBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getELongObject());
	final public static Type SHORTOBJECT = SDOUtil.adaptType(EcorePackage.eINSTANCE.getEShortObject());


}
