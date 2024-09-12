public class DoubleCounterSync {

    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        DoubleCounter doubleCounter = new DoubleCounter(0,0);
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            if (i<(int)numThreads/2) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 100000; j++) {
                        doubleCounter.add1();
                    }
                });
            } else {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 100000; j++) {
                        doubleCounter.add2();
                    }
                });
            }
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

class DoubleCounter {

    private int n1;
    private int n2;
    Object n1_ob = new Object();
    Object n2_ob = new Object();

    public DoubleCounter(int number1,int number2) {
        n1=number1;
        n2=number2;

    }

    public void add1(){
        synchronized (n1_ob) {
            n1++;
        }
    }
    public void add2(){
        synchronized (n2_ob) {
            n2++;
        }
    }

    public int getn1() {
        return n1;
    }

    public int getn2() {
        return n2;
    }
}