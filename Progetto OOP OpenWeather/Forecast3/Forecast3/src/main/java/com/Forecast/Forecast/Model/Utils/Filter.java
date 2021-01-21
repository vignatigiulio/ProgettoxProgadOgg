package com.Forecast.Forecast.Model.Utils;

import java.io.FileNotFoundException;

/**
 * Intefaccia che mette a disposizione la dichiarazione del metodo per filtrare i dati di una classe
 * @param <E> Tipo dell'oggetto che verrà filtrato
 * @param <T> Tipo del valore che verrà utilizzato per filtrare l'oggetto
 */
public interface Filter<E,T> {
    /**
     * Metodo astratto la cui impementazione andrà a filtrare la Map di elementi rispettivamente a un filtro deciso dal client
     * @param filtro campo che deve essere filtrato
     * @return un oggetto FilterUtils,che contiene una Map, degli elementi filtrati mediante i parametri specificati dal client
     */
    abstract FilterUtils filterField(float filter);
    /**
     * Metodo astratto la cui impementazione andrà a filtrare la Map di elementi rispettivamente a un filtro deciso dal client
     * @param filterMin campo che deve essere filtrato
     * @param filterMax campo che deve essere filtrato
     * @return un oggetto FilterUtils,che contiene una Map, degli elementi compresi tra i due filtri,mediante i parametri specificati, 
     */
    abstract FilterUtils filterField(float filterMin, float filterMax);
    /**
     * Metodo astratto la cui implementazione andrà a filtrare la Map di elementi rispettivamente a un filtro deciso dal client
     * @param city campo che deve essere filtrato
     * @return un oggetto CityForecast,che contiene una Map, della città con la relativa soglia di errore della previsione effettuata dal server
     */
    abstract CityForecast filterField(String city) throws FileNotFoundException;

    /**
     * Metodo astratto la cui implementazione andrà a filtrare la Map di elementi rispettivamente a un filtro deciso dal client
     * @param temp campo che deve essere filtrato
     * @param booleano dal quale deciso se prendere tutti i risultati al di sopra o al di sotto
     * @return un oggetto FilterUtils,che contiene una Map, della città con la relativa soglia di errore della previsione effettuata dal server
     */
    abstract FilterUtils getTemp(float temp, Boolean choice);

   
    
}

