import java.util.List;

import nom.TripletNom;

public abstract class Exporteur extends Rapporteur {

    public abstract void exporter(List<TripletNom> triplets);

    @Override
    public void presenterRapport(List<TripletNom> triplets) {}
}