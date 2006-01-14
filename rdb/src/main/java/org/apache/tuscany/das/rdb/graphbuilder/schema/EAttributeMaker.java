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
package org.apache.tuscany.das.rdb.graphbuilder.schema;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcoreFactory;


/**
 *
 * A helper used to create new EAttributes
 */
public class EAttributeMaker {

	private static EAttributeMaker maker;

	protected EAttributeMaker() {
		// Empty Constructor
	}

	/**
	 * Return an EAttributeMaker singleton
	 * @return EAttributeMaker
	 */
	public static EAttributeMaker singleton() {
		if (maker == null)
			maker = new EAttributeMaker();
		return maker;
	}

	/**
	 * Create a new EAttribute with the specified name and type
	 * @param name The name of the new EAttribute
	 * @param type The type of the new EAttribute
	 * @return EAttribute
	 */
	public EAttribute createEAttribute(String name, EDataType type) {
		
		EAttribute attr = getFactory().createEAttribute();
		attr.setName(name);
		attr.setEType(type);
		attr.setUnique(false);
			
		return attr;
	}

	private EcoreFactory getFactory() {
		return EcoreFactory.eINSTANCE;
	}
}
