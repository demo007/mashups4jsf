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

import java.util.List;

import com.googlecode.mashups.services.common.ServiceParameter;

public interface DiggSearchService {
    public static final String DIGG_SEARCH_SERVICE_URL  = "http://services.digg.com/1.0/endpoint";
    public static final String DIGG_SEARCH_STORY_METHOD = "search.stories";
    public static final String DIGG_SEARCH_STORY_TYPE   = "xml";

    public DiggSearchStoryResults getStoriesList(List<ServiceParameter> parameters) throws Exception;
}
