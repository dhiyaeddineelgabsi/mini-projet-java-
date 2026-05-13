import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexeurParTokenHashMap implements Indexeur<String> {

    @Override
    public Map<String, List<Nom>> indexer(List<Nom> noms) {
        Map<String, List<Nom>> index = new HashMap<>();

        for (Nom nom : noms) {
            if (!nom.estTokenise()) {
                continue;
            }

            Set<String> tokensUniques = new HashSet<>(nom.getTokens());

            for (String token : tokensUniques) {
                index.computeIfAbsent(token, k -> new ArrayList<>()).add(nom);
            }
        }

        return index;
    }
}