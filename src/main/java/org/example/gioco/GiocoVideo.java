package org.example.gioco;

public class GiocoVideo extends Gioco {
	public enum Piattaforma {
		PC,
		PS5,
		XBOX
	}

	public enum Genere {
		AZIONE,
		AVVENTURA,
		RPG,
		STRATEGIA,
		SPORT
	}

	private Piattaforma Piattaforma;
	private int Durata;
	private Genere Genere;

	public GiocoVideo(
			String Titolo,
			int Anno,
			double Prezzo,
			Piattaforma Piattaforma,
			int Durata,
			Genere Genere
			) {
		super(Titolo, Anno, Prezzo);
		setPiattaforma(Piattaforma);
		setDurata(Durata);
		setGenere(Genere);
			}

	public void setPiattaforma(Piattaforma Piattaforma) {
		if (Piattaforma == null) {
			throw new IllegalArgumentException("piattaforma non valida");
		}
		this.Piattaforma = Piattaforma;
	}

	public void setDurata(int Durata) {
		if (Durata <= 0) {
			throw new IllegalArgumentException("durata non valida");
		}
		this.Durata = Durata;
	}

	public void setGenere(Genere Genere) {
		if (Genere == null) {
			throw new IllegalArgumentException("genere non valido");
		}
		this.Genere = Genere;
	}

	public Piattaforma getPiattaforma() {
		return Piattaforma;
	}

	public int getDurata() {
		return Durata;
	}

	public Genere getGenere() {
		return Genere;
	}
}
