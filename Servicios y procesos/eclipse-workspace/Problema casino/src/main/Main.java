package main;

public class Main {

	public static void main(String[] args) {
		
		Casino casino = new Casino(50000);
		
		for(int i = 0; i < 4; ++i) {
			new Thread(new JugadorNumero(casino, 1000)).start();
		}
		
		for(int i = 0; i < 4; ++i) {
			new Thread(new JugadorParImpar(casino, 1000)).start();
		}
		
		for(int i = 0; i < 4; ++i) {
			new Thread(new JugadorMartingala(casino, 1000)).start();
		}
		
		casino.girarRuleta();
	}

}
