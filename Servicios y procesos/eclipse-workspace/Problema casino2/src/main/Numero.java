package main;

public class Numero {
	
	private int numero;
	private String color;
	
	public Numero(int numero) {
		this.numero = numero;
		this.color = null;
	}
	
	public Numero(int numero, String color) {
		this.numero = numero;
		this.color = color;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Numero [numero=" + numero + ", color=" + color + "]";
	}
	
}
