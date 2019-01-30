package main;

public class JugadorParImpar extends Jugador {

	private boolean elegidoPar;
	
	public JugadorParImpar(Casino casino, long saldo) {
		super(casino, saldo);
	}

	@Override
	public void comunicarNumero(Numero numeroGanador) {
		if(numeroGanador.getNumero() != 0) {
			if(numeroGanador.getNumero() % 2 == 0) {
				if(elegidoPar) {
					long saldoGanadoReal = casino.restarSaldo(20);
					saldo += saldoGanadoReal;
					System.out.println(nombre + " ha ganado " + saldoGanadoReal + "€ eligiendo un número par.");
				}
			} else {
				if(!elegidoPar) {
					long saldoGanadoReal = casino.restarSaldo(20);
					saldo += saldoGanadoReal;
					System.out.println(nombre + " ha ganado " + saldoGanadoReal + "€ eligiendo un número impar.");
				}
			}
		}
		apuestaRealizada = false;
		System.out.println("Saldo actual de " + nombre + ": " + saldo + "€.");
	}

	@Override
	public void hacerApuesta() {
		if(casino.aceptarApuesta(this, 20)) {
			elegidoPar = random.nextBoolean();
			casino.sumarSaldo(10);
			saldo -= 10;
			apuestaRealizada = true;
			
			if(elegidoPar) {
				System.out.println(nombre + " haciendo apuesta a números pares por una cantidad de 10€.");
			} else {
				System.out.println(nombre + " haciendo apuesta a números impares por una cantidad de 10€.");
			}
		}
	}

	@Override
	public boolean puedeApostar() {
		return (saldo >= 10);
	}

}
