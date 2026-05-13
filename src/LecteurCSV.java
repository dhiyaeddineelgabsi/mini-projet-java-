import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nom.Nom;
public class LecteurCSV implements Lecteur{
    public List<Nom> lireFichier(String filepath) {
        List<Nom> noms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    noms.add(new Nom(parts[1].trim(), parts[0].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier: " + e.getMessage());
            return null;
        }
        return noms;
    }
}