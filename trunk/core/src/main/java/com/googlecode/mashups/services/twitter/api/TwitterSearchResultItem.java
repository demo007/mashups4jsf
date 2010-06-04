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
package com.googlecode.mashups.services.twitter.api;

public class TwitterSearchResultItem {
    private String title;
    private String titleNotFormatted; 
    private String authorName;
    private String authorTwitterURL;
    private String authorImageURL;
    private String publishDate;    
    private String updateDate;
    
    /**
     * 
     */
    public TwitterSearchResultItem() {
    }

    /**
     * @param title
     * @param titleNotFormatting
     * @param authorName
     * @param authorTwitterURL
     * @param authorImageURL
     */
    public TwitterSearchResultItem(String title, String titleNotFormatted,
	    			   String authorName, String authorTwitterURL, 
	    			   String authorImageURL, String publishedDate,
	    			   String updateDate) {
	
	this.title = title;
	this.titleNotFormatted = titleNotFormatted;
	this.authorName = authorName;
	this.authorTwitterURL = authorTwitterURL;
	this.authorImageURL = authorImageURL;
	this.publishDate = publishedDate;
	this.updateDate = updateDate;
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
     * @return the titleNotFormatting
     */
    public String getTitleNotFormatted() {
        return titleNotFormatted;
    }
    /**
     * @param titleNotFormatting the titleNotFormatting to set
     */
    public void setTitleNotFormatted(String titleNotFormatted) {
        this.titleNotFormatted = titleNotFormatted;
    }
    
    /**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }
    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    /**
     * @return the authorTwitterURL
     */
    public String getAuthorTwitterURL() {
        return authorTwitterURL;
    }
    /**
     * @param authorTwitterURL the authorTwitterURL to set
     */
    public void setAuthorTwitterURL(String authorTwitterURL) {
        this.authorTwitterURL = authorTwitterURL;
    }
    
    /**
     * @return the authorImageURL
     */
    public String getAuthorImageURL() {
        return authorImageURL;
    }
    /**
     * @param authorImageURL the authorImageURL to set
     */
    public void setAuthorImageURL(String authorImageURL) {
        this.authorImageURL = authorImageURL;
    }

    /**
     * @return the updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }
    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }
    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    
    public String toString () {
	return "title: '" + title + "', titleNotFormatted: '"
	        + titleNotFormatted + "', authorName: '" + authorName
	        + "', authorTwitterURL: '" + authorTwitterURL
	        + "', authorImageURL: '" + authorImageURL + "', publishDate: '"
	        + publishDate + "', updateDate:'" + updateDate + "'";
    }    
}