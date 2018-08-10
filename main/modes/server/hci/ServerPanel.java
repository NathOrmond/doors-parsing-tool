package main.modes.server.hci;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import main.global.hci.FileChooser;
import main.modes.server.run.ServerMain;

public class ServerPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String path = "";
	private JLabel titleLabel, pathAreaLabel;
	private JTextArea pathArea, srvStatus; 
	private JButton newPath, copySrvLink, start, stop; 
	GridBagLayout mgr;
	GridBagConstraints gbc;
	ServerMain srv;
	Thread t;
	

	public ServerPanel() {
		path = "none chosen";
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(mgr);

		createPanel();
	}
	
	public ServerPanel(String path) {
		this.path = path;
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(mgr);
		createPanel();
	}
	
	private void createPanel(){ 
		title();
		fileChoose();
		startStop();
	}
	
	private void title() { 
		titleLabel = new JLabel("Server");
		addComponentToPanel(titleLabel, 0, 0, gbc.NORTH);
	}
	
	private void fileChoose() { 
		pathAreaLabel = new JLabel("serve file :");
		
		pathArea = new JTextArea(path); 
		
		newPath = new JButton("new"); 
		newPath.setActionCommand(SrvButtonCommands.new_file);
		newPath.addActionListener(this);
		
		copySrvLink = new JButton("copy url");
		copySrvLink.setActionCommand(SrvButtonCommands.copy);
		copySrvLink.addActionListener(this);
		
		addComponentToPanel(pathAreaLabel, 0, 1, gbc.SOUTH);
		addComponentToPanel(pathArea, 0, 2, gbc.LINE_START);
		addComponentToPanel(newPath, 1, 2, gbc.LINE_START);
		addComponentToPanel(copySrvLink, 2, 2, gbc.LINE_START);
	}
	
	private void startStop() { 
		start = new JButton("start");
		start.setActionCommand(SrvButtonCommands.start_srv);
		addComponentToPanel(start, 1, 3, gbc.LINE_START);
		start.addActionListener(this);
		stop = new JButton("stop");
		stop.setActionCommand(SrvButtonCommands.stop_srv);
		addComponentToPanel(stop, 2, 3, gbc.LINE_START);
		stop.addActionListener(this);
		
		srvStatus= new JTextArea("not started");
		addComponentToPanel(srvStatus, 0, 3, gbc.LINE_START);
	}
	
	private void addComponentToPanel(Component c, int x, int y, int anchor){ 
		gbc.anchor = anchor;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mgr.setConstraints(c, gbc);
		this.add(c);
	}
	
	public JPanel getPanel() {
		this.setBorder(new TitledBorder("Server Controls"));
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) { 
		
			case SrvButtonCommands.new_file:
				FileChooser chooser = new FileChooser();
				setPath(chooser.selectPath());
				pathArea.setText("localhost:8080/" + path);
				break;
				
			case SrvButtonCommands.copy:
				break;
				
			case SrvButtonCommands.start_srv:
				srv = new ServerMain(path, 8080);
				srv.startServer();
				break;
				
			case SrvButtonCommands.stop_srv:
				
				break;	
		
			default: 
				break;
		}
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	

}
