package lab2.eventHandler;
import java.util.concurrent.TimeUnit;

public class Admin extends Thread{

  private Eventi eventi;

  public Admin(Eventi eventi){
    this.eventi = eventi;
  }

  public void run() {
    try{
      creaEvento("Coachella", 1);
      creaEvento("Astroworld", 1);
      creaEvento("Conferenza sui thread", 1);
      creaEvento("Lezione PCAD", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      aggiungiPosti("Astroworld", 3);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      cancellaEvento("Lezione PCAD");
      aggiungiPosti("Conferenza sui thread", 10);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      aggiungiPosti("Astroworld", 3);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      TimeUnit.SECONDS.sleep(3);
      aggiungiPosti("Coachella", 1);
      aggiungiPosti("Astroworld", 3);
      TimeUnit.SECONDS.sleep(8);
      chiudiEvento("Coachella");
      chiudiEvento("Astroworld");
      chiudiEvento("Conferenza sui thread");
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

  public void chiudiEvento(String nome){
    // cancellare un evento
    eventi.Chiudi(nome);
    return;
  }
  
  public void cancellaEvento(String nome){
    eventi.Cancella(nome);
    return;
  }
}
