package main.modes.extractdata.hci;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import main.global.hci.ButtonClickEvents;
import main.global.hci.FileChooser;



public class SelectWorkingFilePanel {
	private int columns = 40;
	private JPanel panel;
	public JTextArea areaIn, areaOut;
	private JLabel inLabel, outLabel;
	public JButton browseIn, browseOut, next;
	private String urlIn, urlOut;
	private String fileLocationText = "file location... \n";

	public SelectWorkingFilePanel() {
		panel = new JPanel();
		createWorkingFilePanel();
	}

	private void createWorkingFilePanel() {

		inLabel = new JLabel("input file location");

		areaIn = new JTextArea(fileLocationText);
		areaIn.setEditable(true);
		areaIn.setColumns(columns);
		areaIn.setLineWrap(true);

		browseIn = new JButton("browse");
		browseIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser chooser = new FileChooser();
				setUrl(chooser.selectPath());
				areaIn.setText(urlIn);
			}
		});
		browseIn.setActionCommand(ButtonClickEvents.packFrame);

		outLabel = new JLabel("output file location");

		areaOut = new JTextArea(fileLocationText);
		areaOut.setEditable(true);
		areaOut.setColumns(columns);
		areaOut.setLineWrap(true);

		browseOut = new JButton("browse");
		browseOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser chooser = new FileChooser();
				setUrlOut(chooser.selectFolder());
				areaOut.setText(urlOut);
			}
		});
		browseOut.setActionCommand(ButtonClickEvents.packFrame);

		next = new JButton("next");
		

		GridBagLayout mgr = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(mgr);

		gbc.gridx = 0;
		gbc.gridy = 0;
		mgr.setConstraints(inLabel, gbc);
		panel.add(inLabel);

		gbc.gridx = 0;
		gbc.gridy = 1;
		mgr.setConstraints(areaIn, gbc);
		panel.add(areaIn);

		gbc.gridx = 1;
		gbc.gridy = 1;
		mgr.setConstraints(browseIn, gbc);
		panel.add(browseIn);

		gbc.gridx = 0;
		gbc.gridy = 2;
		mgr.setConstraints(outLabel, gbc);
		panel.add(outLabel);

		gbc.gridx = 0;
		gbc.gridy = 3;
		mgr.setConstraints(areaOut, gbc);
		panel.add(areaOut);

		gbc.gridx = 1;
		gbc.gridy = 3;
		mgr.setConstraints(browseOut, gbc);
		panel.add(browseOut);

		gbc.gridx = 1;
		gbc.gridy = 4;
		mgr.setConstraints(next, gbc);
		panel.add(next);
		
		panel.setBorder(new TitledBorder("Set input and output locations :"));

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public String getUrl() {
		return urlIn;
	}

	public void setUrl(String url) {
		this.urlIn = url;
	}

	public String getUrlOut() {
		return urlOut;
	}

	public void setUrlOut(String urlOut) {
		this.urlOut = urlOut;
	}
}
