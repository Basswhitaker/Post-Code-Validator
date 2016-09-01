import java.util.ArrayList;
import java.util.Scanner;

public class ManualClass {
	public ArrayList<DataClass> postcodedata;
	
	//class to allow users to add data manually
	public ManualClass(Scanner scan){
		postcodedata = new ArrayList<DataClass>();
		while(true){
			postcodedata.add(manualInput(scan));
			System.out.print("Would you like to add another? (Y/N)" );
			String s = scan.nextLine().toLowerCase();
			if (s.equals("n") || s.equals("no")){
				break;
			}
		}
	}
	
	private DataClass manualInput(Scanner scan){
		System.out.println("Please input a Row ID: ");
		while (!scan.hasNextInt()){
			System.out.println("Not a valid row id!");
			scan.next();
		}
		int rowID = scan.nextInt();
		scan.nextLine();
		System.out.println("Please input the PostCode: ");
		String postCode = scan.nextLine();
		DataClass dc = new DataClass(postCode, rowID);
		return dc;
	}
}
