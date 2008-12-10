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
package crawler.impl;

import crawler.Crawler;
import crawler.CrawlerController;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.sca.api.ComponentContextExtension;
import org.osoa.sca.ComponentContext;
import org.osoa.sca.annotations.AllowsPassByReference;
import org.osoa.sca.annotations.Context;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.osoa.sca.annotations.Service;

@Service(CrawlerController.class)
@Scope("COMPOSITE")
@AllowsPassByReference
public class CrawlerControllerImpl implements CrawlerController
{
    @Context
    protected ComponentContext componentContext;

    @Reference
    public List<Crawler> crawlers;

    /**
     * @see CrawlerController#getCrawler(String)
     */
    public Crawler getCrawler(String crawlerId)
    {
        for (int i = 0; i < crawlers.size(); i++)
        {
            Crawler crawler = crawlers.get(i);
            String id = crawler.getCrawlerId();
            if (crawlerId.equals(id))
            {
                Crawler newCrawler = componentContext.getService(Crawler.class, "crawlers");
                return newCrawler;
            }
        }
        throw new RuntimeException("No crawler with id " + crawlerId + " found");
    }

    /**
     * @see CrawlerController#findCrawler(String)
     */
    public Crawler findCrawler(String crawlerId)
    {
        Collection<Crawler> crawlers = ((ComponentContextExtension)componentContext).getServices(Crawler.class, "crawlers");
        for (Crawler crawler : crawlers)
        {
            String id = crawler.getCrawlerId();
            if (crawlerId.equals(id))
            {
                return crawler;
            }
        }
        throw new RuntimeException("No crawler with id " + crawlerId + " found");
    }
}
