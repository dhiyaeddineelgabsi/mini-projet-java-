package generator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nom.*;

import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatUnTokenCommun implements GenerateurCandidat {

    private Map<String, List<Nom>> indexParToken;

    public GenerateurCandidatUnTokenCommun(Map<String, List<Nom>> indexParToken) {
        this.indexParToken = indexParToken;
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelectionnee) {
        List<CoupleNom> candidats = new ArrayList<>();

        if (!nomRecherche.estTokenise()) {
            return candidats;
        }

        Set<Nom> nomsDejaAjoutes = new HashSet<>();
        Set<String> tokensRechercheUniques = new HashSet<>(nomRecherche.getTokensPretraites());

        for (String token : tokensRechercheUniques) {
            List<Nom> nomsAvecToken = indexParToken.get(token);

            if (nomsAvecToken == null) {
                continue;
            }

            for (Nom target : nomsAvecToken) {
                if (nomsDejaAjoutes.add(target)) {
                    candidats.add(new CoupleNom(nomRecherche, target));
                }
            }
        }

        return candidats;
    }
}