package main.modes.extractdata.createwebpage;

import java.util.ArrayList;
import java.util.List;

public class HtmlStrings {

	public String header(String title, String css, String jsLink) {
		return "<!DOCTYPE html>" + "<html>" + "<head>" + "<style>" + css + "</style>" + "<title>" + title + "</title>"
				+ jsLink + "</head>";
	}

	public String scriptTags(String script) {
		return "<script>" + script + "</script>";
	}

	public String bodyOpen() {
		return "<body>";
	}

	public String bodyClose() {
		return "</body>";
	}

	public String tableOpen() {
		return "<div style=\"overflow-x:auto;\"><table align=\"left\" style = \"width:100%\">";
	}

	public String tableClose() {
		return "</table></div>";
	}

	public String footer() {
		return "</html>";
	}

	public String tableRowOpen() {
		return "<tr>";
	}

	public String tableRowClose() {
		return "</tr>";
	}

	public String tableData(String tableData) {
		return "<td align=\"left\">" + tableData + "</td>";
	}

	public String tableHeader(String header) {
		return "<th>" + header + "</th>";
	}

	public String paragraph(String paragraph) {
		return "<p>" + paragraph + "</p>";
	}

	public String tableTrueFalseDropDown(String selectedStr) {

		String returnString, tempString, optionsString;
		List<String> strs = new ArrayList<String>();
		tempString = "<select>";
		switch (selectedStr) {
		case "Pass":
			strs.add("Pass");
			strs.add("None");
			strs.add("Fail");
			break;
		case "Fail":
			strs.add("Fail");
			strs.add("None");
			strs.add("Pass");
			break;
		default:
			strs.add("None");
			strs.add("Pass");
			strs.add("Fail");
			break;
		}
		optionsString = "";
		for (String str : strs) {
			optionsString = optionsString + "<option value=\"" + str + "\">" + str + "</option>";
		}
		tempString = tempString + optionsString + "</select>";
		returnString = "<td align=\"center\" id=\"dropdown\">" + tempString + "</td>";
		return returnString;
	}

}
