package ask1Serializable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerTCP {
    private static final int PORT = 1234;
    private static final String EXIT = "CLOSE";

    public static void main(String[] args) throws IOException {
        
        ServerSocket connectionSocket = new ServerSocket(PORT);
        System.out.println("Server is listening to port: " + PORT);
        System.out.println("Available operations:");
        System.out.println("1. Lowercase");
        System.out.println("2. Uppercase");
        System.out.println("3. Plain encoding");
        System.out.println("4. Decoding");
        System.out.println(" Enter: '1 <text>' for lowercase, '2 <text>' for uppercase, '3 <offset> <text>' for plain-encoding, or '4 <offset> <text>' for decoding.");

        ServerProtocol app = new ServerProtocol();

        while (true) {
            Socket dataSocket = connectionSocket.accept();
            System.out.println("Received request from " + dataSocket.getInetAddress());

            ClientHandler clientHandler = new ClientHandler(dataSocket, app);
            clientHandler.start();
        }

    }
}