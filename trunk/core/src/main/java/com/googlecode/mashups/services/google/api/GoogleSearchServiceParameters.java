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
package com.googlecode.mashups.services.google.api;

public interface GoogleSearchServiceParameters {
    
    /**
     * This argument supplies protocol version number. The only valid value at
     * this point in time is 1.0.
     */
    final static String VERSION = "v"; /* only 1.0 is supported */
    
    /**
     * This argument supplies the query, or search expression, that is passed
     * into the searcher.
     */
    final static String QUERY = "q"; 
    
    /**
     * This optional argument supplies the number of results that the
     * application would like to receive. A value of small indicates a small
     * result set size or 4 results. A value of large indicates a large result
     * set or 8 results. If this argument is not supplied, a value of small is
     * assumed.
     */
    final static String RSZ = "rsz";
    
    /**
     * This optional argument supplies the host language of the application
     * making the request. If this argument is not present then the system will
     * choose a value based on the value of the Accept-Language http header. If
     * this header is not present, a value of en is assumed.
     */
    final static String HL = "hl";
    
    /**
     * This optional argument supplies the application's key. If specified, it
     * must be a valid key associated with your site which is validated against
     * the passed referer header. The advantage of supplying a key is so that we
     * can identify and contact you should something go wrong with your
     * application. Without a key, we will still take the same appropriate
     * measures on our side, but we will not be able to contact you. It is
     * definitely best for you to pass a key.
     */
    final static String KEY = "key";
    
    /**
     * This optional argument supplies the start index of the first search
     * result. Each successful response contains a cursor object (see below)
     * which includes an array of pages. The start property for a page may be
     * used as a valid value for this argument.
     */
    final static String START = "start";
    
    /**
     * This optional argument alters the standard response format. When
     * supplied, instead of producing a simple JSON encoded object, the system
     * produces a Javascript function call response where the value of callback
     * specifies the name of the function called in the response.
     */
    final static String CALLBACK = "callback";
    
    /**
     * This optional argument is related to the context argument. When both are
     * supplied, the value of context alters the normal response format
     * associated with callback.
     */
    final static String CONTEXT = "context";    
}