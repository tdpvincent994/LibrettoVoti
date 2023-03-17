package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	// per lavorare sul model, il controller la riceve da fuori e poi ci lavora
	private Libretto model;

	// serve nel caso voglia fare automatismi, quando cambia il modello aggiorna la
	// vista, ma non è il mio caso
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	// inserisco l'oggetto che deve contenere cmbPunti. Mi interessano solo degli
	// interi in questo caso
	@FXML
	private ComboBox<Integer> cmbPunti;

	@FXML
	private TextField txtNome;

	@FXML
	private TextArea txtVoti;

	// campo per indicare errore nell'inserimento
	@FXML
	private Label txtStatus;

	@FXML
	void handleNuovoVoto(ActionEvent event) {
		// 1. ricavo i dati dai campi corretti
		String nome = txtNome.getText();
		Integer punti = cmbPunti.getValue(); // valore selezionato dall'utente, null altrimenti quindi attenzione meglio
												// usare Integer e non int

		// controllo sui parametri inseriti ed eventuale inserimento del voto
		// richiamando metodi del model
		if (nome.equals("") || punti == null) {
			// errore, non posso eseguire l'operazione, aggiungo all'interfaccia video
			// oppure lo scrivo nella textArea. Non è un controllo così ben fatto
			txtVoti.setText("Errore: occorre inserire nome/voto\n");
			txtStatus.setText("Errore inserimento");
			return;
		}

		// 2. Esecuzione dell'operazione (chiedere al model)
		// per farlo devono comunicare tramite oggetti, verificando l'esito
		boolean ok = model.add(new Voto(nome, punti));

		// 3. verifico che sia andato tutto a buon fine aggiornando il risultato nel view
		// Aggiungo al model un metodo per stampare una Lista, getVoti
		if (ok) { // inserimento avvenuto
			List<Voto> voti = model.getVoti();
			txtVoti.clear(); // pulisco l'area di testo
			txtVoti.appendText("Hai superato i seguenti esami\n");

			for (Voto v : voti) {
				txtVoti.appendText(v.toString() + "\n");
			}

			// Resetto elementi interfaccia grafica
			txtNome.clear();
			cmbPunti.setValue(null);
			txtStatus.setText("");

		} else {
			txtStatus.setText("Errore: esame già presente");
		}
	}

	// in questo metodo inizializzo la parte grafica
	@FXML
	void initialize() {
		assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";

		// ricavo la lista degli elementi della comboBox e la popolo con i valori,
		// essendo una lista ho il metodo add, prima però la ripulisco
		cmbPunti.getItems().clear();

		for (int i = 18; i <= 30; i++) {
			cmbPunti.getItems().add(i);
		}

	}

	// deve accedere ai metodi del model, il model lo passa il main al controller
	public void setModel(Libretto model) {
		this.model = model;
	}

}
