package nonRetardedCPUScheduler;

public class ShortestJobFirst implements Queue {
	PCB front;
	PCB back;
	PCB sentinel;
	PCB spot;
	int size;
	
	ShortestJobFirst()
	{
		sentinel = null;
		back.setNext(sentinel);
		front.setNext(back);
		size = 0;
		
	}
	
	@Override
	public void insert(PCB job)
	{
		findspot(job);
		job.setNext(spot.getNext());
		spot.setNext(job);
		++size;
	}

	@Override
	public void findspot(PCB newJob) {
		spot = front;
		while(spot.getNext() != sentinel && spot.getNext().cpuBursts[spot.getNext().currentBurst] < newJob.cpuBursts[newJob.currentBurst]){
			spot = spot.getNext();
		}	
	}

	@Override
	public PCB remove() {
		PCB temp = front;
		front = front.getNext();
		--size;
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}
	
	public void print(){
		spot = front;
		while(spot.getNext() != sentinel){
			System.out.println("job id: " + spot.job_id + "burst: " + spot.cpuBursts[spot.currentBurst] + "\n");
			spot = spot.getNext();
		}
	}

}
