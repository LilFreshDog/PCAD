package lab3.client.user;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class UserClientGUI extends JFrame {
    final UserClient client;
    protected String[][] eventList;
    protected ArrayList<String> nomiEventi = new ArrayList<>();
    protected ArrayList<Integer> postiEventi = new ArrayList<>();
    protected JPanel mainPanel;
    protected JButton tastoPrenota;
    protected JSpinner spinner1;
    protected JList list;
    protected JLabel postiEvento;
    protected JButton updateButton;
    protected JLabel nomeEvento;

    public UserClientGUI(UserClient client) {
        this.client = client;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tastoPrenota.setEnabled(false);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() != -1){
                    System.out.println(list.getSelectedIndex());
                    spinner1.setValue(0);
                    nomeEvento.setText(nomiEventi.get(list.getSelectedIndex()));
                    postiEvento.setText("Posti disponibili: " + postiEventi.get(list.getSelectedIndex()));
                    tastoPrenota.setEnabled(true);
                }
            }
        });

        updateButton.addActionListener(new RefreshListener(this));
        tastoPrenota.addActionListener(new BookButtonListener(this));
    }
}