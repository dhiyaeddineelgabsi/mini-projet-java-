import java.util.List;

public interface GenerateurCandidat {
    List<CoupleNom> genererCandidats(List<Nom> nomsRecherches, List<Nom> watchList);
}