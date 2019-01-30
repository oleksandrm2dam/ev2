package main;

public class JugadorRojoNegro extends Jugador {

	public boolean elegidoRojo;
	
	public JugadorRojoNegro(Casino casino, long saldo) {
		super(casino, saldo);
	}

	@Override
	public void comunicarNumero(Numero numeroGanador) {
		if(numeroGanador.getColor().equals("Rojo")) {
			if(elegidoRojo) {
				long saldoGanadoReal = casino.restarSaldo(20);
				saldo += saldoGanadoReal;
				System.out.println(nombre + " ha ganado " + saldoGanadoReal + "€ eligiendo un número rojo.");
			}
		} else {
			if(!elegidoRojo) {
				long saldoGanadoReal = casino.restarSaldo(20);
				saldo += saldoGanadoReal;
				System.out.println(nombre + " ha ganado " + saldoGanadoReal + "€ eligiendo un número negro.");
			}
		}
		apuestaRealizada = false;
		System.out.println("Saldo actual de " + nombre + ": " + saldo + "€.");
	}

	@Override
	public void hacerApuesta() {
		if(casino.aceptarApuesta(this, 20)) {
			elegidoRojo = random.nextBoolean();
			casino.sumarSaldo(10);
			saldo -= 10;
			apuestaRealizada = true;
			
			if(elegidoRojo) {
				System.out.println(nombre + " haciendo apuesta a números rojos por una cantidad de 10€.");
			} else {
				System.out.println(nombre + " haciendo apuesta a números negros por una cantidad de 10€.");
			}
		}
	}

	@Override
	public boolean puedeApostar() {
		return (saldo >= 10);
	}

}
