package com.Forecast.Forecast.Model.Stats;

import java.util.Vector;

import com.Forecast.Forecast.Model.Exceptions.ApiRequestException;

public class StatsMethod 
	{
		/**Metodo che si occupa di calcolare la varianza della temperatura.
		 *@Param temp è un vettore di Double contenente le temperature da calcolare
		 *@Return è il valore della varianza approssimato di due cifre dopo la virgola.
		 */
		public double methodVarianceTemperature(Vector<Double> temp) 
		{
			check(temp);
			double somma = 0, arr;
			for(int i = 0; i < temp.size(); i++)
				somma += Math.pow(temp.get(i)-methodArithmetic_averageTemperature(temp), 2);
			arr = (somma/(double)temp.size());
			return (Math.floor(arr*100)/100);
		}
		/**Metodo che si occupa di calcolare la media aritmetica della temperatura.
		 *@Param temp è un vettore di Double contenente le temperature da calcolare
		 *@Return è il valore della media in float approssimato di due cifre dopo la virgola.
		 */
	public double methodArithmetic_averageTemperature(Vector<Double> temp) 
	{
		check(temp);
		double somma = 0, arr;
		for(int i = 0; i < temp.size(); i++)
			somma += temp.get(i);
		arr = (somma/temp.size());
		return (Math.floor(arr*100)/100);
	}
	public void check(Vector<Double> temp)
	{
		if(temp.isEmpty()) throw new NullPointerException();
	}

	
}
