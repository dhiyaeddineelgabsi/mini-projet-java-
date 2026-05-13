package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import indexeur.Indexeur;
import indexeur.IndexeurParTokenHashMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatToken implements GenerateurCandidat {

    private final int seuilTokensCommuns;
    private final Indexeur<String> indexeur;

    public GenerateurCandidatToken() {
        this(1);
    }

    public GenerateurCandidatToken(int seuilTokensCommuns) {
        this(new IndexeurParTokenHashMap(), seuilTokensCommuns);
    }

    public GenerateurCandidatToken(Indexeur<String> indexeur) {
        this(indexeur, 1);
    }

    public GenerateurCandidatToken(Indexeur<String> indexeur, int seuilTokensCommuns) {
        if (seuilTokensCommuns < 1) {
            throw new IllegalArgumentException("Le seuil doit etre >= 1");
        }
        if (indexeur == null) {
            throw new IllegalArgumentException("L'indexeur ne peut pas etre null");
        }

        this.indexeur = indexeur;
        this.seuilTokensCommuns = seuilTokensCommuns;
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> watchList) {
        List<Nom> nomsRecherches = new ArrayList<>();
        if (nomRecherche != null) {
            nomsRecherches.add(nomRecherche);
        }
        return genererCandidats(nomsRecherches, watchList);
    }

    public List<CoupleNom> genererCandidats(List<Nom> nomsRecherches, List<Nom> watchList) {
        List<CoupleNom> candidats = new ArrayList<>();
        if (nomsRecherches == null || watchList == null) return candidats;

        Map<String, List<Nom>> indexParToken = indexeur.indexer(watchList);

        for (Nom n1 : nomsRecherches) {
            candidats.addAll(
                    GenerateurCandidatTokenIndexe.genererDepuisIndex(
                            n1,
                            indexParToken,
                            seuilTokensCommuns,
                            false
                    )
            );
        }
        return candidats;
    }
}
