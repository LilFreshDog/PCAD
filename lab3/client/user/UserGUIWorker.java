package lab3.client.user;

import javax.swing.*;
import java.util.List;

public class UserGUIWorker extends SwingWorker<String, Integer> {

    private UserClientGUI gui;

    public UserGUIWorker(UserClientGUI gui) {
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
    }

}
