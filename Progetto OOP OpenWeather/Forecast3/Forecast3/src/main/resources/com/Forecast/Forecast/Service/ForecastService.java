package com.Forecast.Forecast.Service;

import java.util.List;

import com.Forecast.Forecast.Model.WeatherForecast;

public interface ForecastService {
    
    public List<WeatherForecast> getWeather();

}
