package org.example.gioco;

public class TitoloNonValidoException extends RuntimeException {
	public TitoloNonValidoException() {
		super("titolo non valido");
	}
}
