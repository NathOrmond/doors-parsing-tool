package main.modes.extractdata.csvparsing;

import java.util.ArrayList;
import java.util.List;


public class CSVQuoteParsing {

	List<String> finalList;
	boolean inQuotes = false;
	boolean lastStringWasComplete = false;
	String strBuild = "";

	/**
	 * 
	 * Takes an unformatted string[] and formats commas and double quotes
	 * 
	 * @param inputDatas
	 * @return formatted String[]
	 */
	public String[] formatCommasAndQuotes(String[] inputDatas) {
		finalList = new ArrayList<String>();
		formatDatas(inputDatas);
		if(finalList.size()==11){ 
			boolean boolybool = true;
		}
		return finalList.toArray(new String[0]);
	}

	/**
	 * runs the methods in correct order
	 * 
	 * @param inputDatas
	 */
	private void formatDatas(String[] inputDatas) {

		/** Iterates through string array items **/
		for (int i = 0; i < inputDatas.length; i++) {

			/** Checks if the data value is ready to be added **/
			if (thisDataValueIsReadyToBeAdded(inputDatas[i])) {
				/**
				 * If the value is ready to be added it becomes the builder
				 * string and is added
				 **/
				strBuild = inputDatas[i];
				addToFinalList();
			} else {
				/**
				 * adds next part of string to the builder string checks if the
				 * string reaches the end of a cell
				 **/
				strBuild = strBuild + buildStringForQuoteContainingValue(inputDatas[i]);
				/**
				 * Checks if after the next part of the string has been added
				 * the formatted cell should be added
				 **/
				if (!inQuotes) {
					addToFinalList();
				}
			}
		}
	}

	/**
	 * 
	 * Formats a string which is within "quote containing territory" removes
	 * start of string and end of string quotes so they will not appear in
	 * output, removes double quotes so string output is like "this" rather than
	 * "this". Inverts the InQuotes boolean if either the start or end of a
	 * quote containing string fragment is reached.
	 * 
	 * @param str
	 * @return
	 */
	private String buildStringForQuoteContainingValue(String str) {
		String returnStr = "";
		char[] chars = str.toCharArray();
		boolean finishedWriting = false;
		/** Iterate through characters **/
		for (int j = 0; j < chars.length; j++) {
			/** If current Character is a quote **/
			if ((isCharQuote(chars[j]))) {
				/**
				 * Checks length of chars array isn't greater than Char to be
				 * passed through logic and checks for double quotes
				 **/
				if ((j + 1) != (chars.length) && isDoubleQuote(chars[j], (chars[j + 1]))) {
					j++;
					returnStr = returnStr + "\"";
				}

				else {
					inQuotes = invert(inQuotes);
				}
			}
			/**
			 * If current character is not a quote add to return string and
			 * iterate through
			 **/
			else {
				returnStr = returnStr + chars[j];
			}

		}

		/**
		 * If still inQuotes when returning string implies a comma which should
		 * be in text
		 **/
		if (inQuotes) {
			return returnStr + ",";
		}
		return returnStr;
	}

	/**
	 * Checks for double quotations
	 * @param c1
	 * @param c2
	 * @return
	 */
	private boolean isDoubleQuote(char c1, char c2) {
		return (isCharQuote(c1) && isCharQuote(c2));
	}

	/**
	 * 
	 * Checks whether a character is a quote or not
	 * 
	 * @param c
	 * @return boolean
	 */
	private boolean isCharQuote(char c) {
		return ((int) c == 34);
	}

	/**
	 * 
	 * Checks whether a character is a comma or not
	 * 
	 * @param c
	 * @return boolean
	 */
	public boolean isCharComma(char c) {
		return ((int) c == 44);
	}

	/**
	 * 
	 * Checks whether a string contains quote characters
	 * 
	 * @param str
	 * @return boolean
	 */
	private boolean containsQuotes(String str) {
		char[] chars = str.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			if (isCharQuote(chars[j])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Inverts the value of a boolean
	 * 
	 * @param bool
	 * @return invertedBool
	 */
	public boolean invert(boolean bool) {	
			return !bool;
	}

	/**
	 * Adds a string to the final string list which will be formatted
	 */
	private void addToFinalList() {
		/** Adds to the final List<String> **/
		finalList.add(strBuild);
		/** Resets the builder String **/
		strBuild = "";
	}


	/**
	 * 
	 * False if the string array position is within quotes ie "one of ","these"
	 * or contains quotes (therefore formatting must be done)
	 * 
	 * True for all other cases
	 * 
	 * @param str
	 * @return boolean
	 */
	private boolean thisDataValueIsReadyToBeAdded(String str) {
		return ( ( (inQuotes) || (containsQuotes(str)) ) ? false : true);
	}

}

