package main.modes.extractdata.createwebpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.modes.extractdata.DataPointer;
import main.modes.extractdata.csvparsing.CSVParsing;


public class StringFromCSV {

	private DataPointer dp;
	private CreateHTMLFile file;
	private CSVParsing parse;
	private List<String> stringsForWriting;
	private boolean passOrFailDropDown;
	private int loopNumberOfPassFail;
	private HtmlStrings html = new HtmlStrings();
	private JSStrings js = new JSStrings();
	private CSSStrings css = new CSSStrings();

	/**
	 * constructor takes a dataPointer class as this contains all of the information
	 * about parsing up the target file.
	 * @param dp
	 */
	public StringFromCSV(DataPointer dp) {
		this.dp = dp;
		setPassOrFailDropDown(false);
		file = new CreateHTMLFile(dp.getUrlOut(), dp.getFileName());
		parse = new CSVParsing();
		parse.setUrl(dp.getUrlIn());
		stringsForWriting = new ArrayList<String>();
		try {
			createWebPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a list of all the successive elements to be written to the HTML file. 
	 * Passes the list to a method which writes file from it.
	 * @throws IOException
	 */
	private void createWebPage() throws IOException {
		stringsForWriting.add(html.header("Secure HTML Test Software for DOORS", css.cssString(), js.linkToJQuery()));
		stringsForWriting.add(html.bodyOpen());
		stringsForWriting.add(html.h2(instructionHeading()));
		stringsForWriting.add(html.h2(instructions1()));
		stringsForWriting.add(html.h2(instructions2()));
		stringsForWriting.add(html.tableOpen());
		wrapContentsInHTML(extractRequiredData());
		stringsForWriting.add(html.tableClose());
		stringsForWriting.add(js.script());
		stringsForWriting.add(html.bodyClose());
		stringsForWriting.add(html.footer());
		file.writeFile(stringsForWriting);
	}
	
	
	private String instructionHeading() {
		return "Instructions: ";
				
	}
	
	private String instructions1() { 
		return "i) After editing a cell (by clicking on it and typing) hit return to finish editing.";
	}
	
	private String instructions2() { 
		return "ii) When finished editing press \"ctrl + s\" to save the edited html file.";
	}

	/**
	 * extracts selected data to be written into HTML from the target file
	 * @return 
	 * @throws IOException
	 */
	private List<List<List<String>>> extractRequiredData() throws IOException {
		List<Integer> significantLines = new ArrayList<Integer>();
		List<List<List<String>>> fullReturnedLines = new ArrayList<List<List<String>>>();

		/** get lines in text from PUID Map */
		for (String str : dp.getSelectedPUIDS()) {
			significantLines.add(dp.getRowsMap().get(str));
		}

		/** get line content by reading file */
		for (Integer i : significantLines) {
			fullReturnedLines.add(parse.getFileLine(i));
		}

		List<Integer> significantColumns = new ArrayList<Integer>();
		List<List<List<String>>> formattedFullList = new ArrayList<List<List<String>>>();

		/** get column numbers in list */
		for (String str : dp.getSelectedColumns()) {
			significantColumns.add(dp.getHeadingsMap().get(str));
			if (str.equals("")) {
				setPassOrFailDropDown(true);
			} 
		}

		/** get formatted (desired) columns only in string arrays */

		for (List<List<String>> strsList : fullReturnedLines) {
			List<List<String>> tempStrsList = new ArrayList<List<String>>();
			for (List<String> strs : strsList) {
				List<String> tempList = new ArrayList<String>();
				int count = 0;
				for (String str : strs) {
					for (Integer i : significantColumns) {
						if (count == i) {
							tempList.add(str);
						}
					}
					count++;
				}
				tempStrsList.add(tempList);
			}
			formattedFullList.add(tempStrsList);
		}
		return formattedFullList;
		
	}

	
	/**
	 * Wraps the desired content from target file in HTML tags
	 * @param formattedFullList
	 */
	private void wrapContentsInHTML(List<List<List<String>>> formattedFullList) {

		for (List<List<String>> formattedReturnedLines : formattedFullList) {

			/** Table headings */
			int localCount = 0;
			stringsForWriting.add(html.tableRowOpen());
			for (String str : dp.getSelectedColumns()) {
				stringsForWriting.add(html.tableHeader(str));
				if (str.equals("Test Status")) {
					setPassOrFailDropDown(true);
					loopNumberOfPassFail = localCount;
				}
				localCount++;
			}
			stringsForWriting.add(html.tableRowClose());

			/** Table Rows */

			for (List<String> strs : formattedReturnedLines) {
				stringsForWriting.add(html.tableRowOpen());
				localCount = 0;
				for (String str : strs) {
					if (loopNumberOfPassFail == localCount) {
						stringsForWriting.add(html.tableTrueFalseDropDown(str));
					} else {
						stringsForWriting.add(html.tableData(str));
					}
					localCount++;
				}

				stringsForWriting.add(html.tableRowClose());
			}
		}
	}

	/**
	 * elements to be added to the drop down box within table
	 * @return
	 */
	private List<String> passFailList() {
		List<String> strs = new ArrayList<String>();
		strs.add("none");
		strs.add("pass");
		strs.add("fail");
		return strs;
	}


	
	/***********************************************
	 *  Getters & Setters
	***********************************************/
	
	public boolean isPassOrFailDropDown() {
		return passOrFailDropDown;
	}

	public void setPassOrFailDropDown(boolean passOrFailDropDown) {
		this.passOrFailDropDown = passOrFailDropDown;
	}

}
