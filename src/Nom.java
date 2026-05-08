import java.util.ArrayList;
import java.util.List;
public class Nom {
    private int id;
    private String nom;
    private List<String> nomPretraite = new ArrayList<>();
    private String sourceList;

    public Nom(int id, String nom) {
        this.id = id;
        this.nom = nom;
        if (nom != null) this.nomPretraite.add(nom);
    }
    public Nom(int id, String nom, String sourceList) {
        this.id = id;
        this.nom = nom;
        this.sourceList=sourceList;
        if (nom != null) this.nomPretraite.add(nom);
    }

    public int getId() { return id; }

    public String getNom() { return nom; }

    public String getSourceList() { return sourceList; }

    // Derniere version pretraitee, ou null si vide.
    public String getDernierNomPretraite() {
        if (nomPretraite.isEmpty()) return null;
        return nomPretraite.get(nomPretraite.size() - 1);
    }

    public List<String> getNomPretraite() { return nomPretraite; }

    public void setSourceList(String sourceList) {
        this.sourceList = sourceList;
    }

    public void addNomPretraite(String nomNormalise) {
        if (nomNormalise != null) this.nomPretraite.add(nomNormalise);
    }

    @Override
    public String toString() {
        return "Nom{id=" + id
             + ", nom='" + nom + "'"
             + ", pretraite='" + getDernierNomPretraite() + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nom)) return false;
        Nom autre = (Nom) o;
        return id == autre.id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }
}
