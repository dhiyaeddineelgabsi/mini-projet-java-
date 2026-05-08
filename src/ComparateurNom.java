import java.util.ArrayList;
import java.util.List;

public class ComparateurNom {

    private final ComparateurChaine comparateurChaine;
    private final ITokeniseur tokeniseur;

    public ComparateurNom() {
        this.comparateurChaine = new ComparateurChaine();
        this.tokeniseur = new TokeniseurSimple();
    }

    public ComparateurNom(ComparateurChaine comparateurChaine) {
        this.comparateurChaine = comparateurChaine;
        this.tokeniseur = new TokeniseurSimple();
    }

    public ComparateurNom(ComparateurChaine comparateurChaine, ITokeniseur tokeniseur) {
        this.comparateurChaine = comparateurChaine;
        this.tokeniseur = tokeniseur;
    }

    public double comparerNom(Nom n1, Nom n2) {
        if (n1 == null || n2 == null) return 0.0;

        String s1 = resolveChaine(n1);
        String s2 = resolveChaine(n2);

        if (s1.isEmpty() || s2.isEmpty()) return 0.0;

        double scoreChaine = comparateurChaine.comparerChaine(s1, s2);
        double scoreSort   = tokenSortScore(n1, n2);
        double scoreSet    = tokenSetScore(n1, n2);

        return 0.6 * scoreChaine + 0.25 * scoreSort + 0.15 * scoreSet;
    }

    private String resolveChaine(Nom n) {
        String pretraite = n.getDernierNomPretraite();
        if (pretraite != null && !pretraite.isEmpty()) return pretraite;
        return n.getNom() != null ? n.getNom() : "";
    }

    public double tokenSortScore(Nom n1, Nom n2) {
        List<String> t1 = resolveTokens(n1);
        List<String> t2 = resolveTokens(n2);
        if (t1.isEmpty() || t2.isEmpty()) return 0.0;

        java.util.Collections.sort(t1);
        java.util.Collections.sort(t2);

        String s1 = String.join(" ", t1);
        String s2 = String.join(" ", t2);
        return comparateurChaine.comparerChaine(s1, s2);
    }

    public double tokenSetScore(Nom n1, Nom n2) {
        List<String> t1 = new ArrayList<>(resolveTokens(n1));
        List<String> t2 = new ArrayList<>(resolveTokens(n2));
        if (t1.isEmpty() || t2.isEmpty()) return 0.0;

        List<String> intersection = new ArrayList<>(t1);
        intersection.retainAll(t2);

        List<String> reste1 = new ArrayList<>(t1);
        reste1.removeAll(intersection);

        List<String> reste2 = new ArrayList<>(t2);
        reste2.removeAll(intersection);

        java.util.Collections.sort(intersection);
        java.util.Collections.sort(reste1);
        java.util.Collections.sort(reste2);

        String sInter   = String.join(" ", intersection);
        String sInterR1 = (sInter + " " + String.join(" ", reste1)).trim();
        String sInterR2 = (sInter + " " + String.join(" ", reste2)).trim();

        double score1 = comparateurChaine.comparerChaine(sInter, sInterR1);
        double score2 = comparateurChaine.comparerChaine(sInter, sInterR2);
        double score3 = comparateurChaine.comparerChaine(sInterR1, sInterR2);

        return Math.max(Math.max(score1, score2), score3);
    }

    private List<String> resolveTokens(Nom n) {
        if (n.estTokenise()) return new ArrayList<>(n.getTokens());
        String chaine = resolveChaine(n);
        return tokeniseur.tokeniser(chaine);
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
