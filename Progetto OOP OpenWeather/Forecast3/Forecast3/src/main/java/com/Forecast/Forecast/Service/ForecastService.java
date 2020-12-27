package com.Forecast.Forecast.Service;
import java.util.List;
import com.Forecast.Forecast.Model.Weather;
import com.Forecast.Forecast.Model.Stats.StastInterface;
import com.Forecast.Forecast.Model.Utils.*;
public interface ForecastService extends Filter<Object, Object>,StastInterface {
    
    List<Weather> getWeathers();
    Weather getWeather(String data);
   
	
	
	
}
