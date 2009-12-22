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

import java.util.ArrayList;
import java.util.List;

/** Search result for story type searches .
 * 
   <stories (count, offset, timestamp, total, version,) >    
   <story ( link, submit_date, diggs, id, comments, href, status, media)>
   <description>
   <title>
   <user ( icon, name, profileviews, registered )>
   <topic ( name, short_name )>
   <container ( name, short_name )>
   <shorturl (short_url , view_count)>
   </story>
   ...
   </stories>
**/

public class DiggSearchStoryResults {
  
    /** story count returned. */ 
    private String count;
    /** returned starting offset. */
    private String offset;    
    /** total number of stories results for the search query. */
    private String total;
    /**Result timestamp*/
    private String timestamp;
    /** version of the API result*/
    private String version;
    /** list of stories */
    private List<DiggSearchStoryResultItem> searchResultList;
   
    /**
     * 
     */
    public DiggSearchStoryResults() {
        this("");
    }
    /**
     * 
     */
    public DiggSearchStoryResults(String searchQuery) {
        super();
        searchResultList = new ArrayList<DiggSearchStoryResultItem>();
        //this.searchQuery = searchQuery;
    }
   
    /**
     * @return the searchQuery
     */
   /* public String getSearchQuery() {
        return searchQuery;
    }*/

    /**
     * @param searchQuery the searchQuery to set
     */
   /* public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }*/

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
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
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the searchResultList
     */
    public List<DiggSearchStoryResultItem> getSearchResultList() {
        return searchResultList;
    }

    /**
     * @param searchResultList the searchResultList to set
     */
    public void setSearchResultList(List<DiggSearchStoryResultItem> searchResultList) {
        this.searchResultList = searchResultList;
    }
    
    @Override
    public String toString () {
        return "timestamp: '" +timestamp + "', " + "count: '" +count + "', " +"offset: '" +offset + "', " + "total: '" +total + "', " + "version: '" +version + "' " + "', " + "searchResultList.size: '" + ((searchResultList !=null)? String.valueOf(searchResultList.size()):"0" ) + "' "; 
    }

}
