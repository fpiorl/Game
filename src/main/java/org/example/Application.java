package org.example;

import org.example.gioco.Collezione;
import org.example.gioco.Gioco;
import org.example.gioco.GiocoTavolo;
import org.example.gioco.GiocoVideo;

import java.util.List;
import java.util.Scanner;

public class Application {

    private static final List<String> PIATTAFORME = List.of("PC", "PS5", "XBOX");

    public static void main(String[] args) {
        Collezione collezione = new Collezione();

        try (Scanner scanner = new Scanner(System.in)) {
            int scelta;
            do {
                stampaMenu();
                scelta = leggiIntero(scanner, "Scelta: ");

                try {
                    switch (scelta) {
                        case 0 -> System.out.println("Uscita...");
                        case 1 -> aggiungiGioco(scanner, collezione);
                        case 2 -> cercaPerId(scanner, collezione);
                        case 3 -> cercaPerPrezzo(scanner, collezione);
                        case 4 -> rimuoviPerId(scanner, collezione);
                        case 5 -> collezione.statistiche();
                        case 6 -> listaTutti(collezione);
                        default -> System.out.println("Scelta non valida.");
                    }
                } catch (RuntimeException ex) {
                    System.out.println("Errore: " + ex);
                }

                System.out.println();
            } while (scelta != 0);
        }
    }

    private static void stampaMenu() {
        System.out.println("ESEGUI OPERAZIONE");
        System.out.println("0) Esci");
        System.out.println("1) Aggiungi un gioco");
        System.out.println("2) Cerca gioco per ID");
        System.out.println("3) Cerca giochi per prezzo massimo");
        System.out.println("4) Rimuovi gioco per ID");
        System.out.println("5) Statistiche");
        System.out.println("6) Lista tutti i giochi");
    }

    private static void aggiungiGioco(Scanner scanner, Collezione collezione) {
        System.out.println("Tipo di gioco:");
        System.out.println("1) Videogioco");
        System.out.println("2) Gioco da tavolo");
        int tipo = leggiIntero(scanner, "Scelta tipo: ");

        Gioco gioco = switch (tipo) {
            case 1 -> leggiGiocoVideo(scanner);
            case 2 -> leggiGiocoTavolo(scanner);
            default -> throw new IllegalArgumentException("tipo non valido");
        };

        collezione.aggiungi(gioco);
        System.out.println("Gioco aggiunto. ID assegnato: " + gioco.getID());
    }

    private static GiocoVideo leggiGiocoVideo(Scanner scanner) {
        String titolo = leggiStringaNonVuota(scanner, "Titolo: ");
        int anno = leggiIntero(scanner, "Anno: ");
        double prezzo = leggiDouble(scanner, "Prezzo: ");
        String piattaforma = leggiPiattaforma(scanner);
        int durata = leggiIntero(scanner, "Durata (ore): ");
        GiocoVideo.Genere genere = leggiGenere(scanner);

        return new GiocoVideo(titolo, anno, prezzo, piattaforma, durata, genere);
    }

    private static GiocoTavolo leggiGiocoTavolo(Scanner scanner) {
        String titolo = leggiStringaNonVuota(scanner, "Titolo: ");
        int anno = leggiIntero(scanner, "Anno: ");
        double prezzo = leggiDouble(scanner, "Prezzo: ");
        int numeroGiocatori = leggiIntero(scanner, "Numero giocatori (2-10): ");
        int durataMedia = leggiIntero(scanner, "Durata media partita (minuti): ");

        return new GiocoTavolo(titolo, anno, prezzo, numeroGiocatori, durataMedia);
    }

    private static void cercaPerId(Scanner scanner, Collezione collezione) {
        int id = leggiIntero(scanner, "ID: ");
        Gioco gioco = collezione.cercaId(id);
        stampaGioco(gioco);
    }

    private static void cercaPerPrezzo(Scanner scanner, Collezione collezione) {
        double prezzoMax = leggiDouble(scanner, "Prezzo massimo: ");
        List<Gioco> risultati = collezione.cercaPrezzo(prezzoMax);

        if (risultati.isEmpty()) {
            System.out.println("Nessun gioco trovato.");
            return;
        }

        risultati.forEach(Application::stampaGioco);
    }

    private static void rimuoviPerId(Scanner scanner, Collezione collezione) {
        int id = leggiIntero(scanner, "ID da rimuovere: ");
        collezione.rimuoviId(id);
        System.out.println("Gioco rimosso.");
    }

    private static void listaTutti(Collezione collezione) {
        List<Gioco> tutti = collezione.cercaPrezzo(Double.MAX_VALUE);
        if (tutti.isEmpty()) {
            System.out.println("Collezione vuota.");
            return;
        }

        tutti.forEach(Application::stampaGioco);
    }

    private static void stampaGioco(Gioco gioco) {
        System.out.println("ID: " + gioco.getID());
        System.out.println("Titolo: " + gioco.getTitolo());
        System.out.println("Anno: " + gioco.getAnno() + " d.C.");
        System.out.println("Prezzo: " + gioco.getPrezzo() + " euro");

        if (gioco instanceof GiocoVideo gv) {
            System.out.println("Tipo: Videogioco");
            System.out.println("Piattaforma: " + gv.getPiattaforma());
            System.out.println("Durata: " + gv.getDurata() + " ore");
            System.out.println("Genere: " + gv.getGenere());
        } else if (gioco instanceof GiocoTavolo gt) {
            System.out.println("Tipo: Gioco da tavolo");
            System.out.println("Numero giocatori: " + gt.getNumeroGiocatori());
            System.out.println("Durata media: " + gt.getDurataMediaPartitaMinuti() + " minuti");
        }
    }

    private static int leggiIntero(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Inserisci un numero intero valido.");
            }
        }
    }

    private static double leggiDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(',', '.');

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.println("Inserisci un numero valido (es. 12.50).");
            }
        }
    }

    private static String leggiStringaNonVuota(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.isBlank()) {
                return input.trim();
            }
            System.out.println("Valore non valido.");
        }
    }

    private static String leggiPiattaforma(Scanner scanner) {
        System.out.println("Piattaforme disponibili: " + PIATTAFORME);

        while (true) {
            System.out.print("Piattaforma: ");
            String input = scanner.nextLine().trim().toUpperCase();

            for (String piattaforma : PIATTAFORME) {
                if (piattaforma.equals(input)) {
                    return piattaforma;
                }
            }

            System.out.println("Valore non valido.");
        }
    }

    private static GiocoVideo.Genere leggiGenere(Scanner scanner) {
        while (true) {
            System.out.println("Genere:");
            System.out.println("1) AZIONE");
            System.out.println("2) AVVENTURA");
            System.out.println("3) RPG");
            System.out.println("4) STRATEGIA");
            System.out.println("5) SPORT");
            System.out.print("Scelta genere: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    return GiocoVideo.Genere.AZIONE;
                case 2:
                    return GiocoVideo.Genere.AVVENTURA;
                case 3:
                    return GiocoVideo.Genere.RPG;
                case 4:
                    return GiocoVideo.Genere.STRATEGIA;
                case 5:
                    return GiocoVideo.Genere.SPORT;
                default:
                    System.out.println("Valore non valido.");
            }
        }
    }
}
