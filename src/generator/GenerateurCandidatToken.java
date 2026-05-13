package generator;

import java.util.ArrayList;
import java.util.List;

import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatToken implements GenerateurCandidat {

    private final int seuilTokensCommuns;

    public GenerateurCandidatToken() {
        this.seuilTokensCommuns = 1; // default: at least 1 token in common
    }

    public GenerateurCandidatToken(int seuilTokensCommuns) {
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

        for (Nom n1 : nomsRecherches) {
            for (Nom n2 : watchList) {
                if (compterTokensCommuns(n1, n2) >= seuilTokensCommuns) {
                    candidats.add(new CoupleNom(n1, n2));
                }
            }
        }
        return candidats;
    }

    private int compterTokensCommuns(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return 0;
        if (!n1.estTokenise() || !n2.estTokenise()) return 0;
        List<String> t1 = new ArrayList<>(n1.getTokens());
        t1.retainAll(n2.getTokens());
        return t1.size();
    }
}

