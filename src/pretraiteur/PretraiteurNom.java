package pretraiteur;
import java.util.ArrayList;
import java.util.List;
public class PretraiteurNom implements IPretraiteurNom {

    private final IPretraiteurChaine pretraiteurChaine;
    private final ITokeniseur tokeniseur;

    public PretraiteurNom() {
        this.pretraiteurChaine = new PretraiteurChaine();
        this.tokeniseur = new TokeniseurSimple();
    }

    public PretraiteurNom(IPretraiteurChaine pretraiteurChaine) {
        this.pretraiteurChaine = pretraiteurChaine;
        this.tokeniseur = new TokeniseurSimple();
    }

    public PretraiteurNom(IPretraiteurChaine pretraiteurChaine, ITokeniseur tokeniseur) {
        this.pretraiteurChaine = pretraiteurChaine;
        this.tokeniseur = tokeniseur;
    }

    @Override
    public Nom pretraiter(Nom nom) {
        if (nom == null) return null;

        String brut = nom.getNom();
        String normalise = pretraiteurChaine.pretraiter(brut);
        nom.addNomPretraite(normalise);

        List<String> tokens = tokeniseur.tokeniser(normalise);
        nom.setTokens(tokens);

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


