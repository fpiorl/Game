package org.example.gioco;

import java.util.ArrayList;
import java.util.List;

public class Collezione {
	private final List<Gioco> giochi = new ArrayList<>();

	public void aggiungi(Gioco gioco) {
		if (gioco == null) {
			throw new GiocoNonValidoException();
		}

		String titolo = gioco.getTitolo();
		if (titolo == null || titolo.isBlank()) {
			throw new TitoloNonValidoException();
		}

		boolean idPresente = giochi.stream().anyMatch(g -> g.getID() == gioco.getID());
		if (idPresente) {
			throw new IdGiaPresenteException(gioco.getID());
		}

		boolean titoloPresente = giochi.stream()
				.anyMatch(g -> g.getTitolo() != null && g.getTitolo().equalsIgnoreCase(titolo));
		if (titoloPresente) {
			throw new TitoloGiaPresenteException(titolo);
		}

		giochi.add(gioco);
	}

	public Gioco cercaId(int id) {
		Gioco trovato = giochi.stream()
				.filter(g -> g.getID() == id)
				.findFirst()
				.orElseThrow(() ->
					throw new IDNonTrovatoException(id)
				);

		return trovato;
	}

	public List<Gioco> cercaPrezzo(double prezzoMax) {
		return giochi.stream()
				.filter(g -> g.getPrezzo() < prezzoMax)
				.toList();
	}

	public List<GiocoTavolo> cercaNumeroGiocatori(int NumeroGiocatori) {
		return giochi.stream()
				.filter(g -> g instanceof GiocoTavolo)
				.map(g -> (GiocoTavolo) g)
				.filter(gt -> gt.getNumeroGiocatori() == NumeroGiocatori)
				.toList();
	}

	public void rimuoviId(int id) {
		boolean rimosso = giochi.removeIf(g -> g.getID() == id);
		if (!rimosso) {
			throw new IDNonTrovatoException(id);
		}
	}

	public void statistiche() {
		if (giochi.isEmpty()) {
			throw new CollezioneVuotaException();
		}

		int video = (int) giochi.stream().filter(g -> g instanceof GiocoVideo).count();
		int tavolo = (int) giochi.stream().filter(g -> g instanceof GiocoTavolo).count();
		Gioco piuCaro = giochi.stream()
				.reduce(null, (acc, g) -> acc == null || g.getPrezzo() > acc.getPrezzo() ? g : acc);

		if (piuCaro == null) {
			throw new CollezioneVuotaException();
		}
		double[] totali = giochi.stream().collect(
				() -> new double[] { 0.0, 0.0 },
				(acc, g) -> {
					acc[0] = acc[0] + g.getPrezzo();
					acc[1] = acc[1] + 1;
				},
				(a, b) -> {
					a[0] = a[0] + b[0];
					a[1] = a[1] + b[1];
				}
		);

		double mediaPrezzi = totali[0] / totali[1];

		System.out.println("video: " + video);
		System.out.println("tavolo: " + tavolo);
		System.out.println("piu caro: " + piuCaro.getTitolo() + " (" + piuCaro.getPrezzo() + ")");
		System.out.println("media prezzi: " + mediaPrezzi);
	}
}
