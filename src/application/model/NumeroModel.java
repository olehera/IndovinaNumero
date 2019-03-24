package application.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	public NumeroModel() {
		inGioco = false;
	}
	
	public int getSegreto() {
		return segreto;
	}


	public int getTentativiFatti() {
		return tentativiFatti;
	}
	
	public int getTentativiRimasti() {
		return TMAX - tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}

	public boolean isInGioco() {
		return inGioco;
	}


	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		this.segreto = (int) ((Math.random()*NMAX)+1);
    	this.inGioco = true;
    	this.tentativiFatti = 0;
	}
	
	/**
	 * Metodo per effettuare un tentativo
	 * @param t il tentativo
	 * @return 1 se il tentativo è troppo alto, -1 se è troppo basso, 0 se l'utente ha indovinato
	 */
	public int tentativo(int t) {
		//controllo se la partita è in corso
		if (!inGioco) 
			throw new IllegalStateException("La partita è terminata!");
		
		//controllo se l'input è nel range corretto
		if (!tentativoValido(t))
			throw new InvalidParameterException(String.format("Devi inserire un numero tra 1 e "+NMAX));
		
		//gestisci tentativo
		this.tentativiFatti++;
		if (this.tentativiFatti == this.TMAX) {
			//la partita è finita perchè ho esaurito i tentativi
			this.inGioco = false; 
		}
		
		if (t == this.segreto) {
			//ho indovinato
			this.inGioco = false;
			return 0;
		}
		
		if (t > this.segreto) {
			return 1;
		}
			
		return -1;
		
	}
	
	public boolean tentativoValido(int t) {
		if (t<1 || t>NMAX)
			return false;
		else
			return true;
	}
	

}
