package lab3;

import java.util.concurrent.ConcurrentHashMap;

public class Eventi {
  
  public ConcurrentHashMap<String,Evento> eventi;

  public Eventi(){
    this.eventi = new ConcurrentHashMap<String,Evento>();
  }

  public int getNumEventi(){
    return eventi.size();
  }

  public void nuovoEvento(String nome, Integer posti){
    //TODO: CONTROLLARE SE ESISTE GIA EVENTO
    Evento evento = new Evento(nome, posti);
    eventi.put(nome,evento);
    String str = posti == 1 ? "1 posto" : posti + " posti";
    System.out.println("🆕 Creato " + nome + " con " + str);
  }

  public void aggiungiPosti(String nome, Integer posti){
    Evento evento = eventi.get(nome);
    evento.aggiungiPosti(posti);
  }

  //fare controllo se la prenotaPosti ritorna true o false [bisogna gestire il thread che vuole prenotare]
  public void Prenota(String nomeProcesso, String nomeEvento, Integer posti){
    Evento ev = eventi.get(nomeEvento);
    if(ev == null) return;
    if(ev.statoPrenotazioni() || !eventi.containsKey(nomeEvento)){
      ev.prenotaPosti(nomeProcesso, posti);
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
