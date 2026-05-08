
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatching {

    private Configuration config;

    public MoteurDeMatching(Configuration config) {
        this.config = config;
    }

    public List<TripletNom> rechercheIndiv(Nom nomCible, List<Nom> listNoms) {
        if (nomCible == null || listNoms == null) return new ArrayList<>();

        List<Nom> liste1 = new ArrayList<>();
        liste1.add(nomCible);

        return rechercheParLot(liste1, listNoms);
    }

    public List<TripletNom> rechercheParLot(List<Nom> liste1, List<Nom> liste2) {
        if (liste1 == null || liste2 == null) return new ArrayList<>();

        IPretraiteurNom pretraiteur = config != null ? config.getPretraiteur() : null;
        List<Nom> prep1 = pretraiteur != null ? pretraiteur.pretraiterListe(liste1) : liste1;
        List<Nom> prep2 = pretraiteur != null ? pretraiteur.pretraiterListe(liste2) : liste2;

        GenerateurCandidat generateur = config != null ? config.getGenerateur() : null;
        List<CoupleNom> couples = generateur != null
                ? generateur.genererCandidats(prep1, prep2)
                : genererCandidatsDefaut(prep1, prep2);

        ComparateurNom comparateur = config != null ? config.getComparateur() : null;
        List<TripletNom> triplets = new ArrayList<>();
        for (CoupleNom couple : couples) {
            double score = comparateur != null
                    ? comparateur.comparerNom(couple.getNom1(), couple.getNom2())
                    : 0.0;
            triplets.add(new TripletNom(couple, score));
        }

        double seuil = config != null ? config.getSeuil() : 0.0;
        if (seuil > 0.0) {
            SelectionneurCandidatSeuil selecteur = new SelectionneurCandidatSeuil();
            selecteur.selectCandidats(triplets, seuil);
        }

        return triplets;
    }

    public void setSeuil(double seuil) {
        if (config != null) {
            config.setSeuil(seuil);
        }
    }

    public Configuration getConfig() {
        return config;
    }

    private List<CoupleNom> genererCandidatsDefaut(List<Nom> liste1, List<Nom> liste2) {
        GenerateurCandidatCartesien generateur = new GenerateurCandidatCartesien();
        return generateur.genererCandidats(liste1, liste2);
    }
}