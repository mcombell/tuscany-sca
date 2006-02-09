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
import org.apache.tuscany.sdo.SDOFactory;
import org.apache.tuscany.sdo.impl.ClassImpl;
import org.apache.tuscany.sdo.impl.ReferenceImpl;

import commonj.sdo.Type;

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
	public ReferenceImpl createReference(String name, Type type, int lowerBound, int upperBound, boolean containment, boolean changeable) {
		ReferenceImpl ref = (ReferenceImpl) getFactory().createReference();		
		ref.setName(name);
		ref.setEType((ClassImpl)type);
		ref.setLowerBound(lowerBound);
		ref.setUpperBound(upperBound);
		ref.setContainment(containment);
		ref.setResolveProxies(false);
		ref.setChangeable(changeable);
		
		return ref;
	}
	
	public ReferenceImpl createReference(String name, Type type, int lowerBound, int upperBound, boolean containment) {
		return createReference(name, type, lowerBound, upperBound, containment, true);
	}
	/**
	 * Create a one to many reference with the specified name, type, and containment
	 * @param name
	 * @param type
	 * @param containment
	 * @return
	 */
	public ReferenceImpl createOneToManyReference(String name, Type type, boolean containment) {
		return createReference(name, type, 0, -1, containment);
	}
	
	/**
	 * Create a one to many reference with the specified name and type
	 * @param name
	 * @param type
	 * @return EReference
	 */
	public ReferenceImpl createOneToManyReference(String name, Type type) {
		return createOneToManyReference(name, type, false);	
	}
	
	/**
	 * Create a one to one reference with the specified name and type
	 * @param name
	 * @param type
	 * @return EReference
	 */
	public ReferenceImpl createOneToOneReference(String name, Type type) {
		ReferenceImpl ref = createReference(name, type,0,1,false);		  
		return ref;
	}
	
	/**
	 * Create a many to one reference with the specified name and type
	 * @param name
	 * @param type
	 * @return EReference
	 */
	public ReferenceImpl createManyToOneReference(String name, Type type) {
		return createReference(name,type,0,1,false);
	}
	
	private SDOFactory getFactory() {
		return SDOFactory.eINSTANCE;
	}


	/**
	 * @param r
	 * @param parent
	 * @param child
	 * @return
	 */
	public ReferenceImpl createReference(Relationship r, Type parent, Type child) {
		if ( !r.isMany() ) {
			ReferenceImpl ref = createOneToOneReference(r.getName(), child);
			ReferenceImpl opp = createOneToOneReference(r.getName() + "_opposite", parent);
			ref.setEOpposite(opp);
			opp.setEOpposite(ref);
			return ref;
		} else {
			ReferenceImpl ref = createOneToManyReference(r.getName(), child);
			ReferenceImpl opp = createManyToOneReference(r.getName() + "_opposite", parent);
			ref.setEOpposite(opp);
			opp.setEOpposite(ref);
			return ref;
		}
		
	}

}
