package nom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nom {
    private String id;
    private String valeur;
    private String nomPretraite;
    private List<String> tokensPretraites;
    private Set<String> tokensUniques;
    private String source;

    public Nom(String id, String valeur) {
        this(id, valeur, "");
    }

    public Nom(String id, String valeur, String source) {
        this.id = id;
        this.valeur = valeur;
        this.source = source;
        this.nomPretraite = "";
        this.tokensPretraites = new ArrayList<>();
        this.tokensUniques = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getNomPretraite() {
        return nomPretraite;
    }

    public void setNomPretraite(String nomPretraite) {
        this.nomPretraite = nomPretraite;
    }

    public List<String> getTokensPretraites() {
        return tokensPretraites;
    }

    public void setTokensPretraites(List<String> tokensPretraites) {
        if (tokensPretraites == null) {
            this.tokensPretraites = new ArrayList<>();
        } else {
            this.tokensPretraites = new ArrayList<>(tokensPretraites);
        }
        this.tokensUniques = new HashSet<>(this.tokensPretraites);
    }

    public Set<String> getTokensUniques() {
        return tokensUniques;
    }

    public void setTokensUniques(Set<String> tokensUniques) {
        if (tokensUniques == null) {
            this.tokensUniques = new HashSet<>();
        } else {
            this.tokensUniques = new HashSet<>(tokensUniques);
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean estPretraite() {
        return nomPretraite != null && !nomPretraite.isEmpty();
    }

    public boolean estTokenise() {
        return tokensPretraites != null && !tokensPretraites.isEmpty();
    }

    @Override
    public String toString() {
        return "Nom{id='" + id + "', valeur='" + valeur
                + "', source='" + source + "', nomPretraite='"
                + nomPretraite + "'}";
    }
}
