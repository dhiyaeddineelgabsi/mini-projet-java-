package tokenizeurs;

import java.util.ArrayList;
import java.util.List;

public class TokeniseurNGramme implements ITokeniseur {

    private final int n;

    public TokeniseurNGramme() {
        this(3);
    }

    public TokeniseurNGramme(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("La taille des n-grammes doit etre > 0");
        }

        this.n = n;
    }

    @Override
    public List<String> tokeniser(String chaine) {
        List<String> ngrammes = new ArrayList<>();

        if (chaine == null || chaine.trim().isEmpty()) {
            return ngrammes;
        }

        String[] mots = chaine.trim().split("\\s+");
        for (String mot : mots) {
            if (mot.length() < n) {
                ngrammes.add(mot);
            } else {
                for (int i = 0; i <= mot.length() - n; i++) {
                    ngrammes.add(mot.substring(i, i + n));
                }
            }
        }

        return ngrammes;
    }
}
