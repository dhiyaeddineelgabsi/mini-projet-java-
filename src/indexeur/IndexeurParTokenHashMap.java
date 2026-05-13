package indexeur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nom.Nom;

public class IndexeurParTokenHashMap implements Indexeur<String> {

    @Override
    public Map<String, List<Nom>> indexer(List<Nom> noms) {
        Map<String, List<Nom>> index = new HashMap<>();

        if (noms == null) {
            return index;
        }

        for (Nom nom : noms) {
            if (nom == null || !nom.estTokenise()) {
                continue;
            }

            for (String token : nom.getTokensUniques()) {
                index.computeIfAbsent(token, k -> new ArrayList<>()).add(nom);
            }
        }

        return index;
    }
}
