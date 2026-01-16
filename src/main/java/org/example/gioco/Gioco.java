package org.example.gioco;

public class Gioco {
	private static int prossimoId = 1;

	private final int ID;
	private final String Titolo;
	private int Anno;
	private double Prezzo;

	public Gioco(String Titolo, int Anno, double Prezzo) {
		this.ID = prossimoId++;
		this.Titolo = Titolo;
		setAnno(Anno);
		setPrezzo(Prezzo);
	}

	public void setAnno(int Anno) {
		if (Anno <= 1950 || Anno > 2026) {
			throw new IllegalArgumentException("anno non valido");
		}
		this.Anno = Anno;
	}

	public void setPrezzo(double Prezzo) {
		if (Prezzo <= 0) {
			throw new IllegalArgumentException("prezzo non valido");
		}
		this.Prezzo = Prezzo;
	}

	public int getID() {
		return ID;
	}

	public String getTitolo() {
		return Titolo;
	}

	public int getAnno() {
		return Anno;
	}

	public double getPrezzo() {
		return Prezzo;
	}
}
