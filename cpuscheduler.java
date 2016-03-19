package CPUScheduler;

import java.io.*;
import java.util.*;


public class CPUScheduler {
	static PriorityQueue<PCB> ReadyQueue;
	
	
	public static int CPUClock;
	public static BlockedQueue BlockedQueue;;
	//public int systemJobs;
	public static int CPUStatus = 1;
	public int CPUallocatedBurst;
	public static int timeslice;
	public static String Alg;
	public static int printcounter;
	public static Scanner inFile;
	
	static PCB getJob(String data) //creates a PCB object from a given string (line)
	{
		Scanner scan=new Scanner(data);//read data
		
		int job_id,arrival_time,burstCount=0;
		String bursts;
		PCB PCBnode;
		
		job_id=scan.nextInt();
		arrival_time=scan.nextInt();
		burstCount=scan.nextInt();
		bursts=scan.nextLine();
		
		PCBnode=new PCB(job_id,arrival_time,burstCount,bursts);
		
		scan.close();
		
		return PCBnode;

	}
	
	public static void insertRQ(PCB job) //insert into ReadyQueue.
	{
		ReadyQueue.add(job);
		job.waitTime += (CPUClock - job.ATBQ);
		job.ATRQ = CPUClock;
		//job.state = "Ready"; //this is already done by the constructor;
		//++systemJobs;
	}
	
	public static void runJob(PCB job)
	{
		CPUStatus = 1;
		job.waitTime += (CPUClock-job.ATRQ);
		job.state = "Running"; 			//not that this shit matters;

		if(Alg == "FCFS" || Alg == "SJF") timeslice = job.cpuBursts[job.currentBurst];
		
		if(job.virgin){
			job.start = CPUClock;
			job.virgin =false;
		}
		
		while(timeslice > 0) {
			--job.cpuBursts[job.currentBurst];
			++BlockedQueue.iocounter;
			++printcounter;
			++CPUClock;
			System.out.println(CPUClock);
			++job.programCounter;
			--timeslice;
			
			//if 10 time units have passed 
			if (BlockedQueue.iocounter >= 10 ){
				insertRQ(BlockedQueue.remove());
				BlockedQueue.iocounter = 0;
			}
			//print if 200 time units have passed
			if(printcounter>=200){
				printStats();
				printcounter = 0;
			}
		
			//completion check
			if(job.lastBurst() && job.cpuBursts[job.currentBurst] == 0) { 
				completeJob(job);
				CPUStatus=0;
			}
			
			else if (job.cpuBursts[job.currentBurst] == 0){ 
				stopJob(job);
				CPUStatus=0;
			}
			
		}

		
	} //end of runJOb
	
	public static void printStats() {
		((Queue)ReadyQueue).print();
	}
	
	public static void completeJob(PCB job) {
		job.completion = CPUClock;
		++job.currentBurst;
		job.state = "Completed";
		job.printStats();
		
		//calculate stats;
	}
	
	public static void stopJob(PCB job){
		BlockedQueue.insert(job);
		++job.currentBurst;
		job.ATBQ = CPUClock;
		job.state = "Blocked";
		//time in BQ = 0;  //why did I put this in here again?
/*		if (CPUallocatedBurst >= 10){	//redo this part
			job.waitTime += (job.ATBQ - CPUClock);
			insertRQ(BlockedQueue.remove());
		}
*/	//constant check.		
	}
	
	public static void setDispatch(String benis) throws FileNotFoundException
	{
		String []bnes = benis.split(" ");
		Alg = bnes[0];
		switch (Alg)
		{
		case "FCFS":
			//ReadyQueue = new FirstComeFirstServed();
			inFile = new Scanner(new FileReader(bnes[1]));
			break;
		case "SJF":
			ReadyQueue = new ShortestJobFirst();
			inFile = new Scanner(new FileReader(bnes[1]));
			break;
		case "RR":
			//ReadyQueue = new RoundRobin();
			timeslice = Integer.parseInt(bnes[1]);
			inFile = new Scanner(new FileReader(bnes[2]));
			break;
		}
	}
	
	
	public static void main(String args[]) throws FileNotFoundException { 
		BlockedQueue = new BlockedQueue();
		//CPUClock= 0;
		
		StringBuilder builder=new StringBuilder();
		for(String a: args){
			builder.append(a+" ");
		}
		String InputLine=builder.toString();
		
		setDispatch(InputLine);
		printcounter = 0;
		/*Checking readyQueue
		int a=0;
		while(a<10) {
			String x=inFile.nextLine();
			System.out.println(x);
			
			insertRQ(getJob(x));
			
			++a;
		}

		PCB node;
			while(!ReadyQueue.isEmpty()){
				node=((PCB)ReadyQueue.remove());
				node.printStats();
				node.printBursts();
				
			}
*/PCB node;
	CPUStatus=0;
	boolean first;
	boolean second;
	boolean third;
	int job_id;
		/*while(true){
			first=(CPUStatus + (ReadyQueue).size() +BlockedQueue.size() <10);
			if(first) {
				if(inFile.hasNextLine()){
				insertRQ(getJob(inFile.nextLine()));
				System.out.println("insert");
				}
			}
			
			second=(!(ReadyQueue).isEmpty());
			if(!(ReadyQueue).isEmpty()){
				System.out.println("Run");

				node=((ReadyQueue).remove());
				job_id=node.job_id;
				runJob(node);
				CPUStatus=0;
				}
			third=(CPUStatus+(ReadyQueue).size() +BlockedQueue.size() ==0 && !(inFile.hasNextLine()));
			if (CPUStatus+(ReadyQueue).size() +BlockedQueue.size() ==0 && !(inFile.hasNextLine())) break;
		}
			
		System.out.println("Victory Screen");
		System.out.println(CPUClock);
			*/
	int a=0;
	while(a<10) {
		String x=inFile.nextLine();
		System.out.println(x);
		
		insertRQ(getJob(x));
		
		++a;
	}

	PCB node1;
		while(!ReadyQueue.isEmpty()){
			node1=((PCB)ReadyQueue.remove());
			node1.printStats();
			node1.printBursts();
			
		}
	
}
	}	
	

