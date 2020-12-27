package com.Forecast.Forecast.Model;
/*Classe Weather con i relativi set e get 
 *@param  city contiene il nome della citta selezionata dall'utente
 *@param  date contiene la data relativa alla previsione del tempo
 *@param  weather contiene la previsione del tempo
 *@param  oggetto di temperature
 */

public class Weather {
   
     private String city;
     private String date;
     private String weather;
     private Temperature temp = null; 
     
		
     
     public Weather(String city, String date, String weather, Temperature temp) {
	 
	 super();
	 this.city = city;
	 this.date = date;
	 this.weather = weather;
	 this.temp = temp;
		
     }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }


    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    
}