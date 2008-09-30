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

/**
 * Common superclass for references that can be passed between components.
 * 
 * @version $Rev$ $Date$
 * @param <B> the Java interface associated with this reference
 */
public interface CallableReference<B> {
    /**
     * Returns a type-safe reference to the target of this reference.
     * The instance returned is guaranteed to implement the business interface for this reference
     * but may not be a proxy as defined by java.lang.reflect.Proxy.
     *
     * @return a proxy to the target that implements the business interface associated with this reference
     */
    B getService();

    /**
     * Returns the Java class for the business interface associated with this reference.
     *
     * @return the Class for the business interface associated with this reference
     */
    Class<B> getBusinessInterface();

    /**
     * Returns true if this reference is conversational.
     *
     * @return true if this reference is conversational
     */
    boolean isConversational();

    /**
     * Returns the conversation associated with this reference.
     * Returns null if no conversation is currently active.
     *
     * @return the conversation associated with this reference; may be null
     */
    Conversation getConversation();

    /**
     * Returns the callback ID.
     *
     * @return the callback ID
     */
    Object getCallbackID();
}
