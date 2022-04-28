package lab2.eventHandler;

public class Test {
    Admin admin;
    Utente ut1;
    Utente ut2;
    Utente ut3;
    Eventi eventi;

    public Test(){
        this.eventi = new Eventi();
        this.admin = new Admin(this.eventi);
        this.ut1 = new Utente("Giorgio", this.eventi);
        this.ut2 = new Utente("Milo", this.eventi);
        this.ut3 = new Utente("Samuele", this.eventi);
    }

    public void startTesting() {    
        try{
            admin.start();
            ut1.start();
            ut2.start();
            ut3.start();

            ut1.join();
            ut2.join();
            ut3.join();
            admin.join();

            eventi.ListaEventi();
        }catch(InterruptedException e){
            System.out.println("Thread error in execution");
        }
    }

}
