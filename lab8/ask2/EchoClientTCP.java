import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class EchoClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	private static final String EXIT = "!";

	public static void main(String args[]) throws IOException {

		Socket dataSocket = new Socket(HOST, PORT);
		
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		System.out.println("Connection to " + HOST + " established");

		String inmsg, outmsg;
		ClientProtocolCalc app = new ClientProtocolCalc();
		
		outmsg = app.prepareRequest();
		while(!outmsg.equals(EXIT)) {
			out.println(outmsg);
			inmsg = in.readLine();
			app.processReply(inmsg);
			outmsg = app.prepareRequest();
		}
		out.println(outmsg);

		dataSocket.close();
		System.out.println("Data Socket closed");

	}
}			

