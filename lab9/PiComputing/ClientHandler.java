package ask3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private static final ConcurrentMap<Integer, Double> piCache = new ConcurrentHashMap<>();
    private static final Lock lock = new ReentrantLock();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            int n = in.readInt();
            if (n == 1) {
                out.writeDouble(0.0);
                out.flush();
                clientSocket.close();
                return;
            }

            double piValue;
            lock.lock();  // Acquire the lock
            try {
                //check if n is already in
                if (piCache.containsKey(n)) {
                    piValue = piCache.get(n);
                } else {
                    piValue = PiCalc.calculatePi(n);
                    piCache.put(n, piValue);
                }
            } finally {
                lock.unlock();  // Release the lock
            }

            out.writeDouble(piValue);
            out.flush();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
