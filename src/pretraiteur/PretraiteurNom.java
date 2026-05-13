package pretraiteur;
import java.util.ArrayList;
import java.util.List;
import nom.Nom; 

import tokenizeurs.ITokeniseur;
import tokenizeurs.TokeniseurSimple;

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

        String brut = nom.getValeur();
        String normalise = pretraiteurChaine.pretraiter(brut);

        nom.setNomPretraite(normalise);

        List<String> tokens = tokeniseur.tokeniser(normalise);
        nom.setTokensPretraites(tokens);
        return nom;
    }

    @Override
    public List<Nom> pretraiterListe(List<Nom> noms) {
        List<Nom> resultat = new ArrayList<>();
        if (noms == null) {
            return resultat;
        }
        for (Nom n : noms) {
            resultat.add(pretraiter(n));
        }
        return resultat;
    }
}


