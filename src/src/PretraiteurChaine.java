import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class PretraiteurChaine {

    // Mots vides specifiques aux noms arabes/francais/tunisiens
    private static final Set<String> MOTS_VIDES = new HashSet<>(Arrays.asList(
        "ben", "bel", "bou", "el", "al", "de", "du", "la", "le", "les",
        "dit", "dit", "dite", "saint", "sainte", "bint", "abu", "ould"
    ));

    public String pretraiterChaine(String s) {
        if (s == null || s.isEmpty()) return "";
        s = mettreEnMinuscules(s);
        s = supprimerAccents(s);
        s = supprimerPonctuation(s);
        s = supprimerMotsVides(s);
        s = normaliserEspaces(s);
        return s;
    }

    public String mettreEnMinuscules(String s) {
        if (s == null) return "";
        return s.toLowerCase();
    }

    // Supprime les accents via decomposition Unicode (NFD).
    public String supprimerAccents(String s) {
        if (s == null) return "";
        // NFD decompose les caracteres accentues en lettre + diacritique
        String nfd = Normalizer.normalize(s, Normalizer.Form.NFD);
        // \p{InCombiningDiacriticalMarks} cible uniquement les diacritiques
        return nfd.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }

    public String supprimerPonctuation(String s) {
        if (s == null) return "";
        return s.replaceAll("[^a-z0-9\\s]", "");
    }

    // Retire les particules listées dans MOTS_VIDES.
    public String supprimerMotsVides(String s) {
        if (s == null || s.isEmpty()) return "";
        String[] tokens = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            if (!MOTS_VIDES.contains(token)) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(token);
            }
        }
        return sb.toString();
    }

    public String normaliserEspaces(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\s+", " ");
    }
}
