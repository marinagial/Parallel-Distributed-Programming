
/* Vector Addition a = b + c                       */

class VectorAdd
{
  public static void main(String args[])
  {
    int size = 1000;
    int numThreads = Runtime.getRuntime().availableProcessors();
    
    double[] a = new double[size];
    double[] b = new double[size];
    double[] c = new double[size];
    for(int i = 0; i < size; i++) {
        a[i] = 0.0;
		b[i] = 1.0;
        c[i] = 0.5;
    }
 // get current time
 	long start = System.currentTimeMillis();

 	//  Static block partitioning
 	int block = size / numThreads;
 	int from = 0;
 	int to = 0;
        
 		// create threads
 		VectorThread threads[] = new VectorThread[numThreads];
 		
 		// thread execution   
 		for(int i = 0; i < numThreads; i++) 
 		{
 			from = i * block;
 			to = i * block + block;
 			if (i == (numThreads - 1)) to = size;
 			threads[i] = new VectorThread(a,b,c,from,to);
 			threads[i].start();
 		}

 		// wait for threads to terminate            
 		for(int i = 0; i < numThreads; i++) {
 			try {
 				threads[i].join();
 			} catch (InterruptedException e) {}
 		}



    // print
    for(int i = 0; i < size; i++) 
        System.out.println(a[i]); 
    
 // get current time and calculate elapsed time
 	long elapsedTimeMillis = System.currentTimeMillis()-start;
 	System.out.println("time in ms = "+ elapsedTimeMillis);
  }
}
class VectorThread extends Thread
{
	private double [] table1;
	private double [] table2;
	private double [] table3;
	private int myfrom;
	private int myto;

	// constructor
	public VectorThread(double [] array1, double [] array2,double [] array3,int from, int to)
	{
		table1 = array1;
		table2 = array2;
		table3 = array3;
		myfrom = from;
		myto = to;
	}

	// thread code
	public void run()
	{
		for(int i=myfrom; i<myto; i++) 
			table1[i] = table2[i]+table3[i];
	}
}

