

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JPanelPrincipale extends JPanel{
	
	static JTabbedPane panelTab;
	JPanelAnagrafica panelAnagrafica;
	JPanelRisorseSoddisf panelRisorseSoddisf;
	JPanelBisogniRisorse panelBisogniRisorse;
	JPanelImporta panelImporta;
	
public JPanelPrincipale() {
		
    setLayout(new BorderLayout());
    panelTab = new JTabbedPane ();
	panelTab.setFont(new Font(null,Font.BOLD,16));
	panelAnagrafica= new JPanelAnagrafica();
	panelRisorseSoddisf=new JPanelRisorseSoddisf();
	panelBisogniRisorse = new JPanelBisogniRisorse();
	panelImporta = new JPanelImporta();
	
	panelTab.addTab(Messages.getString("JPanelPrincipale.0"),panelAnagrafica);  //$NON-NLS-1$
	panelTab.addTab(Messages.getString("JPanelPrincipale.1"),panelBisogniRisorse);  //$NON-NLS-1$
	panelTab.addTab(Messages.getString("JPanelPrincipale.2"),panelRisorseSoddisf);  //$NON-NLS-1$
	panelTab.addTab(Messages.getString("JPanelPrincipale.3"),panelImporta);  //$NON-NLS-1$
	add(panelTab,BorderLayout.CENTER);
	
}


}
