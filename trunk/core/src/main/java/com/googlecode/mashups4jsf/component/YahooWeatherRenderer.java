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
package com.googlecode.mashups4jsf.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.mashups.services.common.ServiceParameter;
import com.googlecode.mashups.services.factory.YahooServicesFactory;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherService;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherServiceParameters;
import com.googlecode.mashups.services.yahoo.api.YahooWeatherServiceStatus;
import com.googlecode.mashups4jsf.component.component.YahooWeather;

/**
 * @author Hazem Saleh
 * @date Oct. 24, 2009
 * The (YahooWeatherRenderer) renders yahoo weather widget.
 */
public class YahooWeatherRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter         writer              = context.getResponseWriter();        
        YahooWeather           yahooWeather        = (YahooWeather) component;
        String                 locationCode        = yahooWeather.getLocationCode();
        String                 temperatureType     = yahooWeather.getTemperatureType();
        List<ServiceParameter> parameters          = new ArrayList<ServiceParameter>();
        YahooWeatherService    yahooWeatherService = YahooServicesFactory.getYahooWeatherService();
        
        parameters.add(new ServiceParameter(YahooWeatherServiceParameters.ZIP_CODE, locationCode));
        parameters.add(new ServiceParameter(YahooWeatherServiceParameters.TEMPERATURE_TYPE, temperatureType));
        
         try {
            YahooWeatherServiceStatus status = yahooWeatherService.getWeatherStatus(parameters);
            
            writer.write(status.toString());
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("");
        }
    }
    
}
