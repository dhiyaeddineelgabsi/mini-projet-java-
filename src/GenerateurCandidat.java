import java.util.List;

public interface GenerateurCandidat {
    List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> watchList);
}