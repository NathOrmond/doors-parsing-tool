package main.modes.extractdata.createwebpage;

import java.io.IOException;
import main.global.genericmethods.FileExtractor;

public class CSSStrings {
	
	FileExtractor tfs;
	
	public CSSStrings() {
		tfs = new FileExtractor();
	}

	public String cssString() throws IOException {	
		String filePath = "/resource/web/css/css.css";
		return tfs.readResourceTxtToString(filePath);
	}

}
