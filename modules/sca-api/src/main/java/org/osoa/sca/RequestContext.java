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
package org.osoa.sca;

import javax.security.auth.Subject;

/**
 * Interface that provides information on the current request.
 *
 * @version $Rev$ $Date$
 */
public interface RequestContext {
    /**
     * Returns the JAAS Subject of the current request.
     *
     * @return the Subject of the current request
     */
    Subject getSecuritySubject();

    /**
     * Returns the name of the service that was invoked.
     *
     * @return the name of the service that was invoked
     */
    String getServiceName();

    /**
     * Returns a CallableReference for the service that was invoked by the caller.
     *
     * @param <B> the Java type of the business interface for the reference
     * @return a CallableReference for the service that was invoked by the caller
     */
    <B> CallableReference<B> getServiceReference();

    /**
     * Returns a type-safe reference to the callback provided by the caller.
     *
     * @param <CB> the Java type of the business interface for the callback
     * @return a type-safe reference to the callback provided by the caller
     */
    <CB> CB getCallback();

    /**
     * Returns a CallableReference to the callback provided by the caller.
     *
     * @param <CB> the Java type of the business interface for the callback
     * @return a CallableReference to the callback provided by the caller
     */
    <CB> CallableReference<CB> getCallbackReference();
}
