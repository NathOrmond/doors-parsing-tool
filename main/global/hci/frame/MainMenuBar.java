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
	
	private JMenu navigate;
	public JMenuItem extract, serve, commit, home;
	private List<JMenuItem> navigateItems;
	
	public JMenu instructions;
	public JMenuItem doorsToCSVExtractInstructions, csvToWebPageInstructions;
	
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
		
		doorsToCSVExtractInstructions = new JMenuItem("How do I convert a DOORS database to CSV?");
		doorsToCSVExtractInstructions.setActionCommand(RunModes.doors_to_csv_extract_instructions);
		instructions.add(doorsToCSVExtractInstructions);
		
		csvToWebPageInstructions = new JMenuItem("How do I use the \"Extract\" tool to create a web page from my DOORS CSV?");
		csvToWebPageInstructions.setActionCommand(RunModes.csv_to_webpage_instructions);
		instructions.add(csvToWebPageInstructions);
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
