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
package com.googlecode.mashups4jsf.example.beans;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.mashups.services.generic.api.Feed;
import com.googlecode.mashups.services.generic.api.FeedDescription;
import com.googlecode.mashups.services.generic.api.FeedItems;
import com.googlecode.mashups.services.generic.api.FeedLink;
import com.googlecode.mashups.services.generic.api.FeedTitle;


/**
 * @author hazems
 *
 */

@Feed
public class News {
    static List <NewsItem> news = new ArrayList<NewsItem>();
    
    static {
	
	// Generate random data (It can be got from the database).
	for (int i = 0; i < 10; ++i) {
	    news.add(new NewsItem("News Title" + i, "News Desc" + i, "http://www.google.com/search?q=" + i, "News Category" + i, "News Author" + i));
	}
    }
    
    @FeedItems
    public List getNews() {
	return news;
    }
    
    @FeedTitle
    public String getTitle() {
	return "News Title";
    }
    
    @FeedDescription
    public String getDescription() {
	return "News Description";
    }
    
    @FeedLink
    public String getLink() {
	return "http://www.google.com";
    }
}
