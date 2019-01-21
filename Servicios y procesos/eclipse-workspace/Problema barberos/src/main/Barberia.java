package main;

public class Barberia {
	
	private boolean[] sillasLibres;
	private boolean[] clientesAtendidos;
	
	public Barberia(int numeroSillas) {
		sillasLibres = new boolean[numeroSillas];
		clientesAtendidos = new boolean[numeroSillas];
		for(int i = 0; i < numeroSillas; ++i) {
			sillasLibres[i] = true;
			clientesAtendidos[i] = false;
		}
	}
	
	public synchronized int sentarseSillaLibre() {
		for(int i = 0; i < sillasLibres.length; ++i) {
			if(sillasLibres[i]) {
				sillasLibres[i] = false;
				System.out.println("Cliente sentado en silla " + i);
				return i;
			}
		}
		return -1;
	}
	
	public synchronized int atenderCliente() {
		for(int i = 0; i < sillasLibres.length; ++i) {
			if(!sillasLibres[i]) {
				if(!clientesAtendidos[i]) {
					clientesAtendidos[i] = true;
					System.out.println("Afeitando cliente en silla " + i);
					return i;
				}
			}
		}
		return -1;
	}
	
	public synchronized void liberarSilla(int i) {
		sillasLibres[i] = true;
		clientesAtendidos[i] = false;
		System.out.println("Se marcha el cliente de la silla " + i);
	}
}
