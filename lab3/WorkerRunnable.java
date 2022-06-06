package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerRunnable implements Runnable {
	protected Socket clientSocket;
	protected String serverMessage;
	protected Eventi eventi;

	public WorkerRunnable(Socket clientSocket, String serverMessage, Eventi eventi) {
		this.clientSocket = clientSocket;
		this.serverMessage = serverMessage;
		this.eventi = eventi;
	}

	public void run() {
		try {
			System.out.println("\n🛠 WorkerRunnable has started working");
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
			//implemnto le operazioni del worker
			try {
				while (parseCommand(input.readLine(), output)) {}
			} catch (Exception e) {
				System.out.println("⏹ End of parsing");
			}
			output.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean parseCommand(String command, PrintWriter output) {
		System.out.println(command);
		String[] mycommand = command.split("\\|");
		if (mycommand[0].equals("crea")) {
			creaEvento(mycommand[1], Integer.parseInt(mycommand[2]), output);
			return true;
		}
		if (mycommand[0].equals("prenota")) {
			prenotaEvento(mycommand[1], Integer.parseInt(mycommand[2]), output);
			return true;
		}
		if (mycommand[0].equals("cancella")) {
			cancellaEvento(mycommand[1], output);
			return true;
		}
		if (mycommand[0].equals("chiudi")) {
			chiudiPrenotazioni(mycommand[1], output);
			return true;
		}
		if (mycommand[0].equals("aggiungi")) {
			aggiungiPosti(mycommand[1], Integer.parseInt(mycommand[2]), output);
			return true;
		}
		if (mycommand[0].equals("lista")) {
			listaEventi(output);
			return true;
		}
		return false;
	}

	private void creaEvento(String nome, Integer posti, PrintWriter output) {
		eventi.nuovoEvento(nome, posti);
		output.println("OK|" + nome + "|" + posti);
	}
	
	private void prenotaEvento(String nome, Integer posti, PrintWriter output){
    eventi.Prenota("PIZZA",nome, posti);
		output.println("200 ok");
  }

	private void aggiungiPosti(String nome, Integer posti, PrintWriter output){
    eventi.aggiungiPosti(nome, posti);
		output.println("200 ok");
  }

  private void cancellaEvento(String nome, PrintWriter output){
    eventi.Cancella(nome);
		output.println("200 ok");
  }

  private void chiudiPrenotazioni(String nome, PrintWriter output){
    eventi.Chiudi(nome);
		output.println("200 ok");
  }

	private void listaEventi(PrintWriter output) {
		String toReturn = "";

		for (String key : eventi.eventi.keySet()) {
			toReturn = toReturn + key + "|" + eventi.eventi.get(key).getPosti() + "&";
		}

		if (toReturn.length() == 0) {
			System.out.println("NO EVENTS FOUND");
			output.println("NO EVENTS");
		} else {
			System.out.println("SENT EVENTS: " + toReturn);
			toReturn = toReturn.substring(0, toReturn.length() - 1);
			output.println(toReturn);
		}
	}
}