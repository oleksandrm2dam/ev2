package main;

import java.util.Random;

public class Jugador {
	
	private int euros;
	private Ruleta ruleta;
	private int tipoJugador;
	
	public Jugador(int euros, Ruleta ruleta, int tipoJugador) {
		this.euros = euros;
		this.ruleta = ruleta;
		this.tipoJugador = tipoJugador;
	}
	
	public void apostar() {
		switch(tipoJugador) {
		case 1:
			ruleta.apostarNumero(this, new Random().nextInt(36) + 1);
			break;
		case 2:
			break;
		case 3:
			break;	
		}
	}
	
	public int getEuros() {
		return euros;
	}
	
	public void setEuros(int euros) {
		this.euros = euros;
	}
	
	
}
