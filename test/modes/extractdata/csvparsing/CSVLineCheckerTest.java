package test.modes.extractdata.csvparsing;

import main.modes.extractdata.csvparsing.CSVQuoteParsing;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


public class CSVLineCheckerTest {
	
	/**
	 * ToDo 
	 * 
	 * Update Expected Outputs (until so expect them to FAIL)
	 */

	String[] input1 = {"","","","","\"15\"","\"From the blah blah Stim web interface"," inject data to populate the table as follows: Within the :\"\"blah blah::bitMask[1]\\\"\" \"\"blah blah::bitMask[blah blah]\"\" folder enter a \"\"MUXTemp\"\" value of blah blah\"","\"From the blah blah tab see that the top row for each blah blah has a blah blah temp set to blah blah", " see that the temperature is displayed to blah blah decimal point precision.\"","","","\"blah blah\"","\"21/11/2017\""};
	String[] input2 = {"","","","","\"13\"","\"From the blah blah blah blah Stim web interface"," inject data to populate the table as follows: Within the :\"\"blah blah::bitMask[1]\"\"\"\"blah blah::bitMask[5]\"\" folder switch the \"\"blah blahrrors\"\" option to\"\"blah blah\"\"\"","\"From the blah blah tab see that the second row for each blah blah"," blah blah Status column is populated with blah blah (allow ~ blah blahs for this to update)Also"," see that each associated blah blah is reporting as blah blah and is blah blah: This is a blah blah state and the status returns to blah blah withing ~blah blah seconds"," if the error blah blah does not increment\"","","","\"blah blah\"","\"10/08/2017\""};
	String[] input3 = {"\"PUID\"","\"Test Level\"","\"Test Class\"","\"Method\"","\"Step\"","\"Software Test Description\"","\"Expected Results\"","\"Test Evidence\"","\"Test Status\"","\"Build\"","\"Created On\""};
	String[] input4 = {"","","","","","\"The blah blah will provide additional capability to be fitted to a blah blah blah blah. The additional functionality consists of : Updated blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah\"","","","","","\"09/01/2018\""};
	String[] input5 = {"","","","","\"2\"","\"Start the blah blah HCI on the blah blah From the Line up tab"," select 'blah blah blah blah Data' and select line up number blah blah. Ensure that the blah blah is set to 'blah blah' and the integration rate is set to blah blah seconds Bring up the blah blah blah blah (click blah blah button after clicking with the blah blah blah blah display). Ensure blah blah shading is set to blah blah blah blah shading is set to blah blah blah blah blah blah blah blah set to blah blah Vertical blah blah set to Horiz blah blah that blah blah correction blah blah  blah blah the blah blah blah blah display, set the octave to blah blah"," set the integration to blah blah seconds On the fine fan display, set the octave to blah blah"," set the blah blah to blah blah seconds \"","\"Confirm that a blah blah is clearly seen at blah blah degrees in fan at blah degrees in the fine fan display Confirm that a target is clearly seen at blah blah degrees, elevation blah blah in the blah blah blah blah blah blah Confirm that the blah blah blah blah blah blah is blah blah at the correct rate ( blah blah updates per second) \"","","","","\"09/01/2018\""};
	
	String[] expectedOutput1 = {"","","","","15","From the blah blah Stim web interface, inject data to populate the table as follows: Within the :\"blah blah::bitMask[1]\\\" \"blah blah::bitMask[blah blah]\" folder enter a \"MUXTemp\" value of blah blah","From the blah blah tab see that the top row for each blah blah has a blah blah temp set to blah blah, see that the temperature is displayed to blah blah decimal point precision.","" ,"" ,"blah blah", "21/11/2017"};
	String[] expectedOutput2 = {"","","","","13","From the blah blah blah blah Stim web interface, inject data to populate the table as follows: Within the :\"blah blah::bitMask[1]\"\"blah blah::bitMask[5]\" folder switch the \"blah blahrrors\" option to\"blah blah\"","From the blah blah tab see that the second row for each blah blah, blah blah Status column is populated with blah blah (allow ~ blah blahs for this to update)Also, see that each associated blah blah is reporting as blah blah and is blah blah: This is a blah blah state and the status returns to blah blah withing ~blah blah seconds, if the error blah blah does not increment","" ,"" ,"blah blah","10/08/2017"};
	String[] expectedOutput3 = {"PUID","Test Level","Test Class","Method","Step","Software Test Description","Expected Results","Test Evidence","Test Status","Build","Created On"};
	String[] expectedOutput4 = {"","","","","","The blah blah will provide additional capability to be fitted to a blah blah blah blah. The additional functionality consists of : Updated blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah","","","","","09/01/2018"};
	String[] expectedOutput5 = {"","","","","2","Start the blah blah HCI on the blah blah From the Line up tab, select 'blah blah blah blah Data' and select line up number blah blah. Ensure that the blah blah is set to 'blah blah' and the integration rate is set to blah blah seconds Bring up the blah blah blah blah (click blah blah button after clicking with the blah blah blah blah display). Ensure blah blah shading is set to blah blah blah blah shading is set to blah blah blah blah blah blah blah blah set to blah blah Vertical blah blah set to Horiz blah blah that blah blah correction blah blah  blah blah the blah blah blah blah display, set the octave to blah blah, set the integration to blah blah seconds On the fine fan display, set the octave to blah blah, set the blah blah to blah blah seconds ","Confirm that a blah blah is clearly seen at blah blah degrees in fan at blah degrees in the fine fan display Confirm that a target is clearly seen at blah blah degrees, elevation blah blah in the blah blah blah blah blah blah Confirm that the blah blah blah blah blah blah is blah blah at the correct rate ( blah blah updates per second) ","","","","09/01/2018"};
	
	String[] actualOutput; 
	
	private void printActualOutput() { 
		 System.out.println(Arrays.toString(actualOutput));
	}
	
	
	CSVQuoteParsing checker = new CSVQuoteParsing();
	
	@Test
	public void attemptTest1() {
		 actualOutput = checker.formatCommasAndQuotes(input1);
		 System.out.println(Arrays.toString(expectedOutput1));
		 printActualOutput();
		 for(int i = 0; i < expectedOutput1.length; i++){ 
			 Assert.assertEquals(expectedOutput1[i], actualOutput[i]);
		 }
	} 
	
	@Test
	public void attemptTest2() {
		 actualOutput = checker.formatCommasAndQuotes(input2);
		 printActualOutput();
		 for(int i = 0; i < expectedOutput2.length; i++){ 
			 Assert.assertEquals(expectedOutput2[i], actualOutput[i]);
		 }
	}
	
	@Test
	public void attemptTest3() {
		 actualOutput = checker.formatCommasAndQuotes(input3);
		 printActualOutput();
		 for(int i = 0; i < expectedOutput3.length; i++){ 
			 Assert.assertEquals(expectedOutput3[i], actualOutput[i]);
		 }
	}
	
	@Test
	public void attemptTest4() {
		 actualOutput = checker.formatCommasAndQuotes(input4);
		 printActualOutput();
		 for(int i = 0; i < expectedOutput4.length; i++){ 
			 Assert.assertEquals(expectedOutput4[i], actualOutput[i]);
			 
		 }
	}
	
	@Test
	public void attemptTest5() {
		 actualOutput = checker.formatCommasAndQuotes(input5);
		 System.out.println(Arrays.toString(expectedOutput5));
		 printActualOutput();
		 for(int i = 0; i < expectedOutput5.length; i++){ 
			 Assert.assertEquals(expectedOutput5[i], actualOutput[i]);
			 System.out.println(expectedOutput5[i]);
			 System.out.println(actualOutput[i]);
		 }
	}
	
	@Test 
	public void invertTest(){ 
		boolean bool = true; 
		bool = checker.invert(bool);
		Assert.assertFalse(bool);
		bool = checker.invert(bool); 
		Assert.assertTrue(bool);
	}
	
	@Test 
	public void isCharCommaTest(){ 
		Assert.assertTrue(checker.isCharComma(','));
		String str = "none_of_THESE are commas!";
		char[] chars = str.toCharArray(); 
		for(int i = 0; i < chars.length; i++){ 
			Assert.assertFalse(checker.isCharComma(chars[i]));
		}
	}

}
