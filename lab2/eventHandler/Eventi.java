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
    System.out.println("Creato " + Nome + " con " + Posti + " posti");
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
  public synchronized void Prenota(String Nome, Integer Posti){
    for(Evento ev : Eventi){
      if(ev.getNome() == Nome){
        while(!ev.prenotaPosti(Posti)){
          try { 
            wait();
            //esiste ancora l'evento cercato?
            if(!esisteEvento(Nome))return;
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            System.out.println("Waiting for some seats to free...");
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
    for(Evento ev : Eventi){
      System.out.println(ev.getNome() + " posti disponibili : " + ev.getPosti());
    }
  }

  // cancellare l'evente e sbloccare i thread che stanno aspettando di prenotare posti
  public synchronized void Chiudi(String Nome){
    for(Evento ev : Eventi){
      if(ev.getNome() == Nome){
        Eventi.remove(ev);
        System.out.println("Cancellato " + Nome);
        notifyAll();
      }
    }
  }
}
