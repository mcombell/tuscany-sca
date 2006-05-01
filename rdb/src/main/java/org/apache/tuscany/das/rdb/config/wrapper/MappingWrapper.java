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
package org.apache.tuscany.das.rdb.config.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.tuscany.das.rdb.Key;
import org.apache.tuscany.das.rdb.config.Column;
import org.apache.tuscany.das.rdb.config.Config;
import org.apache.tuscany.das.rdb.config.ConfigFactory;
import org.apache.tuscany.das.rdb.config.KeyPair;
import org.apache.tuscany.das.rdb.config.Relationship;
import org.apache.tuscany.das.rdb.config.Table;
import org.apache.tuscany.das.rdb.config.impl.ConfigFactoryImpl;
import org.apache.tuscany.das.rdb.util.DebugUtil;

import commonj.sdo.Property;

public class MappingWrapper {

    private static final ConfigFactory factory = ConfigFactoryImpl.eINSTANCE;

    private static final boolean debug = false;

    private Config config;

    public MappingWrapper() {
        // Empty Constructor
    }

    public MappingWrapper(Config mapping) {
        this.config = mapping;
    }

    public Config getConfig() {
        return this.config;
    }

    public Table getTable(String name) {
        if (config == null)
            return null;
        DebugUtil.debugln(getClass(), debug, "Looking for table " + name);
        Iterator i = config.getTable().iterator();
        while (i.hasNext()) {
            Table t = (Table) i.next();
            if (name.equals(t.getName()))
                return t;
        }

        return null;
    }

    public Table getTableByPropertyName(String name) {
        if (config == null)
            return null;
        DebugUtil.debugln(getClass(), debug, "Looking for table by property: " + name);
        Iterator i = config.getTable().iterator();
        while (i.hasNext()) {
            Table t = (Table) i.next();
            TableWrapper wrapper = new TableWrapper(t);
            if (name.equals(wrapper.getPropertyName()))
                return t;
        }
        return null;

        // throw new RuntimeException("Table with property name " + name
        // + " not found.");

    }

    public void addRelationship(String parentName, String childName) {

        if (config == null)
            config = factory.createConfig();

        QualifiedColumn parent = new QualifiedColumn(parentName);
        QualifiedColumn child = new QualifiedColumn(childName);

        Relationship r = factory.createRelationship();
        r.setName(child.getTableName());
        r.setPrimaryKeyTable(parent.getTableName());
        r.setForeignKeyTable(child.getTableName());
        DebugUtil.debugln(getClass(), debug, "Created relationship from " + r.getPrimaryKeyTable() + " to "
                + r.getForeignKeyTable() + " named " + r.getName());

        KeyPair pair = factory.createKeyPair();
        pair.setPrimaryKeyColumn(parent.getColumnName());
        pair.setForeignKeyColumn(child.getColumnName());

        r.getKeyPair().add(pair);
        r.setMany(true);

        config.getRelationship().add(r);

    }

    public void addRelationship(Key parentKey, Key childKey) {

        if (config == null)
            config = factory.createConfig();

        QualifiedColumn parent = new QualifiedColumn((String) parentKey.getColumNames().get(0));
        QualifiedColumn child = new QualifiedColumn((String) childKey.getColumNames().get(0));

        Relationship r = factory.createRelationship();
        r.setName(child.getTableName());
        r.setPrimaryKeyTable(parent.getTableName());
        r.setForeignKeyTable(child.getTableName());
        DebugUtil.debugln(getClass(), debug, "Created relationship from " + r.getPrimaryKeyTable() + " to "
                + r.getForeignKeyTable() + " named " + r.getName());

        Iterator i = parentKey.getColumNames().iterator();
        Iterator j = childKey.getColumNames().iterator();
        while (i.hasNext() && j.hasNext()) {
            parent = new QualifiedColumn((String) i.next());
            child = new QualifiedColumn((String) j.next());
            KeyPair pair = factory.createKeyPair();
            pair.setPrimaryKeyColumn(parent.getColumnName());
            pair.setForeignKeyColumn(child.getColumnName());

            r.getKeyPair().add(pair);
        }
        r.setMany(true);
        config.getRelationship().add(r);

    }

    public void addPrimaryKey(String columnName) {
        Key k = new Key(columnName);
        addPrimaryKey(k, false);
    }

    public void addPrimaryKey(Key key) {
        addPrimaryKey(key, false);
    }

    public void addGeneratedPrimaryKey(String columnName) {
        Key key = new Key(columnName);
        addPrimaryKey(key, true);
    }

    private void addPrimaryKey(Key key, boolean generated) {
        if (config == null)
            config = factory.createConfig();

        Iterator i = key.getColumNames().iterator();
        while (i.hasNext()) {
            String columnName = (String) i.next();

            QualifiedColumn pkColumn = new QualifiedColumn(columnName);
            Table t = findOrCreateTable(pkColumn.getTableName());
            Column c = findOrCreateColumn(t, pkColumn.getColumnName());
            c.setPrimaryKey(true);
            if (generated)
                c.setGenerated(true);
        }
    }

    public String getTablePropertyName(String tableName) {
        Table t = getTable(tableName);
        if (t == null)
            return tableName;
        String propertyName = t.getPropertyName();

        if (propertyName == null)
            return tableName;

        return propertyName;
    }

    public Column getColumn(Table t, String name) {
        if (t == null)
            return null;
        Iterator i = t.getColumn().iterator();
        while (i.hasNext()) {
            Column c = (Column) i.next();
            if (c.getName().equals(name)) {
                return c;
            }
        }
        DebugUtil
                .debugln(getClass(), debug, "WARNING: Could not find column " + name + " in table " + t.getName());
        return null;
    }

    public Column getColumnByPropertyName(Table t, String propertyName) {
        if (t == null)
            return null;
        Iterator i = t.getColumn().iterator();
        while (i.hasNext()) {
            Column c = (Column) i.next();
            if (c.getName().equals(propertyName))
                return c;
            if (c.getPropertyName() != null && c.getPropertyName().equals(propertyName))
                return c;
        }
        DebugUtil.debugln(getClass(), debug, "WARNING: Could not find column " + propertyName + " in table "
                + t.getName());
        return null;
    }

    public String getColumnPropertyName(String tableName, String columnName) {
        Table t = getTable(tableName);
        Column c = getColumn(t, columnName);
        if (c == null)
            return columnName;

        String propertyName = c.getPropertyName();
        if (propertyName == null)
            return c.getName();

        return propertyName;
    }

    public void addCollisionColumn(String columnName) {

        if (config == null)
            config = factory.createConfig();

        QualifiedColumn occColumn = new QualifiedColumn(columnName);
        Table t = findOrCreateTable(occColumn.getTableName());
        Column c = findOrCreateColumn(t, occColumn.getColumnName());
        c.setCollision(true);

        config.getTable().add(t);
    }

    public void addTable(String tableName, String propertyName) {
        Table table = getTable(tableName);
        if (table != null)
            throw new Error("Table " + tableName + "already exists");

        table = ConfigFactoryImpl.eINSTANCE.createTable();
        table.setName(tableName);
        table.setPropertyName(propertyName);
        config.getTable().add(table);

    }

    private Table findOrCreateTable(String tableName) {
        Table table = getTable(tableName);
        if (table == null) {
            table = ConfigFactoryImpl.eINSTANCE.createTable();
            table.setName(tableName);
            config.getTable().add(table);
        }
        return table;

    }

    private Column findOrCreateColumn(Table t, String name) {
        Iterator i = t.getColumn().iterator();
        while (i.hasNext()) {
            Column c = (Column) i.next();
            if (name.equals(c.getName()))
                return c;
        }

        Column c = ConfigFactoryImpl.eINSTANCE.createColumn();
        c.setName(name);
        t.getColumn().add(c);
        return c;
    }

    public boolean hasRecursiveRelationships() {
        if (config != null) {
            Iterator i = getConfig().getRelationship().iterator();
            while (i.hasNext()) {
                Relationship r = (Relationship) i.next();
                if (r.getPrimaryKeyTable().equals(r.getForeignKeyTable()))
                    return true;
            }
        }
        return false;
    }

    public Collection getRelationshipsByChildTable(String name) {
        ArrayList results = new ArrayList();
        if (config != null) {
            Iterator i = getConfig().getRelationship().iterator();
            while (i.hasNext()) {
                Relationship r = (Relationship) i.next();
                if (name.equals(r.getForeignKeyTable()))
                    results.add(r);
            }
        }
        return results;
    }

    // TODO optimize
    public ArrayList getInsertOrder() {
        DebugUtil.debugln(getClass(), debug, "Getting insert order");
        ArrayList inserts = new ArrayList();
        HashMap parentToChild = new HashMap();

        ArrayList parents = new ArrayList();
        ArrayList children = new ArrayList();
        if (config != null) {
            Iterator i = getConfig().getRelationship().iterator();
            while (i.hasNext()) {
                Relationship r = (Relationship) i.next();
                parents.add(r.getPrimaryKeyTable());
                children.add(r.getForeignKeyTable());
                parentToChild.put(r.getPrimaryKeyTable(), r.getForeignKeyTable());
            }

            while (parents.size() > 0) {
                String parent = (String) parents.get(0);
                if (!children.contains(parent)) {
                    if (!inserts.contains(parent))
                        inserts.add(parent);

                    String child = (String) parentToChild.get(parent);
                    if (!inserts.contains(child))
                        inserts.add(child);

                    parents.remove(parent);
                    children.remove(child);
                } else {
                    parents.add(parents.remove(0));
                }
            }
            inserts.addAll(children);

        }

        DebugUtil.debugln(getClass(), debug, inserts);
        return inserts;
    }

    public ArrayList getDeleteOrder() {
        ArrayList deleteOrder = new ArrayList();
        deleteOrder.addAll(getInsertOrder());
        Collections.reverse(deleteOrder);
        return deleteOrder;
    }

    public void addConverter(String name, String converter) {
        if (config == null)
            config = factory.createConfig();

        QualifiedColumn column = new QualifiedColumn(name);
        Table t = findOrCreateTable(column.getTableName());
        Column c = findOrCreateColumn(t, column.getColumnName());
        c.setConverterClassName(converter);

    }

    public String getConverter(String tableName, String columnName) {
        Table t = getTable(tableName);
        Column c = getColumn(t, columnName);
        if (c != null)
            return c.getConverterClassName();
        return null;
    }

    public HashMap getConverters(Table table) {
        HashMap converters = new HashMap();

        Iterator columns = table.getColumn().iterator();
        while (columns.hasNext()) {
            Column c = (Column) columns.next();
            if (c.getConverterClassName() != null) {
                String property = c.getPropertyName();
                if (property == null)
                    property = c.getName();
                converters.put(property, c.getConverterClassName());
            }
        }
        return converters;
    }

    public Relationship getRelationshipByReference(Property ref) {
        Iterator i = config.getRelationship().iterator();
        while (i.hasNext()) {
            Relationship r = (Relationship) i.next();
            if (ref.getName().equals(r.getName()) || ref.getOpposite().getName().equals(r.getName()))
                return r;
        }
        throw new RuntimeException("Could not find relationship " + ref.getName() + " in the configuration");
    }

    public Relationship getRelationshipByName(String name) {
        Iterator i = config.getRelationship().iterator();
        while (i.hasNext()) {
            Relationship r = (Relationship) i.next();
            if (name.equals(r.getName()))
                return r;
        }
        throw new RuntimeException("Could not find relationship " + name + " in the configuration");
    }

}
