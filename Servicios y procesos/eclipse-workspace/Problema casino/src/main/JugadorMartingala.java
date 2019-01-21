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
			casino.restarSaldo(apuestaAnterior * 36);
			saldo += apuestaAnterior * 36;
			apuestaAnterior = 5;
		}
		apuestaRealizada = false;
	}

	@Override
	public void hacerApuesta() {
		numeroElegido = random.nextInt(36) + 1;
		casino.sumarSaldo(apuestaAnterior * 2);
		saldo -= apuestaAnterior * 2;
		apuestaRealizada = true;
		casino.aceptarApuesta(this);
		apuestaAnterior *= 2;
	}

	@Override
	public boolean puedeApostar() {
		return (saldo >= apuestaAnterior * 2);
	}

}
