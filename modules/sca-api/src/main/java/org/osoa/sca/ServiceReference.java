/*
 * (c) Copyright BEA Systems, Inc., Cape Clear Software, International Business Machines Corp, Interface21, IONA Technologies,
 * Oracle, Primeton Technologies, Progress Software, Red Hat, Rogue Wave Software, SAP AG., Siemens AG., Software AG., Sybase
 * Inc., TIBCO Software Inc., 2005, 2007. All rights reserved.
 * 
 * see http://www.osoa.org/display/Main/Service+Component+Architecture+Specifications
 */
package org.osoa.sca;


/**
 * A ServiceReference represents a client's perspective of a reference to another service.
 *
 * @version $Rev$ $Date$
 * @param <B> the Java interface associated with this reference
 */
public interface ServiceReference<B> extends CallableReference<B> {
    /**
     * Returns the id supplied by the user that will be associated with conversations initiated through this reference.
     *
     * @return the id to associated with any conversation initiated through this reference
     */
    Object getConversationID();

    /**
     * Set the id to associate with any conversation started through this reference.
     * If the value supplied is null then the id will be generated by the implementation.
     *
     * @param conversationId the user-defined id to associated with a conversation
     * @throws IllegalStateException if a conversation is currently associated with this reference
     */
    void setConversationID(Object conversationId) throws IllegalStateException;

    /**
     * Sets the callback ID.
     *
     * @param callbackID the callback ID
     */
    void setCallbackID(Object callbackID);

    /**
     * Returns the callback object.
     *
     * @return the callback object
     */
    Object getCallback();

    /**
     * Sets the callback object.
     *
     * @param callback the callback object
     */
    void setCallback(Object callback);
}
