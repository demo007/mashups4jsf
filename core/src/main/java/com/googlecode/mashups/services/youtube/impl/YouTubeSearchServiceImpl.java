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
package com.googlecode.mashups.services.youtube.impl;

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
import com.googlecode.mashups.services.youtube.api.YouTubeSearchResultItem;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchService;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchServiceParameters;

public class YouTubeSearchServiceImpl implements YouTubeSearchService {
    private static final String YOUTUBE_NAMESPACE_URL = "http://gdata.youtube.com/schemas/2007";
    private static final String MEDIA_NAMESPACE_URL   = "http://search.yahoo.com/mrss/";
    private static final String YOUTUBE_NAMESPACE     = "yt";    
    private static final String MEDIA_NAMESPACE       = "media";
    private static final String VIDEOID_ELEMENT       = "videoid";
    private static final String GROUP_ELEMENT         = "group";    

    public static YouTubeSearchService getInstance() {
        return youTubeSearchService;
    }
    
    @SuppressWarnings("unchecked")    
    public List<YouTubeSearchResultItem> getVideoList(List<ServiceParameter> parameters) throws Exception {
        parameters = prepareQueryParameters(parameters);
        
        URL                           feedUrl       = new URL(YOU_TUBE_SEARCH_SERVICE_URL
                                                    + "?"
                                                    + ServiceParametersUtility.toParametersString(parameters));
        SAXBuilder                    builder       = new SAXBuilder();
        Document                      document      = builder.build(feedUrl);
        Element                       rootElement   = document.getRootElement();
        List<YouTubeSearchResultItem> searchResults = new ArrayList<YouTubeSearchResultItem>();
        List<Element>                 children      = rootElement.getChild(ServiceConstants.RSS_CHANNEL)
                                                    . getChildren(ServiceConstants.RSS_ITEM);
        
        for (Element element : children) {
            String title   = element.getChild(ServiceConstants.RSS_TITLE).getText();
            String link    = element.getChild(ServiceConstants.RSS_LINK).getText();
            String author  = element.getChild(ServiceConstants.RSS_AUTHOR).getText();
            String pubDate = element.getChild(ServiceConstants.RSS_PUB_DATE).getText();  
            
            // get the video id
            Namespace mediaNamespace   = Namespace.getNamespace(MEDIA_NAMESPACE, MEDIA_NAMESPACE_URL);
            Namespace youTubeNamespace = Namespace.getNamespace(YOUTUBE_NAMESPACE, YOUTUBE_NAMESPACE_URL);            
            
            String videoID = element.getChild(GROUP_ELEMENT, mediaNamespace).getChild(VIDEOID_ELEMENT, youTubeNamespace).getValue();
            
            searchResults.add(new YouTubeSearchResultItem(title, link, author, videoID, pubDate));         
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
        
        if (parameters.contains(new ServiceParameter(YouTubeSearchServiceParameters.ALT))) {
            parameters.remove(new ServiceParameter(YouTubeSearchServiceParameters.ALT));
        }
        
        parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.ALT, ServiceConstants.RSS_FORMAT));
        
        return parameters;
    }    
    
    private YouTubeSearchServiceImpl() {
    }
    
    private static YouTubeSearchService youTubeSearchService = new YouTubeSearchServiceImpl();
}
