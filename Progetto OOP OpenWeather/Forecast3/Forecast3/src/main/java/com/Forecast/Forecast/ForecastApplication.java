package com.Forecast.Forecast;


import java.io.File;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.Forecast.Forecast.Model.Gui;
import com.Forecast.Forecast.Model.Data.DataForecast;

@RestController
@SpringBootApplication
/*Main del programma
 *Viene richiamata la Gui situata nel package Model, che permette di inserire una citta e trasmetterla tramite setter
 *Viene assegnata la chiave API tramite setter presente nella classe DataForecast
 *Viene avviata la SpringApplication.
*/
public class ForecastApplication {
	
	public static void main(String[] args) 
	{
		DataForecast.setCitta("1");
		Gui gui = new Gui();
		File f = new File(".\\Resources\\Data");
		gui.deleteDirectory(f);
		gui.inizialize();
		gui.insertInvio();
		gui.insertCancel();
		gui.insertSelected();
		gui.insertNull();
		//DataForecast.setapiKey("8a4ee3b7f356511b37b0082bbd8580e2");
		//SpringApplication.run(ForecastApplication.class, args);
	}
}
