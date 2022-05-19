package lab3;
import java.io.*;
import java.io.IOException;
import java.net.Socket;

public class WorkerRunnable implements Runnable {
	protected Socket clientSocket = null;
	protected String serverMessage = null;
	protected Eventi eventi;

	public WorkerRunnable(Socket clientSocket, String serverMessage, Eventi eventi) {
		this.clientSocket = clientSocket;
		this.serverMessage = serverMessage;
		this.eventi = eventi;
	}

	public void run() {
		try {
			System.out.println("\n\n🛠 WorkerRunnable has started working");
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
			//implemnto le operazioni del worker
			parseCommand(serverMessage);
			output.println("200 ok SOOS");
			output.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}

	private void parseCommand(String command){
		String[] mycommand = command.split("|");
		if(mycommand[0].equals("crea"))creaEvento(mycommand[1], Integer.parseInt(mycommand[2]));
		if(mycommand[0].equals("prenota"))prenotaEvento(mycommand[1], Integer.parseInt(mycommand[2]));
		if(mycommand[0].equals("cancella"))cancellaEvento(mycommand[1]);
		if(mycommand[0].equals("chiudi"))chiudiPrenotazioni(mycommand[1]);
		if(mycommand[0].equals("aggiungi"))aggiungiPosti(mycommand[1], Integer.parseInt(mycommand[2]));;
		if(mycommand[0].equals("lista"))listaEventi();	
	}

	private void creaEvento(String nome, Integer posti){
    eventi.Crea(nome, posti);
  }
	
	private void prenotaEvento(String nome, Integer posti){
    eventi.Prenota("PIZZA",nome, posti);
  }

	private void aggiungiPosti(String nome, Integer posti){
    eventi.aggiungiPosti(nome, posti);
  }

  private void cancellaEvento(String nome){
    eventi.Cancella(nome);
  }

  private void chiudiPrenotazioni(String nome){
    eventi.Chiudi(nome);
  }

	private void listaEventi(){

  }
}