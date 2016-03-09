import java.util.Scanner;

public class FCFS extends PriorityQueue{
	PriorityQueue RQ=new PriorityQueue();
	PriorityQueue BQ=new PriorityQueue();
	int CPUclock=0;
	
	//allocating a new PCB node
	public static void FCFS(){}
	public PCB getJobs(String bursts){
		//to read the line into PCB
		Scanner scan=new Scanner(bursts);
		
		int job_id=0;
		int time_of_arrival=0;
		int num_cpu_burst=0;
		String CPU_bursts;
		
		job_id=scan.nextInt();
		time_of_arrival=scan.nextInt();
		num_cpu_burst=scan.nextInt();
		
		PCB pcbnode=new PCB(job_id,num_cpu_burst);
		int counter=0;
		int[] temparray=new int[num_cpu_burst];
		
		while(scan.hasNextInt()){
			temparray[counter]=scan.nextInt();
			counter++;
		}
		pcbnode.putInArray(temparray);
		scan.close();
		return pcbnode;
	}
	//Constructing the ready queue with the first 10 jobs
	public void ReadyQueue_Construct(String first_10_jobs){
	Scanner scan=new Scanner(first_10_jobs);
	String newline=null;
	PCB tempPCBnode=null;
	while(scan.hasNext()){
		newline=scan.next();
		tempPCBnode=getJobs(newline);
		RQ.insertback(tempPCBnode);
	}
	}
	
	//removing the first node from Ready Queue
	public PCB ReadyQueue_Remove(){
	PCB tempPCBnode = null;
	tempPCBnode=RQ.remove();
	return tempPCBnode;
	}
	
	//adding to the ready queue
	public PCB ReadyQueue_Add(String newJobline){
	PCB tempPCBnode=null;
	tempPCBnode=getJobs(newJobline);
	RQ.insertback(tempPCBnode);		
	return tempPCBnode;
	}
	
	//removing from the BlockedQueue
	public PCB BlockedQueue_Remove(){
	PCB tempPCBnode=null;
	tempPCBnode=BQ.remove();
	return tempPCBnode;
	}
	
	//Adding into the BlockedQueue
	public void BlockedQueue_Add(PCB newjob){
	BQ.insertback(newjob);
	}
	
	//CPU computation
	public void CPU(){
	
	}
	
}
