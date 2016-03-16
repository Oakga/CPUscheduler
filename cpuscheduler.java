import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class cpuscheduler {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String algorithm="FCFS",quantum,textfile;
		switch(algorithm){
		case "FCFS":{
			//textfile=args[2];
			quantum=null;
			FCFS TA=new FCFS();
			TA.run("JobQueue.txt");
			System.out.println("You have succeeded");
			break;
		}
		case "SJF":{
			textfile=args[2];
			quantum=null;
			//SJF(textfile);
		}
		case "RR":{
			textfile=args[3];
			quantum=args[2];
			//RR(quantum,textfile);
		}
		default:{}
		}
	}
	
}

		
	
			
			
			
			
			
			

