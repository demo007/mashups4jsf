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


public interface DiggSearchServiceParameters {

	// common arguments for version  1.0
	final static String SEARCH_ARGS_METHOD = "method"; // e.g. search.stories
	
	final static String SEARCH_ARGS_TYPE = "type"; // e.g. xml, json, javascript,

	//search.stories arguments //////////////////////////////////////////////////
	final static String SEARCH_ARGS_COUNT = "count";    // the request count for
														// paging support
	
	final static String SEARCH_ARGS_OFFSET = "offset";  // The starting offset
	
	final static String SEARCH_ARGS_DOMAIN = "domain";  // filter search to
														// a domain e.g.
														// digg.com
	
	final static String SEARCH_ARGS_SORT = "sort"; // string. Sort result.
														// Possible values :
														// 	 promote_date-desc,
														// 	 promote_date-asc,
														// 	 submit_date-desc,
														//	 submit_date-asc,
														// 	 digg_count-desc,
														//   digg_count-asc,
														//   date-desc,
														//   date-asc.														
														// Not all sort options
														// are available on
														// each endpoint.
	final static String SEARCH_ARGS_SEARCH_QUERY = "query"; // search query

	// @since @version 2.0
	final static String SEARCH_ARGS_SEARCH_TOPIC = "topic"; // search topic
														// String Topic's
														// short_name name.
														// i.e. apple (see
														// topic.getAll)
														// (optional)

	// @since @version 2.0
	final static String SEARCH_ARGS_SEARCH_MEDIA = "media"; // search media List
														// or single medium
														// name. Possible
														// values include
														// news, videos, and
														// images. Default
														// is all.
														// (optional)

	// @since @version 2.0
	final static String SEARCH_ARGS_SEARCH_MAX_DATE = "max_date"; // search max_date.
														// timestamp
														// Max date
														// represented
														// as a unix
														// timestamp
														// (optional)
	// @since @version 2.0	
	final static String SEARCH_ARGS_SEARCH_MIN_DATE = "min_date"; // search min_date
														// timestamp
														// Min date
														// represented
														// as a unix
														// timestamp
														// (optional)
}
