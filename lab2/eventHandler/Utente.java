package lab2.eventHandler;
import java.util.concurrent.TimeUnit; 
// qua mi segno tutte le donne che scopo
public class Utente extends Thread {

  private  Eventi eventi;

  public Utente(Eventi eventi){
    this.eventi = eventi;
  }

  public String takeRandomName(){
    int randomNumber = (int)Math.floor(Math.random()*eventi.getNumEventi());
    int i = 0;
    for(Evento ev : eventi.Eventi){
      if( i == randomNumber)return ev.getNome();
      i++;
    }
    return "";
  }

  public void run() {
    try {
      TimeUnit.SECONDS.sleep(10);
      eventi.Prenota(takeRandomName(),(int)Math.floor(Math.random()*(5-1+1)+1));
      TimeUnit.SECONDS.sleep(3);
      eventi.Prenota(takeRandomName(),(int)Math.floor(Math.random()*(5-1+1)+1));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
