package indexeur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nom.Nom;

public class IndexeurParNombreTokensHashMap implements Indexeur<Integer> {

    @Override
    public Map<Integer, List<Nom>> indexer(List<Nom> noms) {
        Map<Integer, List<Nom>> index = new HashMap<>();

        if (noms == null) {
            return index;
        }

        for (Nom nom : noms) {
            if (nom == null || !nom.estTokenise()) {
                continue;
            }

            int nombreTokens = nom.getNombreTokens();
            index.computeIfAbsent(nombreTokens, k -> new ArrayList<>()).add(nom);
        }

        return index;
    }
}
