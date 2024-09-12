import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class er2LocksGlobal {

    public static double pi;
	public static Lock lock = new ReentrantLock();
    public static void main(String[] args) {


        long numSteps = 10000;

        int numThreads = Runtime.getRuntime().availableProcessors();

        double step = 1.0 / (double)numSteps;

        pi=0.0;

        /* start timing */
        long startTime = System.currentTimeMillis();

		// create threads
		aThreadL threads[] = new aThreadL[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new aThreadL(i, numThreads, numSteps,step);
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
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}
class aThreadL extends Thread{

	private int myId;
	private int myStart;
	private int myStop;
    private long size;
    private double step;
    private double sum;

    public aThreadL(int id, int numThreads, long s,double step1){
        myId = id;
        size=s;
        step=step1;
        myStart = myId * ((int)size / numThreads);
        myStop = myStart + ((int)size / numThreads);
        if (myId == (numThreads - 1)) myStop = (int)size;

    }
    public void run()
	{  
		for(int i = myStart; i < myStop; i++){
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
        } 
    er2LocksGlobal.lock.lock();
       try {
       er2LocksGlobal.pi+=sum*step;
   } finally {
       er2LocksGlobal.lock.unlock();
   }
} 
  }
          


