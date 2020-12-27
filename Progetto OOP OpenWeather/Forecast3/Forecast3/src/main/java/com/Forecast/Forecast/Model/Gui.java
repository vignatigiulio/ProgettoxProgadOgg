package com.Forecast.Forecast.Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.AbstractButton;
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

import com.Forecast.Forecast.Model.Data.DataForecast;

public class Gui {
	
	public  JFrame frame;
	public  JButton btnInvio;
	public  JButton btnCancel;
	public JTextField textString;
	public JLabel lblAdd1;
	public JButton btnGetSelected;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	public JList list ;
	DLM city = null;
	
	public void inizialize()
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
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(108, 120, 84, 20);
		frame.getContentPane().add(btnCancel);
		
		textString = new JTextField();
		textString.setToolTipText("Cerca per Previsioni per localit\u00E0");
		textString.setBounds(108, 66, 150, 26);
		frame.getContentPane().add(textString);
		textString.setColumns(10);
		
		btnGetSelected = new JButton("Selected");
		btnGetSelected.setBounds(108, 98, 84, 20);
		frame.getContentPane().add(btnGetSelected);
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(454, 10, 2, 666);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(350, 74, 150, 250);
		frame.getContentPane().add(scrollPane_1);
		
		list = new JList();
		scrollPane_1.setViewportView(list);
		
		JLabel lblNewLabel_1 = new JLabel("Cerca per Previsioni per localit\u00E0");
		lblNewLabel_1.setBounds(108, 49, 168, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComponent lblNewLabel_2 = new JLabel("Localit\u00E0 trovate");
		lblNewLabel_2.setToolTipText("Localit\u00E0 trovate");
		lblNewLabel_2.setBounds(350, 61, 150, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("OPEN WEATHER WEB SERVICE");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(218, 11, 184, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(".\\Resources\\immagine\\logo_white_croppednew.png"));
		lblNewLabel_5.setBounds(62, 146, 241, 192);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(".\\Resources\\immagine\\sfonno2.png"));
		lblNewLabel_4.setBounds(0, 0, 611, 338);
		frame.getContentPane().add(lblNewLabel_4);
		
		frame.setVisible(true);
	}

	
	public void insertInvio()
	{
		btnInvio.addActionListener(new ActionListener() {
			
			@Override
	public void actionPerformed(ActionEvent e) {
					
						try {
							city.elenco(textString.getText().toLowerCase());
							if(city.isEmpty())
								JOptionPane.showMessageDialog(null,"citta non presente nell'elenco");
							
							list.setModel(city.getDLM());
						
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						}
						
					}
			});
	}

	public void insertCancel()
	{
		
		btnCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				pulisci();
			
			}
		});
		
	}

	public void insertSelected() {
		
		int[] indic = null;	 
			 indic = list.getSelectedIndices();
			 DataForecast.setCitta(city.getDLMIndex(indic[0]));
			
	}
	
	public int  msg()
	{ 	
		
		 return JOptionPane.showConfirmDialog(null, "la citta è:" + DataForecast.getCitta(),"OpenWeather", 0, 1, null);
	}
	
	public void pulisci()
	{
		textString.setText("");
	 DefaultListModel model=new DefaultListModel();
	 model.clear();
	 list.setModel(model);
	}

}

	
	

