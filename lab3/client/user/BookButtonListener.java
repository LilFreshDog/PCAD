package lab3.client.user;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookButtonListener implements ActionListener {

    private UserClientGUI gui;
    private BookButtonWorker worker;

    public BookButtonListener(UserClientGUI gui) {
        this.gui = gui;

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        gui.tastoPrenota.setEnabled(false);
        gui.updateButton.setEnabled(false);
        worker = new BookButtonWorker(gui);
        worker.execute();
    }
}