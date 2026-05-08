/*
public class ComparateurNom {

    private final ComparateurChaine comparateurChaine;

    public ComparateurNom() {
        this.comparateurChaine = new ComparateurChaine();
    }

    public ComparateurNom(ComparateurChaine comparateurChaine) {
        this.comparateurChaine = comparateurChaine;
    }

    public double comparerNom(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return 0.0;

        String s1 = resolveChaine(n1);
        String s2 = resolveChaine(n2);

        if (s1.isEmpty() || s2.isEmpty()) return 0.0;

        return comparateurChaine.comparerChaine(s1, s2);
    }

    private String resolveChaine(Nom n) {
        String pretraite = n.getNomPretraite();
        if (pretraite != null && !pretraite.isEmpty()) return pretraite;
        return n.getNom() != null ? n.getNom() : "";
    }

    public double[] comparerNomDetail(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return new double[]{0, 0, 0, 0};

        String s1 = resolveChaine(n1);
        String s2 = resolveChaine(n2);

        double jw  = comparateurChaine.jaroWinkler(s1, s2);
        double lev = comparateurChaine.levenshteinNormalise(s1, s2);
        double sdx = comparateurChaine.soundexScore(s1, s2);
        double global = comparateurChaine.comparerChaine(s1, s2);

        return new double[]{jw, lev, sdx, global};
    }
}


 */