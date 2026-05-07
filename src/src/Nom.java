public class Nom {
    private int id;
    private String nom;
    private String nomComplet;
    private java.util.List<String> nomPretraite = new java.util.ArrayList<>();
    private String sourceList;

    public Nom(int id, String nom, String nomComplet) {
        this.id = id;
        this.nom = nom;
        this.nomComplet = nomComplet;
        this.nomPretraite.add(nom);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getDernierNomPretraite() {
        if (nomPretraite.isEmpty()) {
            return null;
        }
        return nomPretraite.get(nomPretraite.size() - 1);
    }

    public String getSourceList() {
        return sourceList;
    }




}