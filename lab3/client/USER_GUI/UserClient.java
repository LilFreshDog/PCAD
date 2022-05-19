package lab3.client.user_gui;
import java.io.*;
import java.net.*;
import java.util.*;

/**
* 1. initiate connection - socket
* 2. send data - es richiedo gli eventi
* 3. receive data - la lista degli eventi
* 4. close connection
*/


public class UserClient {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
		} catch (Exception e) {
			System.out.println("Error connecting to server");
			System.exit(1);
		}

		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {
			System.out.println("Error creating streams");
			System.exit(1);
		}
	}

	public String sendMessage(String msg) {
		out.println(msg);
		String resp = in.readLine();
		return resp;
	}

	public void stopConnection() {
		in.close();
		out.close();
		clientSocket.close();
	}
}
