package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

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

    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	// Gestisce l'inizio di una nuova partita
    	
    	// Logica del gioco
    	this.segreto = (int) ((Math.random()*NMAX)+1);
    	this.inGioco = true;
    	this.tentativiFatti = 0;

    	// Gestione dell'interfaccia
    	boxControlloPartita.setDisable(true);
    	boxControlloTentativi.setDisable(false);
    	txtMessaggi.clear();
    	txtRimasti.setText(Integer.toString(this.TMAX));
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	// Leggi il valore del tentativo
    	String st = txtTentativo.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(st);
    	} catch (NumberFormatException nfe) {
    		// la stringa inserita non è un numero valido
    		txtMessaggi.appendText("Non è un numero valido! \n");
    		return;
    	}
    	
    	tentativiFatti++;
    	
    	// Aggiornare interfaccia
    	txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    	txtTentativo.clear();

    	
    	// Controlla se ha indovinato -> fine partita
    	if (tentativo == segreto) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+tentativiFatti+" tentativi \n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	// Verifica se ha esaurito i tentativi -> fine partita
    	if (tentativiFatti == TMAX) {
    		txtMessaggi.appendText("Hai perso, il numero segreto era: "+segreto+"\n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	// Informa se era troppo alto/basso
    	if (tentativo < segreto) 
    		txtMessaggi.appendText("Tentativo troppo basso! \n");
    	else
    		txtMessaggi.appendText("Tentativo troppo alto! \n");
    	
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
