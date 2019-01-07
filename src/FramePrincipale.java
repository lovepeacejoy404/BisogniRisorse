

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
			
	} catch (Exception ex) {System.out.println(ex);}
	}    

	
	
	
	public FramePrincipale() {
			
		super();
		setTitle(Messages.getString("FramePrincipale.0")); //$NON-NLS-1$
    	JPanelPrincipale panel1 = new JPanelPrincipale();
    	getContentPane().add(panel1, BorderLayout.CENTER);
    	pack();
    	setVisible(true);
 		//setExtendedState(Frame.MAXIMIZED_BOTH);

    }

	 

     
}
