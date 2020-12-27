package com.Forecast.Forecast;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.Forecast.Forecast.Model.DLM;
import com.Forecast.Forecast.Model.Gui;
import com.Forecast.Forecast.Model.Data.DataForecast;
@RestController
@SpringBootApplication
/*Main del programma
 * Viene richiamata la Gui situata nel package Model,che permette di inserire una citta
 *Viene assegnata la chiave API tramite setter presente nella classe DataForecast
 *Successivamente viene avviata la SpringApplication,solo dopo aver inserito la citta e assegnato la chiave per le chiamate dell'api.
*/
public class ForecastApplication {
	
	public static void main(String[] args) {
		
		
		Gui gui = new Gui();
			gui.inizialize();
			gui.insertInvio();
			gui.insertCancel();
			gui.btnGetSelected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
					DataForecast.setapiKey("80da379aa6764f47ae8d83559daeb6b4");
					SpringApplication.run(ForecastApplication.class, args);
					
			}catch(Exception e) {
			
			}
			}
			
		});
	
			
	}
}
