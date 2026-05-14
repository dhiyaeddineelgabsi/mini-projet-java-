package comparateurs.CompNom;
import java.util.List;

import Tokenizeurs.TokeniseurSimple;
import comparateurs.CompChaine.ComparateurChaine;
import comparateurs.CompChaine.ComparateurChaineLevenshtein;
import nom.Nom;

public class ComparateurNomParMots implements ComparateurNom {
    private ComparateurChaine comparateurChaine;
    private TokeniseurSimple tokeniseur;

    public ComparateurNomParMots() {
        this(new ComparateurChaineLevenshtein());
    }

    public ComparateurNomParMots(ComparateurChaine comparateurChaine) {
        this.comparateurChaine = comparateurChaine;
        this.tokeniseur = new TokeniseurSimple();
    }

    @Override
    public double comparer(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return 0.0;

        List<String> mots1 = tokens(n1);
        List<String> mots2 = tokens(n2);
        if (mots1.isEmpty() || mots2.isEmpty()) return 0.0;

        double sens1 = moyenneDesMeilleursScores(mots1, mots2);
        double sens2 = moyenneDesMeilleursScores(mots2, mots1);
        return (sens1 + sens2) / 2.0;
    }

    private double moyenneDesMeilleursScores(List<String> base, List<String> comparaison) {
        double somme = 0.0;
        for (String mot : base) {
            double meilleur = 0.0;
            for (String autre : comparaison) {
                double score = comparateurChaine.comparer(mot, autre);
                if (score > meilleur) {
                    meilleur = score;
                }
            }
            somme += meilleur;
        }
        return somme / base.size();
    }

    private List<String> tokens(Nom nom) {
        if (nom.estTokenise()) {
            return nom.getTokensPretraites();
        }
        String valeur = nom.getNomPretraite();
        if (valeur == null || valeur.isEmpty()) {
            valeur = nom.getValeur();
        }
        return tokeniseur.tokeniser(valeur);
    }
}
