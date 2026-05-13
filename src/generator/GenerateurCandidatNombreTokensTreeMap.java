package generator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatNombreTokensTreeMap implements GenerateurCandidat {

    private TreeMap<Integer, List<Nom>> index;
    private int marge;

    public GenerateurCandidatNombreTokensTreeMap(TreeMap<Integer, List<Nom>> index, int marge) {
        if (marge < 0) {
            throw new IllegalArgumentException("La marge doit être >= 0");
        }

        this.index = index;
        this.marge = marge;
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelectionnee) {
        List<CoupleNom> candidats = new ArrayList<>();

        if (!nomRecherche.estTokenise()) {
            return candidats;
        }

        int nbTokensRecherche = nomRecherche.getNombreTokens();

        int min = Math.max(0, nbTokensRecherche - marge);
        int max = nbTokensRecherche + marge;

        Map<Integer, List<Nom>> sousIndex = index.subMap(min, true, max, true);

        for (List<Nom> noms : sousIndex.values()) {
            for (Nom target : noms) {
                candidats.add(new CoupleNom(nomRecherche, target));
            }
        }

        return candidats;
    }
}