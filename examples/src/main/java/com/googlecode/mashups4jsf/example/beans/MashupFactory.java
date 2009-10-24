package com.googlecode.mashups4jsf.example.beans;

public class MashupFactory {
	public static YahooWeatherService getYahooWeatherService () {
		return new YahooWeatherServiceImpl();
	}
}
