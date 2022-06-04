package lab3.client.user;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class BookButtonWorker extends SwingWorker<String, Integer> {

    private UserGUI gui;
    private String eventToBook;
    private String seatsToBook;

    public BookButtonWorker(UserGUI gui) {
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
        eventToBook = gui.Eventfield.getText();
        seatsToBook = gui.Seatsfield.getText();
        if (eventToBook.equals("") || seatsToBook.equals("")) {
            gui.BookButton.setEnabled(true);
            return;
        }
        gui.client.prenota(eventToBook, Integer.parseInt(seatsToBook));
        String[][] data = gui.client.lista();

        //preparing the table for events to be displayed
        JPanel Tablepanel = new JPanel();
        Tablepanel.setLayout(new BorderLayout());
        String[] columnNames = {"Evento", "Posti"};

        //displaying all events from server
        JTable table = new JTable(data, columnNames);
        Tablepanel.add(table, BorderLayout.NORTH);

        gui.panel.add(Tablepanel, BorderLayout.NORTH);
        gui.window.getContentPane().add(gui.panel);
        gui.window.setVisible(true);

        gui.Eventfield.setText("");
        gui.Seatsfield.setText("");
        gui.BookButton.setEnabled(true);
    }

}
