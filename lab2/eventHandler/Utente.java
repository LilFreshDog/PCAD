package lab2.eventHandler;

public class Utente extends Thread {
  private Evento evento;
  private Integer postiDaPrenotare;

  public Utente(Evento evento, Integer postiDaPrenotare){
    this.evento = evento;
    this.postiDaPrenotare = postiDaPrenotare;
  }

  public void run() {
    prenotaPosti(evento, postiPrenotati)
  }

  // method for the user to reserve a seat by calling the reserve method of the event
  public Boolean prenotaPosti() {
    System.out.println("Provo a prenotare " + postiDaPrenotare + " posti per l'evento " + evento.getNome());
    return evento.prenotaPosti(postiDaPrenotare);
  }

}
