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
package com.googlecode.mashups4jsf.component.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.YouTubeServicesFactory;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchResultItem;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchService;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchServiceParameters;
import com.googlecode.mashups4jsf.common.util.ComponentConstants;

/**
 * @author Hazem Saleh
 * @date March. 05, 2010
 * The (YouTubeVideoListRenderer) renders youTube video list.
 */
public class YouTubeVideoListRenderer extends Renderer {
    private static final String A_ONE_ITEM_FACET_MUST_BE_DEFINED = "A one item facet must be defined";
    private static final String YOUTUBE_RESULT_ITEM = "resultItem";
    private static final String MAXIMUM_COUNT_SHOULD_BE_GREATER_THAN_ZERO = "Maximum results should be greater than zero!!!";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {            
            try {
                ResponseWriter   writer           = context.getResponseWriter();               
                YouTubeVideoList youTubeVideoList = (YouTubeVideoList) component;
                
                if (youTubeVideoList.getMaxResults() <= 0) {
                    throw new IOException(MAXIMUM_COUNT_SHOULD_BE_GREATER_THAN_ZERO);
                }
                
                // start encoding the component markup.
                writer.startElement  (ComponentConstants.DIV_ELEMENT, youTubeVideoList);
                writer.writeAttribute(ComponentConstants.ID_ATTRIBUTE, youTubeVideoList.getClientId(context),
                                      ComponentConstants.ID_ATTRIBUTE);            
                
                // get the component attributes.
                List<ServiceParameter> parameters           = new ArrayList<ServiceParameter>();     
                YouTubeSearchService   youTubeSearchService = YouTubeServicesFactory.getYouTubeSearchService();
               
                parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.VERSION, "2"));      
                parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.MAX_RESULTS, youTubeVideoList.getMaxResults().toString()));
                parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.QUERY, youTubeVideoList.getSearchQuery()));
                
                if (youTubeVideoList.getCategory() != null) {
                    parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.CATEGORY, youTubeVideoList.getCategory()));
                }
                
                if (youTubeVideoList.getLocation() != null) {
                    parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.LOCATION, youTubeVideoList.getLocation()));
                }
                
                if (youTubeVideoList.getLocationRadius() != null) {
                    parameters.add(new ServiceParameter(YouTubeSearchServiceParameters.LOCATION_RADIUS, youTubeVideoList.getLocationRadius()));
                }
                
                // perform the actual video search on youTube.
                List<YouTubeSearchResultItem> videoResults = youTubeSearchService.getVideoList(parameters);            
    
                // encode results .
                UIComponent itemFacet = youTubeVideoList.getFacet(YOUTUBE_RESULT_ITEM);
                
                if (itemFacet == null) {
                    throw new IOException(A_ONE_ITEM_FACET_MUST_BE_DEFINED);
                }
                
                Integer index = 0;
                
                for (YouTubeSearchResultItem youTubeSearchResultItem : videoResults) {
                    context.getApplication().createValueBinding("#{" + youTubeVideoList.getResultItemVar() + "}").setValue(context, youTubeSearchResultItem);
                    context.getApplication().createValueBinding("#{" + youTubeVideoList.getResultItemIndex() + "}").setValue(context, index++);        
                    encodeRecursive(context, itemFacet);
                    
                    if (index >= youTubeVideoList.getMaxResults()) {
                        break;
                    }                
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