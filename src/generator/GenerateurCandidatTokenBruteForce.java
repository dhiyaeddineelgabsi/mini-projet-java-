package generator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nom.*;

public class GenerateurCandidatTokenBruteForce implements GenerateurCandidat {

    private final int seuilTokensCommuns;

    public GenerateurCandidatTokenBruteForce() {
        this.seuilTokensCommuns = 1; 
    }

    public GenerateurCandidatTokenBruteForce(int seuilTokensCommuns) {
        this.seuilTokensCommuns = seuilTokensCommuns;
    }

    @Override

    public List<CoupleNom> genererCandidats(Nom nomsRecherche, List<Nom> listeselection) {
        List<CoupleNom> candidats = new ArrayList<>();
        if (nomsRecherche == null || listeselection == null) return candidats;

       
        for (Nom n2 : listeselection) {
            if (compterTokensCommuns(nomsRecherche, n2) >= seuilTokensCommuns) {
                candidats.add(new CoupleNom(nomsRecherche, n2));
            }
        }
        return candidats;
    }
    
    private int compterTokensCommuns(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return 0;
        if (!n1.estTokenise() || !n2.estTokenise()) return 0;
        Set<String> tokensCommuns = new HashSet<>(n1.getTokensUniques());
        tokensCommuns.retainAll(n2.getTokensUniques());
        return tokensCommuns.size();
    }
    
}


