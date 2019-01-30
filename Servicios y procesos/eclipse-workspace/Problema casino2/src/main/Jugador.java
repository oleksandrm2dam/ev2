package main;

import java.util.Random;

public abstract class Jugador implements Runnable {
	
	protected Casino casino;
	protected long saldo;
	protected boolean apuestaRealizada;
	protected Random random;
	protected String nombre;
	
	public Jugador(Casino casino, long saldo) {
		this.casino = casino;
		this.saldo = saldo;
		apuestaRealizada = false;
		random = new Random();
	}
	
	@Override
	public void run() {
		nombre = Thread.currentThread().getName();
		while(puedeApostar()) {
			if(!apuestaRealizada) {
				hacerApuesta();
			}
		}
		System.out.println(Thread.currentThread().getName() + " está en bancarrota!.");
	}
	
	public abstract void comunicarNumero(Numero numeroGanador);
	
	public abstract void hacerApuesta();
	
	public abstract boolean puedeApostar();
	
}
