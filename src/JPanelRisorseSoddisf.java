

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class JPanelRisorseSoddisf extends JPanel{
	
	private JTextArea txtElenco;
	private JComboBox<String> comboAnagr;
	private static JPanelRisorseSoddisf panelRisorseS;
	private JPanel panelX3;
	
	public JPanelRisorseSoddisf() {
		panelRisorseS=this;
	
	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	add (Box.createVerticalStrut(10));
	//PULSANTI
	JButton bStampa,bGenera;
	JPanel panelX2 = new JPanel();
	panelX2.setLayout(new BoxLayout(panelX2,BoxLayout.X_AXIS));
	
	panelX2.add(bGenera=new JButton("Genera elenco",new ImageIcon("images/pulsanti/PageSetup24.gif")));
	bGenera.addActionListener(actionListenerbGenera);
	panelX2.add(Box.createHorizontalStrut(10));
	panelX2.add(bStampa=new JButton("Stampa elenco",new ImageIcon("images/pulsanti/Print24.gif")));
	bStampa.addActionListener(actionListenerbStampa);
	panelX2.add(Box.createHorizontalStrut(10));
	add(panelX2);
	
	add (Box.createVerticalStrut(10));
	
	add(Box.createVerticalStrut(10));
	
	createPanelCombo(!JPanelAnagrafica.AGGIORNA);

	
		
	JPanel panelX4 = new JPanel();
	panelX4.setLayout(new BoxLayout(panelX4,BoxLayout.X_AXIS));
	//panelX3.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelX4.add(Box.createHorizontalStrut(10));
	
	JScrollPane scroll;
	final Border border4 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(87,0,174),new Color (255,255,255));
	
  	scroll= new JScrollPane(txtElenco = new JTextArea());
  	scroll.setBorder(BorderFactory.createTitledBorder(border4,
  			"Risorse soddisfacenti la persona selezionata:",TitledBorder.LEFT,TitledBorder.TOP,Font.getFont("Arial"),new Color(87,0,174))); 
  	txtElenco.setFont(new Font(null,Font.PLAIN,16));
  	txtElenco.setBorder( new EmptyBorder(5,5,5,5));
  	txtElenco.setLineWrap(true);
  	txtElenco.setWrapStyleWord(true);
	
  	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setPreferredSize(new Dimension(400,800));
	
	
	panelX4.add(scroll);
	panelX4.add(Box.createHorizontalStrut(10));
	add(panelX4);
	
	comboAnagr.setSelectedIndex(0);
	
	}

	private void createPanelCombo(boolean aggiorna) {
		panelX3 = new JPanel();
		panelX3.setLayout(new BoxLayout(panelX3,BoxLayout.X_AXIS));
		panelX3.add(new JLabel("Ricerca anagrafica:"));
		panelX3.add(Box.createHorizontalStrut(10));
		panelX3.add(comboAnagr = new JComboBox(DBadapter.getVectorAnagrafica(true,"")));
		comboAnagr.addActionListener(actionListenerComboSelect);
		panelX3.add(Box.createHorizontalStrut(10));
		if (aggiorna) 
			add(panelX3,3);
		else
			add(panelX3);
		add(Box.createVerticalStrut(10));
	}

		
	ActionListener actionListenerbStampa =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
				
				
					

		}
	};
	
	ActionListener actionListenerbGenera =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
				
			comboAnagr.setSelectedIndex(0);	
					

		}
	};
	
	
	ActionListener actionListenerComboSelect =new ActionListener () {
		public void actionPerformed(ActionEvent e) {
				String item =(String) comboAnagr.getSelectedItem();
				StringBuilder sb = new StringBuilder(),sb1 = new StringBuilder(),sb2 = new StringBuilder();
				if (item.equals("Tutti")){
					
					for (int i=1;i<comboAnagr.getItemCount();i++){
						item=(String)comboAnagr.getItemAt(i);
						sb2.append(getItemAnagrafica(item, sb, sb1));
						sb  = new StringBuilder();
						sb1 = new StringBuilder();
					}
					txtElenco.setText("RISORSE SODDISFACENTI I BISOGNI:\n"+ sb2);
				} else {
					txtElenco.setText("RISORSE SODDISFACENTI I BISOGNI:\n"+ getItemAnagrafica(item, sb, sb1));

				}
								
				
		}

		private String getItemAnagrafica(String item, StringBuilder sb,
				StringBuilder sb1) {
			String ID = item.split("-")[0];
			Vector<String> v = DBadapter.getBisogniPersona(ID);
			sb.append("BISOGNI: ");
			for (String s:v) sb.append(s);

			v = DBadapter.getVectorAnagrafica(
					false, DBadapter.getIDRisorseSoddisf(ID));
			
			for (String s:v){
				sb1.append("\n\t"+s+"\n\n");
				if (s.equals("Risorse non trovate")) break;
				sb1.append("\tRISORSE: ");
				Vector<String> v1 = DBadapter.getRisorsePersona(s.split("-")[0]);
				for (String s1:v1)
					sb1.append(s1);
			}
			return "\n"+item +"\n\n"+sb+
						"\n-------------\n"+sb1+
						"\n*************************************************************************************";
		}
	};
	
	public static void updateCombo(){
		panelRisorseS.remove(panelRisorseS.panelX3);
		panelRisorseS.createPanelCombo(JPanelAnagrafica.AGGIORNA);
		panelRisorseS.validate();
	}

}
		
