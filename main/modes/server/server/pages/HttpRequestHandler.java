package main.modes.server.server.pages;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.modes.extractdata.createwebpage.TextFileStrings;

public class HttpRequestHandler implements HttpHandler {

	String file;
	
	public HttpRequestHandler(String file) {
		this.file = file;
		}
	
	@Override
	public void handle(HttpExchange he) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		he.sendResponseHeaders(200, 0);
		bw.write(output());
		bw.close();
	}
	
	/**
	 * HTML for producing a web form with two inputs: latitude and longitude
	 * @return string
	 * @throws IOException 
	 */
	private String output() throws IOException {
		
		TextFileStrings tfs = new TextFileStrings();
		return  tfs.readResourceTxtToString(file);
	}

}
