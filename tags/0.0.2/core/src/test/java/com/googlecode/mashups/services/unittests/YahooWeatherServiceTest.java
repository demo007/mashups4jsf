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

import java.util.ArrayList;
import java.util.List;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.YahooServicesFactory;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherService;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherServiceParameters;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherServiceStatus;

import junit.framework.TestCase;

public class YahooWeatherServiceTest extends TestCase {
    public void testGetWeatherStatus() {
        List<ServiceParameter> weatherStatusParameters = new ArrayList<ServiceParameter>();
        
        weatherStatusParameters.add(new ServiceParameter(YahooWeatherServiceParameters.ZIP_CODE, "94089"));
        weatherStatusParameters.add(new ServiceParameter(YahooWeatherServiceParameters.TEMPERATURE_TYPE, "c"));
        
        YahooWeatherService yahooWeatherService = YahooServicesFactory.getYahooWeatherService();
        
        try {
            YahooWeatherServiceStatus status = yahooWeatherService.getWeatherStatus(weatherStatusParameters);
            
            System.out.println("Output: \n\r" + status);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unable to get the weather status ...");
        }
        
    }
}
