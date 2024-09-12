package ask1Serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private ServerProtocol serverProtocol;

    public ClientHandler(Socket socket, ServerProtocol protocol) {
        this.clientSocket = socket;
        this.serverProtocol = protocol;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());

            Request request;
            Reply reply;

            while (true) {
                request = (Request) is.readObject();
                if (request == null) {
                    break;
                }
                reply = serverProtocol.processRequest(request);
                os.writeObject(reply);
                os.flush();
            }

            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}