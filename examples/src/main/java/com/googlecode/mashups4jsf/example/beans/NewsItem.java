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


/**
 * @author hazems
 *
 */
//@FeedItem (Optional)
public class NewsItem {
    String title;
    String desc;
    String link;
    String category;
    String author;

    //@ItemTitle (Optional) 
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //@ItemDescription (Optional)
    public String getDescription() {
        return desc;
    }
    public void setDescription(String desc) {
        this.desc = desc;
    }

    //@ItemLink (Optional)
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    
    //@ItemCategory (Optional)
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    //@ItemAuthor (Optional)
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * @param title
     * @param desc
     * @param link
     * @param category
     * @param author
     */
    public NewsItem(String title, String desc, String link, String category, String author) {
		super();
		this.title = title;
		this.desc = desc;
		this.link = link;
		this.category = category;
		this.author = author;
    }
}