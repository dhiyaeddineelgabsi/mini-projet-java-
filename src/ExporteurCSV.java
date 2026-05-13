import java.util.List;

import nom.TripletNom;

public class ExporteurCSV extends Exporteur {

	private final String chemin;

	public ExporteurCSV(String chemin) {
		this.chemin = chemin;
	}

	public String getChemin() {
		return chemin;
	}

	@Override
	public void exporter(List<TripletNom> triplets) {
		// TODO: implement CSV export
	}
}
