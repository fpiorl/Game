package org.example.gioco;

public class TitoloGiaPresenteException extends RuntimeException {
	public TitoloGiaPresenteException(String titolo) {
		super("titolo gia presente: " + titolo);
	}
}
