package lab3;
import java.io.InputStream;;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class WorkerRunnable implements Runnable {
	protected Socket clientSocket = null;
	protected String serverText = null;
	protected Eventi eventi;

	public WorkerRunnable(Socket clientSocket, String serverText, Eventi eventi) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
		this.eventi = eventi;
	}

	public void run() {
		try {
			System.out.println("\n\n🛠 WorkerRunnable has started working");
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			output.write(serverText).getBytes();
			output.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
}