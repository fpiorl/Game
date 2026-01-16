package org.example.gioco;

public class IDNonTrovatoException extends RuntimeException {
	public IDNonTrovatoException(int id) {
		super("id non trovato: " + id);
	}
}
