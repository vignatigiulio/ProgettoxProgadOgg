package com.Forecast.Forecast.Model.Stats;

import java.io.FileNotFoundException;

import java.util.HashMap;


public abstract  class Stats {

	protected String citta;
	protected HashMap<String,Float> variance_temperature = new HashMap<String,Float>();
	protected HashMap<String,Float> arithmetic_average_temperature = new HashMap<String,Float>();
	
	public abstract  void ReadFile(String string) throws FileNotFoundException;

	public abstract  HashMap<String, Float> methodVariance();
	public abstract  HashMap<String, Float> methodMedia();
	
	public String getCitta() {
		return citta;
	}

	public HashMap<String, Float> getVariance_temperature() {
		return variance_temperature;
	}

	public HashMap<String, Float> getArithmetic_average_temperature() {
		return arithmetic_average_temperature;
	}

	
	



	
	


	
	
}
