package com.Forecast.Forecast.Model.Utils;

/**
 * Classe CityForecast con i relativi set e get dei campi della classe 
 *@param  city contiene il nome della città passato come filtro
 *@param  error_threshold contiene l' errore della previsione effettuata dal server
 */
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

