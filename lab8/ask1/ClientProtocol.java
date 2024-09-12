import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientProtocol {
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    public String prepareRequest() throws IOException {
        System.out.print("Enter the option you want and the message: ");
        String request = user.readLine();
        return request;
    }

    public void processReply(String theInput) throws IOException {
        System.out.println("Message received from server: " + theInput);
    }
}
