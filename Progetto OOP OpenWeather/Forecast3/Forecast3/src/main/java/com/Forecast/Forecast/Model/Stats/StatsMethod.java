package com.Forecast.Forecast.Model.Stats;

import java.util.Vector;

public class StatsMethod 
	{
		/**Metodo che si occupa di calcolare la varianza della temperatura.
		 *@Param temp è un vettore di Double contenente le temperature da calcolare
		 *@Return è il valore della varianza approssimato di due cifre dopo la virgola.
		 */
		public float methodVarianceTemperature(Vector<Double> temp) 
		{
			double somma = 0;
			for(int i = 0; i < temp.size(); i++)
				somma += Math.pow(temp.get(i)-methodArithmetic_averageTemperature(temp), 2);
			float arr = (float)(somma/(double)temp.size());
			return (float) (Math.floor(arr*100)/100);
		}
		/**Metodo che si occupa di calcolare la media aritmetica della temperatura.
		 *@Param temp è un vettore di Double contenente le temperature da calcolare
		 *@Return è il valore della media in float approssimato di due cifre dopo la virgola.
		 */
	public float methodArithmetic_averageTemperature(Vector<Double> temp) 
	{
		double somma = 0;
		for(int i = 0; i < temp.size(); i++)
			somma += temp.get(i);
		float arr = (float)(somma/(double)temp.size());
		return (float) (Math.floor(arr*100)/100);
	}

	
}
