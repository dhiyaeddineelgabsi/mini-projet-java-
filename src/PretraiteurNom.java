import java.util.ArrayList;
import java.util.List;
public class PretraiteurNom implements IPretraiteurNom {

    private final IPretraiteurChaine pretraiteurChaine;

    public PretraiteurNom() {
        this.pretraiteurChaine = new PretraiteurChaine();
    }

    public PretraiteurNom(IPretraiteurChaine pretraiteurChaine) {
        this.pretraiteurChaine = pretraiteurChaine;
    }

    @Override
    public Nom pretraiter(Nom nom) {
        if (nom == null) return null;

        String brut = nom.getNom();
        String normalise = pretraiteurChaine.pretraiter(brut);

        nom.setNomPretraite(normalise);
        return nom;
    }

    @Override
    public List<Nom> pretraiterListe(List<Nom> noms) {
        List<Nom> resultat = new ArrayList<>();
        for (Nom n : noms) {
            resultat.add(pretraiter(n));
        }
        return resultat;
    }
}
