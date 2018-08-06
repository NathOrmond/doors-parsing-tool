package main.modes.server.hci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.modes.extractdata.importexportfiles.FileChooser;

public class SrvFileChoosePanel {
	
	private JPanel panel; 
	private String fileLocationText;
	private int columns = 50;
	private String url;
	
	public SrvFileChoosePanel() {
		createPanel();	
	}
	
	private void createPanel(){ 
		panel = new JPanel();
		
		JLabel outLabel = new JLabel("server file location");

		JTextArea areaOut = new JTextArea(fileLocationText);
		areaOut.setEditable(true);
		areaOut.setColumns(columns);
		areaOut.setLineWrap(true);

		JButton browseOut = new JButton("browse");
		browseOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser chooser = new FileChooser();
				setUrl(chooser.selectFolder());
				areaOut.setText(url);
			}
		});
		
		panel.add(outLabel); 
		panel.add(areaOut);
		panel.add(browseOut);
	} 
	
	public JPanel getPanel() {
		return panel;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
