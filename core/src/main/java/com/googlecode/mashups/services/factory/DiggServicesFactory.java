package com.googlecode.mashups.services.factory;

import com.googlecode.mashups.services.digg.api.DiggSearchService;
import com.googlecode.mashups.services.digg.impl.DiggSearchService2Impl;
import com.googlecode.mashups.services.digg.impl.DiggSearchServiceImpl;

public class DiggServicesFactory {
    public static DiggSearchService getDiggSearchService() {
        return DiggSearchService2Impl.getInstance();
    }

}
