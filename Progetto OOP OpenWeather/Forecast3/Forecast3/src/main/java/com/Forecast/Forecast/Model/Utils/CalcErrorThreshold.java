package com.Forecast.Forecast.Model.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/*Rappresenta la classe che implementa il metodo per la determinazione della soglia di errore delle previsioni effettuate dal server
 * 
 * 
 */
public class CalcErrorThreshold {
	
	/**
	 * metodo che implementa il calcolo della soglia d'errore,tramite la lettura di file contenenti i valori delle temperature registrate dal server,
	 * che popolano i vettori su cui viene calcolata la soglia di errore
	 * @param citta 
	 * @return media che rappresenta la soglia di errore delle previsioni effettuate dal server
	 * @throws FileNotFoundException eccezione che viene lanciata se il file non viene trovato
	 */
	
	public float Calcolo (String citta) throws FileNotFoundException {
	    	    Vector<Double> tempC = new Vector<>();
	    	    Vector<Double> tempF = new Vector<>();
	    	    Boolean trovato = true;
	    	    for (int i = 1; i <= 4; i++)//5
	    	    {
    	    	Scanner scanC = new Scanner(new BufferedReader(new FileReader(".\\Resources\\ErrorThreshold\\WeatherCurrentDay"+i+".txt"))); 
	    	    String lettura;
	    	    while (scanC.hasNextLine() && trovato) {
	    	    	lettura = scanC.nextLine();
	    	    	while (lettura.equalsIgnoreCase(citta) && trovato) {    	    	    
	    	    	    tempC.add(Double.parseDouble(scanC.nextLine()));
	    	    	    tempC.add(Double.parseDouble(scanC.nextLine()));
	    	    	    tempC.add(Double.parseDouble(scanC.nextLine()));
	    	    	    tempC.add(Double.parseDouble(scanC.nextLine()));
	    	    	    trovato = false;
	    	    	   }
	    	    }
	    	    scanC.close();
	    	    }
	    	    for (int i = 1; i <= 4; i++)//5
	    	    {
	    	    	Scanner scanF = new Scanner(new BufferedReader(new FileReader(".\\Resources\\ErrorThreshold\\ForecastGiorno"+i+"full.txt")));
	    	    	trovato = true;
		    	    String lettura2;
		    	    while (scanF.hasNextLine() && trovato) {
			    	lettura2 = scanF.nextLine();
			    	while (lettura2.equalsIgnoreCase(citta) && trovato) {
			    	    tempF.add(Double.parseDouble(scanF.nextLine()));//uguali
			    	    tempF.add(Double.parseDouble(scanF.nextLine()));
			    	    tempF.add(Double.parseDouble(scanF.nextLine()));//uguali
			    	    tempF.add(Double.parseDouble(scanF.nextLine()));//uguali
			    	    trovato = false;
			    	    
			    	}
			    }
		    	    scanF.close();
	    	    }
	    	    Vector<Double> errori = new Vector<>();
	    	    double differenza, somma = 0;
	    	    for (int i = 0; i < tempC.size(); i++) {
	    		differenza = tempC.get(i) - tempF.get(i);
	    		errori.add(Math.abs(differenza));
	    		somma += (Math.abs(differenza));
	    	    }
	    	    float media = (float) (somma/tempC.size());
	    	    return media;
	    	}

	  
	

	
	/**
	 * Metodo che riordina una Map secondo il campo Float, con l'ausilio di un iteratore
	 * @param HashMap<String, Float> passedMap rappresenta una Map di appoggio su cui viene effettuata l'operazione di ordinamento
	 * @return LinkedHashMap<String, Float> riordinato secondo il campo Float
	 */
	public LinkedHashMap<String, Float> sortHashMapByValues(HashMap<String, Float> passedMap) {
	    	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    	    List<Float> mapValues = new ArrayList<>(passedMap.values());
	    	    Collections.sort(mapValues);
	    	    Collections.sort(mapKeys);

	    	    LinkedHashMap<String, Float> sortedMap =
	    	        new LinkedHashMap<>();

	    	    Iterator<Float> valueIt = mapValues.iterator();
	    	    while (valueIt.hasNext()) {
	    	        float val = valueIt.next();
	    	        Iterator<String> keyIt = mapKeys.iterator();

	    	        while (keyIt.hasNext()) {
	    	            String key = keyIt.next();
	    	            Float comp1 = passedMap.get(key);
	    	            Float comp2 = val;

	    	            if (comp1.equals(comp2)) {
	    	                keyIt.remove();
	    	                sortedMap.put(key, val);
	    	                break;
	    	            }
	    	        }
	    	    }
	    	    return sortedMap;
	    	}
	/*
	 * 
	 */
	public float tempMin(String citta) throws FileNotFoundException
	{
		Vector<Double> tempF = new Vector<>();
		for (int i = 1; i <= 4; i++)//5
	    {
	    	Scanner scanF = new Scanner(new BufferedReader(new FileReader(".\\Resources\\ErrorThreshold\\ForecastGiorno"+i+"full.txt")));
	    	Boolean trovato = true;
    	    String lettura2;
    	    while (scanF.hasNextLine() && trovato) {
	    	lettura2 = scanF.nextLine();
	    	while (lettura2.equalsIgnoreCase(citta) && trovato) {
	    	    tempF.add(Double.parseDouble(scanF.nextLine()));
	    	    trovato = false;
	    	}
	    }
    	//scanF.close();
	    }
		float somma = 0;
		for(int i = 0; i < tempF.size(); i++)
		{
			somma += tempF.elementAt(i);
		}
		float media = somma / tempF.size();
		return (float) (Math.floor(media*100)/100);
	}

	}

