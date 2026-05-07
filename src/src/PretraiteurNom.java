import java.util.ArrayList;
import java.util.List;
public class PretraiteurNom implements Pretraiteur {

    private final PretraiteurChaine pretraiteurChaine;

    public PretraiteurNom() {
        this.pretraiteurChaine = new PretraiteurChaine();
    }

    public PretraiteurNom(PretraiteurChaine pretraiteurChaine) {
        this.pretraiteurChaine = pretraiteurChaine;
    }

    public Nom pretraiterNom(Nom nom) {
        if (nom == null) return null;

        String brut = nom.getNom();
        String normalise = pretraiteurChaine.pretraiterChaine(brut);
        normalise = normaliserOrdre(normalise);

        nom.setNomPretraite(normalise);
        return nom;
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

    @Override
    public String pretraiter(String chaine) {
        if (chaine == null) return "";
        Nom nom = new Nom(0, chaine);
        pretraiterNom(nom);
        String resultat = nom.getNomPretraite();
        return resultat != null ? resultat : "";
    }
}
