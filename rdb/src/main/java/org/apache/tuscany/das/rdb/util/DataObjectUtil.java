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
package org.apache.tuscany.das.rdb.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataObject;
import commonj.sdo.ChangeSummary.Setting;

/**
 */
public class DataObjectUtil {
	
	//Utilities
	public static DataObject getRestoredCopy(DataObject changedDO) {
	    DataObject changedCopy = getCopy(changedDO);
	    restoreAttributeValues(changedCopy, changedDO);
	    return changedCopy;
	}

	public static DataObject getCopy(DataObject original) {
		
		//Drop to "E" land and create a new instance
		EObject eOriginal = (EObject) original;
		EFactory factory = eOriginal.eClass().getEPackage().getEFactoryInstance();
		EObject eCopy = factory.create(eOriginal.eClass());
		
		//Fill in values
		Iterator i = eOriginal.eClass().getEAllStructuralFeatures().iterator();
		while (i.hasNext()) {
			EStructuralFeature feature = (EStructuralFeature) i.next();
			eCopy.eSet(feature, eOriginal.eGet(feature));
		}
		return (DataObject) eCopy;
	}	

	/**
     * @param changedCopy
     * @return
     */
    private static void restoreAttributeValues(DataObject changedCopy, DataObject changedDO) {
        
		ChangeSummary changeSummary = changedDO.getDataGraph().getChangeSummary();
		List changes = changeSummary.getOldValues(changedDO);
		if ( changes == null )
			return;
		
		Iterator i = changes.iterator();
		while (i.hasNext()) {
		    Setting s = (Setting) i.next();    
		    if ( s.getProperty().getType().isDataType() )
		    	changedCopy.set(s.getProperty(), s.getValue());
		}
   }

	

}
