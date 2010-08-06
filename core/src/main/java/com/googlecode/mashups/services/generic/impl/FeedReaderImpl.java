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
package com.googlecode.mashups.services.generic.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.mashups.services.generic.api.FeedReader;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

/**
 * <code>FeedReaderImpl</code> is the implementation of the <code>FeedReader</code> which 
 * is responsible for reading Mashup feeds (ATOM, RSS or JSON).
 * @author hazems
 *
 */
public class FeedReaderImpl implements FeedReader {
    private static final String NONE = "none";

    public static FeedReader getInstance() {
        return feedReader;
    }

    private FeedReaderImpl() {
    }

    @SuppressWarnings("unchecked")
    public SyndFeed readATOMFeed(String feedURL) throws Exception {
        URL            feedUrl = new URL(feedURL);
        SyndFeedInput  input   = new SyndFeedInput();
        SyndFeed       feed    = input.build(new InputStreamReader(feedUrl.openStream()));
        
        return feed;
    }

    @SuppressWarnings("unchecked")
    public JSONArray readJSONFeed(String feedURL, String jsonArrayName) throws Exception {
        URL               feedUrl      = new URL(feedURL);        
        InputStreamReader streamReader = new InputStreamReader(feedUrl.openStream());        
        StringBuilder     content      = new StringBuilder();
        BufferedReader    reader       = new BufferedReader(streamReader);
        String            line         = "";
        
        while((line = reader.readLine()) != null) {
            content.append(line);
        }
                
        return getJSONArray(jsonArrayName, content);
    }

    @SuppressWarnings("unchecked")
    public SyndFeed readRSSFeed(String feedURL) throws Exception {
        URL            feedUrl = new URL(feedURL);
        SyndFeedInput  input   = new SyndFeedInput();
        SyndFeed       feed    = input.build(new InputStreamReader(feedUrl.openStream()));
        
        return feed;
    }
    
    private static JSONArray getJSONArray(String arrayName, StringBuilder content) throws Exception {
        if (arrayName.equalsIgnoreCase(NONE)) {
            return new JSONArray(content.toString());
        }
        
        String searchFor = "\"" + arrayName + "\":[";        
        
        int arrayStartIndex = content.indexOf(searchFor);
        int arrayEndIndex = 0;
        
        if (arrayStartIndex <= -1) {
            return new JSONArray();
        }
        
        // TODO optimize.
        int balance = 0;
        
        for (int i = (arrayStartIndex + searchFor.length()) ; i < content.length(); ++i) {
            char streamChar = content.charAt(i);
            
            if (streamChar == '[') {
                ++balance;
            } else if (streamChar == ']') {
                if (balance == 0) {
                    arrayEndIndex = i + 1;
                    break;
                }
                
                --balance;
            }
        }
        
        String arrayContent = "{" + content.substring(arrayStartIndex, arrayEndIndex) + "}";      
        
        // create the JSON array.
        JSONObject json = new JSONObject(arrayContent);
            
        return json.getJSONArray(arrayName);
    }    
    
    private static FeedReader feedReader = new FeedReaderImpl();    
}
