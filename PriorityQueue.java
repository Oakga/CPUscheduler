/**
 * PriorityQueue
 * @author Mathew
 * with insertion sort
 */

public class PriorityQueue {
	private PCB front;
	private PCB back;
	private PCB spot;
	
	public PriorityQueue() {
		back = new PCB();  // sentinel PCB;
		front = new PCB(); 
		back.priority = -1;
		front.setNext(back);
	}
	
	/*
	 * PCB is to be inserted after spot
	 * priority is the priority of the PCB to be inserted;
	 * 
	 */
	public void findSpot(PCB newJob){    
		spot = front;
		while ((spot.next.job_id != -1) &&  spot.next.priority >= newJob.priority){
			spot = spot.next;
		}
	}
	
	public void insert(PCB newJob){
		findSpot(newJob);
		newJob.next = spot.next;
		spot.next = newJob;
	}
	
	public PCB remove(PCB job){
		PCB temp = front.next;
		front.next = front.next.next;
		return temp;
	}
	
	
}
