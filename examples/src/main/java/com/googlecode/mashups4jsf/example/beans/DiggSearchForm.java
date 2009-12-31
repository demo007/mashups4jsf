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
import javax.faces.bean.RequestScoped;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.digg.api.DiggSearchServiceParameters;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResults;
import com.googlecode.mashups.services.factory.DiggServicesFactory;

/***/
@ManagedBean
@RequestScoped
public class DiggSearchForm {
    private String                 searchQuery;
    private DiggSearchStoryResults results;

    /**
     * @return the searchQuery
     */
    public String getSearchQuery() {
        return searchQuery;
    }

    /**
     * @param searchQuery
     *            the searchQuery to set
     */
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * @return the results
     */
    public DiggSearchStoryResults getResults() {
        return results;
    }

    /**
     * @param results
     *            the results to set
     */
    public void setResults(DiggSearchStoryResults results) {
        this.results = results;
    }

    /** Used for calling the search service action . */
    public String getStoryList() {
        List<ServiceParameter> searchParameters = new ArrayList<ServiceParameter>();

        searchParameters.add(new ServiceParameter(
                DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_QUERY,searchQuery));
        searchParameters.add(new ServiceParameter(
                DiggSearchServiceParameters.SEARCH_ARGS_COUNT,"5"));
        

        try {
            results = DiggServicesFactory.getDiggSearchService()
                    . getStoriesList(searchParameters);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
