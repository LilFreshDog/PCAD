package lab3.client.user;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookButtonListener implements ActionListener {

    private UserGUI gui;
    private UserGUIWorker worker;

    public BookButtonListener(UserGUI gui) {
        this.gui = gui;

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        gui.BookButton.setEnabled(false);
        worker = new UserGUIWorker(gui);
        worker.execute();
    }
}