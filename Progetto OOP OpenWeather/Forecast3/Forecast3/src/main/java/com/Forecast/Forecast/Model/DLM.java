package com.Forecast.Forecast.Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.DefaultListModel;
/**
 * Classe che si occupa di eseguire la ricerca di sottostringhe o di comuni ed inserirle nella DLM
 * 
 *
 */
public class DLM {
  private DefaultListModel<String> DLM = new DefaultListModel<String>();
 /**
  * 
  * @param citta nome da cercare. Se la stringa è nulla vengono inseriti tutti i comuni
  * @throws FileNotFoundException
  */
  public void elenco(String citta) throws FileNotFoundException
  {
	  this.DLM.clear();
	  Scanner scan = new Scanner(new BufferedReader(new FileReader(".\\Resources\\Data\\Comuni.txt")));
	  String paese = null;
	  if(citta != null)
	  {
	      while(scan.hasNext()) 
	      {
		  	paese = scan.nextLine();
			if(paese.equalsIgnoreCase(citta)) //Controllo se si ottiene una corrispondenza completa
			{
				inserisciM(paese);
				break; //Evito la ricerca di sottostringhe in quanto ho una corrispondenza univoca
			}
			else
				for(int i = 0; i < citta.length(); i++) //Controllo lettera per lettera
					if(citta.charAt(i) != paese.charAt(i) || i == paese.length()-1) break;
					/*Una lettera non è uguale o non ho una corrispondenza completa:
					*non è il comune che sto cercando.
					*/
					else if(i == citta.length()-1) inserisciM(paese);
					//Aggiungo alla lista dei risultati il comune contenuto in "paese"
	      }
	  }
	  else
	  {
		  while(scan.hasNextLine()) inserisciM(scan.nextLine());
	  }
  }
  
  public String getDLMIndex(int indice) {
	return DLM.get(indice);
	
  }


  public DefaultListModel<String> getDLM() {
	return DLM;
  }
  
  /**Metodo di verifica dei risultati ottenuti
   * 
   * @return true se la lista è vuota
   *
   */
  
  public boolean isEmpty()
  {
	if(this.DLM.isEmpty()) return true;
	
	return false;
  }
  /**Metodo che inserisce le opportune maiuscole nella lista
   *
   * @param comune città da inserire
   */
  private void inserisciM(String comune)
  {
	  char[] charArray = comune.toCharArray();
	  charArray[0] = Character.toUpperCase(charArray[0]);
	  for(int i = 1; i < charArray.length-1; i++)
	  {
		  if(!Character.isLetter(charArray[i]) && Character.isLetter(charArray[i+1]))
			  charArray[i+1] = Character.toUpperCase(charArray[i+1]);
	  }
	  String citta = new String(charArray);
	  this.DLM.addElement(citta);
  }
}


