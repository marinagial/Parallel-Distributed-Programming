//creates 2 threads from 2 different classes

public class ex1 {
	
	 public static void main(String[] args) {
		
		   Thread anotherThread=new Thread();
		 
	        FirstThread aThread = new FirstThread();
	        anotherThread=new Thread(new SecondThread());
	        aThread.start();  
	        anotherThread.start();

	        try {
	            aThread.join(); 
	            anotherThread.join();
	        }
	        catch (InterruptedException e) {}

	        System.out.println("both threads completed");
	    }
	 
	 }



class FirstThread extends Thread {

    /* thread code */
    public void run() {
    
        System.out.println("Hello from the first thread " );
    } 

}

class SecondThread implements Runnable {

    /* thread code */
    public void run() {
    
        System.out.println("Hello from the second thread " );
    } 

}

