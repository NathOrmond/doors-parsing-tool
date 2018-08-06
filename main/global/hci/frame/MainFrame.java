package main.global.hci.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public MainMenuBar mb;

	/**
	 * Initialises frame, 
	 * Declares frames properties which will stay the same throughout the programme.
	 * @param panel
	 */
	public MainFrame(JPanel panel) {
		setTheMenu();
		this.panel = panel;
		setTitle("DOORS test tool");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		setResizable(false);
		pack();
	}

	/**
	 * Called to update the frames 
	 * @param panel
	 */
	public void updateFrame(JPanel panel) {
		removeFrame();
		this.panel = panel;
		add(panel);
		repaint();
		pack();
	}

	
	public void removeFrame() {
		remove(panel);
	}

	/**
	 * Adds menu bar to frame (permanent)
	 */
	private void setTheMenu() {
		mb = new MainMenuBar();
		setJMenuBar(mb.getMb());
	}

}
