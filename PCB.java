import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class PCB {
	public int time_in_BQ,Left_time_from_BQ,Left_time_from_RQ,Arrival_time_to_BQ,processing_time,Arrival_time_to_RQ,current_cpu_burst,job_id,num_cpu_burst,totalwait,priority,iocompletion;
	public String state;
	public List<Integer> CPU_bursts; 
	public PCB next;
	public int counter;
	
	public PCB(int id,int amount_of_cpu_burst) throws FileNotFoundException{
		
		this.job_id=id;
		this.num_cpu_burst=amount_of_cpu_burst;
		this.CPU_bursts =new ArrayList<Integer>(num_cpu_burst);
		this.state="New"; 
		this.current_cpu_burst=0;
		//System.out.println(current_cpu_burst);
		this.iocompletion=0;
		this.next = null; 
		this.counter=0;
		
	}
	
	public PCB(){
		this.next = null;
	}
	
	/**********************methods*********************/
	
	public void putInArray(String bursts){
		Scanner scan=new Scanner(bursts);
		while(scan.hasNextInt()){
			CPU_bursts.add(scan.nextInt());
		}
		scan.close();
	}
	
	public void printPCBbursts(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<(CPU_bursts.size()-1);i++){
			sb.append(CPU_bursts.get(i)+" ");
		}
		System.out.println("This is whats in the node:"+sb);
		}
	
	public void setNext(PCB next){
		this.next=next;
	}
	
	
	public void nextBurst(){
		current_cpu_burst++;
	}

	public boolean IsLastBurst() {
		return (current_cpu_burst>=num_cpu_burst);
	}
	public int returnCurrent(){
		System.out.println(CPU_bursts.get(current_cpu_burst));
		return CPU_bursts.get(current_cpu_burst);
	}
}
