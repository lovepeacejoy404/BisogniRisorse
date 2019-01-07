

import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class JPanelAnagrafica extends JPanel{
	
	private JTextField txtID,txtCognome,txtNome,txtEta, txtAppartenenza,txtProfessione
	,txtIndirizzo,txtCitta,txtTelefono,txtCellulare,txtEmail,txtIDRisorse,txtSesso;
	private JTextArea txtNote;
	private static JList JListBisogni,JListRisorse;
	//private JRadioButton rbCognome,rbAppartenenza,rbProfessione,rbCognome1,rbAppartenenza1,rbProfessione1;
	//private JRadioButton rbRicerca,rbRicerca1;
	private JTable table=new JTable(),table1=new JTable();
	private JScrollPane jsp,jsp1;
	private JPanel panelY;
	private static JPanel panelX;
	private Vector<String> tableColumnsName;
	private JButton bSalva;
	private int selectedRow;
	private boolean isNuova=false;
	private static ListModel lmBisogni,lmRisorse;
	private ButtonGroup group=new ButtonGroup(),group1 = new ButtonGroup();
	private static JPanelAnagrafica panelAnagrafica;
	public static final boolean RICERCA_BISOGNI=true,AGGIORNA=true;
	
	private class FocusTextField extends JTextField {
	    {
	        addFocusListener(new FocusListener() {

	            @Override
	            public void focusGained(FocusEvent e) {
	                FocusTextField.this.select(0, getText().length());
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                FocusTextField.this.select(0, 0);
	            }
	        });
	    }
	}	
		
	
	public JPanelAnagrafica() {
	panelAnagrafica=this;
	
	setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

	//DATI ANAGRAFICI
		panelY = new JPanel();
		panelY.setLayout(new BoxLayout(panelY,BoxLayout.Y_AXIS));
		panelY.add(Box.createVerticalStrut(10));
	
	//PULSANTI
	JButton bNuovo,bCancella,buttonLicenza;
	JPanel panelX2 = new JPanel();
	panelX2.setLayout(new BoxLayout(panelX2,BoxLayout.X_AXIS));
	panelX2.add(bNuovo=new JButton(Messages.getString("JPanelAnagrafica.0"),new ImageIcon("images/pulsanti/New24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bNuovo.addActionListener(actionListenerbNuovo);	
	panelX2.add(Box.createHorizontalStrut(10));
	panelX2.add(bSalva=new JButton(Messages.getString("JPanelAnagrafica.2"),new ImageIcon("images/pulsanti/Save24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bSalva.addActionListener(actionListenerbSalva);
	panelX2.add(Box.createHorizontalStrut(10));
	panelX2.add(bCancella=new JButton(Messages.getString("JPanelAnagrafica.4"),new ImageIcon("images/pulsanti/Delete24.gif"))); //$NON-NLS-1$ //$NON-NLS-2$
	bCancella.addActionListener(actionListenerbCancella);
	panelX2.add(Box.createHorizontalStrut(10));
	
	panelY.add(panelX2);
	
	
	JPanel panelX3 = new JPanel();
	panelX3.setLayout(new BoxLayout(panelX3,BoxLayout.X_AXIS));
	
		
	panelX3.add(new JLabel(Messages.getString("JPanelAnagrafica.6"))); //$NON-NLS-1$
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(txtID=new JTextField());
	txtID.setVisible(false);
	panelX3.add(txtNome=new FocusTextField());
	
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(new JLabel(Messages.getString("JPanelAnagrafica.7"))); //$NON-NLS-1$
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(txtCognome=new FocusTextField());
	
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(new JLabel(Messages.getString("JPanelAnagrafica.8"))); //$NON-NLS-1$
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(txtEta=new FocusTextField());
	
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(new JLabel(Messages.getString("JPanelAnagrafica.9"))); //$NON-NLS-1$
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(txtSesso=new FocusTextField());
	
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(new JLabel(Messages.getString("JPanelAnagrafica.10"))); //$NON-NLS-1$
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(txtProfessione=new FocusTextField());
	
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(new JLabel(Messages.getString("JPanelAnagrafica.11"))); //$NON-NLS-1$
	panelX3.add(Box.createHorizontalStrut(10));
	panelX3.add(txtAppartenenza=new FocusTextField());
	panelX3.add(Box.createHorizontalStrut(10));
	
	
	
	
	panelY.add(panelX3);
	panelY.add(Box.createVerticalStrut(10));

	
	JPanel panelX5 = new JPanel();
	panelX5.setLayout(new BoxLayout(panelX5,BoxLayout.X_AXIS));
	
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(new JLabel(Messages.getString("JPanelAnagrafica.12"))); //$NON-NLS-1$
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(txtIndirizzo=new FocusTextField());
	panelX5.add(Box.createHorizontalStrut(10));
	
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(new JLabel(Messages.getString("JPanelAnagrafica.13"))); //$NON-NLS-1$
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(txtCitta=new FocusTextField());
	panelX5.add(Box.createHorizontalStrut(10));
	
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(new JLabel(Messages.getString("JPanelAnagrafica.14"))); //$NON-NLS-1$
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(txtTelefono=new FocusTextField());
	panelX5.add(Box.createHorizontalStrut(10));
	
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(new JLabel(Messages.getString("JPanelAnagrafica.15"))); //$NON-NLS-1$
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(txtCellulare=new FocusTextField());
	panelX5.add(Box.createHorizontalStrut(10));
	
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(new JLabel(Messages.getString("JPanelAnagrafica.16"))); //$NON-NLS-1$
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(txtEmail=new FocusTextField());
	panelX5.add(Box.createHorizontalStrut(10));
	
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(new JLabel(Messages.getString("JPanelAnagrafica.17"))); //$NON-NLS-1$
	panelX5.add(Box.createHorizontalStrut(10));
	panelX5.add(txtIDRisorse=new FocusTextField());
	panelX5.add(Box.createHorizontalStrut(10));
	
	
	panelY.add(panelX5);
	panelY.add(Box.createVerticalStrut(10));

	final Border border4 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(87,0,174),new Color (255,255,255));
	
	
	
	
	
	
	JPanel panelX4 = new JPanel();
	panelX4.setLayout(new BoxLayout(panelX4,BoxLayout.X_AXIS));
	//panelX3.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelX4.add(Box.createHorizontalStrut(10));
	
	JScrollPane scroll;
	
  	scroll= new JScrollPane(txtNote = new JTextArea());
  	scroll.setBorder(BorderFactory.createTitledBorder(border4,
  			Messages.getString("JPanelAnagrafica.18"),TitledBorder.LEFT,TitledBorder.TOP,Font.getFont("Arial"),new Color(87,0,174)));  //$NON-NLS-1$ //$NON-NLS-2$
  	txtNote.setFont(new Font(null,Font.PLAIN,16));
  	txtNote.setBorder( new EmptyBorder(5,5,5,5));
  	txtNote.setLineWrap(true);
  	txtNote.setWrapStyleWord(true);
	
  	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setPreferredSize(new Dimension(50,100));
	
	
	panelX4.add(scroll);
	panelX4.add(Box.createHorizontalStrut(10));
	panelY.add(panelX4);
	//panelY.add(Box.createVerticalStrut(10));
	
	
	//FINE DATI ANAGRAFICI
	
	//PANNELLO DI RICERCA 
	
	
		
	JPanel panelX14= new JPanel(new FlowLayout(FlowLayout.LEFT));
	panelX14.add(new jpanelRicerca(RICERCA_BISOGNI));
	panelY.add(panelX14);
	panelY.add (Box.createVerticalStrut(10));
	
	tableColumnsName = DBadapter.getColumnsNameTable();
	setTabella(RICERCA_BISOGNI,!AGGIORNA,"","APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
	//panelY.add (Box.createVerticalStrut (10));
	panelY.add (jsp=new JScrollPane(table));
  	jsp.getViewport().setBackground(new Color(255,245,215));
  	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	
	JPanel panelX15= new JPanel(new FlowLayout(FlowLayout.LEFT));
	panelX15.add(new jpanelRicerca(!RICERCA_BISOGNI));
	panelY.add(panelX15);
	panelY.add (Box.createVerticalStrut(10));
		
	setTabella(!RICERCA_BISOGNI,!AGGIORNA,"","APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
	//panelY.add (Box.createVerticalStrut (10));
	panelY.add (jsp1=new JScrollPane(table1));
  	jsp1.getViewport().setBackground(new Color(255,245,215));
  	jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  	panelY.add (Box.createVerticalStrut(50));
	
	add(panelY);
		
	//BISOGNI E RISORSE
	
	panelX = new JPanel();
	panelX.setLayout(new BoxLayout(panelX,BoxLayout.Y_AXIS));
	
	panelX.setBorder(BorderFactory.createTitledBorder(border4,
  			Messages.getString("JPanelAnagrafica.24"),TitledBorder.LEFT,TitledBorder.TOP,Font.getFont("Arial"),new Color(87,0,174)));  //$NON-NLS-1$ //$NON-NLS-2$
	
	panelX.add(Box.createVerticalStrut(10));
	panelX.add(new JLabel(Messages.getString("JPanelAnagrafica.26"))); //$NON-NLS-1$
	panelX.add(Box.createVerticalStrut(10));
	setJLists(!AGGIORNA,RICERCA_BISOGNI);
		
	panelX.add(Box.createVerticalStrut(10));
	
	panelX.add(new JLabel(Messages.getString("JPanelAnagrafica.27"))); //$NON-NLS-1$
	panelX.add(Box.createVerticalStrut(10));
	setJLists(!AGGIORNA,!RICERCA_BISOGNI);
	
	panelX.add(Box.createVerticalStrut(200));
	panelX.setPreferredSize(new Dimension(300,800));
	JSplitPane splitPane= new JSplitPane();
	JScrollPane scroll2=new JScrollPane(panelX);
	scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll2.setPreferredSize(new Dimension(300,600));
	splitPane.setLeftComponent(scroll2);
	splitPane.setRightComponent(panelY);
	splitPane.setContinuousLayout(true);
	splitPane.setOneTouchExpandable(true);
	splitPane.setDividerLocation(300);
	add (splitPane );
	if(table.getRowCount()>0)selezionaDati(RICERCA_BISOGNI,0); 
	if(table1.getRowCount()>0)selezionaDati(!RICERCA_BISOGNI,0); 
	
	}



	public static void setJLists(boolean aggiorna,boolean is_bisogni) {
		Vector<CheckListItem> v = new Vector<CheckListItem>();
		if (is_bisogni) {
			for (String s:DBadapter.getBisogni(false))
				v.add(new CheckListItem(s));
			if (aggiorna) {
				panelX.remove(JListBisogni);
				panelX.add(JListBisogni=new JList(v),3);
				
			} else 	panelX.add(JListBisogni=new JList(v));
			JListBisogni.setCellRenderer(new CheckListRenderer());
			JListBisogni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JListBisogni.addMouseListener(CheckListItem.ma);
			lmBisogni =JListBisogni.getModel();
		} else {
			for (String s:DBadapter.getRisorse(false))
				v.add(new CheckListItem(s));
			if (aggiorna) {
				panelX.remove(JListRisorse);
				panelX.add(JListRisorse=new JList(v),6);
				
			} else 	panelX.add(JListRisorse=new JList(v));
			JListRisorse.setCellRenderer(new CheckListRenderer());
			JListRisorse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JListRisorse.addMouseListener(CheckListItem.ma);
			lmRisorse =panelAnagrafica.JListRisorse.getModel();
		}
		panelAnagrafica.validate();
		//panelAnagrafica.selezionaDati(RICERCA_BISOGNI,0);
		//panelAnagrafica.selezionaDati(!RICERCA_BISOGNI,0);
	}


	
	public static void setTabella(boolean ricerca_bisogni,boolean aggiorna,String where,String orderBy) {

		try{
			Vector<Vector<String>> tableData = DBadapter.getTableDataAnagrafica(ricerca_bisogni,where,orderBy);
			if (ricerca_bisogni){
				panelAnagrafica.table 	= new JTable(tableData, panelAnagrafica.tableColumnsName) {
					public boolean isCellEditable(int row,int column) {
						return false;
					}
				};
				panelAnagrafica.table.addMouseListener(panelAnagrafica.listenerClickTableBisogni);
				panelAnagrafica.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );



				if (aggiorna) {
					panelAnagrafica.panelY.remove(panelAnagrafica.jsp);
					panelAnagrafica.panelY.add (panelAnagrafica.jsp=new JScrollPane(panelAnagrafica.table),8);
					//jsp.getViewport().setBackground(new Color(255,211,168));
					panelAnagrafica.jsp.getViewport().setBackground(new Color(255,245,215));
					panelAnagrafica.jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					panelAnagrafica.selectedRow =panelAnagrafica.getSelectedRow();
					if (panelAnagrafica.table.getRowCount()>panelAnagrafica.selectedRow) {
						//System.out.println(panelAnagrafica.selectedRow);
						panelAnagrafica.selezionaDati(ricerca_bisogni,panelAnagrafica.selectedRow);
					}

					panelAnagrafica.validate();
				}
			} else {
				panelAnagrafica.table1 	= new JTable(tableData, panelAnagrafica.tableColumnsName) {
					public boolean isCellEditable(int row,int column) {
						return false;
					}
				};
				panelAnagrafica.table1.addMouseListener(panelAnagrafica.listenerClickTableRisorse);
				panelAnagrafica.table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );



				if (aggiorna) {
					panelAnagrafica.panelY.remove(panelAnagrafica.jsp1);
					panelAnagrafica.panelY.add (panelAnagrafica.jsp1=new JScrollPane(panelAnagrafica.table1),11);
					//jsp.getViewport().setBackground(new Color(255,211,168));
					panelAnagrafica.jsp1.getViewport().setBackground(new Color(255,245,215));
					panelAnagrafica.jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					panelAnagrafica.selectedRow =panelAnagrafica.getSelectedRow();
					if (panelAnagrafica.table1.getRowCount()>panelAnagrafica.selectedRow) {
						//System.out.println(panelAnagrafica.selectedRow);
						panelAnagrafica.selezionaDati(ricerca_bisogni,panelAnagrafica.selectedRow);
					}

					panelAnagrafica.validate();
				}
			}




		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
	private int getSelectedRow(){
		 ArrayList<String> ll= new ArrayList<String>();
		 for (int row=0;row<table.getRowCount();row++){
			 ll.add(table.getValueAt(row, 0).toString());
		 }
		 int row=ll.indexOf(txtID.getText());
		 if (row==-1) 
			 return 0;
		 else 
			 return row;
		 
		 
	 }
	
	private void selezionaDati (boolean is_ricerca_bisogni,int selRow) {
	 	try {
	 		if (selRow==-1) return;
	 		isNuova=false;
	 		String ID;
	 		if (is_ricerca_bisogni){
	 			table.clearSelection() ;
		 		table.addRowSelectionInterval(selRow,selRow);
		 		ID=(String)table.getValueAt(selRow,0);
	 		} else {
	 			table1.clearSelection() ;
		 		table1.addRowSelectionInterval(selRow,selRow);
		 		ID=(String)table1.getValueAt(selRow,0);
	 		}
	 			
	 		
	 		ResultSet rs = DBadapter.getRecordAnagrafica(ID);
	 		
	 		rs.next();
	 		
	 		txtID.setText(rs.getString(1));
	 		//txtID.setText(Long.toString(rs.getLong("APP.BISOGNI.ID")));
	 		txtNome.setText(rs.getString(2));
	 		txtCognome.setText(rs.getString(3));
	 		txtEta.setText(rs.getString(4));
	 		txtProfessione.setText(rs.getString(5));
	 		txtAppartenenza.setText(rs.getString(6));
	 		txtIndirizzo.setText(rs.getString(7));
	 		txtCitta.setText(rs.getString(8));
	 		txtTelefono.setText(rs.getString(9));
	 		txtCellulare.setText(rs.getString(10));
	 		txtEmail.setText(rs.getString(11));
	 		txtNote.setText(rs.getString(12));
	 		txtIDRisorse.setText(rs.getString(13));
	 		txtSesso.setText(rs.getString(14));
	 		uncheckedLists();
	 		
	 		
	 		
	 		CheckListItem cli;
	 		for (String s:DBadapter.getAnagBisogni(txtID.getText()))
	 			for (int i=0;i<lmBisogni.getSize();i++){
	 				cli =(CheckListItem)lmBisogni.getElementAt(i);
	 				if (cli.toString().split("-")[0].equals(s.split("-")[0])){ //$NON-NLS-1$ //$NON-NLS-2$
	 					
	 					cli.setSelected(true);
	 				}
	 					
	 			}
	 		
	 		
	 		for (String s:DBadapter.getAnagRisorse(txtID.getText()))
	 			for (int i=0;i<lmRisorse.getSize();i++){
	 				cli =(CheckListItem)lmRisorse.getElementAt(i);
	 				if (cli.toString().split("-")[0].equals(s.split("-")[0])) 
	 					cli.setSelected(true);
	 			}
	 		
	 		rs.close();
	 		refreshLists();
	 	}catch (Exception sqe) {sqe.printStackTrace();}
	        
	 }

	private void refreshLists() {
		JListBisogni.setCellRenderer(new CheckListRenderer());
		JListBisogni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JListBisogni.addMouseListener(CheckListItem.ma);
		lmBisogni =JListBisogni.getModel();
		JListRisorse.setCellRenderer(new CheckListRenderer());
		JListRisorse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JListRisorse.addMouseListener(CheckListItem.ma);
		lmRisorse =JListRisorse.getModel();
	}
	
	MouseListener listenerClickTableBisogni = new MouseAdapter() {
	     public void mousePressed(MouseEvent e) {
	         int selRow = table.rowAtPoint (new Point (e.getX(), e.getY()));
	         selectedRow=selRow;
	         if(selRow != -1) {
	              if(e.getClickCount() == 1) {
	              	 
	              	 selezionaDati(RICERCA_BISOGNI,selectedRow);
	              	 
	              }
	
	        }
	    }
	 };	
	 
	 MouseListener listenerClickTableRisorse = new MouseAdapter() {
	     public void mousePressed(MouseEvent e) {
	         int selRow = table1.rowAtPoint (new Point (e.getX(), e.getY()));
	         selectedRow=selRow;
	         if(selRow != -1) {
	              if(e.getClickCount() == 1) {
	              	 
	              	 selezionaDati(!RICERCA_BISOGNI,selectedRow);
	              	 
	              }
	
	        }
	    }
	 };	
	 
	 ActionListener actionListenerbNuovo =new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				isNuova=true;
				txtID.setText(""); //$NON-NLS-1$
		 		//txtID.setText(Long.toString(rs.getLong("APP.BISOGNI.ID")));
		 		txtNome.setText(""); //$NON-NLS-1$
		 		txtCognome.setText(""); //$NON-NLS-1$
		 		txtEta.setText(""); //$NON-NLS-1$
		 		txtProfessione.setText(""); //$NON-NLS-1$
		 		txtAppartenenza.setText(""); //$NON-NLS-1$
		 		txtIndirizzo.setText(""); //$NON-NLS-1$
		 		txtCitta.setText(""); //$NON-NLS-1$
		 		txtTelefono.setText(""); //$NON-NLS-1$
		 		txtCellulare.setText(""); //$NON-NLS-1$
		 		txtEmail.setText(""); //$NON-NLS-1$
		 		txtNote.setText("");//null //$NON-NLS-1$
		 		txtIDRisorse.setText("");//null //$NON-NLS-1$
		 		
		 		//refreshLists();
		 		uncheckedLists();
				
			}
		};
		
		ActionListener actionListenerbCancella =new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				int result =JOptionPane.showConfirmDialog(null,Messages.getString("JPanelAnagrafica.45")); //$NON-NLS-1$
				if (result==JOptionPane.YES_OPTION) {
					
					String ID= (String)table.getValueAt(table.getSelectedRow(), 0);
					DBadapter.deleteItemAnagrafica(ID);
					setTabella(RICERCA_BISOGNI,AGGIORNA,"","APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		};
		
		ActionListener actionListenerbSalva =new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				ListModel lmodel= JListBisogni.getModel();
			
		 		CheckListItem cli;
		 		List<Integer> listIDBisogni = new ArrayList<Integer>();
		 		for (int i=0;i<lmodel.getSize();i++){
		 			cli =(CheckListItem)lmodel.getElementAt(i);
		 			if (cli.isSelected()){
		 				
		 				listIDBisogni.add(Integer.parseInt(cli.toString().split("-")[0])); //$NON-NLS-1$
		 			}
		 				
		 		}
		 		ListModel lmodel1= JListRisorse.getModel();
		 		List<Integer> listIDRisorse = new ArrayList<Integer>();
		 		for (int i=0;i<lmodel1.getSize();i++){
		 			cli =(CheckListItem)lmodel1.getElementAt(i);
		 			if (cli.isSelected()){
		 				listIDRisorse.add(Integer.parseInt(cli.toString().split("-")[0])); //$NON-NLS-1$
		 			}
		 				
		 		}
					
					if (isNuova) {
							isNuova=false;
							
							txtID.setText(Long.toString(DBadapter.createItemAnagrafica(txtNome.getText(), txtCognome.getText(), 
									txtEta.getText(), 
									txtSesso.getText(),txtProfessione.getText(), txtAppartenenza.getText(), txtIndirizzo.getText(), 
									txtCitta.getText(), txtTelefono.getText(), txtCellulare.getText(), 
									txtEmail.getText(), txtNote.getText(),txtIDRisorse.getText(), listIDRisorse, listIDBisogni)));
							
							
						
					} else {
						
						
						
						long id_anagrafica= Long.parseLong((String)table.getValueAt(table.getSelectedRow(), 0));
						DBadapter.updateItemAnagrafica(id_anagrafica,txtNome.getText(), txtCognome.getText(), txtEta.getText(), 
								txtSesso.getText(),txtProfessione.getText(), txtAppartenenza.getText(), txtIndirizzo.getText(), 
								txtCitta.getText(), txtTelefono.getText(), txtCellulare.getText(), 
								txtEmail.getText(), txtNote.getText(), txtIDRisorse.getText(), listIDRisorse, listIDBisogni);
						
					}
					setTabella(RICERCA_BISOGNI,AGGIORNA,"","APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
					setTabella(!RICERCA_BISOGNI,AGGIORNA,"","APP.ANAGRAFICA.COGNOME"); //$NON-NLS-1$ //$NON-NLS-2$
					JPanelRisorseSoddisf.updateCombo();

			}
		};
		
		private void uncheckedLists(){
			
			CheckListItem cli;
	 			for (int i=0;i<lmBisogni.getSize();i++){
	 				cli =(CheckListItem)lmBisogni.getElementAt(i);
	 					cli.setSelected(false);
	 			}
	 		
	 			for (int i=0;i<lmRisorse.getSize();i++){
	 				cli =(CheckListItem)lmRisorse.getElementAt(i);
	 					cli.setSelected(false);
	 			}
	
		}
		
		/*public static void selectRadioButtonRicerca(){
			panelAnagrafica.rbRicerca.setSelected(true);
			 
		 }*/
		
		}
