package it.polito.tdp.librettovoti;

public class Main {
	public static void main(String[] args) {
		EntryPoint.main(args);
	}
}

//Punto di avvio del progetto, se javafx con wayland non dovesse funzionare, passare come argomenti della vm -Djdk.gtk.version=2
//Nello strumento SceneBuilder conviene, nella creazione dello skeleton da copiare nel controller, spuntare l'opzione full, il metodo initialize viene richiamato
//dal controller

//FXMLLoader legge il file .fxml costruisce i vari nodi della scena, costruisce la classe controller e inietta i riferimenti definiti. Chiama poi il metodo initialize dove posso
//definire altre operazioni da far. In questo caso devo popolare la comboBox 