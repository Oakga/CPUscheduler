import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class PCB implements Comparable<PCB>{
	public int time_of_arrival,time_in_BQ,Left_time_from_BQ,Left_time_from_RQ,Arrival_time_to_BQ,processing_time,Arrival_time_to_RQ,current_cpu_burst,job_id,num_cpu_burst,totalwait,priority;
	public String state;
	public List<Integer> CPU_bursts; 
	public PCB next;
	
	
	public PCB(int id,int amount_of_cpu_burst,int time_of_arrival) throws FileNotFoundException{
		
		this.job_id=id;
		this.num_cpu_burst=amount_of_cpu_burst;
		this.CPU_bursts =new ArrayList<Integer>(num_cpu_burst);
		this.state="New"; 
		this.current_cpu_burst=0;
		this.next = null; 
		this.time_of_arrival=time_of_arrival;

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
		for(int i=0;i<(CPU_bursts.size());i++){
			sb.append(CPU_bursts.get(i)+" ");
		}
		System.out.println("This is whats in the node:"+sb);
		}
	
	public void setNext(PCB next){
		this.next=next;
	}
	
	
	public void nextBurst(){
		System.out.println("current_cpu_burst(before): "+current_cpu_burst);

		if(current_cpu_burst<num_cpu_burst){
		current_cpu_burst++;
		}
		
		else{ 
			System.out.println("Error here");
			System.out.println("current_cpu_burst: "+current_cpu_burst);
			System.out.println("num_cpu_burst: "+num_cpu_burst);
		}
		}

	public boolean IsLastBurst() {
		if(current_cpu_burst+1>=num_cpu_burst)return true;
		else return false;
	}
	public int returnCurrent(){
		return CPU_bursts.get(current_cpu_burst);
	}
	public int PCBid(){
		return job_id;
	}
	private boolean equalTo(PCB node){
		int i=this.CPU_bursts.get(this.current_cpu_burst);
		System.out.println(this.job_id);
		System.out.println(node.job_id);
		System.out.println(i+" This current CPU burst comparing with "+node.CPU_bursts.get(node.current_cpu_burst));
		
		return (i==node.CPU_bursts.get(node.current_cpu_burst));
	}
	
	public int compareTo(PCB node) {
		int i=this.CPU_bursts.get(this.current_cpu_burst);
		if(equalTo(node))return 0;
		else if(i>node.CPU_bursts.get(current_cpu_burst)) return -1;
		else return 1;
	}
}
