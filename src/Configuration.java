public class Configuration {


 private ComparateurNom comparateur;
    private double seuil;
    private IPretraiteurNom pretraiteur;
    private GenerateurCandidat generateur;

    public Configuration(ComparateurNom comparateur, double seuil,
                         IPretraiteurNom pretraiteur, GenerateurCandidat generateur) {
        this.comparateur = comparateur;
        this.seuil = seuil;
        this.pretraiteur = pretraiteur;
        this.generateur = generateur;
    }

    public ComparateurNom     getComparateur()  { return comparateur; }
    public double             getSeuil()         { return seuil; }
    public IPretraiteurNom    getPretraiteur()   { return pretraiteur; }
    public GenerateurCandidat getGenerateur()    { return generateur; }

    public void setComparateur(ComparateurNom c)   { this.comparateur = c; }
    public void setSeuil(double seuil)             { this.seuil = seuil; }
    public void setPretraiteur(IPretraiteurNom p)   { this.pretraiteur = p; }
    public void setGenerateur(GenerateurCandidat g){ this.generateur = g; }
}    
