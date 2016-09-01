import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexClass {
	private String defaultpattern = "";
	//the supplied regex pattern
	private static final String PATTERN_ONE = "(GIR\\s0AA)|"+
		    "("+
		        "(([A-PR-UWYZ][0-9][0-9]?)|"+
		            "(([A-PR-UWYZ][A-HK-Y][0-9](?<!(BR|FY|HA|HD|HG|HR|HS|HX|JE|LD|SM|SR|WC|WN|ZE)[0-9])[0-9])|"+
		             "([A-PR-UWYZ][A-HK-Y](?<!AB|LL|SO)[0-9])|"+
		             "(WC[0-9][A-Z])|"+
		             "("+
		               "([A-PR-UWYZ][0-9][A-HJKPSTUW])|"+
		               "([A-PR-UWYZ][A-HK-Y][0-9][ABEHMNPRVWXY])"+
		             ")"+
		            ")"+
		          ")"+
		        "\\s[0-9][ABD-HJLNP-UW-Z]{2}"+
		        ")";
	//second pattern was supplied by Internet search and has proved to be a historic regex made by the UK government
	private static final String PATTERN_TWO = "(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})";

	public RegexClass(int pattern){
		switch(pattern){
			case 2:
				defaultpattern = PATTERN_TWO;
				break;
			default:
				defaultpattern = PATTERN_ONE;
				break;
		}
	}
	
	public void displayPattern(){
		System.out.println(defaultpattern);
	}
	
	public boolean testPattern(String inputData){
		//Compile chosen pattern
		Pattern r = Pattern.compile(defaultpattern);
		//test the input data, which in this case will be UK post codes
		Matcher m = r.matcher(inputData);
		//check whole string for matches
		if (m.matches()){
			return true;
		} else {
			return false;
		}
	}
}
