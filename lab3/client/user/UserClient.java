package lab3.client.user;

import lab3.client.Client;

public class UserClient extends Client {
	public UserClient(String hostname, int port) {
		super(hostname, port);
	}
	public String prenota(String evento, int posti) {
		return sendMessage("prenota|" + evento + "|" + posti);
	}

	public static void main(String[] args) {
		UserClient user = new UserClient("localhost", 9000);
		System.out.println(user.lista());
		System.out.println(user.prenota("SAS", 100));
		user.close();
	}
}
