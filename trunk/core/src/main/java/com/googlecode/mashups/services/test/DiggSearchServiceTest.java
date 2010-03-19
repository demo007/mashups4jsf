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
package com.googlecode.mashups.services.test;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.digg.api.*;
import com.googlecode.mashups.services.factory.DiggServicesFactory;

import junit.framework.TestCase;

public class DiggSearchServiceTest extends TestCase {
    public void testGetSearchStory(){
        List<ServiceParameter> parameters  = new ArrayList<ServiceParameter>();
        DiggSearchService      diggService = DiggServicesFactory.getDiggSearchService();
        
        //Mandatory
        parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_QUERY,"JSF"));
        //parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_TYPE,"xml"));
        
        //Optional Parameters
        parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_COUNT, "5"));
        parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_OFFSET, "0"));
        parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_DOMAIN, "digg.com"));
        //parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_SORT,""));
        
        try {
            DiggSearchStoryResults storyResults = diggService.getStoriesList(parameters);
            
            for (DiggSearchStoryResultItem searchItem : storyResults.getSearchResultList()) {
                System.out.println("Story : " + searchItem);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the story search list ...");
        }
        
    }
}
