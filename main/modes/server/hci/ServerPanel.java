package main.modes.server.hci;

import javax.swing.JPanel;

public class ServerPanel {
	
	JPanel panel; 
	
	public ServerPanel() {
		createPanel();
	}
	
	private void createPanel(){ 
		panel = new JPanel();
		
		SrvFileChoosePanel srvFileChoose = new SrvFileChoosePanel();
		SrvUrlConnectPanel srvUrl = new SrvUrlConnectPanel();
		SrvSwitchOnPanel srvSwitch = new SrvSwitchOnPanel();
		
		panel.add(srvFileChoose.getPanel());
		panel.add(srvUrl.getPanel()); 
		panel.add(srvSwitch.getPanel());
		
	}
	
	public JPanel getPanel() {
		return panel;
	}

}
