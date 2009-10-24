package com.googlecode.mashups4jsf.example.beans;

public interface YahooWeatherService {
	public WeatherServiceStatus getWeather(String zipCode, char temperatureType) throws Exception;
}