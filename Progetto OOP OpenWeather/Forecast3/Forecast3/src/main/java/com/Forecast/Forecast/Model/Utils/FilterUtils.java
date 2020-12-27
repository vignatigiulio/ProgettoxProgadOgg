package com.Forecast.Forecast.Model.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class FilterUtils {
    private HashMap<String, Float> Previsioni = new HashMap<>();
    /**
     * Metodo che determina per ciscun'oggetto della collezione, in base al filtro specificato,
     * se deve essere incluso nella risposta
     * @param value Oggetto che viene considerato nel test
     * @param operator Condizione del filtro
     * @param th Oggetti che caratterizzano il filtro
     * @return Un booleano che determina se l'oggetto deve essere tenuto o meno
     * @throws FileNotFoundException 
     */
/*    public static boolean check(Object value, String operator, Object... th) {
        if (th.length == 1 && th[0] instanceof Number && value instanceof Number) {
            Double thC = ((Number)th[0]).doubleValue();
            Double valuec = ((Number)value).doubleValue();
            if (operator.equals("$eq"))
                return value.equals(th[0]);
            else if (operator.equals("$not"))
                return !value.equals(th[0]);
            else if (operator.equals("$gt"))
                return valuec > thC;
            else if (operator.equals("$lt"))
                return valuec < thC;
        } else if(th.length == 1 && th[0] instanceof String && value instanceof String) {
            if(operator.equals("$eq") || operator.equals("$in")) return value.equals(th[0]);
            else if(operator.equals("$not") || operator.equals("$nin")) return !value.equals(th[0]);
        } else if(th.length > 1) {
            if (operator.equals("$bt")) {
                if(th.length == 2 && th[0] instanceof Number && th[1] instanceof Number) {
                    Double min = ((Number)th[0]).doubleValue();
                    Double max = ((Number)th[1]).doubleValue();
                    Double valuec = ((Number)value).doubleValue();
                    return valuec >= min && valuec <= max;
                }
            }
            else if (operator.equals("$in"))
                return Arrays.asList(th).contains(value);
            else if (operator.equals("$nin"))
                return !Arrays.asList(th).contains(value);
        }
        return false;
    } */
    
    public boolean check(String citta, float filtro) throws FileNotFoundException {
	
	CalcErrorThreshold cet = new CalcErrorThreshold();
	if (cet.Calcolo(citta) < filtro)
	    return true;
	else
	    return false;
    }
    
    public boolean check(String citta, float filterMin, float filterMax) throws FileNotFoundException {
	
	CalcErrorThreshold cet = new CalcErrorThreshold();
	if (cet.Calcolo(citta) < filterMax && cet.Calcolo(citta) > filterMin)
	    return true;
	else
	    return false;
    }

    /**
     * Metodo che riceve l'intera collezione di oggetti ed il filtro e restituisce una collezione parziale con gli
     * oggetti selezionati
     * @param src L'intera collezione di oggetti
     * @param fieldName Campo su cui opera il filtro
     * @param operator Condizione del filtro
     * @param value Oggetti che caratterizzano il filtro
     * @return Collezione risultante
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
