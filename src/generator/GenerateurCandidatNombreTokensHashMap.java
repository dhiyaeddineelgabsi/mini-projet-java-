package generator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nom.*;

public class GenerateurCandidatNombreTokensHashMap implements GenerateurCandidat {

    private Map<Integer, List<Nom>> index;
    private int marge;

    public GenerateurCandidatNombreTokensHashMap(Map<Integer, List<Nom>> index, int marge) {
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

        for (int nbTokens = min; nbTokens <= max; nbTokens++) {
            List<Nom> noms = index.get(nbTokens);

            if (noms == null) {
                continue;
            }

            for (Nom target : noms) {
                candidats.add(new CoupleNom(nomRecherche, target));
            }
        }

        return candidats;
    }
}