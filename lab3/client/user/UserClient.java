package lab3.client.user;

import lab3.client.Client;
import java.util.*;

public class UserClient extends Client {

	public UserClient(String hostname, int port) {
		super(hostname, port);
		UserGUI gui = new UserGUI(this);
		gui.display();
	}
	
	public String prenota(String evento, int posti) {
		return sendMessage("prenota|" + evento + "|" + posti);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserisci hostname: ");
		String hostname = input.nextLine();
		System.out.println("Inserisci port: ");
		int port = input.nextInt();
		UserClient user = new UserClient(hostname, port);
		input.close();
		System.out.println(user.prenota("SAS", 100));
		user.close();
	}
}
