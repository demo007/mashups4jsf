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

import org.jdom.Attribute;
import org.jdom.Element;

import com.googlecode.mashups.services.factory.GenericServicesFactory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * @author Hazem Saleh
 * @date Feb. 20, 2010
 * The <code>RssFeedReaderRenderer</code> is a generic RSS feed reader.
 */
public class RssFeedReaderRenderer extends Renderer {
    private static final String A_ONE_ITEM_FACET_MUST_BE_DEFINED = "A one item facet must be defined";
    private static final String ITEM = "item";
    private static final String CHANNEL = "channel";
    private static final String MAXIMUM_COUNT_SHOULD_BE_GREATER_THAN_ZERO = "Maximum count should be greater than zero!!!";

    @SuppressWarnings("unchecked")
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        try {
            RssFeedReader rssFeedReader = (RssFeedReader) component;
            
            if (rssFeedReader.getMaximumCount() <= 0) {
                throw new IOException(MAXIMUM_COUNT_SHOULD_BE_GREATER_THAN_ZERO);
            }
                        
            SyndFeed feed = GenericServicesFactory.getFeedReaderService().readRSSFeed(rssFeedReader.getFeedURL());
            
            context.getApplication().createValueBinding("#{" + rssFeedReader.getChannelVar() + "}").setValue(context, feed);

            UIComponent channelFacet = rssFeedReader.getFacet(CHANNEL);
            
            if (channelFacet != null) {
                encodeRecursive(context, channelFacet);
            }

            UIComponent itemFacet = rssFeedReader.getFacet(ITEM);
            
            if (itemFacet == null) {
                throw new IOException(A_ONE_ITEM_FACET_MUST_BE_DEFINED);
            }
            
            Integer index = 0;
            
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                context.getApplication().createValueBinding("#{" + rssFeedReader.getItemVar() + "}").setValue(context, entry);
                context.getApplication().createValueBinding("#{" + rssFeedReader.getItemIndex() + "}").setValue(context, index++);    
                
                if (rssFeedReader.getIncludeCustomModules().booleanValue()) {
                	
	                //Parse (RSS / ATOM) custom modules ...
	            	List<Element> foreignMarkupList = (List<Element>) entry.getForeignMarkup();
	            	Map <String, Map> customModulesMap = new HashMap<String, Map>();
	            	
	                for (Element outerForeignMarkup : foreignMarkupList) {
	                	
	                	String prefix = outerForeignMarkup.getNamespacePrefix();
	                	Map <String, Map> moduleElementsMap = new HashMap<String, Map>(); 
	                	
		                for (Element foreignMarkup : foreignMarkupList) {
		                	    
		                    if (prefix.equals(foreignMarkup.getNamespacePrefix())) {
			                	Map <String, String> elementAttributesMap = new HashMap<String, String>(); 
			                    List<Attribute> attributes = (List <Attribute>) foreignMarkup.getAttributes();  
			                    
			                	for (Attribute attribute : attributes) {
			                        elementAttributesMap.put(attribute.getName(), attribute.getValue());
			                    }
			                    
			                    elementAttributesMap.put("value", foreignMarkup.getValue());
			                    
			                    //ElementsMap Item ==> Element --> Element Attributes ...
			                	moduleElementsMap.put(foreignMarkup.getName(), elementAttributesMap);
		                    }
		                }
		                
	                    customModulesMap.put(prefix, moduleElementsMap); 
	                    
	                    context.getApplication().createValueBinding("#{" + prefix + "}").setValue(context, moduleElementsMap);
	                }
                }
                
                encodeRecursive(context, itemFacet);
                
                if (index >= rssFeedReader.getMaximumCount()) {
                    break;
                }                
            }

        } catch (Exception exception) {
            throw new IOException("The following exception occurs: " + exception.getMessage());
        }
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
