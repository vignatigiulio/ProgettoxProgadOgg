package com.Forecast.Forecast.Model.Data;

import java.io.BufferedReader;




import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/** Classe che include i metodi necessari per la lettura e il parsing
 *  dei dati prelevati dalla API con relativi getters e setters 
 *  e implementa l'interfaccia Data
 */

 public class DataForecast implements Data{
    
    private Vector<Double> temperature = null;
    private Vector<String> weather = null;
    private Vector<Double> feels_like = null;
    private Vector<Double> temp_min  = null;
    private Vector<Double> temp_max = null;
    private Vector<String> date = null;
    private static String citta = "";
    private double lat;
    private double lon;
    public static String apiKey;

    
    public DataForecast() {
    	
    	temperature = new Vector<Double>();
    	date = new Vector<String>();
    	feels_like = new Vector<Double>();
    	temp_min = new Vector<Double>();
    	temp_max = new Vector<Double>();
    	weather = new Vector<String>();
    }
   
   
    @Override
	public void callApi() {
		try {
			
		    if (DataForecast.citta == null) System.exit(0);
		    URLConnection openConnection = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + DataForecast.citta +",IT&units=metric&appid="+apiKey).openConnection();
		    	
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
    	JSONArray jsonObject = (JSONArray) obj.get("list");
	    JSONObject org = (JSONObject) obj.get("city");
	    JSONObject cord = (JSONObject) org.get("coord");
	    this.lat = (double) cord.get("lat");
	    this.lon = (double) cord.get("lon");
	    JSONObject jo  = null;
	    
	    for(int i=0;i<jsonObject.size();i++) {
		
		jo = (JSONObject) jsonObject.get(i);	
		date.add((String) jo.get("dt_txt"));
		JSONArray array = (JSONArray) jo.get("weather");
		
		for(int j = 0; j < array.size();j++) {
		    
		    JSONObject a = (JSONObject) array.get(j);
		    this.weather.add(String.valueOf(a.get("description")));
			    	
		}
		
		JSONObject json = (JSONObject) jo.get("main");

	        this.temperature.add(Double.parseDouble(String.valueOf(json.get("temp"))));
	    	this.feels_like.add (Double.parseDouble(String.valueOf(json.get("feels_like"))));
	    	this.temp_min.add(Double.parseDouble(String.valueOf(json.get("temp_min"))));
	    	this.temp_max.add(Double.parseDouble(String.valueOf(json.get("temp_max"))));
    	
	    }
    }

	@Override
	public void saveFile() {
		try {
		    
	    	PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(".\\Resources\\Data\\"+ DataForecast.citta +"Forecast.txt")));
	    	PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(".\\Resources\\Data\\"+ DataForecast.citta +"ForecastStats.txt")));
				
	    {
		file_output.println(DataForecast.citta);
		for(int i = 0; i < temperature.size();i++) {
		    file_output.println(date.get(i));
		    file_output.println(weather.get(i));
		    file_output.println(temperature.get(i));
		    file_output.println(feels_like.get(i));	
		    file_output.println(temp_min.get(i));	
		    file_output.println(temp_max.get(i));
		    output.println(temperature.get(i));
		    output.println(feels_like.get(i));	
		    output.println(temp_min.get(i));	
		    output.println(temp_max.get(i));
		    
		   
		}
	    }
			
			
	    file_output.close();
	    output.close();
		
	    System.out.println("File salvato!");
	    System.out.println("File stats salvato!");

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    System.out.println(e);
	}
	
	}

    public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	public static  String getCitta() {
        return citta;
    }
	

	public static void setCitta(String city) {
		citta = city;
	}


	public static void setapiKey(String chiave)
	{
		apiKey = chiave;
	}

	public static String getApiKey() {
		return apiKey;
	}

	

}



