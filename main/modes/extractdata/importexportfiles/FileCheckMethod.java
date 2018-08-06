package main.modes.extractdata.importexportfiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileCheckMethod {

	/**
	 * @param fileName
	 * @return whether or not a passed file location exists
	 */
	public boolean exists(String fileName) {
		if (fileName != null) {
			File f = new File(fileName);
			if (f.exists() || f.isDirectory()) {
				return true;
			}

		}
		noFileFoundMethod();
		return false;
	}

	/**
	 * When no file is found pops up a window 
	 * showing no file was found
	 */
	private void noFileFoundMethod() {
		JFrame frame = new JFrame();
		JLabel label = new JLabel("file path not found");
		JButton button = new JButton("ok");
		JPanel panel = new JPanel();
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		panel.add(label);
		panel.add(button);

		frame.setVisible(true);
		frame.setTitle("WARNING");
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}
}
