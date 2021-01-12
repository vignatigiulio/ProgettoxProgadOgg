package com.Forecast.Forecast.Model.Stats;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import com.Forecast.Forecast.Model.Gui;
/*La classe si occupa di scrivere nei vettori i valori delle temperature nei vettori
 *Verranno scritti la temperatura, la percepita, la massima e la minima.
 *Al fine di una valutazione più esatta, verranno inserite le temperature previste per ogni 24 ore
 */
public class StatsForecast extends Stats {
	
	private Vector<Double> temperature = new Vector<>();
	private Vector<Double> feels_like = new Vector<>();
	private Vector<Double> temp_min = new Vector<>();
	private Vector<Double> temp_max = new Vector<>();
	private StatsMethod method = null;
	public StatsForecast()
	{
		method = new StatsMethod();
	}
	@Override
	/**Il metodo si occupa di inserire nei vettori le temperature presenti nei file di testo precedentemente creati.
	 *@Param string il nome della città
	 */
	public void ReadFile(String string)  
	{ 
	String citta=string;
		try
		{
			Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\"+ getCitta()+"ForecastStats.txt")));
			while(scan.hasNextLine())
			{
				temperature.addElement((Double.parseDouble(scan.nextLine())));
				feels_like.addElement((Double.parseDouble(scan.nextLine())));
				temp_min.addElement((Double.parseDouble(scan.nextLine())));
				temp_max.addElement((Double.parseDouble(scan.nextLine())));
			}
			
		}catch(FileNotFoundException e){
				System.out.print(e);
		}catch(Exception e){
			System.out.print(e);
		}
	}
	/**Il metodo si occupa di creare la Hashmap contenente i risultati della varianza nei giorni futuri.
	 *@Return la HashMap che verrà visualizzata.
	 */
	public HashMap<String, Float> methodVariance()
	{
		variance_temperature.put("temperature:",method.methodVarianceTemperature(temperature));
		variance_temperature.put("feels_like:",method.methodVarianceTemperature(feels_like));
		variance_temperature.put("temperature_min:",method.methodVarianceTemperature(temp_min));
		variance_temperature.put("temperature_max:",method.methodVarianceTemperature(temp_max));
		return variance_temperature;
	}
	/**Il metodo si occupa di creare la Hashmap contenente i risultati della media nei giorni futuri.
	 *@Return la HashMap che verrà visualizzata.
	 */
	public HashMap<String, Float> methodMedia()
	{
		
		arithmetic_average_temperature.put("temperature:",method.methodArithmetic_averageTemperature(temperature));
		arithmetic_average_temperature.put("feels_like:",method.methodArithmetic_averageTemperature(feels_like));
		arithmetic_average_temperature.put("temperature_min:",method.methodArithmetic_averageTemperature(temp_min));
		arithmetic_average_temperature.put("temperature_max:",method.methodArithmetic_averageTemperature(temp_max));
		return arithmetic_average_temperature;
	}

}

	
	
	
	
	
	
	


