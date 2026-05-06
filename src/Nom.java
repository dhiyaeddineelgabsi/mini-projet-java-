import java.util.ArrayList;
import java.util.List;

public class Nom {
    final private int id;
    final private String nom;
    private List<String> nomPretraite= new ArrayList<>();;
    private String sourceList;

    public Nom(int id, String nom, String nomComplet) {
        this.id = id;
        this.nom = nom;
        this.nomPretraite.add(nom);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDernierNomPretraite() {
        return nomPretraite;
    }


    public String getSourceList() {
        return sourceList;
    }




}