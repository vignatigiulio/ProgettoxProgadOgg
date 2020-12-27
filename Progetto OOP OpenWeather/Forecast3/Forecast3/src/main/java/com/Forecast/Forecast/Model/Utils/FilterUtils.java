package com.Forecast.Forecast.Model.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Rappresenta la classe che genera l'oggetto FilterUtils,contenente una Map,utilizzando i filtri imposti dal Client  
 */
public class FilterUtils {
    private HashMap<String, Float> Previsioni = new HashMap<>();
   
   
    /**
     * Metodo che determina per ciascun'oggetto della Map, in base al filtro specificato,
     * se deve essere incluso nella risposta
     * @param String citta che viene considerato nel test
     * @param filtro Condizione del filtro
     * @return Un booleano che determina se l'oggetto deve essere tenuto o meno
     * @throws FileNotFoundException 
     */
    public boolean check(String citta, float filtro) throws FileNotFoundException {
	
	CalcErrorThreshold cet = new CalcErrorThreshold();
	if (cet.Calcolo(citta) < filtro)
	    return true;
	else
	    return false;
    }
    
    /**
     * Metodo che determina per ciascun'oggetto della Map, in base al filtro specificato,
     * se deve essere incluso nella risposta
     * @param String citta che viene considerato nel test
     * @param filterMin Condizione del filtro
     * @param filterMax Condizione del filtro
     * @return Un booleano che determina se l'oggetto deve essere tenuto o meno
     * @throws FileNotFoundException 
     */
    public boolean check(String citta, float filterMin, float filterMax) throws FileNotFoundException {
	
	CalcErrorThreshold cet = new CalcErrorThreshold();
	if (cet.Calcolo(citta) < filterMax && cet.Calcolo(citta) > filterMin)
	    return true;
	else
	    return false;
    }

    /**
     * Metodo che riceve il filtro e crea una Map parziale con gli
     * oggetti selezionati
     * @param filtro Campo su cui opera il filtro
     */
    public void select(float filtro) {
	HashMap<String, Float> Elenco = new HashMap<>();
	CalcErrorThreshold cet = new CalcErrorThreshold();
	try {
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Comuni.txt")));
	    while (scan.hasNextLine()) {
		
		String citta = scan.nextLine();	 
		if(check(citta, filtro))
		    Elenco.put(citta, cet.Calcolo(citta));
		}
	} catch(Exception e) {
	    System.out.print(e);
	}
	Previsioni = cet.sortHashMapByValues(Elenco);
	
    }
    /**
     * Metodo che riceve il filtro e crea una Map parziale con gli
     * oggetti selezionati
     * @param filtroMin Campo su cui opera il filtro
     * @param filtroMax Campo su cui opera il filtro
     */
    public void select(float filtroMin, float filtroMax) {
	HashMap<String, Float> Elenco = new HashMap<>();
	CalcErrorThreshold cet = new CalcErrorThreshold();
	try {
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Comuni.txt")));
	    while (scan.hasNextLine()) {
		
		String citta = scan.nextLine();	 
		if(check(citta, filtroMin, filtroMax)) 
		    Elenco.put(citta, cet.Calcolo(citta));
		}
	} catch(Exception e) {
	    System.out.print(e);
	}
	Previsioni = cet.sortHashMapByValues(Elenco);
	
    }
    /**
     * Metodo che crea una Map  con gli
     * oggetti selezionati
     */
    public void select() {
	
	HashMap<String, Float> Elenco = new HashMap<>();
	CalcErrorThreshold cet = new CalcErrorThreshold();
	try {
	    Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Comuni.txt")));
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
     * @return un float che andrà a creare l'oggetto FilterUtils filtrato
     */
    public float searchCity(String city) {
		city.toLowerCase();
		 for (Entry<String, Float> entry : Previsioni.entrySet())  
	        	if(entry.getKey().equals(city))
	        	{
	        		return entry.getValue();
	        	}
	     return -1;   	
	}

    public HashMap<String, Float> getPrevisioni() {
        return Previsioni;
    }

    public void setPrevisioni(HashMap<String, Float> previsioni) {
        Previsioni = previsioni;
    }
    
    

}

