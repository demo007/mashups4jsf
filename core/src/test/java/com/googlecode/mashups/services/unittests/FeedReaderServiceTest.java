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

import org.json.JSONArray;
import org.json.JSONObject;

import junit.framework.TestCase;

import com.googlecode.mashups.services.factory.GenericServicesFactory;

/**
 * @author hazems
 *
 */
public class FeedReaderServiceTest extends TestCase {

    public void testGetJSONFeedFromGoogle() throws Exception {
        try {
            String feedURL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=javaone";
            JSONArray resultArray = (JSONArray) GenericServicesFactory.getFeedReaderService().readJSONFeed(feedURL, "results");
            
            System.out.println("Trying to search for JavaOne in Google ...");
            
            for (int i = 0; i < resultArray.length(); ++i) {
                JSONObject jsonObject = (JSONObject) resultArray.get(i);
                
                System.out.println("Title: \"" + jsonObject.get("titleNoFormatting") + "\", URL: " + jsonObject.get("url"));
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to execute the JSON Feed Reader ...");
        }
    }
    
    public void testGetJSONFeedFromYahooImageSearch() throws Exception {
        try {
            String feedURL = "http://search.yahooapis.com/ImageSearchService/V1/imageSearch?appid=YahooDemo&query=javaone&output=json";
            JSONArray resultArray = (JSONArray) GenericServicesFactory.getFeedReaderService().readJSONFeed(feedURL, "Result");
            
            System.out.println("Trying to search for JavaOne in Yahoo Images ...");
            
            for (int i = 0; i < resultArray.length(); ++i) {
                JSONObject jsonObject = (JSONObject) resultArray.get(i);
                
                System.out.println("Title: \"" + jsonObject.get("Title") + "\", URL: " + jsonObject.get("Url"));
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to execute the JSON Feed Reader ...");
        }
    }   
    
    public void testGetJSONFeedFromDelicious() throws Exception {
        try {
            String feedURL = "http://feeds.delicious.com/v2/json/popular/javaone";
            JSONArray resultArray = (JSONArray) GenericServicesFactory.getFeedReaderService().readJSONFeed(feedURL, "none");
            
            System.out.println("Trying to search for popular 'JavaOne' tags in Delicious ...");
            
            for (int i = 0; i < resultArray.length(); ++i) {
                JSONObject jsonObject = (JSONObject) resultArray.get(i);
                
                System.out.println("Title: \"" + jsonObject.get("d") + "\", URL: " + jsonObject.get("u"));
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to execute the JSON Feed Reader ...");
        }
    }       
}
