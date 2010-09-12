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
package com.googlecode.mashups4jsf.component.digg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.digg.api.DiggSearchService;
import com.googlecode.mashups.services.digg.api.DiggSearchServiceParameters;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResultItem;
import com.googlecode.mashups.services.factory.DiggServicesFactory;
import com.googlecode.mashups4jsf.common.util.ComponentConstants;

/**
 * @author Hazem Saleh
 * @date March. 19, 2010
 * The (DiggSearchListRenderer) renders a Digg search result list.
 */
public class DiggSearchListRenderer extends Renderer {
    private static final String A_ONE_ITEM_FACET_MUST_BE_DEFINED = "A one item facet must be defined";
    private static final String DIGG_STORIES_RESULT_ITEM = "resultItem";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {            
            try {
                ResponseWriter  writer         = context.getResponseWriter();               
                DiggSearchList diggSearchList = (DiggSearchList) component;
                
                // start encoding the component markup.
                writer.startElement  (ComponentConstants.DIV_ELEMENT, diggSearchList);
                writer.writeAttribute(ComponentConstants.ID_ATTRIBUTE, diggSearchList.getClientId(context),
                                      ComponentConstants.ID_ATTRIBUTE);            
                
                // get the component attributes.
                List<ServiceParameter> parameters        = new ArrayList<ServiceParameter>();     
                DiggSearchService      diggSearchService = DiggServicesFactory.getDiggSearchService();
                 
                parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_QUERY, diggSearchList.getSearchQuery()));
                parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_OFFSET, diggSearchList.getStartResultIndex().toString()));
                parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_COUNT, diggSearchList.getResultSetSize().toString()));                
               
                
                if (diggSearchList.getDomain() != null) {
                    parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_DOMAIN, diggSearchList.getDomain()));
                }
                
                if (diggSearchList.getSortBy() != null) {
                    parameters.add(new ServiceParameter(DiggSearchServiceParameters.SEARCH_ARGS_SORT, diggSearchList.getSortBy()));                    
                }
                
                // perform the actual stories search on digg.
                List<DiggSearchStoryResultItem> diggSearchResults = diggSearchService.getStoriesList(parameters).getSearchResultList();
    
                // encode results .
                UIComponent itemFacet = diggSearchList.getFacet(DIGG_STORIES_RESULT_ITEM);
                
                if (itemFacet == null) {
                    throw new IOException(A_ONE_ITEM_FACET_MUST_BE_DEFINED);
                }
                
                Integer index = 0;
                
                for (DiggSearchStoryResultItem diggSearchStoryResultItem : diggSearchResults) {
                    context.getApplication().createValueBinding("#{" + diggSearchList.getResultItemVar() + "}").setValue(context, diggSearchStoryResultItem);
                    context.getApplication().createValueBinding("#{" + diggSearchList.getResultItemIndex() + "}").setValue(context, index++);        
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