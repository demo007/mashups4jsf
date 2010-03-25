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

import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.mashups.services.generic.api.FeedDescription;
import com.googlecode.mashups.services.generic.api.FeedItems;
import com.googlecode.mashups.services.generic.api.FeedLink;
import com.googlecode.mashups.services.generic.api.FeedTitle;
import com.googlecode.mashups.services.generic.api.ItemAuthor;
import com.googlecode.mashups.services.generic.api.ItemCategory;
import com.googlecode.mashups.services.generic.api.ItemDescription;
import com.googlecode.mashups.services.generic.api.ItemLink;
import com.googlecode.mashups.services.generic.api.ItemTitle;
import com.googlecode.mashups.services.generic.api.Feed;
import com.googlecode.mashups.services.generic.api.FeedProducer;
import com.googlecode.mashups.services.generic.api.FeedItem;
import com.googlecode.mashups.services.generic.api.Feed.FeedType;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * @author hazems
 *
 */
public class FeedProducerImpl implements FeedProducer {
    public static FeedProducer getInstance() {
        return feedProducer;
    }
    
    @SuppressWarnings("unchecked")
    public void produceFeed(Object annotatedObject, Writer writer) throws Exception {
        Class  feedClass      = annotatedObject.getClass();
        String title          = "";
        String description    = "";
        String link           = "";
        List   feedItems      = new ArrayList();
        Feed   feedAnnotation = (Feed) feedClass.getAnnotation(Feed.class);

        if (feedAnnotation != null) {
            for (Method method : feedClass.getMethods()) {
                if (method.getAnnotation(FeedTitle.class) != null) {
                    title = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(FeedDescription.class) != null) {
                    description = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(FeedLink.class) != null) {
                    link = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(FeedItems.class) != null) {
                    feedItems = (List) method.invoke(annotatedObject);
                }
            }
        } else {
            throw new Exception("Object must have the @Feed for producing an RSS Feed ...");
        }
        
        SyndFeed feed = new SyndFeedImpl();
        
        if (feedAnnotation.type().equals(FeedType.Rss)) {
            feed.setFeedType(RSS_2_0);
        } else {
            feed.setFeedType(ATOM_1_0);
        }
        
        feed.setTitle(title);
        feed.setDescription(description);
        feed.setLink(link);
        
        List<SyndEntry> entries = new ArrayList<SyndEntry>();

        for (Object feedItem : feedItems) {
            SyndEntry syndEntry = createFeedItem(feedItem);
            
            entries.add(syndEntry);
        }
        
        feed.setEntries(entries);
        
        // output the feed to the output stream
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, writer);
    }
    
    @SuppressWarnings("unchecked")    
    private SyndEntry createFeedItem(Object annotatedObject) throws Exception {
        SyndEntry syndEntry     = new SyndEntryImpl();
        Class     feedItemClass = annotatedObject.getClass();
        
        if (feedItemClass.getAnnotation(FeedItem.class) != null) {
            String title        = "";
            String description  = "";            
            String link         = "";
            String category     = "";         
            String author       = "";                     
            
            for (Method method : feedItemClass.getMethods()) {
                
                if (method.getAnnotation(ItemTitle.class) != null) {
                    title = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(ItemDescription.class) != null) {
                    description = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(ItemLink.class) != null) {
                    link = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(ItemCategory.class) != null) {
                    category = (String) method.invoke(annotatedObject);
                } else if (method.getAnnotation(ItemAuthor.class) != null) {
                    author = (String) method.invoke(annotatedObject);
                }
            }
            
            // category.
            List<SyndCategory> categories = new ArrayList<SyndCategory>();
            
            SyndCategory syndCategory = new SyndCategoryImpl();
            syndCategory.setName(category);
            categories.add(syndCategory);
            
            syndEntry.setCategories(categories);
            
            // description.
            SyndContent descriptionContent = new SyndContentImpl();
            
            descriptionContent.setType("text/html");
            descriptionContent.setValue(description);
            syndEntry.setDescription(descriptionContent);
            
            
            // title, link, author, categories.
            syndEntry.setTitle(title);
            syndEntry.setLink(link);
            syndEntry.setAuthor(author);
        }
        
        return syndEntry;
    }     

    private FeedProducerImpl() {
    }
    
    private static final String ATOM_1_0 = "atom_1.0";    
    private static final String RSS_2_0 = "rss_2.0";
    private static FeedProducer feedProducer = new FeedProducerImpl();    
}
