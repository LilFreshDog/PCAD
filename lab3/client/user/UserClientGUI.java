package lab3.client.user;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserClientGUI extends JFrame {
    private JPanel mainPanel;
    private JButton tastoPrenota;
    private JSpinner spinner1;
    private JList list;

    public UserClientGUI() {
        tastoPrenota.setEnabled(false);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(list.getSelectedIndex());
                spinner1.setValue(0);
                tastoPrenota.setEnabled(true);
            }
        });

        spinner1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                evt.getNewValue();
            }
        });
    }

    public static void main(String[] args) {
        UserClientGUI gui = new UserClientGUI();
        gui.setContentPane(gui.mainPanel);
        gui.pack();
        gui.setVisible(true);
    }

}