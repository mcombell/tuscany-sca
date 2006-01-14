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
package org.apache.tuscany.das.rdb.test.mappings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.ConfigPackage;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.Table;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class BuildMappings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuildMappings b = new BuildMappings();
		try {
			b.build();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void build() throws IOException {
		Config m = getMappingModel_1xM_uni();
		XMLResource resource = new XMLResourceImpl();
		resource.getContents().add(m);
		FileOutputStream stream = new FileOutputStream("1xM_uni_mapping.xml");

		HashMap map = new HashMap();
		ExtendedMetaData metadata = ExtendedMetaData.INSTANCE;
		metadata.setQualified(ConfigPackage.eINSTANCE, false);
		map.put(XMLResource.OPTION_EXTENDED_META_DATA, metadata);

		resource.save(stream, map);

	}

	protected Config getMappingModel_1xM_uni() {
		Relationship r = ConfigFactory.eINSTANCE.createRelationship();
		r.setPrimaryKeyTable("CUSTOMER");
		r.setForeignKeyTable("ANORDER");
		r.setName("orders");

		r.setMany(true);
		// r.setMaintained(true);

		KeyPair pair = ConfigFactory.eINSTANCE.createKeyPair();
		pair.setPrimaryKeyColumn("ID");
		pair.setForeignKeyColumn("CUSTOMER_ID");
	
		r.getKeyPair().add(pair);

		Config model = ConfigFactory.eINSTANCE.createConfig();
		
		model.getRelationship().add(r);

		Table t = ConfigFactory.eINSTANCE.createTable();
		t.setName("CUSTOMER");
		
		Column c = ConfigFactory.eINSTANCE.createColumn();
		c.setName("ID");
		c.setPrimaryKey(true);

		model.getTable().add(t);

		return model;
	}
}
