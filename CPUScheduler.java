package nonRetardedCPUScheduler;

import java.io.*;
import java.util.*;


public class CPUScheduler {
	Object ReadyQueue;
	public static int CPUClock;
	public BlockedQueue BlockedQueue;;
	//public int systemJobs;
	public int CPUStatus = 1;
	public int CPUallocatedBurst;
	public int timeslice;
	public String InputLine;
	public int printcounter;
	public Scanner inFile;
	
	PCB getJob(String data) //creates a PCB object from a given string (line)
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
	
	public void insertRQ(PCB job) //insert into ReadyQueue.
	{
		((Queue)ReadyQueue).insert(job);
		job.waitTime += (CPUClock - job.ATBQ);
		job.ATRQ = CPUClock;
		//job.state = "Ready"; //this is already done by the constructor;
		//++systemJobs;
	}
	
	public void runJob(PCB job)
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
			//if 10 time units have passed 
			if (BlockedQueue.iocounter >= 10 ){
				BlockedQueue.remove();
				BlockedQueue.iocounter = 0;
			}
			//print if 200 time units have passed
			if(printcounter >= 200){
				printStats();
				printcounter = 0;
			}
			
			++BlockedQueue.iocounter;
			++printcounter;
			++CPUClock;
			++job.programCounter;
			--timeslice;
			//completion check
			if(job.lastBurst() && job.cpuBursts[job.currentBurst] == 0) completeJob(job);
			else if (job.cpuBursts[job.currentBurst] == 0) stopJob(job);
		}

		
		CPUStatus =0;
		
	} //end of runJOb
	
	public void printStats() {
		
	}
	
	public void completeJob(PCB job) {
		job.completion = CPUClock;
		job.state = "Completed";
		//calculate stats;
	}
	
	public void stopJob(PCB job){
		BlockedQueue.insert(job);
		job.ATBQ = CPUClock;
		job.state = "Blocked";
		//time in BQ = 0;  //why did I put this in here again?
/*		if (CPUallocatedBurst >= 10){	//redo this part
			job.waitTime += (job.ATBQ - CPUClock);
			insertRQ(BlockedQueue.remove());
		}
*/	//constant check.		
	}
	
	public void setDispatch(String A, String B, String C) throws FileNotFoundException
	{
		switch (A)
		{
		case "FCFS":
			ReadyQueue = new FirstComeFirstServed();
			inFile = new Scanner(new FileReader(B));
			break;
		case "SJF":
			ReadyQueue = new ShortestJobFirst();
			inFile = new Scanner(new FileReader(B));
			break;
		case "RR":
			ReadyQueue = new RoundRobin();
			timeslice = Integer.parseInt(B);
			inFile = new Scanner(new FileReader(C));
			break;
		}
	}
	
	
	public void main(String args[]) throws FileNotFoundException { 
		BlockedQueue = new BlockedQueue();
		StringBuilder builder=new StringBuilder();
		for(String a: args){
			builder.append(a+" ");
		}
		InputLine=builder.toString();
		
		setDispatch(InputLine);
		printcounter = 0;
		String data = inFile.nextLine();
		
		while (true) {
			if (CPUStatus + ((Queue)ReadyQueue).size() +BlockedQueue.size() <10) insertRQ(getJob(data));
			if (CPUStatus==1)runJob(((Queue)ReadyQueue).remove());
		}
		
		
	}
}
