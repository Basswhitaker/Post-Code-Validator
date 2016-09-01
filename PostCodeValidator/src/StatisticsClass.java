import java.util.List;

public class StatisticsClass {
	public int numberoffails = 0;
	public int numberofsuccesses = 0;
	public int numberofrecords = 0;
	
	public StatisticsClass(List<DataClass> postCodeData){
		//gather stats for the current data set by the user
		numberofrecords = postCodeData.size();
		for(DataClass x: postCodeData){
			if(x.validcode){
				numberofsuccesses++;
			}else{
				numberoffails++;
			}
		}
		//plans to extend this class to include more options, possible upgrade
	}
}
