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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

import com.googlecode.gmaps4jsf.component.marker.MarkerValue;
import com.googlecode.gmaps4jsf.services.GMaps4JSFServiceFactory;
import com.googlecode.gmaps4jsf.services.data.PlaceMark;

/**
 * @author hazems
 *
 */
@ManagedBean
@RequestScoped
public class Theater1 {
    String postalCode;
    String address; 
    String location;
    
    public void processValueChangeForMarker(ValueChangeEvent valueChangeEvent) {
        MarkerValue value = (MarkerValue) valueChangeEvent.getNewValue();
        
        if (value != null) {
            try {
                PlaceMark placeMark = GMaps4JSFServiceFactory.getReverseGeocoderService().getPlaceMark(value.getLatitude(), value.getLongitude());
                
                address    = placeMark.getAddress();
                postalCode = placeMark.getPostalCodeNumber();
                location   = value.getLatitude() + ", " + value.getLongitude();
                
                System.out.println("LatLng: " + value.getLatitude() + ", " + value.getLongitude());
                System.out.println("address: " + address);
                System.out.println("postalCode: " + postalCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }      
}
