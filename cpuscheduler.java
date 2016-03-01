import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class cpuscheduler {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String algorithm=args[0];
		fileRead("new.txt");	
		
	}
	public static void fileRead(String text) throws FileNotFoundException{
		File test=new File(text);
		Scanner sc=new Scanner(test);
		String newLine=null;
		while(sc.hasNext()){
			JobQueue.push(newLine);//assuming job queue exist//need to specified the time it arrive to the jobquene 
			//with a timer though
		}
		sc.close();
	}
}
		
	
			
			
			
			
			
			

