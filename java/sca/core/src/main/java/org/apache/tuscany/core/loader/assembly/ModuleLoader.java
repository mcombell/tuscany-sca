/**
 *
 * Copyright 2005 The Apache Software Foundation
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
package org.apache.tuscany.core.loader.assembly;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.tuscany.core.config.ConfigurationLoadException;
import org.apache.tuscany.core.context.impl.CompositeContextImpl;
import org.apache.tuscany.core.loader.LoaderContext;
import org.apache.tuscany.core.system.context.SystemCompositeContextImpl;
import org.apache.tuscany.model.assembly.Module;
import org.osoa.sca.annotations.Scope;

/**
 * @version $Rev$ $Date$
 */
@Scope("MODULE")
public class ModuleLoader extends CompositeLoader {
    public QName getXMLType() {
        return AssemblyConstants.MODULE;
    }

    public Module load(XMLStreamReader reader, LoaderContext loaderContext) throws XMLStreamException, ConfigurationLoadException {
        Module module = factory.createModule();
        loadComposite(reader, module, loaderContext);
        // JFM Hack until recursive model in place
        if (module.getName().startsWith("org.apache.tuscany.core.system")) {
            module.setImplementationClass(SystemCompositeContextImpl.class);
        } else {
            module.setImplementationClass(CompositeContextImpl.class);
        }
        return module;
    }
}
