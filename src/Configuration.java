import generator.GenerateurCandidat;
import generator.GenerateurCandidatToken;
import pretraiteur.PretraiteurNom;

public class Configuration {

    private ComparateurNom comparateur;
    private double seuil;
    private PretraiteurNom pretraiteur;
    private GenerateurCandidat generateur;
    private int N = 10;
    private double pourcentage = 0.5;
    private SelectionneurCandidat selectionneur;
    private Exporteur exporteur;
    private Rapporteur rapporteur;
    private String cheminCSV;
    private String langue;
 
    public Configuration(ComparateurNom comparateur, double seuil,
                         PretraiteurNom pretraiteur, GenerateurCandidat generateur) {
        this.comparateur = comparateur;
        this.seuil = seuil;
        this.pretraiteur = pretraiteur;
        this.generateur = generateur;
        this.selectionneur = new SelectionneurCandidatSeuil();
        this.exporteur = new ExporteurCSV("alertes.csv");
        this.rapporteur = new Afficheur();
        this.cheminCSV = "";
        this.langue = "fr";
    }

    public ComparateurNom     getComparateur()  { return comparateur; }
    public double             getSeuil()         { return seuil; }
    public PretraiteurNom     getPretraiteur()   { return pretraiteur; }
    public GenerateurCandidat getGenerateur()    { return generateur; }
    public int                getN()             { return N; }
    public double             getPourcentage()   { return pourcentage; }
    public SelectionneurCandidat getSelectionneur() { return selectionneur; }
    public Exporteur          getExporteur()     { return exporteur; }
    public Rapporteur         getRapporteur()    { return rapporteur; }
    public String             getCheminCSV()     { return cheminCSV; }
    public String             getLangue()        { return langue; }

    public void setComparateur(ComparateurNom c)   { this.comparateur = c; }
    public void setSeuil(double seuil)             { this.seuil = seuil; }
    public void setPretraiteur(PretraiteurNom p)   { this.pretraiteur = p; }
    public void setGenerateur(GenerateurCandidat g){ this.generateur = g; }
    public void setN(int N)                        { this.N = N; }
    public void setPourcentage(double pourcentage) { this.pourcentage = pourcentage; }
    public void setSelectionneur(SelectionneurCandidat selectionneur) {
        this.selectionneur = selectionneur;
    }
    public void setExporteur(Exporteur exporteur)  { this.exporteur = exporteur; }
    public void setRapporteur(Rapporteur rapporteur) { this.rapporteur = rapporteur; }
    public void setCheminCSV(String cheminCSV)     { this.cheminCSV = cheminCSV; }
    public void setLangue(String langue)           { this.langue = langue; }

    public static Configuration defaut() {
        Configuration config = new Configuration(
                new ComparateurNom(),
                0.75,
                new PretraiteurNom(),
                new GenerateurCandidatToken()
        );
        config.setSelectionneur(new SelectionneurCandidatSeuil());
        config.setN(10);
        config.setPourcentage(0.5);
        config.setExporteur(new ExporteurCSV("alertes.csv"));
        config.setRapporteur(new Afficheur());
        config.setCheminCSV("");
        config.setLangue("fr");
        return config;
    }
}




