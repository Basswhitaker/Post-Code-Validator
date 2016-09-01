import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExportClass {
	private boolean orderedfile = false;
	
	public ExportClass(Scanner scan){
		//check to see if user would like the orginal data sorted into rowid order
		System.out.print("Would you like an additonal sorted file? (Y/N)");
		String s = scan.nextLine().toLowerCase();
		if (s.equals("y") || s.equals("yes")){
			orderedfile = true;
		}
	}
	
	public void writeToFiles(ArrayList<DataClass> postCodeData){
		//arrays for capturing file data
		List<String> invalidcodes = new ArrayList<String>();
		List<String> validcodes = new ArrayList<String>();
		List<String> orderedcodes = new ArrayList<String>();
		//add data headers
		invalidcodes.add("row_id,postcode");
		validcodes.add("row_id,postcode");
		orderedcodes.add("row_id,postcode");
		System.out.println("Exporting...");
		//loop through data set
		for(DataClass x: postCodeData){
			//check if post code is valid and pass data to correct array
			if(x.validcode){
				validcodes.add(String.format("%s,%s", x.rowid,x.postcode));
			}else{
				invalidcodes.add(String.format("%s,%s", x.rowid,x.postcode));
			}
			orderedcodes.add(String.format("%s,%s", x.rowid,x.postcode));
		}
		//create csv files
		if (orderedfile){
			createFile(orderedcodes,false, orderedfile);
		}
		createFile(validcodes,false, false);
		createFile(invalidcodes,true, false);
	}
	private void createFile(List<String> data, boolean invalidFile, boolean ordered){
		Path file;
		//default messages to display if errors occur
		String msg = "%s postcodes have been successfully exported";
		String failmsg = "Unable to create %s file.";
		//set up data for files
		if(invalidFile){
			file = Paths.get("failed_validation.csv");
			msg = String.format(msg, "Invalid");
			failmsg = String.format(failmsg, "invalid");
		}else if (ordered){
			file = Paths.get("ordered_file.csv");
			msg = String.format(msg, "Ordered");
			failmsg = String.format(failmsg, "ordered");
		}else{
			file = Paths.get("succeeded_validation.csv");
			msg = String.format(msg, "Valid");
			failmsg = String.format(failmsg, "valid");
		}
		//try and create file if not display fail message
		try {
			Files.write(file, data, Charset.forName("UTF-8"));
			System.out.println(msg);
		} catch (IOException e) {
			System.out.println(failmsg);
		}
	}
}
