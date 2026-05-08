import java.util.ArrayList;
import java.util.List;

public class GenerateurCandidatLongeurN implements GenerateurCandidat {
    private int n;
    public GenerateurCandidatLongeurN(int n) {
        this.n = n;
    }
    public int getLongeurN(){
        return(this.n);
    }


    public List<CoupleNom> genererCandidats(Nom nomRecherche, List<Nom> listselectionne){
        List<CoupleNom> candidats=new ArrayList<>() ;

        int longeur = nomRecherche.getNom().length();
        for (Nom targetNom : listselectionne){
            if (longeur - n < targetNom.getNom().length() && targetNom.getNom().length() < longeur + n){
                CoupleNom coupleCandidat=new CoupleNom(nomRecherche,targetNom);
                candidats.add(coupleCandidat);
            }
        }
        return candidats;
    }

}
