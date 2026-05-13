import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import generator.GenerateurCandidatCartesien;
import generator.GenerateurCandidatLongeurN;
import nom.CoupleNom;
import nom.Nom;

public class MainTesterGenerateur {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter n : ");
        String input = scanner.nextLine();

        int n = Integer.parseInt(input);

        GenerateurCandidatCartesien gen1 = new GenerateurCandidatCartesien();
        GenerateurCandidatLongeurN gen2 = new GenerateurCandidatLongeurN(n);

        Nom nomRecherche = new Nom(1, "Ahmed Ben Ali", "clients.csv");

        List<Nom> watchList = new ArrayList<>();

        watchList.add(new Nom(101, "Ahmed Benali", "sanctions.csv"));
        watchList.add(new Nom(102, "Jean Dupont", "sanctions.csv"));
        watchList.add(new Nom(103, "Francois Muller", "pep.csv"));
        watchList.add(new Nom(104, "Maria Garcia", "pep.csv"));
        watchList.add(new Nom(105, "Ali Ahmed", "gel_avoirs.csv"));
        watchList.add(new Nom(106, "Mohamed Salah", "sanctions.csv"));
        watchList.add(new Nom(107, "Ahmed Ben Ali", "pep.csv"));
        watchList.add(new Nom(108, "John Smith", "sanctions.csv"));
        watchList.add(new Nom(109, "Youssef Trabelsi", "pep.csv"));
        watchList.add(new Nom(110, "Hassan Ali", "gel_avoirs.csv"));

        List<CoupleNom> candidatsCartesien = gen1.genererCandidats(nomRecherche, watchList);
        List<CoupleNom> candidatsLongueur = gen2.genererCandidats(nomRecherche, watchList);

        System.out.println("=== Générateur cartésien ===");
        System.out.println("Nombre de candidats: " + candidatsCartesien.size());

        for (CoupleNom couple : candidatsCartesien) {
            System.out.println(
                    couple.getNom1().getNom()
                            + " <-> "
                            + couple.getNom2().getNom()
                            + " | source: "
                            + couple.getNom2().getSourceList()
            );
        }

        System.out.println();

        System.out.println("=== Générateur longueur N ===");
        System.out.println("Nombre de candidats: " + candidatsLongueur.size());

        for (CoupleNom couple : candidatsLongueur) {
            System.out.println(
                    couple.getNom1().getNom()
                            + " <-> "
                            + couple.getNom2().getNom()
                            + " | source: "
                            + couple.getNom2().getSourceList()
            );
        }

        scanner.close();
    }
}