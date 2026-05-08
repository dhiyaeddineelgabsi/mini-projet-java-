import java.util.List;

public class MoteurDeMatching {

    private Configuration config;

    public MoteurDeMatching(Configuration config) {
        this.config = config;
    }

    public List<TripletNom> rechercheIndiv(Nom nomCible, List<Nom> listNoms) {
        return null;
    }

    public List<TripletNom> rechercheParLot(List<Nom> liste1, List<Nom> liste2) {
        return null;
    }

    public void setSeuil(double seuil) {}

    public Configuration getConfig() {
        return config;
    }
}