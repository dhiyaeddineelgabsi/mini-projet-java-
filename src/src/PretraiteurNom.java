import java.util.ArrayList;
import java.util.List;
public class PretraiteurNom {

    private final PretraiteurChaine pretraiteurChaine;

    public PretraiteurNom() {
        this.pretraiteurChaine = new PretraiteurChaine();
    }

    public PretraiteurNom(PretraiteurChaine pretraiteurChaine) {
        this.pretraiteurChaine = pretraiteurChaine;
    }

    public Nom pretraiterNom(Nom nom) {
        if (nom == null) return null;

        String brut = nom.getNomComplet() != null
                ? nom.getNomComplet()
                : nom.getNom();

        String normalise = pretraiteurChaine.pretraiterChaine(brut);

        normalise = normaliserOrdre(normalise);

        Nom resultat = new Nom(nom.getId(), nom.getNom(), nom.getNomComplet());
        resultat.addNomPretraite(normalise);

        return resultat;
    }

    public List<Nom> pretraiterListe(List<Nom> noms) {
        List<Nom> resultat = new ArrayList<>();
        for (Nom n : noms) {
            resultat.add(pretraiterNom(n));
        }
        return resultat;
    }

    private String normaliserOrdre(String normalise) {
        if (normalise == null || normalise.isEmpty()) return normalise;

        String[] tokens = normalise.trim().split("\\s+");
        if (tokens.length < 2) return normalise;

        return normalise;
    }
}
