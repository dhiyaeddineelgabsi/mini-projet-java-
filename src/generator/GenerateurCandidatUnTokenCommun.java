package generator;
import java.util.List;
import java.util.Map;

import indexeur.Indexeur;
import indexeur.IndexeurParTokenHashMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatUnTokenCommun implements GenerateurCandidat {

    private final Indexeur<String> indexeur;
    private final Map<String, List<Nom>> indexParToken;

    public GenerateurCandidatUnTokenCommun() {
        this(new IndexeurParTokenHashMap());
    }

    public GenerateurCandidatUnTokenCommun(Map<String, List<Nom>> indexParToken) {
        this.indexeur = null;
        this.indexParToken = indexParToken;
    }

    public GenerateurCandidatUnTokenCommun(Indexeur<String> indexeur) {
        if (indexeur == null) {
            throw new IllegalArgumentException("L'indexeur ne peut pas etre null");
        }

        this.indexeur = indexeur;
        this.indexParToken = null;
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelectionnee) {
        Map<String, List<Nom>> index = indexParToken;

        if (index == null) {
            index = indexeur.indexer(listeSelectionnee);
        }

        return GenerateurCandidatTokenIndexe.genererDepuisIndex(nomRecherche, index, 1, false);
    }
}
