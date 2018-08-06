package main.global.hci;


import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import main.global.hci.frame.AbstractInstructionsPanel;
import main.global.hci.frame.MainFrame;
import main.global.runtime.RunModes;
import main.modes.extractdata.ExtractFromCSVMode;
import main.modes.extractdata.hci.CreateWebPageFromCSVInstructions;
import main.modes.extractdata.hci.ExtractCSVFromDoorsInstruction;


public class HomePage extends AbstractAction implements ActionListener  {

	private static final long serialVersionUID = -7176861529737481759L;
	private MainFrame frame;
	private JButton extractModeB, serveModeB, commitModeB;
	private ExtractFromCSVMode fbhv;
	private GridBagLayout mgr;
	private GridBagConstraints gbc;
	private int buttonSizeY = 40, buttonSizeX = 30;

	public HomePage() {
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		frame = new MainFrame(homePanel());
		addMenuBarListeners();
	}
	
	private void addMenuBarListeners(){ 
		frame.mb.extract.addActionListener(this);
		frame.mb.commit.addActionListener(this);
		frame.mb.serve.addActionListener(this);
		frame.mb.home.addActionListener(this);
		frame.mb.doorsToCSVExtractInstructions.addActionListener(this);
		frame.mb.csvToWebPageInstructions.addActionListener(this);
	}

	public JPanel homePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(mgr);

		JLabel titleLabel = new JLabel("<html><h2>Menu:</h2></html>");
		addComponentToPanel(panel, titleLabel, 1, 0);
		
		
		
		JTextArea description = new JTextArea("This tool creates a web page from\n"
											+ "a DOORS CSV and commits changes.\n"
											+ "Select an option from the menu"); 
		description.setBackground(new Color(220, 220, 220));
		description.setEditable(false);
		gbc.anchor = gbc.WEST;
		addComponentToPanel(panel, description, 0, 1);

		extractModeB = new JButton("Extract");
		extractModeB.setActionCommand(RunModes.extract_mode);
		extractModeB.addActionListener(this);
		extractModeB.setToolTipText("Convert DOORS CSV to an interactive web page");
		gbc.anchor = gbc.WEST;
		gbc.ipady = buttonSizeY;
		gbc.ipadx = buttonSizeX;
		addComponentToPanel(panel, extractModeB, 1, 1);

		serveModeB = new JButton("Serve");
		serveModeB.setActionCommand(RunModes.serve_mode);
		serveModeB.addActionListener(this);
		serveModeB.setToolTipText("Serve a created Web Page *NOT YET IMPLEMENTED*");
		gbc.anchor = gbc.WEST;
		gbc.ipady = buttonSizeY;
		gbc.ipadx = buttonSizeX;
		addComponentToPanel(panel, serveModeB, 2, 1);
		serveModeB.setEnabled(false);

		commitModeB = new JButton("Commit");
		commitModeB.setActionCommand(RunModes.serve_mode);
		commitModeB.addActionListener(this);
		commitModeB.setToolTipText("Commit database changes to DOORS *NOT YET IMPLEMENTED*");
		addComponentToPanel(panel, commitModeB, 3, 1);
		gbc.anchor = gbc.WEST;
		gbc.ipady = buttonSizeY;
		gbc.ipadx = buttonSizeX;
		commitModeB.setEnabled(false);

		panel.setBorder(new TitledBorder("Home"));
		return panel;
	}
	
	/**
	 * adds new component to panel dependent on x and y grid 
	 * (GridBagConstraint Layout mgr)
	 * @param c
	 * @param x
	 * @param y
	 */
	private void addComponentToPanel(JPanel panel, Component c, int x, int y){ 
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 15.0;
		gbc.weighty = 15.0;
		mgr.setConstraints(c, gbc);
		panel.add(c);
	}

	/*****************************************************************************************************
	 * 		EVENT LISTENER SWITCH
	 *****************************************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		runForMode(e.getActionCommand());
	}
	
	public void runForMode(String currMode) {
		switch (currMode) {
		case RunModes.extract_mode:
			extractMode();
			break;

		case RunModes.serve_mode:
			serveMode();
			break;

		case RunModes.commit_mode:
			commitMode();
			break;

		case RunModes.home:
			home();
			break;
			
		case RunModes.doors_to_csv_extract_instructions: 
			extractInstructions1();
			break;
			
		case RunModes.csv_to_webpage_instructions: 
			extractInstructions2();
			break;

		default:
			/** not used **/
			break;
		}
	}
	
	/*****************************************************************************************************
	 * 		SWITCH METHODS
	 *****************************************************************************************************/
	
	private void extractMode(){ 
		fbhv = new ExtractFromCSVMode(frame);
	}
	
	private void serveMode(){ 
		
	}
	
	private void commitMode(){ 
		
	}
	
	private void home(){ 
		frame.updateFrame(homePanel());
	}
	
	private void extractInstructions1(){ 
		String resourcePath = "/resource/images/doors_screenshots/";
		String[] files = { "null", "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg" };
		ExtractCSVFromDoorsInstruction instructions = new ExtractCSVFromDoorsInstruction(resourcePath, files); 
		instructions.finishedButton.addActionListener(this);
		frame.updateFrame(instructions.getPanel());
		frame.pack();
	}
	
	private void extractInstructions2(){
		String resourcePath = "/resource/images/extract_screenshots/";
		String[] files = { "null", "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg"};
		CreateWebPageFromCSVInstructions instructions = new CreateWebPageFromCSVInstructions(resourcePath, files); 
		instructions.finishedButton.addActionListener(this);
		frame.updateFrame(instructions.getPanel());
		frame.pack();
	}
	
}
