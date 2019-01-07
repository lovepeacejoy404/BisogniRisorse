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
		  			Messages.getString("jpanelRicerca.0"),TitledBorder.LEFT,TitledBorder.TOP,new Font("Verdana",Font.BOLD,12),new Color(87,0,174) ); //$NON-NLS-1$ //$NON-NLS-2$
		 	setBorder( border);
    		
		add(new JLabel(new ImageIcon("images/pulsanti/frecciaSin.gif"))); //$NON-NLS-1$
    	add(new JLabel(is_ricerca_bisogni?Messages.getString("jpanelRicerca.3"):Messages.getString("jpanelRicerca.4"))); //$NON-NLS-1$ //$NON-NLS-2$
	 	add(Box.createHorizontalStrut(10));
	  	  	
	  	comboElenco = new JComboBox(is_ricerca_bisogni?DBadapter.getBisogni(true):DBadapter.getRisorse(true));
	  	comboElenco.setToolTipText(Messages.getString("jpanelRicerca.5")); //$NON-NLS-1$
	  	add(comboElenco);
	  	//comboElenco.setSelectedIndex(3);
	  	comboElenco.addActionListener(new CercaAction());
	  	
	  	add(Box.createHorizontalStrut(10));
	  		JButton buttonLicenza;
	  		add(buttonLicenza=new JButton(Messages.getString("jpanelRicerca.6"),new ImageIcon("images/pulsanti/Help24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	  		add(Box.createHorizontalStrut(10));
	  		add(new JLabel(new ImageIcon("images/pulsanti/frecciaDes.gif"))); //$NON-NLS-1$
	  		buttonLicenza.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e) {
					Object[] message = new Object[2];
					message[0]=Messages.getString("jpanelRicerca.9")  //$NON-NLS-1$
							;
					JTextArea txtArea = new JTextArea();
					txtArea.setText(
							Messages.getString("jpanelRicerca.10")+ //$NON-NLS-1$
							Messages.getString("jpanelRicerca.11")+ //$NON-NLS-1$
							Messages.getString("jpanelRicerca.12")+ //$NON-NLS-1$
							Messages.getString("jpanelRicerca.13")+ //$NON-NLS-1$
							Messages.getString("jpanelRicerca.14") //$NON-NLS-1$
							);
					txtArea.setWrapStyleWord(true);
					txtArea.setLineWrap(true);
					txtArea.setEditable(false);
					txtArea.setCaretPosition(0);
					JScrollPane scroll =new JScrollPane(txtArea);
					scroll.setPreferredSize(new Dimension(100,200));
					message[1]= scroll;
					String[] options = { 
				 		    Messages.getString("jpanelRicerca.15"),Messages.getString("jpanelRicerca.16")  //$NON-NLS-1$ //$NON-NLS-2$
				 		}; 
					int result = JOptionPane.showOptionDialog( 
							null,                             // the parent that the dialog blocks 
							message,                                    // the dialog message array 
							Messages.getString("jpanelRicerca.17"), // the title of the dialog window  //$NON-NLS-1$
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
							uriMailTo = new URI("mailto", Messages.getString("jpanelRicerca.19"), null); //$NON-NLS-1$ //$NON-NLS-2$
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
	  		buttonLicenza.setToolTipText(Messages.getString("jpanelRicerca.20")); //$NON-NLS-1$
	  	
	  	
    	
    }
   
    
    
    class CercaAction extends AbstractAction {
       	           
     protected CercaAction() {
           super();
       
     }

       public void actionPerformed(ActionEvent e) {
             try {
            	 
            	//JPanelAnagrafica.selectRadioButtonRicerca();
             	String item=(String)comboElenco.getSelectedItem();
             	if (item.equals(Messages.getString("jpanelRicerca.21"))) //$NON-NLS-1$
             		JPanelAnagrafica.setTabella(JPanelAnagrafica.RICERCA_BISOGNI,true, "", "APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
             	else if (item.equals(Messages.getString("jpanelRicerca.24"))) //$NON-NLS-1$
             		JPanelAnagrafica.setTabella(!JPanelAnagrafica.RICERCA_BISOGNI,true, "", "APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
             	else if (is_ricerca_bisogni)
             		JPanelAnagrafica.setTabella(JPanelAnagrafica.RICERCA_BISOGNI,true, item.split("-")[0], ""); //$NON-NLS-1$ //$NON-NLS-2$
             	else
             		JPanelAnagrafica.setTabella(!JPanelAnagrafica.RICERCA_BISOGNI,true, item.split("-")[0], ""); //$NON-NLS-1$ //$NON-NLS-2$
             	
             }	catch (Exception ex) {System.out.println(ex.toString());}
       }


    }
    
    
    
}    	