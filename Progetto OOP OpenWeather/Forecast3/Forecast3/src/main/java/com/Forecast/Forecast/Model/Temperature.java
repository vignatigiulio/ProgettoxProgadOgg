package com.Forecast.Forecast.Model;
/*Classe Temperature con i relativi set e get 
 *@param  temp contiene la temperatura registrata dal server
 *@param  feels_like contiene la temperatura percepita
 *@param  temp_min contiene la temperatura min
 *@param  temp_max contiene la temperatura max
 */

public class Temperature {
    
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    
    public Temperature(double temp, double feels_like, double temp_min, double temp_max) {
	super();
	this.temp = temp;
	this.feels_like = feels_like;
	this.temp_min = temp_min;
	this.temp_max = temp_max;
	
    }

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

  
    
    
    
}
