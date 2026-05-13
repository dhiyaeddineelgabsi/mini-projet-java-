package indexeur;

import java.util.List;
import java.util.Map;

import nom.Nom;

public interface Indexeur<K> {
    Map<K, List<Nom>> indexer(List<Nom> noms);
}
