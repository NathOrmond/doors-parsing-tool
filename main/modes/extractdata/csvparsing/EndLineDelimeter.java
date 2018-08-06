package main.modes.extractdata.csvparsing;
/**
 * 
 * This class contains methods to ascertain whether an end line 
 * delimeter value has been read from the CSV file 
 * 
 * @author chno
 *
 */
public class EndLineDelimeter {

	String heading;

	public EndLineDelimeter() {
	}

	/**
	 * Sets heading column
	 * @param heading
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * Switch 
	 * (more endline cases to be added in future ? )
	 * 
	 * @param mode
	 * @param str
	 * @return
	 */
	public boolean delimeterIsMet(String mode, String str) {
		str = str.replace("\"", "");
		switch (mode) {
		default:
			if (str.equals(heading)) {
				return true;
			}
			return dateMode(str);
		}
	}

	/**
	 * 
	 * returns true if the date is contained and false if not
	 * 
	 * @param str
	 * @return
	 */
	private boolean dateMode(String str) {
		char[] chars = str.toCharArray();

		if (chars.length != 10) {
			return false;
		}

		for (int i = 0; i < chars.length; i++) {
			if ((i == 2) || (i == 5)) {
				if (chars[i] != '/') {
					return false;
				}
			} else {
				if (!(Character.isDigit(chars[i]))) {
					return false;
				}
			}

		}

		return true;
	}

}
