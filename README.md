#  Open Weather Web Service
 <div align="center">
  <br><br>
  <img src="https://i.postimg.cc/dV6DkjmQ/Open-Weather-Map.png" alt="Currency_logo" width="260" height=”69”></img>
  <br>
  <h4>Versione attuale: 1.1.3</h4><br>
  <p>
    <a href="#intro">Introduzione</a>&nbsp•
    <a href="#inizio">Come iniziare</a>&nbsp•
    <a href="#diagram">Diagramma casi d'uso</a>&nbsp•
    <a href="#richieste">Eseguire richieste</a>&nbsp•
    <a href="#sviluppo">Sviluppo </a>&nbsp•
    <a href="#chiamate">Chiamate </a>&nbsp•
    <a href="#riconoscimenti">Autori</a> 
  </p>
</div><br><br>
Un Web Service è un sistema software in grado di mettersi al servizio di un Client (applicazione, sito web, Postman), comunicando tramite il protocollo HTTP. </br> Un Web service consente quindi agli utenti che vi si collegano di usufruire delle funzioni che mette a disposizione. Con Spring Boot, è stato possibile creare questo software che lancia l'intera applicazione web, compreso il web server integrato.

## Consegna progetto esame
#### Api reference
-https://openweathermap.org/current </br>
-https://openweathermap.org/forecast5 </br>
-https://openweathermap.org/history </br>

#### Obiettivo progetto esame  </br>
Uno studente vorrebbe scegliere una località marittima italiana per le sue vacanze estive. Implementare un sistema per studiare le temperature di varie città, scelte dall'utente. Occorre basarsi su dati storici, dati attuali e predizioni dei giorni futuri di quelle città. Da questi dati si ricaveranno delle statistiche. Inoltre, occorre salvare i dati attuali per un paio di settimane, in modo tale da verificare se le predizioni siano affidabili. L'utente potrà scegliere le città da studiare, utilizzando anche una sottostringa del nome (Es. ricercare tutte le città che iniziano con "A*").</br> Occorre quindi crearsi una lista predefinita coi nomi delle città marittime italiane
<STATS E FILTRI>: Statistiche dei mesi estivi dell'anno precedente, riguardanti valori minimi, massimi, media e varianza delle temperature reali e percepite. Filtraggio delle statistiche cambiando la finestra temporale (uno o più anni precedenti). </br>Generare statistiche sulla quantità di previsioni azzeccate, in base ad una soglia di errore, ed in base ai giorni di predizione (da 1 a 5 giorni successivi). Filtraggio modificando la soglia di errore.

## Introduzione <a name="intro"></a>
OPen Weather Web Service è un servizio web di REST API che offre informazioni riguardo le previsioni meteo di tutti i comuni italiani marittimi con relative statistiche. 
</br> Il software, in questione, è parte di un progetto realizzato per l'esame del corso di Programmazione ad Oggetti 2020/2021 dell'Università Politecnica delle Marche, con lo scopo di apprendere tutte le nozioni della Programmazione ad Oggetti per mezzo del linguaggio Java.

La nostra applicazione permette di richiedere mediante API REST (GET o POST) con rotte distinte:

*Restituzione dei metadati, formato JSON, delle previsioni meteo di un specifico comune scelto dal client. 
</br>*Restituzione dei metadati, formato JSON, delle previsioni meteo del comune selezionato dopo essere stato filtrato. 
</br>*Restituzione delle statistiche sui dati di uno specifico campo filtrato. 
</br>*Restituzione dei dati,formato JSON,circa la soglia di errore delle previsioni effettuate. 
note: come effettuare richieste.
## Diagramma casi d'uso <a name="diagram"></a>
<img src="https://i.postimg.cc/Nj1ScPRs/uml1.png" width="250"></img>
</br><img src="https://i.postimg.cc/mktKWLyJ/uml2.png" width="600"></img>
<img src="https://i.postimg.cc/9fCSFtBG/uml3.png" width="600"></img>
## Come iniziare <a name="inizio"></a>

### Download 

Usando l'ide eclipse si possono seguire i seguenti passi:
- aprire eclipse, nella Show view premere il pulsante "clone a Git Repository".
- nella finestra che appare, incollare URL di questa repository nella casella URI e procedere.
- recarsi nel clone della repository che apparirà, tasto destro quindi Import Project (verificare che sia importato come progetto Maven) e procedere.
- a questo punto potete provare ad eseguire il codice, selezionando "Progetto OOP OpenWeather" tasto destro, "Run as" e quindi "Spring boot App".
- per utlizzare correttamente occorre aggiungere alla classpath la libreria json-simple-1.1.1.jar nel link https://code.google.com/archive/p/json-simple/downloads
- all'avvio del programma apparirà un interfaccia  
### Graphical user interface
<img src="https://i.postimg.cc/0QcPMHY8/gui.png" width="400"/>
Il form presenta una textBox per l'inserimento del comune (o di eventuali sottostringhe) da cercare. I risultati saranno poi mostrati nella lista a fianco.
I bottoni sono 5: </br>
1. La lente di ingrandimento per la ricerca affianco alla textBox (attivabile anche premendo il tasto invio) </br>
2. Il tasto "Seleziona". Dopo aver cercato la città ed aver selezionato il comune desiderato dalla lista, il programma visualizzerà una finestra di conferma.
   Premendo "Sì", il form verrà chiuso ed il server caricato con la città selezionata, altrimenti si avvierà da capo.</br>
3. Il pulsante "Cancella". Questo si occupa di ripulire la lista, ripulire la textBox ed evidenzia immediatamente la casella di ricerca.</br>
4. Il pulsante "Tutti i comuni" scrive sulla lista l'intero database di comuni disponibili. Spetterà poi all'utente di selezionare un comune e confermare.</br>
5. Il pulsante "Avvia il server senza comune" serve a, come detto dal testo, eseguire il server senza specificare alcun comune. Ciò è consigliato se si
   vuole visualizzare statistiche generali.</br>


Ora l'applicazione Web Service sarà attiva e in ascolto alla porta http://localhost:8080.

### Eseguire richieste <a name="richieste"></a>

Per eseguire le richieste GET o POST si può installare un API testing, (ad esempio: Postman). 
</br>La seguente tabella mostra le richieste possibili. 

|    TIPO        |rotta                                          |descrizione                                                                                                                                      |
|----------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
|GET             |/weather                                       |restituisce le previsioni meteo del comune scelto dal cliente.                                                                                   |
|GET             |/weather/{filter}                              |restituisce le previsioni meteo del comune scelto dal cliente filtrate.                                                                          |
|GET             |/stats/{filter}                                |restituisce una statistica sulla varianza di temperatura e media della temperatura prevista e passata.                                           |
|GET             |/statsError/{filter}                           |restituisce i comuni con la relativa soglia di errore,data dalla differenza tra la temperatura prevista e quella corrente,che rientra nel filtro.|                        
|GET             |/statsTwoError/{filtermin}-{filtermax}         |restitursce i comuni con la relativa soglia di errore compresa tra i due filtri                                                                  |
|GET             |/statsErrorCity/{city}                         |restituisce la soglia di errore del relativo comune passato dal client nella rotta                                                               |
|GET             |/temp/{temp}-{choice}                          |restituisce solamente la temperatura media di tutti i comuni compresi nel filtro                                                                 |

## Sviluppo <a name="sviluppo"></a>

### Package
<img src="https://i.postimg.cc/wjYXbXWz/packages.png" width="800"/>

### Classi
* **Package com.Forecast.Forecast.RestController**
<img src="https://i.postimg.cc/J46nst1f/restcontroller.png" width="200"/>

* **Package com.Forecast.Forecast.Service**
<img src="https://i.postimg.cc/pVkVSynm/service.png" width="200"/>

* **Package com.Forecast.Forecast.Model**
<img src="https://i.postimg.cc/9MCV8jmP/model.png" width="400"/>

* **Package com.Forecast.Forecast.Model.Stats**
<img src="https://i.postimg.cc/CMvYNtWD/stats.png" width="700"/>

* **Package com.Forecast.Forecast.Model.Utils**
<img src="https://i.postimg.cc/1tvxvnYP/utils.png" width="600"/>

* **Package com.Forecast.Forecast.Model.Exception**
<img src="https://i.postimg.cc/NFF3SsWX/exception.png" width="400"/>

* **Package com.Forecast.Forecast.Model.Data**
<img src="https://i.postimg.cc/NfW3Cwby/data.png" width="400"/>

* **Package com.Forecast.Forecast**
<img src="https://i.postimg.cc/jSxFFdPS/main.gif" width="150"/>

### Chiamate <a name="chiamate"></a>
* **Chiamata <code>GET /weather</code>**
il RestController esegue una chiamata tramite il metodo `getWeather`,restituisce le informazioni attuali del meteo quali:
*Nome del comune;
*Data di riferimento e fascia oraria di riferimento;
*Breve descrizione del cielo;
*Temperatura (gradi Celsius);
*Temperatura percepita (gradi Celsius);
*Temperatura minima (gradi Celsius);
*Temperatura massima (gradi Celsius);
Le previsioni coprono un massimo di 4 giorni e 21 ore per via della versione free della API. Tramite la rotta
successiva ` GET /weather/{filter}`, il programma potrà restituire risultati più capillari della precedente rotta.
<img src="https://i.postimg.cc/wM16J4nd/weather.png" width="1000"/>

* **Chiamata <code>GET /weather/{filter}</code>**
La variabile filter è una variabile di tipo stringa nella quale posso inserire una data specifica della quale
voglio le previsioni o la descrizione del cielo.
Inserendo una data del formato “yyyy-MM-dd hh:mm:ss” verrà restituita la previsione riguardante quel
periodo gia effettuata nella rotta precedente ` GET /weather` . Se non viene inserito un orario preciso 
il programma arrotonderà automaticamente l'orario e restituirà quello più vicino possibile.
Inserendo invece la descrizione del cielo (es. clear sky) vengono restituite tutte le previsioni di
“clear sky” della città precedentemente selezionata nel massimo lasso di tempo concesso dalla API. In caso
di un inserimento errato da parte dell’utente, verrà lanciata un’eccezione.
<img src="https://i.postimg.cc/5y0xLBsL/weather-filter.png" width="1000"/>

* **Chiamata <code>GET /stats/{filter}</code>**
il RestController tramite la chiamata ` getStats`,restituisce una statistica.
Prevede una variabile di tipo stringa “filter” che conterrà o “historical” o “forecast”. La stringa forecast
prevede di calcolare la varianza e la media aritmetica dei dati riguardanti la temperatura (temperatura
attuale, percepita, minima e massima) dei 5 giorni successivi (tutti alla stessa ora, onde evitare sbalzi
esagerati) della città scelta in precedenza. Scrivendo invece historical, il programma riprodurrà gli stessi dati
di prima ma lasciando nulli i campi di temperatura minima e massima, in quanto questi dati non sono
fornidi dall’` API`.
<img src="https://i.postimg.cc/QCpkxynS/stats-filter.png" width="1000"/>

* **Chiamata <code>GET /statsError/{filter}</code>**
Contiene la variabile ` error threshold` di tipo float. Il programma costruirà un’Hashmap contenente i nomi
dei comuni e la soglia di errore delle previsioni. Quest’ultima è calcolata sulla media della differenza tra le
temperature previste e le temperature poi effettive di quei comuni, già precedentemente salvati in un
totale di dieci file di testo presenti in .\Resources\ErrorThreshold. I dati hanno una profondità di cinque
giorni. Il server restituirà solamente quei comuni, insieme alla rispettiva soglia, i quali hanno una media di
errore minore del numero immesso dall’utente. Genererà eccezione se non si immette un numero o si
immette un numero troppo basso o negativo.
`la rotta potrebbe impiegare abbastanza tempo poichè deve effettuare operazioni su oltre 600 comuni italiani,tuttavia eliminando qualche comune il server impiegherà molto meno tempo,ma non sarà completa di tutti i comuni`
<img src="https://i.postimg.cc/6pHV3RYs/stats-Error.png" width="1000"/>

* **Chiamata <code>GET /statsTwoError/{filterMin}-{filterMax}</code>**
Contiene le due variabili di tipo float {error min} e {error max}. L’utente dovrà inserire due cifre separate da
un trattino, ed il programma, come effettuato nella rotta precendente ` GET /statsError/{filter}` , restituirà solamente i comuni i
quali hanno una soglia di errore compresa tra i due valori. Non è importante l'ordine di inserimento degli errori 
in quanto il programma riconoscerà l'errore minore e quello maggiore.
`la rotta potrebbe impiegare abbastanza tempo poichè deve effettuare operazioni su oltre 600 comuni italiani,tuttavia eliminando qualche comune il server impiegherà molto meno tempo,ma non sarà completa di tutti i comuni`
<img src="https://i.postimg.cc/L4B15L8y/stats-Two-Errors.png" width="1000"/>

* **Chiamata <code>GET /statsErrorCity/{city}</code>**
Contiene la variabile di tipo stringa ` city` con il nome di un comune scelto dall’utente. La rotta restituirà la
soglia di errore del comune scelto. Se il comune non esiste, verrà generata un’eccezione.
<img src="https://i.postimg.cc/g2fJN20X/stats-Error-City.png" width="1000"/>

* **Chiamata <code>GET /temp/{temp}-{choice}</code>**
Contiene la variabile ` temp` di tipo float e la variabile ` choice` di tipo stringa. L’utente dovrà inserire una
temperatura e, dopo il trattino, la parola “under” o “over”. Queste si riferiscono al valore in float: la parola
“under” intende i valori minori del numero precedentemente inserito; invece “over” andrà a filtrare le
temperature medie maggiori di ` temp`.
</br>Il programma calcolerà la media della temperatura dei 5 giorni
precedenti di tutti i comuni. Successivamente, restituirà comuni e rispettivi valori i quali soddisfano la
condizione dettata dal cliente.
`la rotta potrebbe impiegare abbastanza tempo poichè deve effettuare operazioni su oltre 600 comuni italiani,tuttavia eliminando qualche comune il server impiegherà molto meno tempo,ma non sarà completa di tutti i comuni`
<img src="https://i.postimg.cc/tgQqFvrF/temp.png" width="1000"/>

## Softwere utilizzati

* [Eclipse](https://www.eclipse.org/) - ambiente di sviluppo integrato
* [Spring Boot](https://spring.io/projects/spring-boot) - framework per  sviluppo applicazioni Java
* [Maven](https://maven.apache.org/) - strumento di gestione di progetti

## Autori <a name="riconoscimenti"></a>

* **Matteo Losa** - [GitHub](https://github.com/losamatteo)
* **Giulio Vignati** - [GitHub](https://github.com/vignatigiulio)
* **Andrea Faccenda** - [GitHub](https://github.com/Andreafaccenda)
