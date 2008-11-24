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
package crawler;

import crawler.Crawler;
import crawler.CrawlerController;

import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.apache.tuscany.sca.node.SCAClient;
import org.apache.tuscany.sca.node.SCAContribution;
import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.SCANodeFactory;
import org.osoa.sca.ServiceRuntimeException;

import junit.framework.TestCase;

public class ReferenceMultiplicityTestCase extends TestCase
{
    private final String CRAWLER_ID = "normalcrawler";
    private final String SPECIAL_CRAWLER_ID = "specialcrawler";

    private static SCANode _scaDomain;
    private CrawlerController _controller;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        SCANodeFactory nodeFactory = SCANodeFactory.newInstance();
        _scaDomain = nodeFactory.createSCANode("test.composite", 
                                               new SCAContribution("crawler", "./target/classes"));
        assertNotNull(_scaDomain);
        _scaDomain.start();
        _controller = ((SCAClient)_scaDomain).getService(CrawlerController.class, "CrawlerControllerComponent");
        assertNotNull(_controller);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        _controller = null;
        if (_scaDomain != null)
        {
            _scaDomain.stop();
            _scaDomain = null;
        }
    }

    public void testGetCrawler() throws Exception
    {
        try
        {
            _controller.getCrawler(CRAWLER_ID);
            fail("Expected exception");
        }
        catch (ServiceRuntimeException e)
        {
            assertEquals("Unexpected error message", "Reference crawlers has multiplicity ONE_N", e.getMessage());
        }
        catch (Exception e)
        {
            fail("Expected ServiceRuntimeException, not a " + e.getClass());
        }

        try
        {
            _controller.getCrawler(SPECIAL_CRAWLER_ID);
            fail("Expected exception");
        }
        catch (ServiceRuntimeException e)
        {
            assertEquals("Unexpected error message", "Reference crawlers has multiplicity ONE_N", e.getMessage());
        }
        catch (Exception e)
        {
            fail("Expected ServiceRuntimeException, not a " + e.getClass());
        }
    }
/*
    public void testFindCrawler() throws Exception
    {
        try
        {
            Crawler crawler = _controller.findCrawler(CRAWLER_ID);
            assertNotNull(crawler);
            String msg = crawler.crawl();
            assertEquals("Received unexpected msg", "started crawl with id " + CRAWLER_ID, msg);
            msg = crawler.close();
            assertEquals("Received unexpected msg", "ended conversation with id " + CRAWLER_ID, msg);
        }        
        catch (Exception e)
        {
            fail("Unexpected Exception " + e.getClass());
        }

        try
        {
            Crawler crawler = _controller.findCrawler(SPECIAL_CRAWLER_ID);
            assertNotNull(crawler);
            String msg = crawler.crawl();
            assertEquals("Received unexpected msg", "started crawl with id " + SPECIAL_CRAWLER_ID, msg);
            msg = crawler.close();
            assertEquals("Received unexpected msg", "ended conversation with id " + SPECIAL_CRAWLER_ID, msg);
        }
        catch (Exception e)
        {
            fail("Unexpected Exception " + e.getClass());
        }
    }
*/
}
