package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Casino {
	
	private long saldo;
	private Estado estadoRuleta;
	private ArrayList<Jugador> apostadores;
	private Random random;
	private Numero[] numeros;
	
	public Casino(long saldo) {
		this.saldo = saldo;
		estadoRuleta = Estado.INICIO;
		apostadores = new ArrayList<Jugador>();
		random = new Random();
		numeros = initNumeros();
	}
	
	private Numero[] initNumeros() {
		// Así es cómo se eligen los números en la ruleta real
		// Fuente: https://es.wikipedia.org/wiki/Ruleta
		Numero[] numeros = new Numero[37];
		
		for(int i = 0; i < 37; ++i) {
			numeros[i] = new Numero(i);
			if(i == 0) {
				numeros[i].setColor("Verde");
			} else {
				if(i == 10 || i == 28) {
					numeros[i].setColor("Negro");
				} else {
					if(reduct(i) % 2 == 0) {
						numeros[i].setColor("Negro");
					} else {
						numeros[i].setColor("Rojo");
					}
				}
			}
		}
		return numeros;
	}
	
	private int reduct(int num) {
		// Método que obtiene la reducción de la suma de un número recursivamente
		String numStr = Integer.toString(num);
		if(numStr.length() > 1) {
			int sum = 0;
			for(int i = 0; i < numStr.length(); ++i) {
				sum += Integer.parseInt(Character.toString(numStr.charAt(i)));
			}
			return reduct(sum);
		} else {
			return num;
		}
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
	
	public synchronized boolean aceptarApuesta(Jugador jugador, long posibleGanancia) {
		if(estadoRuleta == Estado.ACEPTANDO_APUESTAS) {
			if(saldo >= posibleGanancia) {
				apostadores.add(jugador);
				return true;
			}
		}
		return false;
	}
	
	private void comunicarNumeroGanador(Numero numeroGanador) {
		for(Jugador apostador : apostadores) {
			apostador.comunicarNumero(numeroGanador);
		}
		apostadores.clear();
	}
	
	public void girarRuleta() {
		try {
			Numero numeroGanador;
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
				numeroGanador = numeros[random.nextInt(37)];
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
