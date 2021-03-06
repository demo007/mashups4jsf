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
package com.googlecode.mashups.services.unittests;

import java.io.FileWriter;
import java.io.Writer;

import junit.framework.TestCase;

import com.googlecode.mashups.services.factory.GenericServicesFactory;
import com.googlecode.mashups.services.generic.api.FeedType;

/**
 * @author hazems
 *
 */
public class ATOMFeedProducerServiceTest extends TestCase {

    public void testProduceATOMFeed() throws Exception {
        try {
            News news = new News();

            Writer writer = new FileWriter("ATOM_feed.xml");

            GenericServicesFactory.getFeedProducerService().produceFeed(FeedType.ATOM, news, writer);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the Rss Feed producer ...");
        }
    }
}
