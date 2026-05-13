package generator;
import java.util.ArrayList;
import java.util.List;

import nom.CoupleNom;
import nom.Nom;

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