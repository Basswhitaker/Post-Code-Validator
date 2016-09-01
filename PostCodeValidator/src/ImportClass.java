import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class ImportClass {
	public File datafile;
	public ArrayList<DataClass> postcodedata;
	public String fileext;
	
	public ImportClass(Scanner scan){
		datafile = getfile(scan);
		postcodedata = new ArrayList<DataClass>();
		if (datafile != null){
			importfile();
		}
	}
	
	private File getfile(Scanner scan){
		//Loop to make sure a file is collected
		while(true){
			//prompt user for file path and name input and show what the rules are
			System.out.println("(Accepted formats are CSV or CSV.GZ, to exit type EXIT)");
			System.out.print("Please input file path and name: ");
			String filename = scan.nextLine();
			//allow users to exit file entry if this option was chosen by mistake
			if (filename.toLowerCase().equals("exit") || filename.toLowerCase().equals("end")){
				return null;
			}
			File f = new File(filename);
			//check if file is valid
			if(f.exists() && !f.isDirectory()){
				int i = f.getName().lastIndexOf(".");
				fileext = f.getName().substring(i+1);
				//check file extension is also valid
				if (fileext.equals("gz") || fileext.equals("csv")){
					return f;
				}else{
					System.out.println("Invalid file type.");
				}
			}else{
				System.out.println("Invalid file.");
			}
		}
	}
	
	private void importfile(){
		Reader decoder = null;
		InputStream fileStream = null;
		InputStream gzipStream = null;
		if (fileext.equals("gz")){
			//used to decrypt gz file type and import data
			try {
				fileStream = new FileInputStream(datafile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				gzipStream = new GZIPInputStream(fileStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				decoder = new InputStreamReader(gzipStream, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			//if file is csv then import data as normal
			try {
				decoder = new FileReader(datafile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Importing...");
		try (BufferedReader in = new BufferedReader(decoder)){
			String[] line;
			String data;
			//looping through lines and adding to a collection
			while((data = in.readLine()) != null){
				//split line into array
				line = data.split(",");
				//check for headers
				if(!line[0].equals("row_id")){
					//if data isn't valid move to next line
					try{
						postcodedata.add(new DataClass(line[1],Integer.parseInt(line[0])));
					}catch(NumberFormatException e){
						System.out.println(String.format("Invalid data. Either post code or id wasn't valid. (%s, %s)",line[0],line[1]));
					}
				}
			}
			System.out.println("Import successful!");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
