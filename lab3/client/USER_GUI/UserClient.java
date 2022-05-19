package lab3.client.user_gui;
import lab3.client.Client;
import java.io.*;
import java.net.*;
import java.util.*;

import lab3.client.Client;

public class UserClient extends Client {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public UserClient(String hostname, int port) {
		super(hostname, port);
	}

	// metodi per Utente
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

	public String getEventi() {
		return sendMessage("getEventi");
	}

	public String prenota(String evento, int posti) {
		return sendMessage("prenota|" + evento + "|" + posti);
	}


}
