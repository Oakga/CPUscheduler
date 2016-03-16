import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FCFS extends PriorityQueue{
	Queue RQ=new LinkedList<PCB>();
	Queue BQ=new LinkedList<PCB>();
	int time_in_BQ,CPUclock,Systemjobs,PCB_burst_process_time_unit,PCB_burst_left_time_unit;

	
	//Algorithm constructor
	public FCFS(){
		CPUclock=0;
		Systemjobs=0;
	}
	//**********************MAIN RUNNING PROGRAM****************
	public void run(String filename) throws FileNotFoundException{
		File givenfile=new File(filename);
		
		Scanner scan= new Scanner(givenfile);
		//reading in file
		
		String newline=null;
		PCB tempPCB=null;
		
		while(Systemjobs<=9){
			if(scan.hasNextLine()){
				newline=scan.nextLine();
				ReadyQueue_Add(newline);
				++Systemjobs;
			}
			else {break;}
		}//while loop
		System.out.println("_________________Ready_Queue Construction complete____________");
		int i=10;
		while(true){
			if(Systemjobs<=9){
				if(scan.hasNextLine()){
					newline=scan.nextLine();
					ReadyQueue_Add(newline);
					++Systemjobs;
					System.out.println(i);
					}//reincrement the system jobs number
				}
			
			//if next line exist read in to fill in the ready queue
			//otherwise finish whatever left in the system
			
			if(Systemjobs==0 && !(scan.hasNextLine()))break;
				tempPCB=ReadyQueue_Remove();
				tempPCB.printPCBbursts();
				--Systemjobs;
				i++;
				//CPU_compute(tempPCB);//start running
				//runjob will come back here after changing system jobs number and thus another cycle continue;
				
			 
		}
		scan.close();//don't forget to close the scanner
	}
	
	
	//********************Ready Queue****************
	private PCB getJobs(String bursts) throws FileNotFoundException{
		//to read the line into PCB
		Scanner scan=new Scanner(bursts);
		
		//read in variables
		int job_id,time_of_arrival,num_cpu_burst=0;
		String CPU_bursts;
		
		//reading in
		job_id=scan.nextInt();
		time_of_arrival=scan.nextInt();
		num_cpu_burst=scan.nextInt();
		
		//new PCB node
		PCB pcbnode=new PCB(job_id,num_cpu_burst);
		
		CPU_bursts=scan.nextLine();
		System.out.println("Scanning in:"+CPU_bursts);
		pcbnode.putInArray(CPU_bursts);
		scan.close();
		return pcbnode;
	}
	
	
	//removing the first node from Ready Queue
	private PCB ReadyQueue_Remove(){
		System.out.println("Amount of systemjobs before removing:"+Systemjobs);
	PCB tempPCBnode = (PCB) RQ.remove();
	tempPCBnode.state="running";
	System.out.println("size of RQ after removing:"+RQ.size());
	
	return tempPCBnode;
	}
	
	//adding to the ready queue
	private void ReadyQueue_Add(String newJobline) throws FileNotFoundException{
	PCB tempPCB=null;
	
	//tempPCB.Arrival_time_to_RQ=CPUclock; this is handle by getjobs
	tempPCB=getJobs(newJobline);
	tempPCB.state="ready";
	tempPCB.totalwait+=CPUclock-(tempPCB.Arrival_time_to_RQ);
	RQ.add(tempPCB);	
	System.out.println("After adding to RQ,RQ size:"+RQ.size());
	}
	
	private void ReadyQueue_Add(PCB tempPCB){
		tempPCB.state="ready";
		tempPCB.totalwait+=CPUclock-(tempPCB.Arrival_time_to_RQ);
		RQ.add(tempPCB);		
	}
	
	//****************Blocked Queue*********************
	//removing from the BlockedQueue
	private PCB BlockedQueue_Remove(){
		PCB tempPCB=null;
		tempPCB=(PCB) BQ.remove();
		tempPCB.state="Ready";
		return tempPCB;
	}
	
	private void BlockedQueue_Add(PCB tempPCB){
		tempPCB.Arrival_time_to_BQ=CPUclock;
		tempPCB.state="blocked";
		BQ.add(tempPCB);
	}
	
	
	//****************CPU computations helpers*****************
	
	private void print(PCB tempPCB){	
		System.out.println("print PCB");
	
	}
	private void TimeUnit200(){
		System.out.println("200 time unit reach");
	}
	
	//******************************RUNNING PROCESSS********************
	
	//PCB_burst_left_time_unit has to be set to current cpu burst in run
	private void CPU_compute(PCB tempPCB){
		PCB_burst_process_time_unit=0;
		while(true){
			
		//processing
		PCB_burst_process_time_unit+=1;
		PCB_burst_left_time_unit=(tempPCB.returnCurrent())-PCB_burst_process_time_unit;
		CPUclock+=1;
		
		//******************************Cases***********************************
		//if finish case
		if(PCB_burst_left_time_unit==0){
			if(tempPCB.IsLastBurst())
			{ //if it is last cpu burst of the PCB
				print(tempPCB); //then print its values
				Systemjobs--;
				PCB_burst_process_time_unit=0;
				break; //this will go back and put in a new job
			}
			else
			{ 
				stopjob(tempPCB);
				tempPCB.nextBurst();
			}
		}//finish 0 case
		if(CPUclock%10==0){
			if(!BQ.isEmpty())IOprocess();
		}
		
		//200 time unit case
		if(CPUclock==200){
			TimeUnit200();
		}
		
		}//while true loop
	}//end of compute method class
	
	private void IOprocess(){
			PCB tempPCB=null;
			tempPCB=BlockedQueue_Remove();
			ReadyQueue_Add(tempPCB);
			tempPCB.time_in_BQ=(CPUclock-tempPCB.time_in_BQ);
	}
	private void stopjob(PCB tempPCB){
		
		BlockedQueue_Add(tempPCB);
		tempPCB.time_in_BQ = CPUclock;
		//ready_queue_add
		
	}
}
	