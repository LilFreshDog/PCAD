package lab3;

import java.util.concurrent.atomic.AtomicInteger;
import java.net.ProxySelector;
import java.util.concurrent.atomic.AtomicBoolean;

public class Evento {
  private String Nome;
  private AtomicInteger Posti;
  private AtomicBoolean prenotazioniAperte = new AtomicBoolean(true);

  public Evento(String Nome, Integer Posti){
    this.Nome = Nome;
    this.Posti = new AtomicInteger(Posti);
  }

  public String getNome() {
    return Nome;
  }

  public Integer getPosti() {
    return Posti.get();
  }

  public Boolean statoPrenotazioni(){
    return prenotazioniAperte.get();
  }

  public synchronized void chiudiPrenotazioni(){
    prenotazioniAperte.set(false);
    notifyAll();
  }

  public synchronized void aggiungiPosti(Integer postiNuovi){
    Posti.addAndGet(postiNuovi);
    String str = postiNuovi == 1 ? "Aggiunto 1 posto" : "Aggiunti "+postiNuovi+" posti";
    System.out.println("🟢 " + str + " a " +Nome+" (totale: " + Posti.get() + ")");
    notifyAll();
  } 
 
  public synchronized void prenotaPosti(String nomeProcesso, Integer postiPrenotati){
  
    if(postiPrenotati <= 0) return;
    if(Posti.get() < postiPrenotati){
      try {
        wait();
        // le prenotazioni sono ancora aperte o l'evente esiste ancora?
        if (!this.statoPrenotazioni())
          return;
        String str1 = postiPrenotati == 1 ? "1 posto" : postiPrenotati + " posti";
        String str2 = this.getPosti() == 1 ? "è disponibile 1 solo posto"
            : "sono disponibili " + this.getPosti() + " posti";
        System.out.println("🟡 " + nomeProcesso + " vuole " + str1 + " per " + this.Nome + ". (" + str2 + ") ");
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    Posti.addAndGet(-postiPrenotati);
    String str1 = postiPrenotati == 1 ? "1 posto" : postiPrenotati + " posti";
    String str2 = Posti.get() == 1 ? "è disponibile 1 solo posto" : "sono disponibili "+Posti.get()+" posti";
    System.out.println("🔴 "+nomeProcesso+" ha prenotato "+str1+" da "+Nome+". ("+str2+") ");
    return;
  }

}
