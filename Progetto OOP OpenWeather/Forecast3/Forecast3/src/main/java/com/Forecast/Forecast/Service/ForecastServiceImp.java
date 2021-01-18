package com.Forecast.Forecast.Service;


import com.Forecast.Forecast.Model.Stats.Stats;

import com.Forecast.Forecast.Model.Stats.StatsForecast;
import com.Forecast.Forecast.Model.Stats.StatsHistorical;
import com.Forecast.Forecast.Model.Utils.CityForecast;
import com.Forecast.Forecast.Model.Utils.FilterUtils;
import org.springframework.stereotype.Service;
import com.Forecast.Forecast.Model.Weather;
import com.Forecast.Forecast.Model.Data.DataHistorical;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Implementa l'interfaccia ForecastService e contiene i metodi 
 *  relativi alle rotte specificate in RestController
 */


@Service
public class ForecastServiceImp implements ForecastService {
    
    List<Weather> list = null;
    DataHistorical weatherHistorical = null;
    Stats stats = null;
    FilterUtils filterUtils = null;
    CityForecast prev = null;
    
    /** Vengono inizializzati i vari oggetti
     *  Contiene un ciclo for che partendo dal giorno odierno va a ritroso
     *  di 5 giorni indietro passandoli all'oggeto di tipo DataHistorical
     */    
    public ForecastServiceImp()  {
	    
    	
    	list = new ArrayList<Weather>();
    	weatherHistorical = new  DataHistorical ();
    	list = weatherHistorical.fillList();
    	filterUtils = new FilterUtils();
    	
    	 Date date = new Date();
	    Long epochFormat = (date.getTime())/1000;
	    for(int i = 1; i <= 5;i++)
	    {
	    	epochFormat-=86400;
	    	weatherHistorical.setDt(epochFormat);
	    	weatherHistorical.callApi();
	    	weatherHistorical.saveFile();
	    }
	
    }
   
    /** Metodo che, dato un filtro passato come paraetro, crea un oggetto
     * di tipo Stats relativo al filtro
     * @param filter stringa utilizzata per filtrare l'oggetto
     * @return tipo di oggetto Stats opportunamente filtrato
     */
    
    public Stats statsFilter(String filter) 
    {
    if(weatherHistorical.getCitta()!= null)
    {
	filter = filter.toLowerCase();
    	if(filter.equals("historical"))
			try {
				{  	
				     stats = new StatsHistorical();
				     stats.ReadFile(weatherHistorical.getCitta());
				    stats.methodVariance();
				    stats.methodMedia();
				    return stats;
				      
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		else if(filter.equals("forecast"))
      {  	
	      
	  	try {
			{  	
				 stats = new StatsForecast();
			     stats.ReadFile(weatherHistorical.getCitta());
				 stats.methodVariance();
				 stats.methodMedia();
				 return stats;
			      
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			}
      	}
		return null;
	}
    return null;
    }
    
    @Override
    public List<Weather> getWeathers() {
    	
    	return list;
    }

	@Override
	public Stats getStats() {
		
		return stats;
	}

	@Override
	public List<Weather> getWeather(String filter) {
		List<Weather> c = new ArrayList<Weather>();

		if(list != null)
		{
		    for(Weather w:list)
		    {
			if(w.getDate().equals(filter) || w.getWeather().equals(filter))
			{
				c.add(w);
			}
		    }
		    return c;
		}
		return null;
	}

	@Override
	public FilterUtils filterField(float filter) {
	    filterUtils.select(filter);
	    return filterUtils;
	}

	@Override
	public FilterUtils filterField(float filterMin, float filterMax) {
	    filterUtils.select(filterMin, filterMax);
	    return filterUtils;
	}
	
	@Override
	public FilterUtils getTemp(float filter, Boolean choice) {
	    return filterUtils;
	}

	@Override
	public CityForecast filterField(String city) throws FileNotFoundException {
		float c = filterUtils.ricerca(city);
		if(c != -1)
		{
			prev = new CityForecast(city, c);
			return prev;
		}
		else
			return null;
	}



	
}




