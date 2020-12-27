package com.Forecast.Forecast.Model.Stats;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class StatsHistorical  extends Stats{
	private   Vector<Double> temperature = new Vector<>();
	private   Vector<Double> feels_like = new Vector<>();
	private StatsMethod method = null;
	public StatsHistorical()
	{
		method = new StatsMethod();
	}
	
	@Override
	public void ReadFile(String string) {
		 
		 citta=string;

		 try {
			    
			    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\" + this.citta + "Historical.txt")));
				
			    String str = null;
			    
			    while(scan.hasNextLine()) 
			    {	
			    	str=scan.nextLine();
			    	str=scan.nextLine();
			    	str=scan.nextLine();
			    	temperature.add(Double.parseDouble(str)); //indice i numeri pari indice dispari feels_like
			    	str="";
			    	str=scan.nextLine();
			    	feels_like.add(Double.parseDouble(str));
			    }
			    scan.close();
			
			} catch (IOException e) {
			    System.out.print(e);
			} catch (Exception e) {
			    System.out.print(e);
			}
	
		
	}

	public HashMap<String, Float> methodVariance()
	{
		variance_temperature.put("temperature:",method.methodVarianceTemperature(temperature));
		variance_temperature.put("feels_like:",method.methodVarianceTemperature(feels_like));
		variance_temperature.put("temperature_min:",null);
		variance_temperature.put("temperature_max:",null);
		return variance_temperature;
	}
	public HashMap<String, Float> methodMedia()
	{
		
		arithmetic_average_temperature.put("temperature:",method.methodArithmetic_averageTemperature(temperature));
		arithmetic_average_temperature.put("feels_like:",method.methodArithmetic_averageTemperature(feels_like));
		arithmetic_average_temperature.put("temperature_min:",null);
		arithmetic_average_temperature.put("temperature_max:",null);
		return arithmetic_average_temperature;
	}

	
}
