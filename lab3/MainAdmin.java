package lab3;
import java.util.Scanner;
import lab3.client.admin.AdminClient;
import lab3.client.user.UserClient;
import java.util.concurrent.TimeUnit;


public class MainAdmin {
  public static void main(String[] args){
    
    try{
      //create the admin and create some events
      Scanner input = new Scanner(System.in);
      System.out.println("🟡 Inserisci hostname: ");
      String hostname = input.nextLine();
      System.out.println("🟡 Inserisci porta: ");
      int port = input.nextInt();
      AdminClient admin = new AdminClient(hostname, port);

      System.out.println("🛠----- ISTRUZIONI -----🛠");
      System.out.println("🟣 crea");
      System.out.println("🟣 aggiungi");
      System.out.println("🟣 elimina");
      System.out.println("🟣 chiudi");
      System.out.println("🟣 esci");
      System.out.println("------------------------");

      String line = "";
      System.out.println("Cosa vuoi fare: ");
      while(!line.equals("exit")){
        line = input.nextLine();
        if(line.equals(""))continue;
        if(line.equals("crea")){
          System.out.println("🟨 Inserisci nome evento: ");
          String name = input.nextLine();
          System.out.println("🟨 Inserisci numero posti: ");
          int seats = input.nextInt();
          admin.crea(name, seats);
          System.out.println("🟢 Success! Creato " + name + " con " + seats + " posti");
        }else if(line.equals("elimina")){
          System.out.println("Inserisci nome evento: ");
          String name = input.nextLine();
          admin.cancella(name);
          System.out.println("🟢 Success! Eliminato " + name);
        }else if(line.equals("esci")){
          System.out.println("🟡 ADMIN SESSION ENDED!\nBye-bye 👋");
          System.exit(0);
        }else if(line.equals("aggiungi")){
          System.out.println("🟨 Inserisci nome evento: ");
          String name = input.nextLine();
          System.out.println("🟨 Inserisci numero posti: ");
          int seats = input.nextInt();
          admin.aggiungi(name, seats);
          System.out.println("🟢 Success! Aggiunti " + seats + " posti a " + name);
        }else if(line.equals("chiudi")){
          System.out.println("🟨 Inserisci nome evento: ");
          String name = input.nextLine();
          admin.chiudi(name);
          System.out.println("🟢 Success! Chiuse le prenotazioni " + name);
        }else if (line.equals("sus")){
          System.out.println("https://www.youtube.com/watch?v=ZKuDJQqy6ak");
        }
        else{
          System.out.println("🔴 Comando non riconosciuto...");
        }

        System.out.println("Cosa vuoi fare: ");
      }
      input.close();

    }catch(Exception e){
      System.out.println(e.getMessage());
    }

  }
}
