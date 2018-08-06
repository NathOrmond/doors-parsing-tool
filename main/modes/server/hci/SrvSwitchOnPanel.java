package main.modes.server.hci;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SrvSwitchOnPanel {
	
	JPanel panel;
	JButton button; 
	
	public SrvSwitchOnPanel() {
		createPanel();
	}
	
	private void createPanel(){ 
		panel = new JPanel(); 
		button = new JButton(); 
		
		panel.add(button);
	}
	
	public JPanel getPanel() {
		return panel;
	}

}
