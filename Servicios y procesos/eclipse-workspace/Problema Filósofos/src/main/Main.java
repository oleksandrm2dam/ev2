package main;

public class Main {
	
	public static int NUM_FILOSOFOS = 5;
	
	public static void main(String[] args) {
		
		Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
		GestorPalillos gp = new GestorPalillos(NUM_FILOSOFOS);
		
		for(int i = 0; i < NUM_FILOSOFOS; ++i) {
			filosofos[i] = new Filosofo(gp, i);
			new Thread(filosofos[i]).start();
		}
		
	}

}
