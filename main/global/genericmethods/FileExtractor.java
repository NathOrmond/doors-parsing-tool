package main.global.genericmethods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileExtractor {

	BufferedReader br;

	public FileExtractor() {
	}

	public String readResourceTxtToString(String url) throws IOException {
		br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(url)));
		String line = "";
		StringBuilder sb = new StringBuilder();

		while (line != null) {
			line = br.readLine();
			sb.append(line + System.lineSeparator());
		}

		return sb.toString();
	} 
	
	public String readTxtToString(String url) throws IOException {
		br = new BufferedReader(new FileReader(url));
		String line = "";
		StringBuilder sb = new StringBuilder();

		while (line != null) {
			line = br.readLine();
			sb.append(line + System.lineSeparator());
		}

		return sb.toString();
	} 

}
