package org.example.gioco;

public class GiocoTavolo extends Gioco {
	private int NumeroGiocatori;
	private int DurataMediaPartitaMinuti;

	public GiocoTavolo(
			String Titolo,
			int Anno,
			double Prezzo,
			int NumeroGiocatori,
			int DurataMediaPartitaMinuti
		) {
		super(Titolo, Anno, Prezzo);
		setNumeroGiocatori(NumeroGiocatori);
		setDurataMediaPartitaMinuti(DurataMediaPartitaMinuti);
	}

	public void setDurataMediaPartitaMinuti(int DurataMediaPartitaMinuti) {
		if (DurataMediaPartitaMinuti <= 0) {
			throw new IllegalArgumentException("durata partita non valida");
		}
		this.DurataMediaPartitaMinuti = DurataMediaPartitaMinuti;
	}

	public void setNumeroGiocatori(int NumeroGiocatori) {
		if (NumeroGiocatori < 2 || NumeroGiocatori > 10) {
			throw new IllegalArgumentException("numero giocatori non valido");
		}
		this.NumeroGiocatori = NumeroGiocatori;
	}

	public int getNumeroGiocatori() {
		return NumeroGiocatori;
	}

	public int getDurataMediaPartitaMinuti() {
		return DurataMediaPartitaMinuti;
	}
}
