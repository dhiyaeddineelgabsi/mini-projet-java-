package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import nom.*;

public class MainTestCandidatsToken {

    public static void main(String[] args) {
        List<Nom> liste = new ArrayList<>();

        Nom n1 = new Nom(1, "Ahmed Ben Ali", "sanctions.csv");
        n1.setTokens(List.of("ahmed", "ben", "ali"));

        Nom n2 = new Nom(2, "Ahmed Benali", "pep.csv");
        n2.setTokens(List.of("ahmed", "benali"));

        Nom n3 = new Nom(3, "Jean Dupont", "pep.csv");
        n3.setTokens(List.of("jean", "dupont"));

        Nom n4 = new Nom(4, "Ali Ahmed", "gel_avoirs.csv");
        n4.setTokens(List.of("ali", "ahmed"));

        liste.add(n1);
        liste.add(n2);
        liste.add(n3);
        liste.add(n4);

        Nom recherche = new Nom(0, "Ahmed Ben Ali");
        recherche.setTokens(List.of("ahmed", "ben", "ali"));

        System.out.println("=== HashMap nombre tokens ===");

        IndexeurParNombreTokensHashMap indexeurHash = new IndexeurParNombreTokensHashMap();
        Map<Integer, List<Nom>> indexHash = indexeurHash.indexer(liste);

        GenerateurCandidat generateurHash =
                new GenerateurCandidatNombreTokensHashMap(indexHash, 1);

        List<CoupleNom> candidatsHash =
                generateurHash.genererCandidats(recherche, liste);

        for (CoupleNom c : candidatsHash) {
            System.out.println(c);
        }

        System.out.println("=== TreeMap nombre tokens ===");

        IndexeurParNombreTokensTreeMap indexeurTree = new IndexeurParNombreTokensTreeMap();
        TreeMap<Integer, List<Nom>> indexTree = indexeurTree.indexer(liste);

        GenerateurCandidat generateurTree =
                new GenerateurCandidatNombreTokensTreeMap(indexTree, 1);

        List<CoupleNom> candidatsTree =
                generateurTree.genererCandidats(recherche, liste);

        for (CoupleNom c : candidatsTree) {
            System.out.println(c);
        }

        System.out.println("=== Un token commun ===");

        IndexeurParTokenHashMap indexeurToken = new IndexeurParTokenHashMap();
        Map<String, List<Nom>> indexToken = indexeurToken.indexer(liste);

        GenerateurCandidat generateurToken =
                new GenerateurCandidatUnTokenCommun(indexToken);

        List<CoupleNom> candidatsToken =
                generateurToken.genererCandidats(recherche, liste);

        for (CoupleNom c : candidatsToken) {
            System.out.println(c);
        }
    }
}