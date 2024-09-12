public class exe1 {

    public static void main(String[] args) {

        int numThreads = 4;
        int end = 1000;
        int[] array = new int[end];

		CounterThread threads[] = new CounterThread[numThreads];
		
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread(end,array);
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
     class CounterThread extends Thread {
  	
       private int end;
       private int[] array;
       public CounterThread(int e,int[] ar) {
         end=e;
         array=ar;
       }
  	
       public void run() {
  
            for (int i = 0; i < end; i++) {
				for (int j = 0; j < i; j++)
					array[i]++;		
            } 
		}            	
    }
    

