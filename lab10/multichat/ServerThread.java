import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ServerThread implements Runnable {
    private static Connection[] connections;
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int id;

    public ServerThread(Connection[] connections, int id) {
        this.connections = connections;
        this.id = id;
    }

    public void run() {
        try {
            String message;
            while ((message = connections[id].inputStream.readLine()) != null) {
                broadcastMessage(id, message);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                connections[id].socket.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void broadcastMessage(int senderId, String message) {
        lock.readLock().lock();
        try {
            for (int i = 0; i < connections.length; i++) {
                if (i != senderId && connections[i] != null) {
                    connections[i].outputStream.println(connections[senderId].socket.getInetAddress().getHostAddress() + ": " + message);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }
}