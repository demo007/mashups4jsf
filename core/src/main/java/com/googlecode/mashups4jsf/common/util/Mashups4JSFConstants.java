package com.googlecode.mashups4jsf.common.util;

public class Mashups4JSFConstants {
	public static final String FEED_CLASS  = "FEED_CLASS";
	public static final String OUTPUT_TYPE = "OUTPUT";
	
	public static final String RSS_OUTPUT_TYPE  = "application/rss+xml";
	public static final String ATOM_OUTPUT_TYPE = "application/atom+xml";		
	
	public static final String RSS_VALUE  = "rss";	
	public static final String ATOM_VALUE = "atom";	
	
	public static final String FEED_ERROR_MESSAGE_MISSING_FEED_CLASS = "Feed class must be specified in order to generate the feed !!!";
	public static final String FEED_ERROR_MESSAGE_CANNOT_INSTANCE_CLASS = "Feed class cannot be instantiated or does not exist !!!";	
}
