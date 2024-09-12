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
	private static final String EXIT = "!";

	public static void main(String args[]) throws IOException {

		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);

		Socket dataSocket = connectionSocket.accept();
		System.out.println("Received request from " + dataSocket.getInetAddress());

		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		String inmsg, outmsg;
		inmsg = in.readLine();
		ServerProtocolCalc app = new ServerProtocolCalc();
		outmsg = app.processRequest(inmsg);
		
		while(!outmsg.equals(EXIT)) {
			
			if (outmsg.equals(EXIT)) {
				break; 
			}
			out.println(outmsg);
			inmsg = in.readLine();
			outmsg = app.processRequest(inmsg);
		}

		dataSocket.close();
		System.out.println("Data socket closed");	

		
	}
}			

