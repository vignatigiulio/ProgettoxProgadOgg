package com.Forecast.Forecast.Model.Utils;

/**
 * Intefaccia che mette a disposizione la dichiarazione del metodo per filtrare i dati di una classe
 * @param <E> Tipo dell'oggetto che verrà filtrato
 * @param <T> Tipo del valore che verrà utilizzato per filtrare l'oggetto
 */
public interface Filter<E,T> {
    /**
     * Metodo astratto la cui impementazione andrà a filtrare la collezione di elementi rispettivamente a un filtro deciso dal client
     * @param filtro campo che deve essere filtrato
     * @return una Map degli elementi fltrata mediante i parametri specificati
     */
    abstract FilterUtils filterField(float filter);
    /**
     * Metodo astratto la cui impementazione andrà a filtrare la collezione di elementi rispettivamente a un filtro deciso dal client
     * @param filterMin campo che deve essere filtrato
     * @param filterMax campo che deve essere filtrato
     * @return una Map degli elementi compresi tra i due filtri,mediante i parametri specificati, imposti 
     */
    abstract FilterUtils filterField(float filterMin, float filterMax);
    /**
     * Metodo astratto la cui impementazione andrà a filtrare la collezione di elementi rispettivamente a un filtro deciso dal client
     * @param city campo che deve essere filtrato
     * @return una Map della città con la relativa soglia di errore della previsione
     */
    abstract CityForecast filterField(String city);
}
