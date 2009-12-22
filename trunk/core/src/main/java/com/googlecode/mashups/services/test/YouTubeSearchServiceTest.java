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

import junit.framework.TestCase;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.YouTubeServicesFactory;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchResultItem;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchService;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchServiceParameters;

public class YouTubeSearchServiceTest extends TestCase {
    public void testgGetVideoList() {
        List<ServiceParameter> parameters           = new ArrayList<ServiceParameter>();     
        YouTubeSearchService   youTubeSearchService = YouTubeServicesFactory.getYouTubeSearchService();
       
        parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.VERSION, "2"));      
        parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.MAX_RESULTS, "25"));
        parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.CATEGORY, "sports"));
        parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.QUERY, "%22brazil%22"));          
         
        try {
            List<YouTubeSearchResultItem> videoResults = youTubeSearchService.getVideoList(parameters);
            
            for (YouTubeSearchResultItem searchItem : videoResults) {
                System.out.println("Video: " + searchItem);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the video list ...");
        }
        
    }
}
