package selectionneur;

import java.util.Iterator;
import java.util.List;

import nom.TripletNom;

public class SelectionneurCandidatSeuil extends SelectionneurCandidat {

    @Override
    public void selectCandidats(List<TripletNom> triplets, double seuil) {
        if (triplets == null) {
            return;
        }

        Iterator<TripletNom> it = triplets.iterator();

        while (it.hasNext()) {
            TripletNom item = it.next();

            if (item == null || item.getScore() < seuil) {
                it.remove();
            }
        }
    }
}
