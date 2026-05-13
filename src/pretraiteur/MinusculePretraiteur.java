package pretraiteur;
public class MinusculePretraiteur implements IPretraiteurChaine {
    @Override
    public String pretraiter(String chaine) {
        if (chaine == null) return "";
        return chaine.toLowerCase();
    }
}
