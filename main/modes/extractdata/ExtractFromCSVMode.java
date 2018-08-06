package main.modes.extractdata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.global.hci.ButtonClickEvents;
import main.global.hci.frame.MainFrame;
import main.modes.extractdata.createwebpage.StringFromCSV;
import main.modes.extractdata.hci.NameHTMLFilePanel;
import main.modes.extractdata.hci.ExtractionCompletePanel;
import main.modes.extractdata.hci.HeadingChooserPanel;
import main.modes.extractdata.hci.PUIDElementChooserPanel;
import main.modes.extractdata.hci.SelectWorkingFilePanel;
import main.modes.extractdata.importexportfiles.FileCheckMethod;

public class ExtractFromCSVMode extends DataPointer implements ActionListener {

	MainFrame main;
	SelectWorkingFilePanel selectPanel;
	HeadingChooserPanel headingChoosePanel;
	PUIDElementChooserPanel puidPanel;
	NameHTMLFilePanel htmlFilePanel;
	StringFromCSV stringCSV;
	public ExtractionCompletePanel exComplete;

	public ExtractFromCSVMode(MainFrame main) {
		super();
		this.main = main;
		selectCSVFileLocationPanel();
	}

	/**
	 * Calls action performed switch for each action
	 * this class is a listener of.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		runForMode(e.getActionCommand());
	}

	/**
	 * Switch for different button commands 
	 * calls methods in correct sequence.
	 * @param command
	 */
	public void runForMode(String command) {
		switch (command) {


		case ButtonClickEvents.inputs:
			selectCSVFileLocationPanel();
			break;

		case ButtonClickEvents.filename:
			FileCheckMethod fileCheck = new FileCheckMethod();
			if (fileCheck.exists(selectPanel.getUrl())) {
				enterFileName();
			}
			break;

		case ButtonClickEvents.selectcolmns:
			selectDesiredColumns();
			break;

		case ButtonClickEvents.selectpuids:
			selectDesiredPUIDS();
			break;

		case ButtonClickEvents.writehtml:
			createWebPage();
			break;

		case ButtonClickEvents.packFrame:
			main.pack();
			break;
		}
	}
	
	/*****************************************************************************************************
	 * 		SWITCH METHODS
	 *****************************************************************************************************/

	/**
	 * 1
	 * 
	 * Creates select working file panel, updates the main frame 
	 * with the panel. 
	 * Makes this a listener of the "next" button.
	 */
	private void selectCSVFileLocationPanel() {
		selectPanel = new SelectWorkingFilePanel();
		main.updateFrame(selectPanel.getPanel());
		selectPanel.next.addActionListener(this);
		selectPanel.next.setActionCommand(ButtonClickEvents.filename);
	}

	/**
	 * 2
	 * 
	 * Sets the URL in and out from completed file location panel
	 * creates file name panel and refreshes main frame with panel. 
	 * makes this an action listener of the next step button on the panel. 
	 * 
	 */
	private void enterFileName() {
		setUrlIn(selectPanel.getUrl());
		setUrlOut(selectPanel.getUrlOut());
		htmlFilePanel = new NameHTMLFilePanel();
		htmlFilePanel.next.addActionListener(this);
		htmlFilePanel.next.setActionCommand(ButtonClickEvents.selectcolmns);
		main.updateFrame(htmlFilePanel.getPanel());
	}

	/**
	 * 3
	 * 
	 * sets the file name to text from previous pane
	 * creates new csv element chooser panel and refreshes the frame with it
	 * updates the headings and rows Maps based off of the read CSV file 
	 * makes this class a listener for the next step button on the panel.
	 */
	private void selectDesiredColumns() {
		setFileName(htmlFilePanel.area.getText());
		headingChoosePanel = new HeadingChooserPanel(getUrlIn());
		main.updateFrame(headingChoosePanel.getPanel());
		setHeadingsMap(headingChoosePanel.csvParsing.getHeadingsMap());
		setRowsMap(headingChoosePanel.csvParsing.getPuidMap());
		setDescriptionsMap(headingChoosePanel.csvParsing.getDescriptionsMap());
		headingChoosePanel.next.addActionListener(this);
		headingChoosePanel.next.setActionCommand(ButtonClickEvents.selectpuids);
	}

	/**
	 * 4
	 * 
	 * sets the selected columns based off of data in last panel
	 * creates new PUID chooser panel
	 * updates the frame with the new panel
	 * makes this a listener for the next action command button
	 */
	private void selectDesiredPUIDS() {
		setSelectedColumns(headingChoosePanel.getSelectedColumns());
		puidPanel = new PUIDElementChooserPanel(getRowsMap(), getDescriptionsMap());
		main.updateFrame(puidPanel.getPanel());
		puidPanel.next.addActionListener(this);
		puidPanel.next.setActionCommand(ButtonClickEvents.writehtml);
	}

	/**
	 * 5
	 * 
	 * sets the selected puids from previous panel
	 * runs method to create web page 
	 */
	private void createWebPage() {
		setSelectedPUIDS(puidPanel.getSelectedItems());
		stringCSV = new StringFromCSV(this);
		exComplete = new ExtractionCompletePanel(getUrlOut() + "/" + getFileName() + ".html");
		main.updateFrame(exComplete.getPanel());
		
	}
}
