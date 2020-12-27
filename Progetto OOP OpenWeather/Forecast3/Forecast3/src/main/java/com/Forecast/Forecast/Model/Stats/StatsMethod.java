package com.Forecast.Forecast.Model.Stats;

import java.util.Vector;

public class StatsMethod {

	
	public float methodVarianceTemperature(Vector<Double> temp) {
		double somma = 0;
		 for(int i = 0; i < temp.size(); i++)
			somma += Math.pow(temp.get(i)-methodArithmetic_averageTemperature(temp), 2);
		   return (float)(somma/(double)temp.size());// da convertire
	}

	

	
	public float methodArithmetic_averageTemperature(Vector<Double> temp) {
		double somma = 0;
		for(int i = 0; i < temp.size(); i++)
			somma += temp.get(i);
		return  (float)(somma/(double)temp.size()); // da convertire
	
	}



}
