# Spring boot Web Service

Un Web Service è un sistema software in grado di mettersi al servizio di un Client (applicazione, sito web, Postman), comunicando tramite il protocollo HTTP. </br> Un Web service consente quindi agli utenti che vi si collegano di usufruire delle funzioni che mette a disposizione. Con Spring Boot, è stato possibile creare questo software che lancia l'intera applicazione web, compreso il web server integrato.

## Introduzione <a name="intro"></a>
OPen Weather Web Service è un servizio web di REST API che offre informazioni riguardo le previsioni meteo di tutti i comuni italiani marittimi con relative statistiche. 
</br> Il software, in questione, è parte di un progetto realizzato per l'esame del corso di Programmazione ad Oggetti 2020/2021 dell'Università Politecnica delle Marche, con lo scopo di apprendere tutte le nozioni della Programmazione ad Oggetti per mezzo del linguaggio Java.

La nostra applicazione permette di richiedere mediante API REST (GET o POST) con rotte distinte:

*Restituzione dei metadati, formato JSON, delle previsioni meteo di un specifico comune scelto dal client. 
*Restituzione dei metadati, formato JSON, delle previsioni meteo del comune selezionato dopo essere stato filtrato. 
*Restituzione delle statistiche sui dati di uno specifico campo filtrato. 
*Restituzione dei dati,formato JSON,circa la soglia di errore delle previsioni effettuate. 
note: come effettuare richieste..

## Come iniziare 

### Download 

Usando l'ide eclipse si possono seguire i seguenti passi:
- aprire eclipse, nella Show view premere il pulsante "clone a Git Repository".
- nella finestra che appare, incollare URL di questa repository nella casella URI e procedere.
- recarsi nel clone della repository che apparirà, tasto destro quindi Import Project (verificare che sia importato come progetto Maven) e procedere.
- a questo punto potete provare ad eseguire il codice, selezionando "nome_Progetto" tasto destro, "Run as" e quindi "Sprign boot App".

In alternativa su linux senza l'utilizzo del ide eclipse si puo scaricare il file Zip ed estrarlo, da terminale recarsi nella directory, digitare il comando `mvn clean install` se il BUILD avrà suceccesso, all'interno del progetto nella directory target si troveranno i file compilati. Per eseguire il programma `java -jar target/nomeProgettoCompilato.jar` (oppure `mvn spring-boot:run`).

Ora l'aplicazione Web Service sarà attiva e in ascolto alla porta http://localhost:8080.

### Eseguire richieste

Per eseguire le richieste GET o POST si può installare un API testing, (ad esempio: Postman). 
La seguente tabella mostra le richieste possibili. 

##TIPO rotta descrizione 
|    TIPO        |rotta                                          |descrizione                                                                                                                                      |
|----------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
|GET             |/weather                                       |restituisce le previsioni meteo del comune scelto dal cliente.                                                                                   |
|GET             |/weather/{filter}                              |restituisce le previsioni meteo del comune scelto dal cliente filtrate.                                                                          |
|GET             |/stats/{filter}                                |restituisce una statistica sulla varianza di temperatura e media della temperatura prevista e passata.                                           |
|GET             |/statsError/{filter}                           |restituisce i comuni con la relativa soglia di errore,data dalla differenza tra la temperatura prevista e quella corrente,che rientra nel filtro.|                        
|GET             |/statsTwoError/{filtermin}-{filtermax}         |restitursce i comuni con la relativa soglia di errore compresa tra i due filtri                                                                  |
|GET             |/statsErrorCity/{city}                         |restituisce la soglia di errore del relativo comune passato dal client nella rotta                                                               |
|GET             |/temp/{temp}-{choice}                          |restituisce solamente la temperatura media di tutti i comuni compresi nel filtro                                                                 |

