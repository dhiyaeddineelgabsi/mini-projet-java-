package indexeur;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import nom.Nom;

public class IndexeurParNombreTokensTreeMap implements Indexeur<Integer> {

    @Override
    public TreeMap<Integer, List<Nom>> indexer(List<Nom> noms) {
        TreeMap<Integer, List<Nom>> index = new TreeMap<>();

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
