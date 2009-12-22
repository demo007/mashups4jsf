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
package com.googlecode.mashups4jsf.example.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.GoogleServicesFactory;
import com.googlecode.mashups.services.google.api.GoogleSearchResultItem;
import com.googlecode.mashups.services.google.api.GoogleSearchServiceParameters;

/**
 * @author hazems
 *
 */
@ManagedBean
public class GoogleSearchForm {
    private String searchQuery;
    private List<GoogleSearchResultItem> results;
    
    /**
     * @return the searchQuery
     */
    public String getSearchQuery() {
        return searchQuery;
    }

    /**
     * @param searchQuery the searchQuery to set
     */
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * @return the results
     */
    public List<GoogleSearchResultItem> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(List<GoogleSearchResultItem> results) {
        this.results = results;
    }

    public String getWebSearchResultList() {
        List<ServiceParameter> searchParameters = new ArrayList<ServiceParameter>();
       
        System.out.println("search query is: " + searchQuery);
        
        searchParameters.add(new ServiceParameter(GoogleSearchServiceParameters.QUERY, searchQuery));
        searchParameters.add(new ServiceParameter(GoogleSearchServiceParameters.RSZ, "large"));        
        
        try {
            results = GoogleServicesFactory.getGoogleSearchService().getWebSearchResultList(searchParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
