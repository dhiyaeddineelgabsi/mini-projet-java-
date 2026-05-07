public class Nom {
    private int id;
    private String nom;
    private String nomPretraite;
    private String sourceList;

    public Nom(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.nomPretraite = null;
    }

    public int getId() { return id; }

    public String getNom() { return nom; }

    public String getSourceList() { return sourceList; }

    public String getNomPretraite() { return nomPretraite; }

    public void setSourceList(String sourceList) {
        this.sourceList = sourceList;
    }

    public void setNomPretraite(String nomPretraite) { this.nomPretraite = nomPretraite; }

    @Override
    public String toString() {
        return "Nom{id=" + id
             + ", nom='" + nom + "'"
             + ", pretraite='" + nomPretraite + "'}";
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
