

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class JPanelImporta extends JPanel{
	
	private JTextArea txtCsv;
	
	
	
	
	public JPanelImporta() {
		
	
	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	add (Box.createVerticalStrut(10));
	//PULSANTI
	JButton bImporta,bBisogni,bRisorse,bElenco;
	JPanel panelX2 = new JPanel();
	panelX2.setLayout(new BoxLayout(panelX2,BoxLayout.X_AXIS));
	panelX2.add(bImporta=new JButton(Messages.getString("JPanelImporta.0"),new ImageIcon("images/pulsanti/PageSetup24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bImporta.addActionListener(actionListenerbImporta);
	panelX2.add(Box.createHorizontalStrut(10));
	
	panelX2.add(bBisogni=new JButton(Messages.getString("JPanelImporta.2"),new ImageIcon("images/pulsanti/PageSetup24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bBisogni.addActionListener(actionListenerbEsportaBisogni);
	panelX2.add(Box.createHorizontalStrut(10));
	
	panelX2.add(bRisorse=new JButton(Messages.getString("JPanelImporta.4"),new ImageIcon("images/pulsanti/PageSetup24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bRisorse.addActionListener(actionListenerbEsportaRisorse);
	panelX2.add(Box.createHorizontalStrut(10));
	
	
	panelX2.add(bElenco=new JButton(Messages.getString("JPanelImporta.6"),new ImageIcon("images/pulsanti/PageSetup24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bElenco.addActionListener(actionListenerbElenco);
	panelX2.add(Box.createHorizontalStrut(10));
	
	add(panelX2);
	
	
	
	add(Box.createVerticalStrut(10));
	
	

	
		
	JPanel panelX4 = new JPanel();
	panelX4.setLayout(new BoxLayout(panelX4,BoxLayout.X_AXIS));
	//panelX3.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelX4.add(Box.createHorizontalStrut(10));
	
	JScrollPane scroll;
	final Border border4 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(87,0,174),new Color (255,255,255));
	
  	scroll= new JScrollPane(txtCsv = new JTextArea());
  	scroll.setBorder(BorderFactory.createTitledBorder(border4,
  			Messages.getString("JPanelImporta.8"),TitledBorder.LEFT,TitledBorder.TOP,Font.getFont("Arial"),new Color(87,0,174)));  //$NON-NLS-1$ //$NON-NLS-2$
  	txtCsv.setFont(new Font(null,Font.PLAIN,16));
  	txtCsv.setBorder( new EmptyBorder(5,5,5,5));
  	txtCsv.setLineWrap(true);
  	txtCsv.setWrapStyleWord(true);
	
  	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setPreferredSize(new Dimension(400,800));
	
	
	panelX4.add(scroll);
	panelX4.add(Box.createHorizontalStrut(10));
	add(panelX4);
	
	
	
	}

	
	ActionListener actionListenerbImporta =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
			try {	
			String csv = txtCsv.getText();
			txtCsv.setText(txtCsv.getText()+"\n\n"); //$NON-NLS-1$
			for (String line :csv.split("\n")){ //$NON-NLS-1$
				String a[]=line.split(","); //$NON-NLS-1$
				//DBadapter.createItemAnagrafica(nome, cognome, eta, sesso, professione, appartenenza, indirizzo, citta, telefono, cellulare, email, note, id_risorse_soddisf, id_risorse, id_bisogni)
				DBadapter.createItemAnagrafica(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10],a[11], Messages.getString("JPanelImporta.13"), new ArrayList<Integer>(), new ArrayList<Integer>()); //$NON-NLS-1$
				txtCsv.setText(txtCsv.getText()+"\n"+line +Messages.getString("JPanelImporta.15")); //$NON-NLS-1$ //$NON-NLS-2$
			}
			} catch (Exception ex)	{
				ex.printStackTrace();
			}

		}
	};
	
	ActionListener actionListenerbEsportaBisogni =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
			StringBuilder sb = new StringBuilder();	
			sb.append("Bisogno#Numero Persone\n"); //$NON-NLS-1$
			for (String s: DBadapter.getVectorCount(JPanelAnagrafica.RICERCA_BISOGNI))
				sb.append(s.substring(0, s.length()-1)+"\n"); //$NON-NLS-1$
			txtCsv.setText(sb.toString());
			createFileCsv(sb.toString());	

		}
	};
	
	ActionListener actionListenerbEsportaRisorse =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
			StringBuilder sb = new StringBuilder();	
			sb.append("Risorsa#Numero Persone\n"); //$NON-NLS-1$
			for (String s: DBadapter.getVectorCount(!JPanelAnagrafica.RICERCA_BISOGNI))
				sb.append(s.substring(0, s.length()-1)+"\n"); //$NON-NLS-1$
			txtCsv.setText(sb.toString());
			createFileCsv(sb.toString());	

		}
	};
	
	ActionListener actionListenerbElenco =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
			StringBuilder sb = new StringBuilder();	
			String ID=null;
			sb.append("ID#Eta#Sesso#Professione#Bisogni#Risorse\n"); //$NON-NLS-1$
			
			for (String s: DBadapter.getVectorAnagrafica(true)){
				sb.append(s);
				ID=s.split("#")[0]; //$NON-NLS-1$
				sb.append(DBadapter.getBisogniPersona(ID));
				sb.append("#"); //$NON-NLS-1$
				sb.append(DBadapter.getRisorsePersona(ID));
				sb.append("\n"); //$NON-NLS-1$
			}
				
			
			txtCsv.setText(sb.toString());
			createFileCsv(sb.toString());	

		}
	};
	
	
	private void createFileCsv(String csv){
		 File file = new File("files/file.csv"); //$NON-NLS-1$

	        PrintWriter printWriter = null;

	        try
	        {
	            printWriter = new PrintWriter(file);
	            printWriter.println(csv);
	            printWriter.close();
                Desktop.getDesktop().open(file);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	}
	

}
		
