package main.modes.extractdata.hci;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.global.hci.ButtonClickEvents;
import main.modes.extractdata.csvparsing.CSVParsing;



public class CSVElementChooserPanel extends CSVParsing {

	/**
	 * IN PROGRESS: 
	 * Zoomability 
	 * Hover changes colour
	 * alternating background colour
	 * commenting 
	 * unit tests
	 * 
	 * @author chno
	 */
	
	public JButton next;
	JPanel panel;
	String urlIn, urlOut;
	JLabel label;
	public List<String> selectedColumns, selectedPUIDS;
	public JList headerList;

	/**
	 * 
	 * @param urlIn
	 * @param urlOut
	 */
	public CSVElementChooserPanel(String urlIn, String urlOut) {
		super(urlIn);
		panel = new JPanel();
		this.urlIn = urlIn;
		this.urlOut = urlOut;
		createPanel(getHeadingsForJList());
		selectedColumns = new ArrayList<String>();
		selectedPUIDS = new ArrayList<String>();
	}

	/**
	 * 
	 * @param header
	 */
	private void createPanel(List<String> header) {
		label = new JLabel("Choose Rows to Include :");
		next = new JButton("next");
		headerList = new JList<>(header.toArray());
		headerList.setCellRenderer(getRenderer());
		headerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listUI();
		headerList.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				} else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});

		headerList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedColumns = headerList.getSelectedValuesList();
			}
		});

		JScrollPane jspOne = new JScrollPane(headerList);
		

		GridBagLayout mgr = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(mgr);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(label,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		mgr.setConstraints(jspOne, gbc);
		panel.add(jspOne);

		gbc.gridx = 0;
		gbc.gridy = 2;
		mgr.setConstraints(next, gbc);
		panel.add(next);
	}
	
	private void listUI(){ 
		Font f = new Font("Arial", Font.PLAIN, 20);
		headerList.setFont(f);
		headerList.setVisibleRowCount(10);
	}
	
	private ListCellRenderer<? super String> getRenderer(){ 
		return new DefaultListCellRenderer(){ 
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}
			
		};
	}
	
	/***********************************************************
	 * Getters & Setters
	 ***********************************************************/
	public JPanel getPanel() {
		panel.setBorder(new TitledBorder("Choose desired elements :"));
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public List<String> getSelectedColumns() {
		return selectedColumns;
	}
}
