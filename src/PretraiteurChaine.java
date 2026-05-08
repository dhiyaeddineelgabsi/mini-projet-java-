import java.util.Arrays;
import java.util.List;

public class PretraiteurChaine implements IPretraiteurChaine {

    private final List<IPretraiteurChaine> etapes;

    public PretraiteurChaine() {
        this.etapes = Arrays.asList(
            new MinusculePretraiteur(),
            new AccentPretraiteur(),
            new PonctuationPretraiteur(),
            new MotsVidesPretraiteur(),
            new EspacesPretraiteur()
        );
    }

    @Override
    public String pretraiter(String chaine) {
        if (chaine == null || chaine.isEmpty()) return "";
        String resultat = chaine;
        for (IPretraiteurChaine etape : etapes) {
            resultat = etape.pretraiter(resultat);
        }
        return resultat;
    }
}
