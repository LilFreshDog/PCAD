package lab3.client.user;

import javax.swing.*;
import org.w3c.dom.events.Event;
import java.awt.*;

public class UserGUI {
    public void display() {
        UserClient client = new UserClient("localhost", "9000");
        JFrame window = new JFrame();
        window.setTitle("Prenota tutti gli eventi del mondo 🥳");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        //setting up the panel to add everything
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.PINK);
        
        //preparing the table for events to be displayed
        JPanel Tablepanel = new JPanel();
        Tablepanel.setLayout(new BorderLayout());
        String[] columnNames = {"Evento", "Posti"};

        Object[][] data = {
          {"Coachella",300},
          {"Tomorrowland", 150},
          {"Lezione di PCAD", 20},
          {"Coachella",300},
          {"Tomorrowland", 150},
          {"Lezione di PCAD", 20},
          {"Coachella",300},
          {"Tomorrowland", 150},
          {"Lezione di PCAD", 20},
          {"Coachella",300},
          {"Tomorrowland", 150},
          {"Lezione di PCAD", 20},
          {"LA FORESTA DEI CAZZI VOLANTI NENNO", 20}
        };
        JTable table = new JTable(data, columnNames);
        Tablepanel.add(table, BorderLayout.NORTH);
    
        //preparing the textfield for event
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
    
        //preparing panels for the textfields and labels
        
        //-----------first
        JPanel prenotaEventoPanel = new JPanel();
        prenotaEventoPanel.setLayout(new BorderLayout());
        
        JLabel eventlabel = new JLabel("Evento da prenotare");
        eventlabel.setForeground(Color.WHITE);
        JTextField Eventfield = new JTextField();
        
        prenotaEventoPanel.add(eventlabel,BorderLayout.NORTH);
        prenotaEventoPanel.add(Eventfield,BorderLayout.SOUTH);
        prenotaEventoPanel.setBackground(Color.PINK);
    
    
        //------------second
        JPanel prenotaEventoPanel2 = new JPanel();
        prenotaEventoPanel2.setLayout(new BorderLayout());
    
        JLabel eventlabel2 = new JLabel("Posti");
        eventlabel2.setForeground(Color.WHITE);
        JTextField Seatsfield = new JTextField();
        
        prenotaEventoPanel2.add(eventlabel2,BorderLayout.NORTH);
        prenotaEventoPanel2.add(Seatsfield,BorderLayout.SOUTH);
        prenotaEventoPanel2.setBackground(Color.PINK);
        
        //
        JButton BookButton = new JButton("Prenota");
    
        inputpanel.add(prenotaEventoPanel);
        inputpanel.add(prenotaEventoPanel2);
        inputpanel.add(BookButton);
        inputpanel.setBackground(Color.PINK); 
        
        //adding elements to the main panel and disposing them
        panel.add(Tablepanel, BorderLayout.NORTH);
        panel.add(inputpanel, BorderLayout.CENTER);
    
        window.getContentPane().add(panel);
        window.setVisible(true);
    }
}
