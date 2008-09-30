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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to indicate a constructor parameter, field or method that is
 * used to inject a configuration property value.
 *
 * @version $Rev$ $Date$
 */
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Property {
    /**
     * The name of the property. If not specified then the name will be derived
     * from the annotated field or method.
     *
     * @return the name of the property
     */
    String name() default "";

    /**
     * Indicates whether a value for the property must be provided.
     *
     * @return true if a value must be provided
     */
    boolean required() default false;
}
