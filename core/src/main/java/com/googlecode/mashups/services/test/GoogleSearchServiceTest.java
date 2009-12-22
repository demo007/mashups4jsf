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

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.GoogleServicesFactory;
import com.googlecode.mashups.services.google.api.GoogleSearchResultItem;
import com.googlecode.mashups.services.google.api.GoogleSearchService;
import com.googlecode.mashups.services.google.api.GoogleSearchServiceParameters;

public class GoogleSearchServiceTest extends TestCase {
    public void testGetWebSearchResults() throws Exception {
        List<ServiceParameter> parameters           = new ArrayList<ServiceParameter>();     
        GoogleSearchService    googleSearchService  = GoogleServicesFactory.getGoogleSearchService();
       
        parameters.add(new ServiceParameter(GoogleSearchServiceParameters.VERSION, "1.0"));      
        parameters.add(new ServiceParameter(GoogleSearchServiceParameters.RSZ, "large"));
        parameters.add(new ServiceParameter(GoogleSearchServiceParameters.QUERY, "Egypt Algeria"));        
         
        try {
            List<GoogleSearchResultItem> webSearchResults = googleSearchService.getWebSearchResultList(parameters);
            
            for (GoogleSearchResultItem searchItem : webSearchResults) {
                System.out.println("Web news: " + searchItem);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the web news list ...");
        }        
    }
}
