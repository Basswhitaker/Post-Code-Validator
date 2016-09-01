import java.util.Comparator;

public class DataClass {
	
	public String postcode = "";
	public int rowid = 0;
	public boolean validcode = false;
	
	public DataClass(String postCode, int rowID){
		RegexClass rc = new RegexClass(1);
		postcode = postCode;
		rowid = rowID;
		//test inputed post code against regex pattern to be recalled later
		validcode = rc.testPattern(postcode);
	}
	
	//comparator used for sorting of objects when in a collection
	public static Comparator<DataClass> getCompbyRowID(){
		Comparator<DataClass> comp = new Comparator<DataClass>(){
			@Override
			public int compare(DataClass dc1, DataClass dc2){
				return dc1.rowid - dc2.rowid;
			}
		};
		return comp;
	}
	
}
