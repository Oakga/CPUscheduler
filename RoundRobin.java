package nonRetardedCPUScheduler;

public class RoundRobin implements Queue{
	private PCB tail;
	private PCB curProcess;
	int size;
	
	public RoundRobin() {
		size = 0;
	}
	
	public void insert(PCB newJob)
	{
		if(isEmpty()){
			tail = newJob;
			tail.setNext(tail);
		}
		else {
			newJob.setNext(tail.getNext());
			tail.setNext(newJob);
			tail.setNext(tail.getNext());
		}
		++size;
	}
	
	public PCB remove()
	{
		PCB done = curProcess;
		curProcess = curProcess.getNext();
		done.setNext(null); //failsafe?
		return done;
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}

	public void findspot(PCB newJob)
	{
		
	}

	public int size()
	{
		return size;
	}
	
};

