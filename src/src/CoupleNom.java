public class CoupleNom {
    private Nom nom1;
    private Nom nom2;

    public CoupleNom(Nom nom1, Nom nom2, double score) {
        this.nom1 = nom1;
        this.nom2 = nom2;
    }

    //Getters lehna

    public Nom getNom1() {
        return nom1;
    }

    public Nom getNom2() {
        return nom2;
    }

    //Setters
    public void setNom1(Nom n1){
        this.nom1=n1;
    }
    public void setNom2(Nom n2){
        this.nom2=n2;
    }

}