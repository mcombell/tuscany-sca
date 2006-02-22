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

import java.util.Iterator;

import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.wrapper.MappingWrapper;
import org.apache.tuscany.das.rdb.graphbuilder.impl.GraphBuilderMetadata;
import org.apache.tuscany.das.rdb.graphbuilder.impl.ResultMetadata;
import org.apache.tuscany.das.rdb.util.DebugUtil;
import org.apache.tuscany.sdo.SDOFactory;
import org.apache.tuscany.sdo.impl.AttributeImpl;
import org.apache.tuscany.sdo.impl.ClassImpl;
import org.apache.tuscany.sdo.impl.DynamicDataObjectImpl;
import org.apache.tuscany.sdo.impl.ReferenceImpl;
import org.apache.tuscany.sdo.util.DataObjectUtil;
import org.apache.tuscany.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import commonj.sdo.Type;
import commonj.sdo.helper.TypeHelper;

/**
 * 
 * An ESchemaMaker is used to create an EMF Schema from an instance of JDBC
 * Mediator Metadata.
 * 
 */
public class ESchemaMaker {

	private final GraphBuilderMetadata metadata;

	private EPackage dataGraphPackage;

	private final String nsPrefix;

	private final String pkgName;

	private boolean debug = false;

	/**
	 * Constructor for ESchemaMaker. Creates an EMF Schema based on the metadata
	 * passed in.
	 * 
	 * @param metadata
	 */
	public ESchemaMaker(GraphBuilderMetadata metadata) {
		this(metadata, null, null);
	}

	/**
	 * Constructor for ESshemaMaker. Creates an EMF Schema based on the supplied
	 * metadata, namespace prefix, and package name
	 * 
	 * @param metadata
	 *            the metadata
	 * @param nsPrefix
	 *            the namespace prefix - this affects the generated
	 *            Factory/Package name - defaults to datagraph
	 * @param pkgName
	 *            the package name - determines package name for generated code -
	 *            defaults to datagraph
	 */
	public ESchemaMaker(GraphBuilderMetadata metadata, String nsPrefix,
			String pkgName) {
		if (nsPrefix == null)
			nsPrefix = "datagraph";
		if (pkgName == null)
			pkgName = "datagraph";

		this.metadata = metadata;
		this.nsPrefix = nsPrefix;
		this.pkgName = pkgName;

		TypeHelper helper = TypeHelper.INSTANCE;
	}

	/**
	 * Creates an EMF Schema by using the
	 * 
	 * @link TableMaker and
	 * @link RelationshipMaker to transform
	 * @link Metadata elements into EMF Schema elements.
	 */

	public Type createESchema() {
		TypeHelper.INSTANCE.getType("commonj.sdo", "Integer");
		DataObjectUtil.initRuntime();
		SDOUtil.createDataGraph();

		ClassImpl rootClass = (ClassImpl) SDOFactory.eINSTANCE.createClass();
		Type rootType = (Type) rootClass;

		rootClass.setName("DataGraphRoot");
		getEPackage().getEClassifiers().add(rootClass);

		EReferenceMaker refMaker = new EReferenceMaker();

		Iterator iter = metadata.getResultMetadata().iterator();
		while (iter.hasNext()) {

			ResultMetadata resultMetadata = (ResultMetadata) iter.next();

			Iterator names = resultMetadata.getAllTablePropertyNames()
					.iterator();
			while (names.hasNext()) {
				String tableName = (String) names.next();
				if (rootClass.getEStructuralFeature(tableName) == null) {
					Type clazz = createType(tableName);
					getEPackage().getEClassifiers().add(clazz);
					ReferenceImpl ref = refMaker.createOneToManyReference(
							tableName, clazz, true);
					rootClass.getEStructuralFeatures().add(ref);
				}
			}

			for (int i = 1; i <= resultMetadata.getColumnNames().size(); i++) {

				ReferenceImpl ref = (ReferenceImpl) rootType
						.getProperty(resultMetadata.getTablePropertyName(i));

				if (ref == null)
					throw new RuntimeException("Could not find table "
							+ resultMetadata.getTablePropertyName(i)
							+ " in the SDO model");
				EClass clazz = ref.getEReferenceType();
				String columnName = resultMetadata.getColumnPropertyName(i);

				if (clazz.getEStructuralFeature(columnName) == null) {
					Type atype = (Type) resultMetadata.getDataType(i);

					// EDataType type = (EDataType) atype.getEClassifier();

					AttributeImpl attr = getAttributeMaker().createEAttribute(
							columnName, atype);

					DebugUtil.debugln(getClass(), debug, "Adding column "
							+ columnName + " to "
							+ resultMetadata.getTablePropertyName(i));
					clazz.getEStructuralFeatures().add(attr);
				}
			}
		}

		if (metadata.hasMappingModel()) {
			MappingWrapper wrapper = new MappingWrapper(metadata.getMapping());
			Iterator i = metadata.getRelationships().iterator();
			while (i.hasNext()) {
				Relationship r = (Relationship) i.next();

				EClass parent = (EClass) getEPackage().getEClassifier(
						wrapper.getTablePropertyName(r.getPrimaryKeyTable()));
				EClass child = (EClass) getEPackage().getEClassifier(
						wrapper.getTablePropertyName(r.getForeignKeyTable()));
				if (parent == null) {
					throw new RuntimeException("The parent table ("
							+ r.getPrimaryKeyTable() + ") in relationship "
							+ r.getName()
							+ " was not found in the mapping information.");
				} else if (child == null) {
					throw new RuntimeException("The child table ("
							+ r.getForeignKeyTable() + ") in relationship "
							+ r.getName()
							+ " was not found in the mapping information.");
				}

				ReferenceImpl ref = refMaker.createReference(r, (Type)parent, (Type)child);

				DebugUtil.debugln(getClass(), debug, "Adding reference: "
						+ ref.getName() + " to " + parent.getName());
				if (parent.getEStructuralFeature(ref.getName()) == null)
					parent.getEStructuralFeatures().add(ref);

				if (child.getEStructuralFeature(ref.getEOpposite().getName()) == null)
					child.getEStructuralFeatures().add(ref.getEOpposite());

			}

		}

		// EcoreUtil.freeze(rootObject.getEPackage());

		return (Type) rootClass;
	}

	/**
	 * Create an EClass with the specified name
	 * 
	 * @param name
	 * @return EClass
	 */
	protected Type createType(String name) {
		ClassImpl ecl = (ClassImpl) SDOFactory.eINSTANCE.createClass();
		ecl.setName(name);

		return ecl;
	}

	/**
	 * Get an EAttributeMaker singleton
	 * 
	 * @return EAttributeMaker
	 */
	private EAttributeMaker getAttributeMaker() {
		return EAttributeMaker.singleton();
	}


	/**
	 * @return the EPackage for this schema
	 */
	public EPackage getEPackage() {
		if (this.dataGraphPackage == null)
			this.dataGraphPackage = createEPackage();
		return this.dataGraphPackage;
	}

	/**
	 * Create the EPackage for this schema Uses the packageName and nsPrefix
	 * values set in the constructors The EPackage overrides the default
	 * EFactory so that DataObjects will be created using MapDataObjectImpl.
	 * 
	 * @return the new EPackage
	 */
	protected EPackage createEPackage() {
		
		EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
		pkg.setName(pkgName);
		pkg.setNsPrefix(nsPrefix);
		pkg.setNsURI("datagraph.ecore");

		pkg.setEFactoryInstance(new EFactoryImpl() {
			public EObject basicCreate(EClass cls) {
				EObject result = new DynamicDataObjectImpl(cls);
				return result;
			}
		});

		return pkg;
	}

}
