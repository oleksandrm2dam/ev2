package main;

public class Cliente implements Runnable {
	
	private Barberia barberia;
	
	public Cliente(Barberia barberia) {
		this.barberia = barberia;
	}
	
	@Override
	public void run() {
		barberia.sentarseSillaLibre();
	}
}
