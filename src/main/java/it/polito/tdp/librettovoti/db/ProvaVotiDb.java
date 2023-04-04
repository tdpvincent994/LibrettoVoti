package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaVotiDb {

	public void aggiungiVoto(String nome, int punti) {
		// in seguito avremo un punto centralizzato e non ripetizione, maggiore sicurezza non passando dati sensibili cosi
		String url = "jdbc:mysql://localhost:3306/Libretto?user=vincent&password=16scudetti";

		try {
			Connection conn = DriverManager.getConnection(url);

			//OCCORRE SEMPRE USARE PreparedStatement e non come nel main Statement
			
			// preparo il template ( meglio verificare su heidiSQL la query e copiarla)
			// i punti interrogativi indicano dove andrà il valore
			String template = "INSERT INTO voti (nome, punti) VALUES (?, ?) ";
			PreparedStatement st = conn.prepareStatement(template);

			// passo i valori al PreperedStatement, posizione e valore nel formato corretto
			st.setString(1, nome);
			st.setInt(2, punti);

			int res = st.executeUpdate();

			// rilascio le risorse
			st.close();
			// chiudo connessione
			conn.close();

			// verifico che sia andato a buon fine, numero righe aggiornate
			if (res == 1) {
				System.out.println("Riga inserita correttamente");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		//per richiamare il metodo che non è statico devo istanziare l'oggetto
		ProvaVotiDb prova = new ProvaVotiDb();
		prova.aggiungiVoto("Tecniche di Programmazione", 30);
		
		
		// per aprire una connessione ho bisogno di una stringa di connessione
		// a chi voglio connettermi, dove si trova, nome schema, utente, password
		// mysql comprende mariadb
		// vedremo un modo migliore per evitare di insire dati sensibili qui

		String url = "jdbc:mysql://localhost:3306/Libretto?user=admin&password=password";

		// per aprire la connessione invoco un metodo statico di DriverManager a cui
		// passo url
		// connessione che va chiusa anche nel programma, il timeout sarebbe troppo
		// lungo magari
		// e il numero di connessioni che il db supporta sono limitate

		try { // Connection, Statement ResultSet sono Interfacce, le classi che le implementano non le vedremo mai noi da qui
				
			
			// fiume
			Connection conn = DriverManager.getConnection(url);

			// barca
			
			
			// query che voglio fare, in JDBC vengono chiamate statement 
			// useremo questo metodo solo inizialmente, poi useremo un metodo più robusto PreparedStatement
			Statement st = conn.createStatement();

			String sql = "SELECT * FROM voti";
			ResultSet res = st.executeQuery(sql);

			
			// lettura, che vale sempre, del set di risultati, scorrimento con cursore
			while (res.next()) {
				
				// devo conoscere il tipo delle colonne e accedo per nome della colonan
				String nome = res.getString("nome");
				int voti = res.getInt("punti");
				
				System.out.println(nome + " " + voti);
			}

			// rilascio le risorse
			
			//non ho bisogno di questo più, per fare nuove query dovrei crearne un altro
			st.close();
			
			//rilascio la connessione
			conn.close();

		} catch (SQLException e) {
			// eventuali eccezioni vengono qui intercettate
			e.printStackTrace();
		}

	}

}
