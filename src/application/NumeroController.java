package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private NumeroModel model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox boxControlloPartita;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox boxControlloTentativi;

    @FXML
    private TextField txtTentativo;

    @FXML
    private TextArea txtMessaggi;

    public void setModel(NumeroModel model) {
		this.model = model;
	}

	@FXML
    void handleNuovaPartita(ActionEvent event) {
    	// Gestisce l'inizio di una nuova partita
		model.newGame();

    	// Gestione dell'interfaccia
    	boxControlloTentativi.setDisable(false);
    	boxControlloPartita.setDisable(true);
    	txtMessaggi.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	// Leggi il valore del tentativo
    	String st = txtTentativo.getText();
    	int ten;
    	try {
    		ten = Integer.parseInt(st);
    	} catch (NumberFormatException nfe) {
    		// la stringa inserita non è un numero valido
    		txtMessaggi.appendText("Non è un numero valido! \n");
    		txtTentativo.clear();
    		return;
    	}
    	
    	if (!model.tentativoValido(ten)) {
    		txtMessaggi.appendText("Range non valido! \n");
    		txtTentativo.clear();
    	}
    	
    	int risultato = model.tentativo(ten);
    	
    	// Aggiornare interfaccia
    	txtRimasti.setText(Integer.toString(model.getTentativiRimasti()));
    	txtTentativo.clear();
    	
    	if (risultato == 0) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+model.getTentativiFatti()+" tentativi \n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    	} else if (risultato < 0)
    		txtMessaggi.appendText("Tentativo troppo basso! \n");
    	else
    		txtMessaggi.appendText("Tentativo troppo alto! \n");
    	
    	
    	if (!model.isInGioco() && risultato != 0) {
    		//la partita è finita
    			txtMessaggi.appendText("Hai perso, il numero segreto era: "+model.getSegreto()+"\n");
        		
        		boxControlloPartita.setDisable(false);
        		boxControlloTentativi.setDisable(true);
    	}
    	
    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Sample.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
