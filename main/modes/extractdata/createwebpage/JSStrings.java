package main.modes.extractdata.createwebpage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import main.global.debugger.DebugFrame;




public class JSStrings {

	TextFileStrings tfs;
	DebugFrame df;

	public JSStrings() {
		tfs = new TextFileStrings();
	}

	public String linkToJQuery() throws IOException {
		String filePath = "/resource/web/javascript/jquery.txt";
		return "<script>" + tfs.readResourceTxtToString(filePath) + " </script> <!---jQuery Source -->";
	}

	public String script() throws IOException {
		String filePath = "/resource/web/javascript/jscript.txt";
		return "<script>" + tfs.readResourceTxtToString(filePath) + " </script> <!---jScript, editable tds -->";
	}

	public String rowColouringDropDownScript() {
		return "" ;
	}

}
