package lab2.eventHandler;

import java.util.concurrent.ConcurrentHashMap;

public class Eventi {
  
  public ConcurrentHashMap<String,Evento> eventi;

  public Eventi(){
    this.eventi = new ConcurrentHashMap<String,Evento>();
  }

  public int getNumEventi(){
    return eventi.size();
  }

  public void Crea(String Nome, Integer Posti){
    Evento evento = new Evento(Nome, Posti);
    eventi.put(Nome,evento);
    String str = Posti == 1 ? "1 posto" : Posti + " posti";
    System.out.println("🆕 Creato " + Nome + " con " + str);
  }

  public synchronized void Aggiungi(String Nome, Integer Posti){
    Evento ev = eventi.get(Nome);
    ev.aggiungiPosti(Posti);
    notifyAll();    
  }

  //fare controllo se la prenotaPosti ritorna true o false [bisogna gestire il thread che vuole prenotare]
  public synchronized void Prenota(String nomeProcesso, String nomeEvento, Integer posti){
    Evento ev = eventi.get(nomeEvento);
    if(ev.statoPrenotazioni()){
      while(!ev.prenotaPosti(nomeProcesso, posti)){
        try { 
          wait();
          //le prenotazioni sono ancora aperte o l'evente esiste ancora?
          if(!ev.statoPrenotazioni() || !eventi.containsKey(nomeEvento))return;
          String str1 = posti==1 ? "1 posto" : posti+" posti";
          String str2 = ev.getPosti()==1 ? "è disponibile 1 solo posto" : "sono disponibili " + ev.getPosti() + " posti";
          System.out.println("🟡 "+nomeProcesso+" vuole "+str1+" per "+nomeEvento+". ("+str2+") ");
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); 
        }
      }
    }
  
  }

  public void ListaEventi(){
    System.out.println("\n----------------- EVENTI -------------------------");
    for(Evento ev : eventi.values()){
      System.out.println("📅 " + ev.getNome() + " posti disponibili: " + ev.getPosti());
    }
    System.out.println("--------------------------------------------------\n");
  }

  public synchronized void Chiudi(String Nome){
    Evento ev = eventi.get(Nome);
    ev.chiudiPrenotazioni();
    System.out.println("🚫 Prenotazioni chiuse per " + Nome);
    notifyAll();
  }

  public synchronized void Cancella(String Nome){
    eventi.remove(Nome);
    System.out.println("💀 Cancellato " + Nome);
    notifyAll();
  }

}
