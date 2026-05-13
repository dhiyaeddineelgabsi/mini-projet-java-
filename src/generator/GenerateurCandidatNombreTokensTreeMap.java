package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import indexeur.Indexeur;
import indexeur.IndexeurParNombreTokensTreeMap;
import nom.CoupleNom;
import nom.Nom;

public class GenerateurCandidatNombreTokensTreeMap implements GenerateurCandidat {

    private final Indexeur<Integer> indexeur;
    private final NavigableMap<Integer, List<Nom>> index;
    private final int marge;

    public GenerateurCandidatNombreTokensTreeMap() {
        this(0);
    }

    public GenerateurCandidatNombreTokensTreeMap(int marge) {
        this(new IndexeurParNombreTokensTreeMap(), marge);
    }

    public GenerateurCandidatNombreTokensTreeMap(TreeMap<Integer, List<Nom>> index, int marge) {
        if (marge < 0) {
            throw new IllegalArgumentException("La marge doit etre >= 0");
        }

        this.indexeur = null;
        this.index = index;
        this.marge = marge;
    }

    public GenerateurCandidatNombreTokensTreeMap(Indexeur<Integer> indexeur, int marge) {
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

        NavigableMap<Integer, List<Nom>> indexActif = index;

        if (indexActif == null) {
            indexActif = new TreeMap<>(indexeur.indexer(listeSelectionnee));
        }

        int nbTokensRecherche = nomRecherche.getNombreTokens();
        int min = Math.max(0, nbTokensRecherche - marge);
        int max = nbTokensRecherche + marge;

        Map<Integer, List<Nom>> sousIndex = indexActif.subMap(min, true, max, true);

        for (List<Nom> noms : sousIndex.values()) {
            for (Nom target : noms) {
                candidats.add(new CoupleNom(nomRecherche, target));
            }
        }

        return candidats;
    }
}
