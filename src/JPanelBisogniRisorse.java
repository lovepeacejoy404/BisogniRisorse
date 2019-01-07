

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class JPanelBisogniRisorse extends JPanel{
	
	private JList JListBisogni,JListRisorse;
	private JTextField txtNome; 
	private boolean isNuovo=false;
	private JComboBox comboScelta;
	private JPanel panelX0;
	
	public JPanelBisogniRisorse() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add (Box.createVerticalStrut(10));
		//PULSANTI
		JButton bNuovo,bSalva,bCancella,buttonLicenza;
		JPanel panelX2 = new JPanel();
		panelX2.setLayout(new BoxLayout(panelX2,BoxLayout.X_AXIS));
		panelX2.add(bNuovo=new JButton(Messages.getString("JPanelBisogniRisorse.0"),new ImageIcon("images/pulsanti/New24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
		bNuovo.addActionListener(actionListenerbNuovo);	
		panelX2.add(Box.createHorizontalStrut(10));
		panelX2.add(bSalva=new JButton(Messages.getString("JPanelBisogniRisorse.2"),new ImageIcon("images/pulsanti/Save24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
		bSalva.addActionListener(actionListenerbSalva);
		panelX2.add(Box.createHorizontalStrut(10));
		panelX2.add(bCancella=new JButton(Messages.getString("JPanelBisogniRisorse.4"),new ImageIcon("images/pulsanti/Delete24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
		bCancella.addActionListener(actionListenerbCancella);
		panelX2.add(Box.createHorizontalStrut(10));
		add(panelX2);
		add (Box.createVerticalStrut(10));
		JPanel panelX1 = new JPanel();
		panelX1.setLayout(new BoxLayout(panelX1,BoxLayout.X_AXIS));
		panelX1.add(Box.createHorizontalStrut(10));
		panelX1.add(comboScelta=new JComboBox(new String[]{
				Messages.getString("JPanelBisogniRisorse.6"),Messages.getString("JPanelBisogniRisorse.7") //$NON-NLS-1$ //$NON-NLS-2$
		}));
		
		panelX1.add(Box.createHorizontalStrut(10));
		panelX1.add(txtNome=new JTextField(50));
		panelX1.add(Box.createHorizontalStrut(10));
		add (panelX1);
		
		add (Box.createVerticalStrut(10));
		createJPanelJLists();
		add (Box.createVerticalStrut(600));
	
	}

	private void createJPanelJLists() {
		final Border border4 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(87,0,174),new Color (255,255,255));
		panelX0 = new JPanel();
		panelX0.setLayout(new BoxLayout(panelX0,BoxLayout.X_AXIS));
		panelX0.setBorder(BorderFactory.createTitledBorder(border4,
	  			Messages.getString("JPanelBisogniRisorse.8"),TitledBorder.LEFT,TitledBorder.TOP,Font.getFont("Arial"),new Color(87,0,174)));  //$NON-NLS-1$ //$NON-NLS-2$
		
		panelX0.add(Box.createHorizontalStrut(10));
		panelX0.add(new JLabel(Messages.getString("JPanelBisogniRisorse.10"))); //$NON-NLS-1$
		panelX0.add(Box.createHorizontalStrut(10));
		Vector<CheckListItem> v = new Vector<CheckListItem>();
		for (String s:DBadapter.getBisogni(false))
			v.add(new CheckListItem(s));
		
		panelX0.add(JListBisogni=new JList(v));
		JListBisogni.setCellRenderer(new CheckListRenderer());
		JListBisogni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JListBisogni.addMouseListener(CheckListItem.ma);	
		panelX0.add(Box.createHorizontalStrut(10));
		
		panelX0.add(new JLabel(Messages.getString("JPanelBisogniRisorse.11"))); //$NON-NLS-1$
		panelX0.add(Box.createHorizontalStrut(10));
		Vector<CheckListItem> v1 = new Vector<CheckListItem>();
		for (String s:DBadapter.getRisorse(false))
			v1.add(new CheckListItem(s));
		
		panelX0.add(JListRisorse=new JList(v1));
		JListRisorse.setCellRenderer(new CheckListRenderer());
		JListRisorse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JListRisorse.addMouseListener(CheckListItem.ma);
		panelX0.add(Box.createHorizontalStrut(10));
		add (panelX0);
	}
 
	
	 ActionListener actionListenerbNuovo =new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				isNuovo=true;
				txtNome.setText(""); //$NON-NLS-1$
			}
		};
		
		ActionListener actionListenerbCancella =new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				int result =JOptionPane.showConfirmDialog(null,Messages.getString("JPanelBisogniRisorse.13") + //$NON-NLS-1$
						Messages.getString("JPanelBisogniRisorse.14")); //$NON-NLS-1$
				if (result==JOptionPane.YES_OPTION) {
					CheckListItem nome;
					if (comboScelta.getSelectedIndex()==0){
						nome=((CheckListItem)JListBisogni.getSelectedValue());
						if (nome==null) {
							txtNome.setText(Messages.getString("JPanelBisogniRisorse.15")); //$NON-NLS-1$
							return;
						}
						remove(panelX0);
						DBadapter.deleteItemBisognoRisorsa(true, nome.toString().split("-")[0]); //$NON-NLS-1$
						JPanelAnagrafica.setJLists(JPanelAnagrafica.AGGIORNA, JPanelAnagrafica.RICERCA_BISOGNI);
					} else {
						nome=((CheckListItem)JListRisorse.getSelectedValue());
						if (nome==null) {
							txtNome.setText(Messages.getString("JPanelBisogniRisorse.17")); //$NON-NLS-1$
							return;
						}
						remove(panelX0);
						DBadapter.deleteItemBisognoRisorsa(false, nome.toString().split("-")[0]); //$NON-NLS-1$
						JPanelAnagrafica.setJLists(JPanelAnagrafica.AGGIORNA, !JPanelAnagrafica.RICERCA_BISOGNI);
					}
					createJPanelJLists();
					add(panelX0,4);
					//refreshLists();
					validate();
					
					
				}	
			}
		};
		
		ActionListener actionListenerbSalva =new ActionListener () {
			public void actionPerformed(ActionEvent e) {

				if (isNuovo){
					remove(panelX0);
					if (comboScelta.getSelectedIndex()==0){
						DBadapter.createItemBisognoRisorsa(true, txtNome.getText());
						createJPanelJLists();
						JPanelAnagrafica.setJLists(JPanelAnagrafica.AGGIORNA, JPanelAnagrafica.RICERCA_BISOGNI);
					}

					else {
						DBadapter.createItemBisognoRisorsa(false, txtNome.getText());
						createJPanelJLists();
						JPanelAnagrafica.setJLists(JPanelAnagrafica.AGGIORNA, !JPanelAnagrafica.RICERCA_BISOGNI);
					}
					isNuovo=false;
				} else {
					CheckListItem nome;
					
					if (comboScelta.getSelectedIndex()==0){
						nome=((CheckListItem)JListBisogni.getSelectedValue());
						if (nome==null) return;
						remove(panelX0);
						DBadapter.updateItemBisognoRisorsa(true, txtNome.getText(), nome.toString().split("-")[0]); //$NON-NLS-1$
						JPanelAnagrafica.setJLists(JPanelAnagrafica.AGGIORNA, JPanelAnagrafica.RICERCA_BISOGNI);
					}
					else {
						nome=((CheckListItem)JListRisorse.getSelectedValue());
						if (nome==null) return;
						remove(panelX0);
						DBadapter.updateItemBisognoRisorsa(false, txtNome.getText(), nome.toString().split("-")[0]); //$NON-NLS-1$
						JPanelAnagrafica.setJLists(JPanelAnagrafica.AGGIORNA, !JPanelAnagrafica.RICERCA_BISOGNI);
					}
					createJPanelJLists();
				}

				add(panelX0,4);
				//refreshLists();
				validate();
				
			}
		};
	
		
}
		
