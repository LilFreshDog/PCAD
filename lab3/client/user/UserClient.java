package lab3.client.user;

import lab3.client.Client;

import java.util.Scanner;

public class UserClient extends Client {

	public UserClient(String hostname, int port) {
		super(hostname, port);
		UserGUI gui = new UserGUI(this);
		gui.display();
	}
	
	public String prenota(String evento, int posti) {
		return sendMessage("prenota|" + evento + "|" + posti);
	}
}
