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
package com.googlecode.mashups4jsf.component.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.TwitterServicesFactory;
import com.googlecode.mashups.services.twitter.api.TwitterSearchResultItem;
import com.googlecode.mashups.services.twitter.api.TwitterSearchService;
import com.googlecode.mashups.services.twitter.api.TwitterSearchServiceParameters;
import com.googlecode.mashups4jsf.common.util.ComponentConstants;

/**
 * @author Hazem Saleh
 * @date June. 04, 2010
 * The (TwitterSearchListRenderer) renders a Twitter search result list.
 */
public class TwitterSearchListRenderer extends Renderer {
    private static final String A_ONE_ITEM_FACET_MUST_BE_DEFINED = "A one item facet must be defined";
    private static final String TWITTER_TWEET_RESULT_ITEM = "resultItem";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {  
            try {
                ResponseWriter  writer         = context.getResponseWriter();               
                TwitterSearchList twitterSearchList = (TwitterSearchList) component;
                
                // start encoding the component markup.
                writer.startElement  (ComponentConstants.DIV_ELEMENT, twitterSearchList);
                writer.writeAttribute(ComponentConstants.ID_ATTRIBUTE, twitterSearchList.getClientId(context),
                                      ComponentConstants.ID_ATTRIBUTE);            
                
                // get the component attributes.
                List<ServiceParameter> parameters           = new ArrayList<ServiceParameter>();     
                TwitterSearchService   twitterSearchService = TwitterServicesFactory.getTwitterSearchService();
                 
                parameters.add(new ServiceParameter(TwitterSearchServiceParameters.QUERY, twitterSearchList.getSearchQuery()));
                parameters.add(new ServiceParameter(TwitterSearchServiceParameters.PAGE, twitterSearchList.getPageNumber().toString()));
                parameters.add(new ServiceParameter(TwitterSearchServiceParameters.TWEETS_PER_PAGE, twitterSearchList.getResultSetSize().toString()));                
                parameters.add(new ServiceParameter(TwitterSearchServiceParameters.SHOW_USER, twitterSearchList.getShowUser()));               
                
                if (twitterSearchList.getResultType() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.RESULT_TYPE, twitterSearchList.getResultType()));
                }
                
                if (twitterSearchList.getSinceDate() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.SINCE_DATE, twitterSearchList.getSinceDate()));                    
                }
                
                if (twitterSearchList.getBeforeDate() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.BEFORE_DATE, twitterSearchList.getBeforeDate()));                    
                }   
                
                if (twitterSearchList.getSinceID() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.SINCE_ID, twitterSearchList.getSinceID()));                    
                }   
                
                if (twitterSearchList.getMaxID() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.MAX_ID, twitterSearchList.getMaxID()));                    
                }    
                
                if (twitterSearchList.getGeocode() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.GEOCODE, twitterSearchList.getGeocode()));                    
                }     
                
                if (twitterSearchList.getLocale() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.LOCALE, twitterSearchList.getLocale()));                    
                }
                
                if (twitterSearchList.getLanguage() != null) {
                    parameters.add(new ServiceParameter(TwitterSearchServiceParameters.LANGUAGE, twitterSearchList.getLanguage()));                    
                }                  
                
                // perform the actual tweets search on twitter.
                List<TwitterSearchResultItem> twitterSearchResults = twitterSearchService.getSearchList(parameters);
    
                // encode results .
                UIComponent itemFacet = twitterSearchList.getFacet(TWITTER_TWEET_RESULT_ITEM);
                
                if (itemFacet == null) {
                    throw new IOException(A_ONE_ITEM_FACET_MUST_BE_DEFINED);
                }
                
                Integer index = 0;
                
                for (TwitterSearchResultItem twitterSearchResultItem : twitterSearchResults) {
                    context.getApplication().createValueBinding("#{" + twitterSearchList.getResultItemVar() + "}").setValue(context, twitterSearchResultItem);
                    context.getApplication().createValueBinding("#{" + twitterSearchList.getResultItemIndex() + "}").setValue(context, index++);        
                    encodeRecursive(context, itemFacet);               
                }
    
            } catch (Exception exception) {
                throw new IOException("The following exception occurs: " + exception.getMessage());
            }
        }
    }
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            ResponseWriter writer = context.getResponseWriter();               
            
            // end encoding the component markup.
            writer.endElement(ComponentConstants.DIV_ELEMENT);
        }
    }
        
    private void encodeRecursive(FacesContext context, UIComponent component) throws IOException {
        component.encodeBegin(context);
        
        if (component.getRendersChildren()) {
            component.encodeChildren(context);
        } else {
            for (UIComponent child : (List<UIComponent>) component.getChildren()) {
                encodeRecursive(context, child);
            }
        }
        component.encodeEnd(context);
    }     
}