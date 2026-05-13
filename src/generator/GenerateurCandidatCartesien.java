package generator;
import java.util.ArrayList;
import java.util.List;
import nom.*;

public class GenerateurCandidatCartesien implements GenerateurCandidat {

    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listselectionne) {
        List<CoupleNom> candidats = new ArrayList<>();

         if (nomRecherche == null || listselectionne == null) {
            return candidats;
        }
        //si le nom vide ou liste vide retourner liste vide
        for (Nom targetNom : listselectionne) {
            CoupleNom couple = new CoupleNom(nomRecherche, targetNom);
            candidats.add(couple);
        }

        return candidats;
    }
}