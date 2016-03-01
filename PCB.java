import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.String;

public class PCB {
	public int job_id;
	public java.lang.String state;
	public int program_counter;
	public int num_cpu_burst;
	public int[] CPU_bursts;
	public int current_cpu_burst;
	public int iocompletion;
	private PCB next;
	//To set a PCB node you need to do 3 things
	//construct a PCB node, set it next PCB in the linked list, set the bursts
	//PCB note
	public PCB(int job_id,int num_cpu_burst){
		this.current_cpu_burst=0;
		this.job_id=job_id;
		this.num_cpu_burst=num_cpu_burst;
		this.CPU_bursts =new int[num_cpu_burst];
		this.state="Ready";
		this.iocompletion=0;
		this.program_counter=0;
	}
	//put the bursts in the array
	public void putInArray(String CPU_bursts){
		Scanner sc=new Scanner((Readable) CPU_bursts);
		int newInt=0;
		int counter=0;
		while(sc.hasNextInt()){
			newInt=sc.nextInt();
			this.CPU_bursts[counter]=newInt;
			counter++;
		}
	}
	//to set the next PCB linked list
	public void setNext(PCB next){
		this.next=next;
	}
	//to increment the next burst 
	public void nextBurst(){
		current_cpu_burst++;
	}
}
