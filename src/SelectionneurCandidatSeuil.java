import java.util.Iterator;
import java.util.List;

public class SelectionneurCandidatSeuil extends SelectionneurCandidat {

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