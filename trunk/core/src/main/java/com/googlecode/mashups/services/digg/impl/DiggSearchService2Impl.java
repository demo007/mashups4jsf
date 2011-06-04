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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.common.ServiceParametersUtility;
import com.googlecode.mashups.services.digg.api.DiggSearchService;
import com.googlecode.mashups.services.digg.api.DiggSearchServiceParameters;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResultItem;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResults;
import com.googlecode.mashups.services.digg.exceptions.InvalidResultException;

/** 
 * Implements DiggSearchService methods that support digg REST API v2.0 with JSON results
 * 
 * @author moh.maher
 *
 * @version 2.0 
 **/
public class DiggSearchService2Impl implements DiggSearchService {

    // XML Tags for Digg Stories
    private static final String DIGG_STORIES_ELEMENT          = "stories";
    private static final String DIGG_STORY_ELEMENT            = "story";
    private static final String DIGG_DESCRIPTION_ELEMENT      = "description";
    private static final String DIGG_TITLE_ELEMENT            = "title";
    private static final String DIGG_USER_ELEMENT             = "user";
    private static final String DIGG_TOPIC_ELEMENT            = "topic";
    private static final String DIGG_CONTAINER_ELEMENT        = "container";
    private static final String DIGG_SHORTURL_ELEMENT         = "shorturl";
    private static final String DIGG_THUMBNAIL_ELEMENT        = "thumbnail";

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
    private static final String USER_ICON_ATTRIBUTE           = "icon";
    private static final String USER_NAME_ATTRIBUTE           = "name";
    private static final String USER_FULLNAME_ATTRIBUTE       = "fullname";
    private static final String USER_LINKS_ATTRIBUTE       	  = "links";
    private static final String USER_PROFILEVIEWS_ATTRIBUTE   = "profileviews";
    private static final String USER_REGISTERED_ATTRIBUTE     = "registered";

    // Topic Element Attributes
    private static final String TOPIC_NAME_ATTRIBUTE          = "name";
    private static final String TOPIC_SHORTNAME_ATTRIBUTE     = "short_name";

    private static final String CONTAINER_NAME_ATTRIBUTE      = "name";
    private static final String CONTAINER_SHORTNAME_ATTRIBUTE = "short_name";

    // ShortURL
    private static final String SHORTURL_URL_ATTRIBUTE        = "short_url";
    private static final String SHORTURL_VIEW_COUNT_ATTRIBUTE = "view_count";
    
    // Thumbnail
    private static final String THUMBNAIL_CONTENT_TYPE_ATTRIBUTE = "contentType";
    private static final String THUMBNAIL_SRC_ATTRIBUTE = "src";
    private static final String THUMBNAIL_HEIGHT_ATTRIBUTE = "height";
    private static final String THUMBNAIL_WIDTH_ATTRIBUTE = "width";
    private static final String THUMBNAIL_ORIGINAL_HEIGHT_ATTRIBUTE = "originalheight";
    private static final String THUMBNAIL_ORIGINAL_WIDTH_ATTRIBUTE = "originalwidth";
    

    public static DiggSearchService getInstance() {
        return diggSearchService;
    }

    private Object getJSONElementChecked(JSONObject jsonObj, String key)
    {
    	try{
	    	if( jsonObj.has(key) ){
	    		return jsonObj.get(key);
	    	}
    	}
	    catch (JSONException e)
	    {
	    	e.printStackTrace();
	    }
    	return null;
    }
    @SuppressWarnings("unchecked")    
    public DiggSearchStoryResults getStoriesList(
            List<ServiceParameter> parameters) throws Exception {
        
        DiggSearchStoryResults results = new DiggSearchStoryResults();
        
        StringBuilder builder = new StringBuilder();    
                

        /* Prepare search story parameters */
        parameters = prepareSearchStoryQueryParameters(parameters);
        
        /* Safely prepare the search URL and make connection */
       
        URL diggSearchUrl 	= new URL(DIGG_SEARCH_SERVICE_URL_V2 
        				+ DIGG_SEARCH_STORY_METHOD_V2  /* digg Apis v.2.0 search method  */ 
        				+ "?" + ServiceParametersUtility.toParametersString(parameters));
        URLConnection	connection    = diggSearchUrl.openConnection();
        
        //System.out.println("diggSearchUrl = "+ diggSearchUrl.toString());
        //connection.addRequestProperty(REFERER, REFERER_VALUE);
        String line;
        BufferedReader	reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            builder.append(line).append('\n');
        }                
       
        
        JSONObject rootResults    = new JSONObject(builder.toString());  
        // Check for invalid result or returned an error
        if (! rootResults.has(DIGG_STORIES_ELEMENT) ) {
            throw new InvalidResultException(
                    "Error in Results: The stories root element is not available ");
        }               
        JSONArray  storiesElement = rootResults.getJSONArray(DIGG_STORIES_ELEMENT); 

        /*  Parse the Results as long there is no returned Error code */

        // Start parsing for general results information
        results.setCount(rootResults.getString(STORIES_COUNT_ATTRIBUTE));        
        results.setTimestamp(rootResults.getString(STORIES_TIME_ATTRIBUTE));
        results.setTotal(rootResults.getString(STORIES_TOTAL_ATTRIBUTE));
        
        //@Deprecated in JSON v2.0 
        if(rootResults.has(STORIES_OFFSET_ATTRIBUTE))
        	results.setOffset(rootResults.getString(STORIES_OFFSET_ATTRIBUTE));        
        if(rootResults.has(STORIES_VERSION_ATTRIBUTE))
        	results.setVersion(rootResults.getString(STORIES_VERSION_ATTRIBUTE));
        
        
        //System.out.println("results Count = "+results.getCount());
        //System.out.println("results Total = "+results.getTotal());
        // Start collecting the list of results
        List<DiggSearchStoryResultItem> searchResultList = new ArrayList<DiggSearchStoryResultItem>();

        // Add each story
        for (int i = 0; i < storiesElement.length(); ++i) {

        	JSONObject keyElement = storiesElement.getJSONObject(i);
            // Cursor element
            JSONObject cur = null;
            DiggSearchStoryResultItem resultItem = new DiggSearchStoryResultItem();

            // Story Element
            resultItem.setStoryComments(keyElement.getString(STORY_COMMENTS_ATTRIBUTE));
            resultItem.setDescription(keyElement.getString(DIGG_DESCRIPTION_ELEMENT));            
            resultItem.setStoryDiggs(keyElement.getString(STORY_DIGGS_ATTRIBUTE));
            resultItem.setStoryHref(keyElement.getString(STORY_HREF_ATTRIBUTE));
            resultItem.setStoryId(keyElement.getString(STORY_ID_ATTRIBUTE));
            resultItem.setStoryLink(keyElement.getString(STORY_LINK_ATTRIBUTE));
            resultItem.setStoryMedia(keyElement.getString(STORY_MEDIA_ATTRIBUTE));
            resultItem.setStoryStatus(keyElement.getString(STORY_STATUS_ATTRIBUTE));
            resultItem.setStorySubmitDate(keyElement.getString(STORY_SUBMITDATE_ATTRIBUTE));
            resultItem.setTitle(keyElement.getString(DIGG_TITLE_ELEMENT));
           
            // User Info
            cur = (JSONObject)getJSONElementChecked(keyElement,DIGG_USER_ELEMENT);
            if(cur!=null){
            	resultItem.setUserIcon(cur.getString(USER_ICON_ATTRIBUTE));
            	resultItem.setUserName(cur.getString(USER_NAME_ATTRIBUTE));
            	resultItem.setUserProfileviews(cur.getString(USER_PROFILEVIEWS_ATTRIBUTE));
            	resultItem.setUserRegistered(cur.getString(USER_REGISTERED_ATTRIBUTE));
            	/* @since digg Apis v2.0*/
            	resultItem.setUserFullName(cur.getString(USER_FULLNAME_ATTRIBUTE));
            	// TODO The links can be commented because it is irrelevant to the Stories data.
            	if(cur.getJSONArray(USER_LINKS_ATTRIBUTE)!=null)
            		resultItem.setUserLinks(cur.getJSONArray(USER_LINKS_ATTRIBUTE).toString());

            }

            // Topic
            cur = (JSONObject)getJSONElementChecked(keyElement,DIGG_TOPIC_ELEMENT);            
            if(cur!=null){
            	resultItem.setTopicName(cur.getString(TOPIC_NAME_ATTRIBUTE));
            	resultItem.setTopicShortName(cur.getString(TOPIC_SHORTNAME_ATTRIBUTE));
            }

            // Container
            cur = (JSONObject)getJSONElementChecked(keyElement,DIGG_CONTAINER_ELEMENT);
            if(cur!=null){
            	resultItem.setContainerName(cur.getString(CONTAINER_NAME_ATTRIBUTE));
            	resultItem.setContainerShortName(cur.getString(CONTAINER_SHORTNAME_ATTRIBUTE));
            }

            // Short_url
            cur = (JSONObject)getJSONElementChecked(keyElement,DIGG_SHORTURL_ELEMENT);
            if(cur!=null){
            	resultItem.setShorturl(cur.getString(SHORTURL_URL_ATTRIBUTE));
            	resultItem.setShorturlViewCount(cur.getString(SHORTURL_VIEW_COUNT_ATTRIBUTE));
            }
            
            /* @since digg Apis v2.0*/
            // Thumbnail 
            cur = keyElement.getJSONObject(DIGG_THUMBNAIL_ELEMENT);
            if(cur!=null){
            	resultItem.setThumbnailContentType(cur.getString(THUMBNAIL_CONTENT_TYPE_ATTRIBUTE));
            	resultItem.setThumbnailSrc(cur.getString(THUMBNAIL_SRC_ATTRIBUTE));
            	resultItem.setThumbnailHeight(cur.getString(THUMBNAIL_HEIGHT_ATTRIBUTE));
            	resultItem.setThumbnailWidth(cur.getString(THUMBNAIL_WIDTH_ATTRIBUTE));
            	resultItem.setThumbnailOriginalHeight(cur.getString(THUMBNAIL_ORIGINAL_HEIGHT_ATTRIBUTE));
            	resultItem.setThumbnailOriginalWidth(cur.getString(THUMBNAIL_ORIGINAL_WIDTH_ATTRIBUTE));
            }

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

        /* Required API parameters: Implicit fixed value parameters to the search API */
        //supportedParameters.add(new ServiceParameter(
        //        DiggSearchServiceParameters.SEARCH_ARGS_METHOD, DiggSearchService.DIGG_SEARCH_STORY_METHOD));
        //supportedParameters.add(new ServiceParameter(
        //        DiggSearchServiceParameters.SEARCH_ARGS_TYPE,DiggSearchService.DIGG_SEARCH_STORY_TYPE));

        /* Optional API parameters: filters the supported argument from the submitted parameters */ 
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
            /* @since digg Apis v2.0*/
            else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_TOPIC)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            }
            else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_MEDIA)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            }
            else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_MAX_DATE)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            }
            else if (param.getParameterName().equals(
                    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_MIN_DATE)) {
                if (!supportedParameters.contains(param))
                    supportedParameters.add(param);
            }
        }

        return supportedParameters;
    }

    private DiggSearchService2Impl() {
    }

    private static DiggSearchService diggSearchService = new DiggSearchService2Impl();
}
