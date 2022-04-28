package lab2.eventHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Eventi {
  
  public BlockingQueue<Evento> Eventi;

  public Eventi(){
    this.Eventi = new LinkedBlockingDeque<>();
  }

  public int getNumEventi(){
    return Eventi.size();
  }

  public void Crea(String Nome, Integer Posti){
    Evento evento = new Evento(Nome, Posti);
    Eventi.add(evento);
    System.out.println("🆕 Creato " + Nome + " con " + Posti + " posti");
  }

  public synchronized void Aggiungi(String Nome, Integer Posti){
    for(Evento ev : Eventi){
      if(ev.getNome() == Nome){
        ev.aggiungiPosti(Posti);
        notifyAll();
      }
    }
  }

  //fare controllo se la prenotaPosti ritorna true o false [bisogna gestire il thread che vuole prenotare]
  public synchronized void Prenota(String nomeProcesso, String nomeEvento, Integer posti){
    for(Evento ev : Eventi){
      if(ev.getNome() == nomeEvento && ev.statoPrenotazioni()){
        while(!ev.prenotaPosti(nomeProcesso, posti)){
          try { 
            wait();
            //esiste ancora l'evento cercato?
            if(!ev.statoPrenotazioni())return;
            System.out.println("🟡 "+nomeProcesso+" vuole "+posti+" per "+nomeEvento+". (sono disponibili "+ev.getPosti()+" posti...) ");
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
          }
        }
      }
    }
  }

  public Boolean esisteEvento(String Nome){
    for(Evento ev : Eventi)if(ev.getNome() == Nome)return true;
    return false;
  }

  public void ListaEventi(){
    System.out.println("\n\n----------------- EVENTI -------------------------");
    for(Evento ev : Eventi){
      System.out.println("📅 " + ev.getNome() + " posti disponibili: " + ev.getPosti());
    }
    System.out.println("--------------------------------------------------\n\n");
    
  }

  // cancellare l'evente e sbloccare i thread che stanno aspettando di prenotare posti
  public synchronized void Chiudi(String Nome){
    for(Evento ev : Eventi){
      if(ev.getNome() == Nome){
        ev.chiudiPrenotazioni();
        System.out.println("🚫 Prenotazioni chiuse per " + Nome);
        notifyAll();
      }
    }
  }
}
