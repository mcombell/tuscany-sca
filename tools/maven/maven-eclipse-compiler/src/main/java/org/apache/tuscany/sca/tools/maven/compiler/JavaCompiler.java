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

package org.apache.tuscany.sca.tools.maven.compiler;

import static org.codehaus.plexus.compiler.CompilerOutputStyle.ONE_OUTPUT_FILE_PER_INPUT_FILE;
import static org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies.proceedWithAllProblems;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.DISABLED;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.GENERATE;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.IGNORE;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_Encoding;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_LineNumberAttribute;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_LocalVariableAttribute;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_ReportDeprecation;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_Source;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_SourceFileAttribute;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_SuppressWarnings;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.OPTION_TargetPlatform;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.WARNING;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.codehaus.plexus.compiler.AbstractCompiler;
import org.codehaus.plexus.compiler.CompilerConfiguration;
import org.codehaus.plexus.compiler.CompilerError;
import org.codehaus.plexus.compiler.CompilerException;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;

/**
 * A custom Plexus Java compiler plugin that uses the Eclipse compiler.
 *
 * @version $Rev: $ $Date: $
 */
public class JavaCompiler extends AbstractCompiler {
    
    public JavaCompiler() {
        super(ONE_OUTPUT_FILE_PER_INPUT_FILE, ".java", ".class", null);
    }

    public List<CompilerError> compile(CompilerConfiguration configuration) throws CompilerException {
        
        getLogger().info("Tuscany-Eclipse compiler");

        List<URL> urls;
        try {
            urls = new ArrayList<URL>();
            urls.add(new File(configuration.getOutputLocation()).toURI().toURL());
            for (String entry: (List<String>)configuration.getClasspathEntries()) {
                urls.add(new File(entry).toURI().toURL());
            }
        } catch (MalformedURLException e) {
            throw new CompilerException(e.getMessage(), e);
        }

        ClassLoader classLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));

        // Determine compiler configuration
        Map<String, String> settings = new HashMap<String, String>();
        String sourceVersion = configuration.getSourceVersion();
        if (sourceVersion != null && sourceVersion.length() != 0) {
            settings.put(OPTION_Source, sourceVersion);
        }
        String targetVersion = configuration.getTargetVersion();
        if (targetVersion != null && targetVersion.length() != 0) {
            settings.put(OPTION_TargetPlatform, targetVersion);
        }
        settings.put(OPTION_LineNumberAttribute, GENERATE);
        settings.put(OPTION_SourceFileAttribute, GENERATE);
        if (configuration.isDebug()) {
            settings.put(OPTION_LocalVariableAttribute, GENERATE);
        }
        if (configuration.getSourceEncoding() != null && !(configuration.getSourceEncoding().length() == 0)) {
            settings.put(OPTION_Encoding, configuration.getSourceEncoding());
        }
        if (!configuration.isShowWarnings()) {
            settings.put(OPTION_SuppressWarnings, DISABLED);
        }
        if (configuration.isShowDeprecation()) {
            settings.put(OPTION_ReportDeprecation, WARNING);
        } else {
            settings.put(OPTION_ReportDeprecation, IGNORE);
        }

        // Create a compiler
        List<CompilerError> compilerErrors = new ArrayList<CompilerError>();
        INameEnvironment nameEnvironment = new ClassLoaderNameEnvironment(classLoader, configuration.getSourceLocations());
        ICompilerRequestor requestor = new CompilerRequestor(configuration.getOutputLocation(), compilerErrors);
        Compiler compiler = new Compiler(nameEnvironment,
                                         proceedWithAllProblems(),
                                         settings,
                                         requestor,
                                         new DefaultProblemFactory(Locale.getDefault()));

        // Create compilation units for the source files
        List<FileCompilationUnit> compilationUnits = new ArrayList<FileCompilationUnit>();
        
        // Go over the input source locations
        List<String> sourceLocations = (List<String>)configuration.getSourceLocations(); 
        for (String sourceLocation : sourceLocations) {
            
            // Exclude nested source locations
            List<String> excludeLocations = new ArrayList<String>(); 
            for (String nestedLocation : sourceLocations) {
                if (nestedLocation != sourceLocation && nestedLocation.startsWith(sourceLocation)) {
                    excludeLocations.add(nestedLocation);
                }
            }
            
            // List source files in each source location
            for (String sourceFile: (Set<String>)getSourceFilesForSourceRoot(configuration, sourceLocation)) {
                
                // Exclude files from excluded nested locations
                boolean excluded = false;
                for (String excludeLocation: excludeLocations) {
                    if (sourceFile.startsWith(excludeLocation)) {
                        excluded = true;
                    }
                }
                if (!excluded) {
                  
                    // Create a compilation unit for the source file
                    FileCompilationUnit compilationUnit = new FileCompilationUnit(sourceFile, makeClassName(sourceFile, sourceLocation));
                    compilationUnits.add(compilationUnit);
                }
            }
        }
        
        // Compile all the compilation units
        getLogger().info("Compiling " + compilationUnits.size() + " to " + configuration.getOutputLocation());
        compiler.compile((ICompilationUnit[])compilationUnits.toArray(new ICompilationUnit[compilationUnits.size()]));

        return compilerErrors;
    }

    public String[] createCommandLine(CompilerConfiguration config) throws CompilerException {
        return null;
    }
}
