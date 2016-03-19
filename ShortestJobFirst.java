package CPUScheduler;

import java.util.Iterator;
import java.util.PriorityQueue;

public class ShortestJobFirst extends PriorityQueue<PCB> {
	public void print(){
		PCB current=null;
		Iterator<PCB> iter=iterator();
		while(iter.hasNext()){
			current=iter.next();
			System.out.print(current+" ");
			System.out.println(" ");
		}
	}
}
