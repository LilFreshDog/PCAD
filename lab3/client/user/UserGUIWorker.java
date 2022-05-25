package lab3.client.user;
import javax.swing.*;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ExecutionException;

public class UserGUIWorker extends SwingWorker<String, Integer> {

    private UserGUI gui;
    public UserGUIWorker(UserGUI gui) {
      this.gui = gui;
    }


    @Override
    protected String doInBackground() throws Exception {
      return "Done!";
    }
    
    @Override
    protected void process(List<Integer> chunks) {
    }

    @Override
    protected void done() {
      String eventToBook = gui.Eventfield.getText();
      String seatsToBook = gui.Seatsfield.getText();
      gui.client.prenota(eventToBook, Integer.parseInt(seatsToBook));
      gui.BookButton.setEnabled(true);
    }

}
