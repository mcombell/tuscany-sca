/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package org.apache.tuscany.das.rdb.test.suites;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.tuscany.das.rdb.test.AliasTests;
import org.apache.tuscany.das.rdb.test.BestPracticeTests;
import org.apache.tuscany.das.rdb.test.CUDGeneration;
import org.apache.tuscany.das.rdb.test.CommandGroupTests;
import org.apache.tuscany.das.rdb.test.CompanyTests;
import org.apache.tuscany.das.rdb.test.CompoundKeyRelationshipTests;
import org.apache.tuscany.das.rdb.test.CompoundKeyTests;
import org.apache.tuscany.das.rdb.test.ConverterTests;
import org.apache.tuscany.das.rdb.test.CorrectedDefectTests;
import org.apache.tuscany.das.rdb.test.CrudWithChangeHistory;
import org.apache.tuscany.das.rdb.test.ExceptionTests;
import org.apache.tuscany.das.rdb.test.GeneratedCommandTests;
import org.apache.tuscany.das.rdb.test.GeneratedId;
import org.apache.tuscany.das.rdb.test.GraphMergeTests;
import org.apache.tuscany.das.rdb.test.ImpliedRelationshipTests;
import org.apache.tuscany.das.rdb.test.KennelTests;
import org.apache.tuscany.das.rdb.test.MultiSchemaTests;
import org.apache.tuscany.das.rdb.test.OCCTests;
import org.apache.tuscany.das.rdb.test.OneToOneRelationshipTests;
import org.apache.tuscany.das.rdb.test.OperationOrderingTests;
import org.apache.tuscany.das.rdb.test.Paging;
import org.apache.tuscany.das.rdb.test.PartialUpdateTests;
import org.apache.tuscany.das.rdb.test.PassiveConnectionTests;
import org.apache.tuscany.das.rdb.test.ProgrammaticConfigTests;
import org.apache.tuscany.das.rdb.test.RecursiveTests;
import org.apache.tuscany.das.rdb.test.RelationshipTests;
import org.apache.tuscany.das.rdb.test.ResultSetShapeTests;
import org.apache.tuscany.das.rdb.test.SerializationTests;
import org.apache.tuscany.das.rdb.test.SimplestCrud;
import org.apache.tuscany.das.rdb.test.StoredProcs;
import org.apache.tuscany.das.rdb.test.TopDown;
import org.apache.tuscany.das.rdb.test.TransactionTests;
import org.apache.tuscany.das.rdb.test.TypeTests;
import org.apache.tuscany.das.rdb.test.typed.SimplestStaticCrud;

public final class AllCommonTests {

    private AllCommonTests() {  
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("All platform-common DAS tests");
        // $JUnit-BEGIN$

        suite.addTest(new TestSuite(SimplestCrud.class));
        suite.addTest(new TestSuite(CrudWithChangeHistory.class));
        suite.addTest(new TestSuite(SimplestStaticCrud.class));
        suite.addTest(new TestSuite(Paging.class));
        suite.addTest(new TestSuite(GeneratedId.class));

        suite.addTest(new TestSuite(StoredProcs.class));
        suite.addTest(new TestSuite(CUDGeneration.class));
        suite.addTest(new TestSuite(TopDown.class));
        suite.addTest(new TestSuite(OCCTests.class));
        suite.addTest(new TestSuite(RecursiveTests.class));

        suite.addTest(new TestSuite(GraphMergeTests.class));
        suite.addTest(new TestSuite(CompoundKeyTests.class));
        suite.addTest(new TestSuite(CompoundKeyRelationshipTests.class));
        suite.addTest(new TestSuite(RelationshipTests.class));
        suite.addTest(new TestSuite(GeneratedCommandTests.class));

        suite.addTest(new TestSuite(CompanyTests.class));
        suite.addTest(new TestSuite(ResultSetShapeTests.class));
        suite.addTest(new TestSuite(TypeTests.class));
        suite.addTest(new TestSuite(OperationOrderingTests.class));
        suite.addTest(new TestSuite(ConverterTests.class));

        suite.addTest(new TestSuite(PartialUpdateTests.class));
        suite.addTest(new TestSuite(ExceptionTests.class));
        suite.addTest(new TestSuite(PassiveConnectionTests.class));
        suite.addTest(new TestSuite(SerializationTests.class));

        suite.addTest(new TestSuite(CommandGroupTests.class));
        suite.addTest(new TestSuite(BestPracticeTests.class));
        suite.addTest(new TestSuite(CorrectedDefectTests.class));
        suite.addTest(new TestSuite(OneToOneRelationshipTests.class));

        suite.addTest(new TestSuite(ProgrammaticConfigTests.class));
        suite.addTest(new TestSuite(AliasTests.class));

        suite.addTest(new TestSuite(ImpliedRelationshipTests.class));

        suite.addTest(new TestSuite(KennelTests.class));
        
        suite.addTest(new TestSuite(TransactionTests.class));
        suite.addTest(new TestSuite(MultiSchemaTests.class));
    
        //$JUnit-END$
        return suite;
    }

}
