package pretraiteur;
public class PonctuationPretraiteur implements IPretraiteurChaine {
    @Override
    public String pretraiter(String chaine) {
        if (chaine == null) return "";
        return chaine.replaceAll("[^a-z0-9\\s]", "");
    }
}
