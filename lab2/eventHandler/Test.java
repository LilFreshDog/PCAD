package lab2.eventHandler;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    Integer numeroUtenti = 10;
    listaEventi = new ArrayList<String>();
    postiEventi = new ArrayList<Integer>();
    listaEventi.add("Coachella");
    postiEventi.add(300000);
    listaEventi.add("Tomorrowland");
    postiEventi.add(500000);
    listaEventi.add("Unigeparty");
    postiEventi.add(200);
    listaEventi.add("Conferenza sui thread di java");
    postiEventi.add(50);
    listaEventi.add("Lezione di PCAD");
    postiEventi.add(100);


    Eventi eventi = new Eventi();
    // Aggiungo gli aventi alla classe eventi
    for (int i = 0; i < listaEventi.size(); i++){
        eventi.Crea(listaEventi.get(i), postiEventi.get(i));
    }

    for (int i = 0; i < numeroUtenti; i++){
        Utente utente = new Utente(eventi.Eventi.get( ThreadLocalRandom.current().nextInt(0, listaEventi.size()) ), ThreadLocalRandom.current().nextInt(1, 20));
        utente.start();
    })
    
}
