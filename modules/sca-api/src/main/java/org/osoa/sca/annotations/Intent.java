/*
 * Permission to copy, display and distribute the Service Component Architecture Specification and/or
 * portions thereof, without modification, in any medium without fee or royalty is hereby granted, provided
 * that you include the following on ALL copies of the Service Component Architecture Specification, or
 * portions thereof, that you make:
 * 1. A link or URL to the Service Component Architecture Specification at this location:
 *    http://www.osoa.org/display/Main/Service+Component+Architecture+Specifications
 * 2. The full text of the copyright notice as shown in the Service Component Architecture Specification.
 * BEA, Cape Clear, IBM, Interface21, IONA, Oracle, Primeton, Progress Software, Red Hat, Rogue Wave,
 * SAP, Siemens, Software AG., Sun, Sybase, TIBCO (collectively, the "Authors") agree to grant you a
 * royalty-free license, under reasonable, non-discriminatory terms and conditions to patents that they deem
 * necessary to implement the Service Component Architecture Specification.
 * THE Service Component Architecture SPECIFICATION IS PROVIDED "AS IS," AND THE
 * AUTHORS MAKE NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED,
 * REGARDING THIS SPECIFICATION AND THE IMPLEMENTATION OF ITS CONTENTS,
 * INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, NON-INFRINGEMENT OR TITLE.
 * THE AUTHORS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL, INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF OR RELATING TO ANY USE OR
 * DISTRIBUTION OF THE Service Components Architecture SPECIFICATION.
 * The name and trademarks of the Authors may NOT be used in any manner, including advertising or
 * publicity pertaining to the Service Component Architecture Specification or its contents without specific,   
 */
package org.osoa.sca.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation that can be applied to annotations that describe SCA intents.
 * Adding this annotation allows SCA runtimes to automatically detect user-defined intents.
 * <p/>
 * Applications must specify a value, a pairing of targetNamespace and localPort, or both.
 * If both value and pairing are supplied they must define the name qualified name.
 *
 * @version $Rev$ $Date$
 */
@Target({ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface Intent {
    /**
     * The qualified name of the intent, in the form defined by {@link javax.xml.namespace.QName#toString}.
     *
     * @return the qualified name of the intent
     */
    String value() default "";

    /**
     * The XML namespace for the intent.
     *
     * @return the XML namespace for the intent
     */
    String targetNamespace() default "";

    /**
     * The name of the intent within its namespace.
     *
     * @return name of the intent within its namespace
     */
    String localPart() default "";
}
