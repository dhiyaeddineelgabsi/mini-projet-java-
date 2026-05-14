package comparateurs.CompChaine;

public class ComparateurChaineJaroWinkler implements ComparateurChaine {

    @Override
    public double comparer(String s1, String s2) {
        if (s1 == null || s2 == null) return 0.0;
        if (s1.equals(s2)) return 100.0;
        if (s1.isEmpty() || s2.isEmpty()) return 0.0;

        double jaro = calculerJaro(s1, s2);
        int prefixe = longueurPrefixeCommun(s1, s2);
        double score = jaro + Math.min(0.1, 1.0 / Math.max(s1.length(), s2.length()))
                * prefixe * (1.0 - jaro);
        return score * 100.0;
    }

    private double calculerJaro(String s1, String s2) {
        int distance = Math.max(Math.max(s1.length(), s2.length()) / 2 - 1, 0);
        boolean[] vus1 = new boolean[s1.length()];
        boolean[] vus2 = new boolean[s2.length()];

        int matches = 0;
        for (int i = 0; i < s1.length(); i++) {
            int debut = Math.max(0, i - distance);
            int fin = Math.min(i + distance + 1, s2.length());
            for (int j = debut; j < fin; j++) {
                if (!vus2[j] && s1.charAt(i) == s2.charAt(j)) {
                    vus1[i] = true;
                    vus2[j] = true;
                    matches++;
                    break;
                }
            }
        }

        if (matches == 0) return 0.0;

        char[] m1 = new char[matches];
        char[] m2 = new char[matches];
        for (int i = 0, k = 0; i < s1.length(); i++) {
            if (vus1[i]) m1[k++] = s1.charAt(i);
        }
        for (int i = 0, k = 0; i < s2.length(); i++) {
            if (vus2[i]) m2[k++] = s2.charAt(i);
        }

        int transpositions = 0;
        for (int i = 0; i < matches; i++) {
            if (m1[i] != m2[i]) transpositions++;
        }

        return ((double) matches / s1.length()
                + (double) matches / s2.length()
                + (matches - transpositions / 2.0) / matches) / 3.0;
    }

    private int longueurPrefixeCommun(String s1, String s2) {
        int limite = Math.min(4, Math.min(s1.length(), s2.length()));
        int prefixe = 0;
        for (int i = 0; i < limite; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                prefixe++;
            } else {
                break;
            }
        }
        return prefixe;
    }
}
