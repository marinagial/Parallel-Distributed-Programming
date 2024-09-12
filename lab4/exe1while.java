public class exe1while {


    public static void main(String[] args) {

         int end = 10000;
         int counter = 0;
         int[] array = new int[end];
         int numThreads = 4;

        CounterThread threads[] = new CounterThread[numThreads];
	
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread(counter,array,end);
			threads[i].start();
		} 
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
        check_array (end,array);
    }
     
    static void check_array (int end , int[] array )  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array[i] != 1) {
				errors++;
				System.out.printf("%d: %d should be 1\n", i, array[i]);
			}         
		}
        System.out.println (errors+" errors.");
    }

}
     class CounterThread extends Thread {
  	
        private int counter ;
        private int[] array;
        private int end;
       public CounterThread(int c, int[] ar,int e ) {
        counter=c;
        array=ar;
        end=e;
       }
  	
       public void run() {
       
            while (true) {
				if (counter >= end) 
                	break;
            	array[counter]++;
				counter++;		
            } 
		}            	
    }
    

