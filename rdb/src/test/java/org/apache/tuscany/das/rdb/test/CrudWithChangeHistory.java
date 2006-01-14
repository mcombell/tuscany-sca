/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
package org.apache.tuscany.das.rdb.test;

/*
 * This provdes the simplest examples that make use of the change history. The assumptions are:
 * 
 * Single type
 * Change history utilized
 * Dynamic DataObjects
 * 
 * 
 */

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.SDODataTypes;
import org.apache.tuscany.das.rdb.test.data.CustomerData;
import org.apache.tuscany.das.rdb.test.framework.DasTest;

import commonj.sdo.DataObject;

public class CrudWithChangeHistory
    extends DasTest
{

    protected void setUp()
        throws Exception
    {
        super.setUp();
        new CustomerData( getAutoConnection() ).refresh();
    }

    protected void tearDown()
        throws Exception
    {
        super.tearDown();
    }

    /**
     * Read and modify a customer.  
     * Provide needed Create/Update/Delete statements programatically
     */
    public void testReadModifyApply()
        throws Exception
    {

        //Read customer 1
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = 1" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get( "CUSTOMER[1]" );

        //Modify customer
        customer.set( "LASTNAME", "Pavick" );

        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
        apply.setConnection( getConnection() );

        //Manually create and add update command
        Command update = Command.FACTORY.createCommand( "update CUSTOMER set LASTNAME = :LASTNAME where ID = :ID" );
        update.addParameter( "LASTNAME", SDODataTypes.STRING );
        update.addParameter( "ID", SDODataTypes.INTEGER );
        apply.addUpdateCommand( customer.getType(), update );

        //Flush changes
        apply.execute( root );

        //Verify changes
        root = select.executeQuery();
        assertEquals( "Pavick", root.getString( "CUSTOMER[1]/LASTNAME" ) );

    }

    /**
     * Read and modify a customer.  
     * Provide needed Create/Update/Delete statements via xml file
     */
    public void testReadModifyApply1()
        throws Exception
    {

        //Read customer 1
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = 1" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get( "CUSTOMER[1]" );

        //Modify customer
        customer.set( "LASTNAME", "Pavick" );

        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand( getBasicCustomerMappingWithCUD() );
        apply.setConnection( getConnection() );

        //Flush changes
        apply.execute( root );

        //Verify changes
        root = select.executeQuery();
        assertEquals( "Pavick", root.getString( "CUSTOMER[1]/LASTNAME" ) );

    }

    /**
     * Same as previous but:
     * 	Utilizes generated CUD statements
     * 		Key info provided programatically
     */
    public void testReadModifyApply2()
        throws Exception
    {

        //Read customer with particular ID
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = 1" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject customer = root.getDataObject( "CUSTOMER[1]" );

        //Modify customer
        customer.set( "LASTNAME", "Pavick" );

        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand();
        apply.setConnection( getConnection() );
        //programatically add the only part of the mapping model needed for this simple case.
        //The ID designation is needed to generate the CUD statements
        apply.addPrimaryKey( "CUSTOMER.ID" );

        //Flush changes
        apply.execute( root );

        //Verify the change
        root = select.executeQuery();
        assertEquals( "Pavick", root.getDataObject( "CUSTOMER[1]" ).getString( "LASTNAME" ) );

    }

    /**
     * Builds on previous but:
     * 		1. Key info provided via XML file
     */
    public void testReadModifyApply3()
        throws Exception
    {

        //Read customer with particular ID
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = 1" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject customer = (DataObject) root.get( "CUSTOMER[1]" );

        //Modify customer
        customer.set( "LASTNAME", "Pavick" );

        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand( getBasicCustomerMappingModel() );
        apply.setConnection( getConnection() );

        //Flush changes
        apply.execute( root );

        //Verify the change
        root = select.executeQuery();
        assertEquals( "Pavick", root.getDataObject( "CUSTOMER[1]" ).getString( "LASTNAME" ) );

    }

    /**
     * Test ability to handle multiple changes to the graph including Creates/Updates/Deletes
     * Employs generated CUD
     */
    public void testReadModifyDeleteInsertApply()
        throws Exception
    {

        //Read some customers
        Command select = Command.FACTORY.createCommand( "Select * from CUSTOMER where LASTNAME = 'Williams'" );
        select.setConnection( getConnection() );
        DataObject root = select.executeQuery();

        DataObject cust1 = (DataObject) root.getList( "CUSTOMER" ).get( 0 );
        DataObject cust2 = (DataObject) root.getList( "CUSTOMER" ).get( 1 );
        DataObject cust3 = (DataObject) root.getList( "CUSTOMER" ).get( 2 );

        //Modify a customer
        cust1.set( "LASTNAME", "Pavick" );
        int cust1ID = cust1.getInt( "ID" );

        //Save IDs before delete
        int cust2ID = cust2.getInt( "ID" );
        int cust3ID = cust3.getInt( "ID" );
        //Delete a couple
        cust2.delete();
        cust3.delete();

        //Create a new customer
        DataObject cust4 = root.createDataObject( "CUSTOMER" );
        cust4.set( "ID", new Integer( 100 ) );
        cust4.set( "ADDRESS", "5528 Wells Fargo Drive" );
        cust4.set( "LASTNAME", "Gerkin" );

        //Build apply changes command
        ApplyChangesCommand apply = Command.FACTORY.createApplyChangesCommand( getBasicCustomerMappingModel() );
        apply.setConnection( getConnection() );

        //Flush changes
        apply.execute( root );

        //Verify deletes
        select = Command.FACTORY.createCommand( "Select * from CUSTOMER where ID = :ID" );
        select.setConnection( getConnection() );
        select.setParameterValue( "ID", new Integer( cust2ID ) );
        root = select.executeQuery();
        assertTrue( root.getList( "CUSTOMER" ).isEmpty() );
        //reparameterize same command
        select.setParameterValue( "ID", new Integer( cust3ID ) );
        root = select.executeQuery();
        assertTrue( root.getList( "CUSTOMER" ).isEmpty() );

        //verify insert
        select.setParameterValue( "ID", new Integer( 100 ) );
        root = select.executeQuery();
        assertEquals( 1, root.getList( "CUSTOMER" ).size() );
        assertEquals( "5528 Wells Fargo Drive", root.getString( "CUSTOMER[1]/ADDRESS" ) );
        assertEquals( "Gerkin", root.getString( "CUSTOMER[1]/LASTNAME" ) );

        //verify update
        select.setParameterValue( "ID", new Integer( cust1ID ) );
        root = select.executeQuery();
        assertEquals( "Pavick", root.getString( "CUSTOMER[1]/LASTNAME" ) );

    }

    //Utilities

    /**
     * Provides only a definition of the table
     */
    private InputStream getBasicCustomerMappingModel()
        throws FileNotFoundException
    {
        //		return new FileInputStream("src/test/resources/basicCustomerMapping.xml");
        return Thread.currentThread().getContextClassLoader().getResourceAsStream( "basicCustomerMapping.xml" );
    }

    /**
     * Adds definition of CUD statements
     */
    private InputStream getBasicCustomerMappingWithCUD()
        throws FileNotFoundException
    {
        //		return new FileInputStream("src/test/resources/basicCustomerMappingWithCUD.xml");
        return Thread.currentThread().getContextClassLoader().getResourceAsStream( "basicCustomerMappingWithCUD.xml" );

    }

}
