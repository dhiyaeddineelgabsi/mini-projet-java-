package indexeur;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import nom.Nom;

public class IndexeurParTokenTreeMap implements Indexeur<String> {

    @Override
    public TreeMap<String, List<Nom>> indexer(List<Nom> noms) {
        TreeMap<String, List<Nom>> index = new TreeMap<>();

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
