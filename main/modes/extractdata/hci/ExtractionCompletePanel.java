package main.modes.extractdata.hci;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class ExtractionCompletePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JTextArea tArea;
	public JButton copy;
	private String url;
	private GridBagLayout mgr;
	private GridBagConstraints gbc;
	
	public ExtractionCompletePanel(String url) {
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		this.setLayout(mgr);
		this.url = ("file:///" + url).replace("\\", "/");
		title = new JLabel("Path to created file :\n"
						 + "past path into file explorer to open in browser");
		tArea = new JTextArea(this.url);
		tArea.setEditable(false);
		copy = new JButton("copy");
		copy.setActionCommand(this.url);
		copy.addActionListener(this);
		createPanel();
	}
	
	private void createPanel(){ 
		
		/** Adds title to panel **/
		gbc.anchor = gbc.FIRST_LINE_START;
		addComponentToPanel(title, 0, 0);
		
		/** Adds visible url textArea to panel **/
		gbc.anchor = gbc.FIRST_LINE_START;
		addComponentToPanel(tArea,0,1);
		
		/** Adds copy button to panel **/
		gbc.anchor = gbc.FIRST_LINE_START;
		addComponentToPanel(copy,1,1);
		
	}
	
	/**
	 * adds new component to panel dependent on x and y grid 
	 * (GridBagConstraint Layout mgr)
	 * @param c
	 * @param x
	 * @param y
	 */
	private void addComponentToPanel(Component c, int x, int y){ 
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mgr.setConstraints(c, gbc);
		this.add(c);
	}
	
	public JPanel getPanel(){ 
		setBorder(new TitledBorder("Extaction Complete"));
		return this;
	}

	/**
	 * Copies action command of event to OS clipboard
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		StringSelection selection = new StringSelection(e.getActionCommand());
		Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipBoard.setContents(selection, selection);	
	}

}
