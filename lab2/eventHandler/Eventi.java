package lab2.eventHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Eventi {
  
  BlockingQueue<Evento> Eventi;

  public Eventi(){
    this.Eventi = new LinkedBlockingDeque<>();
  }

  public void Crea(String Nome, Integer Posti){
    Evento evento = new Evento(Nome, Posti);
    Eventi.add(evento);
  }

  public void Aggiungi(String Nome, Integer Posti){
    for(Evento ev : Eventi){
      if(ev.getNome() == Nome) ev.aggiungiPosti(Posti);
    }
  }

  //fare controllo se la prenotaPosti ritorna true o false [bisogna gestire il thread che vuole prenotare]
  public void Prenota(String Nome, Integer Posti){
    for(Evento ev : Eventi){
      if(ev.getNome() == Nome)ev.prenotaPosti(Posti);
    }
  }

  public void ListaEventi(){
    for(Evento ev : Eventi){
      System.out.println((ev.getNome().toString()) + " posti disponibili : " + (ev.getPosti().toString()));
    }
  }

  // cancellare l'evente e sbloccare i thread che stanno aspettando di prenotare posti
  public void Chiudi(String Nome){
    
  }
}
