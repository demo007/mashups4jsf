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
package com.googlecode.mashups4jsf.component.google;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.GoogleServicesFactory;
import com.googlecode.mashups.services.factory.YouTubeServicesFactory;
import com.googlecode.mashups.services.google.api.GoogleSearchResultItem;
import com.googlecode.mashups.services.google.api.GoogleSearchService;
import com.googlecode.mashups.services.google.api.GoogleSearchServiceParameters;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchResultItem;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchService;
import com.googlecode.mashups.services.youtube.api.YouTubeSearchServiceParameters;
import com.googlecode.mashups4jsf.common.util.ComponentConstants;

/**
 * @author Hazem Saleh
 * @date March. 06, 2010
 * The (GoogleSearchListRenderer) renders Google search result list.
 */
public class GoogleSearchListRenderer extends Renderer {
    private static final String A_ONE_ITEM_FACET_MUST_BE_DEFINED = "A one item facet must be defined";
    private static final String GOOGLE_RESULT_ITEM = "resultItem";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {            
            try {
                ResponseWriter   writer           = context.getResponseWriter();               
                GoogleSearchList googleSearchList = (GoogleSearchList) component;
                
                // start encoding the component markup.
                writer.startElement  (ComponentConstants.DIV_ELEMENT, googleSearchList);
                writer.writeAttribute(ComponentConstants.ID_ATTRIBUTE, googleSearchList.getClientId(context),
                                      ComponentConstants.ID_ATTRIBUTE);            
                
                // get the component attributes.
                List<ServiceParameter> parameters          = new ArrayList<ServiceParameter>();     
                GoogleSearchService    googleSearchService = GoogleServicesFactory.getGoogleSearchService();
               
                parameters.add(new ServiceParameter(GoogleSearchServiceParameters.VERSION, "1.0"));      
                parameters.add(new ServiceParameter(GoogleSearchServiceParameters.QUERY, googleSearchList.getSearchQuery()));
                
                parameters.add(new ServiceParameter(GoogleSearchServiceParameters.START, googleSearchList.getStartResultIndex().toString()));
                parameters.add(new ServiceParameter(GoogleSearchServiceParameters.RSZ, googleSearchList.getResultSetSize()));
                parameters.add(new ServiceParameter(GoogleSearchServiceParameters.HL, googleSearchList.getHostLanguage()));                
                
                if (googleSearchList.getKey() != null) {
                    parameters.add(new ServiceParameter(GoogleSearchServiceParameters.KEY, googleSearchList.getKey()));
                }
                
                // perform the actual video search on youTube.
                List<GoogleSearchResultItem> googleSearchResults = googleSearchService.getWebSearchResultList(parameters);
    
                // encode results .
                UIComponent itemFacet = googleSearchList.getFacet(GOOGLE_RESULT_ITEM);
                
                if (itemFacet == null) {
                    throw new IOException(A_ONE_ITEM_FACET_MUST_BE_DEFINED);
                }
                
                Integer index = 0;
                
                for (GoogleSearchResultItem googleSearchResultItem : googleSearchResults) {
                    context.getApplication().createValueBinding("#{" + googleSearchList.getResultItemVar() + "}").setValue(context, googleSearchResultItem);
                    context.getApplication().createValueBinding("#{" + googleSearchList.getResultItemIndex() + "}").setValue(context, index++);        
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