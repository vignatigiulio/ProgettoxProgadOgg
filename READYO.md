                                                                Open Weather Web Service

Un Web Service è un sistema software in grado di mettersi al servizio di un Client (applicazione, sito web, Postman), comunicando tramite il protocollo HTTP. Un Web service consente quindi agli utenti che vi si collegano di usufruire delle funzioni che mette a disposizione. Con Spring Boot, è stato possibile creare questo software che lancia l'intera applicazione web, compreso il web server integrato.

Introduzione
OPen Weather Web Service è un servizio web di REST API che offre informazioni riguardo le previsioni meteo di tutti i comuni italiani marittimi con relative statistiche. Il software, in questione, è parte di un progetto realizzato per l'esame del corso di Programmazione ad Oggetti 2020/2021 dell'Università Politecnica delle Marche, 
con lo scopo di apprendere tutte le nozioni della Programmazione ad Oggetti per mezzo del linguaggio Java.

La fonte dei dati distribuiti è:
  https://openweathermap.org/current 
  https://openweathermap.org/forecast5 
  https://openweathermap.org/history

Di seguito è possibile leggere la consegna:
<OBIETTIVO>:  Uno studente vorrebbe scegliere una località marittima italiana per le sue vacanze estive. Implementare un sistema per studiare le temperature di varie città, scelte dall'utente. Occorre basarsi su dati storici, 
dati attuali e predizioni dei giorni futuri di quelle città. Da questi dati si ricaveranno delle statistiche. Inoltre, occorre salvare i dati attuali per un paio di settimane,
in modo tale da verificare se le predizioni siano affidabili. L'utente potrà scegliere le città da studiare, utilizzando anche una sottostringa del nome (Es. ricercare tutte le città che iniziano con "A*").
Occorre quindi crearsi una lista predefinita coi nomi delle città marittime italiane.

<STATS E FILTRI>: Statistiche dei mesi estivi dell'anno precedente, riguardanti valori minimi, massimi, media e varianza delle temperature reali e percepite. Filtraggio delle statistiche cambiando la finestra temporale (uno o più anni precedenti).
Generare statistiche sulla quantità di previsioni azzeccate, in base ad una soglia di errore, ed in base ai giorni di predizione (da 1 a 5 giorni successivi). Filtraggio modificando la soglia di errore.

La nostra applicazione permette di richiedere mediante API REST (GET) con rotte distinte:

Restituzione dei metadati, formato JSON, delle previsioni meteo di un specifico comune scelto dal client.
Restituzione dei metadati, formato JSON, delle previsioni meteo del comune selezionato dopo essere stato filtrato. 
Restituzione delle statistiche sui dati di uno specifico campo filtrato.
Restituzione dei dati,formato JSON,circa la soglia di errore delle previsioni effettuate.
note: come effettuare richieste.

Come iniziare
Download
Usando l'ide eclipse si possono seguire i seguenti passi:

aprire eclipse, nella Show view premere il pulsante "clone a Git Repository".
nella finestra che appare, incollare URL di questa repository nella casella URI e procedere.
recarsi nel clone della repository che apparirà, tasto destro quindi Import Project (verificare che sia importato come progetto Maven) e procedere.
a questo punto potete provare ad eseguire il codice, selezionando "nome_Progetto" tasto destro, "Run as" e quindi "Sprign boot App".
In alternativa su linux senza l'utilizzo del ide eclipse si puo scaricare il file Zip ed estrarlo, da terminale recarsi nella directory, digitare il comando mvn clean install se il BUILD avrà suceccesso, all'interno del progetto nella directory target si troveranno i file compilati. Per eseguire il programma java -jar target/nomeProgettoCompilato.jar (oppure mvn spring-boot:run).

Ora l'aplicazione Web Service sarà attiva e in ascolto alla porta http://localhost:8080.

Eseguire richieste
Per eseguire le richieste GET o POST si può installare un API testing, (ad esempio: Postman). La seguente tabella mostra le richieste possibili.

TIPO	    rotta	                                        descrizione
GET	      /weather	                                    restituisce le previsioni meteo del comune scelto dal cliente.
GET	      /weather/{filter}	                            restituisce le previsioni meteo del comune scelto dal cliente filtrate.
GET	      /stats/{filter}                               restituisce una statistica sulla varianza di temperatura e media della temperatura prevista e passata.
GET       /statsError/{filter}                          restituisce i comuni con la relativa soglia di errore,data dalla differenza tra la temperatura prevista e quella corrente,che rientra nel filtro.
GET       /statsTwoError/{filtermin}-{filtermax}        restitursce i comuni con la relativa soglia di errore compresa tra i due filtri
GET       /statsErrorCity/{city}                        restituisce la soglia di errore del relativo comune passato dal client nella rotta
GET       /temp/{temp}-{choice}                         restituisce solamente la temperatura media di tutti i comuni compresi nel filtro

Sviluppo
Package


Classi
Package com.esame.controller


Package com.esame.database


Package com.esame.model


Package com.esame.service


Package com.esame.exception


Package com.esame.util.filter


Package com.esame.util.statistics


Chiamate
Chiamata GET /weather RestController esegue una chiamata tramite il metodo getWeathers, prende un  ArrayList di metadati,inizializzato nella classe DataHistorical e lo restituisce.Il  RestController trasforma quest ultimo in Json e lo ritorna al client.

Chiamata GET/weather/{filter} 

Chiamata GET /data ControllerClass esegue una chiamata tramite il metodo getRecords, il quale restituisce l'intero ArrayList di Record. ControllerClass trasforma quest ultimo in Json e lo ritorna al client.


Chiamata POST /data ControllerClass esegue una chiamata tramite jsonParserColumn alla classe JsonParser, che insieme a jsonParserOperator effetueranno il parsing del body ricevuto in modo ciclico. Estrapolate le informazioni relative al filtraggio richiesto, verranno utilizzate da instanceFilter per istanziare nuovi oggetti filtro prendedoli della classi contenute nel package com.esame.util.filter. A questo punto tramite runFilter si potrà eseguire il filtraggio e restituire a ControllerClass l'Arraylist di Record filtrato da consegnare al Client in formato Json.


Chiamata GET /stats?field="nome" L'arrayList di Record sul quale fare il calcolo delle statisctiche viene ottenuto nel modo spiegato nella richiesta GET /data. Viene passato il nome del campo su cui si desidera effettuare la statistica a instanceStatsCalculator, il quale instanzia l'oggetto StasCalculator corretto dalle classi contenute nel package com.esame.util.statistics. Quest'ultimo tramite il metodo run eseguirà il calocolo statistico che verrà incapsulato come oggetto stats, e restituito in formato Json al client


Chiamata POST /stats?field="nome" L'arrayList di Record sul quale calcolare le statisctiche viene ottenuto nel modo spiegato nella richiesta POST /data. Il calcolo statistico viene eseguito come spiegato nella richiesta GET /stats?field="nome"


Softwere utilizzati
Eclipse - ambiente di sviluppo integrato
Spring Boot - framework per sviluppo applicazioni Java
Maven - strumento di gestione di progetti
Autori
Losa Matteo  - GitHub
Vignati Giulio - GitHub
Faccenda Andrea - GitHub
