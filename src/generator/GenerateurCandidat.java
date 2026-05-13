package generator;
import java.util.List;
import nom.*;

public interface GenerateurCandidat {
    List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> watchList);
}