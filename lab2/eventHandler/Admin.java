package lab2.eventHandler;

public class Admin extends Thread{

  private BlockingQueue eventi;

  public Admin(BlockingQueue eventi){
    this.eventi = eventi;
  }

  public void creaEvento(String nome, Integer posti){
    // creare un nuovo evento
    eventi.Crea(nome, posti);
  }

  public void aggiungiPosti(String nome, Integer posti){
    // aggiungere posti a un evento
    eventi.Aggiungi(nome, posti);
  }

  public void cancellaEvento(String nome){
    // cancellare un evento
    eventi.Chiudi(nome);
  }
}
