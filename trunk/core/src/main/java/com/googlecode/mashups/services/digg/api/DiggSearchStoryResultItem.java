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
package com.googlecode.mashups.services.digg.api;

/**
 * The Result item represent a story that is returned from searching for stories.
 * It is a Single story search result and contains these tags with their attributes:<br><code> 
 * <story  att:( link, submit_date, diggs, id, comments, href, status, media)> 
 *     <description> 
 * 	   <title> 
 *     <user att:( icon, name, profileviews, registered, 
 *     			   fullname, links )> 
 *     <topic ( name, short_name )> 
 *     <container att:( name, short_name )> 
 *     <shorturl att:(short_url , view_count)> 
 *     <thumbnail att:(contenttype, src, height, width, originalheight, originalwidth )>
 * </story>
 * </code>
 * @version 2.0 
 * Supports digg APIs version 2.0 
 */
public class DiggSearchStoryResultItem {
    private String storyLink;
    private String storySubmitDate;
    private String storyDiggs;
    private String storyId;
    private String storyComments;
    private String storyHref;
    private String storyStatus;
    private String storyMedia;
    private String description;
    private String title;
    private String userIcon;
    private String userName;
    private String userProfileviews;
    private String userRegistered;
    private String userFullName;
    private String userLinks;
    private String topicName;
    private String topicShortName;
    private String containerName;
    private String containerShortName;
    private String shorturl;
    private String shorturlViewCount;
    private String thumbnailContentType;
    private String thumbnailHeight;
    private String thumbnailOriginalHeight;
    private String thumbnailOriginalWidth;
    private String thumbnailSrc;
    private String thumbnailWidth;
    

    /**
     * @return the storyLink
     */
    public String getStoryLink() {
        return storyLink;
    }

    /**
     * @param storyLink
     *            the storyLink to set
     */
    public void setStoryLink(String storyLink) {
        this.storyLink = storyLink;
    }

    /**
     * @return the storySubmit
     */
    public String getStorySubmitDate() {
        return storySubmitDate;
    }

    /**
     * @param storySubmit
     *            the storySubmit to set
     */
    public void setStorySubmitDate(String storySubmitDate) {
        this.storySubmitDate = storySubmitDate;
    }

    /**
     * @return the storyDiggs
     */
    public String getStoryDiggs() {
        return storyDiggs;
    }

    /**
     * @param storyDiggs
     *            the storyDiggs to set
     */
    public void setStoryDiggs(String storyDiggs) {
        this.storyDiggs = storyDiggs;
    }

    /**
     * @return the storyId
     */
    public String getStoryId() {
        return storyId;
    }

    /**
     * @param storyId
     *            the storyId to set
     */
    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    /**
     * @return the storyComments
     */
    public String getStoryComments() {
        return storyComments;
    }

    /**
     * @param storyComments
     *            the storyComments to set
     */
    public void setStoryComments(String storyComments) {
        this.storyComments = storyComments;
    }

    /**
     * @return the storyHref
     */
    public String getStoryHref() {
        return storyHref;
    }

    /**
     * @param storyHref
     *            the storyHref to set
     */
    public void setStoryHref(String storyHref) {
        this.storyHref = storyHref;
    }

    /**
     * @return the storyStatus
     */
    public String getStoryStatus() {
        return storyStatus;
    }

    /**
     * @param storyStatus
     *            the storyStatus to set
     */
    public void setStoryStatus(String storyStatus) {
        this.storyStatus = storyStatus;
    }

    /**
     * @return the storyMedia
     */
    public String getStoryMedia() {
        return storyMedia;
    }

    /**
     * @param storyMedia
     *            the storyMedia to set
     */
    public void setStoryMedia(String storyMedia) {
        this.storyMedia = storyMedia;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the userIcon
     */
    public String getUserIcon() {
        return userIcon;
    }

    /**
     * @param userIcon
     *            the userIcon to set
     */
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userProfileviews
     */
    public String getUserProfileviews() {
        return userProfileviews;
    }

    /**
     * @param userProfileviews
     *            the userProfileviews to set
     */
    public void setUserProfileviews(String userProfileviews) {
        this.userProfileviews = userProfileviews;
    }

    /**
     * @return the userRegistered
     */
    public String getUserRegistered() {
        return userRegistered;
    }

    /**
     * @param userRegistered
     *            the userRegistered to set
     */
    public void setUserRegistered(String userRegistered) {
        this.userRegistered = userRegistered;
    }

    /**
	 * @return the userFullName
	 */
	public String getUserFullName() {
		return userFullName;
	}

	/**
	 * @param userFullName the userFullName to set
	 */
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	/**
	 * @return the userLinks
	 */
	public String getUserLinks() {
		return userLinks;
	}

	/**
	 * @param userLinks the userLinks to set
	 */
	public void setUserLinks(String userLinks) {
		this.userLinks = userLinks;
	}

	/**
     * @return the topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * @param topicName
     *            the topicName to set
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * @return the topicShortName
     */
    public String getTopicShortName() {
        return topicShortName;
    }

    /**
     * @param topicShortName
     *            the topicShortName to set
     */
    public void setTopicShortName(String topicShortName) {
        this.topicShortName = topicShortName;
    }

    /**
     * @return the containerName
     */
    public String getContainerName() {
        return containerName;
    }

    /**
     * @param containerName
     *            the containerName to set
     */
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    /**
     * @return the containerShorName
     */
    public String getContainerShortName() {
        return containerShortName;
    }

    /**
     * @param containerShorName
     *            the containerShorName to set
     */
    public void setContainerShortName(String containerShortName) {
        this.containerShortName = containerShortName;
    }

    /**
     * @return the shorturl
     */
    public String getShorturl() {
        return shorturl;
    }

    /**
     * @param shorturl
     *            the shorturl to set
     */
    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    /**
     * @return the shorturlViewCount
     */
    public String getShorturlViewCount() {
        return shorturlViewCount;
    }

    /**
     * @param shorturlViewCount
     *            the shorturlViewCount to set
     */
    public void setShorturlViewCount(String shorturlViewCount) {
        this.shorturlViewCount = shorturlViewCount;
    }

    /**
	 * @return the thumbnailContentType
	 */
	public String getThumbnailContentType() {
		return thumbnailContentType;
	}

	/**
	 * @param thumbnailContentType the thumbnailContentType to set
	 */
	public void setThumbnailContentType(String thumbnailContentType) {
		this.thumbnailContentType = thumbnailContentType;
	}

	/**
	 * @return the thumbnailHeight
	 */
	public String getThumbnailHeight() {
		return thumbnailHeight;
	}

	/**
	 * @param thumbnailHeight the thumbnailHeight to set
	 */
	public void setThumbnailHeight(String thumbnailHeight) {
		this.thumbnailHeight = thumbnailHeight;
	}

	/**
	 * @return the thumbnailOriginalHeight
	 */
	public String getThumbnailOriginalHeight() {
		return thumbnailOriginalHeight;
	}

	/**
	 * @param thumbnailOriginalHeight the thumbnailOriginalHeight to set
	 */
	public void setThumbnailOriginalHeight(String thumbnailOriginalHeight) {
		this.thumbnailOriginalHeight = thumbnailOriginalHeight;
	}

	/**
	 * @return the thumbnailOriginalWidth
	 */
	public String getThumbnailOriginalWidth() {
		return thumbnailOriginalWidth;
	}

	/**
	 * @param thumbnailOriginalWidth the thumbnailOriginalWidth to set
	 */
	public void setThumbnailOriginalWidth(String thumbnailOriginalWidth) {
		this.thumbnailOriginalWidth = thumbnailOriginalWidth;
	}

	/**
	 * @return the thumbnailSrc
	 */
	public String getThumbnailSrc() {
		return thumbnailSrc;
	}

	/**
	 * @param thumbnailSrc the thumbnailSrc to set
	 */
	public void setThumbnailSrc(String thumbnailSrc) {
		this.thumbnailSrc = thumbnailSrc;
	}

	/**
	 * @return the thumbnailWidth
	 */
	public String getThumbnailWidth() {
		return thumbnailWidth;
	}

	/**
	 * @param thumbnailWidth the thumbnailWidth to set
	 */
	public void setThumbnailWidth(String thumbnailWidth) {
		this.thumbnailWidth = thumbnailWidth;
	}

	@Override
    public String toString() {

        return "link: '" + storyLink + "', " 
                + "SubmitDate: '"
                + storySubmitDate + "', " 
                + "Diggs: '" + storyDiggs + "', "
                + "Id: '" + storyId + "', "
                + "Comments: '" + storyComments + "', " 
                + "Href: '" + storyHref + "', " 
                + "Status: '" + storyStatus + "', "
                + "Media: '" + storyMedia + "', "
                + "Description: [" + description + "]" + "', " 
                + "title: '" + title + "', " 
                + "userIcon: '" + userIcon + "', "
                + "userName: '" + userName + "', " 
                + "userProfileviews: '"+ userProfileviews + "', " 
                + "userRegistered: '"+ userRegistered + "', " 
                + "topicName: '" + topicName + "', "
                + "topicShortName: '" + topicShortName + "', "
                + "containerName: '" + containerName + "', "
                + "containerShortName: '" + containerShortName + "', "
                + "shorturl: '" + shorturl + "', " 
                + "shorturlViewCount: '"+ shorturlViewCount;

    }
}
