package lab3.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
	protected Socket socket;
	protected String hostname = "localhost";
	protected int port = 8123;
	protected PrintWriter out;
	protected BufferedReader in;

	public Client(String hostname, int port){
		try {
			socket = new Socket(hostname, port);
		} catch (Exception e) {
			System.out.println("Error connecting to "+hostname);
			System.exit(1);
		}

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("Error creating streams");
			System.exit(1);
		}
	}

	protected String sendMessage(String msg) {
	out.println(msg);
	try {
		return in.readLine();
	} catch (Exception e) {
		System.out.println("Error reading message");
		System.exit(1);
	}
	return null;
	}

	protected void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error closing connection");
			System.exit(1);
		}
	}

	protected Object[] lista() {
		String response = sendMessage("lista");
		/*
		  string is formatted using | and &
		  example: "crea|Lezione PCAD|10"
		  exaple: "lista"
		 */
		ArrayList<String[]> eventi = new ArrayList<>();

		String[] commands = response.split("&");
		for (String command : commands) {
			String[] listEntry = command.split("|");
			eventi.add(listEntry);
		}
		return eventi.toArray();
	}

}