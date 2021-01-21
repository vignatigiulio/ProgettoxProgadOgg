package com.Forecast.Forecast.Service;
import java.util.List;
import com.Forecast.Forecast.Model.Weather;
import com.Forecast.Forecast.Model.Stats.StastInterface;
import com.Forecast.Forecast.Model.Utils.*;

/** Interfaccia che richiama i metodi relativi ai GetMapping
 *  delle rotte /weather e /weather/{data}
 *  Estende le interfacce Filter e StatsInterface per richiamare
 *  i metodi relativi alle rotte /stats, /statsError,
 *  /statsTwoError e /statsErrorCity situate nel RestController
 */



public interface ForecastService extends Filter<Object, Object>,StastInterface {
    
    List<Weather> getWeathers();
    List<Weather> getWeather(String filter);
    
   
	
	
	
}
