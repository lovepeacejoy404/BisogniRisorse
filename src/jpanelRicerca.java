import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;





public class jpanelRicerca extends JPanel {
	private JComboBox comboElenco;
	private boolean is_ricerca_bisogni;
	
	
	
	
    public jpanelRicerca(boolean is_ricerca_bisogni) {
    	this.is_ricerca_bisogni=is_ricerca_bisogni;
    	
    	setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    	
	    	Border border4 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(87,0,174),new Color (255,255,255));
		  	Border border = BorderFactory.createTitledBorder(border4,
		  			"Ricerca:",TitledBorder.LEFT,TitledBorder.TOP,new Font("Verdana",Font.BOLD,12),new Color(87,0,174) );
		 	setBorder( border);
    		
		add(new JLabel(new ImageIcon("images/pulsanti/frecciaSin.gif")));
    	add(new JLabel(is_ricerca_bisogni?"Ricerca bisogni:":"Ricerca risorse:"));
	 	add(Box.createHorizontalStrut(10));
	  	  	
	  	comboElenco = new JComboBox(is_ricerca_bisogni?DBadapter.getBisogni(true):DBadapter.getRisorse(true));
	  	comboElenco.setToolTipText("Cliccare su questo menù a tendina per selezionare il bisogno o la risorsa da cercare");
	  	add(comboElenco);
	  	//comboElenco.setSelectedIndex(3);
	  	comboElenco.addActionListener(new CercaAction());
	  	
	  	add(Box.createHorizontalStrut(10));
	  		JButton buttonLicenza;
	  		add(buttonLicenza=new JButton("Info e Guida",new ImageIcon("images/pulsanti/Help24.gif")));
	  		add(Box.createHorizontalStrut(10));
	  		add(new JLabel(new ImageIcon("images/pulsanti/frecciaDes.gif")));
	  		buttonLicenza.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e) {
					Object[] message = new Object[2];
					message[0]="Questo software �� stato realizzato da Gianluca Crocivera. " 
							;
					JTextArea txtArea = new JTextArea();
					txtArea.setText(
							"SCOPO DEL SOFTWARE:"+
							"\nLo scopo di questo lavoro �� quello di gestire clienti,scadenze e pagamenti di una palestra"+
							"\nGUIDA ALL'UTILIZZO:"+
							"\nPer cercare un cognome, un tipo di abbonamento, untipo di attivit�� cliccare sul men�� a tendina: \"La visualizzazione\" "+
							"I clienti si selezionano cliccando direttamente sulla tabella"
							);
					txtArea.setWrapStyleWord(true);
					txtArea.setLineWrap(true);
					txtArea.setEditable(false);
					txtArea.setCaretPosition(0);
					JScrollPane scroll =new JScrollPane(txtArea);
					scroll.setPreferredSize(new Dimension(100,200));
					message[1]= scroll;
					String[] options = { 
				 		    "Invia e-mail all'autore ","Annulla" 
				 		}; 
					int result = JOptionPane.showOptionDialog( 
							null,                             // the parent that the dialog blocks 
							message,                                    // the dialog message array 
							"Informazioni e guida", // the title of the dialog window 
							JOptionPane.DEFAULT_OPTION,                 // option type 
							JOptionPane.INFORMATION_MESSAGE,            // message type 
							null,                                       // optional icon, use null to use the default icon 
							options,                                    // options string array, will be made into buttons 
							options[0]                                  // option that should be made into a default button 
					); 
					switch(result) {
					case 0:
						Desktop desktop=Desktop.getDesktop();
						URI uriMailTo;
						try {
							uriMailTo = new URI("mailto", "cantor2640@gmail.com?subject=Software palestra", null);
							desktop.mail(uriMailTo); 
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case 1: // connessione
						break; 

					} 

				}
	  		});
	  		buttonLicenza.setToolTipText("Informazioni sull'autore e licenza");
	  	
	  	
    	
    }
   
    
    
    class CercaAction extends AbstractAction {
       	           
     protected CercaAction() {
           super();
       
     }

       public void actionPerformed(ActionEvent e) {
             try {
            	 
            	//JPanelAnagrafica.selectRadioButtonRicerca();
             	String item=(String)comboElenco.getSelectedItem();
             	if (item.equals("Tutti"))
             		JPanelAnagrafica.setTabella(JPanelAnagrafica.RICERCA_BISOGNI,true, "", "APP.ANAGRAFICA.COGNOME");
             	else if (item.equals("Tutte"))
             		JPanelAnagrafica.setTabella(!JPanelAnagrafica.RICERCA_BISOGNI,true, "", "APP.ANAGRAFICA.COGNOME");
             	else if (is_ricerca_bisogni)
             		JPanelAnagrafica.setTabella(JPanelAnagrafica.RICERCA_BISOGNI,true, item.split("-")[0], "");
             	else
             		JPanelAnagrafica.setTabella(!JPanelAnagrafica.RICERCA_BISOGNI,true, item.split("-")[0], "");
             	
             }	catch (Exception ex) {System.out.println(ex.toString());}
       }


    }
    
    
    
}    	