package com.googlecode.mashups.services.factory;

import com.googlecode.mashups.services.generic.api.FeedProducer;
import com.googlecode.mashups.services.generic.api.FeedReader;
import com.googlecode.mashups.services.generic.api.LocationService;
import com.googlecode.mashups.services.generic.impl.FeedProducerImpl;
import com.googlecode.mashups.services.generic.impl.FeedReaderImpl;
import com.googlecode.mashups.services.generic.impl.LocationServiceImpl;

/**
 * The <code>GenericServicesFactory</code> is the factory responsible for 
 * wrapping the Feed producing and reading and Location services. 
 * @author hazems
 *
 */
public class GenericServicesFactory {
    public static FeedProducer getFeedProducerService() {
        return FeedProducerImpl.getInstance();
    }
    
    public static FeedReader getFeedReaderService() {
        return FeedReaderImpl.getInstance();
    }    
    
    public static LocationService getLocationService() {
        return LocationServiceImpl.getInstance();
    }      
}
