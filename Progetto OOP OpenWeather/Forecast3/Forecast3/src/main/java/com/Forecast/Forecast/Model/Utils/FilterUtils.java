package com.Forecast.Forecast.Model.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Rappresenta la classe che genera l'oggetto FilterUtils,contenente una Map,utilizzando i filtri imposti dal Client  
 */
public class FilterUtils {
    private HashMap<String, Double> Previsioni = new HashMap<>();

   /**
     * Metodo che determina per ciascun'oggetto della Map, in base al filtro specificato,
     * se deve essere incluso nella risposta
     * @param String citta che viene considerato nel test
     * @param filtro Condizione del filtro
     * @return double equivalente alla differenza tra le previsioni effettuate da current e forecast
     * @throws FileNotFoundException 
     */
    public double check(String citta, double filtro) throws FileNotFoundException {
    	
    	CalcErrorThreshold cet = new CalcErrorThreshold();
    	double differenza = cet.Calcolo(citta);
    	if (differenza < filtro)
    	{
    	    return differenza;
    	}
    	else
    	    return -1;
        }
    
    /**
     * Metodo che determina per ciascun'oggetto della Map, in base al filtro specificato,
     * se deve essere incluso nella risposta
     * @param String citta che viene considerato nel test
     * @param filterMin Condizione del filtro
     * @param filterMax Condizione del filtro
     * @return double equivalente alla differenza tra le previsioni effettuate da current e forecast
     * @throws FileNotFoundException 
     */
    public double check(String citta, double filterMin, double filterMax) throws FileNotFoundException {
	
	CalcErrorThreshold cet = new CalcErrorThreshold();
	double diff = cet.Calcolo(citta);
	if (diff < filterMax && diff > filterMin)
	    return diff;
	else
	    return -1;
    }

    /**
     * Metodo che riceve il filtro e crea una Map parziale con gli
     * oggetti selezionati
     * @param filtro Campo su cui opera il filtro
     * @return un booleano se la Map è vuota 
     */
    public boolean select(double filtro) {
	HashMap<String, Double> Elenco = new HashMap<>();
	CalcErrorThreshold cet = new CalcErrorThreshold();
	try {
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\Comuni.txt")));
	    while (scan.hasNextLine()) 
	    {
	    	String citta = scan.nextLine();
	    	double diff = check(citta, filtro);
	    	if(diff != -1)
	    		Elenco.put(citta, diff);
		}
	} catch(Exception e) {
	    System.out.print(e);
	}
	Previsioni = cet.sortHashMapByValues(Elenco);
	if(Previsioni.isEmpty()) return true;
	
	return false;
	
    }
    /**
     * Metodo che riceve il filtro e crea una Map parziale con gli
     * oggetti selezionati
     * @param filtro condizione del filtro
     * @param choice se deve prendere la condizione di maggiore o minore
     * @return un booleano se la Map è vuota 
     */
    public boolean temp(double filtro, Boolean choice) {

    	HashMap<String, Double> Elenco = new HashMap<>();
    	CalcErrorThreshold cet = new CalcErrorThreshold();
    	try {
    	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\Comuni.txt")));
    	    while (scan.hasNextLine()) {

    		String citta = scan.nextLine();	
    		if (choice) {
    		    if(filtro < cet.tempMin(citta))
    			Elenco.put(citta, cet.tempMin(citta));
    		}
    		else {
    		    if(filtro > cet.tempMin(citta))
			Elenco.put(citta, cet.tempMin(citta));
    		}    
    	    }
    	} catch(Exception e) {
    	    System.out.print(e);
    	}
    	Previsioni = cet.sortHashMapByValues(Elenco);  
    	if(Previsioni.isEmpty()) return true;
    	
    	return false;
    }
    /**
     * Metodo che riceve il filtro e crea una Map parziale con gli
     * oggetti selezionati
     * @param filtroMin Campo su cui opera il filtro
     * @param filtroMax Campo su cui opera il filtro
     * @return un booleano se la Map è vuota 
     */
    public boolean select(double filtroMin, double filtroMax) {
	HashMap<String, Double> Elenco = new HashMap<>();
	CalcErrorThreshold cet = new CalcErrorThreshold();
	try {
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\Comuni.txt")));
	    while (scan.hasNextLine()) {
		String citta = scan.nextLine();
		double diff = check(citta, filtroMin, filtroMax);
		if(diff != -1) 
		    Elenco.put(citta, diff);
		}
	} catch(Exception e) {
	    System.out.print(e);
	}
	Previsioni = cet.sortHashMapByValues(Elenco);
	if(Previsioni.isEmpty()) return true;
	
	return false;
    }
    /**
     * Metodo che crea una Map  con gli
     * oggetti selezionati
     */
    public void select() {
	
	HashMap<String, Double> Elenco = new HashMap<>();
	CalcErrorThreshold cet = new CalcErrorThreshold();
	try {
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\Comuni.txt")));
	    while (scan.hasNextLine()) {
		
		String citta = scan.nextLine();	 
		Elenco.put(citta, cet.Calcolo(citta));
	    }
	} catch(Exception e) {
	    System.out.print(e);
	}
	Previsioni = cet.sortHashMapByValues(Elenco);
	
    }
  
    /**
     * Metodo che crea una Map parziale con gli
     * oggetti selezionati
     * @param city Campo su cui opera il filtro
     * @return un double che andrà a creare l'oggetto FilterUtils filtrato
     */

    public double ricerca(String citta) throws FileNotFoundException
    {
    	CalcErrorThreshold cet = new CalcErrorThreshold();
    	return cet.Calcolo(citta);
    }

    public HashMap<String, Double> getPrevisioni() {
        return Previsioni;
    }

    public void setPrevisioni(HashMap<String, Double> previsioni) {
        Previsioni = previsioni;
    }
   
}