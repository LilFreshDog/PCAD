package lab2.eventHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

public class Utente extends Thread {

  private  Eventi eventi;

  public Utente(Eventi eventi){
    this.eventi = eventi;
  }

  public String takeRandomName(){
    int randomNumber = ThreadLocalRandom.current().nextInt(0, eventi.getNumEventi());
    int i = 0;
    System.out.println(randomNumber);
    System.out.println(eventi.getNumEventi() + "fhadfkhask");
    for(Evento ev : eventi.Eventi){
      if( i == randomNumber)return ev.getNome();
      i++;
    }
    return "";
  }

  public void run() {
    try {
      eventi.Prenota(takeRandomName(), ThreadLocalRandom.current().nextInt(1, 20));
      TimeUnit.SECONDS.sleep(500);
      eventi.Prenota(takeRandomName(), ThreadLocalRandom.current().nextInt(1, 20));
      TimeUnit.SECONDS.sleep(500);
      eventi.Prenota(takeRandomName(), ThreadLocalRandom.current().nextInt(1, 20));
      TimeUnit.SECONDS.sleep(500);
      eventi.Prenota(takeRandomName(), ThreadLocalRandom.current().nextInt(1, 20));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
