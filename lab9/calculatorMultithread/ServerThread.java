import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread extends Thread {
    private Socket clientSocket;

    public ServerThread(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Received request from " + clientSocket.getInetAddress());

            // Get input and output streams from the client socket
            InputStream is = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            OutputStream os = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);

            String inmsg, outmsg;
            ServerProtocolCalc app = new ServerProtocolCalc();

            inmsg = in.readLine();
            outmsg = app.processRequest(inmsg);

            while (!outmsg.equals("CLOSE")) {
                out.println(outmsg);
                inmsg = in.readLine();
                outmsg = app.processRequest(inmsg);
            }

            clientSocket.close();
            System.out.println("Data socket closed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}