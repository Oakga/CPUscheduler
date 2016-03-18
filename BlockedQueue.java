package nonRetardedCPUScheduler;


/*
 * just a regular Queue;
 */
public class BlockedQueue implements Queue{
	int size;
	PCB first;
	int iocounter;
	BlockedQueue(){
		iocounter = 0;
	}
	
	public void insert(PCB job){
		
	}
	
	public PCB remove() 
	{
		if (isEmpty()) {/* nothing to remove */ }
		return first;
		
	}
	
	public void findspot(PCB newJob)
	{
		
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public int size()
	{
		return size;
	}
	
}
