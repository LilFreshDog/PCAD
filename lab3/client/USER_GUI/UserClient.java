package lab3.client.user_gui;
import java.io.*;
import java.net.*;
import java.util.*;

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
		try {
			String resp = in.readLine();
			return resp;
		} catch (Exception e) {
			System.out.println("Error reading message");
			System.exit(1);
		}
		return null;
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Error closing connection");
			System.exit(1);
		}
	}
}
