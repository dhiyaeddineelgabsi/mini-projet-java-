import java.util.List;
import java.util.Map;

public interface Indexeur<K> {
    Map<K, List<Nom>> indexer(List<Nom> noms);
}