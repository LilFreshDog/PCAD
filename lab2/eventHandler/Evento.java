package lab2.eventHandler;

public class Evento {
  private String Nome;
  private Integer Posti;
  private Boolean prenotazioniAperte = true;

  public Evento(String Nome, Integer Posti){
    this.Nome = Nome;
    this.Posti = Posti;
  }

  public String getNome() {
    return Nome;
  }

  public Integer getPosti() {
    return Posti;
  }

  public Boolean statoPrenotazioni(){
    return prenotazioniAperte;
  }

  public void chiudiPrenotazioni(){
    prenotazioniAperte = false;
  }

  public synchronized void aggiungiPosti(Integer postiNuovi){
    Posti += postiNuovi;
    System.out.println("\n\n🟢 Aggiunti " + postiNuovi + " posti a " + Nome);
  } 
 
  public synchronized Boolean prenotaPosti(Integer postiPrenotati){
    if(Posti < postiPrenotati) return false;
    Posti -= postiPrenotati;
    System.out.println("\n\n🔴 Prenotati " + postiPrenotati + " posti da " + Nome);
    return true;
  }
}
