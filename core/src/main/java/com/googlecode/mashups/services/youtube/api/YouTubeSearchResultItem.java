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
package com.googlecode.mashups.services.youtube.api;


public class YouTubeSearchResultItem {
    private String title;
    private String link;
    private String author;
    private String videoID;    
    private String pubDate;
    private String previewURL;
    
    /**
     * 
     */
    public YouTubeSearchResultItem() {
        super();
    }

    /**
     * @param title
     * @param link
     * @param author
     * @param videoID
     * @param pubDate
     */
    public YouTubeSearchResultItem(String title, String link, String author, String videoID, String pubDate) {
        super();
        this.title   = title;
        this.link    = link;
        this.author  = author;
        this.videoID = videoID;
        this.pubDate = pubDate;
    }
    
    /**
     * @param title
     * @param link
     * @param author
     * @param videoID
     * @param pubDate
     * @param previewURL
     */
    public YouTubeSearchResultItem(String title, String link, String author, String videoID, String pubDate, String previewURL) {
        super();
        this.title      = title;
        this.link       = link;
        this.author     = author;
        this.videoID    = videoID;
        this.pubDate    = pubDate;
        this.previewURL = previewURL;
    }    
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
  
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }
    
    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * @return the videoID
     */
    public String getVideoID() {
        return videoID;
    }
    
    /**
     * @param videoID the videoID to set
     */
    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }
    
    /**
     * @return the pubDate
     */
    public String getPubDate() {
        return pubDate;
    }
    
    /**
     * @param pubDate the pubDate to set
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    
    /**
     * @return the previewURL
     */
    public String getPreviewURL() {
        return previewURL;
    }

    /**
     * @param previewURL the previewURL to set
     */
    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String toString () {
        return "title: '" + title + "', link: '" + link + "', author: '" + author + "', videoID: '" + videoID + "', pubDate: '" + pubDate + "', previewURL: '" + previewURL +  "'";
    }
}