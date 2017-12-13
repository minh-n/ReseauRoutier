package com.Exception;

public class ErreurCapteur extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ErreurCapteur(){
		System.err.println("Erreur Capteur !! \n");
	}
	
	public ErreurCapteur(String s) {
		super(s);
	}

}
