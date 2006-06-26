package org.apache.tuscany.das.rdb;

import org.apache.tuscany.das.rdb.impl.DASFactoryImpl;

import commonj.sdo.DataObject;

/**
 * A CommandGroup represents a set of {@link Command} and single
 * {@link ApplyChangesCommand} that are created from a common config file.
 * 
 * 
 */
public interface DAS {

    public static DASFactory FACTORY = new DASFactoryImpl();

	/**
	 * The change history is scanned and modifications to the graph of data
	 * objects are flushed to the database.
	 * 
	 * @param root
	 *            the topmost containing data object
	 */
	public void applyChanges(DataObject root);
  

    /**
     * Gets the named command from this factory's inventory
     * 
     * @param name
     *            The identifying name of the requested command
     * @return Returns the identified command
     */
    public Command getCommand(String name);

    
    /**
     * If the CommandGroup is managing connections then this method
     * must be called when the client is done with the instance.
     * 
     */
    public void releaseResources();
        
    /**
     * Creates a Command based on the provided SQL statement
     * 
     * @param sql
     *            The SQL statement
     * @return returns a Command instance
     */
    public Command createCommand(String sql);
    
}
