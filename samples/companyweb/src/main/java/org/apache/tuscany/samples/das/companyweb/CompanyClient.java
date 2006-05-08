package org.apache.tuscany.samples.das.companyweb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.tuscany.das.rdb.ApplyChangesCommand;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.CommandGroup;

import commonj.sdo.DataObject;

public class CompanyClient {

    private Random generator = new Random();

    private CommandGroup commandGroup = CommandGroup.FACTORY.createCommandGroup(getConfig("CompanyConfig.xml"));

    public final List getCompanies() {

        Command read = commandGroup.getCommand("all companies");
        DataObject root = read.executeQuery();
        return root.getList("COMPANY");

    }

    public final List getCompaniesWithDepartments() {

        Command read = commandGroup.getCommand("all companies and departments");
        DataObject root = read.executeQuery();
        return root.getList("COMPANY");
    }

    public final List getDepartmentsForCompany(int id) {
        Command read = commandGroup.getCommand("all departments for company");
        read.setParameterValue("ID", new Integer(id));
        DataObject root = read.executeQuery();
        return root.getList("COMPANY");
    }

    public final void addDepartmentToFirstCompany() {
        Command read = commandGroup.getCommand("all companies and departments");
        DataObject root = read.executeQuery();
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");

        DataObject newDepartment = root.createDataObject("DEPARTMENT");
        newDepartment.setString("NAME", "Default Name");
        firstCustomer.getList("departments").add(newDepartment);

        ApplyChangesCommand apply = commandGroup.getApplyChangesCommand();
        apply.execute(root);

    }

    public final void deleteDepartmentsFromFirstCompany() {

        // This section gets the ID of the first Company just so I can
        // demonstrate a parameterized command next
        Command readAll = commandGroup.getCommand("all companies and departments");
        DataObject root = readAll.executeQuery();
        int idOfFirstCustomer = root.getInt("COMPANY[1]/ID");
        System.out.println("ID of first company is: " + idOfFirstCustomer);

        // Read a specific company based on the known ID
        Command readCust = commandGroup.getCommand("company by id with departments");
        readCust.setParameterValue("ID", new Integer(idOfFirstCustomer));
        root = readCust.executeQuery();

        // Delete all the comany's departments from the graph
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");

        // Shallow copy of list for deleting. This is required to avoid the
        // dreaded
        // ConcurrentModificationException since #delete operation also removes
        // from the original list
        List allDepartments = new ArrayList(firstCustomer.getList("departments"));

        Iterator i = allDepartments.iterator();
        DataObject department;
        while (i.hasNext()) {
            department = (DataObject) i.next();
            System.out.println("Deleting department named: " + department.getString("NAME"));
            department.delete();
        }

        ApplyChangesCommand apply = commandGroup.getApplyChangesCommand();
        apply.execute(root);

    }

    public final void changeFirstCompanysDepartmentNames() {

        // This section gets the ID of the first Company just so I can
        // demonstrate a parameterized command next
        Command readAll = commandGroup.getCommand("all companies and departments");
        DataObject root = readAll.executeQuery();
        int idOfFirstCustomer = root.getInt("COMPANY[1]/ID");
        System.out.println("ID of first company is: " + idOfFirstCustomer);

        // Read a specific company based on the known ID
        Command readCust = commandGroup.getCommand("company by id with departments");
        readCust.setParameterValue("ID", new Integer(idOfFirstCustomer));
        root = readCust.executeQuery();

        // Modify all the comany's department names
        DataObject firstCustomer = root.getDataObject("COMPANY[1]");
        Iterator i = firstCustomer.getList("departments").iterator();
        DataObject department;
        while (i.hasNext()) {
            department = (DataObject) i.next();
            System.out.println("Modifying department: " + department.getString("NAME"));
            department.setString("NAME", getRandomDepartmentName());
        }

        ApplyChangesCommand apply = commandGroup.getApplyChangesCommand();
        apply.execute(root);

    }
    
    
    public void releaseResources() {
        commandGroup.releaseResources();
    }

    // Utilities

    private String getRandomDepartmentName() {
        int number = generator.nextInt(1000) + 1;
        return "Dept-" + number;
    }

    private InputStream getConfig(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
