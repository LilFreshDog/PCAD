package lab2.eventHandler;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    Admin admin;
    Utente ut1;
    Utente ut2;
    Utente ut3;
    Eventi eventi;

    public Test(){
        this.eventi = new Eventi();
        this.admin = new Admin(this.eventi);
        this.ut1 = new Utente(this.eventi);
        this.ut2 = new Utente(this.eventi);
        this.ut3 = new Utente(this.eventi);
    }

    public void startTesting() {
        admin.start();
        ut1.start();
        ut2.start();
        ut3.start();
    }

}
