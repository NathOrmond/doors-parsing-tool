package main.modes.extractdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPointer {

	String urlIn, urlOut, fileName;
	Map<String, Integer> headingsMap, rowsMap;
	Map<String, String> descriptionsMap;
	
	public Map<String, String> getDescriptionsMap() {
		return descriptionsMap;
	}

	public void setDescriptionsMap(Map<String, String> descriptionsMap) {
		this.descriptionsMap = descriptionsMap;
	}

	List<String> selectedColumns, selectedPUIDS;

	public DataPointer() {
		headingsMap = new HashMap<String, Integer>();
		rowsMap = new HashMap<String, Integer>();
		selectedColumns = new ArrayList<String>();
		selectedPUIDS = new ArrayList<String>();
	}

	public Map<String, Integer> getHeadingsMap() {
		return headingsMap;
	}

	public void setRowsMap(Map<String, Integer> rowsMap) {
		this.rowsMap = rowsMap;
	}

	public Map<String, Integer> getRowsMap() {
		return rowsMap;
	}

	public void setHeadingsMap(Map<String, Integer> headingsMap) {
		this.headingsMap = headingsMap;
	}

	public String getUrlIn() {
		return urlIn;
	}

	public void setUrlIn(String url) {
		this.urlIn = url;
	}

	public String getUrlOut() {
		return urlOut;
	}

	public void setUrlOut(String urlOut) {
		this.urlOut = urlOut;
	}

	public void setSelectedColumns(List<String> selectedColumns) {
		this.selectedColumns = selectedColumns;
	}

	public List<String> getSelectedColumns() {
		return selectedColumns;
	}

	public void setSelectedPUIDS(List<String> selectedPUIDS) {
		this.selectedPUIDS = selectedPUIDS;
	}

	public List<String> getSelectedPUIDS() {
		return selectedPUIDS;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
