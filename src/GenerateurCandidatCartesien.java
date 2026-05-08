import java.util.ArrayList;
import java.util.List;

public class GenerateurCandidatCartesien implements GenerateurCandidat {

    public List<CoupleNom> genererCandidats(List<Nom> nomsRecherches, List<Nom> watchList) {
        List<CoupleNom> candidats = new ArrayList<>();

        for (Nom nomRecherche : nomsRecherches) {
            for (Nom nomWatchList : watchList) {
                CoupleNom couple = new CoupleNom(nomRecherche, nomWatchList);
                candidats.add(couple);
            }
        }

        return candidats;
    }
}