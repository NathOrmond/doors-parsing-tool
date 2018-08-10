package main.global.hci.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

public abstract class AbstractElementChooserPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	public JList list;
	public JButton next;
	GridBagLayout mgr;
	GridBagConstraints gbc;
	
	
	public AbstractElementChooserPanel(List<String> list2, String labelText) {
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(mgr);
		label = new JLabel(labelText);
		next = new JButton("next");
		list = new JList<>(list2.toArray());
		
		setGraphicsAndStyling();
		setListBehviours();
		
		addAllElementsToPanel();
	}
	
	private void addAllElementsToPanel() { 
		gbc.anchor = GridBagConstraints.NORTH;
		addComponentToPanel(label, 0, 0);
		addComponentToPanel(new JScrollPane(list), 0, 1);
		addComponentToPanel(next, 0, 2);
	}
	
	private void addComponentToPanel(Component c, int x, int y){ 
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		mgr.setConstraints(c, gbc);
		this.add(c);
	}
	
	private void setGraphicsAndStyling() { 
		Font f = new Font("Arial", Font.PLAIN, 20);
		list.setFont(f);
		list.setVisibleRowCount(10);
		list.setCellRenderer(getRenderer());
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
	
	public abstract void setListBehviours();

	public JPanel getPanel() {
		this.setBorder(new TitledBorder("Choose desired Elements :"));
		return this;
	}

}
