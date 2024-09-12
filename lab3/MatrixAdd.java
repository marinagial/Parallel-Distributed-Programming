

/* Matrix Addition A = B + C   */                   

class MatrixAdd
{
  public static void main(String args[])
  {
    int size = 2000;
    int numThreads = Runtime.getRuntime().availableProcessors();
    
    double[][] a = new double[size][size];
    double[][] b = new double[size][size];
    double[][] c = new double[size][size];
    for(int i = 0; i < size; i++) 
      for(int j = 0; j < size; j++) { 
        a[i][j] = 0.1;
		b[i][j] = 0.3;
        c[i][j] = 0.5;
      }   
      
	// get current time
	long start = System.currentTimeMillis();

	//  Static block partitioning
	int block = size / numThreads;
	int from = 0;
	int to = 0;
       
		// create threads
		MtrxGroupThread threads[] = new MtrxGroupThread[numThreads];
		
		// thread execution   
		for(int i = 0; i < numThreads; i++) 
		{
			from = i * block;
			to = i * block + block;
			if (i == (numThreads - 1)) to = size;
			threads[i] = new MtrxGroupThread(a,b,c ,from,to,size);
			threads[i].start();
		}

		// wait for threads to terminate            
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}





        

    // for debugging 
    for(int i = 0; i < size; i++) { 
      for(int j = 0; j < size; j++) 
        System.out.print(a[i][j]+" "); 
      System.out.println();
    } 
    
	// get current time and calculate elapsed time
	long elapsedTimeMillis = System.currentTimeMillis()-start;
	System.out.println("time in ms = "+ elapsedTimeMillis);
  }
}
 
class MtrxGroupThread extends Thread
{
	private double [][] table1;
	private double [][] table2;
	private double [][] table3;
	private int megethos;
	private int myfrom;
	private int myto;

	// constructor
	public MtrxGroupThread(double [][] a,double [][] b,double [][] c, int from, int to,int size)
	{
		table1=a;
		table2=b;
		table3=c;
		myfrom = from;
		myto = to;
		megethos=size;
	}

	// thread code
	public void run()
	{
		for(int i=myfrom; i<myto; i++) 
			for(int j=0 ;  j<megethos ; j++) {
				table1[i][j]=table2[i][j]+table3[i][j];
			}
	}
}
