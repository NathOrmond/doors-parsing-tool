package main.modes.extractdata.createwebpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CreateHTMLFile {

	String fileName;
	String url;
	String folderUrl;
	PrintWriter printWriter;

	public CreateHTMLFile(String folderUrl, String fileName) {
		this.folderUrl = folderUrl;
		this.fileName = fileName;
		url = folderUrl + "\\" + checkFileEnding(fileName);
	}

	private String checkFileEnding(String fileName) {
		if (fileName.contains(".html")) {
			return fileName;
		} else {
			return fileName + ".html";
		}
	}

	public void writeFile(List<String> strs) throws IOException {
		printWriter = new PrintWriter(url, "UTF-8");
		for (String str : strs) {
			printWriter.println(str);
		}
		printWriter.close();
	}

}
