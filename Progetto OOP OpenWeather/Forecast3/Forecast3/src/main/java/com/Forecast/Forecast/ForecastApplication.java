package com.Forecast.Forecast;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
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
import com.Forecast.Forecast.Model.Data.DataForecast;
import com.Forecast.Forecast.Model.Exceptions.ApiRequestException;

@RestController
@SpringBootApplication
/**Main del programma
 *Viene creata una GUI che permette di inserire una citta e trasmetterla tramite setter
 *Viene assegnata la chiave API tramite setter presente nella classe DataForecast
 *Viene avviata la SpringApplication.
*/
public class ForecastApplication {
	
	private static JFrame frame;
	private static JButton btnInvio;
	private static JButton btnTuttiComuni;
	private static JButton btnCancel;
	private static JButton btnNull;
	private static JTextField textString;
	private static JLabel lblAdd1;
	private static JButton btnGetSelected;
	private static JScrollPane scrollPane;
	private static JScrollPane scrollPane_1;
	private static JList<String> list ;
	private static JComponent lblNewLabel_2;
	private static JLabel lblNewLabel_3;
	private static JLabel lblNewLabel_5;
	private static DLM city = null;
	
	private static void inizializzazione()
	{
		city = new DLM();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 627, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("OpenWeather");
		frame.getContentPane().setLayout(null);
		
		btnInvio = new JButton("");
		btnInvio.setIcon(new ImageIcon(".\\Resources\\immagine\\Immagine.png"));
		btnInvio.setBounds(264, 66, 39, 26);
		frame.getContentPane().add(btnInvio);
		
		btnCancel = new JButton("Cancella");
		btnCancel.setBounds(108, 120, 94, 20);
		frame.getContentPane().add(btnCancel);
		
		btnNull = new JButton("Avvia il server senza comune");
		btnNull.setBounds(108, 164, 200, 20);
		frame.getContentPane().add(btnNull);
		
		btnTuttiComuni = new JButton("Tutti i comuni");
		btnTuttiComuni.setBounds(108, 142, 147, 20);
		frame.getContentPane().add(btnTuttiComuni);
		
		textString = new JTextField();
		textString.setToolTipText("Puoi inserire anche una sottostringa!");
		textString.setBounds(108, 66, 150, 26);
		frame.getContentPane().add(textString);
		textString.setColumns(10);
		
		btnGetSelected = new JButton("Seleziona");
		btnGetSelected.setBounds(108, 98, 94, 20);
		frame.getContentPane().add(btnGetSelected);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(454, 10, 2, 666);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(350, 74, 150, 250);
		frame.getContentPane().add(scrollPane_1);
		
		list = new JList<>();
		scrollPane_1.setViewportView(list);
		
		lblAdd1 = new JLabel("Cerca per Previsioni per localit\u00E0");
		lblAdd1.setBounds(108, 49, 190, 20);
		frame.getContentPane().add(lblAdd1);
		
		lblNewLabel_2 = new JLabel("Localit\u00E0 trovate");
		lblNewLabel_2.setToolTipText("Localit\u00E0 trovate");
		lblNewLabel_2.setBounds(350, 61, 150, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("OPEN WEATHER WEB SERVICE");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(218, 11, 184, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(".\\Resources\\immagine\\logo_white_croppednew.png"));
		lblNewLabel_5.setBounds(62, 170, 241, 192);
		frame.getContentPane().add(lblNewLabel_5);
		
		frame.setVisible(true);
		
		frame.getRootPane().setDefaultButton(btnInvio);
		
		}
	
	private static int msg(String testo) { return JOptionPane.showConfirmDialog(frame, testo,"OpenWeather", 0, 1, null); }
	
	private static void pulisci()
	{
		textString.setText("");
		DefaultListModel<String> model=new DefaultListModel<>();
		model.clear();
		list.setModel(model);
		textString.requestFocus();
	}
	
	private static void avviso(String testo) { JOptionPane.showMessageDialog(frame, testo); }
	
	private static void deleteFiles(File f)
	{
		File[] files = f.listFiles();
	    if(files!=null) 
	       for(File path: files) 
	    	   if(!path.getName().toString().equalsIgnoreCase("comuni.txt")) 
	    		   path.delete();
	}
	
	public static void main(String[] args) 
	{
		DataForecast.setapiKey("8a6a917d48b1adcdbafdcd6b825aeb9f");
		File folder = new File(".\\Resources\\Data");
		deleteFiles(folder);
		inizializzazione();
	    
		btnInvio.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(!textString.getText().isEmpty())
				{
					try 
					{
						city.elenco(textString.getText().toLowerCase());
						if(city.isEmpty())
						{
							avviso("Città non presente nell'elenco.");
							pulisci();
							return;
						}
						list.setModel(city.getDLM());
					} catch (FileNotFoundException e1) {	
					
					    throw new ApiRequestException();
					} 
				}
				else
				{
					avviso("Inserire il nome del comune prima di cercare.");
					pulisci();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) { pulisci(); }
		});
		
		btnTuttiComuni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					city.elenco(null);
					list.setModel(city.getDLM());
				} catch (FileNotFoundException e1) {
				    throw new ApiRequestException();
				} 
			}
		});
		
		btnGetSelected.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int indice = list.getSelectedIndex();
				if(indice != -1)
				{
					String comune = city.getDLMIndex(indice);
					if(msg("Hai selezionato "+comune)==0)
						{
							frame.setVisible(false);
							DataForecast.setCitta(city.getDLMIndex(indice));
							SpringApplication.run(ForecastApplication.class, args);
						}
						else pulisci();
				}
				else avviso("Devi prima selezionare un comune!");
			}
		});
		
		btnNull.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(msg("Sei sicuro di voler avviare il server senza un comune?")==0)
				{
					DataForecast.setCitta(null);
					frame.setVisible(false);
					SpringApplication.run(ForecastApplication.class, args);
				}
				else pulisci();
			}
		});
	}
}
