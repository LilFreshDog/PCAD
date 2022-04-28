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
    System.out.println("🟢 Aggiunti " + postiNuovi + " posti a " + Nome+" (totale: " + Posti + ")");
  } 
 
  public synchronized Boolean prenotaPosti(String nomeProcesso, Integer postiPrenotati){
    if(Posti < postiPrenotati) return false;
    Posti -= postiPrenotati;
    System.out.println("🔴 "+nomeProcesso+" ha prenotato "+postiPrenotati+" posti da "+Nome+". (sono disponibili "+Posti+" posti...) ");
    return true;
  }
}
