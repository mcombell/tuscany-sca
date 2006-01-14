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

import org.apache.tuscany.das.rdb.config.Relationship;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

/**
 * A helper used to create EReferences
 */
public class EReferenceMaker {
	
	public EReferenceMaker() {
		// Empty Constructor
	}
	
	/**
	 * Create an EReference with the specified name, type, lower bound, upper bound
	 * and containment.
	 * @param name
	 * @param type
	 * @param lowerBound
	 * @param upperBound
	 * @param containment
	 * @return EReference
	 */
	public EReference createEReference(String name, EClass type, int lowerBound, int upperBound, boolean containment, boolean changeable) {
		EReference ref = getFactory().createEReference();
		ref.setName(name);
		ref.setEType(type);
		ref.setLowerBound(lowerBound);
		ref.setUpperBound(upperBound);
		ref.setContainment(containment);
		ref.setResolveProxies(false);
		ref.setChangeable(changeable);
		
		return ref;
	}
	
	public EReference createEReference(String name, EClass type, int lowerBound, int upperBound, boolean containment) {
		return createEReference(name, type, lowerBound, upperBound, containment, true);
	}
	/**
	 * Create a one to many reference with the specified name, type, and containment
	 * @param name
	 * @param type
	 * @param containment
	 * @return
	 */
	public EReference createOneToManyReference(String name, EClass type, boolean containment) {
		return createEReference(name, type, 0, -1, containment);
	}
	
	/**
	 * Create a one to many reference with the specified name and type
	 * @param name
	 * @param type
	 * @return EReference
	 */
	public EReference createOneToManyReference(String name, EClass type) {
		return createOneToManyReference(name, type, false);	
	}
	
	/**
	 * Create a one to one reference with the specified name and type
	 * @param name
	 * @param type
	 * @return EReference
	 */
	public EReference createOneToOneReference(String name, EClass type) {
		EReference ref = createEReference(name, type,0,1,false);
	//	EcoreUtil.setAnnotation(ref, "commonj.sdo", "readOnly", "true");
		  		
		return ref;
	}
	
	/**
	 * Create a many to one reference with the specified name and type
	 * @param name
	 * @param type
	 * @return EReference
	 */
	public EReference createManyToOneReference(String name, EClass type) {
		return createEReference(name,type,0,1,false);
	}
	
	private EcoreFactory getFactory() {
		return EcoreFactory.eINSTANCE;
	}


	/**
	 * @param r
	 * @param parent
	 * @param child
	 * @return
	 */
	public EReference createReference(Relationship r, EClass parent, EClass child) {
		if ( !r.isMany() ) {
			EReference ref = createOneToOneReference(r.getName(), child);
			EReference opp = createOneToOneReference(r.getName() + "_opposite", parent);
			ref.setEOpposite(opp);
			opp.setEOpposite(ref);
			return ref;
		} else {
			EReference ref = createOneToManyReference(r.getName(), child);
			EReference opp = createManyToOneReference(r.getName() + "_opposite", parent);
			ref.setEOpposite(opp);
			opp.setEOpposite(ref);
			return ref;
		}
		
	}

}
