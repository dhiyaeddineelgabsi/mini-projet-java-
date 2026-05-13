package generator;

import java.util.List;

import indexeur.Indexeur;
import indexeur.IndexeurParTokenHashMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatIndexDeuxTokensHashMap implements GenerateurCandidat {

    private final Indexeur<String> indexeur;
    private final int seuilTokensCommuns;

    public GenerateurCandidatIndexDeuxTokensHashMap() {
        this(2);
    }

    public GenerateurCandidatIndexDeuxTokensHashMap(int seuilTokensCommuns) {
        this(new IndexeurParTokenHashMap(), seuilTokensCommuns);
    }

    public GenerateurCandidatIndexDeuxTokensHashMap(Indexeur<String> indexeur, int seuilTokensCommuns) {
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
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelection) {
        return GenerateurCandidatTokenIndexe.generer(
                nomRecherche,
                listeSelection,
                indexeur,
                seuilTokensCommuns,
                true
        );
    }
}
