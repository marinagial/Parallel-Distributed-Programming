import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock; 

public class exe2Lock {
    public static void main(String[] args) {

        int numThreads = 4;
        int end = 1000;
        int[] array = new int[end];
        Lock lock = new ReentrantLock();


		CounterThread1 threads[] = new CounterThread1[numThreads];
		
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread1(end,array,lock);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
		check_array (numThreads,end,array);
    }
     
    static void check_array (int numThreads,int end,int[] array)  {
		int i, errors = 0;
        

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array[i] != numThreads*i) {
				errors++;
				System.out.printf("%d: %d should be %d\n", i, array[i], numThreads*i);
			}         
        }
        System.out.println (errors+" errors.");
    }
}

     class CounterThread1 extends Thread {
  	
       private int end;
       private int[] array;
       private Lock lock;

       public CounterThread1(int e,int[] ar,Lock l) {
         end=e;
         array=ar;
         lock=l;
       }
  	
       public void run() {
  
      for (int i = 0; i < end; i++) {
				for (int j = 0; j < i; j++){
           lock.lock();
                try{
                    array[i]++;	
                }finally {
                    lock.unlock() ;
                }
        }
       } 
		}            	
    }
    

