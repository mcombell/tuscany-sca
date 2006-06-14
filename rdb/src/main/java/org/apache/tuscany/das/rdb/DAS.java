package org.apache.tuscany.das.rdb;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.tuscany.das.rdb.config.Config;
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
     * Set the default connection for all command in the group
     * 
     * @param connection
     */
    public void setConnection(Connection connection);
    
    /**
     * If the CommandGroup is managing connections then this method
     * must be called when the client is done with the instance.
     * 
     */
    public void releaseResources();
    
    
    // begin from CommandFactory
    
    /**
     * Creates a Command based on the provided SQL statement
     * 
     * @param sql
     *            The SQL statement
     * @return returns a Command instance
     */
    public Command createCommand(String sql);

    /**
     * Creates a Command based on the provided SQL statement and configuration
     * 
     * @param sql
     *            The SQL statement
     * @param mappingModel
     *            The congiguration as XML file stream
     * @return returns a COmmand instance
     */
    public Command createCommand(String sql, InputStream config);
    
    

    /**
     * Creates a Command based on the provided SQL statement and configuration
     * 
     * @param sql
     *            The SQL statement
     * @param config
     *            The congiguration as Config instance
     * @return returns a COmmand instance
     */
    public Command createCommand(String sql, Config config);
}
