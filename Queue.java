package nonRetardedCPUScheduler;

public interface Queue {
	
	void findspot(PCB newJob);
	
	void insert(PCB newJob);
	
	PCB remove();
	
	boolean isEmpty();
	
	int size();
}
