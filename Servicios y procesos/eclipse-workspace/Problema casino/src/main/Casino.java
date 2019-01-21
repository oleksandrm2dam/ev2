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
	
	public synchronized long restarSaldo(long cantidad) {
		if(saldo - cantidad <= 0) {
			long saldoDevolver = saldo;
			saldo = 0;
			estadoRuleta = Estado.EN_BANCARROTA;
			return saldoDevolver;
		} else {
			saldo -= cantidad;
			return cantidad;
		}
	}
	
	public synchronized boolean aceptarApuesta(Jugador jugador) {
		if(estadoRuleta == Estado.ACEPTANDO_APUESTAS) {
			apostadores.add(jugador);
			return true;
		}
		return false;
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
			estadoRuleta = Estado.INICIO;
			Thread.sleep(1000);
			while(estadoRuleta != Estado.EN_BANCARROTA) {
				System.out.println("");
				System.out.println("");
				System.out.println("---Saldo de la banca actual: " + saldo + "€.---");
				System.out.println("---Aceptando apuestas---");
				estadoRuleta = Estado.ACEPTANDO_APUESTAS;
				Thread.sleep(3000);
				System.out.println("---Girando ruleta---");
				estadoRuleta = Estado.RULETA_GIRANDO;
				Thread.sleep(1500);
				numeroGanador = random.nextInt(37);
				System.out.println("---Número ganador: " + numeroGanador + "---");
				System.out.println("---Pagando apuestas---");
				estadoRuleta = Estado.PAGANDO_APUESTAS;
				comunicarNumeroGanador(numeroGanador);
			}
			System.out.println("---BANCARROTA!---");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
