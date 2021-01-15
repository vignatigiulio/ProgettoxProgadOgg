package com.Forecast.Forecast;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.springframework.boot.SpringApplication;
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
		Gui gui = new Gui();
		File f = new File(".\\Resources\\Data");
		gui.deleteDirectory(f);
		gui.inizialize();
		gui.insertInvio();
		gui.insertCancel();
		gui.btnGetSelected.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{ 
					int conferma;
					do
					{
						conferma = 0;
						gui.insertSelected();
						conferma = gui.msg();
						gui.pulisci();
					}while(conferma  == 1);
					DataForecast.setapiKey("8a4ee3b7f356511b37b0082bbd8580e2");
					SpringApplication.run(ForecastApplication.class, args);
				
				}catch(Exception e) 
				{
				}
			}
		});
	}
}
