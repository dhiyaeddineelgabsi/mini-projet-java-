package generator;

import java.util.List;

import indexeur.Indexeur;
import indexeur.IndexeurParTokenHashMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatIndexTokenHashMap implements GenerateurCandidat {

    private final Indexeur<String> indexeur;

    public GenerateurCandidatIndexTokenHashMap() {
        this(new IndexeurParTokenHashMap());
    }

    public GenerateurCandidatIndexTokenHashMap(Indexeur<String> indexeur) {
        if (indexeur == null) {
            throw new IllegalArgumentException("L'indexeur ne peut pas etre null");
        }

        this.indexeur = indexeur;
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelection) {
        return GenerateurCandidatTokenIndexe.generer(
                nomRecherche,
                listeSelection,
                indexeur,
                1,
                true
        );
    }
}
