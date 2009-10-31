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
package com.googlecode.mashups.services.yahoo.impl;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherService;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherServiceStatus;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

public class YahooWeatherServiceImpl implements YahooWeatherService {
    
    public YahooWeatherServiceStatus getWeatherStatus(List<ServiceParameter> parameters) throws Exception {
        URL                  feedUrl               = new URL(YAHOO_WEATHER_SERVICE_URL + "?p=" + "12"
                                                  + "&u=" + "22");
        SyndFeedInput        input                = new SyndFeedInput();
        SyndFeed             feed                 = input.build(new InputStreamReader(feedUrl.openStream()));    
        YahooWeatherServiceStatus weatherServiceStatus = new YahooWeatherServiceStatus();
        
        if (feed.getEntries() != null && feed.getEntries().size() > 0) {
            SyndEntry entry = ((SyndEntry) feed.getEntries().get(0));
            
            String title = entry.getTitle();            
            String description = entry.getDescription().getValue();
            
            weatherServiceStatus.setTitle(title);
            weatherServiceStatus.setDescription(description);
        }         
        
        return weatherServiceStatus;
    }
}
