package generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatIndexTokenHashMap implements GenerateurCandidat {

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelection) {
        List<CoupleNom> candidats = new ArrayList<>();

        if (nomRecherche == null || listeSelection == null || !nomRecherche.estTokenise()) {
            return candidats;
        }

        Map<String, Set<Nom>> index = construireIndex(listeSelection);

        Set<Nom> nomsCandidats = new HashSet<>();

        for (String token : nomRecherche.getTokensUniques()) {
            Set<Nom> nomsAvecCeToken = index.get(token);

            if (nomsAvecCeToken != null) {
                nomsCandidats.addAll(nomsAvecCeToken);
            }
        }

        for (Nom nom : nomsCandidats) {
            if (nom != null && nom != nomRecherche) {
                candidats.add(new CoupleNom(nomRecherche, nom));
            }
        }

        return candidats;
    }

    private Map<String, Set<Nom>> construireIndex(List<Nom> listeSelection) {
        Map<String, Set<Nom>> index = new HashMap<>();

        for (Nom nom : listeSelection) {
            if (nom == null || !nom.estTokenise()) {
                continue;
            }

            for (String token : nom.getTokensUniques()) {
                index.putIfAbsent(token, new HashSet<>());
                index.get(token).add(nom);
            }
        }

        return index;
    }
}