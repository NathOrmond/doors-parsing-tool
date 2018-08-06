package main.global.hci.frame;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import main.global.runtime.RunModes;



public abstract class AbstractInstructionsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static String[] files;
	private int fileMaxNum, width, height;
	private Map<Integer, String> fileMap;
	private URL url;
	public JButton prevButton, finishedButton, nextButton;
	private GridBagLayout mgr;
	private GridBagConstraints gbc;
	private String file, resourcePath;
	private BufferedImage image;
	private JLabel picLabel; 

	


	/********************************
	 * Initialisation
	 ********************************/
	
	public AbstractInstructionsPanel(String resourcePath, String[] files) {
		this.files = files;
		fileMaxNum = files.length - 1;
		this.resourcePath = resourcePath;
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		this.setLayout(mgr);
		fileMap = new HashMap<Integer,String>();
		addAllTofileMap();
		prevButton = new JButton(" prev "); 
		prevButton.addActionListener(this);
		finishedButton = new JButton(" home"); 
		nextButton = new JButton("next ");
		nextButton.addActionListener(this);
		finishedButton.setActionCommand(RunModes.home);
		file = files[1];
		actionSwitch(file);
	}
	
	private void addAllTofileMap(){ 
		for(int i = 0; i < files.length; i++){ 
			fileMap.put(i, files[i]);
		}
	}
	
	/********************************
	 * Generic Class Methods
	 ********************************/
	/**
	 * Called each time panel updated, refreshes panel
	 */
	private void updatePanel(){

		clearComponents();
		
		/** Adds image to panel **/
		getImage(url);
		picLabel = new JLabel(new ImageIcon(image));
		picLabel.setSize(width, height);
		remove(picLabel);
		addComponentToPanel(picLabel, 0, 0);
		
			/** Adds previous button to panel **/
			gbc.anchor = gbc.FIRST_LINE_END;
			addComponentToPanel(prevButton, 1, 0);
			prevButton.setActionCommand(fileMap.get(getPrevActionCommand()));
			prevButton.setEnabled(getKeysByValue(fileMap, file) > 1);
			
			/** Adds next button to nextButton **/
			gbc.anchor = gbc.FIRST_LINE_START;
			addComponentToPanel(nextButton, 2, 0);
			nextButton.setActionCommand(fileMap.get(getNextActionCommand()));
			nextButton.setEnabled(getKeysByValue(fileMap, file) < fileMaxNum );
			
		/** Adds finished button to panel **/
		gbc.anchor = gbc.CENTER;
		addComponentToPanel(finishedButton,0, 1);
		
		repaint();
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
	
	/**
	 * @return previous button key to obtain action command from fileMap
	 */
	private int getPrevActionCommand(){ 
		int key = getKeysByValue(fileMap, file);
		return (key == 0) ? key : (key -1);
	}
	
	/**
	 * @return next button key to obtain action command from fileMap
	 */
	private int getNextActionCommand(){
		int key = getKeysByValue(fileMap, file);
		return (key == fileMaxNum) ? key : (key +1);
	}
	
	private void updateURL(String file){ 
		this.file = file;
		url = getClass().getResource(resourcePath + file);
	}
	
	private void getImage(URL url){ 
		try{ 
			image = ImageIO.read(url);	
		} catch (IOException e){ 
			e.printStackTrace();
			//ToDo
			/** IMAGE NOT FOUND EXCEPTION **/
		}	
		width = image.getWidth(); 
		height = image.getHeight();
	}
	/**
	 * returns key for a value in a 1:1 Map
	 * 
	 * @param map
	 * @param value
	 * @return keyForValue
	 */
	public static <T,E> T getKeysByValue(Map<T,E> map, E value){ 
		for (Entry<T,E> entry : map.entrySet()){ 
			if(Objects.equals(value, entry.getValue())){ 
				return entry.getKey();
			}
		}
		return null;
	}
	
	public JPanel getPanel(){ 
		setBorder(new TitledBorder("Extraction Instructions"));
		return this;
	}
	
	private void clearComponents(){ 
		removeAll();
		revalidate();
		repaint();
	}
	
	/********************************
	 * Button Handler && Methods
	 ********************************/
	
	/**
	 * calls switch and passes action command
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		actionSwitch(e.getActionCommand());
	}
	
	/**
	 * 
	 * updates: 
	 * url
	 * action commands for buttons
	 * panel (and its components)
	 * @param str
	 */
	private void actionSwitch(String str){
		updateURL(str);
		updatePanel();
	}
	
}
