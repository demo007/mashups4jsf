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

import javax.faces.context.FacesContext;

/**
 * @author hazems
 *
 */
public class Place {
    String shortAddressName;
    String longAddressName;
    String postalCode;
    String keyword;

    public Place(String shortAddressName, String longAddressName, String postalCode) {
        this.shortAddressName = shortAddressName;
        this.longAddressName = longAddressName;
        this.postalCode = postalCode;
    }
    
    public Place(String shortAddressName, String longAddressName, String postalCode, String keyword) {
        this.shortAddressName = shortAddressName;
        this.longAddressName = longAddressName;
        this.postalCode = postalCode;
        this.keyword = keyword;
    }    
    

    /**
     * @return the shortAddressName
     */
    public String getShortAddressName() {
        return shortAddressName;
    }

    /**
     * @param shortAddressName the shortAddressName to set
     */
    public void setShortAddressName(String shortAddressName) {
        this.shortAddressName = shortAddressName;
    }

    /**
     * @return the longAddressName
     */
    public String getLongAddressName() {
        return longAddressName;
    }

    /**
     * @param longAddressName the longAddressName to set
     */
    public void setLongAddressName(String longAddressName) {
        this.longAddressName = longAddressName;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String setSelectedPlace() {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("selectedPlace", this);
        
        return null;
    }
}
