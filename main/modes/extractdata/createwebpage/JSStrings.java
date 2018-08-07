package main.modes.extractdata.createwebpage;

import java.io.IOException;
import main.global.debugger.DebugFrame;
import main.global.genericmethods.FileExtractor;




public class JSStrings {

	FileExtractor tfs;
	DebugFrame df;

	public JSStrings() {
		tfs = new FileExtractor();
	}

	public String linkToJQuery() throws IOException {
		String filePath = "/resource/web/javascript/jquery.js";
		return "<script>" + tfs.readResourceTxtToString(filePath) + " </script> <!---jQuery Source -->";
	}

	public String script() throws IOException {
		String filePath = "/resource/web/javascript/jscript.js";
		return "<script>" + tfs.readResourceTxtToString(filePath) + " </script> <!---jScript, editable tds -->";
	}

	public String rowColouringDropDownScript() {
		return "" ;
	}

}
