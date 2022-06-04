package lab3.client.user;
import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import java.awt.*;

public class RefreshWorker extends SwingWorker<String, Integer> {

    private UserClientGUI gui;

    public RefreshWorker(UserClientGUI gui) {
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
        gui.eventList = gui.client.lista();
        gui.nomiEventi.clear();
        gui.postiEventi.clear();
        for (int i=0; i<gui.eventList.length; i++){
            gui.nomiEventi.add(gui.eventList[i][0]);
            gui.postiEventi.add(Integer.parseInt(gui.eventList[i][1]));
        }
        gui.list.setListData(gui.nomiEventi.toArray());
        gui.tastoPrenota.setEnabled(true);
        gui.updateButton.setEnabled(true);
    }

}
