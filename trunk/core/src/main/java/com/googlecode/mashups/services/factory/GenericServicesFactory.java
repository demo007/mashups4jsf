package com.googlecode.mashups.services.factory;

import com.googlecode.mashups.services.generic.api.FeedProducer;
import com.googlecode.mashups.services.generic.impl.FeedProducerImpl;

public class GenericServicesFactory {
    public static FeedProducer getFeedProducerService() {
        return FeedProducerImpl.getInstance();
    }
}
