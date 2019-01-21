package main;

import java.util.Random;

public abstract class Jugador implements Runnable {
	
	protected Casino casino;
	protected long saldo;
	protected boolean apuestaRealizada;
	protected Random random;
	
	public Jugador(Casino casino, long saldo) {
		this.casino = casino;
		this.saldo = saldo;
		apuestaRealizada = false;
		random = new Random();
	}
	
	@Override
	public void run() {
		while(!apuestaRealizada && puedeApostar()) {
			hacerApuesta();
		}
	}
	
	public abstract void comunicarNumero(int numeroGanador);
	
	public abstract void hacerApuesta();
	
	public abstract boolean puedeApostar();
	
}
