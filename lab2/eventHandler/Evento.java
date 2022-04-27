package lab2.eventHandler;

public class Evento {
  private String Nome;
  private Integer Posti;

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

  public synchronized void aggiungiPosti(Integer postiNuovi){
    Posti += postiNuovi;
  } 
 
  public synchronized Boolean prenotaPosti(Integer postiPrenotati){
    if(Posti < postiPrenotati) return false;
    Posti -= postiPrenotati;
    return true;
  }

}
