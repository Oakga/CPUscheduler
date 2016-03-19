package CPUScheduler;

public class ShortestJobFirst implements Queue {
	PCB front;
	PCB back;
	PCB sentinel;
	PCB spot;
	int size;
	
	ShortestJobFirst()
	{
		sentinel = new PCB();
		//sentinel = null;
		back = new PCB();
		back.setNext(sentinel);
		front = new PCB();
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
		//spot = new PCB();
		spot = front;
		while(spot.getNext().getNext() != sentinel && spot.getNext().cpuBursts[spot.getNext().currentBurst] < newJob.cpuBursts[newJob.currentBurst]){
			spot = spot.getNext();
		}	
	}

	@Override
	public PCB remove() {
		if (isEmpty()) { 
			System.out.println("there are no jobs in the Queue"); 
			return null;
		}
		PCB temp = front.getNext();
		front = temp.getNext();
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
		spot = front.getNext();
		while(spot.getNext() != sentinel){
			System.out.println("jobID: " + spot.job_id + " burst: " + spot.cpuBursts[spot.currentBurst]);
			spot = spot.getNext();
		}
	}

}
