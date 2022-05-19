package lab3;

public class Admin {
	public static void main(String[] args) {
		
		Server server = new Server(80);

		List eventi = server.getEventi();
		
		server.nuovoEvento("Lezione PCAD", 10);
		
		server.aggiungiPosti("Lezione PCAD", 5);
		
		server.chiudiPrenotazioni("Lezione PCAD");

		server.cancellaEvento("Lezione PCAD");
		
	}
}
