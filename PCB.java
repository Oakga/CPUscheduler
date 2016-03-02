//import java.util.Scanner;


public class PCB {
	public int job_id;
	public java.lang.String state;
	public int program_counter;
	public int num_cpu_burst;
	public int[] CPU_bursts;
	public int current_cpu_burst;
	public int iocompletion;
	public PCB next;
	public int priority;
	//To set a PCB node you need to do 3 things
	//construct a PCB node, set it next PCB in the linked list, set the bursts  
	//PCB note
	public PCB(int job_id,int num_cpu_burst){
		this.current_cpu_burst=0;
		this.job_id=job_id;
		this.num_cpu_burst=num_cpu_burst;
		this.CPU_bursts =new int[num_cpu_burst];
		this.state="New"; //New while in Job queue, Ready once moved to ready queue;
		this.iocompletion=0;
		this.program_counter=0;
		this.next = null; //initially all processes will not have a next, next will be assigned by the scheduling algorithm
	}
	
	public PCB(){
		this.next = null;
	}
	//put the bursts in the array
	public void putInArray(String CPU_bursts){
		String [] array = CPU_bursts.split(" ");
		for (int i = 0; i < array.length; ++i){
			this.CPU_bursts[i] = Integer.parseInt(array[i]);
		};
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
