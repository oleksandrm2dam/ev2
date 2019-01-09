package main;

import java.util.Random;

public class Filosofo implements Runnable {
	
	GestorPalillos gp;
	int numFilosofo;
	
	public Filosofo(GestorPalillos gp, int numFilosofo) {
		this.gp = gp;
		this.numFilosofo = numFilosofo;
	}
	
	@Override
	public void run() {
		while(true) {
			if(gp.darPalillos(numFilosofo)) {
				System.out.println("Filósofo " + numFilosofo + " comiendo");
				try {
					Thread.sleep(new Random().nextInt(4000) + 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gp.liberarPalillos(numFilosofo);
				System.out.println("Filósofo " + numFilosofo + " pensando");
				try {
					Thread.sleep(new Random().nextInt(1000) + 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Filósofo " + numFilosofo + " pensando");
				try {
					Thread.sleep(new Random().nextInt(1000) + 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
