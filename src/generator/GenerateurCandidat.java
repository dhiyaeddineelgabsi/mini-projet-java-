package generator;

import java.util.List;

import nom.CoupleNom;
import nom.Nom;

public interface GenerateurCandidat {
    List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> watchList);
}