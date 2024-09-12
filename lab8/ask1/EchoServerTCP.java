import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerTCP {
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String args[]) throws IOException {

		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);
		System.out.println("Available operations:");
		System.out.println("1. Lowercase");
		System.out.println("2. Uppercase");
		System.out.println("3. Plain encoding");
		System.out.println("4. Decoding");
		System.out.println(" Enter: '1 <text>' for lowercase, '2 <text>' for uppercase, '3 <offset> <text>' for plain-encoding, or '4 <offset> <text>' for decoding.");

		Socket dataSocket = connectionSocket.accept();
		System.out.println("Received request from " + dataSocket.getInetAddress());

		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		String inmsg, outmsg;
		inmsg = in.readLine();
		ServerProtocol app = new ServerProtocol();
		outmsg = app.processRequest(inmsg);
		while(!outmsg.equals(EXIT)) {
			out.println(outmsg);
			inmsg = in.readLine();
			outmsg = app.processRequest(inmsg);
		}

		dataSocket.close();
		System.out.println("Data socket closed");	
		

		
	}
}			

