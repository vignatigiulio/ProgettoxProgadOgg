package com.Forecast.Forecast.Model.Data;

import org.json.simple.JSONObject;

/** Interfaccia che richiama i metodi per 
 * gestire le chiamate all'API e la scrittura
 * su file di testo
 */

public interface Data {
    
    /** Questo metodo effettua la richiesta all'API e
	 *  richiama il metodo JsonObject()
	 */	
    void callApi();
    
    /** Metodo che crea un nuovo file e stampa i valori
	 * registrati nei vettori riempiti
	 */
    	void saveFile();
    	
    	/** Metodo che preleva i dati di interesse dall'API ed
    	 *  effettua il parsing jn Json dei dati prelevati dall'API
    	 * @param obj è il JSONObject dove viene effettuato il parsing
    	 */
    	void JsonObject(JSONObject obj);
   }


