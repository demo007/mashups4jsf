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
package com.googlecode.mashups.services.generic.api;

/**
 * @author Hazem Saleh
 * The PlaceMark is used for holding the place information.
 * It is used by the <code>LocationService</code> class.
 */
public class PlaceMark {
    String id;
    String address;
    String postalCodeNumber;
    String countryName;
    String countryCode;
    Integer accuracy;
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCountryName() {
        return countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostalCodeNumber() {
        return postalCodeNumber;
    }

    public void setPostalCodeNumber(String postalCodeNumber) {
        this.postalCodeNumber = postalCodeNumber;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }
    
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String toString() {
        return "id: " + id + "\n" +
               "address: " + address + "\n" +
               "postalCodeNumber: " + postalCodeNumber + "\n" +
               "countryName: " + countryName + "\n";
    }
}
