 //Anagogi me moirazomeno pinaka
public class er1 {
    public static void main(String[] args) {

        long numSteps = 10000;

        int numThreads = Runtime.getRuntime().availableProcessors();

       
        double[] tpi = new double[numThreads];
		for(int i = 0; i < numThreads; i++)
			tpi[i] = 0.0;
     
        /* start timing */
        long startTime = System.currentTimeMillis();

		// create threads
		Thread1 threads[] = new Thread1[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new Thread1(i, numThreads, tpi, numSteps);
			threads[i].start();
		}

		double pi = 0.0;          
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
                pi += tpi[i];

			} catch (InterruptedException e) {}
		}

        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}

 class Thread1 extends Thread{

    private double [] temp;
	private double mySum;
	private int myId;
	private int myStart;
	private int myStop;
    private long size;

    public Thread1(int id, int numThreads, double[] tpi, long s){
        temp = tpi;
        myId = id;
        size=s;
        myStart = myId * ((int)size / numThreads);
        myStop = myStart + ((int)size / numThreads);
        if (myId == (numThreads - 1)) myStop = (int)size;
        mySum = 0.0;
    }

    	public void run()
	{  
         double step = 1.0 / (double)size;
		for(int i = myStart; i < myStop; i++){
        
            {
                double x = ((double)i+0.5)*step;
                mySum += 4.0/(1.0+x*x);
            }
        } 

        temp[myId]=mySum*step;

	}



}