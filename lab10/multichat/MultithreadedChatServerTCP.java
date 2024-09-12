import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedChatServerTCP {
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        ServerSocket connectionSocket = new ServerSocket(PORT);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Connection[] connections = new Connection[10]; // Adjust the size as needed

        int clientCount = 0;
        while (true) {
            System.out.println("Server is waiting for clients on port: " + PORT);
            Socket clientSocket = connectionSocket.accept();
            System.out.println("Received request from " + clientSocket.getInetAddress());

            connections[clientCount] = new Connection(clientSocket);
            executorService.execute(new ServerThread(connections, clientCount));
            clientCount++;
        }
    }
}