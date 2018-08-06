package main.modes.server.run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainServer extends SrvPersistanceVariables implements ActionListener {



	//Select Folder To Serve 
	//Start Serving/Stop 
	//Enter URL 
	
	//Autosave screen scraper
	
	public void actionSwitch(String command){ 
		switch(command){ 
			default: 
				break; 
				
			case "": 
				break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		actionSwitch(e.getActionCommand());
		
	}
	
	public SrvPersistanceVariables getSrvPersistanceVariables(){ 
		return this;
	}
	
	
}
