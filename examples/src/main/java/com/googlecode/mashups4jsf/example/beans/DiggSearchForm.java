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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.digg.api.DiggSearchServiceParameters;
import com.googlecode.mashups.services.digg.api.DiggSearchStoryResults;
import com.googlecode.mashups.services.factory.DiggServicesFactory;

/***/
@ManagedBean
@RequestScoped
public class DiggSearchForm {
    private static final String    DEFAULT_COUNT_VALUE = "";

    private DiggSearchStoryResults results;
   
    /* Search parameters */
    private String                 searchQuery;
    private String                 count               = DEFAULT_COUNT_VALUE;
    private String                 offset              = "0";
    private String                 domain              = "";
    private String                 sort                = "";
	private String                 topic               = "";	
    private String                 media               = "";
    private String                 minDate             = "";
    private String                 maxDate             = "";
    
   
    /** List of optional count values */
    private ArrayList<SelectItem>  countItems          = null;
    
    /** List of optional sort values */
    private ArrayList<SelectItem>  sortItems           = null;
    
    /** List of optional media values */
    private ArrayList<SelectItem>  mediaItems           = null;
    
    /** List of optional offset start values */
    private ArrayList<SelectItem>  offsetItems         = null;

    public ArrayList<SelectItem> getCountItems() {
		countItems = new ArrayList<SelectItem>();
		countItems.add(new SelectItem("", "(optional)"));
		countItems.add(new SelectItem("1", "1"));
		countItems.add(new SelectItem("5", "5"));
		countItems.add(new SelectItem("10", "10"));
		countItems.add(new SelectItem("15", "15"));
		return countItems;
    }

    public ArrayList<SelectItem> getOffsetItems() {
		offsetItems = new ArrayList<SelectItem>();
		offsetItems.add(new SelectItem("", "(optional)"));
		offsetItems.add(new SelectItem("0", "0"));
		offsetItems.add(new SelectItem("5", "5"));
		offsetItems.add(new SelectItem("10", "10"));
		offsetItems.add(new SelectItem("15", "15"));
		return offsetItems;
    }

    public ArrayList<SelectItem> getSortItems() {
		sortItems = new ArrayList<SelectItem>();
		sortItems.add(new SelectItem("", "(optional)"));
		sortItems.add(new SelectItem("submit_date-desc", "Submit Date Desc"));
		sortItems.add(new SelectItem("submit_date-asc", "Submit Date Asc"));
		sortItems.add(new SelectItem("promote_date-desc", "Promote Date Desc"));
		sortItems.add(new SelectItem("promote_date-asc", "Promote Date-Asc"));
		sortItems.add(new SelectItem("digg_count-desc", "Digg Count Desc"));
		sortItems.add(new SelectItem("digg_count-asc", "Digg Count Asc"));
		return sortItems;
    }

    public ArrayList<SelectItem> getMediaItems() {
		mediaItems = new ArrayList<SelectItem>();
		mediaItems.add(new SelectItem("", "All"));
		mediaItems.add(new SelectItem("news", "News"));
		mediaItems.add(new SelectItem("videos", "Videos"));
		mediaItems.add(new SelectItem("images", "Images"));
		return mediaItems;
    }
    /**
     * @return the searchQuery
     */
    public String getSearchQuery() {
    	return searchQuery;
    }

    /**
     * @param searchQuery
     *            the searchQuery to set
     */
    public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
    }

    /***
     * 
     * @return the count value
     */
    public String getCount() {
    	return count;
    }

    /***
     * 
     * @param count
     *            the count value to set
     */
    public void setCount(String count) {
    	this.count = count;
    }

    /**
     * @return the offset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return the minDate
	 */
	public String getMinDate() {
		return minDate;
	}

	/**
	 * @param minDate the minDate to set
	 */
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	/**
	 * @return the maxDate
	 */
	public String getMaxDate() {
		return maxDate;
	}

	/**
	 * @param maxDate the maxDate to set
	 */
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

    /**
     * @return the results
     */
    public DiggSearchStoryResults getResults() {
		return results;
    }

    /**
     * @param results
     *            the results to set
     */
    public void setResults(DiggSearchStoryResults results) {
		this.results = results;
    }
    
    
    public String doNothing() {
		return null;
    }

    /** Used for calling the search service action . */
    public String getStoryList() {
    	List<ServiceParameter> searchParameters = new ArrayList<ServiceParameter>();

		searchParameters.add(new ServiceParameter(
		        DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_QUERY,
		        searchQuery));
		//Count parameter
        count = validateCount(getCount(), DEFAULT_COUNT_VALUE);
        if (count != null && !count.equals("")) {
            //count = DEFAULT_COUNT_VALUE;
        	searchParameters.add(new ServiceParameter(
        		DiggSearchServiceParameters.SEARCH_ARGS_COUNT, count));
        }
		
		//Offset parameter
		if (offset != null && !offset.equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_OFFSET, offset));
		}
		//Sort parameter
		if (sort != null && !sort.equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_SORT, sort));
		}
		//Domain parameter
		if (domain != null && !domain.trim().equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_DOMAIN, domain));
		}
		//Media parameter
		if (media != null && !media.trim().equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_MEDIA, media));
		}		
		//Topic parameter
		if (topic != null && !topic.trim().equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_TOPIC, topic));
		}
		// Min Date timestamp parameter
		if (minDate != null && !minDate.trim().equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_MIN_DATE, minDate));
		}
		// Max Date timestamp parameter
		if (maxDate != null && !maxDate.trim().equals("")) {
		    searchParameters.add(new ServiceParameter(
			    DiggSearchServiceParameters.SEARCH_ARGS_SEARCH_MAX_DATE, maxDate));
		}
	
		// Call the search service with the provided parameters
		try {
		    results = DiggServicesFactory.getDiggSearchService().getStoriesList(searchParameters);
	
		} catch (Exception e) {
		    e.printStackTrace();
		}
	
		return null;
    }

    /** Validate count parameter. */
    private String validateCount(String value, String defaultVal) {
		try {
		    if (value == null || value.trim().equals("")) {
			count = defaultVal;
	
		    } else {
			count = String.valueOf(Integer.parseInt(value));
	
		    }
		} catch (NumberFormatException e) {
		    count = defaultVal;
		}
		return count;
    }
}
