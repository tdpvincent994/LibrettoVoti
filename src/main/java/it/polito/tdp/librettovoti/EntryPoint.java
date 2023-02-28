package it.polito.tdp.librettovoti;

import it.polito.tdp.librettovoti.model.Libretto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
    	
    	
    	//Questa è la struttura che devo avere quasi sempre, è corretto fare così perchè in questo modo posso usare il model senza controller
    	//inoltre nel caso avessi più scene e più controller sarebbe un casino
    	//Anzichè richiamare un metodo statico, istanzio un oggetto FXMLLoader perchè poi ho bisogno di avere un riferimento al controller che l'oggetto genera
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml")) ;
        Parent root = loader.load() ;
        
        //riferimento al controller secondo lo schema, mi serve per passargli il model
        FXMLController controller = loader.getController();
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        //passo il model al controller
        Libretto model = new Libretto();
        controller.setModel(model) ;
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
