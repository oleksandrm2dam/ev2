package main;

public class JugadorNumero extends Jugador {
	
	private int numeroElegido;
	
	public JugadorNumero(Casino casino, long saldo) {
		super(casino, saldo);
	}

	@Override
	public void comunicarNumero(int numeroGanador) {
		if(numeroGanador == numeroElegido) {
			casino.restarSaldo(360);
			saldo += 360;
		}
		apuestaRealizada = false;
	}

	@Override
	public void hacerApuesta() {
		numeroElegido = random.nextInt(36) + 1;
		casino.sumarSaldo(10);
		saldo -= 10;
		apuestaRealizada = true;
		casino.aceptarApuesta(this);
	}

	@Override
	public boolean puedeApostar() {
		return (saldo >= 10);
	}

}
