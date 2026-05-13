import java.util.List;

import nom.Nom;

public interface Lecteur {
    List<Nom> lireFichier(String filepath);
}