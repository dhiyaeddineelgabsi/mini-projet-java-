public class Configuration {


 private ComparateurNom comparateur;
    private double seuil;
    private IPretraiteurNom pretraiteur;
    private GenerateurCandidat generateur;

    public Configuration(ComparateurNom comparateur, double seuil,
                         IPretraiteurNom pretraiteur, GenerateurCandidat generateur) {}

    public ComparateurNom     getComparateur()  { return comparateur; }
    public double             getSeuil()         { return seuil; }
    public IPretraiteurNom    getPretraiteur()   { return pretraiteur; }
    public GenerateurCandidat getGenerateur()    { return generateur; }

    public void setComparateur(ComparateurNom c)   {}
    public void setSeuil(double seuil)             {}
    public void setPretraiteur(IPretraiteurNom p)   {}
    public void setGenerateur(GenerateurCandidat g){}
}    
