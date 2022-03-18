package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;

//questa sezione gestirà logica applicativa di IndovinaNumero //PASSO 0: CREARE PACKAGE it.polito.tdp.nomeesercizio.model e poi la Class Model (chiamala come vuoi) al suo interno
public class Model {
	// VARIABILI DEL MODELLO 
	private int segreto;
	private final int TMAX = 8;
	private final int NMAX = 100;
	private int tentativiFatti;
	
	private boolean inGioco = false;
	
    public int tentativo(int tentativo)
    {   if( this.inGioco == false) {throw new IllegalStateException("HAI PERSO. La partita è terminata");} //si attiva se sono entrato nel metodo con tentativiFatti=TMAX //esaurito i tentativi -> HAI PERSO
    	if(tentativoValido(tentativo)==false) {throw new InvalidParameterException("Deve essere inserito un numero tra 1 e "+NMAX);} //si attivo se inserisco un numero non valido
    	
        this.tentativiFatti++;
        if(this.tentativiFatti==TMAX) {this.inGioco = false;}
    	
        if(tentativo==segreto) {
    	 this.inGioco=false;	return 0; //ho indovinato, non sono più in gioco
    	} 
    	else if(tentativo<segreto)
    	{return -1;}
    	
    	else 
    	{return 1;}
    }
    
	private boolean tentativoValido(int tentativo) {//controllo 2 -> intervallo numerico corretto
		if(tentativo < 1 || tentativo > NMAX) return false;
		
		return true;
	}
	public void nuovaPartita() { //gestione di una nuova partita --> da portare all'interno del model (prima era in Controller) 
	this.inGioco = true;
	this.segreto = (int)((Math.random() * NMAX) +1);
	this.tentativiFatti = 0;
	}

	
	public int getSegreto() {
		return segreto;
	}


	public int getTentativiFatti() {
		return tentativiFatti;
	}

	

	/**
	 * @return the tMAX
	 */
	public int getTMAX() {
		return TMAX;
	}

	/**
	 * @return the nMAX
	 */
	public int getNMAX() {
		return NMAX;
	}
	
}
