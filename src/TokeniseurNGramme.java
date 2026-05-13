import java.util.ArrayList;
import java.util.List;

import Tokenizeurs.ITokeniseur;

public class TokeniseurNGramme implements ITokeniseur {

    private final int n;

    public TokeniseurNGramme() {
        this.n = 3;
    }

    public TokeniseurNGramme(int n) {
        this.n = n;
    }

    @Override
    public List<String> tokeniser(String chaine) {
        if (chaine == null || chaine.isEmpty()) return new ArrayList<>();
        List<String> ngrams = new ArrayList<>();
        // apply ngrams on each token separately
        String[] mots = chaine.trim().split("\\s+");
        for (String mot : mots) {
            if (mot.length() < n) {
                ngrams.add(mot);
            } else {
                for (int i = 0; i <= mot.length() - n; i++) {
                    ngrams.add(mot.substring(i, i + n));
                }
            }
        }
        return ngrams;
    }
}

