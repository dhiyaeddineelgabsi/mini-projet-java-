package generator;
import java.util.List;

import indexeur.Indexeur;
import nom.*;

public class GenerateurCandidatTokenBruteForce implements GenerateurCandidat {

    private final GenerateurCandidatToken generateurIndexe;

    public GenerateurCandidatTokenBruteForce() {
        this(1);
    }

    public GenerateurCandidatTokenBruteForce(int seuilTokensCommuns) {
        this.generateurIndexe = new GenerateurCandidatToken(seuilTokensCommuns);
    }

    public GenerateurCandidatTokenBruteForce(Indexeur<String> indexeur, int seuilTokensCommuns) {
        this.generateurIndexe = new GenerateurCandidatToken(indexeur, seuilTokensCommuns);
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomsRecherche, List<Nom> listeselection) {
        return generateurIndexe.genererCandidats(nomsRecherche, listeselection);
    }
}


