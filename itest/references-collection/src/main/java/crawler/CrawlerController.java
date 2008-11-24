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

import org.osoa.sca.annotations.Remotable;

@Remotable
public interface CrawlerController
{
    /**
     * Tries to return a Crawler with the given Id in the list of crawlers.
     * It uses the getServiceReference() method which should throw an exception because of multiplicity 0..n
     * @param crawlerId the id of the Crawler
     * @return the Crawler
     */
    Crawler getCrawler(String crawlerId);

    /**
     * Tries to return a Crawler with the given Id in the list of crawlers.
     * It uses the getServiceReferences() method which shoulkd work properly with multiplicity 0..n
     * @param crawlerId the id of the Crawler
     * @return the Crawler
     */
    Crawler findCrawler(String crawlerId);
}
