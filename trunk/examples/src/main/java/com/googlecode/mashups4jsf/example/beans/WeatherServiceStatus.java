package com.googlecode.mashups4jsf.example.beans;

public class WeatherServiceStatus {
	private String title;
	private String description;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String toString () {
		return title + "\n" + description;
	}
}
