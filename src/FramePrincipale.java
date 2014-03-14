

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ProgressMonitor;

@SuppressWarnings("serial")
public class FramePrincipale extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	try {
		
		avviaProgramma();
			
	} catch (Exception ex) {System.out.println(ex);}
	}    

	public static void avviaProgramma() {
		try {	
			Object[] message = new Object[2]; 
			String[] options = { 
					"Ok","Annulla" 
			}; 
			JPasswordField password;
			message[0]="Digitare la passsword:";
			message[1]=password=new JPasswordField(20);

			int result = JOptionPane.showOptionDialog( 
					null,                             // the parent that the dialog blocks 
					message,                                    // the dialog message array 
					"Informazioni per l'utente", // the title of the dialog window 
					JOptionPane.DEFAULT_OPTION,                 // option type 
					JOptionPane.INFORMATION_MESSAGE,            // message type 
					null,                                       // optional icon, use null to use the default icon 
					options,                                    // options string array, will be made into buttons 
					options[0]                                  // option that should be made into a default button 
			); 
			
			switch(result) {
			case 0:
				int c=0;
				for (char ch:password.getPassword())
					c+=Character.getNumericValue(ch);
				//System.out.println(c);
				if (c==121){
					final int Max = 5; 
					final ProgressMonitor wPM = new ProgressMonitor(null, 
							"Caricamento in corso", "", 0, Max); 
					wPM.setProgress(0); 
					//wPM.setMillisToDecideToPopup(1); 
					// richiesta senza attesa di risposta 


					final Runnable t = new Runnable() { 
						public void run() { 
							for ( int i = 0; i<Max; i++) { 
								wPM.setProgress(i); 
								try { 
									Thread.sleep(1000); 
								} 
								catch (InterruptedException ex) { 
								} 
							} 
						} 
					}; 
					Thread myt = new Thread(t); 
					myt.start(); 



					//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					//Rectangle screenRect =  ge.getMaximumWindowBounds();


					DBadapter.createConnection();
					FramePrincipale frame = new FramePrincipale();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					WindowListener l = new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							DBadapter.shutdown(); 
							
						}
					};
					frame.addWindowListener(l);	
					wPM.close();
					myt.stop();
					
				}
				break;
			case 1:
			 
				break; 
			

			} 

	
		} catch (Exception ex) {ex.printStackTrace();}
	}
	
	
	public FramePrincipale() {
			
		super();
		setTitle("Rilevazione Dati Bisogni e Risorse");
    	JPanelPrincipale panel1 = new JPanelPrincipale();
    	getContentPane().add(panel1, BorderLayout.CENTER);
    	pack();
    	setVisible(true);
 		//setExtendedState(Frame.MAXIMIZED_BOTH);

    }

	 

     
}
