package main.modes.extractdata.createwebpage;

import java.io.IOException;

public class CSSStrings {
	
	TextFileStrings tfs;
	
	public CSSStrings() {
		tfs = new TextFileStrings();
	}

	public String cssString() throws IOException {		
		return "table{\n"  +
			   "	border-collapse: collapse;\n"   +   
			   "	width: 75%;\n" +     
			   "	height: 75%;\n" +  
			   "	font-family: arial, sans-serif;\n"+
			   "	}\n"+         
			   "th{\n" +
			   "	text-align: left;\n"+
			   "	background-color: #4CAF50;\n"  +   
			   "	color:white;\n" +
			   "	height: 50px;\n"+
			   "	}\n"     +    
			   "th td{ \n" +
			   "	padding: 15px;\n"   +   
			   "	}     \n"+
			   "td, th{ \n"+
			   "	border:1px solid #dddddd;\n"  +   
			   "	}\n"+         
			   "input{ \n" +  
			   "	width:100%; \n"+
			   "	height:100%; \n"  +  
			   "	}  \n "  +
			   "tr:nth-child(even){\n"   + 
			   "	background-color: #dddddd;\n"+
			   "	} \n"   +     
			   "tr:hover{\n"+ 
			   "	background-color: #ffdab9;\n"+
			   "	}  \n"   +     
			   "tr.redRow{\n"+
			   "	color:#f00;"+
			   "	} \n"+
			   "tr.RedRow input, tr.RedRow select{\n"+
			   "	color:#f00;"+
			   "	} \n";
	}

}
