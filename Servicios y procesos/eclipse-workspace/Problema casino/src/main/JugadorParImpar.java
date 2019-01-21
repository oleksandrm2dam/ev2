package main;

public class JugadorParImpar extends Jugador {

	private boolean elegidoPar;
	
	public JugadorParImpar(Casino casino, long saldo) {
		super(casino, saldo);
	}

	@Override
	public void comunicarNumero(int numeroGanador) {
		if(numeroGanador % 2 == 0) {
			if(elegidoPar) {
				casino.restarSaldo(20);
				saldo += 20;
			}
		} else {
			if(!elegidoPar) {
				casino.restarSaldo(20);
				saldo += 20;
			}
		}
		apuestaRealizada = false;
	}

	@Override
	public void hacerApuesta() {
		elegidoPar = random.nextBoolean();
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
