import java.util.List;

public abstract class Exporteur extends Rapporteur {

    public abstract void exporter(List<TripletNom> triplets);

    @Override
    public void presenterRapport(List<TripletNom> triplets) {}
}