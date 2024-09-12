import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class SendThread implements Runnable {
    private Socket dataSocket;
    private PrintWriter out;

    public SendThread(Socket socket) throws IOException {
        this.dataSocket = socket;
        out = new PrintWriter(dataSocket.getOutputStream(), true);
    }

    public void run() {
        try {
            String outmsg;
            ChatClientProtocol app = new ChatClientProtocol();
            outmsg = app.sendMessage();
            while (!outmsg.equals("CLOSE")) {
                out.println(outmsg);
                outmsg = app.sendMessage();
            }
            out.println(outmsg);
            dataSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
