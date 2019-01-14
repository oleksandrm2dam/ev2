package main;

public class Main {

	public static void main(String[] args) {
		Ruleta ruleta = new Ruleta(50000);
		
		for(int i = 0; i < 4; ++i) {
			ruleta.anadirJugador(new Jugador(1000, ruleta, 1));
		}
		
		for(int i = 0; i < 4; ++i) {
			ruleta.anadirJugador(new Jugador(1000, ruleta, 2));
		}
		
		for(int i = 0; i < 4; ++i) {
			ruleta.anadirJugador(new Jugador(1000, ruleta, 3));
		}
		
		new Thread(ruleta).start();
		
	}

}
