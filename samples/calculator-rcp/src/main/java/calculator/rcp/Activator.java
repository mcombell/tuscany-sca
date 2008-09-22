/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package calculator.rcp;

import java.io.File;

import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.equinox.launcher.Contribution;
import org.apache.tuscany.sca.node.equinox.launcher.NodeLauncher;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.apache.tuscany.sca.sample.calculator.rcp";

    // The shared instance
    private static Activator plugin;

    private NodeLauncher launcher;
    private SCANode node;

    public Activator() {
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        
        launcher = NodeLauncher.newInstance();
        node = launcher.createNode("Calculator.composite", new Contribution("c1", new File("target/classes").toURI().toString()));
        node.start();
    }

    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        if (node != null) {
            node.stop();
        }
        if (launcher != null) {
            launcher.destroy();
        }
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path
     *
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
