package lab3.client.user;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshListener implements ActionListener {
  private UserGUI gui;
  private RefreshWorker worker;

  public RefreshListener(UserGUI gui) {
      this.gui = gui;

  }

  @Override
  public void actionPerformed(ActionEvent ev) {
      gui.RefreshButton.setEnabled(false);
      worker = new RefreshWorker(gui);
      worker.execute();
  }  
}
