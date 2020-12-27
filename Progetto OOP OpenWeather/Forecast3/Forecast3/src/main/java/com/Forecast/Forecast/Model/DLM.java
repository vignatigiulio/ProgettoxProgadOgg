package com.Forecast.Forecast.Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.DefaultListModel;
/*classe che verifica se la citta inserita nella gui è presente nell'elenco comuni,situato nella cartella Resources,mediante l'utilizzo 
 * di un vettore di stringhe.
 * 
 * 
 * 
 */
public class DLM {
  private DefaultListModel<String> DLM = new DefaultListModel<String>();
 
  public  void elenco(String citta) throws FileNotFoundException
  {
	  
	 this.DLM.clear();
	  Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Comuni.txt")));
		String paese = null;
		while(scan.hasNext()) 
		{
			paese = scan.nextLine();
			if(paese.equalsIgnoreCase(citta)) //Controllo se sei ha una corrispondenza completa
			{
				this.DLM.addElement(paese);
				break; //Evito la ricerca di sottostringhe in quanto ho una corrispondenza univoca
			}
			else
				for(int i = 0; i < citta.length(); i++) //Controllo lettera per lettera
					if(citta.charAt(i) != paese.charAt(i) || i == paese.length()-1) break;
					/*Una lettera non � uguale o non ho una corrispondenza completa:
					*non � il comune che sto cercando.
					*/
					else if(i == citta.length()-1) this.DLM.addElement(paese);
					//Aggiungo al vettore dei risultati il comune
		}
  }

  public String getDLMIndex(int indice) {
	return DLM.get(indice);
  }

  public void setDLM(DefaultListModel<String> dLM) {
	DLM = dLM;
  }

  public DefaultListModel<String> getDLM() {
	return DLM;
  }
  public boolean isEmpty()
  {
	if(DLM.isEmpty())return true;
	
	return false;
  }
}


