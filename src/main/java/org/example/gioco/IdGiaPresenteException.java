package org.example.gioco;

public class IdGiaPresenteException extends RuntimeException {
	public IdGiaPresenteException(int id) {
		super("id gia presente: " + id);
	}
}
