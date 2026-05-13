package pretraiteur;
import java.util.List;

public interface IPretraiteurNom {
    Nom pretraiter(Nom nom);
    List<Nom> pretraiterListe(List<Nom> noms);
}
