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
package com.googlecode.mashups.services.digg.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.common.ServiceParametersUtility;
import com.googlecode.mashups.services.digg.api.DiggSearchService;
import com.googlecode.mashups.services.digg.api.DiggSearchServiceParameters;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResultItem;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResults;
import com.googlecode.mashups.services.digg.exceptions.InvalidResultException;

public class DiggSearchServiceImpl implements DiggSearchService {
    
    // XML Tags for Digg Stories
    private static final String DIGG_STORIES_ELEMENT          = "stories";
    private static final String DIGG_STORY_ELEMENT            = "story";
    private static final String DIGG_DESCRIPTION_ELEMENT      = "description";
    private static final String DIGG_TITLE_ELEMENT            = "title";
    private static final String DIGG_USER_ELEMENT             = "user";
    private static final String DIGG_TOPIC_ELEMENT            = "topic";
    private static final String DIGG_CONTAINER_ELEMENT        = "container";
    private static final String DIGG_SHORTURL_ELEMENT         = "shorturl";
    
    // Stories Elements Attributes
    private static final String STORIES_COUNT_ATTRIBUTE       = "count";
    private static final String STORIES_OFFSET_ATTRIBUTE      = "offset";
    private static final String STORIES_TIME_ATTRIBUTE        = "timestamp";
    private static final String STORIES_TOTAL_ATTRIBUTE       = "total";
    private static final String STORIES_VERSION_ATTRIBUTE     = "version";
    
    // Story Element Attributes
    private static final String STORY_LINK_ATTRIBUTE          = "link";
    private static final String STORY_SUBMITDATE_ATTRIBUTE    = "submit_date";
    private static final String STORY_DIGGS_ATTRIBUTE         = "diggs";
    private static final String STORY_ID_ATTRIBUTE            = "id";
    private static final String STORY_COMMENTS_ATTRIBUTE      = "comments";
    private static final String STORY_HREF_ATTRIBUTE          = "href";
    private static final String STORY_STATUS_ATTRIBUTE        = "status";
    private static final String STORY_MEDIA_ATTRIBUTE         = "media";
    
    // User Element Attributes
    private static final String USER_ICON_ATTRIBUTE           = "userIcon";
    private static final String USER_NAME_ATTRIBUTE           = "name";
    private static final String USER_PROFILEVIEWS_ATTRIBUTE   = "profileviews";
    private static final String USER_REGISTERED_ATTRIBUTE     = "registered";
    
    // Topic Element Attributes
    private static final String TOPIC_NAME_ATTRIBUTE          = "name";
    private static final String TOPIC_SHORTNAME_ATTRIBUTE     = "short_name";

    private static final String CONTAINER_NAME_ATTRIBUTE      = "name";
    private static final String CONTAINER_SHORTNAME_ATTRIBUTE = "short_name";
    
    // ShortUSRL
    private static final String SHORTURL_URL_ATTRIBUTE        = "short_url";
    private static final String SHORTURL_VIEW_COUNT_ATTRIBUTE = "view_count";

    public static DiggSearchService getInstance() {
        return diggSearchService;
    }

    @SuppressWarnings("unchecked")
    public DiggSearchStoryResults getSearchStory(List<ServiceParameter> parameters) throws Exception {

        // Prepare search story parameters
        parameters = prepareSearchStoryQueryParameters(parameters);

        // Prepare the search url
        URL searchUrl = new URL(DIGG_SEARCH_SERVICE_URL + "?"
                + ServiceParametersUtility.toParametersString(parameters));
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(searchUrl);
        Element storiesElement = document.getRootElement();
        
        if (!DIGG_STORIES_ELEMENT.equals(storiesElement.getName())) {
            throw new InvalidResultException(
                    "The result root element is expected to be (" + DIGG_STORIES_ELEMENT
							+ ") but returned element is (" + storiesElement + ")");
        }

        List<Element> storyList = storiesElement.getChildren(DIGG_STORY_ELEMENT);

        DiggSearchStoryResults results = new DiggSearchStoryResults();
        
        // parse here
        results.setCount(storiesElement.getAttributeValue(STORIES_COUNT_ATTRIBUTE));
        results.setOffset(storiesElement.getAttributeValue(STORIES_OFFSET_ATTRIBUTE));
        results.setTotal(storiesElement.getAttributeValue(STORIES_TOTAL_ATTRIBUTE));
        results.setTimestamp(storiesElement.getAttributeValue(STORIES_TIME_ATTRIBUTE));
        results.setVersion(storiesElement.getAttributeValue(STORIES_VERSION_ATTRIBUTE));
        System.out.println("Stories :" + results);

        List<DiggSearchStoryResultItem> searchResultList = new ArrayList<DiggSearchStoryResultItem>();

        // Add each parsed story
        for (Element element : storyList) {
        
            // Cursor element
            Element cur = null;
            DiggSearchStoryResultItem resultItem = new DiggSearchStoryResultItem();
            
            // Story Element
            resultItem.setStoryComments(element.getAttributeValue(STORY_COMMENTS_ATTRIBUTE));
            resultItem.setStoryDiggs(element.getAttributeValue(STORY_DIGGS_ATTRIBUTE));
            resultItem.setStoryHref(element.getAttributeValue(STORY_HREF_ATTRIBUTE));
            resultItem.setStoryId(element.getAttributeValue(STORY_ID_ATTRIBUTE));
            resultItem.setStoryLink(element.getAttributeValue(STORY_LINK_ATTRIBUTE));
            resultItem.setStoryMedia(element.getAttributeValue(STORY_MEDIA_ATTRIBUTE));
            resultItem.setStoryStatus(element.getAttributeValue(STORY_STATUS_ATTRIBUTE));
            resultItem.setStorySubmitDate(element.getAttributeValue(STORY_SUBMITDATE_ATTRIBUTE));

            // Description
            resultItem.setDescription(element.getChild(DIGG_DESCRIPTION_ELEMENT).getText());

            // Title
            resultItem.setTitle(element.getChild(DIGG_TITLE_ELEMENT).getText());
            
            // User Info
            cur = element.getChild(DIGG_USER_ELEMENT);
            
            resultItem.setUserIcon(cur.getAttributeValue(USER_ICON_ATTRIBUTE));
            resultItem.setUserName(cur.getAttributeValue(USER_NAME_ATTRIBUTE));
            resultItem.setUserProfileviews(cur.getAttributeValue(USER_PROFILEVIEWS_ATTRIBUTE));
            resultItem.setUserRegistered(cur.getAttributeValue(USER_REGISTERED_ATTRIBUTE));

            // Topic
            cur = element.getChild(DIGG_TOPIC_ELEMENT);
            
            resultItem.setTopicName(cur.getAttributeValue(TOPIC_NAME_ATTRIBUTE));
            resultItem.setTopicShortName(cur.getAttributeValue(TOPIC_SHORTNAME_ATTRIBUTE));

            // Container
            cur = element.getChild(DIGG_CONTAINER_ELEMENT);
            
            resultItem.setContainerName(cur.getAttributeValue(CONTAINER_NAME_ATTRIBUTE));
            resultItem.setContainerShortName(cur.getAttributeValue(CONTAINER_SHORTNAME_ATTRIBUTE));

            // Short_url
            cur = element.getChild(DIGG_SHORTURL_ELEMENT);
            
            resultItem.setShorturl(cur.getAttributeValue(SHORTURL_URL_ATTRIBUTE));
            resultItem.setShorturlViewCount(cur.getAttributeValue(SHORTURL_VIEW_COUNT_ATTRIBUTE));
            
            //System.out.println(resultItem);
            
            // Add the populated item to the resultList
            searchResultList.add(resultItem);
        }
        
        results.setSearchResultList(searchResultList);
        
        return results;
    }

    /** Prepare and normalize the required parameter before submitting */
    private List<ServiceParameter> prepareSearchStoryQueryParameters(
            List<ServiceParameter> parameters) {
        
        List<ServiceParameter> supportedParameters = new ArrayList<ServiceParameter>();
        supportedParameters.add(new ServiceParameter(
                DiggSearchServiceParameters.SEARCH_ARGS_METHOD,
                DiggSearchService.DIGG_SEARCH_STORY_METHOD));
        supportedParameters.add(new ServiceParameter(
                DiggSearchServiceParameters.SEARCH_ARGS_TYPE,
                DiggSearchService.DIGG_SEARCH_STORY_TYPE));

        // filter the supported argument from the parameters
        for (ServiceParameter param : parameters) {
            if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_COUNT)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            } else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_OFFSET)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            } else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_DOMAIN)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            } else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_SORT)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            } else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_QUERY)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            }
        }

        return supportedParameters;
    }

    private DiggSearchServiceImpl() {
    }

    private static DiggSearchService diggSearchService = new DiggSearchServiceImpl();
}
