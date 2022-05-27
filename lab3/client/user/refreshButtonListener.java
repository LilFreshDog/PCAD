package lab3.client.user;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class refreshButtonListener implements ActionListener {

    private UserClientGUI gui;
    private UserGUIWorker worker;

    public refreshButtonListener(UserClientGUI gui) {
        this.gui = gui;

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        gui.button1.setEnabled(false);
        worker = new UserGUIWorker(gui);
        worker.execute();
    }
}