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
package com.googlecode.mashups.services.google.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.common.ServiceParametersUtility;
import com.googlecode.mashups.services.google.api.GoogleSearchResultItem;
import com.googlecode.mashups.services.google.api.GoogleSearchService;
import com.googlecode.mashups.services.google.api.GoogleSearchServiceParameters;

public class GoogleSearchServiceImpl implements GoogleSearchService {
    private static final String REFERER               = "Referer";
    private static final String REFERER_VALUE         = "http://www.mashups4jsf.com";
    private static final String RESPONSE_DATA_ROOT    = "responseData";
    private static final String RESPONSE_DATA_RESULTS = "results";
    private static final String TITLE                 = "title";  
    private static final String TITLE_NO_FORMATTING   = "titleNoFormatting";    
    private static final String URL                   = "url";    
    private static final String UNESCAPED_URL         = "unescapedUrl";
    private static final String CACHE_URL             = "cacheUrl";
    private static final String CONTENT               = "content";
    private static final String VISIBLE_URL           = "visibleUrl";    
    private static final String GSEARCH_RESULT_CLASS  = "GsearchResultClass";    

    public static GoogleSearchService getInstance() {
        return googleSearchService;
    }
     
    public List<GoogleSearchResultItem> getWebSearchResultList(List<ServiceParameter> parameters) throws Exception {
        parameters = prepareQueryParameters(parameters);
        
        URL                           feedUrl      = new URL(GOOGLE_SEARCH_SERVICE_URL
                                                   + "?"
                                                   + ServiceParametersUtility.toParametersString(parameters));
        List<GoogleSearchResultItem> searchResults = new ArrayList<GoogleSearchResultItem>();
        URLConnection                connection    = feedUrl.openConnection();
        
        connection.addRequestProperty(REFERER, REFERER_VALUE);

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        JSONObject json    = new JSONObject(builder.toString());
        JSONObject root    = (JSONObject) json.get(RESPONSE_DATA_ROOT);
        JSONArray  results = root.getJSONArray(RESPONSE_DATA_RESULTS); 
        
        for (int i = 0; i < results.length(); ++i) {
            JSONObject searchItem  = (JSONObject) results.get(i);
            
            // Get attributes.
            String title              = (String) searchItem.get(TITLE);
            String titleNotFormatting = (String) searchItem.get(TITLE_NO_FORMATTING);              
            String content            = (String) searchItem.get(CONTENT);
            String cacheUrl           = (String) searchItem.get(CACHE_URL);
            String unescapedUrl       = (String) searchItem.get(UNESCAPED_URL);
            String visibleUrl         = (String) searchItem.get(VISIBLE_URL);
            String url                = (String) searchItem.get(URL);               
            String GsearchResultClass = (String) searchItem.get(GSEARCH_RESULT_CLASS);
            
            // Fill the GoogleSearchResultItem.
            GoogleSearchResultItem googleSearchResultItem = new GoogleSearchResultItem();
            
            googleSearchResultItem.setTitle(title);
            googleSearchResultItem.setTitleNotFormatting(titleNotFormatting);
            googleSearchResultItem.setContent(content);
            googleSearchResultItem.setCacheUrl(cacheUrl);
            googleSearchResultItem.setUnescapedUrl(unescapedUrl);
            googleSearchResultItem.setVisibleUrl(visibleUrl);
            googleSearchResultItem.setUrl(url);
            googleSearchResultItem.setGsearchResultClass(GsearchResultClass);
            
            searchResults.add(googleSearchResultItem);
        }        
        
        return searchResults;
    }    
    
    /*
     * @param parameters
     * @return
     */
    private List<ServiceParameter> prepareQueryParameters(List<ServiceParameter> parameters) {
        if (parameters == null) {
            parameters = new ArrayList<ServiceParameter>();
        }
        
        if (parameters.contains(new ServiceParameter(GoogleSearchServiceParameters.VERSION))) {
            parameters.remove(new ServiceParameter(GoogleSearchServiceParameters.VERSION));
        }
        
        parameters.add(new ServiceParameter(GoogleSearchServiceParameters.VERSION, "1.0"));
        
        return parameters;
    }      
    
    private GoogleSearchServiceImpl() {
    }
    
    private static GoogleSearchService googleSearchService = new GoogleSearchServiceImpl();
}