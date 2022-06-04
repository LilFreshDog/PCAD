package lab3.client.admin;

import lab3.client.Client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminClient extends Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public AdminClient(String hostname, int port) {
        super(hostname, port);
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

}
