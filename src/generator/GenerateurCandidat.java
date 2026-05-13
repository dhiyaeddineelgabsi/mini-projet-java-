package generator;

import java.util.List;
import nom.*;

import nom.CoupleNom;
import nom.Nom;

public interface GenerateurCandidat {
    List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> watchList);
}