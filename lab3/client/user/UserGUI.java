package client.user;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class UserGUI {
    JFrame f;

    public void display() {
        f = new JFrame();
        f.setTitle("User GUI");

        //Client api = new Client("localhost", 80);

		// Richiedo la lista degli eventi

		String[][] data = {{"Tomorrowland", "2"}, {"assussa", "4"}};

        JPanel sus = new JPanel();

		for (String[] s : data){
            JPanel evento = new JPanel();
            JLabel nome = new JLabel(s[0]);
            evento.add(nome);
            sus.add(evento);
        } 
        f.add(sus);

        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 200);
		f.setVisible(true);
    }

    public static JPanel addPanel(String title){
        JPanel panel = new JPanel();
        panel.add(new JButton(title));
        return panel;
    }
}
