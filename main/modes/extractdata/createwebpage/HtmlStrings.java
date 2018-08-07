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
		return "<div style=\"overflow-x:auto;\"><table>";
	}

	public String tableClose() {
		return "</table></div>";
	}

	public String footer() {
		return "</html>";
	}

	public String tableRowOpen( ) {
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
	
	public String h2(String content) { 
		return "<h2  align=\"center\" style = \"width:100%\">" + content + "</h2><br>";
	}

	public String tableTrueFalseDropDown(String selectedStr) {

		String returnString, tempString, optionsString;
		List<String> strs = new ArrayList<String>();
		tempString = "<select id = \"select\" onchange=\"setColor()\">";
		
		switch (selectedStr) {
		case "Pass":
			strs.add(option("green", "pass", true));
			strs.add(option("red", "fail", false));
			strs.add(option("blue", "none", false));
			break;
		case "Fail":
			strs.add(option("green", "pass", false));
			strs.add(option("red", "fail", true));
			strs.add(option("blue", "none", false));
			break;
			
		default:
			strs.add(option("green", "pass", false));
			strs.add(option("red", "fail", false));
			strs.add(option("blue", "none", true));
			break;
		}
		
		optionsString = "";
		for (String str : strs) {
			optionsString = optionsString + str ;
		}
		
		
		tempString = tempString + optionsString + "</select>";
		returnString = "<td align=\"center\" id=\"dropdownTableData\">" + tempString + "</td>";
		return returnString;
	}
	
	public String option(String value, String content, boolean isSelected) { 
		return isSelected ? "<option value=\""+value+"\" selected=\"selected\">" +content+ "</option>" :  "<option value=\""+value+"\">" +content+ "</option>";
	}

}
