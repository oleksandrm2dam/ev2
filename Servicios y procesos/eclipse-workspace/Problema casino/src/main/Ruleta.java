package main;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta implements Runnable {
	
	private int euros;
	private Random random;
	private int numero;
	private ArrayList<Jugador> jugadores;
	
	public Ruleta(int euros) {
		random = new Random();
		this.euros = euros;
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public void anadirJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	
	@Override
	public void run() {
		while(jugadores.size() > 0) {
			numero = random.nextInt(37);
			
			for(Jugador jugador : jugadores) {
				jugador.apostar();
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void apostarNumero(Jugador jugador, int numero) {
		
	}
	
	public void apostarParImpar(Jugador jugador, boolean par) {
		
	}

	public void apostarMartingala(Jugador jugador, int numero) {
		
	}	
	
	
	
}
