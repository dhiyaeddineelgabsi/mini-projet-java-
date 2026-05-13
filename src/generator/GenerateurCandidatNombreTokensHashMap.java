package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import indexeur.Indexeur;
import indexeur.IndexeurParNombreTokensHashMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatNombreTokensHashMap implements GenerateurCandidat {

    private final Indexeur<Integer> indexeur;
    private final Map<Integer, List<Nom>> index;
    private final int marge;

    public GenerateurCandidatNombreTokensHashMap() {
        this(0);
    }

    public GenerateurCandidatNombreTokensHashMap(int marge) {
        this(new IndexeurParNombreTokensHashMap(), marge);
    }

    public GenerateurCandidatNombreTokensHashMap(Map<Integer, List<Nom>> index, int marge) {
        if (marge < 0) {
            throw new IllegalArgumentException("La marge doit etre >= 0");
        }

        this.indexeur = null;
        this.index = index;
        this.marge = marge;
    }

    public GenerateurCandidatNombreTokensHashMap(Indexeur<Integer> indexeur, int marge) {
        if (marge < 0) {
            throw new IllegalArgumentException("La marge doit etre >= 0");
        }
        if (indexeur == null) {
            throw new IllegalArgumentException("L'indexeur ne peut pas etre null");
        }

        this.indexeur = indexeur;
        this.index = null;
        this.marge = marge;
    }

    @Override
    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listeSelectionnee) {
        List<CoupleNom> candidats = new ArrayList<>();

        if (nomRecherche == null || !nomRecherche.estTokenise()) {
            return candidats;
        }

        Map<Integer, List<Nom>> indexActif = index;

        if (indexActif == null) {
            indexActif = indexeur.indexer(listeSelectionnee);
        }

        int nbTokensRecherche = nomRecherche.getNombreTokens();
        int min = Math.max(0, nbTokensRecherche - marge);
        int max = nbTokensRecherche + marge;

        for (int nbTokens = min; nbTokens <= max; nbTokens++) {
            List<Nom> noms = indexActif.get(nbTokens);

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
