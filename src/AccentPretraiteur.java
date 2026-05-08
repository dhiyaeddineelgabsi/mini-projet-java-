import java.text.Normalizer;

public class AccentPretraiteur implements IPretraiteurChaine {
    @Override
    public String pretraiter(String chaine) {
        if (chaine == null) return "";
        String specials = remplacerLettresSpeciales(chaine);
        String nfd = Normalizer.normalize(specials, Normalizer.Form.NFD);
        return nfd.replaceAll("\\p{M}+", "");
    }

    private String remplacerLettresSpeciales(String chaine) {
        String resultat = chaine;
        resultat = resultat.replace("\u0153", "oe"); // oe
        resultat = resultat.replace("\u0152", "OE"); // OE
        resultat = resultat.replace("\u00E6", "ae"); // ae
        resultat = resultat.replace("\u00C6", "AE"); // AE
        resultat = resultat.replace("\u00DF", "ss"); // ss
        resultat = resultat.replace("\u00F8", "o");  // o
        resultat = resultat.replace("\u00D8", "O");  // O
        resultat = resultat.replace("\u00F0", "d");  // d
        resultat = resultat.replace("\u00D0", "D");  // D
        resultat = resultat.replace("\u00FE", "th"); // th
        resultat = resultat.replace("\u00DE", "TH"); // TH
        resultat = resultat.replace("\u0142", "l");  // l
        resultat = resultat.replace("\u0141", "L");  // L
        resultat = resultat.replace("\u0111", "d");  // d
        resultat = resultat.replace("\u0110", "D");  // D
        return resultat;
    }
}
