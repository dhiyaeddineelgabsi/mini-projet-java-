package generator;
import java.util.ArrayList;
import java.util.List;

public class GenerateurCandidatCartesien implements GenerateurCandidat {

    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listselectionne) {
        List<CoupleNom> candidats = new ArrayList<>();


        for (Nom targetNom : listselectionne) {
            CoupleNom couple = new CoupleNom(nomRecherche, targetNom);
            candidats.add(couple);
        }

        return candidats;
    }
}