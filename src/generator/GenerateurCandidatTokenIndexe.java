package generator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import indexeur.Indexeur;
import nom.CoupleNom;
import nom.Nom;

final class GenerateurCandidatTokenIndexe {

    private GenerateurCandidatTokenIndexe() {
    }

    static List<CoupleNom> generer(Nom nomRecherche,
                                   List<Nom> listeSelection,
                                   Indexeur<String> indexeur,
                                   int seuilTokensCommuns,
                                   boolean ignorerNomRecherche) {
        if (indexeur == null) {
            throw new IllegalArgumentException("L'indexeur ne peut pas etre null");
        }

        Map<String, List<Nom>> indexParToken = indexeur.indexer(listeSelection);
        return genererDepuisIndex(nomRecherche, indexParToken, seuilTokensCommuns, ignorerNomRecherche);
    }

    static List<CoupleNom> genererDepuisIndex(Nom nomRecherche,
                                              Map<String, List<Nom>> indexParToken,
                                              int seuilTokensCommuns,
                                              boolean ignorerNomRecherche) {
        List<CoupleNom> candidats = new ArrayList<>();

        if (nomRecherche == null || indexParToken == null || !nomRecherche.estTokenise()) {
            return candidats;
        }

        Map<Nom, Integer> compteurTokensCommuns = new LinkedHashMap<>();

        for (String token : nomRecherche.getTokensUniques()) {
            List<Nom> nomsAvecToken = indexParToken.get(token);

            if (nomsAvecToken == null) {
                continue;
            }

            for (Nom nom : nomsAvecToken) {
                if (nom == null || (ignorerNomRecherche && nom.equals(nomRecherche))) {
                    continue;
                }

                compteurTokensCommuns.put(
                        nom,
                        compteurTokensCommuns.getOrDefault(nom, 0) + 1
                );
            }
        }

        for (Map.Entry<Nom, Integer> entry : compteurTokensCommuns.entrySet()) {
            if (entry.getValue() >= seuilTokensCommuns) {
                candidats.add(new CoupleNom(nomRecherche, entry.getKey()));
            }
        }

        return candidats;
    }
}
