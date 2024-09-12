import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientProtocolCalc {
    private BufferedReader userInput;
    private int a, b;
    double r;
    private char op;
    private int errCode;

    public ClientProtocolCalc() {
        userInput = new BufferedReader(new InputStreamReader(System.in));
    }

    public String prepareRequest() throws IOException {
        boolean done = false;
        String data = null;
        while (!done) {
            promptUser();
            data = readUserInput();
            if (data.equals("!")) {
                return data; 
            }
            done = checkData(data);
            if (!done){
                System.out.println("Invalid input. Please enter in the format: operator first_number second_number");
            }
        }
        return buildRequestMessage();
    }

    public void processReply(String reply) {
        String[] parts = getReplyData(reply);
        if (parts[0].equals("R")) {
            r = Double.parseDouble(parts[1]); 
            displayDataToUser();
        } else if (parts[0].equals("E")) {
            errCode = Integer.parseInt(parts[1]);
            displayErrorToUser(errCode);
        }
    }

    private void promptUser() {
        System.out.print("Enter operator (+, -, *, /), first number, and second number (separated by spaces): ");
    }

    private String readUserInput() throws IOException {
        return userInput.readLine();
    }

    private boolean checkData(String data) {
        String[] parts = data.split(" ");
        if (parts.length != 3) {
            return false;
        }
        
        op = parts[0].charAt(0);
        try {
            a = Integer.parseInt(parts[1]);
            b = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }
      
         return isValidOperator(op);
    }

    private boolean isValidOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    private String buildRequestMessage() {
        return op + " " + a + " " + b;
    }

    private String[] getReplyData(String reply) {
        return reply.split(" ");
    }

    private void displayDataToUser() {
        System.out.println("Result: " + r);
    }

    private void displayErrorToUser(int errCode) {
        System.out.println("Error code: " + errCode);
        // Add error message handling based on the error code
    }
}