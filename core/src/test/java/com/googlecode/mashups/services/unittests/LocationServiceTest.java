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
package com.googlecode.mashups.services.unittests;

import junit.framework.TestCase;

import com.googlecode.mashups.services.factory.GenericServicesFactory;
import com.googlecode.mashups.services.generic.api.Location;
import com.googlecode.mashups.services.generic.api.LocationService;
import com.googlecode.mashups.services.generic.api.PlaceMark;

public class LocationServiceTest extends TestCase {
    public void testGetAddressFromLocation() throws Exception {
        LocationService locationService = GenericServicesFactory.getLocationService();
        
        try {
            PlaceMark placeMark = locationService.getAddressFromLocation("30.064742", "31.249509");
            
            System.out.println("Address for (\"30.2\", \"31.21\") is: " + placeMark.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the address from the location ...");
        }        
    }
    
    public void testGetLocationFromAddress() throws Exception {
        LocationService locationService = GenericServicesFactory.getLocationService();
        
        try {
            Location location = locationService.getLocationFromAddress("Cairo, Egypt");
            
            System.out.println("LatLng for (\"Cairo, Egypt\") is: " + location.getLatitude() +", " + location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the location from the address ...");
        }        
    }    
}
