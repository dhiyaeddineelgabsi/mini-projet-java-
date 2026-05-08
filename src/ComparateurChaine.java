// Sources: tdebatty/java-string-similarity (Apache 2.0), ssaurel/Soundex (public domain).
public class ComparateurChaine {

    // Poids des trois algorithmes (somme = 1.0)
    private static final double POIDS_JARO_WINKLER = 0.5;
    private static final double POIDS_LEVENSHTEIN  = 0.3;
    private static final double POIDS_SOUNDEX      = 0.2;

    // Constantes Jaro-Winkler (depuis tdebatty)
    private static final double SEUIL_JW  = 0.7;
    private static final double COEF_JW   = 0.1;
    private static final int    THREE     = 3;

    public ComparateurChaine() {}

    // Methode principale.

    public double comparerChaine(String s1, String s2) {
        if (s1 == null || s2 == null) return 0.0;
        if (s1.equals(s2)) return 1.0;
        if (s1.isEmpty() || s2.isEmpty()) return 0.0;

        double scoreJW  = jaroWinkler(s1, s2);
        double scoreLev = levenshteinNormalise(s1, s2);
        double scoreSdx = soundexScore(s1, s2);

        return POIDS_JARO_WINKLER * scoreJW
             + POIDS_LEVENSHTEIN  * scoreLev
             + POIDS_SOUNDEX      * scoreSdx;
    }

    // Jaro-Winkler (extrait de tdebatty/java-string-similarity).

    public double jaroWinkler(String s1, String s2) {
        if (s1.equals(s2)) return 1.0;

        int[] mtp = matchesJaro(s1, s2);
        double m = mtp[0];
        if (m == 0) return 0.0;

        double jaro = (m / s1.length()
                     + m / s2.length()
                     + (m - mtp[1]) / m) / THREE;

        double jw = jaro;
        if (jaro > SEUIL_JW) {
            jw = jaro + Math.min(COEF_JW, 1.0 / mtp[THREE])
                      * mtp[2] * (1 - jaro);
        }
        return jw;
    }

    // Retourne {matches, transpositions/2, prefix, maxLen}.
    private int[] matchesJaro(String s1, String s2) {
        String max = s1.length() > s2.length() ? s1 : s2;
        String min = s1.length() > s2.length() ? s2 : s1;

        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        java.util.Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;

        for (int mi = 0; mi < min.length(); mi++) {
            char c1 = min.charAt(mi);
            int start = Math.max(mi - range, 0);
            int end   = Math.min(mi + range + 1, max.length());
            for (int xi = start; xi < end; xi++) {
                if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                    matchIndexes[mi] = xi;
                    matchFlags[xi]   = true;
                    matches++;
                    break;
                }
            }
        }

        char[] ms1 = new char[matches];
        char[] ms2 = new char[matches];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (matchIndexes[i] != -1) ms1[si++] = min.charAt(i);
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (matchFlags[i]) ms2[si++] = max.charAt(i);
        }

        int transpositions = 0;
        for (int i = 0; i < ms1.length; i++) {
            if (ms1[i] != ms2[i]) transpositions++;
        }

        int prefix = 0;
        for (int i = 0; i < min.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) prefix++;
            else break;
        }

        return new int[]{matches, transpositions / 2, prefix, max.length()};
    }

    // Levenshtein normalise (extrait de tdebatty/java-string-similarity).

    public double levenshteinNormalise(String s1, String s2) {
        if (s1.equals(s2)) return 1.0;
        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0) return 1.0;
        return 1.0 - (double) distanceLevenshtein(s1, s2) / maxLen;
    }

    private int distanceLevenshtein(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cout = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cout
                );
            }
        }
        return dp[len1][len2];
    }

    // Soundex (extrait du gist ssaurel/Soundex).

    public double soundexScore(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) return 0.0;
        // Prendre le premier token si plusieurs mots
        String t1 = s1.split("\\s+")[0];
        String t2 = s2.split("\\s+")[0];
        return encoderSoundex(t1).equals(encoderSoundex(t2)) ? 1.0 : 0.0;
    }

    // Code Soundex sur 4 caracteres.
    public String encoderSoundex(String s) {
        if (s == null || s.isEmpty()) return "0000";
        char[] x = s.toUpperCase().toCharArray();
        String output = "" + x[0];

        // Remplacer chaque lettre par son chiffre Soundex
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'B': case 'F': case 'P': case 'V':
                    x[i] = '1'; break;
                case 'C': case 'G': case 'J': case 'K':
                case 'Q': case 'S': case 'X': case 'Z':
                    x[i] = '2'; break;
                case 'D': case 'T':
                    x[i] = '3'; break;
                case 'L':
                    x[i] = '4'; break;
                case 'M': case 'N':
                    x[i] = '5'; break;
                case 'R':
                    x[i] = '6'; break;
                default:
                    x[i] = '0'; break;
            }
        }

        // Supprimer les doublons adjacents et les zeros
        for (int i = 1; i < x.length; i++) {
            if (x[i] != x[i - 1] && x[i] != '0') {
                output += x[i];
            }
        }

        // Padder a 4 caracteres ou tronquer
        output = output + "0000";
        return output.substring(0, 4);
    }
}
