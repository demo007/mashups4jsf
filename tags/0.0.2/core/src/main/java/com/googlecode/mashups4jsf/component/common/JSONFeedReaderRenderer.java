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
package com.googlecode.mashups4jsf.component.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.mashups.services.factory.GenericServicesFactory;

/**
 * @author Hazem Saleh
 * @date August. 06, 2010
 * The <code>JSONFeedReaderRenderer</code> is a generic JSON feed reader.
 */
public class JSONFeedReaderRenderer extends Renderer {
    private static final String A_ONE_ITEM_FACET_MUST_BE_DEFINED = "A one item facet must be defined";
    private static final String ITEM = "item";
    private static final String MAXIMUM_COUNT_SHOULD_BE_GREATER_THAN_ZERO = "Maximum count should be greater than zero!!!";

    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        try {
            JSONFeedReader jsonFeedReader = (JSONFeedReader) component;
            
            if (jsonFeedReader.getMaximumCount() <= 0) {
                throw new IOException(MAXIMUM_COUNT_SHOULD_BE_GREATER_THAN_ZERO);
            }
            
            JSONArray resultArray = GenericServicesFactory.getFeedReaderService().readJSONFeed(jsonFeedReader.getFeedURL(), 
                                                                                               jsonFeedReader.getFeedArrayName());
            
            UIComponent itemFacet = jsonFeedReader.getFacet(ITEM);
            
            if (itemFacet == null) {
                throw new IOException(A_ONE_ITEM_FACET_MUST_BE_DEFINED);
            }
            
            Integer index = 0;
            
            for (int i = 0; i < resultArray.length(); ++i) {
                
                JSONObject          jsonObject = (JSONObject) resultArray.get(i);
                Map<String, Object> jsonMap    = convertJSONObjectToMap(jsonObject);
                
                context.getApplication().createValueBinding("#{" + jsonFeedReader.getItemVar() + "}").setValue(context, jsonMap);
                context.getApplication().createValueBinding("#{" + jsonFeedReader.getItemIndex() + "}").setValue(context, index++);        
                encodeRecursive(context, itemFacet);
                
                if (index >= jsonFeedReader.getMaximumCount()) {
                    break;
                }                
            }

        } catch (Exception exception) {
            throw new IOException("The following exception occurs: " + exception.getMessage());
        }
    }
    
    private Map<String, Object> convertJSONObjectToMap(JSONObject jsonObject) throws Exception {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        
        String[] names = JSONObject.getNames(jsonObject);
        
        for (String attributeName : names) {
            //System.out.println("Putting Map Item content: " + attributeName);
            jsonMap.put(attributeName, jsonObject.get(attributeName));
        }
        
        return jsonMap;
    }

    @SuppressWarnings("unchecked")
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