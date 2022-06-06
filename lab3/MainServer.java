package lab3;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer implements Runnable {

  protected Integer port;
  protected ServerSocket serverSocket = null;
  protected boolean isStopped = false;
  protected Thread runningThread = null;
  protected Eventi eventi;

  public MainServer(int port) {
    this.port = port;
    this.eventi = new Eventi();
  }

  public static void main(String[] args) {
    System.out.println("🟢 SERVER STARTED 🟢");
    Scanner input = new Scanner(System.in);
    System.out.println("🚀Inserisci porta: ");
    int port = input.nextInt();
    input.close();
    MainServer server = new MainServer(port);
    server.run();
  }

  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  public synchronized void stop() {
    this.isStopped = true;
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      throw new RuntimeException("Error closing server", e);
    }
  }

  @Override
  public void run() {

    synchronized (this) {
      this.runningThread = Thread.currentThread();
    }

    try {
      this.serverSocket = new ServerSocket(this.port);
    } catch (IOException e) {
      throw new RuntimeException("Cannot open port", e);
    }

    while (!isStopped()) {
      System.out.println("🟡 Waiting for a client connection...");
      Socket clientSocket;
      try {
        clientSocket = this.serverSocket.accept();
        System.out.println("\n🟢 Client connected from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
      } catch (IOException e) {
        if (isStopped()) {
          System.out.println("❌ Server Stopped.");
          System.exit(1);
        }
        throw new RuntimeException("Error accepting client connection", e);
      }

      new Thread( new WorkerRunnable( clientSocket, "Multithreaded Server", eventi)).start();
    }

    System.out.println("Server Stopped.");
  }

  public void stampaEventi(){
    System.out.println("\n\n-------------- 🔩 SERVER DEBUG CONSOLE 🔩 ------------------\n\n");
    eventi.ListaEventi();
    System.out.println("\n\n------------------------------------------------------------\n\n");
  }

}
