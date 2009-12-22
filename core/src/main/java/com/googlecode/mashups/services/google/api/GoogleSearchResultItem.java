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
package com.googlecode.mashups.services.google.api;

public class GoogleSearchResultItem {
    private String title;
    private String titleNotFormatting; 
    private String content;
    private String GsearchResultClass;
    private String cacheUrl;
    private String unescapedUrl;    
    private String url;
    private String visibleUrl;
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
     * @return the titleNotFormatting
     */
    public String getTitleNotFormatting() {
        return titleNotFormatting;
    }
    /**
     * @param titleNotFormatting the titleNotFormatting to set
     */
    public void setTitleNotFormatting(String titleNotFormatting) {
        this.titleNotFormatting = titleNotFormatting;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return the gsearchResultClass
     */
    public String getGsearchResultClass() {
        return GsearchResultClass;
    }
    /**
     * @param gsearchResultClass the gsearchResultClass to set
     */
    public void setGsearchResultClass(String gsearchResultClass) {
        GsearchResultClass = gsearchResultClass;
    }
    /**
     * @return the cacheUrl
     */
    public String getCacheUrl() {
        return cacheUrl;
    }
    /**
     * @param cacheUrl the cacheUrl to set
     */
    public void setCacheUrl(String cacheUrl) {
        this.cacheUrl = cacheUrl;
    }
    /**
     * @return the unescapedUrl
     */
    public String getUnescapedUrl() {
        return unescapedUrl;
    }
    /**
     * @param unescapedUrl the unescapedUrl to set
     */
    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the visibleUrl
     */
    public String getVisibleUrl() {
        return visibleUrl;
    }
    /**
     * @param visibleUrl the visibleUrl to set
     */
    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }   
    
    public String toString() {
        return "title: " + title + "\n" +
               "titleNotFormatting: " + titleNotFormatting + "\n" + 
               "content: " + content + "\n" +
               "GsearchResultClass: " + GsearchResultClass + "\n" +
               "cacheUrl: " + cacheUrl + "\n" +
               "unescapedUrl: " + unescapedUrl + "\n" +    
               "url: " + url + "\n" +
               "visibleUrl: " + visibleUrl + "\n";
    }
    
    /*
    private String originalContextUrl;
    private String width;
    private String height;
    private String tbWidth;
    private String tbHeight;
    private String tbUrl;     
    private String imageId;
    private String contentNoFormatting;
    private String videoType;
    private String publisher;
    private String playUrl;
    private String duration;
    private String rating;
    private String published; // The video published date.
    */  
}