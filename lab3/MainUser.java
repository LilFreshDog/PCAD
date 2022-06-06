package lab3;
import java.util.Scanner;

import lab3.client.user.UserClient;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class MainUser {
  public static void main(String[] args){
    
    try{
      //create the client and book some seats 
      Scanner input = new Scanner(System.in);
      System.out.println("🟡 Inserisci hostname: ");
      String hostname = input.nextLine();
      System.out.println("🟡 Inserisci port: ");
      int port = input.nextInt();

      System.out.println("1 2 3 4 or silence?");
      String line = input.nextLine();
      if(line.equals("")) line = input.nextLine();
      if (line.equals("1")){
        playSound("psx");
        System.out.println("Credits: https://youtu.be/Qg0BvV0HE4s");
      }
      if (line.equals("2")){
        playSound("minecraft");
        System.out.println("Credits: https://youtu.be/mukiMaOSLEs");
      }
      if (line.equals("3")){
        playSound("wii");
        System.out.println("Credits: https://youtu.be/x2NzoLMWAwQ");
      }
      if (line.equals("4")){
        playSound("ableton");
        System.out.println("Credits: https://youtu.be/C4Zwe6hMMO4");
      }


      UserClient user = new UserClient(hostname, port);

      input.close();
      System.out.println("🟢 USER GUI STARTED");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static synchronized void playSound(final String url) {
    new Thread(new Runnable() {
      // The wrapper thread is unnecessary, unless it blocks on the
      // Clip finishing; see comments.
      public void run() {
        try {
          Clip clip = AudioSystem.getClip();
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                  MainUser.class.getResourceAsStream("audio/" + url + ".wav"));
          clip.open(inputStream);
          clip.start();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
    }).start();
  }
  
}
