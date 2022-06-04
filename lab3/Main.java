package lab3;
import java.util.Scanner;
import lab3.client.admin.AdminClient;
import lab3.client.user.UserClient;
import java.util.concurrent.TimeUnit;

class Main {
  public static void main(String[] args){
    
    try{
      //create the client and book some seats 
      Scanner input = new Scanner(System.in);
      System.out.println("🟡 Inserisci hostname: ");
      String hostname = input.nextLine();
      System.out.println("🟡 Inserisci port: ");
      int port = input.nextInt();
      UserClient user = new UserClient(hostname, port);
      input.close();
      System.out.println("🟢 USER GUI STARTED");
    }catch(Exception e){
      System.out.println(e.getMessage());
    }

  }
  
}
