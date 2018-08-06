package main.modes.server.hci;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SrvUrlConnectPanel {

	private JPanel panel;
	private JButton copyButton; 
	private JTextArea urlArea;
	
	
	public SrvUrlConnectPanel() {
		createPanel();
	} 
	
	private void createPanel(){ 
		panel = new JPanel();
		copyButton = new JButton(); 
		urlArea = new JTextArea();
	}
	
	
	public JPanel getPanel() {
		return panel;
	}
}
