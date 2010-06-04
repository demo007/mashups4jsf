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
package com.googlecode.mashups.services.common;

/**
 * @author hazems
 *
 */
public interface ServiceConstants {
    public static final String RSS_FORMAT  = "rss";
    public static final String ATOM_FORMAT = "atom";
    
    // Common attributes
    public static final String LINK            = "link";  
    public static final String TITLE           = "title";  
    public static final String AUTHOR          = "author";     
    public static final String TYPE	       = "type";
    public static final String IMAGE	       = "image";
    public static final String HREF	       = "href";  
   
    // Atom attributes
    public static final String ATOM_FEED          = "feed"; 
    public static final String ATOM_NAMESPACE_URI = "http://www.w3.org/2005/Atom";
    public static final String ATOM_ENTRY         = "entry";      
    public static final String ATOM_PUB_DATE      = "published";  
    public static final String ATOM_UPDATE_DATE   = "updated";
    public static final String ATOM_AUTHOR_NAME   = "name";
    public static final String ATOM_URI           = "uri";
    public static final String ATOM_CONTENT       = "content";     
    
    // RSS attributes
    public static final String RSS_CHANNEL     = "channel";
    public static final String RSS_ITEM        = "item";  
    public static final String RSS_PUB_DATE    = "pubDate";
}
