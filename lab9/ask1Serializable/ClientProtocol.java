package ask1Serializable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientProtocol {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    private BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientProtocol() throws IOException {
        socket = new Socket(HOST, PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void startClient() throws IOException, ClassNotFoundException {
        String request;
        Reply reply;

        while (true) {
            System.out.print("Enter the option you want and the message: ");
            request = user.readLine();
            if (request.equals("CLOSE")) {
                break;
            }

            String[] parts = request.split(" ", 3);
            int operation = Integer.parseInt(parts[0]);
            String text = parts.length > 1 ? parts[1] : "";
            int offset = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;

            out.writeObject(new Request(operation, text, offset));
            out.flush();

            reply = (Reply) in.readObject();
            System.out.println("Message received from server: " + reply.getResult());
        }

        out.close();
        in.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientProtocol client = new ClientProtocol();
        client.startClient();
    }
}
