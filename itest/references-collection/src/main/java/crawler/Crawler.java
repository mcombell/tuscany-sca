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

import org.osoa.sca.annotations.Conversational;
import org.osoa.sca.annotations.EndsConversation;
import org.osoa.sca.annotations.Remotable;

@Remotable
@Conversational
public interface Crawler
{
    /**
     * Returns the crawler Id.
     * @return a String containing the CrawlerId
     */
    String getCrawlerId();

    /**
     * Starts a crawl process.
     * @return a message that the crawl was started
     */
    String crawl();
    
    /**
     * Closes the conversation.
     */
    @EndsConversation
    String close();   
}
