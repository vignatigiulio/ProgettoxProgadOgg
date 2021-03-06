package com.Forecast.Forecast.RestController;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.Forecast.Forecast.Model.Weather;
import com.Forecast.Forecast.Model.Exceptions.ApiRequestException;
import com.Forecast.Forecast.Model.Exceptions.FilterIllegalArgumentException;
import com.Forecast.Forecast.Model.Stats.Stats;
import com.Forecast.Forecast.Model.Utils.CityForecast;
import com.Forecast.Forecast.Model.Utils.FilterUtils;
import com.Forecast.Forecast.Service.ForecastService;
/** Rappresenta la classe che gestisce tutte le chiamate al Server 
* 	permesse al Client.
*/
@RestController
public class ForecastRestController {
	
	@Autowired
	private ForecastService forecastService;
	private final String choice1 = "over";
	private final String choice2 = "under";
	
	/**
	 * Eccezione invocata quando non viene trovata l'entità serializzata richiesta
	 * dall'utente.
	*/
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class EntityNotFoundException extends RuntimeException {
	 
	     private static final long serialVersionUID = 1L;
	 
	    public EntityNotFoundException(String msg) {
	        super(msg);
	    }
	 
	    public EntityNotFoundException() {
	        super();
	    }
	}
	 
	
	 /**
		 * Risponde all richiesta GET / Weather 
		 * @throws EntityNotFoundException Eccezione invocata quando non viene trovata l'entità serializzata richiesta
		 * @return ArrayList di oggetti Weather
		 */
	@GetMapping("/weather") 
	public List<Weather> getWeathers() throws EntityNotFoundException
	{
		List<Weather> c = forecastService.getWeathers();
		if(c == null) throw new ApiRequestException("Il server è stato avviato senza città. Riavviare.");
		return c;
	}
	
	/**
	 * Risponde all richiesta GET / Weather
	 * @param data è rappresenta il campo date di Weather nel formato (yy-mm-dd hh:mm:ss) sul quale si vuole effettuare la ricerca.
	 * @throws  ApiRequestException(data) se vengono generati errori di parametro non valido in ingresso al filtro.
	 *  @throws EntityNotFoundException Eccezione invocata quando non viene trovata l'entità serializzata richiesta
	 * @return  oggetto Weather relativo
	 */
	@GetMapping("/weather/{filter}")
	public List<Weather> getWeather(@PathVariable("filter")String filter) throws EntityNotFoundException
	{
		List<Weather> w= forecastService.getWeather(filter);
		if (w.isEmpty()) throw new ApiRequestException("parametro non supportato o server avviato senza città. Riprovare.");
			return w;
	
	}
	
	/**
	 * Risponde all richiesta GET / Stats
	 * @param filter  rappresenta il campo nome della statistica sulla quale si vuole effettuare la ricerca.
	 * @throws  ApiRequestException(filter) se vengono generati errori di parametro non valido in ingresso al filtro.
	 * @throws EntityNotFoundException Eccezione invocata quando non viene trovata l'entità serializzata richiesta
	 * @return  oggetto Stats che contiene le statistiche richieste
	 */
	@GetMapping("/stats/{filter}")
	public Stats getStats(@PathVariable("filter") String filter) throws EntityNotFoundException
	{
		if( this.forecastService.statsFilter(filter) == null) throw new ApiRequestException("Il server è stato avviato senza città. Riavviare."); 
		
		
		return this.forecastService.statsFilter(filter);
		
	}
	/**
	 * Risponde all richiesta GET / Stats
	 * @param   error threshold rappresenta il campo filter della classe FilterUtils  che effettua una statistica relativa alle previsioni
	 *  sulla quale si vuole effettuare la ricerca.
	 * @throws  ApiRequestException(filter) se vengono generati errori di parametro non valido in ingresso al filtro.
	 * @throws EntityNotFoundException Eccezione invocata quando non viene trovata l'entità serializzata richiesta
	 * @return  oggetto FilterUtils contente un hashMap che contiene le statistiche richieste filtrate
	 */
	@GetMapping("/statsError/{error threshold}")
	public FilterUtils getStatsForecast(@PathVariable("error threshold") double filter) 
	{
		if(filter <= 0) throw new ApiRequestException(filter);
		if(this.forecastService.filterField(filter)==null) throw new FilterIllegalArgumentException("soglia di errore troppo piccola");
		else
		return this.forecastService.filterField(filter);

    }
	/**
	 * Risponde all richiesta GET / Stats
	 * @param  error min rappresenta il campo filterMin della classe FilterUtils classe che effettua una statistica relativa 
	 * alle previsioni sulla quale si vuole effettuare la ricerca.
	 * @param  error max rappresenta il campo filterMax della classe FilterUtils classe  che effettua una statistica relativa 
	 * alle previsioni sulla quale si vuole effettuare la ricerca. 
	 * @throws  ApiRequestException(filter) se vengono generati errori di parametro non valido in ingresso al filtro.
	 * @throws EntityNotFoundException Eccezione invocata quando non viene trovata l'entità serializzata richiesta
	 * @return  oggetto FilterUtils contente un hashMap che contiene le statistiche richieste filtrate
	 */
	
	@GetMapping("/statsTwoError/{error min}-{error max}") 
	public FilterUtils getStatsForecast(@PathVariable("error min") double filterMin, @PathVariable("error max") double filterMax)
			throws EntityNotFoundException
	{
		if(filterMin < 0 || filterMax <= 0) throw new ApiRequestException(filterMin, filterMax);
		if(filterMin > filterMax)
		{
			double appoggio=filterMax;
			filterMax=filterMin;
			filterMin=appoggio;
		}
		if(this.forecastService.filterField(filterMin, filterMax) == null) throw new FilterIllegalArgumentException("soglia di errore troppo piccola"); 
		else
		return this.forecastService.filterField(filterMin, filterMax);

	}
	/**
	 * Risponde all richiesta GET / Stats
	 * @param city rappresenta il campo city della classe CityForecast classe che eredita la statistica della relativa città data in ingresso dal client  relativa alle previsioni 
	 * sulla quale si vuole effettuare la ricerca.
	 * @throws  ApiRequestException(filter) se vengono generati errori di parametro non valido in ingresso al filtro.
	 * @throws EntityNotFoundException Eccezione invocata quando non viene trovata l'entità serializzata richiesta
	 * @return  oggetto CityForecast che contiene le statistiche richieste filtrate
	 */
	@GetMapping("/statsErrorCity/{city}")
	public CityForecast getErrorCity2(@PathVariable("city") String city) throws EntityNotFoundException, FileNotFoundException
	{
		city=city.trim().replaceAll("\\s{2,}", " ");//toglie i spazi in una stringa 
		if((this.forecastService.filterField(city)) == null) throw new ApiRequestException(city);
		else
			return this.forecastService.filterField(city);

    }
	/**
	 * Risponde alla richiesta GET /temp
	 * @param temp float della temperatura media da prendere come riferimento
	 * @param stringa che può essere solo "over" o "under"
	 * @return oggetto CityForecast che contiene le statistiche richieste filtrate
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/temp/{temp}-{choice}")
	public FilterUtils getTemp(@PathVariable("temp") double temp, @PathVariable("choice") String choice) throws EntityNotFoundException
	{
	    if (choice.equalsIgnoreCase(choice1))
		 {
	    	if(this.forecastService.getTemp(temp, true)== null) throw new FilterIllegalArgumentException("temperatura troppo grande"); 
	    	else
	    		return this.forecastService.getTemp(temp, true);
		 }
	    
	    else if (choice.equalsIgnoreCase(choice2)) 
	    {
	    	if(this.forecastService.getTemp(temp, false) == null) throw new FilterIllegalArgumentException("temperatura troppo piccola"); 
	    return this.forecastService.getTemp(temp, false);
	    }
		else
		throw new ApiRequestException(choice);
	}	

}








