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
package com.googlecode.mashups.services.yahoo.api;

import java.util.List;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.yahoo.exceptions.InvalidLocationException;


public interface YahooWeatherService {
    public final static String YAHOO_WEATHER_SERVICE_URL = "http://weather.yahooapis.com/forecastrss";
    public final static String RESPONSE_INVALID_KEYWORD  = "Invalid";
    public static final String RESPONSE_INVALID_LOCATION = "Invalid location!!!";    
    
	public YahooWeatherServiceStatus getWeatherStatus(List<ServiceParameter> parameters) throws InvalidLocationException, Exception;
}