package main;

public class GestorPalillos {
	
	boolean[] palillos;
	
	public GestorPalillos(int numPalillos) {
		palillos = new boolean[numPalillos];
		for(int i = 0; i < numPalillos; ++i) {
			palillos[i] = false;
		}
	}
	
	public synchronized boolean darPalillos(int numFilosofo) {
		// Devuelve true si los palillos del filósofo están disponibles
		if(numFilosofo == 0) {
			if(palillos[numFilosofo] == false && palillos[palillos.length - 1] == false) {
				palillos[numFilosofo] = true;
				palillos[palillos.length - 1] = true;
				return true;
			} else {
				return false;
			}
		} else {
			if(palillos[numFilosofo] == false && palillos[numFilosofo - 1] == false) {
				palillos[numFilosofo] = true;
				palillos[numFilosofo - 1] = true;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public synchronized void liberarPalillos(int numFilosofo) {
		if(numFilosofo == 0) {
			palillos[numFilosofo] = false;
			palillos[palillos.length - 1] = false;
		} else {
			palillos[numFilosofo] = false;
			palillos[numFilosofo - 1] = false;
		}
	}
	
}
