public void run(int allocatedTime){
		if(!RQ.isEmpty()){
			tempPCB=RQ.remove();
			CPUstatus=1;
			}
		while(CPUstatus=1){
			is(tempPCB.isFirstTime){
				tempPCB.processTime()=0;//maybe you can intialize in creation
				tempPCB.isFirstTime=false;
				this.allocatedTime=allocatedTime;// or tempPCB.currentBurst();
			}
			if(tempPCB.processTime()<allocatedTime){
				tempPCB.processTime+=1;
				CPUclock+=1;
				tempPCB.currentBurst()=tempPCB.currentBurst()-tempPCB.processTime();
				
				//condition currentBurst is 0
				if(tempPCB.currentBurst()==0)
					if(tempPCB.lastBurst())lastBurst_case();break;//isFirstTime =true;//systemsjob--; //CPUstatus=0;
							else nextBurst_case();break;//isFirstTime=true//sent it to BQ //CPUstatus=0;
					
				//condition: 200 time unit
				if(CPUclock%200==0) print_200():
				
				//condition: 10 time unit pass
				if(CPUclock%10==0) ioprocess();
				
				
				
			}
			else allocatedTime_reach();//only in round robin
			//cpustatus=0;
			// add back to ready queues
			//tempPCB.isFirstTime=false;
			
		}}
		//if rq is empty but bq is not
		else if(!BQ.isEmpty()){
			CPUclock=IncrementUntilIOburst(CPUclock);
			RQ.add(BQ.remove());
		}
		//if both queues are empty
		else {System.out.println("Both Queues are empty");
		Request_More_jobs_from_Job_Queue=true;}
	}
	public int IncrementUntilIOburst(int clock){
		int counter;
			while(clock%10!=0){
				counter++;	
			}
			clock+=counter;
			return clock;
	}
	void allocatedTime_reach(){
		RQ.add(tempPCB);
		
	}