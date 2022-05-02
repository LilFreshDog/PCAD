package lab2.eventHandler;
import java.util.concurrent.TimeUnit; 
// qua mi segno tutte le donne che scopo
public class Utente extends Thread {

  private  Eventi listaeventi;
  private String nome;

  public Utente(String nome, Eventi eventi){
    this.nome = nome;
    this.listaeventi = eventi;
  }

  public String takeRandomName(){
    int randomNumber = (int)Math.floor(Math.random()*listaeventi.getNumEventi());
    int i = 0;
    for(Evento ev : listaeventi.eventi.values()){
      if( i == randomNumber)return ev.getNome();
      i++;
    }
    return "";
  }

  public void run() {
    try {
      TimeUnit.SECONDS.sleep(10);
      listaeventi.Prenota(nome, takeRandomName(), (int)Math.floor(Math.random()*(5-1+1)+1));
      TimeUnit.SECONDS.sleep(3);
      listaeventi.Prenota(nome, takeRandomName(), (int)Math.floor(Math.random()*(5-1+1)+1));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
