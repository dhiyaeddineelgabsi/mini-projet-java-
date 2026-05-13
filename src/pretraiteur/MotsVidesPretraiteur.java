package pretraiteur;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MotsVidesPretraiteur implements IPretraiteurChaine {

    private static final Set<String> MOTS_VIDES = new HashSet<>(Arrays.asList(
        "ben", "bel", "bou", "el", "al", "de", "du", "la", "le", "les",
        "dit", "dite", "saint", "sainte", "bint", "abu", "ould"
    ));

    @Override
    public String pretraiter(String chaine) {
        if (chaine == null || chaine.isEmpty()) return "";
        String[] tokens = chaine.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            if (!MOTS_VIDES.contains(token)) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(token);
            }
        }
        return sb.toString();
    }
}
