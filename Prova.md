# Spring boot Web Service

Un Web Service è un sistema software in grado di mettersi al servizio di un Client (applicazione, sito web, Postman), comunicando tramite il protocollo HTTP. </br> Un Web service consente quindi agli utenti che vi si collegano di usufruire delle funzioni che mette a disposizione. Con Spring Boot, è stato possibile creare questo software che lancia l'intera applicazione web, compreso il web server integrato.

## Introduzione <a name="intro"></a>
OPen Weather Web Service è un servizio web di REST API che offre informazioni riguardo le previsioni meteo di tutti i comuni italiani marittimi con relative statistiche. 
</br> Il software, in questione, è parte di un progetto realizzato per l'esame del corso di Programmazione ad Oggetti 2020/2021 dell'Università Politecnica delle Marche, con lo scopo di apprendere tutte le nozioni della Programmazione ad Oggetti per mezzo del linguaggio Java.

La nostra applicazione permette di richiedere mediante API REST (GET o POST) con rotte distinte:

*Restituzione dei metadati, formato JSON, delle previsioni meteo di un specifico comune scelto dal client. 
</br>*Restituzione dei metadati, formato JSON, delle previsioni meteo del comune selezionato dopo essere stato filtrato. 
</br>*Restituzione delle statistiche sui dati di uno specifico campo filtrato. 
</br>*Restituzione dei dati,formato JSON,circa la soglia di errore delle previsioni effettuate. 
note: come effettuare richieste.

## Come iniziare 

### Download 

Usando l'ide eclipse si possono seguire i seguenti passi:
- aprire eclipse, nella Show view premere il pulsante "clone a Git Repository".
- nella finestra che appare, incollare URL di questa repository nella casella URI e procedere.
- recarsi nel clone della repository che apparirà, tasto destro quindi Import Project (verificare che sia importato come progetto Maven) e procedere.
- a questo punto potete provare ad eseguire il codice, selezionando "nome_Progetto" tasto destro, "Run as" e quindi "Sprign boot App".

Ora l'aplicazione Web Service sarà attiva e in ascolto alla porta http://localhost:8080.

### Eseguire richieste

Per eseguire le richieste GET o POST si può installare un API testing, (ad esempio: Postman). 
</br>La seguente tabella mostra le richieste possibili. 

|    TIPO        |rotta                                          |descrizione                                                                                                                                      |
|----------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
|GET             |/weather                                       |restituisce le previsioni meteo del comune scelto dal cliente.                                                                                   |
|GET             |/weather/{filter}                              |restituisce le previsioni meteo del comune scelto dal cliente filtrate.







|
|GET             |/stats/{filter}                                |restituisce una statistica sulla varianza di temperatura e media della temperatura prevista e passata.                                           |
|GET             |/statsError/{filter}                           |restituisce i comuni con la relativa soglia di errore,data dalla differenza tra la temperatura prevista e quella corrente,che rientra nel filtro.|                        
|GET             |/statsTwoError/{filtermin}-{filtermax}         |restitursce i comuni con la relativa soglia di errore compresa tra i due filtri                                                                  |
|GET             |/statsErrorCity/{city}                         |restituisce la soglia di errore del relativo comune passato dal client nella rotta                                                               |
|GET             |/temp/{temp}-{choice}                          |restituisce solamente la temperatura media di tutti i comuni compresi nel filtro                                                                 |

##SVILUPPO  

### Package
<img src="https://i.postimg.cc/wjYXbXWz/packages.png" width="800"/>

### Classi
* **Package com.Forecast.Forecast.RestController**
<img src="https://i.postimg.cc/j5fNXCz2/restc.png" width="200"/>

* **Package com.Forecast.Forecast.Service**
<img src="https://i.postimg.cc/MZyVfM6r/service.png" width="200"/>

* **Package com.Forecast.Forecast.Model**
<img src="https://i.postimg.cc/50pHpvXx/model.png" width="400"/>

* **Package com.Forecast.Forecast.Model.Stats**
<img src="https://i.postimg.cc/mDZkcD8K/stats.png" width="700"/>

* **Package com.Forecast.Forecast.Model.Utils**
<img src="https://i.postimg.cc/qB5qRpnZ/utils.png" width="600"/>

* **Package com.Forecast.Forecast.Model.Exception**
<img src="https://i.postimg.cc/mgxrYVmY/exception.png" width="400"/>

* **Package com.Forecast.Forecast.Model.Data**
<img src="https://i.postimg.cc/prh28n8c/data.png" width="400"/>

* **Package com.Forecast.Forecast**
<img src="https://i.postimg.cc/RVwWbj6s/main.png" width="150"/>

### Chiamate
* **Chiamata GET /weather**
ControllerClass esegue una chiamata tramite il metodo `getArrayMetadata`, il quale inizializza un ArrayList di metadata e lo restituisce. ControllerClass trasforma quest ultimo in Json e lo ritorna al client.
<img src="https://i.postimg.cc/wM16J4nd/weather.png" width="1000"/>

* **Chiamata GET /weather/{filter}**
ControllerClass esegue una chiamata tramite il metodo `getRecords`, il quale restituisce l'intero ArrayList di Record. ControllerClass trasforma quest ultimo in Json e lo ritorna al client.
<img src="https://i.postimg.cc/5y0xLBsL/weather-filter.png" width="1000"/>

* **Chiamata * **<code>GET /stats/{filter}</code>**
ControllerClass esegue una chiamata tramite `jsonParserColumn` alla classe JsonParser, che insieme a `jsonParserOperator` effetueranno il parsing del body ricevuto in modo ciclico. Estrapolate le informazioni relative al filtraggio richiesto, verranno utilizzate da `instanceFilter` per istanziare nuovi oggetti filtro prendedoli della classi contenute nel package com.esame.util.filter. A questo punto tramite `runFilter` si potrà eseguire il filtraggio e restituire a ControllerClass l'Arraylist di Record filtrato da consegnare al Client in formato Json. 
<img src="https://i.postimg.cc/FRFmXb58/stats-filter.png" width="1000"/>

* **Chiamata GET /statsError/{filter}"**
L'arrayList di Record sul quale fare il calcolo delle statisctiche viene ottenuto nel modo spiegato nella richiesta *GET /data*.
Viene passato il nome del campo su cui si desidera effettuare la statistica a `instanceStatsCalculator`, il quale instanzia l'oggetto `StasCalculator` corretto dalle classi contenute nel package com.esame.util.statistics.
Quest'ultimo tramite il metodo `run` eseguirà il calocolo statistico che verrà incapsulato come oggetto stats, e restituito in formato Json al client
<img src="https://i.postimg.cc/v8NyC6P2/stats-Error.png" width="1000"/>

* **Chiamata GET /statsTwoError/{filterMin}-{filterMax}"**
L'arrayList di Record sul quale fare il calcolo delle statisctiche viene ottenuto nel modo spiegato nella richiesta *GET /data*.
Viene passato il nome del campo su cui si desidera effettuare la statistica a `instanceStatsCalculator`, il quale instanzia l'oggetto `StasCalculator` corretto dalle classi contenute nel package com.esame.util.statistics.
Quest'ultimo tramite il metodo `run` eseguirà il calocolo statistico che verrà incapsulato come oggetto stats, e restituito in formato Json al client
<img src="https://i.postimg.cc/rFrBbZRz/stats-Two-Error.png" width="1000"/>

* **Chiamata GET /statsErrorCity/{city}**
L'arrayList di Record sul quale calcolare le statisctiche viene ottenuto nel modo spiegato nella richiesta *POST /data*.
Il calcolo statistico viene eseguito come spiegato nella richiesta *GET /stats?field="nome"*
<img src="https://i.postimg.cc/kXWn8gcj/stats-Error-City.png" width="1000"/>

* **Chiamata GET /temp/{temp}-{choice}**
L'arrayList di Record sul quale calcolare le statisctiche viene ottenuto nel modo spiegato nella richiesta *POST /data*.
Il calcolo statistico viene eseguito come spiegato nella richiesta *GET /stats?field="nome"*
<img src="https://i.postimg.cc/3r1pZk6S/temp.png" width="1000"/>

## Softwere utilizzati

* [Eclipse](https://www.eclipse.org/) - ambiente di sviluppo integrato
* [Spring Boot](https://spring.io/projects/spring-boot) - framework per  sviluppo applicazioni Java
* [Maven](https://maven.apache.org/) - strumento di gestione di progetti

## Autori

* **Matteo Losa** - [GitHub](https://github.com/losamatteo)
* **Giulio Vignati** - [GitHub](https://github.com/vignatigiulio)
* **Andrea Faccenda** - [GitHub](https://github.com/Andreafaccenda)
