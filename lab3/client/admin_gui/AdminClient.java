package lab3.client.admin_gui;

import lab3.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminClient extends Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public AdminClient(String hostname, int port) {
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

    public String crea(String nome, Integer posti) {
        return sendMessage("crea|"+nome+"|"+posti);
    }

    public String aggiungi(String nome, Integer posti){
        return sendMessage("aggiungi|"+nome+"|"+posti);
    }
    public String cancella(String nome){
        return sendMessage("cancella|"+nome);
    }
    public String chiudi(String nome){
        return sendMessage("chiudi|"+nome);
    }
    public String lista(){
        return sendMessage("lista");
    }
}
