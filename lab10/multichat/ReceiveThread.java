import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ReceiveThread implements Runnable {
    private Socket dataSocket;
    private BufferedReader in;

    public ReceiveThread(Socket socket) throws IOException {
        this.dataSocket = socket;
        in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
    }

    public void run() {
        try {
            String inmsg;
            ChatClientProtocol app = new ChatClientProtocol();
            inmsg = app.receiveMessage(in.readLine());
            while (inmsg != null) {
                inmsg = app.receiveMessage(in.readLine());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                dataSocket.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
