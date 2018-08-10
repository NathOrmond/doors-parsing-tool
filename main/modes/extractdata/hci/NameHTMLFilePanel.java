package main.modes.extractdata.hci;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import main.modes.extractdata.DataPointer;





public class NameHTMLFilePanel {
	DataPointer dp;
	JPanel panel;
	public JTextArea area;
	public JButton next;
	private String areaStr = "insert a file name: ";

	public NameHTMLFilePanel() {
		createPanel();
	}

	private void createPanel() {
		instantiateElements();

		GridBagLayout mgr = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(mgr);

		gbc.gridx = 0;
		gbc.gridy = 0;
		mgr.setConstraints(area, gbc);
		panel.add(area);

		gbc.gridx = 0;
		gbc.gridy = 1;
		mgr.setConstraints(next, gbc);
		panel.add(next);
	}

	private void instantiateElements() {
		panel = new JPanel();
		next = new JButton("next");
		area = new JTextArea(areaStr);
		area.setColumns(25);
		
		area.addMouseListener(new MouseAdapter(){
		 @Override 
		 public void mouseClicked(MouseEvent e){ 
			 if(area.getText().equals(areaStr)){ 
				 area.setText("");
			 }
		 }
		});

	}

	public JPanel getPanel() {
		panel.setBorder(new TitledBorder("Set output file name :"));
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
