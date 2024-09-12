import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ChatClientProtocol {
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    public String sendMessage() throws IOException {
        System.out.print("Send message, CLOSE for exit: ");
        String theOutput = user.readLine();
        return theOutput;
    }

    public String receiveMessage(String theInput) throws IOException {
        System.out.println("Received message: " + theInput);
        System.out.print("Send a reply, CLOSE for exit: ");
        return theInput;
    }
}