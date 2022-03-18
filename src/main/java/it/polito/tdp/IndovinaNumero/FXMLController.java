/**
 * Sample Skeleton for 'Scene.fxml' Controller Class 
 Scene.fxml è la View , FXMLController è il Controller, Model sta in un package a parte dove confluiranno TUTTE le classi che ci servono per gestire logica applicativa*/

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	private Model model;//PASSO 1//dichiaro il mio attributo Model nella classe Controller per farli interagire --> POI VADO AD ISTANZIARLO NELL'ENTRYPOINT (potrei istanziarlo anche qui, ma poi dovrei rifare la procedura per ogni Controller nel caso ne avessi più di uno)
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader
    
    @FXML // fx:id="hboxTentativi"
    private HBox hboxTentativi; // Value injected by FXMLLoader


    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	this.model.nuovaPartita();
    	//gestione di una nuova partita --> da portare all'interno del model (prima era in Controller) 
    	
    	//gestione interfaccia --> compito del controller
    	txtTentativi.setText(Integer.toString(this.model.getTMAX())); //non gli do più tmax ma this.model.gettmax
    	hboxTentativi.setDisable(false);
    	txtRisultato.clear();    	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	String ts = txtTentativo.getText();
    	int tentativo;
    	
    	//controllo 1 -> input numerico?
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un tentativo numerico tra 1 e 100!");
    		return;
    	}
    	
    	int risultato;
    	try {
    	risultato = this.model.tentativo(tentativo); //il modello restituirà -1, 0 oppure 1, se = 0 vinco
    	}
    	catch (InvalidParameterException ip) { txtRisultato.setText(ip.getMessage());//nomeEccezione.getMessage() restituisce il messaggio scritto nel costruttore dell'eccezione (vedi model)
    	        return; } 
    	catch (IllegalStateException is) { txtRisultato.setText(is.getMessage());
    	       hboxTentativi.setDisable(true); return; }
    	
    	
    	
    	
    	if(risultato == 0) { //vedi commento riga sopra //HAI VINTO
    		txtRisultato.setText("HAI INDOVINATO CON " + this.model.getTentativiFatti() + " TENTATIVI");
    		hboxTentativi.setDisable(true);}
    	else if(risultato == -1) {
    		txtRisultato.setText("Tentativo Troppo Basso!");
       }else if(risultato == 1) {
    		txtRisultato.setText("Tentativo Troppo Alto!");
    	}
    	
    	txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model modelparametro) { //PASSO 3 - VEDI PASSO 2 per spiegazioni
    	this.model= modelparametro;
    }
}
