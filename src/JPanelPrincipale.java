

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
	
	panelTab.addTab("Anagrafica",panelAnagrafica); 
	panelTab.addTab("Bisogni e Risorse",panelBisogniRisorse); 
	panelTab.addTab("Risorse soddisfacenti bisogni degli utenti",panelRisorseSoddisf); 
	panelTab.addTab("Importa/Esporta",panelImporta); 
	add(panelTab,BorderLayout.CENTER);
	
}


}
