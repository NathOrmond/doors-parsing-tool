package main.global.hci.frame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import main.global.runtime.RunModes;



public class MainMenuBar {

	/**
	 * ToDo 
	 * 
	 * Home Menu Item click event doesnt link up
	 */
	
	private JMenuBar mb;
	private JMenu navigate, instructions;
	private List<JMenuItem> navigateItems;
	public JMenuItem extract, serve, commit, extractInstructions, home;
	
	public MainMenuBar() {
		mb = new JMenuBar();
		 
		setModes(); 
		setInstructions();
		
		/** Adds elements to menu Bar **/ 
		mb.add(navigate);
		mb.add(instructions);
	}
	
	private void setModes(){ 
		/** Navigates between modes**/
		navigate = new JMenu("nav");
		extract = new JMenuItem("Extract");
		extract.setActionCommand(RunModes.extract_mode);
		serve = new JMenuItem("Serve");
		serve.setActionCommand(RunModes.serve_mode);
		commit = new JMenuItem("Commit");
		commit.setActionCommand(RunModes.commit_mode);
		home = new JMenuItem("Home");
		home.setActionCommand(RunModes.home);
		
		
		navigateItems = new ArrayList<JMenuItem>();
		navigateItems.add(home);
		navigateItems.add(extract);
		navigateItems.add(serve);
		navigateItems.add(commit);
		addItemsToMenu(navigate, navigateItems);
	}
	
	private void setDebugger(){ 
		
	}
	
	
	private void setInstructions(){ 
		/** Navigates between instructions**/
		instructions = new JMenu("Instructions");
		extractInstructions = new JMenuItem("Extraction Instructions");
		extractInstructions.setActionCommand(RunModes.extract_instructions);
		instructions.add(extractInstructions);
	}

	private void addItemsToMenu(JMenu menu, List<JMenuItem> items) {
		for (JMenuItem item : items) {
			menu.add(item);
		}
	}

	public JMenuBar getMb() {
		return mb;
	}

}
