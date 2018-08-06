package main.global.debugger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class DebugFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public DebugFrame(String logInfo) {
		createPanel(logInfo);
		setTitle("Logger");
		setLocationRelativeTo(null);
		add(panel);
		setVisible(true);
		pack();
	}

	private void createPanel(String logInfo) {

		panel = new JPanel();
		JLabel label = new JLabel("Log info :");
		JTextArea area = new JTextArea(logInfo);

		panel.add(label);
		panel.add(area);
	}

	public void updateFrame(JPanel panel) {
		removeFrame();
		this.panel = panel;
		add(panel);
		pack();
	}

	public void removeFrame() {
		remove(panel);
	}



}
