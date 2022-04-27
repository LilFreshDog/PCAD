package lab2.eventHandler;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    ArrayList<Utente> utenti = new ArrayList<Utente>();
    ArrayList<String> nomiEventi = new ArrayList<String>();
    ArrayList<Integer> postiEventi = new ArrayList<Integer>();
    Eventi eventi = new Eventi();

    public Test(){
        this.utenti = new ArrayList<Utente>();
        this.nomiEventi = new ArrayList<String>();
        this.postiEventi = new ArrayList<Integer>();
    }

    private initializeList(){
        Integer numeroUtenti = Runtime.getRuntime().availableProcessors();
        System.out.println("Numero utenti: " + numeroUtenti);

        nomiEventi.add("Coachella");
        postiEventi.add(300000);
        nomiEventi.add("Tomorrowland");
        postiEventi.add(500000);
        nomiEventi.add("Unigeparty");
        postiEventi.add(200);
        nomiEventi.add("Conferenza sui thread di java");
        postiEventi.add(50);
        nomiEventi.add("Lezione di PCAD");
        postiEventi.add(100);
    }

    public static void startTesting(String[] args) {
        // Aggiungo gli aventi alla classe eventi
        for (int i = 0; i < nomiEventi.size(); i++){
            eventi.Crea(nomiEventi.get(i), postiEventi.get(i));
        }

        for (int i = 0; i < numeroUtenti; i++){
            Utente utente = new Utente(eventi.Eventi.get( ThreadLocalRandom.current().nextInt(0, nomiEventi.size()) ), ThreadLocalRandom.current().nextInt(1, 20));
            utenti.add(utente);
            utente.start();
        })

        // join threads
        for (int i = 0; i < numeroUtenti; i++){
            try {
                utenti.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
