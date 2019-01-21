package main;

import java.util.Random;

public class Barbero implements Runnable{
	
	private Barberia barberia;
	private Random random;
	
	public Barbero(Barberia barberia) {
		this.barberia = barberia;
		random = new Random();
	}
	
	public void esperar(int segundos) {
		int millis = (random.nextInt(segundos) + 1) * 1000;
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			int numSilla;
			do {
				esperar(5); // Dormir 5 segundos
				numSilla = barberia.atenderCliente();
			} while (numSilla == -1);
			
			esperar(2); // Afeitar 2 segundos
			barberia.liberarSilla(numSilla);
			
		}
	}
	
}
