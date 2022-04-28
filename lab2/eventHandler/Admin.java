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
      creaEvento("Coachella", 1);
      creaEvento("Astroworld", 20);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(4);
      cancellaEvento("Coachella");

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
