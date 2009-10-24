package com.googlecode.mashups4jsf.example.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class WeatherBean {
	private String selectedLocation;

	public String getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	public String getCurrentWeatherStatus() {
//		String feedResult = null;
//
//		try {
//
//			WeatherServiceStatus weatherServiceStatus = MashupFactory.getYahooWeatherService().getWeather(selectedLocation, 'c');
//			feedResult = weatherServiceStatus.getTitle()
//					   + "<br>"
//					   + weatherServiceStatus.getDescription();
//
//			if (feedResult.indexOf("Invalid") >= 0) {
//				return "";
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return feedResult;
	    
	    return "";
	}
}
