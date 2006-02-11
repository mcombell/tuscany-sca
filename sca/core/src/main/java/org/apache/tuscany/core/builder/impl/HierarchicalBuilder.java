/**
 * 
 * Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.tuscany.core.builder.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tuscany.core.builder.BuilderException;
import org.apache.tuscany.core.builder.RuntimeConfigurationBuilder;
import org.apache.tuscany.core.context.Context;
import org.apache.tuscany.model.assembly.AssemblyModelObject;

/**
 * A builder that contains nested builders. Used for synchronizing parts of the build process, such as references.
 * 
 * @version $Rev$ $Date$
 */
public class HierarchicalBuilder implements RuntimeConfigurationBuilder {

    private AssemblyModelObject modelObject;

    private Context context;

    private List<RuntimeConfigurationBuilder> builders = new ArrayList();

    public HierarchicalBuilder() {
    }

    public void addBuilder(RuntimeConfigurationBuilder builder) {
        builders.add(builder);
    }

    public void setModelObject(AssemblyModelObject modelObject) {
        this.modelObject = modelObject;
    }

    public void setParentContext(Context context) {
        this.context = context;
    }

    public void build() throws BuilderException {
        for (RuntimeConfigurationBuilder builder : builders) {
            builder.setParentContext(context);
            builder.setModelObject(modelObject);
            builder.build();
        }

    }

}
