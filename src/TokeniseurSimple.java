import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Tokenizeurs.ITokeniseur;

public class TokeniseurSimple implements ITokeniseur {
    @Override
    public List<String> tokeniser(String chaine) {
        if (chaine == null || chaine.isEmpty()) return new ArrayList<>();
        String[] parts = chaine.trim().split("\\s+");
        return new ArrayList<>(Arrays.asList(parts));
    }
}

