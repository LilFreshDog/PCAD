package lab2.eventHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Admin extends Thread{

  private Eventi eventi;

  public Admin(Eventi eventi){
    this.eventi = eventi;
  }

  public void run() {
    try{
      creaEvento("Coachella", 50);
      TimeUnit.SECONDS.sleep(3);
      creaEvento("Tomorrowland", 10);
      TimeUnit.SECONDS.sleep(2);
      creaEvento("UnigeParty", 20);
      aggiungiPosti("Coachella", 50);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Tomorrowland", 50);
      TimeUnit.SECONDS.sleep(3);
      cancellaEvento("Coachella");
      creaEvento("Conferenza sui Thread di Java", 50);
      TimeUnit.SECONDS.sleep(3);
      creaEvento("Lezione di PCAD", 100);
      cancellaEvento("Tomorrowland");
      TimeUnit.SECONDS.sleep(3);
      cancellaEvento("UnigeParty");
      cancellaEvento("Conferenza sui Thread di Java");
      cancellaEvento("Lezione di PCAD");
    } catch (Exception e) {
      e.printStackTrace();
    }
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
