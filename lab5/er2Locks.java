import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//ylopoihsh me object
public class er2Locks {
    public static void main(String[] args) {

        long numSteps = 1000000;

        int numThreads = Runtime.getRuntime().availableProcessors();

        double step = 1.0 / (double)numSteps;
        mysumObj pi = new mysumObj();
     
        /* start timing */
        long startTime = System.currentTimeMillis();

		// create threads
		ThreadLo threads[] = new ThreadLo[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new ThreadLo(i, numThreads, numSteps,step,pi);
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

 class ThreadLo extends Thread{

	private double mySum;
	private int myId;
	private int myStart;
	private int myStop;
    private long size;
    private double step;
    private mysumObj sum;

    public ThreadLo(int id, int numThreads, long s,double step1,mysumObj sumob){
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
class mysumObj {

    double sum;
    Lock lock = new ReentrantLock();
    
    public mysumObj (){
		this.sum = 0;
    }

    public void add (double localsum){
       lock.lock();
	   try {
		    this.sum = this.sum + localsum;
	   } finally {
		    lock.unlock();
	   }
	  
    }

    public double getPi(){
        return sum;
    }

}
