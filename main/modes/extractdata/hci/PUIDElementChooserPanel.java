package main.modes.extractdata.hci;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * IN PROGRESS: 
 * Zoomability 
 * Hover changes colour
 * alternating background colour
 * commenting 
 * unit tests
 * 
 * @author chno
 *
 */

public class PUIDElementChooserPanel implements MouseMotionListener {
	JPanel panel;
	public JButton next;
	Map<String, Integer> rowsMap;
	Map<String,String> descriptionMap;
	JList elementsList;
	List<String> selectedItems;
	JLabel label;

	public PUIDElementChooserPanel(Map<String, Integer> rowsMap, Map<String, String> descriptionMap) {
		this.rowsMap = rowsMap;
		this.descriptionMap = descriptionMap;
		selectedItems = new ArrayList<String>();
		panel = new JPanel(new BorderLayout());
		next = new JButton("next");
		createPanel();
	}

	private void createPanel() {
		instantiateList();
		label = new JLabel("Choose PUIDs :");

		panel.add(label, BorderLayout.NORTH);
		
		panel.add(new JScrollPane(elementsList), BorderLayout.CENTER);

		panel.add(next, BorderLayout.SOUTH);
	}

	private void instantiateList() {
		elementsList = new JList<>(getPUIDList().toArray());	
		elementsList.setCellRenderer(getRenderer());
		listUI();
		listBehaviours();
	}
	
	private void listUI(){ 
		Font f = new Font("Arial", Font.PLAIN, 20);
		elementsList.setFont(f);
		elementsList.setVisibleRowCount(10);
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
	

	
	private void listBehaviours(){ 
		elementsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		elementsList.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				} else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});

		elementsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedItems = elementsList.getSelectedValuesList();
			}
		});
		
		elementsList.addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/***
	 * Mouse motion Listener (For JList)
	 * @param e
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		JList l = (JList)e.getSource();
		ListModel m = l.getModel();
		int index = l.locationToIndex(e.getPoint());
		
		if(index > -1){ 
			l.setToolTipText(formatToolTipText(m.getElementAt(index).toString() , descriptionMap.get(m.getElementAt(index).toString())));
		}
	}
	
	/**
	 * Formats tooltip text so longer strings dont sprawl off screen
	 * formats into style using html tags.
	 * 
	 * @param puid
	 * @param description
	 * @return
	 */
	private String formatToolTipText(String puid, String description){ 
		String[] strs = description.split(" ");
		String returnStr = "<html>";
		returnStr = returnStr + "<b>" + puid +"</b>" + "<br><hr>";
		for(int i = 0; i < strs.length; i++){ 
			returnStr = returnStr + strs[i] + " ";
			if(((i%10) == 0) && (i != 0)){ 
				returnStr = returnStr + "<br>";
			}
		}
		return returnStr + "</html>";
	}
	
	/***********************************************************
	 * Getters & Setters
	 ***********************************************************/
	
	public List<String> getPUIDList() {
		return new ArrayList<String>(rowsMap.keySet());
	}

	public JPanel getPanel() {
		panel.setBorder(new TitledBorder("Choose desired Elements :"));
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public List<String> getSelectedItems() {
		return selectedItems;
	}

}
