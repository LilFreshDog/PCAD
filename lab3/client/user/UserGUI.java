package lab3.client.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserGUI {


    protected UserClient client;
    protected JTextField Eventfield = new JTextField();
    protected JTextField Seatsfield = new JTextField();
    protected JButton BookButton = new JButton("Prenota");
    protected JButton RefreshButton = new JButton("Aggiorna");
    protected JFrame window = new JFrame();
    protected JPanel panel = new JPanel();

    public UserGUI(UserClient client){
      this.client = client;
    }

    public void display() {
        BookButtonListener booking_handler = new BookButtonListener(this);
        window.setTitle("Prenota tutti gli eventi del mondo 🥳");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        //setting up the panel to add everything
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.PINK);
        
        //preparing the table for events to be displayed
        JPanel Tablepanel = new JPanel();
        Tablepanel.setLayout(new BorderLayout());
        String[] columnNames = {"Evento", "Posti"};

        //displaying all events from server
        if(client.lista() != null) {
            JTable table = new JTable(client.lista(), columnNames);
            table.setEnabled(false);
            table.addMouseListener( new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    JTable source = (JTable)e.getSource();
                    int row = source.rowAtPoint( e.getPoint() );
                    int column = source.columnAtPoint( e.getPoint() );
    
                    Eventfield.setText(source.getModel().getValueAt(row, column).toString());
                }
            });
            Tablepanel.add(table, BorderLayout.NORTH);
        }
        else {
            JLabel label = new JLabel("Non ci sono eventi disponibili");
            Tablepanel.add(label, BorderLayout.NORTH);
        }

        //preparing the textfield for event
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
    
        //preparing panels for the textfields and labels
        
        //-----------first
        JPanel prenotaEventoPanel = new JPanel();
        prenotaEventoPanel.setLayout(new BorderLayout());
        
        JLabel eventlabel = new JLabel("Evento da prenotare");
        eventlabel.setForeground(Color.WHITE);
        
        prenotaEventoPanel.add(eventlabel,BorderLayout.NORTH);
        prenotaEventoPanel.add(Eventfield,BorderLayout.SOUTH);
        prenotaEventoPanel.setBackground(Color.PINK);
    
        //------------second
        JPanel prenotaEventoPanel2 = new JPanel();
        prenotaEventoPanel2.setLayout(new BorderLayout());
    
        JLabel eventlabel2 = new JLabel("Posti");
        eventlabel2.setForeground(Color.WHITE);
        
        prenotaEventoPanel2.add(eventlabel2,BorderLayout.NORTH);
        prenotaEventoPanel2.add(Seatsfield,BorderLayout.SOUTH);
        prenotaEventoPanel2.setBackground(Color.PINK);
        
        //
       
        BookButton.addActionListener(booking_handler);
        RefreshButton.addActionListener(new RefreshListener(this));
        inputpanel.add(prenotaEventoPanel);
        inputpanel.add(prenotaEventoPanel2);
        inputpanel.add(BookButton);
        inputpanel.add(RefreshButton);
        inputpanel.setBackground(Color.PINK); 
        
        //adding elements to the main panel and disposing them
        panel.add(Tablepanel, BorderLayout.NORTH);
        panel.add(inputpanel, BorderLayout.CENTER);
    
        window.getContentPane().add(panel);
        window.setVisible(true);
    }
}
