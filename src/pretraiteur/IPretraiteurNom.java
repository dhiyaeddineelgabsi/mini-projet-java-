package pretraiteur;

import java.util.List;

import nom.Nom;

public interface IPretraiteurNom {
    Nom pretraiter(Nom nom);
    List<Nom> pretraiterListe(List<Nom> noms);
}
