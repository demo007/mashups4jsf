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
package com.googlecode.mashups.services.twitter.api;

public interface TwitterSearchServiceParameters {
    /**
     * # lang: Optional: Restricts tweets to the given language, given by an ISO 639-1 code.
     * Example: http://search.twitter.com/search.atom?lang=en&q=devo
     */ 
    final static String LANGUAGE = "lang";    
    
    /**
     * # locale: Optional. Specify the language of the query you are sending (only ja is currently effective). 
     * This is intended for language-specific clients and the default should work in the majority of cases.
     * Example: http://search.twitter.com /search.atom?q=test&locale=en
     */
    final static String LOCALE = "locale";    

    /**
     * # max_id: Optional. Returns tweets with status ids less than the given id.
     * Example: http://search.twitter.com/search.atom?q=twitter&max_id=1520639490
     */
    final static String MAX_ID = "max_id";        

    /**
     * # q: Optional.  The text to search for.  See the example queries section for examples of the syntax supported in this parameter
     * Example: http://search.twitter.com/search.json?&q=twitter
     */
    final static String QUERY = "q";        

    /**
     * # rpp: Optional. The number of tweets to return per page, up to a max of 100.
     * Example: http://search.twitter.com/search.atom?q=devo&rpp=15
     */ 
    final static String TWEETS_PER_PAGE = "rpp";       

    /**
     * # page: Optional. The page number (starting at 1) to return, up to a max of roughly 1500 results 
     * based on rpp * page. Note: there are pagination limits.
     * Example: http://search.twitter.com/search.atom?q=devo&rpp=15&page=2
     */ 
    final static String PAGE = "page";     

    /**
     * # since: Optional. Returns tweets with since the given date.  
     * Date should be formatted as YYYY-MM-DD
     * Example: http://search.twitter.com/search.atom?q=twitter&since=2010-02-28
     */ 
    final static String SINCE_DATE = "since";        

    /**
     * # since_id: Optional. Returns tweets with status ids greater than the given id.
     * Example: http://search.twitter.com/search.atom?q=twitter&since_id=1520639490
     */
    final static String SINCE_ID = "since_id";        

    /**
     * # geocode: Optional. Returns tweets by users located within a given radius of the given latitude/longitude.  
     * The location is preferentially taking from the Geotagging API, but will fall back to their Twitter profile. 
     * The parameter value is specified by "latitide,longitude,radius", where radius units must be specified as either "mi" 
     * (miles) or "km" (kilometers). Note that you cannot use the near operator via the API to geocode arbitrary locations; 
     * however you can use this geocode parameter to search near geocodes directly.
     * Example: http://search.twitter.com/search.atom?geocode=40.757929%2C-73.985506%2C25km
     */
    final static String GEOCODE = "geocode";      

    /**
     * # show_user: Optional. When true, prepends "<user>:" to the beginning of the tweet. 
     * This is useful for readers that do not display Atom's author field. The default is false.
     * Example: http://search.twitter.com/search.atom?q=twitterapi&show_user=true
     */ 
    final static String SHOW_USER = "show_user";      

    /**
     * # until: Optional. Returns tweets with generated before the given date.  Date should be formatted as YYYY-MM-DD
     * Example: http://search.twitter.com/search.atom?q=twitter&until=2010-03-28
     */
    final static String BEFORE_DATE = "until";       

    /**
     * # result_type: Optional. Specifies what type of search results you would prefer to receive.
     * Valid values include:  
          o mixed: In a future release this will become the default value. Include both popular and real time results in the response.
          o recent: The current default value. Return only the most recent results in the response.
          o popular: Return only the most popular results in the response.
     */
    final static String RESULT_TYPE = "result_type";           
}