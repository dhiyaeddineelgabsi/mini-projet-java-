package generator;
import java.util.ArrayList;
import java.util.List;

import nom.CoupleNom;
import nom.Nom;

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

        int longeur = nomRecherche.getValeur().length();
        for (Nom targetNom : listselectionne){
            if (Math.abs(longeur - targetNom.getValeur().length()) <= n) {
                CoupleNom coupleCandidat = new CoupleNom(nomRecherche, targetNom);
                candidats.add(coupleCandidat);
            }
        }
        return candidats;
    }

}
