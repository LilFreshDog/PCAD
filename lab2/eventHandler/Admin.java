package lab2.eventHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Admin extends Thread{

  private BlockingQueue<Evento> eventi;

  public Admin(BlockingQueue<Evento> eventi){
    this.eventi = eventi;
  }

  public void creaEvento(String nome, Integer posti){
    // creare un nuovo evento
    eventi.Crea(nome, posti);
    return;
  }

  public void aggiungiPosti(String nome, Integer posti){
    // aggiungere posti a un evento
    eventi.Aggiungi(nome, posti);
    return;
  }

  public void cancellaEvento(String nome){
    // cancellare un evento
    eventi.Chiudi(nome);
    return;
  }
}
