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
package com.googlecode.mashups.services.generic.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.mashups.services.generic.api.Location;
import com.googlecode.mashups.services.generic.api.LocationService;
import com.googlecode.mashups.services.generic.api.PlaceMark;
import com.googlecode.mashups4jsf.common.util.Mashups4JSFConstants;

/**
 * <code>LocationServiceImpl</code> is the implementation of the <code>LocationService</code> which 
 * is responsible for handling the location services.
 * @author hazems
 *
 */
public class LocationServiceImpl implements LocationService {
	private static Log log = LogFactory.getLog(LocationServiceImpl.class);    	

    public static LocationService getInstance() {
        return locationService;
    }
    
	public Location getLocationFromAddress(String address) throws Exception {
		Location   location       = new Location();
		String     encodedAddress = URLEncoder.encode(address, "UTF-8");     
		URL        url            = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&sensor=false"); 
		String     addresses      = readURL(url);    
		JSONObject json           = new JSONObject(addresses);
		
		String status = (String) json.get(Mashups4JSFConstants.STATUS_LABEL);
		
		if (Mashups4JSFConstants.OK_MESSAGE.equalsIgnoreCase(status)) {
		    log.debug("OK Status is: " + status + " for: " + address);                
		    JSONArray results = json.getJSONArray(Mashups4JSFConstants.RESULTS_LABEL);
		    
		    if (results.length() <= 0) {
		        return null;
		    }
		    
		    JSONObject result       = results.getJSONObject(0);
		    JSONObject jsonLocation = result.getJSONObject(Mashups4JSFConstants.GEOMETRY_LABEL).getJSONObject(Mashups4JSFConstants.LOCATION_LABEL);
		    
		    Double latitude  = (Double) jsonLocation.get(Mashups4JSFConstants.LATITUDE_LABEL);
		    Double longitude = (Double) jsonLocation.get(Mashups4JSFConstants.LONGITUDE_LABEL);                
		    
		    location.setLatitude(latitude);
		    location.setLongitude(longitude);
		    
		} else {
		    log.debug("[From Google Service] Status is: " + status);
		    return null;
		}
		
		return location;
	}    
    
    public PlaceMark getAddressFromLocation(String latitude, String longitude) throws Exception {
        try {
            URL            url       = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" 
                                     + latitude.trim()
                                     + "," 
                                     + longitude.trim() 
                                     + "&sensor=false");
            
            PlaceMark      placeMark = new PlaceMark();
            JSONObject     json      = new JSONObject(readURL(url));
            JSONArray      results   = (JSONArray) json.getJSONArray(Mashups4JSFConstants.RESULTS_LABEL);
            
            JSONObject placeMarkPrimaryData = (JSONObject) results.get(0);
            
            getPlaceMarkPrimaryInformation(placeMark, placeMarkPrimaryData);
            
            return placeMark;
        } catch (Exception exception) {
            throw new Exception("Error: " + exception.getMessage());
        }
    }
    
    private static String readURL(URL url) throws Exception {
        URLConnection  connection = url.openConnection();
        StringBuilder  builder    = new StringBuilder();
        BufferedReader reader     = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String         line;
        
        while((line = reader.readLine()) != null) {
        	builder.append(line);
        }        
        
        return builder.toString();
    }

    /*
     * <code>getPlaceMarkPrimaryInformation</code> is used for getting all of the place marker
     * primary information.
     */
    private void getPlaceMarkPrimaryInformation(PlaceMark placeMark, JSONObject placeMarkPrimaryData) {
        
        // Address
        try {
            placeMark.setAddress((String) placeMarkPrimaryData.get(Mashups4JSFConstants.FORMATTED_ADDRESS_LABEL));
        } catch (JSONException exception) {
        	log.debug("Unable to get the place mark address");          	
        }
        
        // Postal code
        try {
        	JSONArray results = (JSONArray) placeMarkPrimaryData.getJSONArray(Mashups4JSFConstants.ADDRESS_COMPONENT_LABEL);
        	
        	for (int i = 0; i < results.length(); ++i) {
        		JSONObject jsonObject = results.getJSONObject(i);
        		
        		String shortName = jsonObject.getString("short_name");        		
        		JSONArray types = jsonObject.getJSONArray("types");
        		
        		for (int j = 0; j < types.length(); ++j) {
        			String attribute = types.getString(j);
        			
        			if ("postal_code".equals(attribute)) {
        				placeMark.setPostalCodeNumber(shortName);
        				break;
        			}
        		}
        	}
        	
        	
        } catch (JSONException exception) {
        	log.debug("Unable to get the place mark address");          	
        }
    }    


    private LocationServiceImpl() {
    }
    
    private static LocationService locationService = new LocationServiceImpl();
}
