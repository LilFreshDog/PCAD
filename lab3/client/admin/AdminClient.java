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
    public String lista(){
        return sendMessage("lista");
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
    public static void main(String[] args) {
        AdminClient admin = new AdminClient("localhost", 9000);
        System.out.println(admin.lista());
        System.out.println(admin.crea("SAS", 100));
        System.out.println(admin.aggiungi("SAS", 50));
        System.out.println(admin.chiudi("SAS"));
        System.out.println(admin.cancella("SAS"));
        admin.close();
    }
}
