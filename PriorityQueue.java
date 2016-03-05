/**
 * PriorityQueue
 * @author Mathew
 * with insertion sort
 */

public class PriorityQueue {
	private PCB front;
	private PCB back;
	private PCB spot;
	private PCB behindback;
	
	public PriorityQueue() {
		back = new PCB();  // sentinel PCB;
		front = new PCB(); 
		front.next=back;
		behindback=front;
		back.priority = -1;
		
	}
	
	/*
	 * PCB is to be inserted after spot
	 * priority is the priority of the PCB to be inserted;
	 * 
	 */
	 
	public void findSpot(PCB newJob){    
		spot = front;
		while ((spot.next.priority != -1) &&  spot.next.priority >= newJob.priority){
			spot = spot.next;
			
		}
	}
	
	public void insertback(PCB newJob){
		newJob.next=behindback.next;
		behindback=newJob;
	}
	
	public void insert(PCB newJob){
		findSpot(newJob);
		newJob.next = spot.next;
		spot.next = newJob;
		if (newJob.next==back)behindback=newJob;
	}
	
	public PCB remove(PCB job){
		PCB temp = front.next;
		front.next = front.next.next;
		return temp;
	}
	
	
}