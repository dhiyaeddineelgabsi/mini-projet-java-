package selectionneur;

import java.util.ArrayList;
import java.util.List;

import nom.CoupleNom;
import nom.Nom;
import nom.TripletNom;

public class MainSelectionneur {

    public static void main(String[] args) {
        List<TripletNom> triplets = exemplesTriplets();

        SelectionneurCandidat standard = new SelectionneurCandidatStandard();
        standard.selectCandidats(triplets);
        check(triplets.size() == 3, "SelectionneurCandidatStandard keeps all candidates");

        SelectionneurCandidat seuil = new SelectionneurCandidatSeuil();
        seuil.selectCandidats(triplets, 0.5);
        check(triplets.size() == 1, "SelectionneurCandidatSeuil removes low scores and nulls");
        check(triplets.get(0).getScore() == 0.8, "SelectionneurCandidatSeuil keeps high score");

        seuil.selectCandidats(null, 0.5);

        System.out.println("OK selectionneur: all tests passed");
    }

    private static List<TripletNom> exemplesTriplets() {
        List<TripletNom> triplets = new ArrayList<>();
        triplets.add(new TripletNom(new CoupleNom(new Nom(1, "Ahmed"), new Nom(2, "Ahmed")), 0.8));
        triplets.add(new TripletNom(new CoupleNom(new Nom(3, "Ali"), new Nom(4, "Jean")), 0.2));
        triplets.add(null);
        return triplets;
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
