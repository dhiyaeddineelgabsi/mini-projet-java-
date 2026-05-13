import java.util.Iterator;
import java.util.List;

import nom.TripletNom;

public class SelectionneurCandidatSeuil extends SelectionneurCandidat {

    @Override
    public void selectCandidats(List<TripletNom> triple, double seuil) {
        Iterator<TripletNom> it = triple.iterator();

        while (it.hasNext()) {
            TripletNom item = it.next();
            double score = item.getScore();

            if (score < seuil) {
                it.remove();
            }
        }
    }
}