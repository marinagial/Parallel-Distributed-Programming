public class er3Sync {
    public static void main(String[] args) {

        long numSteps = 10000;

        int numThreads = Runtime.getRuntime().availableProcessors();

       // double sumpi=0.0;
        double step = 1.0 / (double)numSteps;
        sumObjSync pi = new sumObjSync();
     
        /* start timing */
        long startTime = System.currentTimeMillis();

		// create threads
		ThreadSync threads[] = new ThreadSync[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new ThreadSync(i, numThreads, numSteps,step,pi);
			threads[i].start();
		}

		// wait for threads to terminate            
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}




        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi.getPi());
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi.getPi() - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);

    }
}

 class ThreadSync extends Thread{

	private double mySum;
	private int myId;
	private int myStart;
	private int myStop;
    private long size;
    private double step;
    private sumObjSync sum;

    public ThreadSync(int id, int numThreads, long s,double step1,sumObjSync sumob){
        myId = id;
        size=s;
        step=step1;
        sum=sumob;
        myStart = myId * ((int)size / numThreads);
        myStop = myStart + ((int)size / numThreads);
        if (myId == (numThreads - 1)) myStop = (int)size;
        mySum = 0.0;
    }

    	public void run()
	{  
		for(int i = myStart; i < myStop; i++){
        
            {
                double x = ((double)i+0.5)*step;
                mySum += 4.0/(1.0+x*x);
            }
        } 
        sum.add(mySum*step); 

	}

    
}
class sumObjSync {

    double sum;

    public sumObjSync (){
		this.sum = 0;
    }

    public synchronized void add (double localsum){
		this.sum = this.sum + localsum;
    }

    public synchronized double getPi(){
        return sum;
    }

}
