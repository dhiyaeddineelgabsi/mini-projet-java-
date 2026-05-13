package indexeur;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nom.Nom;

public class MainIndexeur {

    public static void main(String[] args) {
        List<Nom> noms = exemplesNoms();

        Indexeur<String> indexeurTokenHashMap = new IndexeurParTokenHashMap();
        Map<String, List<Nom>> indexTokenHashMap = indexeurTokenHashMap.indexer(noms);
        check(indexTokenHashMap.get("ahmed").size() == 2,
                "IndexeurParTokenHashMap indexes token occurrences");
        check(indexeurTokenHashMap.indexer(null).isEmpty(),
                "IndexeurParTokenHashMap accepts null list");

        Indexeur<String> indexeurTokenTreeMap = new IndexeurParTokenTreeMap();
        Map<String, List<Nom>> indexTokenTreeMap = indexeurTokenTreeMap.indexer(noms);
        check(indexTokenTreeMap instanceof TreeMap,
                "IndexeurParTokenTreeMap returns TreeMap");
        check(indexTokenTreeMap.get("ali").size() == 2,
                "IndexeurParTokenTreeMap indexes token occurrences");

        Indexeur<Integer> indexeurNombreHashMap = new IndexeurParNombreTokensHashMap();
        Map<Integer, List<Nom>> indexNombreHashMap = indexeurNombreHashMap.indexer(noms);
        check(indexNombreHashMap.get(2).size() == 2,
                "IndexeurParNombreTokensHashMap groups by token count");

        Indexeur<Integer> indexeurNombreTreeMap = new IndexeurParNombreTokensTreeMap();
        Map<Integer, List<Nom>> indexNombreTreeMap = indexeurNombreTreeMap.indexer(noms);
        check(indexNombreTreeMap instanceof TreeMap,
                "IndexeurParNombreTokensTreeMap returns TreeMap");
        check(indexNombreTreeMap.get(3).size() == 1,
                "IndexeurParNombreTokensTreeMap groups by token count");

        System.out.println("OK indexeur: all tests passed");
    }

    private static List<Nom> exemplesNoms() {
        Nom n1 = new Nom(1, "Ahmed Ben Ali");
        n1.setTokens(Arrays.asList("ahmed", "ben", "ali"));

        Nom n2 = new Nom(2, "Ali Ahmed");
        n2.setTokens(Arrays.asList("ali", "ahmed"));

        Nom n3 = new Nom(3, "Jean Dupont");
        n3.setTokens(Arrays.asList("jean", "dupont"));

        return Arrays.asList(n1, n2, n3, new Nom(4, "Non Tokenise"));
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
