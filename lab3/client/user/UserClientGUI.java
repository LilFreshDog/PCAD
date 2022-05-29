package lab3.client.user;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserClientGUI extends JFrame {

    protected UserClient client;
    protected JList list;
    protected JButton button1;
    protected JSpinner spinner1;
    protected JPanel mainPanel;
    protected JLabel nomeEvento;

    public UserClientGUI(UserClient client) {
        this.client = client;

        button1.addActionListener(new refreshButtonListener(this));

        // lista degli eventi
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e.getLastIndex());
            }
        });
    }

    public static void main(String[] args) {
        UserClientGUI gui = new UserClientGUI();
        gui.setContentPane(gui.mainPanel);
        gui.pack();
        gui.setVisible(true);
    }

    public void setData(String[] data) {
        list.setListData(data);
    }

    public void getData(guiListHandler data) {
    }

    public boolean isModified(guiListHandler data) {
        return false;
    }
}

