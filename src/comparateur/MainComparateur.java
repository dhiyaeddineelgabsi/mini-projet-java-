package comparateur;

import java.util.Arrays;

import nom.Nom;

public class MainComparateur {

    public static void main(String[] args) {
        ComparateurChaine comparateurChaine = new ComparateurChaine();
        check(comparateurChaine.comparerChaine("ahmed", "ahmed") == 1.0,
                "ComparateurChaine exact match");
        check(comparateurChaine.levenshteinNormalise("abc", "abc") == 1.0,
                "ComparateurChaine levenshtein exact match");
        check(comparateurChaine.jaroWinkler("ahmed", "ahmet") > 0.8,
                "ComparateurChaine jaro-winkler close strings");
        check(comparateurChaine.soundexScore("Robert", "Rupert") == 1.0,
                "ComparateurChaine soundex equivalent names");
        check(comparateurChaine.soundexScore("   ", "Robert") == 0.0,
                "ComparateurChaine soundex blank input");

        ComparateurNom comparateurNom = new ComparateurNom();
        Nom n1 = new Nom(1, "Ahmed Ali");
        n1.setTokens(Arrays.asList("ahmed", "ali"));
        Nom n2 = new Nom(2, "Ali Ahmed");
        n2.setTokens(Arrays.asList("ali", "ahmed"));

        check(comparateurNom.comparerNom(n1, n1) > 0.9, "ComparateurNom exact name");
        check(comparateurNom.tokenSortScore(n1, n2) == 1.0, "ComparateurNom token sort");
        check(comparateurNom.tokenSetScore(n1, n2) == 1.0, "ComparateurNom token set");
        check(comparateurNom.comparerNomDetail(n1, n2).length == 4,
                "ComparateurNom detail has four scores");

        System.out.println("OK comparateur: all tests passed");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
