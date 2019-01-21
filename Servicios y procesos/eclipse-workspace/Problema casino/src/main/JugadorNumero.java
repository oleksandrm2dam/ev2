package main;

public class JugadorNumero extends Jugador {
	
	private int numeroElegido;
	
	public JugadorNumero(Casino casino, long saldo) {
		super(casino, saldo);
	}

	@Override
	public void comunicarNumero(int numeroGanador) {
		if(numeroGanador == numeroElegido) {
			long saldoGanadoReal = casino.restarSaldo(360);
			saldo += saldoGanadoReal;
			System.out.println(nombre + " ha ganado " + saldoGanadoReal + "€ eligiendo el número " + numeroElegido + ".");
		}
		apuestaRealizada = false;
		System.out.println("Saldo actual de " + nombre + ": " + saldo + "€.");
	}

	@Override
	public void hacerApuesta() {
		if(casino.aceptarApuesta(this)) {
			numeroElegido = random.nextInt(36) + 1;
			casino.sumarSaldo(10);
			saldo -= 10;
			apuestaRealizada = true;
			System.out.println(nombre + " haciendo apuesta al número " + numeroElegido + " por una cantidad de 10€.");
		}
		
	}

	@Override
	public boolean puedeApostar() {
		return (saldo >= 10);
	}

}
