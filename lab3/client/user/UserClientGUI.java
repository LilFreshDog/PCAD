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
        //aggiornaLista();

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

        updateButton.addActionListener(new RefreshListener(this));/* {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaLista();
            }
        });*/
        tastoPrenota.addActionListener(new BookButtonListener(this));/* {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedIndex = list.getSelectedIndex();
                client.prenota(nomiEventi.get(selectedIndex), Integer.parseInt(spinner1.getValue().toString()));
                //aggiornaLista();
                postiEvento.setText("Posti disponibili: " + postiEventi.get(selectedIndex));
            }
        });*/
    }

    /*public static void main(String[] args) {
        UserClientGUI gui = new UserClientGUI(new UserClient("localhost", 9000));

    }

    /*void aggiornaLista(){
        tastoPrenota.setEnabled(false);
        eventList = client.lista();
        nomiEventi.clear();
        postiEventi.clear();
        for (int i=0; i<eventList.length; i++){
            nomiEventi.add(eventList[i][0]);
            postiEventi.add(Integer.parseInt(eventList[i][1]));
        }
        list.setListData(nomiEventi.toArray());
        tastoPrenota.setEnabled(true);
    }*/

}