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
package com.googlecode.mashups.services.twitter.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import com.googlecode.mashups.services.common.ServiceConstants;
import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.common.ServiceParametersUtility;
import com.googlecode.mashups.services.twitter.api.TwitterSearchResultItem;
import com.googlecode.mashups.services.twitter.api.TwitterSearchService;

public class TwitterSearchServiceImpl implements TwitterSearchService {
    public static TwitterSearchService getInstance() {
    return twitterSearchService;
    }

    @SuppressWarnings("unchecked")    
    public List<TwitterSearchResultItem> getSearchList(List<ServiceParameter> parameters) throws Exception {
        parameters = prepareQueryParameters(parameters);
        
        URL                           feedUrl       = new URL(TWITTER_SEARCH_SERVICE_URL
                                                    + "?"
                                                    + ServiceParametersUtility.toParametersString(parameters));
        SAXBuilder                    builder       = new SAXBuilder();
        Document                      document      = builder.build(feedUrl);
        Namespace 		      namespace     = Namespace.getNamespace(ServiceConstants.ATOM_FEED, ServiceConstants.ATOM_NAMESPACE_URI);        
        List<TwitterSearchResultItem> searchResults = new ArrayList<TwitterSearchResultItem>();
        List<Element>                 children      = document.getRootElement().getChildren(ServiceConstants.ATOM_ENTRY, namespace);;
        
        for (Element element : children) {
            String titleNotFormatted = element.getChildText(ServiceConstants.TITLE, namespace);
            String title             = element.getChildText(ServiceConstants.ATOM_CONTENT, namespace);         
            String publishedDate     = element.getChildText(ServiceConstants.ATOM_PUB_DATE, namespace);            
            String updateDate        = element.getChildText(ServiceConstants.ATOM_UPDATE_DATE, namespace);            
            String authorName        = element.getChild(ServiceConstants.AUTHOR, namespace).getChildText(ServiceConstants.ATOM_AUTHOR_NAME, namespace);
            String authorTwitterURL  = element.getChild(ServiceConstants.AUTHOR, namespace).getChildText(ServiceConstants.ATOM_URI, namespace);  
            String authorImageURL    = "";
            
            List links = element.getChildren(ServiceConstants.LINK, namespace);
            
            for (int i = 0; i < links.size(); ++i) {
        	Element linkElement = (Element) links.get(i);
        	
        	if (linkElement.getAttributeValue(ServiceConstants.TYPE).contains(ServiceConstants.IMAGE)) {
        	    authorImageURL = linkElement.getAttributeValue(ServiceConstants.HREF);
        	}
            }
            
            searchResults.add(new TwitterSearchResultItem(title, titleNotFormatted, authorName, authorTwitterURL, authorImageURL, publishedDate, updateDate));       
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

        return parameters;
    }        

    private TwitterSearchServiceImpl() {
    }

    private static TwitterSearchService twitterSearchService = new TwitterSearchServiceImpl();
}