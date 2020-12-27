package com.Forecast.Forecast.Service;

import org.springframework.stereotype.Service;

import com.Forecast.Forecast.Model.JsonForecast;
import com.Forecast.Forecast.Model.Temperature;
import com.Forecast.Forecast.Model.WeatherForecast;
import com.Forecast.Forecast.Model.WeatherHistorical;
import com.Forecast.Forecast.Model.Exceptions.clsEccezioni;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

@Service
public class ForecastServiceImp implements ForecastService {
    
    List<WeatherForecast> list = null;
    WeatherHistorical weatherHistorical = new WeatherHistorical();
    
    public ForecastServiceImp() throws clsEccezioni {
	
    	list = new ArrayList<WeatherForecast>();
	
	String lettura = null;
	String name;
	    
	try {
	    
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\" + weatherHistorical.getCitta() + "Forecast.txt")));
	    name = scan.nextLine();
	    
	    while(scan.hasNext()) {
		
		String date = null;
		String weather = null;
		Vector<Double> temperature = new Vector<Double>();
		date = scan.nextLine();
		weather = scan.nextLine();
		Temperature temp = null;
		for(int i = 0; i < 5; i++, lettura = "") {
		    
		    lettura = scan.nextLine();
		    temperature.add(Double.parseDouble(lettura));
		}
		
		for (int j = 0; j < temperature.size(); j += 5) {		    
		    temp = new Temperature(temperature.get(j), temperature.get(j+1), temperature.get(j+2), temperature.get(j+3), temperature.get(j+4));
		    WeatherForecast tempo = new WeatherForecast(name, date, weather, temp);
		    list.add(tempo);
		}
	    }
	    
	 } catch(IOException e) {
	     System.out.print(e);
	 } catch(Exception e) {
	     System.out.print(e);
	 }
	
    }
    
    @Override
    public List<WeatherForecast> getWeather() {	  
    	return list;
    } 

}
