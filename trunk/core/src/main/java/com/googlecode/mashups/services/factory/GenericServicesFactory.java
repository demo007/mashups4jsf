package com.googlecode.mashups.services.factory;

import com.googlecode.mashups.services.generic.api.FeedProducer;
import com.googlecode.mashups.services.generic.api.FeedReader;
import com.googlecode.mashups.services.generic.impl.FeedProducerImpl;
import com.googlecode.mashups.services.generic.impl.FeedReaderImpl;

/**
 * The <code>GenericServicesFactory</code> is the factory responsible for 
 * wrapping the Feed producing and reading services. 
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
}
