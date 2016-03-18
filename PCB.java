package nonRetardedCPUScheduler;

public class PCB {
  public int job_id;
  public String state;
  public int burstCount;
  public int[] cpuBursts;
  public int currentBurst;
  private PCB next;
  public int priority;
  public int ATRQ;
  public int ATBQ;
  public boolean virgin;
  public int start;
  
  public int programCounter;    //this makes alot of other variables obsolete;
  public int arrival;
  public int completion;
  public int processTime;
  public int waitTime;
  public int turnaround;
  
  public PCB()
  {
	  this.next = null;
  }
  
  public PCB(int job_id, int arrival_time, int burstCount, String Bursts)
  {
	  this.job_id = job_id;
	  this.burstCount = burstCount;
	  this.arrival = arrival_time;
	  this.cpuBursts = new int[burstCount];
	  this.currentBurst = 0;
	  this.state = "Ready";
	  this.programCounter = 0;
	  this.next = null;
	  this.waitTime = arrival;
	  this.processTime = 0;
	  virgin = true;
  };
  
  public PCB(String data){
  }
  public void initBursts(String Bursts)
  {
	  String[] array = Bursts.split(" ");
	  for (int i=0; i<array.length; ++i) {
		  this.cpuBursts[i] = Integer.parseInt(array[i]);
	  };
  };
  
  public void setNext(PCB next)
  {
	  this.next = next;
  }
  
  public PCB getNext()
  {
	  return next;
  }
  
  public boolean lastBurst(){
	  return (currentBurst == (burstCount-1));
  }
  
  public void printStats(){
	  //print a bunch of shit;
  }
}
