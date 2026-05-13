package generator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import indexeur.IndexeurParTokenHashMap;
import nom.CoupleNom;
import nom.Nom;

public class MainGenerateur {

    public static void main(String[] args) {
        Nom recherche = nom(0, "Ahmed Ben Ali", "ahmed", "ben", "ali");
        List<Nom> watchList = Arrays.asList(
                nom(1, "Ahmed Ben Ali", "ahmed", "ben", "ali"),
                nom(2, "Ali Ahmed", "ali", "ahmed"),
                nom(3, "Jean Dupont", "jean", "dupont"),
                nom(4, "Ahmed", "ahmed")
        );

        check(new GenerateurCandidatCartesien().genererCandidats(recherche, watchList).size() == 4,
                "GenerateurCandidatCartesien");
        check(new GenerateurCandidatLongeurN(1)
                        .genererCandidats(new Nom(10, "Ali"), Arrays.asList(new Nom(11, "Aly"), new Nom(12, "Jonathan")))
                        .size() == 1,
                "GenerateurCandidatLongeurN");

        check(new GenerateurCandidatToken().genererCandidats(recherche, watchList).size() == 3,
                "GenerateurCandidatToken default threshold");
        check(new GenerateurCandidatToken(2).genererCandidats(recherche, watchList).size() == 2,
                "GenerateurCandidatToken threshold");
        check(new GenerateurCandidatTokenBruteForce(2).genererCandidats(recherche, watchList).size() == 2,
                "GenerateurCandidatTokenBruteForce");
        check(new GenerateurCandidatUnTokenCommun().genererCandidats(recherche, watchList).size() == 3,
                "GenerateurCandidatUnTokenCommun");

        check(new GenerateurCandidatIndexTokenHashMap().genererCandidats(recherche, watchList).size() == 3,
                "GenerateurCandidatIndexTokenHashMap");
        check(new GenerateurCandidatIndexTokenTreeMap().genererCandidats(recherche, watchList).size() == 3,
                "GenerateurCandidatIndexTokenTreeMap");
        check(new GenerateurCandidatIndexDeuxTokensHashMap().genererCandidats(recherche, watchList).size() == 2,
                "GenerateurCandidatIndexDeuxTokensHashMap");
        check(new GenerateurCandidatIndexDeuxTokensTreeMap().genererCandidats(recherche, watchList).size() == 2,
                "GenerateurCandidatIndexDeuxTokensTreeMap");

        check(new GenerateurCandidatNombreTokensHashMap(0).genererCandidats(recherche, watchList).size() == 1,
                "GenerateurCandidatNombreTokensHashMap");
        check(new GenerateurCandidatNombreTokensTreeMap(0).genererCandidats(recherche, watchList).size() == 1,
                "GenerateurCandidatNombreTokensTreeMap");

        Map<String, List<Nom>> index = new IndexeurParTokenHashMap().indexer(watchList);
        List<CoupleNom> depuisIndex = GenerateurCandidatTokenIndexe.genererDepuisIndex(recherche, index, 2, false);
        check(depuisIndex.size() == 2, "GenerateurCandidatTokenIndexe");

        GenerateurCandidat generateurInterface = new GenerateurCandidatToken();
        check(generateurInterface.genererCandidats(recherche, watchList).size() == 3,
                "GenerateurCandidat interface");

        System.out.println("OK generator: all tests passed");
    }

    private static Nom nom(int id, String valeur, String... tokens) {
        Nom nom = new Nom(id, valeur);
        nom.setTokens(Arrays.asList(tokens));
        return nom;
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
