package com.Forecast.Forecast.Model.Stats;

import java.util.Vector;


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
			return approssimazione(arr, 2);
		}
		/**Metodo che si occupa di calcolare la media aritmetica della temperatura.
		 *@Param temp è un vettore di Double contenente le temperature da calcolare
		 *@Return è il valore della media in double approssimato di due cifre dopo la virgola.
		 */
	public double methodArithmetic_averageTemperature(Vector<Double> temp) 
	{
		check(temp);
		double somma = 0, arr;
		for(int i = 0; i < temp.size(); i++)
			somma += temp.get(i);
		arr = (somma/temp.size());
		return approssimazione(arr, 2);
	}
	/**
	 * Metodo per il controllo della presenza di dati nei vettori
	 * @param temp vettore da controllare
	 */
	public void check(Vector<Double> temp)
	{
		if(temp.isEmpty()) throw new NullPointerException();
	}
	/**
	 * Metodo per l'approssimazione di cifre
	 * @param n numero da approssimare
	 * @param cifre cifre da mantenere dopo la virgola
	 * @return numero arrotondato
	 */
	public static double approssimazione(double n, double cifre)
	{
		int decimali = (int) Math.pow(10, cifre);
		return (Math.floor(n*decimali)/decimali);
	}
}
