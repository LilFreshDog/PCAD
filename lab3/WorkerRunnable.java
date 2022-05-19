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
			parseCommand(input.readLine(), output);
			output.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}

	private void parseCommand(String command, PrintWriter output){
		String[] mycommand = command.split("|");
		if(mycommand[0].equals("crea"))creaEvento(mycommand[1], Integer.parseInt(mycommand[2]),output );
		if(mycommand[0].equals("prenota"))prenotaEvento(mycommand[1], Integer.parseInt(mycommand[2]),output);
		if(mycommand[0].equals("cancella"))cancellaEvento(mycommand[1], output);
		if(mycommand[0].equals("chiudi"))chiudiPrenotazioni(mycommand[1], output);
		if(mycommand[0].equals("aggiungi"))aggiungiPosti(mycommand[1], Integer.parseInt(mycommand[2]), output);;
		if(mycommand[0].equals("lista"))listaEventi(output);	
	}

	private void creaEvento(String nome, Integer posti, PrintWriter output){
    eventi.nuovoEvento(nome, posti);
		output.println("200 ok SOOS");
  }
	
	private void prenotaEvento(String nome, Integer posti, PrintWriter output){
    eventi.Prenota("PIZZA",nome, posti);
		output.println("200 ok SOOS");
  }

	private void aggiungiPosti(String nome, Integer posti, PrintWriter output){
    eventi.aggiungiPosti(nome, posti);
		output.println("200 ok SOOS");
  }

  private void cancellaEvento(String nome, PrintWriter output){
    eventi.Cancella(nome);
		output.println("200 ok SOOS");
  }

  private void chiudiPrenotazioni(String nome, PrintWriter output){
    eventi.Chiudi(nome);
		output.println("200 ok SOOS");
  }

	private void listaEventi(PrintWriter output){
		String toReturn = "";
		for (String key : eventi.eventi.keySet()) {
     	toReturn = toReturn + key + "&" + eventi.eventi.get(key).getPosti() + "|";
    }

		toReturn = toReturn.substring(0, toReturn.length()-1);
		output.println(toReturn);
  }
}