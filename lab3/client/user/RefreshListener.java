package lab3.client.user;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshListener implements ActionListener {
  private UserClientGUI gui;
  private RefreshWorker worker;

  public RefreshListener(UserClientGUI gui) {
      this.gui = gui;

  }

  @Override
  public void actionPerformed(ActionEvent ev) {
      gui.updateButton.setEnabled(false);
      gui.tastoPrenota.setEnabled(false);
      worker = new RefreshWorker(gui);
      worker.execute();
  }  
}
