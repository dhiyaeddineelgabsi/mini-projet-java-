package generator;

import java.util.List;

import indexeur.Indexeur;
import indexeur.IndexeurParTokenTreeMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatIndexTokenTreeMap implements GenerateurCandidat {

    private final Indexeur<String> indexeur;

    public GenerateurCandidatIndexTokenTreeMap() {
        this(new IndexeurParTokenTreeMap());
    }

    public GenerateurCandidatIndexTokenTreeMap(Indexeur<String> indexeur) {
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
