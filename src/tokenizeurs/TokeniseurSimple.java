package tokenizeurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokeniseurSimple implements ITokeniseur {

    @Override
    public List<String> tokeniser(String chaine) {
        if (chaine == null || chaine.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] parts = chaine.trim().split("\\s+");
        return new ArrayList<>(Arrays.asList(parts));
    }
}
