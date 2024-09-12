
public class ex2 {
	
	 public static void main(String[] args) {
		 
		 int numThreads = 10;
		 
		   Thread[] threads1=new Thread[numThreads];
		   Thread[] threads2 = new Thread[numThreads];
		    
	        /* create and start threads */
	        for (int i = 0; i < numThreads; ++i) {
	         
	            threads1[i] = new FirstThread();
	            threads2[i]= new Thread(new SecondThread());
	            threads1[i].start();
	            threads2[i].start();
	        }
	        
	        /* wait for threads to finish */
	        for (int i = 0; i < numThreads; ++i) {
	            try {
	                threads1[i].join();
	                threads2[i].join();
	            }
	             catch (InterruptedException e) {}
	        }
	        System.out.println("both threads completed");
	    }
	 
}



/*class FirstThread extends Thread {

   thread code 
  public void run() {
  
      System.out.println("Hello from the first thread " );
  } 

}

class SecondThread implements Runnable {

 
  public void run() {
  
      System.out.println("Hello from the second thread " );
  } 


}*/
