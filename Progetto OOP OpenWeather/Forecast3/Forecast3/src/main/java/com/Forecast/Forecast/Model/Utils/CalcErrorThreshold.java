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

import com.Forecast.Forecast.Model.Stats.StatsMethod;

/*Rappresenta la classe che implementa il metodo per la determinazione della soglia di errore delle previsioni effettuate dal server
 * 
 * 
 */
public class CalcErrorThreshold {
	private final int profondita_dati = 5;
	
	/**
	 * metodo per la lettura delle temperature 
	 * @param scan file da leggere
	 * @param citta comune da studiare
	 * @param vector vettore dove vengono inserite le temperature
	 */
	private void fileReading(Scanner scan, String citta, Vector<Double> vector) {	    
	    String comune;
	    Boolean finelettura = true;
	    while (scan.hasNextLine() && finelettura) {
		comune = scan.nextLine();
		while (comune.equalsIgnoreCase(citta) && finelettura) {    	    	    
		    vector.add(Double.parseDouble(scan.nextLine()));
		    vector.add(Double.parseDouble(scan.nextLine()));
		    vector.add(Double.parseDouble(scan.nextLine()));
		    vector.add(Double.parseDouble(scan.nextLine()));
		    finelettura = false;
		}
	    }
	    scan.close();
	}
	
	/**
	 * metodo che implementa il calcolo della soglia d'errore,tramite la lettura di file contenenti i valori delle temperature registrate dal server,
	 * che popolano i vettori su cui viene calcolata la soglia di errore
	 * @param citta 
	 * @return media che rappresenta la soglia di errore delle previsioni effettuate dal server
	 * @throws FileNotFoundException eccezione che viene lanciata se il file non viene trovato
	 */
	
	public double Calcolo (String citta) throws FileNotFoundException {
	    	    Vector<Double> tempC = new Vector<>();
	    	    Vector<Double> tempF = new Vector<>();
	    	    for (int i = 1; i <= profondita_dati; i++)
	    	    {
	    	    	Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\ErrorThreshold\\WeatherCurrent"+i+".txt")));
	    	    	Scanner scan2 = new Scanner(new BufferedReader(new FileReader(".\\Resources\\ErrorThreshold\\ForecastGiorno"+i+".txt")));
	    	    	fileReading(scan, citta, tempC);
	    	    	fileReading(scan2, citta, tempF);
	    	    }
	    	    if(tempC.isEmpty()) return -1;
	    	    double differenza, somma = 0;
	    	    for (int i = 0; i < tempC.size(); i++) 
	    	    {
	    	    	differenza = tempC.get(i) - tempF.get(i);
	    	    	somma += (Math.abs(differenza));
	    	    }
	    	    double media = (somma/tempC.size());
	    	    return StatsMethod.approssimazione(media, 2);
	    	}

	
	/**
	 * Metodo che riordina una Map secondo il campo Double, con l'ausilio di un iteratore
	 * @param HashMap<String, Double> passedMap rappresenta una Map di appoggio su cui viene effettuata l'operazione di ordinamento
	 * @return LinkedHashMap<String, Double> riordinato secondo il campo Double
	 */
	public LinkedHashMap<String, Double> sortHashMapByValues(HashMap<String, Double> passedMap) {
	    	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    	    List<Double> mapValues = new ArrayList<>(passedMap.values());
	    	    Collections.sort(mapValues);
	    	    Collections.sort(mapKeys);
	    	    LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
	    	    Iterator<Double> valueIt = mapValues.iterator();
	    	    while (valueIt.hasNext()) 
	    	    {
	    	    	double val = valueIt.next();
	    	        Iterator<String> keyIt = mapKeys.iterator();
	    	        while (keyIt.hasNext()) 
	    	        {
	    	            String key = keyIt.next();
	    	            Double comp1 = passedMap.get(key);
	    	            Double comp2 = val;
	    	            if (comp1.equals(comp2)) 
	    	            {
	    	                keyIt.remove();
	    	                sortedMap.put(key, val);
	    	                break;
	    	            }
	    	        }
	    	    }
	    	    return sortedMap;
	    	}
	/**
	 * Metodo per calcolo temperatura media
	 * @param citta comune da calcolare
	 * @return media della temperatura nei 5 giorni precedenti
	 * @throws FileNotFoundException
	 */
	public double tempAvg(String citta) throws FileNotFoundException
	{
	    Vector<Double> tempF = new Vector<>();
	    for (int i = 1; i <= profondita_dati; i++)
	    {
	    	Scanner scanF = new Scanner(new BufferedReader(new FileReader(".\\Resources\\ErrorThreshold\\ForecastGiorno"+i+".txt")));
	    	Boolean finelettura = true;
	    	String comune;
	    	while (scanF.hasNextLine() && finelettura) {
	    	    comune = scanF.nextLine();
	    	    while (comune.equalsIgnoreCase(citta) && finelettura) {
	    		tempF.add(Double.parseDouble(scanF.nextLine()));
	    		finelettura = false;
	    	}
	    }
    	    scanF.close();
	    }
		double somma = 0, media;
		for(int i = 0; i < tempF.size(); i++)
		{
		    somma += tempF.elementAt(i);
		}
		media = somma / tempF.size();
		return StatsMethod.approssimazione(media, 2);
	}

}

