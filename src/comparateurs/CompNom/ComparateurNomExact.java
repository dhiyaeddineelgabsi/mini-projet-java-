package comparateurs.CompNom;

import comparateurs.CompChaine.ComparateurChaineExact;
import nom.Nom;

public class ComparateurNomExact implements ComparateurNom {
    private ComparateurChaineExact comparateurChaine;

    public ComparateurNomExact() {
        this.comparateurChaine = new ComparateurChaineExact();
    }

    @Override
    public double comparer(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return 0.0;
        return comparateurChaine.comparer(chaine(n1), chaine(n2));
    }

    private String chaine(Nom nom) {
        if (nom.getNomPretraite() != null && !nom.getNomPretraite().isEmpty()) {
            return nom.getNomPretraite();
        }
        return nom.getValeur() == null ? "" : nom.getValeur();
    }
}
