package lab3.client.user;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class BookButtonWorker extends SwingWorker<String, Integer> {

    private UserClientGUI gui;
    private String eventToBook;
    private String seatsToBook;

    public BookButtonWorker(UserClientGUI gui) {
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
        Integer selectedIndex = gui.list.getSelectedIndex();
        gui.client.prenota(gui.nomiEventi.get(selectedIndex), Integer.parseInt(gui.spinner1.getValue().toString()));

        gui.eventList = gui.client.lista();
        gui.nomiEventi.clear();
        gui.postiEventi.clear();
        for (int i=0; i<gui.eventList.length; i++){
            gui.nomiEventi.add(gui.eventList[i][0]);
            gui.postiEventi.add(Integer.parseInt(gui.eventList[i][1]));
        }
        gui.list.setListData(gui.nomiEventi.toArray());
        gui.postiEvento.setText("Posti disponibili: " + gui.postiEventi.get(selectedIndex));
        gui.updateButton.setEnabled(true);
        gui.tastoPrenota.setEnabled(true);
    }

}
