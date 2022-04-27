package lab2.eventHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

public class Utente extends Thread {

  private BlockingQueue<Evento> eventi;

  public Utente(BlockingQueue<Evento> eventi){
    this.eventi = eventi;
  }

  public void run() {
    try {
      eventi.Prenota(eventi.get(ThreadLocalRandom.current().nextInt(0, eventi.size())), ThreadLocalRandom.current().nextInt(1, 20));
      TimeUnit.SECONDS.sleep(500);
      eventi.Prenota(eventi.get(ThreadLocalRandom.current().nextInt(0, eventi.size())), ThreadLocalRandom.current().nextInt(1, 20));
      TimeUnit.SECONDS.sleep(500);
      eventi.Prenota(eventi.get(ThreadLocalRandom.current().nextInt(0, eventi.size())), ThreadLocalRandom.current().nextInt(1, 20));
      TimeUnit.SECONDS.sleep(500);
      eventi.Prenota(eventi.get(ThreadLocalRandom.current().nextInt(0, eventi.size())), ThreadLocalRandom.current().nextInt(1, 20));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
