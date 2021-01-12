package com.Forecast.Forecast.Model.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.Forecast.Forecast.Model.Gui;
import com.Forecast.Forecast.Model.Temperature;
import com.Forecast.Forecast.Model.Weather;

/** Classe che include i metodi necessari per la lettura e il parsing
 *  dei dati prelevati dalla API con relativi getters e setters 
 *  e implementa l'interfaccia Data
 */

public class DataHistorical implements Data {
    
    private double lat;
	private double lon;
	private double temp;
	private double feels_like;
	private String citta;
    private long dt;
    public String chiave;
    

	public DataHistorical() 
	{
		
		DataForecast json = new DataForecast();
		this.chiave = DataForecast.getApiKey();
		json.callApi();
		json.saveFile();
		this.citta = DataForecast.getCitta();
		this.lon=json.getLon();
		this.lat = json.getLat();
	}
	
 public void callApi()
 {
   try {
	       URLConnection openConnection = new URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat="+ lat 
       +"&lon="+ lon +"&dt="+ this.dt + "&appid="+this.chiave+"&units=metric").openConnection();
	    
	    	
	    InputStream in = openConnection.getInputStream();
		
	    String data = "";
	    String line = "";
	    
	    try {
		
		InputStreamReader inR = new InputStreamReader(in);
		BufferedReader buf = new BufferedReader(inR);
				  
		while ((line = buf.readLine()) != null) {
		    data+= line;
		}
			    	
	    } finally { 
		in.close();
	    }	
		    

	    		JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
	    		JsonObject(obj);
	    		
	} catch (IOException | ParseException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}	 
 }
 	public void JsonObject(JSONObject obj)
 		{
	 JSONObject current = (JSONObject) obj.get("current");
		this.temp=(Double.parseDouble(String.valueOf(current.get("temp"))));
		this.feels_like=(Double.parseDouble(String.valueOf(current.get("feels_like"))));
 		}
 	
	public void saveFile()
	{
	

		  try {
		    
		    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(".\\Resources\\Data\\"+ this.citta +"Historical.txt",true)));
					
		    {
		    	out.println("giorno"+ this.citta);
		    	out.println(this.dt);
		    	out.println(this.temp);
		    	out.println(this.feels_like);
        }
	
		    out.close();
		  }catch(IOException e) {
			  System.out.println(e);
		  }
		  
	}
	public List<Weather> fillList()
	{
    	String lettura = null;
    	String name;
    	List<Weather> lista = new ArrayList<Weather>();    
    	try {
    	    
    	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\" + this.citta + "Forecast.txt")));
    	    name = scan.nextLine();
    	    
    	    while(scan.hasNext()) {
    		
    		String date = null;
    		String weather = null;
    		Vector<Double> temperature = new Vector<Double>();
    		date = scan.nextLine();
    		weather = scan.nextLine();
    		Temperature temp = null;
    		for(int i = 0; i < 4; i++, lettura = "") {
    		    
    		    lettura = scan.nextLine();
    		    temperature.add(Double.parseDouble(lettura));
    		}
    		
    		for (int j = 0; j < temperature.size(); j += 4) {		    
    		    temp = new Temperature(temperature.get(j), temperature.get(j+1), temperature.get(j+2), temperature.get(j+3));
    		    Weather tempo = new Weather(name, date, weather, temp);
    		    lista.add(tempo);
    			}
    	    }
    	   // Gui.delete(this.citta + "Forecast.txt");
    	   
    	 } catch(IOException e) {
    	     System.out.print(e);
    	 } catch(Exception e) {
    	     System.out.print(e);
    	 }
		return lista;
    }

	public String getCitta() {
		return citta;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(Long epochFormat) {
		this.dt = epochFormat;
	}




	

}