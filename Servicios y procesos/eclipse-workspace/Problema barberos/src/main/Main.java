package main;

public class Main {
	
	public static void main(String[] args) {
		int NUM_BARBEROS = 3;
		int NUM_SILLAS = 5;
		int NUM_CLIENTES = 20;
		
		Barberia barberia = new Barberia(NUM_SILLAS);
		Thread[] barberos = new Thread[NUM_BARBEROS];
		Thread[] clientes = new Thread[NUM_CLIENTES];
		
		for(int i = 0; i < NUM_BARBEROS; ++i) {
			barberos[i] = new Thread(new Barbero(barberia));
			barberos[i].start();
		}
		
		for(int i = 0; i < NUM_CLIENTES; ++i) {
			clientes[i] = new Thread(new Cliente(barberia));
			clientes[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
