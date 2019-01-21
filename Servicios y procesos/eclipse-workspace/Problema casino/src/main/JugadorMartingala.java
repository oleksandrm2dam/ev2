package main;

public class JugadorMartingala extends Jugador {

	private int numeroElegido;
	private long apuestaAnterior;
	
	public JugadorMartingala(Casino casino, long saldo) {
		super(casino, saldo);
		apuestaAnterior = 5;
	}

	@Override
	public void comunicarNumero(int numeroGanador) {
		if(numeroGanador == numeroElegido) {
			long saldoGanadoReal = casino.restarSaldo(apuestaAnterior * 36);
			saldo += saldoGanadoReal;
			System.out.println(nombre + " ha ganado " + saldoGanadoReal + "€ eligiendo el número " + numeroElegido + ".");
			apuestaAnterior = 5;
		}
		apuestaRealizada = false;
		System.out.println("Saldo actual de " + nombre + ": " + saldo + "€.");
	}

	@Override
	public void hacerApuesta() {
		if(casino.aceptarApuesta(this)) {
			numeroElegido = random.nextInt(36) + 1;
			casino.sumarSaldo(apuestaAnterior * 2);
			saldo -= apuestaAnterior * 2;
			apuestaRealizada = true;
			apuestaAnterior *= 2;
			System.out.println(nombre + " haciendo apuesta al número " + numeroElegido + " por una cantidad de " + apuestaAnterior + "€.");
		}
	}

	@Override
	public boolean puedeApostar() {
		return (saldo >= apuestaAnterior * 2);
	}

}
