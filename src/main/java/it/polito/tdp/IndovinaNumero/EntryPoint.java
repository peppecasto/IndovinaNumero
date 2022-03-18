package it.polito.tdp.IndovinaNumero;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	Model model = new Model(); //PASSO 2 //ho appena istanziato (creato) il Model --> ora devo creare metodo setModel nel Controller per associare QUESTO model appena istanziato all'attributo vuoto che avevo dichiarato nel Controller durante il PASSO 1
    	
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml")); 
    	//PASSO 4, creo un nuovo controller vuoto, poi creo un nuovo loader passandogli nel costruttore l'argomento contenuto tra le parentesi di QUESTO loader nella RIGA QUI SOPRA (argomento--> è il riferimento alla mia View, cioè Scene.fxml),
    	// infine chiamo quel loader nuovo e lo assegno al nodo radice, non specifico l'argomento perchè l'ho fatto nella riga sopra
        
        FXMLController controller; //PASSO 4.1 (vedi PASSO 4)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml")); //PASSO 4.2 (vedi PASSO 4)
        Parent root = loader.load(); //nodo radice, PASSO 4.3 (vedi PASSO 4)
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'"); //dopo aver creato la Scene, vado al PASSO 5

        controller = loader.getController(); //PASSO 5 //recuperiamo il controller vuoto e lo istanziamo grazie al metodo del loader, ciò che prima era un controller vuoto ora è il nostro FXMLController
        controller.setModel(model); //al controller passiamo il nostro Modello di gestione della logica applicativa, --> INTERAZIONE TRA CONTROLLER E MODEL
        
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
