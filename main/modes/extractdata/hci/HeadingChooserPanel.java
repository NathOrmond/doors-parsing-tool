package main.modes.extractdata.hci;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import main.global.hci.frame.AbstractElementChooserPanel;
import main.modes.extractdata.csvparsing.CSVParsing;



public class HeadingChooserPanel extends AbstractElementChooserPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String urlIn, urlOut;
	public List<String> selectedColumns;
	public CSVParsing csvParsing;

	public HeadingChooserPanel(String urlIn) {
		super(new CSVParsing(urlIn).getHeadingsForJList(), "Choose Desired Headings");
		csvParsing = new CSVParsing(urlIn);
		selectedColumns = new ArrayList<String>();	
	}

	public List<String> getSelectedColumns() {
		return selectedColumns;
	}

	@Override
	public void setListBehviours() {
	list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
	list.setSelectionModel(new DefaultListSelectionModel() {
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				} else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});

	list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedColumns = list.getSelectedValuesList();
			}
		});
		
	}
}
