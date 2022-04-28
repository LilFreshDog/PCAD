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
    String str = postiNuovi == 1 ? "Aggiunto 1 posto" : "Aggiunti "+postiNuovi+" posti";
    System.out.println("🟢 " + str + " a " + Nome+" (totale: " + Posti + ")");
  } 
 
  public synchronized Boolean prenotaPosti(String nomeProcesso, Integer postiPrenotati){
    if(Posti < postiPrenotati) return false;
    Posti -= postiPrenotati;
    String str1 = postiPrenotati == 1 ? "1 posto" : postiPrenotati + " posti";
    String str2 = Posti == 1 ? "è disponibile 1 solo posto" : "sono disponibili "+Posti+" posti";
    System.out.println("🔴 "+nomeProcesso+" ha prenotato "+str1+" da "+Nome+". ("+str2+") ");
    return true;
  }
}
