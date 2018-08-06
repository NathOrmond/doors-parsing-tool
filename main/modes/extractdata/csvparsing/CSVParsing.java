package main.modes.extractdata.csvparsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParsing {

	
	private List<String> headingsModel, puidModel;
	private List<List<String>> fullLinesList;
	private CSVQuoteParsing quoteParser;
	private Map<String, Integer> puidMap, headingsMap;
	private Map<String, String> descriptionsMap;
	private boolean firstPuidReached = false, lastLineWasComplete = false;
	private int fullLineCount, numReadableLines, numColumns, puidColumn, returnLineNum, descriptionColumn;
	private EndLineDelimeter delimeter;
	private String delimeterMode = "date", whenContainsNothing, url, line = "", prevLine = "";
	private BufferedReader br;

	public CSVParsing(String urlIn) {
		delimeter = new EndLineDelimeter();
		quoteParser = new CSVQuoteParsing();
		headingsModel = new ArrayList<String>();
		puidModel = new ArrayList<String>(); 
		puidMap = new HashMap<String, Integer>();
		descriptionsMap = new HashMap<String, String>();
		headingsMap = new HashMap<String, Integer>();
		this.url = urlIn;
		try {
			readFullFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CSVParsing() {
		delimeter = new EndLineDelimeter();
		quoteParser = new CSVQuoteParsing();
		headingsModel = new ArrayList<String>();
		puidModel = new ArrayList<String>();
		puidMap = new HashMap<String, Integer>();
		headingsMap = new HashMap<String, Integer>();
		descriptionsMap = new HashMap<String, String>();
	}

	/**
	 * Method to read through full target file. 
	 * 
	 * note that reading through a target will result in populating Maps etc.
	 * 
	 * @throws IOException
	 */
	public void readFullFile() throws IOException {
		genericReader(false, 0);
	}
	
	/**
	 * Returns a section of Data for a selected line to read from.
	 * 
	 * @param returnLineNum
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> getFileLine(int returnLineNum) throws IOException {
		return genericReader(true, returnLineNum);
	}
	
	/**
	 * Reader class: 
	 * Essentially just iterates through lines of target file and ascertains whether or not 
	 * end line delimeter is contained or not. Calls other methods based on circumstances.
	 * 
	 * 
	 * @param returnALine
	 * @param returnLineNum
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> genericReader(boolean returnALine, int returnLineNum) throws IOException {
		List<String> fullLine;
		fullLinesList = new ArrayList<List<String>>();
		firstPuidReached = false;
		this.returnLineNum = returnLineNum;
		fullLineCount = 0;
		getNumReadableLinesInFile();
		br = new BufferedReader(new FileReader(url));
		//------------------------------------------------------------------------------------------------------- 
		for (int i = 0; i < numReadableLines; i++) {
			fullLine = new ArrayList<String>();
			line = br.readLine();
			String[] datas = line.split(",");
			if (i == 0) {
				headingMethod(datas);		/** First line contains headings **/
			}
			else if (delimeter.delimeterIsMet(delimeterMode, datas[datas.length - 1])) {
				if (!lastLineWasComplete) {
					datas = (prevLine + line).split(",");		/** add prevLine if last line wasn't complete **/
				}
				if ((isCompleteRowsNum(datas)) || (isCompleteRowsNum(quoteParser.formatCommasAndQuotes(datas)))) {
					datas = quoteParser.formatCommasAndQuotes(datas);		/** Formats for commas and quotation marks **/
					fullPopulatedLineMethod(datas);
					if (returnALine && returnLineCheckMethod(datas, fullLine, br)) {
						return fullLinesList;
					}
				}
				else {
					addToPreviousLine();
				}
			}
			else {
				addToPreviousLine();		/** Delimiter not contained so save result to previous line and iterate through again **/
			}
		}
		//------------------------------------------------------------------------------------------------------- 
		
		
		br.close();
		return fullLinesList;
	}

	/**
	 * 
	 * Returns the line being targeted
	 * 
	 * @param datas
	 * @param fullLine
	 * @param br
	 * @return
	 * @throws IOException
	 */
	private boolean returnLineCheckMethod(String[] datas, List<String> fullLine, BufferedReader br) throws IOException {
		if (fullLineCount == returnLineNum) {  					/** If current line is the desired return line **/
			if (!datas[0].equals(whenContainsNothing)) {
				if (!firstPuidReached) {
					firstPuidReached = true;
					for (int k = 0; k < datas.length; k++) {
						fullLine.add(datas[k]);
					}
					fullLinesList.add(fullLine);
					returnLineNum++;
				} else {
					br.close();
					return true;
				}
			} else if (datas[0].equals(whenContainsNothing)) {
				if (firstPuidReached) {
					for (int k = 0; k < datas.length; k++) {
						fullLine.add(datas[k]);
					}
					fullLinesList.add(fullLine);
				}
				returnLineNum++;
			}
		}
		return false;
	}

	/** 
	 * Increments the line counter when called
	 * 
	 * @param datas
	 */
	private void incrementFullLine(String[] datas) {
		if (fullLineCount == 1) {
			whenContainsNothing = datas[0];
		}
		fullLineCount++;
	}

	/**
	 * Runs when first line of file is read
	 * sets such parameters as number of lines to expect or
	 * which column PUIDs are contained in.
	 * 
	 * @param datas
	 */
	private void headingMethod(String[] datas) {
		datas = quoteParser.formatCommasAndQuotes(datas);
		numColumns = datas.length;
		incrementFullLine(datas);
		for (int j = 0; j < datas.length; j++) {
			
			if (datas[j].contains("PUID")) {
				puidColumn = j;
				datas[j] = "PUID";
			} else if(datas[j].contains("Test Description")){ 
				descriptionColumn = j;
			}
			
			headingsModel.add(datas[j]);
			headingsMap.put(datas[j], j);
			
		}
	}

	/**
	 * CoOrdinates methods called when a full line is reached. 
	 * @param datas
	 */
	private void fullPopulatedLineMethod(String[] datas) {
		correctStringArrayMethod(datas);						/** Adds elements to the map **/
		lastLineWasComplete = true;								
		clearPreviousLine();									/** clears necessary variables for next iteration **/
	}
	
	/**
	 * Adds data elements to the maps
	 * @param datas
	 */
	private void correctStringArrayMethod(String[] datas) {
		incrementFullLine(datas);
		System.out.println(datas);
		if ((!datas[puidColumn].equals(whenContainsNothing)) && (fullLineCount > 1)) {
			puidMap.put(datas[puidColumn], fullLineCount);
			puidModel.add(datas[puidColumn]);
			descriptionsMap.put(datas[puidColumn],datas[descriptionColumn] );
		}
	}

	/**
	 * Adds current line to previous line text including line break html 
	 * for carriage return in read file. (this method will only be triggered 
	 * when reader encounters a carriage return within the target file).
	 */
	private void addToPreviousLine() {
		prevLine = prevLine + line + "</br>";
		lastLineWasComplete = false;
	}

	/**
	 * clears the prevLine variable
	 */
	private void clearPreviousLine() {
		prevLine = "";
	}

	/******
	 * GETTERS & SETTERS
	 ******/
	
	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getHeadingsForJList() {
		return headingsModel;
	}

	public Map<String, Integer> getHeadingsMap() {
		return headingsMap;
	}

	public Map<String, Integer> getPuidMap() {
		return puidMap;
	}
	
	public Map<String, String> getDescriptionsMap() {
		return descriptionsMap;
	}
	
	private boolean isCompleteRowsNum(String[] datas) {
		return (datas.length == numColumns);
	}
	
	/**
	 * loops through entire file and sets numReadableLines to  
	 * number of read lines
	 * @throws IOException
	 */
	private void getNumReadableLinesInFile() throws IOException {
		numReadableLines = 0;
		BufferedReader br = new BufferedReader(new FileReader(url));
		while ((line = br.readLine()) != null) {
			numReadableLines++;
		}
		br.close();
	}
}
