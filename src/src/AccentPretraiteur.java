import java.text.Normalizer;

public class AccentPretraiteur implements IPretraiteurChaine {
    @Override
    public String pretraiter(String chaine) {
        if (chaine == null) return "";
        String nfd = Normalizer.normalize(chaine, Normalizer.Form.NFD);
        return nfd.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }
}
