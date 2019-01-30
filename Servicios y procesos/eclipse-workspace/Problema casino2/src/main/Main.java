package main;

public class Main {

	public static void main(String[] args) {
		
		Casino casino = new Casino(500);
		
		for(int i = 0; i < 4; ++i) {
			Thread hilo = new Thread(new JugadorNumero(casino, 1000));
			hilo.setName("Jugador Clásico Nº" + (i + 1));
			hilo.start();
		}
		
		for(int i = 0; i < 4; ++i) {
			Thread hilo = new Thread(new JugadorParImpar(casino, 1000));
			hilo.setName("Jugador ParImpar Nº" + (i + 1));
			hilo.start();
		}
		
		for(int i = 0; i < 4; ++i) {
			Thread hilo = new Thread(new JugadorMartingala(casino, 1000));
			hilo.setName("Jugador Maringala Nº" + (i + 1));
			hilo.start();
		}
		
		for(int i = 0; i < 4; ++i) {
			Thread hilo = new Thread(new JugadorRojoNegro(casino, 1000));
			hilo.setName("Jugador RojoNegro Nº" + (i + 1));
			hilo.start();
		}
		
		casino.girarRuleta();
	}

}
