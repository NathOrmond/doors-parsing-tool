package main.modes.extractdata.hci;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.global.hci.frame.AbstractElementChooserPanel;


public class PUIDElementChooserPanel extends AbstractElementChooserPanel implements MouseMotionListener {
	
	private static final long serialVersionUID = -2477933403318636465L;
	Map<String,String> descriptionMap;
	List<String> selectedItems;

	public PUIDElementChooserPanel(Map<String, Integer> rowsMap, Map<String, String> descriptionMap) {
		super(getPUIDList(rowsMap), "Select Desired Headings");
		this.descriptionMap = descriptionMap;
		selectedItems = new ArrayList<String>();
	}

	
	@Override
	public void setListBehviours() {
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setSelectionModel(new DefaultListSelectionModel() {
			
			private static final long serialVersionUID = 976007018925049796L;

			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				} else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedItems = list.getSelectedValuesList();
			}
		});
		
		list.addMouseMotionListener(this);
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
	
	public static List<String> getPUIDList(Map<String, Integer> rowsMap) {
		return new ArrayList<String>(rowsMap.keySet());
	}

	public List<String> getSelectedItems() {
		return selectedItems;
	}


}
