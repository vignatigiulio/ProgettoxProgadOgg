package com.Forecast.Forecast.Model.Stats;

import java.io.FileNotFoundException;

import java.util.HashMap;

import com.Forecast.Forecast.Model.Data.DataForecast;

/*Classe che si occupa di creare le HashMap che verranno poi visualizzate all'utente.
 *Richiama il metodo ReadFile passando il nome del comune come parametro.
 *Esso si occuperà poi di popolare gli appositi vettori.
 *Gli HashMap saranno poi visibili grazie agli appositi getter.
 *Il comune in questione viene richiamato tramite getter
 */
public abstract class Stats {

	
	protected String citta;
	protected HashMap<String,Float> variance_temperature = new HashMap<String,Float>();
	protected HashMap<String,Float> arithmetic_average_temperature = new HashMap<String,Float>();
	public abstract void ReadFile(String string) throws FileNotFoundException;
	public abstract HashMap<String, Float> methodVariance();
	public abstract HashMap<String, Float> methodMedia();
	
	public String getCitta() {
		this.citta=DataForecast.getCitta();
		return citta;
	}

	public HashMap<String, Float> getVariance_temperature() {
		return variance_temperature;
	}

	public HashMap<String, Float> getArithmetic_average_temperature() {
		return arithmetic_average_temperature;
	}
}
