public class er1Get {
    public static void main(String[] args) {

        long numSteps = 10000;

        int numThreads = Runtime.getRuntime().availableProcessors();

        double sumpi=0.0;
        double step = 1.0 / (double)numSteps;
     
        /* start timing */
        long startTime = System.currentTimeMillis();

		// create threads
		Thread2 threads[] = new Thread2[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new Thread2(i, numThreads, numSteps,step);
			threads[i].start();
		}

		// wait for threads to terminate            
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
                sumpi += threads[i].Get();
			} catch (InterruptedException e) {}
		}

		double pi = sumpi*step;


        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}

 class Thread2 extends Thread{

	private double mySum;
	private int myId;
	private int myStart;
	private int myStop;
    private long size;
    private double step;

    public Thread2(int id, int numThreads, long s,double step1){
        myId = id;
        size=s;
        step=step1;
        myStart = myId * ((int)size / numThreads);
        myStop = myStart + ((int)size / numThreads);
        if (myId == (numThreads - 1)) myStop = (int)size;
        mySum = 0.0;
    }

    //Anagogi me get
    public double Get(){
        return mySum;
    }
    	public void run()
	{  
		for(int i = myStart; i < myStop; i++){
        
            {
                double x = ((double)i+0.5)*step;
                mySum += 4.0/(1.0+x*x);
            }
        } 

	}

}
