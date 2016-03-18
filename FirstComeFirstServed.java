package nonRetardedCPUScheduler;

public class FirstComeFirstServed implements Queue {
	PCB front;
	PCB back;
	PCB sentinel;
	PCB spot;
	int size;
	
	
	FirstComeFirstServed()
	{
		sentinel = null;
		back.setNext(sentinel);
		front.setNext(back);
		size = 0;
	}
	
	
	@Override
	public void insert(PCB job)
	{
		job.setNext(back.getNext());
		back.setNext(job);
		back = job;
		++size;
	}

	@Override
	public void findspot(PCB newJob) {
		// TODO Auto-generated method stub not used for FCFS
		
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
