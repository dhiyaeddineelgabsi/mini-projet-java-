package nom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

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

    public Nom(int id, String valeur) {
        this(String.valueOf(id), valeur, "");
    }

    public Nom(String id, String valeur, String source) {
        this.id = id;
        this.valeur = valeur;
        this.source = source;
        this.nomPretraite = "";
        this.tokensPretraites = new ArrayList<>();
        this.tokensUniques = new HashSet<>();
    }

    public Nom(int id, String valeur, String source) {
        this(String.valueOf(id), valeur, source);
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

    public String getNom() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public void setNom(String valeur) {
        this.valeur = valeur;
    }

    public String getNomPretraite() {
        return nomPretraite;
    }

    public String getDernierNomPretraite() {
        return nomPretraite;
    }

    public void setNomPretraite(String nomPretraite) {
        this.nomPretraite = nomPretraite;
    }

    public List<String> getTokensPretraites() {
        return tokensPretraites;
    }

    public List<String> getTokens() {
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

    public void setTokens(List<String> tokens) {
        setTokensPretraites(tokens);
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

    public String getSourceList() {
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

    public int getNombreTokens() {
        if (tokensPretraites == null) {
            return 0;
        }
        return tokensPretraites.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Nom)) return false;

        Nom autre = (Nom) o;

        return Objects.equals(this.id, autre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Nom{id='" + id + "', valeur='" + valeur
                + "', source='" + source + "', nomPretraite='"
                + nomPretraite + "', tokens=" + tokensPretraites + "}";
    }
}