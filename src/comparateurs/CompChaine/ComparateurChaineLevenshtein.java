package comparateurs.CompChaine;

public class ComparateurChaineLevenshtein implements ComparateurChaine {

    @Override
    public double comparer(String s1, String s2) {
        if (s1 == null || s2 == null) return 0.0;
        if (s1.equals(s2)) return 100.0;

        int max = Math.max(s1.length(), s2.length());
        if (max == 0) return 100.0;

        int distance = distanceLevenshtein(s1, s2);
        return 100.0 * (1.0 - (double) distance / max);
    }

    private int distanceLevenshtein(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cout = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cout
                );
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
