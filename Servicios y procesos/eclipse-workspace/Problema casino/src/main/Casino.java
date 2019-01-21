package main;

import java.util.ArrayList;
import java.util.Random;

public class Casino {
	
	private long saldo;
	private Estado estadoRuleta;
	private ArrayList<Jugador> apostadores;
	private Random random;
	
	public Casino(long saldo) {
		this.saldo = saldo;
		estadoRuleta = Estado.INICIO;
		apostadores = new ArrayList<Jugador>();
		random = new Random();
	}
	
	public Estado getEstado() {
		return estadoRuleta;
	}
	
	public synchronized void sumarSaldo(long cantidad) {
		saldo += cantidad;
	}
	
	public synchronized void restarSaldo(long cantidad) {
		if(saldo - cantidad <= 0) {
			saldo = 0;
			estadoRuleta = Estado.EN_BANCARROTA;
		} else {
			saldo -= cantidad;
		}
	}
	
	public synchronized void aceptarApuesta(Jugador jugador) {
		if(estadoRuleta == Estado.ACEPTANDO_APUESTAS) {
			apostadores.add(jugador);
		}
	}
	
	private void comunicarNumeroGanador(int numeroGanador) {
		for(Jugador apostador : apostadores) {
			apostador.comunicarNumero(numeroGanador);
		}
		apostadores.clear();
	}
	
	public void girarRuleta() {
		try {
			int numeroGanador;
			while(estadoRuleta != Estado.EN_BANCARROTA) {
				System.out.println("Aceptando apuestas...");
				estadoRuleta = Estado.ACEPTANDO_APUESTAS;
				Thread.sleep(3000);
				System.out.println("Girando ruleta...");
				estadoRuleta = Estado.RULETA_GIRANDO;
				Thread.sleep(1500);
				numeroGanador = random.nextInt(37);
				System.out.println("Número ganador: " + numeroGanador);
				System.out.println("Pagando apuestas...");
				estadoRuleta = Estado.PAGANDO_APUESTAS;
				comunicarNumeroGanador(numeroGanador);
			}
			System.out.println("BANCARROTA!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
