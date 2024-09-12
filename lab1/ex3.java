
public class ex3 {
	
    public static void main(String[] args) {

  
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];


        for (int i = 0; i < numThreads; ++i) {
            System.out.println("Starting thread " + i);
            threads[i] = new Thread(new MyRunnable(i));
            threads[i].start();
            
            //to join einai mesa sto for loop gia na ektelestoun ta threads ara na emfanizontai ta noumera me seira 
            try {
                threads[i].join();
            }
            catch (InterruptedException e) {
                System.err.println("this should not happen");
            }
        }



        System.out.println("In main: threads all done");
    }
}
class MyRunnable implements Runnable {

    /* instance variables */
    private int num;


    /* constructor */
    public MyRunnable(int num) {
    	this.num=num;
    }

    /* thread code */
    public void run() {
        for(int i=1;i<=20;i++) {
        	int n=num+1;
        	int count=i*n;
        	System.out.println(i+"*"+n+"="+count);
        }
    } 

}