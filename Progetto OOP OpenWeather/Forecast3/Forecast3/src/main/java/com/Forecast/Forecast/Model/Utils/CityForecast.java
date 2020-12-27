package com.Forecast.Forecast.Model.Utils;

public class CityForecast {
    
    private String city;
    private float error_threshold;
    
    public CityForecast(String city, float error_threshold) {
	super();
	this.city = city;
	this.error_threshold = error_threshold;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getError_threshold() {
        return error_threshold;
    }

    public void setError_threshold(float error_threshold) {
        this.error_threshold = error_threshold;
    }
    
    

}
