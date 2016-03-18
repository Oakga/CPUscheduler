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
	boolean isFirsttime=true;
	boolean needmore=false;
	
	//Algorithm constructor
	public FCFS(){
		CPUclock=0;
		Systemjobs=0;
	}
	//**********************MAIN RUNNING PROGRAM****************
	public void run(String filename) throws FileNotFoundException{
		File givenfile=new File(filename);
		Scanner scan=new Scanner(givenfile);
		String newline=null;
		PCB tempPCB=null;
		
		Systemjobs=0;
		while(Systemjobs<10){
		scan.hasNextLine();
		newline=scan.nextLine();
		ReadyQueue_Add(newline);
		RQ.size();
		Systemjobs++;
		}//while
		/**************************Ready Queue Construction**************************/
		while(Systemjobs>0){
		
		gothroughburst();
		System.out.println("Number of System jobs: "+Systemjobs);
		//if one job goes out another one come in
		if(needmore==true){
		if(scan.hasNextLine()){
			System.out.println("putting in new node here");
			newline=scan.nextLine();
			ReadyQueue_Add(newline);
			System.out.println("Size of RQ after adding"+RQ.size()+"Size of BQ after adding"+BQ.size());

			Systemjobs++;
			}//if
		}
		}//while
		
		System.out.println("ReadyQueue_size_in_the_end "+RQ.size());
		scan.close();
	}
	
	public void gothroughburst(){
		PCB tempPCB=null;
		PCB tempPCB2=null;
		if(!RQ.isEmpty()){
			tempPCB=ReadyQueue_Remove();
			System.out.println("going through tempPCB :"+tempPCB.PCBid());
			System.out.println("PCB current value: "+tempPCB.returnCurrent());
			while(true){
				if(isFirsttime){
					System.out.println("Since we are running for the first time for value "+tempPCB.returnCurrent());
					PCB_burst_process_time_unit=0;
					PCB_burst_left_time_unit=tempPCB.returnCurrent();
					//System.out.println(PCB_burst_left_time_unit);
					isFirsttime=false;
								}
				PCB_burst_process_time_unit+=1;
				CPUclock+=1;
				PCB_burst_left_time_unit=tempPCB.returnCurrent()-PCB_burst_process_time_unit;
				/*System.out.println("Return current cpu burst:"+tempPCB.returnCurrent());
				System.out.println("PCB_burst_process_time_unit"+PCB_burst_process_time_unit);
				System.out.println("PCB_burst_left_time_unit:"+PCB_burst_left_time_unit);*/
				
				//CONDITION:IOBURST
				if(CPUclock%10==0){
					if(!BQ.isEmpty()){
						System.out.println("CPUclock: "+CPUclock);
						System.out.println("Io process time since ");
						System.out.println("Blocked Queue is not empty ");
						tempPCB2=BlockedQueue_Remove();
						ReadyQueue_Add(tempPCB2);
						System.out.println("Removed from BQ and Put into RQ PCB number: "+tempPCB2.PCBid());
						
						System.out.println("RQ and BQ stats after removing from BQ ");
						System.out.println("Size of RQ "+RQ.size()+" |||  "+"Size of BQ "+BQ.size());

									}//second if
									}//first if
				
				//CONDITION:BURST COMPLETE LAST OR NOT
				if(PCB_burst_left_time_unit<=0){
					if(tempPCB.IsLastBurst()){
						System.out.println("Last burst completed: "+tempPCB.returnCurrent());
						isFirsttime=true;
						System.out.println("RQ and BQ stats after last burst as follow ");
						System.out.println("Size of RQ "+RQ.size()+" |||  "+"Size of BQ "+BQ.size());

						--Systemjobs;
						needmore=true;
						break;
						}//second if(tempPCB.IsLastBurst()){
					else{
						System.out.println("Current burst completed: "+tempPCB.returnCurrent());
						tempPCB.nextBurst();
						BlockedQueue_Add(tempPCB);
						System.out.println("Removed from CPU and put into Blocked Queue PCB number "+tempPCB.PCBid());
						isFirsttime=true;
						System.out.println("RQ and BQ stats after current burst as follow");
						System.out.println("Size of RQ "+RQ.size()+" |||  "+"Size of BQ "+BQ.size());

						needmore=false;
						break;
						}//else
						}//first if(PCB_burst_left_time_unit<=0){
						}//while true	
						}//if ready queue is not empty case
		else if(!BQ.isEmpty()){
				System.out.println("RQ is empty but BQ is not case:");
				CPUclock+=10;
				tempPCB2=BlockedQueue_Remove();
				System.out.println("Removed from BQ and Put into RQ PCB number: "+tempPCB2.PCBid());
				ReadyQueue_Add(tempPCB2);
				System.out.println("RQ and BQ stats after removing from BQ ");
				System.out.println("Size of RQ "+RQ.size()+" |||  "+"Size of BQ "+BQ.size());

			}//if BQ is not empty
		else {System.out.println("both Q are empty");
		System.out.println("RQ and BQ stats at the end: ");
		System.out.println("Size of RQ "+RQ.size()+" |||  "+"Size of BQ "+BQ.size());

		needmore=true;
		}
	
	}//method end
		
	
	
	
	
	
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
	PCB tempPCBnode = (PCB) RQ.remove();
	tempPCBnode.state="running";
	
	
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
		tempPCB.printPCBbursts();
	}
	private void TimeUnit200(){
		System.out.println("200 time unit reach");
	}
	

}

	
