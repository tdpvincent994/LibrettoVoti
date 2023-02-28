package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
 
	//per lavorare sul model, il controller la riceve da fuori e poi ci lavora
	private Libretto model;
	
	
	//serve nel caso voglia fare automatismi, quando cambia il modello aggiorna la vista, ma non è il mio caso
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //inserisco l'oggetto che deve contenere cmbPunti. Mi interessano solo degli interi in questo caso
    @FXML
    private ComboBox<Integer> cmbPunti;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtVoti;

    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	//ricavo i dati dai campi corretti 
    	String nome= txtNome.getText();
    	int punti =cmbPunti.getValue(); //valore selezionato dall'utente
    	
    	//controllo sui parametri inseriti ed eventuale inserimento del voto

    	model.add(new Voto(nome, punti)); 
    	
    	//verifico che sia andato tutto a buon fine stampando sulla textArea
    	String contenutoLibretto= model.toString();
    	txtVoti.setText(contenutoLibretto);
    }

    //in questo metodo inizializzo la parte grafica
    @FXML
    void initialize() {
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";
        
    	//ricavo la lista degli elementi della comboBox e la popolo con i valori, essendo una lista ho il metodo add, prima però la ripulisco
    	cmbPunti.getItems().clear();

        for (int i=18; i<=30; i++) {
        	cmbPunti.getItems().add(i);  
        }

    }

    //deve accedere ai metodi del model, il model lo passa il main al controller
	public void setModel(Libretto model) {
		this.model=model;
	}

}
