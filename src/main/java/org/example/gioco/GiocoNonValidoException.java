package org.example.gioco;

public class GiocoNonValidoException extends RuntimeException {
	public GiocoNonValidoException() {
		super("gioco non valido");
	}
}
