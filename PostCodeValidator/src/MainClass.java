import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class MainClass {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<DataClass> postCodeData = new ArrayList<DataClass>();
		//used for program loop
		boolean exitloop = false;
		System.out.println("Welcome. Please follow the onscreen instructions."); //welcome message
		//program loop allowing users to select from menu of options
		while(!exitloop){
			int i = 0; //to store user inputted option
			System.out.println("Select one of the following options:");
			System.out.println("1 - Manually input Values");
			System.out.println("2 - Import Values from CSV");
			System.out.println("3 - Export Values");
			System.out.println("4 - Statistics from current data set");
			System.out.println("5 - Clear current data set");
			System.out.println("6 - Exit"); //actually all numbers about 5 will exit
			//check user has not inputed none integer value
			while(!scan.hasNextInt()){
				System.out.println("Invalid Input! (Only numbers are accepted)");
				scan.nextLine();
			}
			i = Integer.parseInt(scan.nextLine());
			//control what part of program is executed
			switch (i) {
				case 1:
					ManualClass mc = new ManualClass(scan);
					postCodeData.addAll(mc.postcodedata);
				break;
				case 2: 
					ImportClass ic = new ImportClass(scan);
					postCodeData.addAll(ic.postcodedata);
				break;
				case 3:
					if(postCodeData.size()>0){
						//sort data into correct order before creating files
						Collections.sort(postCodeData, DataClass.getCompbyRowID());
						ExportClass ex = new ExportClass(scan);
						ex.writeToFiles(postCodeData);
					}else{
						System.out.println("There is nothing to export.");
					}
				break;
				case 4:
					StatisticsClass sc = new StatisticsClass(postCodeData);
					//print out current data stats
					System.out.format("Number of records: %s\nNumber of Successes: %s\nNumber of Fails: %s\n",sc.numberofrecords,sc.numberofsuccesses,sc.numberoffails);
				break;
				case 5:
					postCodeData.clear();
					System.out.println("Data cleared successfully.");
				break;
				default:
					exitloop = true;
				break;
			}
		}
		//close scan before program closes
		scan.close();
	}
}
