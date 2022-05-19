package client.USER_GUI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class userGUI {
	JFrame f;
	JTable j;

	userGUI() {
		f = new JFrame();
		f.setTitle("User GUI");

		//Client api = new Client("localhost", 80);

		// Richiedo la lista degli eventi

		// Itero sulla lista e inizializzo gli oggetti eventi
		/*List<Evento> eventList = new ArrayList<Evento>();
		for (int i : listaeventi) {
			Evento e = new Evento(i[0], i[1]);
			eventList.add(e);
		}*/

		// Chiamo la EventoTableModel e gli aggiungo gli eventi
		//EventoTableModel model = new EventoTableModel(eventList);

		String[][] data = {{"1", "2"}, {"3", "4"}};

		String[] columnNames = { "Evento", "Posti disponibili", "", "" };

		j = new JTable(data, columnNames);
		j.setBounds(30, 40, 200, 300);

		JScrollPane sp = new JScrollPane(j);
		f.add(sp);
		f.setSize(500, 200);
		f.setVisible(true);
	}
}
