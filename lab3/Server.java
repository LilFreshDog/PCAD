package lab3;

import java.io.*;
import java.net.*;

public class Server implements Runnable {

  protected Integer serverPort = 8080;
  protected ServerSocket serverSocket = null;
  protected boolean isStopped = false;
  protected Thread runningThread = null;

  public Server(int serverPort) {
    this.serverPort = serverPort;
  }

  @Override
  public void run() {
    
    synchronized (this) {
      this.runningThread = Thread.currentThread();
    }

    try {
      this.serverSocket = new ServerSocket(this.serverPort);
    } catch (IOException e) {
      throw new RuntimeException("Cannot open port 8080", e);
    }

    while (!isStopped()) {
      Socket clientSocket = null;
      try {
        clientSocket = this.serverSocket.accept();
      } catch (IOException e) {
        if (isStopped()) {
          System.out.println("Server Stopped.");
          return;
        }
        throw new RuntimeException("Error accepting client connection", e);
      }
      new Thread( new WorkerRunnable( clientSocket, "Multithreaded Server")).start();
    }

    System.out.println("Server Stopped.");
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
}
