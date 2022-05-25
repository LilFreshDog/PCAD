package lab3.client.user;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserGUIListener implements ActionListener {

    private UserGUI gui;
    private UserGUIWorker worker;

    public UserGUIListener(UserGUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        gui.BookButton.setEnabled(false);
        worker = new UserGUIWorker(gui);
        worker.execute();
    }
}