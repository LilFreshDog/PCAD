package lab3;
import java.util.Scanner;
import lab3.client.admin.AdminClient;
import lab3.client.user.UserClient;
import java.util.concurrent.TimeUnit;

class Main {
  public static void main(String[] args){
    
    try{
      //create the admin and create some events
      AdminClient admin = new AdminClient("localhost", 9000);
      admin.crea("Coachella", 30);
      admin.crea("Astroworld", 20);
      admin.crea("Festival del Thread", 10);
      System.out.println("creati gli eventi");
      TimeUnit.SECONDS.sleep(6);

      //create the client and book some seats 
      Scanner input = new Scanner(System.in);
      System.out.println("Inserisci hostname: ");
      String hostname = input.nextLine();
      System.out.println("Inserisci port: ");
      int port = input.nextInt();
      UserClient user = new UserClient(hostname, port);
      input.close();
    }catch(Exception e){
      System.out.println(e.getMessage());
    }

  }
  
}
